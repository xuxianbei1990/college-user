package college.user.service;

import college.user.dao.entity.DataPermissionUserDO;
import college.user.dao.mapper.DataPermissionUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * User: EDY
 * Date: 2024/4/20
 * Time: 10:54
 * Version:V1.0
 */
@Service
public class DataPermissionTestService {

    @Resource
    private DataPermissionUserMapper dataPermissionUserMapper;

    public DataPermissionUserDO selectUserNames(String username) {
       return dataPermissionUserMapper.selectByUsername(username);
    }
}
