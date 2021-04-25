package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pay", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return con; 
	 } 
	

	public String insertpayment(String amount, String date, String accountNumber, String paymentType) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "INSERT INTO `payment`(`paymentID`, `amount`, `date`, `accountNumber`, `paymentType`) "
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, amount);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4,accountNumber);
			preparedStmt.setString(5,paymentType);
// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readpayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading new.";
			}
			// Prepare the HTML table to be displayed

			output = "<table border='1'><tr><th>amount</th>" +
						"<th>date</th>" +
						"<th>accountNumber</th>" +
					 "<th>paymentType</th>" +
						"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String amount = rs.getString("amount");
				String date = rs.getString("date");
				String accountNumber =rs.getString("accountNumber");
				String paymentType = rs.getString("paymentType");
				// Add into the HTML table
				
				output += "<tr><td>" +amount+ "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" +accountNumber + "</td>";
				output += "<td>" +paymentType+ "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' value='" +paymentID+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	/*public String updatepayment(String paymentID, String amount, String date, String accountNo, String paymentType)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE paymentdb SET amount=?,date=?,accountNo=?,paymentType=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, amount);
			preparedStmt.setString(2, date);
			preparedStmt.setDouble(3, Double.parseDouble(accountNo));
			preparedStmt.setString(4, paymentType);
			preparedStmt.setInt(5, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletepayment(String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from paymentdb where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}*/
	}
