package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = {"com.example.demo.repository.secondary"},
    sqlSessionFactoryRef = SecondaryDataSourceConfig.SQL_SESSION_FACTORY_2)
public class SecondaryDataSourceConfig {
  public static final String SQL_SESSION_FACTORY_2 = "sqlSessionFactory2";
  public static final String DATA_SOURCE_2 = "datasource2";
  public static final String TX_MANAGER_2 = "txManager2";

  @Bean
  @ConfigurationProperties(prefix = "secondary.datasource")
  public DataSourceProperties datasource2Properties() {
    return new DataSourceProperties();
  }

  @Bean(name = {DATA_SOURCE_2})
  public DataSource datasource2(
      @Qualifier("datasource2Properties") DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }

  @Bean(name = {TX_MANAGER_2})
  public PlatformTransactionManager txManager1(@Qualifier(DATA_SOURCE_2) DataSource dataSource1) {
    return new DataSourceTransactionManager(dataSource1);
  }

  @Bean(name = {SQL_SESSION_FACTORY_2})
  public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_2) DataSource datasource1)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(datasource1);
    return (SqlSessionFactory) sqlSessionFactory.getObject();
  }
}