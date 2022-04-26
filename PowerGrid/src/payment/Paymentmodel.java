package payment;

import java.sql.*;

public class Paymentmodel {
	 //A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/power_grid", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertItem(String cusname, String address, String account, String contact, String date, String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment (`Customer_Name`,`Address`,`Account_No`,`Contact_No`,`Date`,`Amount`)" + " values (?, ?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, cusname);
	 preparedStmt.setString(2, address);
	 preparedStmt.setString(4, account);
	 preparedStmt.setString(4, contact);
	 preparedStmt.setString(3, date);
	 preparedStmt.setString(5, amount);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Payment Details Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting.";
	 System.err.println(e.getMessage());
	System.out.println(e.getMessage());
	 }
	 return output;
	 }
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>Customer Name</th>" + "<th>Address</th>" + "<th>Account No</th>" + "<th>Contact No</th>" + "<th>Date</th>" + "<th>Amount</th>" + "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String Payment_ID = Integer.toString(rs.getInt("Payment_ID"));
	 String Customer_Name = rs.getString("Customer_Name");
	 String Address = rs.getString("Address");
	 String Account_No = Integer.toString(rs.getInt("Account_No"));
	 String Contact_No = Integer.toString(rs.getInt("Contact_No"));
	 String Date = rs.getString("Date");
	 String Amount = rs.getString("Amount");
	 // Add into the html table
	 output += "<tr><td>" + Payment_ID + "</td>";
	 output += "<td>" + Customer_Name + "</td>";
	 output += "<td>" + Address + "</td>";
	 output += "<td>" + Account_No + "</td>";
	 output += "<td>" + Contact_No + "</td>";
	 output += "<td>" + Date + "</td>";
	 output += "<td>" + Amount + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + 
	 "<td><form method='post' action='payment.jsp'>" + 
			 "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + 
	 "<input name='itemID' type='hidden' value='" + Payment_ID + "'>" + 
			 "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateItem(String ID, String cusname, String address, String contact, String account, String date, String amount)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payment SET Customer_Name=?,Address=?,Account_No=?,Contact_No=?,Date=?,Amount=? WHERE Payment_ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, cusname);
	 preparedStmt.setString(2, cusname);
	 preparedStmt.setString(3, date);
	 preparedStmt.setString(4, date);
	 preparedStmt.setString(5, amount);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Payment Details Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteItem(String Payment_ID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payment where Payment_ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Payment_ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Payment Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
