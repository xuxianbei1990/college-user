package college.user.service;


import college.user.rule.dept.DeptDataPermissionRespDTO;
import college.user.vo.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.Set;

import static college.user.vo.CommonResult.success;


@Service // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class PermissionApiImpl implements PermissionApi {

    @Resource
    private PermissionService permissionService;

    @Override
    public CommonResult<Set<Long>> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return success(permissionService.getUserRoleIdListByRoleId(roleIds));
    }

    @Override
    public CommonResult<Boolean> hasAnyPermissions(Long userId, String... permissions) {
        return success(permissionService.hasAnyPermissions(userId, permissions));
    }

    @Override
    public CommonResult<Boolean> hasAnyRoles(Long userId, String... roles) {
        return success(permissionService.hasAnyRoles(userId, roles));
    }

    @Override
    public CommonResult<DeptDataPermissionRespDTO> getDeptDataPermission(Long userId) {
        return success(permissionService.getDeptDataPermission(userId));
    }

}
