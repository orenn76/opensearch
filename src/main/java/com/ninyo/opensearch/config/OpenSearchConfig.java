package com.ninyo.opensearch.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import com.ninyo.opensearch.repositories.ProductRepository;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.spring.boot.autoconfigure.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//TODO oren check https://forum.opensearch.org/t/spring-data-opensearch-example-not-working/17033
@Configuration
@EnableElasticsearchRepositories(basePackageClasses = ProductRepository.class)
@ComponentScan(basePackageClasses = OpenSearchConfig.class)
public class OpenSearchConfig {

	/**
	 * Allow to connect to the OpenSearch instance which uses self-signed certificates
	 */
	@Bean
	RestClientBuilderCustomizer customizer() {
		return new RestClientBuilderCustomizer() {
			@Override
			public void customize(HttpAsyncClientBuilder builder) {
				try {
					builder.setSSLContext(new SSLContextBuilder()
							.loadTrustMaterial(null, new TrustSelfSignedStrategy())
							.build());
				} catch (final KeyManagementException | NoSuchAlgorithmException | KeyStoreException ex) {
					throw new RuntimeException("Failed to initialize SSL Context instance", ex);
				}
			}

			@Override
			public void customize(RestClientBuilder builder) {
				// No additional customizations needed
			}
		};
	}
}