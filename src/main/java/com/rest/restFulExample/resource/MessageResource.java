package com.rest.restFulExample.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rest.restFulExample.model.Message;
import com.rest.restFulExample.model.MessageFilterBean;
import com.rest.restFulExample.service.MessageService;

@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
@Path("/messages")
public class MessageResource {
	MessageService messageService  = new MessageService();
	
	@GET
	public List<Message> getMessages(
									 /*This is a tedious process if the params are exceeding
									 @QueryParam("year") int year,
									 @QueryParam("start") int start,
									 @QueryParam("size") int size)}
		if(year > 0){
			return messageService.getAllMessagesForYear(year);
		}
		if(start >= 0 && size >= 0){
			return messageService.getAllMessagesPaginated(start, size);
		}*/
		@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getStart() >= 0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
			
	}
	
	@Path("/{messageId}")
	@GET
	public Message getMessage(@PathParam("messageId") long messageId){
		
		return messageService.getMessage(messageId);
	}
	
	@Path("/addMessage")
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		// If you want to eturn the uri of created message the below line will not work
		//return Response.status(Status.CREATED).entity(newMessage).build();
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@Path("/{messageId}")
	@PUT
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@Path("/{messageId}")
	@DELETE
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
