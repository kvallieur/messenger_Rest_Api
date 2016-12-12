package com.rest.restFulExample.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.restFulExample.model.Message;

public class InvocationDemo {

	public static void main(String[] args) {
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.prepareRequestForMessagesByYear(2016);
		Response response = invocation.invoke();
		System.out.println(response.getStatus());
		Client client = ClientBuilder.newClient();
		List<Message> messagesList = client.target("http://localhost:8080/restFulExample/webapi")
				.path("messages")
				.queryParam("year", 2016)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>(){});
		System.out.println(messagesList);
		
	}
	
	public Invocation prepareRequestForMessagesByYear(int year){

		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/restFulExample/webapi")
								.path("messages")
								.queryParam("year", year)
								.request(MediaType.APPLICATION_JSON)
								.buildGet();
		
	}

}
