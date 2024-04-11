package college.user.dao.mapper;

import college.user.controller.vo.menu.MenuListReqVO;
import college.user.dao.entity.MenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

    default MenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(Wrappers.lambdaQuery(MenuDO.class).eq(MenuDO::getParentId, parentId).eq(MenuDO::getName, name));
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(Wrappers.lambdaQuery(MenuDO.class).eq(MenuDO::getParentId, parentId));
    }

    default List<MenuDO> selectList(MenuListReqVO reqVO) {
        return selectList(Wrappers.lambdaQuery(MenuDO.class).like(MenuDO::getName, reqVO.getName()).eq(MenuDO::getStatus, reqVO.getStatus()));
    }

    default List<MenuDO> selectListByPermission(String permission) {
        return  selectList(Wrappers.lambdaQuery(MenuDO.class).eq(MenuDO::getPermission, permission));
    }
}
