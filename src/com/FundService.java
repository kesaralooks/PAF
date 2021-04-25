package com;

import model.Fund;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Fund")
public class FundService {
	
	Fund fundObj = new Fund(); 
	//get details
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readFunds() 
	{ 
		 return fundObj.readFunds();  
	 }
	
	
	@POST
	@Path("/postFund")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertFund( @FormParam("projectID") String projectID, @FormParam("projectName") String projectName, @FormParam("receiverID") String receiverID,  @FormParam("timeRangeDays") String timeRangeDays, @FormParam("receiverAmountForProject") String receiverAmountForProject,  @FormParam("statuse") String statuse)
	{ 
			
		
			String output = fundObj.insertFund(projectID, projectName, receiverID, timeRangeDays, receiverAmountForProject, statuse);
			return output;
			}
	
	
	@PUT
	@Path("/putFund") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateFunds(String fundID) 
	
	
	{ 
	String fundsData;
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(fundID).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundID1 = fundObject.get("fundID").getAsString(); 
	 String projectID = fundObject.get("projectID").getAsString(); 
	 String projectName = fundObject.get("projectName").getAsString(); 
	 String receiverID = fundObject.get("receiverID").getAsString();
	 String statuse = fundObject.get("statuse").getAsString();


	 String output = fundObj.updateFunds(fundID1, projectID, projectName, receiverID,statuse); 
			 
			 
			 
	return output; 
	}
	
	
	
	@DELETE
	@Path("/DeleteFund") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFunds(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <fundID>
	 String fundID = doc.select("fundID").text(); 
	 String output = fundObj.deleteFunds(fundID); 
	return output; 
	}

}
