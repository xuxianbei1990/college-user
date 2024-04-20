package college.user.config;

import cn.hutool.extra.spring.SpringUtil;
import college.user.rule.dept.DeptDataPermissionRule;
import college.user.rule.dept.DeptDataPermissionRuleCustomizer;
import college.user.service.PermissionApi;
import college.user.util.LoginUser;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * User: EDY
 * Date: 2024/4/20
 * Time: 11:23
 * Version:V1.0
 */
@AutoConfiguration
@ConditionalOnClass(LoginUser.class)
@ConditionalOnBean(value = DeptDataPermissionRuleCustomizer.class)
public class MyDeptDataPermissionAutoConfiguration {

    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(PermissionApi permissionApi,
                                                         List<DeptDataPermissionRuleCustomizer> customizers) {
        // Cloud 专属逻辑：优先使用本地的 PermissionApi 实现类，而不是 Feign 调用
        // 原因：在创建租户时，租户还没创建好，导致 Feign 调用获取数据权限时，报“租户不存在”的错误
        try {
            PermissionApi permissionApiImpl = SpringUtil.getBean("permissionApiImpl", PermissionApi.class);
            if (permissionApiImpl != null) {
                permissionApi = permissionApiImpl;
            }
        } catch (Exception ignored) {}

        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule(permissionApi);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }
}
