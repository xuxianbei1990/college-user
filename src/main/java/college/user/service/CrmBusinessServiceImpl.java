package college.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;

import college.user.annotation.CrmPermission;
import college.user.controller.bo.CrmPermissionCreateReqBO;
import college.user.controller.vo.business.CrmBusinessPageReqVO;
import college.user.controller.vo.business.CrmBusinessSaveReqVO;
import college.user.dao.entity.CrmBusinessDO;
import college.user.dao.entity.CrmBusinessProductDO;
import college.user.dao.mapper.CrmBusinessMapper;
import college.user.dao.mapper.CrmBusinessProductMapper;
import college.user.enums.CrmBizTypeEnum;
import college.user.enums.CrmPermissionLevelEnum;
import college.user.util.BeanUtils;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static college.user.enums.ErrorCodeConstants.BUSINESS_NOT_EXISTS;
import static college.user.enums.LogRecordConstants.*;
import static college.user.util.CollectionUtils.convertList;
import static college.user.util.CollectionUtils.convertSet;
import static college.user.util.ServiceExceptionUtil.exception;


/**
 * 商机 Service 实现类
 *
 * @author ljlleo
 */
@Service
@Validated
public class CrmBusinessServiceImpl implements CrmBusinessService {

    @Resource
    private CrmBusinessMapper businessMapper;

    @Resource
    private CrmBusinessStatusService businessStatusService;

    @Resource
    private CrmPermissionService permissionService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = CRM_BUSINESS_TYPE, subType = CRM_BUSINESS_CREATE_SUB_TYPE, bizNo = "{{#business.id}}",
            success = CRM_BUSINESS_CREATE_SUCCESS)
    public Long createBusiness(CrmBusinessSaveReqVO createReqVO, Long userId) {
        // 1.2 校验关联字段
        validateRelationDataExists(createReqVO);

        // 2.1 插入商机
        CrmBusinessDO business = BeanUtils.toBean(createReqVO, CrmBusinessDO.class);
        business.setStatusId(businessStatusService.getBusinessStatusListByTypeId(createReqVO.getStatusTypeId()).get(0).getId()); // 默认状态

        businessMapper.insert(business);

        CrmPermissionCreateReqBO permissionCreateReqBO = new CrmPermissionCreateReqBO();
        permissionCreateReqBO.setUserId(userId);
        permissionCreateReqBO.setBizType(CrmBizTypeEnum.CRM_BUSINESS.getType());
        permissionCreateReqBO.setBizId(business.getId());
        permissionCreateReqBO.setLevel(CrmPermissionLevelEnum.OWNER.getLevel());
        // 3. 创建数据权限
        permissionService.createPermission(permissionCreateReqBO);

        // 5. 记录操作日志上下文
        LogRecordContext.putVariable("business", business);
        return business.getId();
    }

    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CUSTOMER, bizId = "#pageReqVO.customerId", level = CrmPermissionLevelEnum.READ)
    public PageResult<CrmBusinessDO> getBusinessPageByCustomerId(CrmBusinessPageReqVO pageReqVO) {
        return businessMapper.selectPageByCustomerId(pageReqVO);
    }

    @Override
    public PageResult<CrmBusinessDO> getBusinessPage(CrmBusinessPageReqVO pageReqVO, Long userId) {
        IPage<CrmBusinessDO> iPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        businessMapper.selectPage(iPage, Wrappers.lambdaQuery(CrmBusinessDO.class).eq(CrmBusinessDO::getId, userId));
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    private void validateRelationDataExists(CrmBusinessSaveReqVO saveReqVO) {
        // 校验商机状态
        if (saveReqVO.getStatusTypeId() != null) {
            businessStatusService.validateBusinessStatusType(saveReqVO.getStatusTypeId());
        }
    }

}
