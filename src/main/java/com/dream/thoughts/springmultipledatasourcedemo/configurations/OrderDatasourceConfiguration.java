package com.dream.thoughts.springmultipledatasourcedemo.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "orderEntityManagerFactory", 
		transactionManagerRef = "orderTransactionManager", 
		basePackages = {
		"com.dream.thoughts.springmultipledatasourcedemo.repository.order" })

public class OrderDatasourceConfiguration {

	@Bean(name = "orderProperties")
	@ConfigurationProperties("spring.datasource.order")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "orderDatasource")
	@ConfigurationProperties(prefix = "sqlserver.datasource")
	public DataSource datasource(@Qualifier("orderProperties") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}

	@Bean(name = "orderEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("orderDatasource") DataSource dataSource) {

		return builder
				.dataSource(dataSource)
				.packages("com.dream.thoughts.springmultipledatasourcedemo.entity.order")
				.persistenceUnit("order")
				.build();
	}

	@Bean(name = "orderTransactionManager")
	@ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager transactionManager(
			@Qualifier("orderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

		return new JpaTransactionManager(entityManagerFactory);
	}
}