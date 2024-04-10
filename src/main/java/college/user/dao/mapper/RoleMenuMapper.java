package college.user.dao.mapper;


import college.user.dao.entity.RoleMenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    default List<RoleMenuDO> selectListByRoleId(Long roleId) {
        return selectList(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, roleId));
    }

    default List<RoleMenuDO> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(Wrappers.lambdaQuery(RoleMenuDO.class).in(RoleMenuDO::getRoleId, roleIds));
    }

    default List<RoleMenuDO> selectListByMenuId(Long menuId) {
        return selectList(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getMenuId, menuId));
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, roleId).in(RoleMenuDO::getMenuId, menuIds));
    }

    default void deleteListByMenuId(Long menuId) {
        delete(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getMenuId, menuId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, roleId));
    }

    default void insertBatch(List<RoleMenuDO> roleMenuDOS) {
        for (RoleMenuDO roleMenuDO : roleMenuDOS) {
            insert(roleMenuDO);
        }
    }
}
