/**
 * 
 */
package com.cer.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author Praveen Kumar
 *
 */
public abstract class PropertiesConfig extends PropertyPlaceholderConfigurer {
	/*public static final Properties prop= new Properties();
	static{
		try {
			prop.load(PropertiesConfig.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	/*public static final String driverName = prop.getProperty("driverName");
	public static final String driverLocation = prop.getProperty("driverLocation");
	public static final String userName = prop.getProperty("userName");
	public static final String password = prop.getProperty("password");	*/
	
	private Properties props;
	/* 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
	 */
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
        this.props = props;
		super.processProperties(beanFactoryToProcess, props);
	}

	public String getProperty(String propertyKey){
		return props.getProperty(propertyKey);
	}
}
