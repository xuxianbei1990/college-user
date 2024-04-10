package college.user.dao.mapper;


import college.user.dao.entity.DeptDO;
import college.user.vo.DeptListReqVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {

    default List<DeptDO> selectList(DeptListReqVO reqVO) {
        return selectList(Wrappers.lambdaUpdate(DeptDO.class).like(DeptDO::getName, reqVO.getName()).eq(DeptDO::getStatus, reqVO.getStatus()));
    }

    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(Wrappers.lambdaQuery(DeptDO.class).eq(DeptDO::getParentId, parentId).eq(DeptDO::getName, name));
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(Wrappers.lambdaQuery(DeptDO.class).eq(DeptDO::getParentId, parentId));
    }

    default List<DeptDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(Wrappers.lambdaQuery(DeptDO.class).in(DeptDO::getParentId, parentIds));
    }

}
