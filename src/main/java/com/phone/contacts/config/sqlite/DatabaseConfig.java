package com.phone.contacts.config.sqlite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Since spring doesn't support sqlite out of the box
 * we need to expose a datasource bean
 */
@Configuration
public class DatabaseConfig {

    private final Environment environment;

    public DatabaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("sqlite.driverClassName"));
        driverManagerDataSource.setUrl(environment.getProperty("sqlite.url"));
        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.phone.contacts.models");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        if (environment.getProperty("sqlite.hibernate.dialect") != null) {
            hibernateProperties.setProperty("sqlite.hibernate.dialect", environment.getProperty("sqlite.hibernate.dialect"));
        }
        if (environment.getProperty("sqlite.hibernate.show_sql") != null) {
            hibernateProperties.setProperty("sqlite.hibernate.show_sql", environment.getProperty("sqlite.hibernate.show_sql"));
        }
        return hibernateProperties;
    }

}
