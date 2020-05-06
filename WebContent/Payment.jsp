<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Payment Management</h1>        
				
				<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					cardType:  
					<input id="cardType" name="cardType" type="text" class="form-control form-control-sm">  
					
					<br> 
					cardNo:  
					<input id="cardNo" name="cardNo" type="number" class="form-control form-control-sm">  
					
					<br>
					 cardHolderName:  
					 <input id="cardHolderName" name="cardHolderName" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 expiryDate:  
					 <input id="expiryDate" name="expiryDate" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 time:  
					 <input id="time" name="time" type="time" class="form-control form-control-sm">  
					 
					 <br> 
					 Date:  
					 <input id="date" name="date" type="text" class="form-control form-control-sm"> 
					 
					 <br> 
					 totalAmount:  
					 <input id="totalAmount" name="totalAmount" type="number" class="form-control form-control-sm">
					 
					 <br> 
					patient_id:  
					 <input id="patient_id" name="patient_id" type="number" class="form-control form-control-sm">
					 
					

					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Payment appObj = new Payment();
						out.print(appObj.readPayment());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>

</html>