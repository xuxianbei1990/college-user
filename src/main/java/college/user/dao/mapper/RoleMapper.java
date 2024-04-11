package college.user.dao.mapper;


import college.user.controller.vo.role.RolePageReqVO;
import college.user.dao.entity.RoleDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

    default PageResult<RoleDO> selectPage(RolePageReqVO reqVO) {
        IPage<RoleDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        selectPage(page, new LambdaQueryWrapper<RoleDO>()
                .like(RoleDO::getName, reqVO.getName())
                .like(RoleDO::getCode, reqVO.getCode())
                .eq(RoleDO::getStatus, reqVO.getStatus())
                .orderByDesc(RoleDO::getId));
        PageResult pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    default RoleDO selectByName(String name) {
        return selectOne(Wrappers.lambdaQuery(RoleDO.class).eq(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(Wrappers.lambdaQuery(RoleDO.class).eq(RoleDO::getCode, code));
    }



}
