package college.user.service;

import college.user.dao.entity.CrmCustomerDO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static college.user.util.CollectionUtils.convertMap;

/**
 * 客户 Service 接口
 *
 * @author Wanwan
 */
public interface CrmCustomerService {

    /**
     * 获得客户
     *
     * @param id 编号
     * @return 客户
     */
    CrmCustomerDO getCustomer(Long id);

    /**
     * 获得客户列表
     *
     * @param ids 客户编号数组
     * @return 客户列表
     * @author ljlleo
     */
    List<CrmCustomerDO> getCustomerList(Collection<Long> ids);


    /**
     * 获得客户 Map
     *
     * @param ids 客户编号数组
     * @return 客户 Map
     */
    default Map<Long, CrmCustomerDO> getCustomerMap(Collection<Long> ids) {
        return convertMap(getCustomerList(ids), CrmCustomerDO::getId);
    }





}
