package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Tan$ on 12/3/2016.
 */
@Path("/test")
public class TestController {
    @GET
    @Path("/testGet")
    public String testGetRequest(){
        return "GET Request Working Fine";
    }
}
