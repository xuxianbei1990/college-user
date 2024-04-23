package college.user.service;


import college.user.dao.entity.CrmBusinessStatusDO;
import college.user.dao.entity.CrmBusinessStatusTypeDO;
import college.user.dao.mapper.CrmBusinessStatusMapper;
import college.user.dao.mapper.CrmBusinessStatusTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static college.user.enums.ErrorCodeConstants.BUSINESS_STATUS_TYPE_NOT_EXISTS;
import static college.user.util.ServiceExceptionUtil.exception;


/**
 * 商机状态 Service 实现类
 *
 * @author ljlleo
 */
@Service
@Validated
public class CrmBusinessStatusServiceImpl implements CrmBusinessStatusService {

    @Resource
    private CrmBusinessStatusTypeMapper businessStatusTypeMapper;
    @Resource
    private CrmBusinessStatusMapper businessStatusMapper;

    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private CrmBusinessService businessService;

    private void validateBusinessStatusTypeExists(Long id) {
        if (businessStatusTypeMapper.selectById(id) == null) {
            throw exception(BUSINESS_STATUS_TYPE_NOT_EXISTS);
        }
    }
    @Override
    public void validateBusinessStatusType(Long id) {
        validateBusinessStatusTypeExists(id);
    }

    @Override
    public List<CrmBusinessStatusDO> getBusinessStatusListByTypeId(Long typeId) {
        List<CrmBusinessStatusDO> list = businessStatusMapper.selectListByTypeId(typeId);
        list.sort(Comparator.comparingInt(CrmBusinessStatusDO::getSort));
        return list;
    }

    @Override
    public List<CrmBusinessStatusTypeDO> getBusinessStatusTypeList(Collection<Long> ids) {
        return null;
    }

    @Override
    public List<CrmBusinessStatusDO> getBusinessStatusList(Collection<Long> ids) {
        return null;
    }
}
