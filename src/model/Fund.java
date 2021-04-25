package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/foundmanagement", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return con; 
	 } 
	
	
	
	public String insertFund(String projectID, String projectName, String receiverID, String timeRangeDays, String receiverAmountForProject, String statuse) 
	 { 
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
			} 
			
			// create a prepared statement
			String query = "INSERT INTO `fund`(`fundID`, `projectID`, `projectName`, `receiverID`, `timeRangeDays`, `receiverAmountForProject`, `statuse`)"
			 		+ " values (?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(projectID)); 
			preparedStmt.setString(3, projectName);
			preparedStmt.setInt(4, Integer.parseInt(receiverID)); 
			preparedStmt.setString(5, timeRangeDays);
			preparedStmt.setDouble(6, Double.parseDouble(receiverAmountForProject));
			preparedStmt.setString(7, statuse);
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		
		catch (Exception e) { 
		 
			output = "Error while inserting the fund."; 
			System.err.println(e.getMessage()); 
		} 
		
		
	 return output; 
	 
	 
	 } 
	
	
	
	
	 public String readFunds() 
	 { 
		
		String output = ""; 
		
		try { 
			
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Project ID</th>" +
					"<th>Project Name</th>" + 
					"<th>Receiver ID</th>" +
					"<th>Project Delivery Period</th>" +
					"<th>Receiver Amount</th>" +
					
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from fund"; 
			
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()){ 
				
				String fundID = Integer.toString(rs.getInt("fundID")); 
				String projectID = Integer.toString(rs.getInt("projectID")); 
				String projectName  = rs.getString("projectName"); 
			    String receiverID = Integer.toString(rs.getInt("receiverID")); 
			    String timeRangeDays  = rs.getString("timeRangeDays"); 
			    String receiverAmountForProject = Double.toString(rs.getDouble("receiverAmountForProject")); 
			    String statuse= rs.getString("statuse"); 
	
				
				// Add into the html table
				output += "<tr><td>" + projectID + "</td>"; 
				output += "<td>" + projectName + "</td>"; 
				output += "<td>" + receiverID + "</td>"; 
				output += "<td>" + timeRangeDays + "</td>"; 
				output += "<td>" + receiverAmountForProject + "</td>"; 
				
				; 
				
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='funds.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fundID' type='hidden' value='" + fundID 
						+ "<input name='statuse' type='hidden' value='" + statuse
						+ "'>" + "</form></td></tr>"; 
			} 
			
			
			
		con.close(); 
		
		
	 // Complete the html table
	 output += "</table>"; 
	 
		} 
		
		
		catch (Exception e) 
		{ 
			output = "Error while reading the fund."; 
			System.err.println(e.getMessage()); 
		} 
		
			return output; 
			
			
	 }
	 
	 
	 
		public String updateFunds(String fundID, String projectID, String projectName, String receiverID,  String statuse)
		{ 
			 String output = ""; 
			 
			 try
			 	{ 
				 	Connection con = connect(); 
				 	
				 	if (con == null){
				 		return "Error while connecting to the database for updating."; 
				 	} 
				 	
				 	
				 	// create a prepared statement
				 	String query = "UPDATE `fund` SET `projectID`=?,`projectName`=?,`receiverID`=?,`statuse`=? WHERE `fundID`=?"; 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 	
				 	
				 	// binding values
				 
					preparedStmt.setInt(1, Integer.parseInt(projectID)); 
					preparedStmt.setString(2, projectName); 
					preparedStmt.setInt(3, Integer.parseInt(receiverID)); 
					preparedStmt.setString(4, statuse);
					preparedStmt.setInt(5, Integer.parseInt(fundID)); 
				
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 	output = "Updated successfully";
				 	
			 } 
			 
			 catch (Exception e) 
			 { 
				 output = "Error while updating the fund."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
		 } 
		
		
		public String deleteFunds(String fundID)  { 
			String output = ""; 
			
			try { 
				Connection con = connect(); 
				
				if (con == null){
					return "Error while connecting to the database for deleting.";
				} 
				
				// create a prepared statement
				String query = "DELETE FROM `fund` WHERE `fundID `=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fundID )); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the fund."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }



		



		



		



	
}
