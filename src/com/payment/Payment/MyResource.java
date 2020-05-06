package com.payment.Payment;

import java.sql.Date;

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

import model.Payment;

///import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

//For REST Service 
//import javax.ws.rs.*;

//For JSON 
//import com.google.gson.*;

//For XML 
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	Payment payObj = new Payment();


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		// return "Got it!";
		return payObj.readItems();
	}

	
	
	@POST 
    @Path("/") 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String insertPayment(
			@FormParam("cardType") String cardType,
			@FormParam("cardNo") String cardNo, 
			@FormParam("cardHolderName") String cardHolderName,
			@FormParam("expiryDate") Date expiryDate,
			@FormParam("time") String time,
			@FormParam("date") Date date,
    		@FormParam("totalAmount") String totalAmount,
    		@FormParam("patient_id") Integer patient_id)
    {  
    	String output = payObj.insertPayment(cardType, cardNo, cardHolderName,expiryDate,time,date, totalAmount,patient_id);  
    	return output; 
    }	
	
	@PUT 
    @Path("/") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String updateItem(String itemData) {  
    	//Convert the input string to a JSON object  
    	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
     
    	//Read the values from the JSON object 
    	String payment_id = itemObject.get("payment_id").getAsString();  
    	String cardType = itemObject.get("cardType").getAsString();  
    	String cardNo = itemObject.get("cardNo").getAsString();  
    	String cardHolderName = itemObject.get("cardHolderName").getAsString();  
    	String totalAmount = itemObject.get("totalAmount").getAsString();  
    	//String patient_id = itemObject.get("patient_id").getAsString(); 
     
        String output = payObj.updatePayment(payment_id,cardType, cardNo, cardHolderName, totalAmount);  
    	return output; 
    }
	

	@DELETE 
	   @Path("/delete") 
	   @Consumes(MediaType.APPLICATION_XML) 
	   @Produces(MediaType.TEXT_PLAIN) 
	   public String deletePayment(String itemData) {  
	   	
	   	//Convert the input string to an XML document  
	   	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());     
	   	
	   	//Read the value from the element <itemID>  
	   	String payment_id = doc.select("payment_id").text(); 
	    
	   	String output = payObj.deletePayment(payment_id); 
	    
	   	return output; 
	   	
	   }
}
