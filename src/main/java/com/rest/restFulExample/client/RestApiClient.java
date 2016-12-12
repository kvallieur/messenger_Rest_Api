/**
 * 
 */
package com.rest.restFulExample.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.restFulExample.model.Message;

public class RestApiClient {

	public static void main(String[] args) {
		/*Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/restFulExample/webapi/messages/1").request().get();
		Message message = response.readEntity(Message.class);
		System.out.println(message.getAuthor());*/
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/restFulExample/webapi");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		Message message1 = singleMessageTarget.resolveTemplate("messageId", "1")
						  .request(MediaType.APPLICATION_JSON)
						  .get(Message.class);
		
		Response response = singleMessageTarget.resolveTemplate("messageId", "2")
				  .request(MediaType.APPLICATION_JSON)
				  .get();
		//Response has status codes and other stuff. depending on your usage....
	
		
		// Example for Post 
		Message message3 = new Message(6, "Message from Rest Client", "YV");
		Response postResponse = messagesTarget.path("addMessage")
								.request()
								.post(Entity.json(message3));
		Message createdMessage = postResponse.readEntity(Message.class);
		System.out.println(createdMessage);
	}
}
