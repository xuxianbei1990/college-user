package college.user.service;

import cn.hutool.core.convert.Convert;
import college.user.dto.AdminUserRespDTO;
import college.user.util.CollectionUtils;
import college.user.vo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Tag(name = "RPC 服务 - 管理员用户")
public interface AdminUserApi  {


    @Operation(summary = "通过用户 ID 查询用户下属")
    @Parameter(name = "id", description = "用户编号", example = "1", required = true)
    CommonResult<List<AdminUserRespDTO>> getUserListBySubordinate(@RequestParam("id") Long id);


    @Operation(summary = "校验用户们是否有效")
    @Parameter(name = "ids", description = "用户编号数组", example = "3,5", required = true)
    CommonResult<Boolean> validateUserList(@RequestParam("ids") Collection<Long> ids);



}
