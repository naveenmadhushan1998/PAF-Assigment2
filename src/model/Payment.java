package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	/// Connect to DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/healthcaremanagement","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readItems() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\\\"1\\\"><tr><th>Payment ID</th><th>CardType</th><th>CardNo</th><th>Card HolderName</th><th>expiryDate</th><th>time</th><th>Date</th><th>Total Amount</th><th>Patient_id</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String payment_id = Integer.toString(rs.getInt("payment_id"));
				String cardType = rs.getString("cardType");
				String cardNo = Double.toString(rs.getDouble("cardNo"));
				String cardHolderName = rs.getString("cardHolderName");
				Date expiryDate = rs.getDate("expiryDate");
				String time = rs.getString("time");
				Date date = rs.getDate("date");
				String totalAmount = Double.toString(rs.getDouble("totalAmount"));
				String patient_id = Integer.toString(rs.getInt("patient_id"));


				// Add into the html table
				// output += "<tr><td>" + itemID + "</td>";
				output += "<tr><td>" + payment_id + "</td>";
				output += "<td>" + cardType + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cardHolderName + "</td>";
				output += "<td>" + expiryDate + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + totalAmount + "</td>";
				output += "<td>" + patient_id + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"        "
						+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"payment.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
						+ "class=\"btn btn-danger\">" + "<input name=\"itemID\" type=\"hidden\" value=\"" + payment_id
						+ "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}

		return output;
	}
public String insertPayment(Integer payment_id, String cardType, String cardNo, String cardHolderName,Date expiryDate,String time,Date date, String totalAmount,Integer patient_id) {

		String output = "";
		
		
		try {

			Connection con = connect();

			if (con == null) {

				return "DB Insert error.";
			}
			
			// create a prepared statement
			String query = "insert into payment values(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cardType);
			preparedStmt.setString(3, cardNo);
			preparedStmt.setString(4, cardHolderName);
			preparedStmt.setDate(5, expiryDate);
			preparedStmt.setString(6, time);
			preparedStmt.setDate(7, date);
			preparedStmt.setDouble(8, Double.parseDouble(totalAmount));
			preparedStmt.setInt(9, (patient_id));

			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
			System.out.println("not insert");
		}

		return output;

	}

	public String updatePayment(String payment_id, String cardType, String cardNo, String cardHolderName,
			String totalAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET cardType=?,cardNo=?,cardHolderName=?,totalAmount=? WHERE payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, cardType);
			preparedStmt.setDouble(2, Double.parseDouble(cardNo));
			preparedStmt.setString(3, cardHolderName);
			preparedStmt.setDouble(4, Double.parseDouble(totalAmount));
			//preparedStmt.setInt(5, Integer.parseInt(patient_id));

			preparedStmt.setInt(5, Integer.parseInt(payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String deletePayment(String payment_id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from Payment where payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String insertPayment(String cardType, String cardNo, String cardHolderName, Date expiryDate, String time,
			Date date, String totalAmount, Integer patient_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
