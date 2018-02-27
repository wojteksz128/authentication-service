//package net.wojteksz128.authserver.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
//@Configuration
//class BeansConfig {
//
//    private final Environment env;
//
//    @Autowired
//    public BeansConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    @Primary
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource());
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
//        dataSource.setUrl(env.getProperty("jdbc.url"));
//        dataSource.setUsername(env.getProperty("jdbc.username"));
//        dataSource.setPassword(env.getProperty("jdbc.password"));
//        return dataSource;
//    }
//
//    @Bean("defaultTokenServices")
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }
//}
