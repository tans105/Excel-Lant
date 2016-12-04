package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import service.TableService;

/**
 * Created by Tan$ on 12/3/2016.
 */
@Path("/")
public class TableManagementController {
	@Path("/getTableDropDown")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDropDown() {
		TableService service=new TableService();
		List<String> tableList=service.getTableList();
		String json = new Gson().toJson(tableList);

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
