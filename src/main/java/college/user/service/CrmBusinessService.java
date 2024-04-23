package college.user.service;

import college.user.controller.vo.business.CrmBusinessPageReqVO;
import college.user.controller.vo.business.CrmBusinessSaveReqVO;
import college.user.dao.entity.CrmBusinessDO;
import college.user.dao.entity.CrmBusinessStatusDO;
import college.user.dao.entity.CrmCustomerDO;
import college.user.enums.CrmBusinessEndStatusEnum;
import college.user.vo.PageResult;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商机 Service 接口
 *
 * @author ljlleo
 */
public interface CrmBusinessService {

    /**
     * 创建商机
     *
     * @param createReqVO 创建信息
     * @param userId      用户编号
     * @return 编号
     */
    Long createBusiness(@Valid CrmBusinessSaveReqVO createReqVO, Long userId);

    /**
     * 获得商机分页
     *
     * 数据权限：基于 {@link CrmBusinessDO}
     *
     * @param pageReqVO 分页查询
     * @param userId    用户编号
     * @return 商机分页
     */
    PageResult<CrmBusinessDO> getBusinessPage(CrmBusinessPageReqVO pageReqVO, Long userId);

    /**
     * 获得商机分页，基于指定客户
     *
     * 数据权限：基于 {@link CrmCustomerDO} 读取
     *
     * @param pageReqVO 分页查询
     * @return 商机分页
     */
    PageResult<CrmBusinessDO> getBusinessPageByCustomerId(CrmBusinessPageReqVO pageReqVO);

    /**
     * 获得商机状态名称
     *
     * @param endStatus 结束状态
     * @param status    商机状态
     * @return 商机状态名称
     */
    default String getBusinessStatusName(Integer endStatus, CrmBusinessStatusDO status) {
        if (endStatus != null) {
            return CrmBusinessEndStatusEnum.fromStatus(endStatus).getName();
        }
        return status.getName();
    }

}
