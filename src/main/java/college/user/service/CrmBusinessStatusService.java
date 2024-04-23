package college.user.service;


import college.user.dao.entity.CrmBusinessStatusDO;
import college.user.dao.entity.CrmBusinessStatusTypeDO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static college.user.util.CollectionUtils.convertMap;


/**
 * 商机状态 Service 接口
 *
 * @author ljlleo
 */
public interface CrmBusinessStatusService {


    /**
     * 校验商机状态组
     *
     * @param id 编号
     */
    void validateBusinessStatusType(Long id);




    /**
     * 获得指定类型的商机状态列表
     *
     * @param typeId 商机状态组编号
     * @return 商机状态列表
     */
    List<CrmBusinessStatusDO> getBusinessStatusListByTypeId(Long typeId);

    /**
     * 获得商机状态组列表
     *
     * @param ids 编号数组
     * @return 商机状态组列表
     */
    List<CrmBusinessStatusTypeDO> getBusinessStatusTypeList(Collection<Long> ids);

    /**
     * 获得商机状态列表
     *
     * @param ids 编号数组
     * @return 商机状态列表
     */
    List<CrmBusinessStatusDO> getBusinessStatusList(Collection<Long> ids);


    default Map<Long, CrmBusinessStatusTypeDO> getBusinessStatusTypeMap(Collection<Long> ids) {
        return convertMap(getBusinessStatusTypeList(ids), CrmBusinessStatusTypeDO::getId);
    }

    /**
     * 获得商机状态 Map
     *
     * @param ids 编号数组
     * @return 商机状态 Map
     */
    default Map<Long, CrmBusinessStatusDO> getBusinessStatusMap(Collection<Long> ids) {
        return convertMap(getBusinessStatusList(ids), CrmBusinessStatusDO::getId);
    }


}
