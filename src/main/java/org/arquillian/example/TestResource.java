package org.arquillian.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class TestResource {

   @GET
   public Response test() {
      return Response.ok("{\"test2\": true}", MediaType.APPLICATION_JSON_TYPE).build();
   }
}
