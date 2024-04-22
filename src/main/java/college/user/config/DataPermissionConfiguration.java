package college.user.config;


import college.user.dao.entity.AdminUserDO;
import college.user.dao.entity.DataPermissionUserDO;
import college.user.dao.entity.DeptDO;
import college.user.rule.dept.DeptDataPermissionRuleCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * system 模块的数据权限 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class DataPermissionConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer sysDeptDataPermissionRuleCustomizer() {
        return rule -> {
            // dept
            rule.addDeptColumn(AdminUserDO.class);
            rule.addDeptColumn(DeptDO.class, "id");
            // 需要实现部门的表
            rule.addDeptColumn(DataPermissionUserDO.class);
            // user
            rule.addUserColumn(AdminUserDO.class, "id");
            // 需要实现用户的表
            rule.addUserColumn(DataPermissionUserDO.class);
        };
    }

}
