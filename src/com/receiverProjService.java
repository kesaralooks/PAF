package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.receiverProj;
@Path("/receiverProj")
public class receiverProjService {
	receiverProj receiverProjObj = new receiverProj(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readreceiverProj() 
	{ 
	
		return receiverProjObj.readreceiverProj();  
	 }
	
	
	@POST
	@Path("/postreceiverProj")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertreceiverProj( @FormParam("re_projectID") String re_projectID, @FormParam("re_projectName") String re_projectName, @FormParam("re_timeRangeDays") String re_timeRangeDays,  @FormParam("re_receiverAmountForProject") String re_receiverAmountForProject)
	{ 
			
		
			String output = receiverProjObj.insertreceiverProj(re_projectID, re_projectName, re_timeRangeDays, re_receiverAmountForProject);
			return output;
			}
	

	@PUT
	@Path("/putreceiverProj") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updatereceiverProj(String re_receiverID) 
	
	
	{ 
	String fundsData;
	//Convert the input string to a JSON object 
	 JsonObject receiverProjObject = new JsonParser().parse(re_receiverID).getAsJsonObject(); 
	//Read the values from the JSON object
	 String re_receiverID1 = receiverProjObject.get("re_receiverID1").getAsString(); 
	 String re_projectID = receiverProjObject.get("re_projectID").getAsString(); 
	 String re_projectName = receiverProjObject.get("re_projectName").getAsString(); 
	 String re_timeRangeDays = receiverProjObject.get("re_timeRangeDays").getAsString();
	 String re_receiverAmountForProject = receiverProjObject.get("re_receiverAmountForProject").getAsString();



	 String output = receiverProjObj.updatereceiverProj(re_receiverID1, re_projectID, re_projectName, re_timeRangeDays, re_receiverAmountForProject); 
			 
			 
			 
	return output; 
	}
	
	
	
	@DELETE
	@Path("/DeletereceiverProj") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletereceiverProj(String receiverProjData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(receiverProjData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <re_receiverID>
	 String re_receiverID = doc.select("re_receiverID").text(); 
	 String output = receiverProjObj.deletereceiverProj(re_receiverID); 
	return output; 
	}
}
