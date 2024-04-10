package college.user.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * User: EDY
 * Date: 2024/4/10
 * Time: 17:17
 * Version:V1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
public class MySecurityAutoConfiguration {
}
