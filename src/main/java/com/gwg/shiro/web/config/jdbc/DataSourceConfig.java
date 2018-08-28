package com.gwg.shiro.web.config.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * MyBatis基础配置
 * @author Administrator
 *
 */
@Configuration
/**
 * 启用Spring Aop事物管理
 * 等价于
 * <tx:annotation-driven />
 */
@EnableTransactionManagement //启用Spring Aop事物管理,启用之后就可以使用@Transactional注解了
public class DataSourceConfig{

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	/********主库配置*************************************************/
	@Value("${spring.dataSource.master.dbdriver}")
	private String driverClassNameMaster;
	@Value("${spring.dataSource.master.dburl}")
	private String dburlMaster;
	@Value("${spring.dataSource.master.dbUserName}")
	private String usernameMaster;
	@Value("${spring.dataSource.master.password}")
	private String passwordMaster;


	/*********从库配置***************************************/
	@Value("${spring.dataSource.slaver.dbdriver}")
	private String driverClassNameSlaver;
	@Value("${spring.dataSource.slaver.dburl}")
	private String dburlSlaver;
	@Value("${spring.dataSource.slaver.dbUserName}")
	private String usernameSlaver;
	@Value("${spring.dataSource.slaver.password}")
	private String passwordSlaver;

	/*****Druid connection pool config*****************************************************/
	@Value("${druid.initSize}")
	private int initialSize;
	@Value("${druid.maxActive}")
	private int maxActive = 40;
	@Value("${druid.maxIdle}")
	private int minIdle;
	@Value("${druid.defaultAutoCommit}")
	private boolean defaultAutoCommit;
	@Value("${druid.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
	@Value("${druid.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;
	@Value("${druid.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${druid.testOnReturn}")
	private boolean testOnReturn;
	@Value("${druid.validationQuery}")
	private String validationQuery;




	/**
	 *
	 * @Title: 主库配置
	 * @Description: 数据源的配置
	 */
	@Bean("dataSourceMaster")
	public DataSource dataSourceMaster() {
		logger.info("dataSourceMaster driverClassName:{}, dburl:{}, username:{}, password:{}", driverClassNameMaster, dburlMaster, usernameMaster, passwordMaster);
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassNameMaster);
		druidDataSource.setUrl(dburlMaster);
		druidDataSource.setUsername(usernameMaster);
		druidDataSource.setPassword(passwordMaster);
		druidDataSource.setInitialSize(initialSize);//可选 启动时创建的连接数
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setDefaultAutoCommit(defaultAutoCommit);
		druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		druidDataSource.setTestOnBorrow(testOnBorrow);
		druidDataSource.setTestOnReturn(testOnReturn);
		druidDataSource.setValidationQuery(validationQuery);
		return druidDataSource;

	}

	/**
	 * druid连接池配置详解：https://www.cnblogs.com/wuyun-blog/p/5679073.html
	 * @Title: 从库配置
	 * @Description: 数据源的配置
	 */
	@Bean("dataSourceSlave")
	public DataSource dataSourceSlave() {
		logger.info("dataSourceSlaver driverClassName:{}, dburl:{}, username:{}, password:{}", driverClassNameMaster, dburlMaster, usernameMaster, passwordMaster);
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassNameSlaver);
		druidDataSource.setUrl(dburlSlaver);
		druidDataSource.setUsername(usernameSlaver);
		druidDataSource.setPassword(passwordSlaver);
		druidDataSource.setInitialSize(initialSize);//可选 启动时创建的连接数
		druidDataSource.setMaxActive(maxActive);// 同时可从池中分配的最多连接数，0无限制
		druidDataSource.setMinIdle(minIdle);//不创建新连接情况下池中保持空闲的最小连接数
		druidDataSource.setDefaultAutoCommit(defaultAutoCommit);
		druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);//配置一个连接在池中最小生存的时间，单位是毫秒
		druidDataSource.setTestOnBorrow(testOnBorrow);//申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
		druidDataSource.setTestOnReturn(testOnReturn);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
		druidDataSource.setValidationQuery(validationQuery);
		return druidDataSource;

	}

	/**
	 * 动态数据源配置
	 */
	@Primary
	@Bean("dynamicDataSource")
	public DynamicDataSource dynamicDataSource(){
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
		dataSourceMap.put("master", dataSourceMaster());
		dataSourceMap.put("slave", dataSourceSlave());
		dynamicDataSource.setTargetDataSources(dataSourceMap);//设置动态切换的数据源

		dynamicDataSource.setDefaultTargetDataSource(dataSourceSlave());//设置默认是从库
		return dynamicDataSource;
	}

    
    /**
	 * 
	 * @Title: transactionManager   
	 * @Description: 配置事务管理器
	 * @param: @param dataSource
	 * @return: DataSourceTransactionManager      
	 */
	@Bean
	public DataSourceTransactionManager transactionManager()
			throws Exception {
		return new DataSourceTransactionManager(dynamicDataSource());
	}




}
