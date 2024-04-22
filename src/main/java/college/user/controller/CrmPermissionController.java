package college.user.controller;

import cn.hutool.core.collection.CollUtil;
import college.user.annotation.CrmPermission;
import college.user.controller.bo.CrmPermissionCreateReqBO;
import college.user.controller.vo.permission.CrmPermissionRespVO;
import college.user.controller.vo.permission.CrmPermissionUpdateReqVO;
import college.user.dao.entity.CrmPermissionDO;
import college.user.dto.AdminUserRespDTO;
import college.user.dto.CrmPermissionCreateReqVO;
import college.user.dto.DeptRespDTO;
import college.user.dto.PostRespDTO;
import college.user.enums.CrmPermissionLevelEnum;
import college.user.service.AdminUserApi;
import college.user.service.CrmPermissionService;
import college.user.service.DeptApi;
import college.user.service.PostApi;
import college.user.util.BeanUtils;
import college.user.util.CollectionUtils;
import college.user.util.MapUtils;
import college.user.vo.CommonResult;
import com.google.common.collect.Multimaps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static college.user.util.CollectionUtils.convertSet;
import static college.user.util.CollectionUtils.convertSetByFlatMap;
import static college.user.util.MapUtils.findAndThen;
import static college.user.util.SecurityFrameworkUtils.getLoginUserId;
import static college.user.vo.CommonResult.success;

/**
 * User: EDY
 * Date: 2024/4/22
 * Time: 16:33
 * Version:V1.0
 */
@Tag(name = "管理后台 - CRM 数据权限")
@RestController
@RequestMapping("/crm/permission")
@Validated
public class CrmPermissionController {

    @Resource
    private CrmPermissionService permissionService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private DeptApi deptApi;
    @Resource
    private PostApi postApi;

    @PostMapping("/create")
    @Operation(summary = "创建数据权限")
    @PreAuthorize("@ss.hasPermission('crm:permission:create')")
    @CrmPermission(bizTypeValue = "#reqVO.bizType", bizId = "#reqVO.bizId", level = CrmPermissionLevelEnum.OWNER)
    public CommonResult<Boolean> addPermission(@Valid @RequestBody CrmPermissionCreateReqVO reqVO) {
        permissionService.createPermission(BeanUtils.toBean(reqVO, CrmPermissionCreateReqBO.class));
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "编辑数据权限")
    @PreAuthorize("@ss.hasPermission('crm:permission:update')")
    @CrmPermission(bizTypeValue = "#updateReqVO.bizType", bizId = "#updateReqVO.bizId"
            , level = CrmPermissionLevelEnum.OWNER)
    public CommonResult<Boolean> updatePermission(@Valid @RequestBody CrmPermissionUpdateReqVO updateReqVO) {
        permissionService.updatePermission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除数据权限")
    @Parameter(name = "ids", description = "数据权限编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('crm:permission:delete')")
    public CommonResult<Boolean> deletePermission(@RequestParam("ids") Collection<Long> ids) {
        permissionService.deletePermissionBatch(ids, getLoginUserId());
        return success(true);
    }

    @DeleteMapping("/delete-self")
    @Operation(summary = "删除自己的数据权限")
    @Parameter(name = "id", description = "数据权限编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('crm:permission:delete')")
    public CommonResult<Boolean> deleteSelfPermission(@RequestParam("id") Long id) {
        permissionService.deleteSelfPermission(id, getLoginUserId());
        return success(true);
    }


    @GetMapping("/list")
    @Operation(summary = "获得数据权限列表")
    @Parameters({
            @Parameter(name = "bizType", description = "CRM 类型", required = true, example = "2"),
            @Parameter(name = "bizId", description = "CRM 类型数据编号", required = true, example = "1024")
    })
    @PreAuthorize("@ss.hasPermission('crm:permission:query')")
    public CommonResult<List<CrmPermissionRespVO>> getPermissionList(@RequestParam("bizType") Integer bizType,
                                                                     @RequestParam("bizId") Long bizId) {
        List<CrmPermissionDO> permissions = permissionService.getPermissionListByBiz(bizType, bizId);
        if (CollUtil.isEmpty(permissions)) {
            return success(Collections.emptyList());
        }

        // 查询相关数据
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(
                convertSet(permissions, CrmPermissionDO::getUserId));
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userMap.values(), AdminUserRespDTO::getDeptId));
        Map<Long, PostRespDTO> postMap = postApi.getPostMap(
                convertSetByFlatMap(userMap.values(), AdminUserRespDTO::getPostIds,
                        item -> item != null ? item.stream() : Stream.empty()));
        // 拼接数据
        return success(CollectionUtils.convertList(BeanUtils.toBean(permissions, CrmPermissionRespVO.class), item -> {
            findAndThen(userMap, item.getUserId(), user -> {
                item.setNickname(user.getNickname());
                findAndThen(deptMap, user.getDeptId(), deptRespDTO -> item.setDeptName(deptRespDTO.getName()));
                if (CollUtil.isEmpty(user.getPostIds())) {
                    item.setPostNames(Collections.emptySet());
                    return;
                }
                List<PostRespDTO> postList = MapUtils.getList(Multimaps.forMap(postMap), user.getPostIds());
                item.setPostNames(CollectionUtils.convertSet(postList, PostRespDTO::getName));
            });
            return item;
        }));
    }
}
