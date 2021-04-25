package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class seller {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, userName, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafdb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertseller( String sellerName, String sellerAdd, String Email, String sellerCont)
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into seller(`sellerID`,`Name`,`Address`,`Email, `Contact_No`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sellerName);
			preparedStmt.setDouble(3, Double.parseDouble(sellerAdd));
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, sellerCont);
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

	public String readseller() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed

			output = "<table border='1'><tr><th>sellerID</th><th>sellerName</th><th>address</th>" + "<th>email</th>"
					+ "<th>Contact_No</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from seller";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String sellerID = Integer.toString(rs.getInt("sellerID"));
				String sellerName = rs.getString("sellerName");
				String sellerAdd = Double.toString(rs.getDouble("sellerAdd"));
				String Email = rs.getString("Email");
				String sellerCont = rs.getString("sellerCont");

				// Add into the HTML table
				output += "<tr><td>" + sellerID + "</td>";
				output += "<td>" + sellerName + "</td>";
				output += "<td>" + sellerAdd + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + sellerCont + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
						+ "<input name='sellerID' type='hidden' value='" + sellerID + "'>" + "</form></td></tr>";
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

	public String updateseller(String sellerID, String sellerName, String sellerAdd, String Email, String sellerCont)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE seller SET sellerName=?,sellerAdd=?,Email=?,sellerCont=? WHERE sellerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, sellerName);
			preparedStmt.setDouble(2, Double.parseDouble(sellerAdd));
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, sellerCont);
			preparedStmt.setInt(5, Integer.parseInt(sellerID));
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

	public String deleteUser(String sellerID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from seller where sellerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(sellerID));
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
