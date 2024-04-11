package college.user.dao.mapper;


import college.user.controller.vo.post.PostPageReqVO;
import college.user.dao.entity.PostDO;
import college.user.vo.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<PostDO> {

    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(Wrappers.lambdaQuery(PostDO.class).in(PostDO::getId, ids).in(PostDO::getStatus, statuses));
    }

    default PageResult<PostDO> selectPage(PostPageReqVO reqVO) {
        IPage<PostDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        selectPage(page, Wrappers.lambdaQuery(PostDO.class)
                .like(PostDO::getCode, reqVO.getCode())
                .like(PostDO::getName, reqVO.getName())
                .eq(PostDO::getStatus, reqVO.getStatus())
                .orderByDesc(PostDO::getId));

        PageResult pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    default PostDO selectByName(String name) {
        return selectOne(Wrappers.lambdaQuery(PostDO.class).eq(PostDO::getName, name));
    }

    default PostDO selectByCode(String code) {
        return selectOne(Wrappers.lambdaQuery(PostDO.class).eq(PostDO::getCode, code));
    }

}
