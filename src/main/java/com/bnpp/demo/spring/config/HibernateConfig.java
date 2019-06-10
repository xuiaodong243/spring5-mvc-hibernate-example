package com.bnpp.demo.spring.config;


import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.bnpp.demo.spring")})
public class HibernateConfig {

	@Autowired
	private ApplicationContext context;

	private LocalSessionFactoryBean getSessionFactoryBean(String conf){
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		String resourcePath = "classpath:" + conf;

		if(Config.getInstance().getConfig().containsKey("system_property")){
			resourcePath = "file:" + Config.getInstance().getConfig().getProperty("system_property") + File.separator + conf;
		}
		System.out.println("Load config for resource :"+resourcePath);
		factoryBean.setConfigLocation(context.getResource(resourcePath));
		return factoryBean;
	}

	private void setAnotatedClasses(LocalSessionFactoryBean factoryBean,String basePackage){
		ArrayList<Class> classes = new ArrayList<Class>();
		// the following will detect all classes that are annotated as @Entity
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
			String name = bd.getBeanClassName();
			try {
				System.out.print(name + ".class");
				classes.add(Class.forName(name));
			} catch (Exception E) {
				E.printStackTrace();
			}
		} // for
		// register detected classes with AnnotationSessionFactoryBean
		System.out.println("---------------------:"+classes.size());
		factoryBean.setAnnotatedClasses(classes.toArray(new Class[classes.size()]));
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = getSessionFactoryBean("hibernate.cfg.xml");
		factoryBean.setAnnotatedClasses(Product1.class, Product1History.class);
		return factoryBean;
	}

	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	/* New Database connection*/
	@Bean(name="sessionFactory2")
	public LocalSessionFactoryBean getSessionFactory2() {
		LocalSessionFactoryBean factoryBean = getSessionFactoryBean("hibernate.cfg2.xml");
		setAnotatedClasses(factoryBean,"com.bnpp.demo.spring.model.demo");
		return factoryBean;
	}

	@Bean(name="transactionManager2")
	public HibernateTransactionManager getTransactionManager2() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory2().getObject());
		return transactionManager;
	}

	@Bean(name="sessionFactory3")
	public LocalSessionFactoryBean getSessionFactory3() {
		LocalSessionFactoryBean factoryBean = getSessionFactoryBean("hibernate.cfg3.xml");
		setAnotatedClasses(factoryBean,"com.bnpp.demo.spring.model.demo");
		return factoryBean;
	}

	@Bean(name="transactionManager3")
	public HibernateTransactionManager getTransactionManager3() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory2().getObject());
		return transactionManager;
	}

}
