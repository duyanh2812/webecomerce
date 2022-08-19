package vn.com.cmcglobal.demoshopcart.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import vn.com.cmcglobal.demoshopcart.User.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = 
{"vn.com.cmcglobal.demoshopcart.User.service", 
"vn.com.cmcglobal.demoshopcart.Cart.service",
"vn.com.cmcglobal.demoshopcart.Product.service",
"vn.com.cmcglobal.demoshopcart.Bill.service"})
public class AppConfig{

    // Tạo bean dataSource
    @Bean
    public DriverManagerDataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream user_props = this.getClass()
                .getResourceAsStream("/application.properties");
        properties.load(user_props);
        dataSource.setDriverClassName(
                properties.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(properties.getProperty("spring.datasource.url"));
        dataSource.setUsername(
                properties.getProperty("spring.datasource.username"));
        dataSource.setPassword(
                properties.getProperty("spring.datasource.password"));
        return dataSource;
    }

    // đọc thông tin file cấu hình MyBatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        Resource resource = new ClassPathResource("SqlMapConfig.xml");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    // scan tất cả những mapper package
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("vn.com.cmcglobal.demoshopcart.User.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer1() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("vn.com.cmcglobal.demoshopcart.Cart.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer2() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("vn.com.cmcglobal.demoshopcart.Product.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer3() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("vn.com.cmcglobal.demoshopcart.Bill.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    

}
