package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserLogin {
	User itemObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return itemObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("userEmail") String userEmail, @FormParam("userName") String userName,
			@FormParam("userAdd") String userAdd, @FormParam("userCont") String userCont) {
		String output = itemObj.insertUser(userEmail, userName, userAdd, userCont);
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
		String userID = itemObject.get("userID").getAsString();
		String userEmail = itemObject.get("userEmail").getAsString();
		String userName = itemObject.get("userName").getAsString();
		String userAdd = itemObject.get("userAdd").getAsString();
		String userCont = itemObject.get("userCont").getAsString();
		String output = itemObj.updateUser(userID, userEmail, userName, userAdd, userCont);
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
		String userID = doc.select("userID").text();
		String output = itemObj.deleteUser(userID);
		return output;
	}

}