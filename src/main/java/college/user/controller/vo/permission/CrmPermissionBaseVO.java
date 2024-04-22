package college.user.controller.vo.permission;


import college.user.enums.CrmBizTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 数据权限 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 *
 * @author HUIHUI
 */
@Data
public class CrmPermissionBaseVO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private Long userId;

    /**
     * @see CrmBizTypeEnum
     */
    @Schema(description = "CRM 类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer bizType;

    @Schema(description = "CRM 类型数据编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long bizId;

    /**
     * @see college.user.enums.CrmPermissionLevelEnum
     */
    @Schema(description = "权限级别", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer level;

}
