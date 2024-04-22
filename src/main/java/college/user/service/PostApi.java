package college.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

import college.user.dto.PostRespDTO;
import college.user.util.CollectionUtils;
import college.user.vo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Tag(name = "RPC 服务 - 岗位")
public interface PostApi {

    @Operation(summary = "校验岗位是否合法")
    @Parameter(name = "ids", description = "岗位编号数组", example = "1,2", required = true)
    CommonResult<Boolean> validPostList(@RequestParam("ids") Collection<Long> ids);

    @Operation(summary = "获得岗位列表")
    @Parameter(name = "ids", description = "岗位编号数组", example = "1,2", required = true)
    CommonResult<List<PostRespDTO>> getPostList(@RequestParam("ids") Collection<Long> ids);

    default Map<Long, PostRespDTO> getPostMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return MapUtil.empty();
        }

        List<PostRespDTO> list = getPostList(ids).getData();
        return CollectionUtils.convertMap(list, PostRespDTO::getId);
    }

}
