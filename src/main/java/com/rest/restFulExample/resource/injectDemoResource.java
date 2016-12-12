package com.rest.restFulExample.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@Path("/injectDemo")
public class injectDemoResource {

    @Path("/annotations")
    @GET
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
    										@HeaderParam("authSessionId") String header,
    										@CookieParam("name") String cookie) {
    	
        return "matrix Param is :: "+matrixParam +" authSessionId is ::"+header+" cookie value is ::"+cookie;
    }
    
    @Path("/context")
    @GET
    public String getParamsUsingContexts(@Context UriInfo uriInfo, @Context HttpHeaders header) {
    	String path = uriInfo.getAbsolutePath().toString();
    	String cookie = header.getCookies().toString();
        return path+" --- "+cookie;
    }
}