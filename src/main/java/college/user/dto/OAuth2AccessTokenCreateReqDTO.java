package college.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "RPC 服务 - OAuth2 访问令牌创建 Request DTO")
@Data
public class OAuth2AccessTokenCreateReqDTO implements Serializable {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "用户类型，参见 UserTypeEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @Schema(description = "客户端编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudaoyuanma")
    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    @Schema(description = "授权范围的数组", example = "user_info")
    private List<String> scopes;

}
