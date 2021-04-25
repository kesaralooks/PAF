package com;

import model.seller;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/seller")
public class sellItem {
	
	seller sellerObj = new seller();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readseller() 
	{
		return sellerObj.readseller();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser( @FormParam("sellerName") String sellerName,@FormParam("Email") String Email,
			@FormParam("sellerAdd") String sellerAdd, @FormParam("Cont") String sellerCont) {
		String output = sellerObj.insertseller(sellerName, sellerAdd, Email, sellerCont);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String sellerID = itemObject.get("sellerID").getAsString();
		String sellerName = itemObject.get("sellerName").getAsString();
		String sellerAdd = itemObject.get("sellerAdd").getAsString();
		String Email = itemObject.get("Email").getAsString();
		String sellerCont = itemObject.get("sellerCont").getAsString();
		String output = sellerObj.updateseller(sellerID, Email, sellerName, sellerAdd, sellerCont);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element <paymentID>
		String sellerID = doc.select("sellerID").text();
		String output = sellerObj.deleteUser(sellerID);
		return output;
	}

}
