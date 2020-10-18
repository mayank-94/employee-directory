/**
 * 
 */
package com.spring.boot.employeedirectory.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mayank
 *
 */
@Configuration
public class CacheConfig {
	
	@Bean
	public CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
		
		return new CacheManagerCustomizer<ConcurrentMapCacheManager>() {
			@Override
			public void customize(ConcurrentMapCacheManager cacheManager) {
				cacheManager.setAllowNullValues(false);
				
			}
		};
	}
	
}
