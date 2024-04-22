package college.user.config;

import college.user.aop.DataPermissionAnnotationAdvisor;
import college.user.aop.DataPermissionDatabaseInterceptor;
import college.user.rule.DataPermissionRule;
import college.user.rule.DataPermissionRuleFactory;
import college.user.rule.DataPermissionRuleFactoryImpl;
import college.user.util.MyBatisUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * User: EDY
 * Date: 2024/4/18
 * Time: 16:11
 * Version:V1.0
 */
@AutoConfiguration
public class MyDataPermissionAutoConfiguration {

    /**
     * 拦截的规则
     * @param rules
     * @return
     */
    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }


    /**
     * 实现了数据层面的数据拦截
     * @param interceptor
     * @param ruleFactory
     * @return
     */
    @Bean
    public DataPermissionDatabaseInterceptor dataPermissionDatabaseInterceptor(MybatisPlusInterceptor interceptor,
                                                                               DataPermissionRuleFactory ruleFactory) {
        // 创建 DataPermissionDatabaseInterceptor 拦截器
        DataPermissionDatabaseInterceptor inner = new DataPermissionDatabaseInterceptor(ruleFactory);
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }

    /**
     * 实现了所有的类和方法的注解拦截
     * @return
     */
    @Bean
    public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
        return new DataPermissionAnnotationAdvisor();
    }
}
