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

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		String resourcePath = "classpath:hibernate.cfg.xml";

		if(Config.getInstance().getConfig().containsKey("system_property")){
			resourcePath = "file:" + Config.getInstance().getConfig().getProperty("system_property") + File.separator + "hibernate.cfg.xml";
		}
		System.out.println("Load config for resource 1 :"+resourcePath);
		factoryBean.setConfigLocation(context.getResource(resourcePath));
		factoryBean.setAnnotatedClasses(Product1.class, Product1History.class);

		return factoryBean;
	}

	/* New Database connection*/
	@Bean(name="sessionFactory2")
	public LocalSessionFactoryBean getSessionFactory2() {
		System.out.println("---------------------: session factory  2");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		String resourcePath = "classpath:hibernate.cfg2.xml";

		if(Config.getInstance().getConfig().containsKey("system_property")){
			resourcePath = "file:" + Config.getInstance().getConfig().getProperty("system_property") + File.separator + "hibernate.cfg2.xml";
		}
		System.out.println("Load config for resource 2 :"+resourcePath);
		factoryBean.setConfigLocation(context.getResource(resourcePath));

		//factoryBean.setAnnotatedClasses();

		/* Dont know why doesn't work,
		*  dont want do research...
		*
		* */
		//factoryBean.setAnnotatedPackages("com.bnpp.demo.spring.model.demo");

		ArrayList<Class> classes = new ArrayList<Class>();

		// the following will detect all classes that are annotated as @Entity
		ClassPathScanningCandidateComponentProvider scanner =
				new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

		// only register classes within "com.bnpp.demo.spring.model.demo" package
		for (BeanDefinition bd : scanner.findCandidateComponents("com.bnpp.demo.spring.model.demo")) {
			String name = bd.getBeanClassName();
			try {
				System.out.print(name + ".class,");
				classes.add(Class.forName(name));
			} catch (Exception E) {
				E.printStackTrace();
			}
		} // for

		// register detected classes with AnnotationSessionFactoryBean
		System.out.println("---------------------:"+classes.size());
		factoryBean.setAnnotatedClasses(classes.toArray(new Class[classes.size()]));

		return factoryBean;
	}

	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

	@Bean(name="transactionManager2")
	public HibernateTransactionManager getTransactionManager2() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory2().getObject());
		return transactionManager;
	}

}
