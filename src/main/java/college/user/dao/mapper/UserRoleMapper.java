package college.user.dao.mapper;


import college.user.dao.entity.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    default List<UserRoleDO> selectListByUserId(Long userId) {
        return selectList(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, userId));
    }

    default void deleteListByUserIdAndRoleIdIds(Long userId, Collection<Long> roleIds) {
        delete(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, userId).in(UserRoleDO::getRoleId, roleIds));
    }

    default void deleteListByUserId(Long userId) {
        delete(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, userId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getRoleId, roleId));
    }

    default List<UserRoleDO> selectListByRoleIds(Collection<Long> roleIds) {
        return selectList(Wrappers.lambdaQuery(UserRoleDO.class).in(UserRoleDO::getRoleId, roleIds));
    }

    default void insertBatch(List<UserRoleDO> userRoleDOS){
        for (UserRoleDO userRoleDO : userRoleDOS) {
            insert(userRoleDO);
        }
    }
}
