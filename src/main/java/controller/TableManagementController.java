package controller;

import com.google.gson.Gson;
import entity.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Tan$ on 12/3/2016.
 */
@Path("/")
public class TableManagementController {
	@Path("/getTableDropDown")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDropDown() {
		Product test = new Product();
		test.setFirstName("Tanmay");
		test.setLastName("Awasthi");
		String json = new Gson().toJson(test);

		return Response.ok().entity(json).build();
	}

	@Path("/downloadTableData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDownloadData() {
		return Response.ok().build();
	}
}
