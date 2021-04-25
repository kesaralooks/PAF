package com;

import model.Receiver;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Receiver")
public class ReceiverLogin {
	Receiver itemObj = new Receiver();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readReceiver() {
		return itemObj.readReceiver();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertReceiver(@FormParam("receiverEmail") String receiverEmail, @FormParam("receiverName") String receiverName,
			@FormParam("receiverAdd") String receiverAdd, @FormParam("receiverCont") String receiverCont) {
		String output = itemObj.insertReceiver(receiverEmail, receiverName, receiverAdd, receiverCont);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateReceiver(String receiverData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(receiverData).getAsJsonObject();
		// Read the values from the JSON object
		String receiverID = itemObject.get("receiverID").getAsString();
		String receiverEmail = itemObject.get("receiverEmail").getAsString();
		String receiverName = itemObject.get("receiverName").getAsString();
		String receiverAdd = itemObject.get("receiverAdd").getAsString();
		String receiverCont = itemObject.get("receiverCont").getAsString();
		String output = itemObj.updateReceiver(receiverID, receiverEmail, receiverName, receiverAdd, receiverCont);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteReceiver(String receiverData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(receiverData, "", Parser.xmlParser());

		// Read the value from the element <paymentID>
		String receiverID = doc.select("receiverID").text();
		String output = itemObj.deleteReceiver(receiverID);
		return output;
	}

}
