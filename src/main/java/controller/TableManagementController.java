package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import service.TableService;

/**
 * Created by Tan$ on 12/3/2016.
 */
@Path("/")
public class TableManagementController {

	private static Logger logger = LoggerFactory.getLogger(TableManagementController.class);

	@Path("/getTableList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDropDown() {
		TableService service = new TableService();
		List<String> tableList = service.getTableList();
		String json = new Gson().toJson(tableList);

		return Response.ok().entity(json).build();
	}

	@Path("/getColumnsOfTable")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumnsOfTable(String downloadJSON) {
		Map<String, Object> downloadRequest = new HashMap<String, Object>();
		downloadRequest = new Gson().fromJson(downloadJSON, downloadRequest.getClass());

		TableService service = new TableService();
		String tableName = downloadRequest.get("tableName").toString();
		System.out.println("TABLE NAME------------>" + tableName);
		List<String> tableList = service.getColumnsList(tableName);
		String json = new Gson().toJson(tableList);

		return Response.ok().entity(json).build();
	}

	@Path("/downloadTableData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response downloadExcelSheet(String downloadJSON) {
		Map<String, Object> downloadRequest = new HashMap<String, Object>();
		downloadRequest = new Gson().fromJson(downloadJSON, downloadRequest.getClass());

		final String tableName = downloadRequest.get("tableName").toString();
		final List<String> columnList = (List<String>) downloadRequest.get("columnList");
		final TableService service = new TableService();
		final Map<String, Integer> responseMap = new HashMap<String, Integer>();

		StreamingOutput fileStream = new StreamingOutput() {

			public void write(OutputStream output) throws IOException, WebApplicationException {
				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
				byte[] outArray;
				Workbook workbook = null;
				try {
					workbook = service.getWorkbook(tableName, columnList);
					workbook.write(outByteStream);
				} catch (Exception e) {
					workbook = null;
				}
				outArray = outByteStream.toByteArray();
				output.write(outArray);
				output.flush();
				output.close();
			}
		};
		return Response.ok(fileStream).header("content-disposition", "attachment; filename =file.xls").build();
	}
}
