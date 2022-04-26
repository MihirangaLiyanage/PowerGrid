package payment;

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

import payment.Paymentmodel;

@Path("/Items")
public class Paymentservice {
	Paymentmodel itemObj = new Paymentmodel();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		 return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Customer_Name") String Customer_Name,
							@FormParam("Address") String Address,
							@FormParam("Account_No") String Account_No,
							@FormParam("Contact_No") String Contact_No,
							@FormParam("Date") String Date,
							@FormParam("Amount") String Amount)
	{
	 String output = itemObj.insertItem(Customer_Name, Address, Account_No, Contact_No, Date, Amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String Payment_ID = itemObject.get("Payment_ID").getAsString();
	 String Customer_Name = itemObject.get("Customer_Name").getAsString();
	 String Address = itemObject.get("Address").getAsString();
	 String Account_No = itemObject.get("Account_No").getAsString();
	 String Contact_No = itemObject.get("Account_No").getAsString();
	 String Date = itemObject.get("Date").getAsString();
	 String Amount = itemObject.get("Amount").getAsString();
	 String output = itemObj.updateItem(Payment_ID, Customer_Name, Address, Account_No, Contact_No, Date, Amount);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String Payment_ID = doc.select("Payment_ID").text();
	 String output = itemObj.deleteItem(Payment_ID);
	return output;
	}

	
}
