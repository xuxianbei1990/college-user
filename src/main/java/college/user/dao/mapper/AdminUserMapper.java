package college.user.dao.mapper;


import college.user.controller.vo.user.UserPageReqVO;
import college.user.dao.entity.AdminUserDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {

    default AdminUserDO selectByUsername(String username) {
        return selectOne(Wrappers.lambdaQuery(AdminUserDO.class).eq(AdminUserDO::getUsername, username));
    }

    default AdminUserDO selectByEmail(String email) {
        return selectOne(Wrappers.lambdaQuery(AdminUserDO.class).eq(AdminUserDO::getEmail, email));
    }

    default AdminUserDO selectByMobile(String mobile) {
        return selectOne( Wrappers.lambdaQuery(AdminUserDO.class).eq(AdminUserDO::getMobile, mobile));
    }

    default PageResult<AdminUserDO> selectPage(UserPageReqVO reqVO, Collection<Long> deptIds) {
        IPage<AdminUserDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        selectPage(page, Wrappers.lambdaQuery(AdminUserDO.class)
                .like(AdminUserDO::getUsername, reqVO.getUsername())
                .like(AdminUserDO::getMobile, reqVO.getMobile())
                .eq(AdminUserDO::getStatus, reqVO.getStatus())
                .in(AdminUserDO::getDeptId, deptIds)
                .orderByDesc(AdminUserDO::getId));
        PageResult pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }


    default List<AdminUserDO> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(Wrappers.lambdaQuery(AdminUserDO.class).in(AdminUserDO::getDeptId, deptIds));
    }
}
