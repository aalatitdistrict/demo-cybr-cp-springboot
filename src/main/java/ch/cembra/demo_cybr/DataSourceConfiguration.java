package ch.cembra.demo_cybr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

import javapasswordsdk.PSDKPassword;

@Configuration(proxyBeanMethods = false)
public class DataSourceConfiguration {

    @Autowired
    private DSJavaPasswordSDKConfigurationProperties passwordSDKProperties;

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {

        CyberArkCPRequest request = new CyberArkCPRequest(passwordSDKProperties.getAppId(),
                passwordSDKProperties.getSafe(), passwordSDKProperties.getFolder(),
                passwordSDKProperties.getObject(), passwordSDKProperties.getReason(),
                passwordSDKProperties.getAdditionalProperties());
        // Query CP
        PSDKPassword account = request.getAccount();

        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUsername(account.getUserName());
        dataSourceProperties.setPassword(new String(account.getSecureContent()));
        return dataSourceProperties;
    }

    @Bean
    @ConfigurationProperties("app.datasource.configuration")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
