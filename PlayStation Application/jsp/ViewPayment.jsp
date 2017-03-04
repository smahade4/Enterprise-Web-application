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
	
	
	MongoClient mongo = new MongoClient("localhost", 27017);
		try{
		
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

			DBCollection myOrder = db.getCollection("OrderDetails");
			String  searchField = "CustId";
			BasicDBObject searchQuery = new BasicDBObject();
		int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			searchQuery.put(searchField,CustId);
				DBCursor cursor = myOrder.find(searchQuery);
			response.setContentType("text/html");%>
	<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
<body><div id='container'>
<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>
<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>
<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Accesories'>Accessories</a></li>
<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>
<%out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));%>
 <li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>
<li><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
</a></li></ul></nav>			


<div id='body'>
          <section id='content'>

              <article>

		<legend>Payment information:</legend>
		
		<%	if(cursor.count() == 0){%>
				<h2 align='center'>There Are no Order Placed By you<h2>
				<%}

			else
			{
			int Total=0;%>
			<form method="get" action="CancelPayment.jsp">
			
			<% while(cursor.hasNext()) 
			{			
					BasicDBObject obj = (BasicDBObject)cursor.next();
					Total=Total+Integer.parseInt(obj.get("Total").toString());
						

			out.println("<table>");
			Date date = new Date();
			Date ShippingDate=(Date)obj.get("ShippingDate");	
			int diffInDays = (int) ((ShippingDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
			%>
			<input type='hidden' name='diffInDays' value=<%=diffInDays%> />
			<tr>
			<td>Payment Id:</td>
			<% if(diffInDays>=5 && (!(obj.getString("ShippingStatus").equals("Cancelled"))))
			{%>
			<td><input type='checkbox' name='PaymentId' value=<%=obj.get("PaymentId")%>		
			</td></input>			
			<% out.println(Integer.parseInt(obj.get("PaymentId").toString()));		
				}
			else
			{%>		
			<td> <input type="text" name="PayId" value= <%=obj.get("PaymentId")%> readonly> </td>
			<%}%>

			</tr>
			<tr>
			<td>Order Id: </td>
			<td> <input type="text" name="OrderId" value=<%=obj.get("OrderId")%> readonly> </td>
			</tr>		
			<tr>

			<tr>
			<td> Shipping Address: </td>
			<td><%=obj.getString("ShippingAddress")%></td>
			</tr>	
				<tr>
					<td> ShippingStatus: </td>
					<td><%=obj.getString("ShippingStatus")%> </td>
					</tr>
				<tr>
					<td> Discounts: </td>
					
					<td><%=obj.getString("Discounts")%> </td>
					</tr>
								
				<tr>
					<td> Rebates: </td>
					<td><%=obj.getString("Rebates")%> </td>
					</tr>
			<tr>
					<td> ShippingDate: </td>
					<td> <%= format.format(obj.get("ShippingDate"))%> </td>
					</tr>				
			<tr>
					<td> OrderDate: </td>
					<td> <%= format.format(obj.get("OrderDate"))%> </td>
				</tr>
				<tr>
			<td> ProductName: </td>
			<td> <%=obj.getString("ProductName")%> </td>
			</tr>				
			<tr>
			<td> ProductPrice: </td>
			<td> <%=obj.get("Price")%></td>
			</tr>				
				<%		
			if(diffInDays>=5  && (!(obj.getString("ShippingStatus").equals("Cancelled"))))
				{%>
			<br><br><tr><td><input type='Submit' name='CancelOrder' value='Cancel'></td></tr>
				<%}
			else if(diffInDays<5)
			{
			out.println("Order Cannot be cancelled as it is inside 5 date");}
				}%>
			
			<fieldset>
			<table>
			<tr>			
			<td>Total Amount: </td>

			<td> <input type="text" name="TotalPrice" value= <%=Total%> readonly> </td>
			</tr>	
			</fieldset>

			</table>
                </article></section>


			

				</body>
				</html>
			<%
		} 
}catch (MongoException e) {
		e.printStackTrace();
		}
%>	