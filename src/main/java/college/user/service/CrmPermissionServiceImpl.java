package college.user.service;

import college.user.dao.entity.CrmPermissionDO;
import college.user.dao.mapper.CrmPermissionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;


/**
 * CRM 数据权限 Service 接口实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class CrmPermissionServiceImpl implements CrmPermissionService {

    @Resource
    private CrmPermissionMapper permissionMapper;

    @Resource
    private AdminUserApi adminUserApi;





    @Override
    public List<CrmPermissionDO> getPermissionListByBiz(Integer bizType, Collection<Long> bizIds) {
        return permissionMapper.selectByBizTypeAndBizIds(bizType, bizIds);
    }


}
