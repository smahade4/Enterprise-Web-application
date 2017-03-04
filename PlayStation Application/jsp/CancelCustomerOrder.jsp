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
	
   
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CSP595Tutorial");	
			DBCollection myOrder = db.getCollection("OrderDetails");
			%>						
			
			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='/SalesHome.jsp'>Home</a></li>");
<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelOrder</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
</a></li></ul></nav>
<%
out.println(request.getSession().getAttribute("Username")+"<br><br>");
	
			DBCursor cursor = myOrder.find();
			if(cursor.count()==0)
			{ out.println("There are no Orders to Update");
				}
			else
			{
			
			while(cursor.hasNext())
			{BasicDBObject obj = (BasicDBObject)cursor.next();%>
			<form method="get" action="CancelOrder.jsp">
			<%if(!(obj.getString("ShippingStatus").equals("Cancelled")))
			{%>
				
 			<table>
			<tr>
			<td>Product Id: </td>
			<td> <input type="checkbox" name="PaymentId" value=<%=obj.get("PaymentId")%>
			</td></input><%=obj.get("PaymentId")%>
			</tr>		
			
			<tr>
			<td>Product: </td>
			<td> <input type="text" name="CustId" value= <%=obj.get("CustId")%> readonly> </td>
			</tr>		
			
			<tr>
			<td>Product Name: </td>
			<td><%=obj.getString("ProductName")%> </td>
			</tr>		
			
			<tr>
			<td><input type='Submit' name='Process' value='Cancel'></td>
						
					</tr>		
	
			</table></form>
				<%}		
				}			
					} 
			}
	
			 catch (MongoException e) {
			e.printStackTrace();
		}
%>