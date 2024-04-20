package college.user.dao.mapper;


import college.user.controller.vo.user.UserPageReqVO;

import college.user.dao.entity.DataPermissionUserDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DataPermissionUserMapper extends BaseMapper<DataPermissionUserDO> {

    default DataPermissionUserDO selectByUsername(String username) {
        return selectOne(Wrappers.lambdaQuery(DataPermissionUserDO.class).eq(DataPermissionUserDO::getUsername, username));
    }

    default DataPermissionUserDO selectByEmail(String email) {
        return selectOne(Wrappers.lambdaQuery(DataPermissionUserDO.class).eq(DataPermissionUserDO::getEmail, email));
    }

    default DataPermissionUserDO selectByMobile(String mobile) {
        return selectOne(Wrappers.lambdaQuery(DataPermissionUserDO.class).eq(DataPermissionUserDO::getMobile, mobile));
    }

    default PageResult<DataPermissionUserDO> selectPage(UserPageReqVO reqVO, Collection<Long> deptIds) {
        IPage<DataPermissionUserDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        selectPage(page, Wrappers.lambdaQuery(DataPermissionUserDO.class)
                .like(DataPermissionUserDO::getUsername, reqVO.getUsername())
                .like(DataPermissionUserDO::getMobile, reqVO.getMobile())
                .eq(DataPermissionUserDO::getStatus, reqVO.getStatus())
                .in(DataPermissionUserDO::getDeptId, deptIds)
                .orderByDesc(DataPermissionUserDO::getId));
        PageResult pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }


    default List<DataPermissionUserDO> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(Wrappers.lambdaQuery(DataPermissionUserDO.class).in(DataPermissionUserDO::getDeptId, deptIds));
    }
}
