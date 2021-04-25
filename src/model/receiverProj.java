package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class receiverProj {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
			Connection con = null; 
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/roject_management", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			
			return con; 
		 } 
		
		

		public String insertreceiverProj(String re_projectID, String re_projectName, String re_timeRangeDays,String re_receiverAmountForProject) 

		 { 
			String output = ""; 
			
			try{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for inserting.";
				} 
				
				// create a prepared statement
				String query = "INSERT INTO `reciverde`(`re_receiverID`, `re_projectID`, `re_projectName`, `re_timeRangeDays`, `re_receiverAmountForProject`)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setInt(2, Integer.parseInt(re_projectID)); 
				preparedStmt.setString(3, re_projectName);
				preparedStmt.setInt(4, Integer.parseInt(re_timeRangeDays));
				preparedStmt.setDouble(5, Double.parseDouble(re_receiverAmountForProject));
			
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			
			catch (Exception e) { 
			 
				output = "Error while inserting the receiver project details."; 
				System.err.println(e.getMessage()); 
			} 
			
			
		 return output; 
		 
		 
		 } 
		
		
		
		
		 public String readreceiverProj()
		 { 
			
			String output = ""; 
			
			try { 
				
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for reading 1."; 
				} 
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project ID</th>" +
						"<th>Project Name</th>" + 
						"<th>Project Delivery Period</th>" +
						"<th>Receiver Amount</th>" +
						
						"<th>Update</th><th>Remove</th></tr>"; 
		 
				String query = "select * from reciverde"; 
				
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				// iterate through the rows in the result set
				while (rs.next()){ 
					
					String re_receiverID = Integer.toString(rs.getInt("re_receiverID")); 
					String re_projectID = Integer.toString(rs.getInt("re_projectID")); 
					String re_projectName  = rs.getString("re_projectName");  
				    String re_timeRangeDays = Integer.toString(rs.getInt("re_timeRangeDays"));
				    String re_receiverAmountForProject = Double.toString(rs.getDouble("re_receiverAmountForProject")); 


		
					
					// Add into the html table
					output += "<tr><td>" + re_projectID + "</td>"; 
					output += "<td>" + re_projectName + "</td>";  
					output += "<td>" + re_timeRangeDays + "</td>"; 
					output += "<td>" + re_receiverAmountForProject + "</td>"; 
					
					; 
					
					
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='funds.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='fundID' type='hidden' value='" + re_receiverID
					
							+ "'>" + "</form></td></tr>"; 
				} 
				
				
				
			con.close(); 
			
			
		 // Complete the html table
		 output += "</table>"; 
		 
			} 
			
			
			catch (Exception e) 
			{ 
				output = "Error while reading the receiver project details."; 
				System.err.println(e.getMessage()); 
			} 
			
				return output; 
				
				
		 }
		 
		 
		 
			public String updatereceiverProj(String re_receiverID, String re_projectID, String re_projectName, String re_timeRangeDays, String re_receiverAmountForProject)
			{ 
				 String output = ""; 
				 
				 try
				 	{ 
					 	Connection con = connect(); 
					 	
					 	if (con == null){
					 		return "Error while connecting to the database for updating."; 
					 	} 
					 	
					 	
					 	// create a prepared statement
					 	String query = "UPDATE `reciverde` SET `re_projectID`=?,`re_projectName`=?,`re_timeRangeDays`=?,`re_receiverAmountForProject`=? WHERE `re_receiverID`=?"; 
					 	PreparedStatement preparedStmt = con.prepareStatement(query); 
					 	
					 	
					 	// binding values
					 
						preparedStmt.setInt(1, Integer.parseInt(re_projectID)); 
						preparedStmt.setString(2, re_projectName);  
						preparedStmt.setString(3, re_timeRangeDays);
						preparedStmt.setDouble(4, Double.parseDouble(re_receiverAmountForProject));

					
					 	// execute the statement
					 	preparedStmt.execute(); 
					 	con.close(); 
					 	output = "Updated successfully";
					 	
				 } 
				 
				 catch (Exception e) 
				 { 
					 output = "Error while updating the receiver project details."; 
					 System.err.println(e.getMessage()); 
				 } 
				 	return output; 
			 } 
			
			
			public String deletereceiverProj(String re_receiverID)  { 
				String output = ""; 
				
				try { 
					Connection con = connect(); 
					
					if (con == null){
						return "Error while connecting to the database for deleting.";
					} 
					
					// create a prepared statement
					
					String query = "DELETE FROM `reciverde` WHERE `re_receiverID`=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(re_receiverID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the receiver project details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
}
