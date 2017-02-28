<%@ page import="java.io.*,javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="com.mongodb.MongoClient"%>
<%@ page import="com.mongodb.MongoException"%>
<%@ page import="com.mongodb.WriteConcern"%>
<%@ page import="com.mongodb.DB"%>
<%@ page import="com.mongodb.DBCollection"%>
<%@ page import="com.mongodb.BasicDBObject"%>
<%@ page import="com.mongodb.DBObject"%>
<%@ page import="com.mongodb.DBCursor"%>
<%@ page import="com.mongodb.ServerAddress"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set,java.util.Date,javax.servlet.http.*,javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="assign3.*"%>

<%
		try{
			
	MongoClient mongo= new MongoClient("localhost", 27017);
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			
			int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			%>		
			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='/SalesHome'>Home</a></li>
<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelOrder</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
 </a></li></ul></nav>
	
<%

out.println(request.getSession().getAttribute("Username"));



        
	FileInputStream fileInputStream = new FileInputStream(new File("E:\\Login.txt"));
 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	HashMap Login =(HashMap)objectInputStream.readObject();
	
		out.println(" <form name=\"ProcessCart\" method=\"get\" action=\"PlaceOrder.jsp\">");
	
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Select Customer");
		out.println("</td>");
		
		out.println("<td>");

		out.println("<select name='CustId'>");		
		for(int i=1;i<=Login.size();i++)
		{LoginDetails l=(LoginDetails)Login.get(i);	
		out.println("<option value="+l.getUserId()+">"+l.getUsername()+" </option>");		
		}
		out.println("</td>");
		out.println("</tr>");
	 fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
 	 objectInputStream = new ObjectInputStream(fileInputStream);
	HashMap products =(HashMap)objectInputStream.readObject();
		
		out.println("<tr>");
		out.println("<td>");
		out.println("Select Product");
		out.println("</td>");
		
			out.println("<td>");

		out.println("<select name='ProductId'>");		
				Iterator it = products.entrySet().iterator();			
    		while (it.hasNext()) 
		{
		Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			out.println("<option value="+p.getProductId()+">"+p.getProductName()+" </option>");		
			}
		}
			out.println("</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Quantity: </td>");
			
			out.println("<td> <input type=\"text\" name=\"Quantity\"> </td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Warranty Purchase: </td>");
			
			out.println("<td> <input type=\"radio\" name='WarrantyPurchase' value='Yes'\"> yes");
			out.println(" <input type=\"radio\" name='WarrantyPurchase' value='No'\"> no </td>");
			
			out.println("</tr>");
			
				out.println("<tr><td></td><td><input type='Submit' name='SubmitCart' value='Add to Cart'></td></tr>");
	
			
		out.println("</table> </form>"); 		
%>
	<form name='ProcessPayment' method='Get' action='CustomerPayment.jsp'>
	


<% 
		out.println("<fieldset><legend>Personal information:</legend>");
			
			HashMap orders=(HashMap)session.getAttribute("orders");
			if(orders!=null)
			{
			out.println("<table>");
					
			out.println("<tr>");
			out.println("<td>");
			out.println("Select Order");
			out.println("</td>");
		
			out.println("<td>");

			out.println("<select name='OrderId'>");		
			Iterator entries = orders.entrySet().iterator();			
    			while (entries.hasNext()) 
			{
			Map.Entry e = (Map.Entry)entries.next();
			if(e!=null)
			{
			OrderDetails o=(OrderDetails)e.getValue();
			out.println("<option value="+o.getOrderId()+">"+o.getOrderId()+" </option>");		
			}
			}
			
			out.println("</td>");
			out.println("</tr>");


		
			out.println("<tr>");
			out.println("<td>First Name: </td>");
			out.println("<td> <input type=\"text\" name=\"FirstName\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Last Name: </td>");
			out.println("<td> <input type=\"text\" name=\"LastName\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Card Number: </td>");
			out.println("<td> <input type=\"text\" name=\"CardNumber\"> </td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td> Expiration Date: </td>");
			out.println("<td> <input type=\"date\" name=\"ExpirationDate\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td> CVNO: </td>");
			out.println("<td> <input type=\"text\" name=\"CVNO\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td> Shipping Address: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingAddress\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td> City: </td>");
			out.println("<td> <input type=\"text\" name=\"City\"> </td>");
			out.println("</tr>");				
									
			out.println("<tr>");
			out.println("<td> State: </td>");
			out.println("<td> <input type=\"text\" name=\"State\"> </td>");
			out.println("</tr>");				
												
										
			out.println("<tr>");
			out.println("<td> ZipCode: </td>");
			out.println("<td> <input type=\"text\" name=\"ZipCode\"> </td>");
			out.println("</tr>");				
			
			out.println("<tr>");
			out.println("<td> PhoneNo: </td>");
			out.println("<td> <input type=\"text\" name=\"PhoneNo\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td> EmailId: </td>");
			out.println("<td> <input type=\"text\" name=\"EmailId\"> </td>");
			out.println("</tr>");				
				
				out.println("<tr><td></td><td><input type='Submit' name='SubmitPayment' value='Pay'></td></tr>");
	
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
			}
		}catch (MongoException e) {
				e.printStackTrace();
		}
%>