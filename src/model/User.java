package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyerdb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String userEmail, String userName, String userAdd, String userCont) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into buyerdb(`userID`,`userEmail`,`userName`,`userAdd`,`userCont`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, userEmail);
			preparedStmt.setString(3, userName);
			preparedStmt.setDouble(4, Double.parseDouble(userAdd));
			preparedStmt.setString(5, userCont);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed

			output = "<table border='1'><tr><th>paymentID</th><th>amount</th><th>date</th>" + "<th>accountNo</th>"
					+ "<th>paymentType</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from buyerdb";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String userEmail = rs.getString("userEmail");
				String userName = rs.getString("userName");
				String userAdd = Double.toString(rs.getDouble("userAdd"));
				String userCont = rs.getString("userCont");
				
				// Add into the HTML table
				output += "<tr><td>" + userID + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + userAdd + "</td>";
				output += "<td>" + userCont + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' value='" + userID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String userID, String userEmail, String userName, String userAdd, String userCont)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE buyerdb SET userEmail=?,userName=?,userAdd=?,userCont=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, userEmail);
			preparedStmt.setString(2, userName);
			preparedStmt.setDouble(3, Double.parseDouble(userAdd));
			preparedStmt.setString(4, userCont);
			preparedStmt.setInt(5, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String userID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from paymentdb where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}