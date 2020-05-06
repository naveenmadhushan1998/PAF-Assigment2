$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "MyResource",
		type : t,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalSaveComplete(response.responseText, status);
		}
	});
}); 

function onHospitalSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formAppointment")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
	$("#cardType").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#cardNo").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#cardHolderName").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#expiryDate").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#time").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#date").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#totalAmount").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#patient_id").val($(this).closest("tr").find('td:eq(7)').text()); 


});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "Payment",
		type : "DELETE",
		data : "appID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalDeletedComplete(response.responseText, status);
		}
	});
});

function onHospitalDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateHospitalForm() {  
	// cardType  
	if ($("#cardType").val().trim() == "")  {   
		return "Insert cardType.";  
		
	} 
	
	 // cardNo  
	if ($("#cardNo").val().trim() == "")  {   
		return "Insert cardNo.";  
		
	} 
	 
	 // is numerical value  
	var tmpMobile = $("#cardNo").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for cardNo.";  
		
	}
	 
	// cardHolderName  
	if ($("#cardHolderName").val().trim() == "")  {   
		return "Insert cardHolderName.";  
		
	} 
	
	// expiryDate  
	if ($("#date").val().trim() == "")  {   
		return "Insert expiryDate.";  
		
	} 
	 
	 
	
	// time  
	if ($("#time").val().trim() == "")  {   
		return "Insert time.";  
		
	} 
	
	// Date  
	if ($("#date").val().trim() == "")  {   
		return "Insert date.";  
		
	} 
	
	// totalAmount  
	if ($("#totalAmount").val().trim() == "")  {   
		return "Insert totalAmount.";  
		
	} 
	
	// patient_id  
	if ($("#patient_id").val().trim() == "")  {   
		return "Insert patient_id.";  
		
	} 
	
	 return true; 
	 
}
