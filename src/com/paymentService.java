package com;

import model.payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment")
public class paymentService {
	payment itemObj = new payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpayment() {
		return itemObj.readpayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertpayment(@FormParam("amount") String amount, @FormParam("date") String date,
			@FormParam("accountNumber") String accountNo, @FormParam("paymentType") String paymentType) {
		String output = itemObj.insertpayment(amount, date, accountNo, paymentType);
		return output;
	}

/*	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatepayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String paymentID = itemObject.get("paymentID").getAsString();
		String amount = itemObject.get("amount").getAsString();
		String date = itemObject.get("date").getAsString();
		String accountNo = itemObject.get("accountNo").getAsString();
		String paymentType = itemObject.get("paymentType").getAsString();
		String output = itemObj.updatepayment(paymentID, amount, date, accountNo, paymentType);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element <paymentID>
		String paymentID = doc.select("paymentID").text();
		String output = itemObj.deletepayment(paymentID);
		return output;
	}*/

}
