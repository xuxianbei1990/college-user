package college.user.service;

import college.user.dao.entity.PostDO;
import college.user.dto.PostRespDTO;
import college.user.util.BeanUtils;
import college.user.vo.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;
import static college.user.vo.CommonResult.success;


@Service // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Override
    public CommonResult<Boolean> validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
        return success(true);
    }

    @Override
    public CommonResult<List<PostRespDTO>> getPostList(Collection<Long> ids) {
        List<PostDO> list = postService.getPostList(ids);
        return success(BeanUtils.toBean(list, PostRespDTO.class));
    }

}
