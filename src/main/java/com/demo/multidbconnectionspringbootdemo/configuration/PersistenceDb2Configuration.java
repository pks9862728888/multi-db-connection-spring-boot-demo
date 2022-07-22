package com.demo.multidbconnectionspringbootdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.demo.multidbconnectionspringbootdemo.db2",
        entityManagerFactoryRef = "db2EntityManager",
        transactionManagerRef = "db2TransactionManager"
)
public class PersistenceDb2Configuration {

    @Autowired
    private Environment env;

    @Bean("db2EntityManager")
    public LocalContainerEntityManagerFactoryBean db2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan("com.demo.multidbconnectionspringbootdemo.db2");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("db2.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("db2.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.hbm2dll.create_namespaces", env.getProperty("db2.jpa.properties.javax.persistence.schema-generation.create-database-schemas"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db2EntityManager().getObject());
        return transactionManager;
    }
}