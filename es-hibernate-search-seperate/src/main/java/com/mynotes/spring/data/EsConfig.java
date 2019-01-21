package com.mynotes.spring.data;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class EsConfig {
	
//	@Value("${spring.data.elasticsearch.cluster-name}")
//	private String clusterName;
//
//	@Value("${spring.data.elasticsearch.cluster-nodes}")
//	private String nodeAndPort;	

	@Bean
	public Client client() throws Exception {
//		String host=nodeAndPort.substring(0, nodeAndPort.indexOf(":"));
//		int port = Integer.parseInt(nodeAndPort.substring(nodeAndPort.indexOf(":")+1));
		//Settings esSettings = Settings.builder().put("cluster.name", clusterName).build();
//		return TransportClient.builder().settings(esSettings).build()
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		Settings elasticsearchSettings = Settings.builder()
		          .put("client.transport.sniff", true).build();
		TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}

}