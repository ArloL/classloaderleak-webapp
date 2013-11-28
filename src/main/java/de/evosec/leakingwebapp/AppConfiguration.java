package de.evosec.leakingwebapp;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfiguration {

	@Bean
	public KeyStore keyStore() throws Exception {
		InputStream is = null;
		try {
			is = new ClassPathResource("app.truststore").getInputStream();
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(is, "changeit".toCharArray());
			System.out.println("Loaded KeyStore");
			return keyStore;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
