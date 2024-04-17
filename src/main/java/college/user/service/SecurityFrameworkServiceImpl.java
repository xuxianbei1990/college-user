package college.user.service;

import cn.hutool.core.collection.CollUtil;
import college.user.util.LoginUser;
import college.user.util.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static college.user.util.SecurityFrameworkUtils.getLoginUserId;


/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 * @author 芋道源码
 */
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

    private final PermissionService permissionApi;

//    /**
//     * 针对 {@link #hasAnyRoles(String...)} 的缓存
//     */
//    private final LoadingCache<KeyValue<Long, List<String>>, Boolean> hasAnyRolesCache = CacheUtils.buildAsyncReloadingCache(
//            Duration.ofMinutes(1L), // 过期时间 1 分钟
//            new CacheLoader<KeyValue<Long, List<String>>, Boolean>() {
//
//                @Override
//                public Boolean load(KeyValue<Long, List<String>> key) {
//                    return permissionApi.hasAnyRoles(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
//                }
//
//            });

    /**
     * 针对 {@link #hasAnyPermissions(String...)} 的缓存
     */
//    private final LoadingCache<KeyValue<Long, List<String>>, Boolean> hasAnyPermissionsCache = CacheUtils.buildAsyncReloadingCache(
//            Duration.ofMinutes(1L), // 过期时间 1 分钟
//            new CacheLoader<KeyValue<Long, List<String>>, Boolean>() {
//
//                @Override
//                public Boolean load(KeyValue<Long, List<String>> key) {
//                    return permissionApi.hasAnyPermissions(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
//                }
//
//            });

    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    @Override
    @SneakyThrows
    public boolean hasAnyPermissions(String... permissions) {
        return permissionApi.hasAnyPermissions(getLoginUserId(), permissions);
    }

    @Override
    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    @Override
    @SneakyThrows
    public boolean hasAnyRoles(String... roles) {
        return permissionApi.hasAnyRoles(getLoginUserId(), roles);
    }

    @Override
    public boolean hasScope(String scope) {
        return hasAnyScopes(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scope) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return false;
        }
        return CollUtil.containsAny(user.getScopes(), Arrays.asList(scope));
    }

}
