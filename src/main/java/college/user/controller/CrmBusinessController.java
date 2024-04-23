package college.user.controller;

import cn.hutool.core.collection.CollUtil;
import college.user.controller.vo.business.CrmBusinessPageReqVO;
import college.user.controller.vo.business.CrmBusinessRespVO;
import college.user.controller.vo.business.CrmBusinessSaveReqVO;
import college.user.dao.entity.CrmBusinessDO;
import college.user.dao.entity.CrmBusinessStatusDO;
import college.user.dao.entity.CrmBusinessStatusTypeDO;
import college.user.dao.entity.CrmCustomerDO;
import college.user.dto.AdminUserRespDTO;
import college.user.dto.DeptRespDTO;
import college.user.service.*;
import college.user.util.BeanUtils;
import college.user.util.MapUtils;
import college.user.util.NumberUtils;
import college.user.vo.CommonResult;
import college.user.vo.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static college.user.enums.ErrorCodeConstants.CUSTOMER_NOT_EXISTS;
import static college.user.util.CollectionUtils.convertListByFlatMap;
import static college.user.util.CollectionUtils.convertSet;
import static college.user.util.SecurityFrameworkUtils.getLoginUserId;
import static college.user.util.ServiceExceptionUtil.exception;
import static college.user.vo.CommonResult.success;

/**
 * User: EDY
 * Date: 2024/4/23
 * Time: 9:52
 * Version:V1.0
 */
@Tag(name = "管理后台 - CRM 商机")
@RestController
@RequestMapping("/crm/business")
@Validated
public class CrmBusinessController {

    @Resource
    private CrmBusinessService businessService;

    @Resource
    private CrmCustomerService customerService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private DeptApi deptApi;

    @Resource
    private CrmBusinessStatusService businessStatusTypeService;

    @Resource
    private CrmBusinessStatusService businessStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建商机")
    @PreAuthorize("@ss.hasPermission('crm:business:create')")
    public CommonResult<Long> createBusiness(@Valid @RequestBody CrmBusinessSaveReqVO createReqVO) {
        return success(businessService.createBusiness(createReqVO, getLoginUserId()));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商机分页")
    @PreAuthorize("@ss.hasPermission('crm:business:query')")
    public CommonResult<PageResult<CrmBusinessRespVO>> getBusinessPage(@Valid CrmBusinessPageReqVO pageVO) {
        PageResult<CrmBusinessDO> pageResult = businessService.getBusinessPage(pageVO, getLoginUserId());
        return success(new PageResult<>(buildBusinessDetailList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/page-by-customer")
    @Operation(summary = "获得商机分页，基于指定客户")
    public CommonResult<PageResult<CrmBusinessRespVO>> getBusinessPageByCustomer(@Valid CrmBusinessPageReqVO pageReqVO) {
        if (pageReqVO.getCustomerId() == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
        PageResult<CrmBusinessDO> pageResult = businessService.getBusinessPageByCustomerId(pageReqVO);
        return success(new PageResult<>(buildBusinessDetailList(pageResult.getList()), pageResult.getTotal()));
    }

    private List<CrmBusinessRespVO> buildBusinessDetailList(List<CrmBusinessDO> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        // 1.1 获取客户列表
        Map<Long, CrmCustomerDO> customerMap = customerService.getCustomerMap(
                convertSet(list, CrmBusinessDO::getCustomerId));
        // 1.2 获取创建人、负责人列表
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertListByFlatMap(list,
                contact -> Stream.of(NumberUtils.parseLong(contact.getCreator()), contact.getOwnerUserId())));
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userMap.values(), AdminUserRespDTO::getDeptId));
        // 1.3 获得商机状态组
        Map<Long, CrmBusinessStatusTypeDO> statusTypeMap = businessStatusTypeService.getBusinessStatusTypeMap(
                convertSet(list, CrmBusinessDO::getStatusTypeId));
        Map<Long, CrmBusinessStatusDO> statusMap = businessStatusService.getBusinessStatusMap(
                convertSet(list, CrmBusinessDO::getStatusId));
        // 2. 拼接数据
        return BeanUtils.toBean(list, CrmBusinessRespVO.class, businessVO -> {
            // 2.1 设置客户名称
            MapUtils.findAndThen(customerMap, businessVO.getCustomerId(), customer -> businessVO.setCustomerName(customer.getName()));
            // 2.2 设置创建人、负责人名称
            MapUtils.findAndThen(userMap, NumberUtils.parseLong(businessVO.getCreator()),
                    user -> businessVO.setCreatorName(user.getNickname()));
            MapUtils.findAndThen(userMap, businessVO.getOwnerUserId(), user -> {
                businessVO.setOwnerUserName(user.getNickname());
                MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> businessVO.setOwnerUserDeptName(dept.getName()));
            });
            // 2.3 设置商机状态
            MapUtils.findAndThen(statusTypeMap, businessVO.getStatusTypeId(), statusType -> businessVO.setStatusTypeName(statusType.getName()));
            MapUtils.findAndThen(statusMap, businessVO.getStatusId(), status -> businessVO.setStatusName(
                    businessService.getBusinessStatusName(businessVO.getEndStatus(), status)));
        });
    }
}
