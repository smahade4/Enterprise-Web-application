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

	
   HashMap products;
    
	DB db = mongo.getDB("CSP595Tutorial");	
			DBCollection myOrder = db.getCollection("OrderDetails");
						
			
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='/SalesHome'>Home</a></li>");
out.println("<li class=''><a href='AddOrders.jsp'>Add Order</a></li>");
out.println("<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>");
out.println("<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateOrder</a></li>");
out.println("<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelOrder</a></li>");
out.println("<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>");

 out.println("</a></li></ul></nav>");


out.println(request.getSession().getAttribute("Username")+"<br><br>");
	
			DBCursor cursor = myOrder.find();
			if(cursor.count()==0)
			{ out.println("There are no Orders to Update");
				}
			else
			{
			while(cursor.hasNext())
			{BasicDBObject obj = (BasicDBObject)cursor.next();
			if(!(obj.getString("ShippingStatus").equals("Cancelled")))
			{
				
			out.println(" <form method=\"get\" action=\"UpdateOrder.jsp\">");
			
 			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"radio\" name=\"PaymentId\" value= "+obj.get("PaymentId")+">"+ obj.get("PaymentId")+"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Cust Id: </td>");
			out.println("<td> <input type=\"text\" name=\"CustId\" value= "+obj.get("CustId")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td>"+obj.getString("ProductName")+" </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td><input type='Submit' name='Process' value='Update'></td>");
						
					out.println("</tr>");
				}
			   }
			}
			out.println("</form></table>");				
			out.println("</body>");
				out.println("</html>");
		
	
			} catch (MongoException e) {
			e.printStackTrace();
		}
%>