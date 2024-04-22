package college.user.dto;

import college.user.controller.vo.permission.CrmPermissionBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - CRM 数据权限创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmPermissionCreateReqVO extends CrmPermissionBaseVO {

}
