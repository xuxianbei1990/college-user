package college.user.controller.vo.business;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - CRM 商机 Response VO")
@Data

public class CrmBusinessRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "32129")

    private Long id;

    @Schema(description = "商机名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")

    private String name;

    @Schema(description = "客户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10299")
    private Long customerId;
    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")

    private String customerName;

    @Schema(description = "跟进状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")

    private Boolean followUpStatus;

    @Schema(description = "最后跟进时间")

    private LocalDateTime contactLastTime;

    @Schema(description = "下次联系时间")

    private LocalDateTime contactNextTime;

    @Schema(description = "负责人的用户编号", example = "25682")

    private Long ownerUserId;
    @Schema(description = "负责人名字", example = "25682")

    private String ownerUserName;
    @Schema(description = "负责人部门")

    private String ownerUserDeptName;

    @Schema(description = "商机状态组编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25714")
    private Long statusTypeId;
    @Schema(description = "商机状组名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "进行中")

    private String statusTypeName;

    @Schema(description = "商机状态编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30320")
    private Long statusId;
    @Schema(description = "状态名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "跟进中")

    private String statusName;

    @Schema

    private Integer endStatus;


    private String endRemark;

    @Schema(description = "预计成交日期")

    private LocalDateTime dealTime;

    @Schema(description = "产品总金额", example = "12025")

    private BigDecimal totalProductPrice;

    @Schema(description = "整单折扣")

    private BigDecimal discountPercent;

    @Schema(description = "商机总金额", example = "12371")

    private BigDecimal totalPrice;

    @Schema(description = "备注", example = "随便")

    private String remark;

    @Schema(description = "创建人", example = "1024")

    private String creator;
    @Schema(description = "创建人名字", example = "芋道源码")

    private String creatorName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)

    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)

    private LocalDateTime updateTime;

    @Schema(description = "产品列表")
    private List<Product> products;

    @Schema(description = "产品列表")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "888")
        private Long id;

        @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20529")
        private Long productId;
        @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
        private String productName;
        @Schema(description = "产品条码", requiredMode = Schema.RequiredMode.REQUIRED, example = "20529")
        private String productNo;
        @Schema(description = "产品单位", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
        private Integer productUnit;

        @Schema(description = "产品单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "123.00")
        private BigDecimal productPrice;

        @Schema(description = "商机价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "123.00")
        private BigDecimal businessPrice;

        @Schema(description = "产品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "8911")
        private BigDecimal count;

        @Schema(description = "总计价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "123.00")
        private BigDecimal totalPrice;

    }

}
