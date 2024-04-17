package college.user.config;

import college.user.filter.TokenAuthenticationFilter;
import college.user.handler.AccessDeniedHandlerImpl;
import college.user.handler.AuthenticationEntryPointImpl;
import college.user.handler.GlobalExceptionHandler;
import college.user.service.OAuth2TokenService;
import college.user.service.PermissionService;
import college.user.service.SecurityFrameworkService;
import college.user.service.SecurityFrameworkServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * User: EDY
 * Date: 2024/4/10
 * Time: 17:17
 * Version:V1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
public class MySecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordEncoderLength());
    }

    @Autowired
    private OAuth2TokenService oAuth2TokenService;

    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter(GlobalExceptionHandler globalExceptionHandler) {
        return new TokenAuthenticationFilter(securityProperties, globalExceptionHandler, oAuth2TokenService);
    }

    @Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService(PermissionService permissionApi) {
        return new SecurityFrameworkServiceImpl(permissionApi);
    }

}
