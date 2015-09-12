/**
 * 
 */
package com.cer.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import java.util.Properties;
/**
 * @author NEX6UYU
 *
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	/**
     * The properties used for BeanFactory post-processing.
     */
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
