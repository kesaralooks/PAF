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

import model.Project;

@Path("/Project")
public class ProjectService{

	Project projectObj = new Project(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readProject() 
	{ 
	
		return projectObj.readProject();  
	 }
	
	
	@POST
	@Path("/postProject")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertProject( @FormParam("proCustmerID") String proCustmerID, @FormParam("proCustomerName") String proCustomerName, @FormParam("proCustomerContact") String proCustomerContact,  @FormParam("projectName") String projectName, @FormParam("projectType") String projectType,  @FormParam("projectDescription") String projectDescription, @FormParam("proHandoverDate") String proHandoverDate)
	{ 
			
		
			String output = projectObj.insertProject(proCustmerID, proCustomerName, proCustomerContact, projectName, projectType, projectDescription, proHandoverDate);
			return output;
			}
	

	@PUT
	@Path("/putProject") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateProject(String projectID) 
	
	
	{ 
	String fundsData;
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(projectID).getAsJsonObject(); 
	//Read the values from the JSON object
	 String projectID1 = fundObject.get("projectID").getAsString(); 
	 String proCustmerID = fundObject.get("proCustmerID").getAsString(); 
	 String proCustomerName = fundObject.get("projectName").getAsString(); 
	 String proCustomerContact = fundObject.get("receiverID").getAsString();
	 String projectName = fundObject.get("projectName").getAsString();
	 String projectType = fundObject.get("projectType").getAsString();
	 String projectDescription = fundObject.get("projectDescription").getAsString();
	 String proHandoverDate = fundObject.get("proHandoverDate").getAsString();


	 String output = projectObj.updateProject(projectID1, proCustmerID, proCustomerName, proCustomerContact, projectName, projectType, projectDescription, proHandoverDate); 
			 
			 
			 
	return output; 
	}
	
	
	
	@DELETE
	@Path("/DeleteProject") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProject(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <fundID>
	 String projectID = doc.select("projectID").text(); 
	 String output = projectObj.deleteProject(projectID); 
	return output; 
	}
}
