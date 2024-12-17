//TODO oren delete
//package com.ninyo.opensearch.config;
//
//import lombok.SneakyThrows;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
//
//@Configuration
//public class Config extends ElasticsearchConfiguration {
//
//	@Value("${spring.elasticsearch.rest.uris}")
//	String connetionUrl;
//
//	@SneakyThrows
//	@Override
//	public ClientConfiguration clientConfiguration() {
//		SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true);
//		return ClientConfiguration.builder()
//				.connectedTo(connetionUrl)
//				//.usingSsl(sslBuilder.build())
//				.build();
//	}
//}