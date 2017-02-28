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
	MongoClient mongo= new MongoClient("localhost", 27017);
		try{
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			
			int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());

%>

			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
<nav><ul><li class='start selected'><a href='/SalesHome.jsp'>Home</a></li>
<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelOrder</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
</a></li></ul></nav>

			<%HashMap orders =(HashMap)session.getAttribute("orders");
			int OrderNo=0;
			if(orders == null){%>
				<h2 align='center'>There are no Order Placed In your cart.</h2>
			
				<%	}
				else{
				int i;
			
				if(orders != null)
				{
				Iterator entries = orders.entrySet().iterator();
				while (entries.hasNext()) {
				Map.Entry er = (Map.Entry)entries.next();
				if(er!=null)
				{
			OrderDetails o=(OrderDetails)er.getValue();
				
				int OrderId=o.getOrderId();
				int CustId1=o.getCustId();
				String ProductName = o.getProductName();
				int Price = o.getPrice();
				int Quantity=o.getQuantity();
				int  Discount=o.getDiscounts();
				int  Rebates=o.getRebates();
				//String Total=o.getTotal();
				String Warranty=o.getWarranty();
				int WarrantyPeriod=o.getWarrantyPeriod();
	
				//String ProductDescription=o.getProductDesc();

				%>	
					<form name='ProcessOrder' method='Get' action='CancelCart.jsp'>
					<table>
				<tr>
					
				<td><input type='checkbox' name='Order' value=<%=OrderId%> </td>
				<%=OrderId%></input>	</td>
				<%if(o.getOrderStatus().equals("Unavailable"))
					{%>
				<td> <%out.println("Product Unavailable for check out");%></td>
				<%}%>
				</tr>
					
					<tr>
					<td> Product Name: </td>
					<td> <%=ProductName%></td>
					</tr>
					
					<tr>
					<td> Price: </td>
					<td><%=Price%></td>
					</tr>
					
					<tr>
					<td> CustID: </td>
					<td><%=CustId1%></td>
					</tr>
					
					<tr>
					<td> Order Quantity: </td>
					<td> <%=Quantity%> </td>
					</tr>
					
				
						
				
				
					<tr>
					<td> Total: </td>
					<%int Total = o.getTotal(); %>
					<td> <%=Total%> </td>
					</tr>

	 <tr><td><input type='Submit' name='ProcessOrder' value='Cancel'></td></tr>
		
		<%} } %>
			</table></form>
			
		<%

		}		
		} 

		
		%>	
		              </article></section>

				

			

			</div></body>
				</html>
			
		<%
}catch (MongoException e) {
				e.printStackTrace();
		}
%>