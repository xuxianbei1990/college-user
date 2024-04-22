package college.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import college.user.controller.bo.CrmPermissionCreateReqBO;
import college.user.controller.vo.permission.CrmPermissionUpdateReqVO;
import college.user.dao.entity.CrmPermissionDO;
import college.user.dao.mapper.CrmPermissionMapper;
import college.user.enums.CrmPermissionLevelEnum;
import college.user.util.BeanUtils;
import college.user.util.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static college.user.enums.ErrorCodeConstants.*;
import static college.user.util.CollectionUtils.convertSet;
import static college.user.util.ServiceExceptionUtil.exception;


/**
 * CRM 数据权限 Service 接口实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class CrmPermissionServiceImpl implements CrmPermissionService {

    @Resource
    private CrmPermissionMapper permissionMapper;

    @Resource
    private AdminUserApi adminUserApi;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPermission(CrmPermissionCreateReqBO createReqBO) {
        validatePermissionNotExists(Collections.singletonList(createReqBO));
        // 1. 校验用户是否存在
        adminUserApi.validateUserList(Collections.singletonList(createReqBO.getUserId()));

        // 2. 创建
        CrmPermissionDO permission = BeanUtils.toBean(createReqBO, CrmPermissionDO.class);
        permissionMapper.insert(permission);
        return permission.getId();
    }

    @Override
    public void deletePermissionBatch(Collection<Long> ids, Long userId) {
        List<CrmPermissionDO> permissions = permissionMapper.selectBatchIds(ids);
        if (CollUtil.isEmpty(permissions)) {
            throw exception(CRM_PERMISSION_NOT_EXISTS);
        }
        // 校验：数据权限的模块数据编号是一致的不可能存在两个
        if (convertSet(permissions, CrmPermissionDO::getBizId).size() > 1) {
            throw exception(CRM_PERMISSION_DELETE_FAIL);
        }
        // 校验操作人是否为负责人
        CrmPermissionDO permission = permissionMapper.selectByBizIdAndUserId(permissions.get(0).getBizId(), userId);
        if (permission == null) {
            throw exception(CRM_PERMISSION_DELETE_DENIED);
        }
        if (!CrmPermissionLevelEnum.isOwner(permission.getLevel())) {
            throw exception(CRM_PERMISSION_DELETE_DENIED);
        }

        // 删除数据权限
        permissionMapper.deleteBatchIds(ids);
    }

    @Override
    public void deleteSelfPermission(Long id, Long userId) {
        // 校验数据存在且是自己
        CrmPermissionDO permission = permissionMapper.selectByIdAndUserId(id, userId);
        if (permission == null) {
            throw exception(CRM_PERMISSION_NOT_EXISTS);
        }
        // 校验是否是负责人
        if (CrmPermissionLevelEnum.isOwner(permission.getLevel())) {
            throw exception(CRM_PERMISSION_DELETE_SELF_PERMISSION_FAIL_EXIST_OWNER);
        }

        // 删除
        permissionMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(CrmPermissionUpdateReqVO updateReqVO) {
        // 1. 校验存在
        validatePermissionExists(updateReqVO.getIds());
        // 2. 更新
        List<CrmPermissionDO> updateList = CollectionUtils.convertList(updateReqVO.getIds(),
                id -> {
                    CrmPermissionDO permission = new CrmPermissionDO();
                    permission.setId(id);
                    permission.setLevel(updateReqVO.getLevel());
                    return permission;
                });
        permissionMapper.updateBatch(updateList);
    }

    private void validatePermissionExists(Collection<Long> ids) {
        List<CrmPermissionDO> permissionList = permissionMapper.selectBatchIds(ids);
        if (ObjUtil.notEqual(permissionList.size(), ids.size())) {
            throw exception(CRM_PERMISSION_NOT_EXISTS);
        }
    }

    private void validatePermissionNotExists(Collection<CrmPermissionCreateReqBO> createReqBOs) {
        Set<Integer> bizTypes = convertSet(createReqBOs, CrmPermissionCreateReqBO::getBizType);
        Set<Long> bizIds = convertSet(createReqBOs, CrmPermissionCreateReqBO::getBizId);
        Set<Long> userIds = convertSet(createReqBOs, CrmPermissionCreateReqBO::getUserId);
        Long count = permissionMapper.selectListByBiz(bizTypes, bizIds, userIds);
        if (count > 0) {
            throw exception(CRM_PERMISSION_CREATE_FAIL);
        }
    }

    @Override
    public List<CrmPermissionDO> getPermissionListByBiz(Integer bizType, Collection<Long> bizIds) {
        return permissionMapper.selectByBizTypeAndBizIds(bizType, bizIds);
    }

    @Override
    public List<CrmPermissionDO> getPermissionListByBiz(Integer bizType, Long bizId) {
        return permissionMapper.selectByBizTypeAndBizId(bizType, bizId);
    }

}
