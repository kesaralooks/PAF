package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Receiver {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/receiverdb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertReceiver(String receiverEmail, String receiverName, String receiverAdd, String receiverCont) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into receiverdb(`receiverID`,`receiverEmail`,`receiverName`,`receiverAdd`,`receiverCont`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, receiverEmail);
			preparedStmt.setString(3, receiverName);
			preparedStmt.setDouble(4, Double.parseDouble(receiverAdd));
			preparedStmt.setString(5, receiverCont);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the receiver.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readReceiver() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed

			output = "<table border='1'><tr><th>receiverID</th><th>receiverEmail</th><th>receiverName</th>" + "<th>receiverAdd</th>"
					+ "<th>receiverCont</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from receiverdb";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String receiverID = Integer.toString(rs.getInt("receiverID"));
				String receiverEmail = rs.getString("receiverEmail");
				String receiverName = rs.getString("receiverName");
				String receiverAdd = Double.toString(rs.getDouble("receiverAdd"));
				String receiverCont = rs.getString("receiverCont");
				
				// Add into the HTML table
				output += "<tr><td>" + receiverID + "</td>";
				output += "<td>" + receiverEmail + "</td>";
				output += "<td>" + receiverName + "</td>";
				output += "<td>" + receiverAdd + "</td>";
				output += "<td>" + receiverCont + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
						+ "<input name='receiverID' type='hidden' value='" + receiverID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the receiver.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateReceiver(String receiverID, String receiverEmail, String receiverName, String receiverAdd, String receiverCont)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE receiverdb SET receiverEmail=?,receiverName=?,receiverAdd=?,receiverCont=? WHERE receiverID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, receiverEmail);
			preparedStmt.setString(2, receiverName);
			preparedStmt.setDouble(3, Double.parseDouble(receiverAdd));
			preparedStmt.setString(4, receiverCont);
			preparedStmt.setInt(5, Integer.parseInt(receiverID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the receiver.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteReceiver(String receiverID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from receiverdb where receiverID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(receiverID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the receiver.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
