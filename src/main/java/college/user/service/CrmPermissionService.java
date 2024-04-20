package college.user.service;

import college.user.dao.entity.CrmPermissionDO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

/**
 * crm 数据权限 Service 接口
 *
 * @author HUIHUI
 */
public interface CrmPermissionService {



    /**
     * 获取数据权限列表，通过 数据类型 x 某个数据
     *
     * @param bizType 数据类型，关联 {@link CrmBizTypeEnum}
     * @param bizIds  数据编号，关联 {@link CrmBizTypeEnum} 对应模块 DO#getId()
     * @return Crm 数据权限列表
     */
    List<CrmPermissionDO> getPermissionListByBiz(Integer bizType, Collection<Long> bizIds);


}
