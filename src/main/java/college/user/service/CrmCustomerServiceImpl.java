package college.user.service;

import cn.hutool.core.collection.CollUtil;
import college.user.annotation.CrmPermission;
import college.user.dao.entity.CrmCustomerDO;
import college.user.dao.mapper.CrmCustomerMapper;
import college.user.enums.CrmBizTypeEnum;
import college.user.enums.CrmPermissionLevelEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * User: EDY
 * Date: 2024/4/23
 * Time: 10:37
 * Version:V1.0
 */
@Service
public class CrmCustomerServiceImpl implements CrmCustomerService {

    @Resource
    private CrmCustomerMapper customerMapper;
    @Override
    @CrmPermission(bizType = CrmBizTypeEnum.CRM_CUSTOMER, bizId = "#id", level = CrmPermissionLevelEnum.READ)
    public CrmCustomerDO getCustomer(Long id) {
        return customerMapper.selectById(id);
    }

    @Override
    public List<CrmCustomerDO> getCustomerList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return customerMapper.selectBatchIds(ids);
    }
}
