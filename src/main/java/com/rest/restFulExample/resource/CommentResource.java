package com.rest.restFulExample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

//@Path("*/*")
public class CommentResource {

	@GET
	public String Test(){
		return "Hello from Comments";
	}
	
	@Path("/{commentId}")
	@GET
	public String getComment(@PathParam("messageId") long messageId
			,@PathParam("commentId") long commentId){
		return "Comment for "+ commentId + "and messageId "+messageId;
	}
	
}
