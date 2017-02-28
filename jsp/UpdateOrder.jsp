
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
						
			// If database doesn't exists, MongoDB will create it for you
               DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("OrderDetails");
			String Button = request.getParameter("Process");
	%>
			
			<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'>
<nav><ul><li class='start selected'><a href='/SalesHome.jsp'>Home</a></li>
<li class=''><a href='AddOrders.jsp'>Add Order</a></li>
<li class=''><a href='ViewOrders.jsp'>View Order</a></li>
<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>
<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateCustomerOrder</a></li>
<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelCustomerOrder</a></li>
<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
 </a></li></ul></nav>
	
<%

out.println(request.getSession().getAttribute("Username")+"<br><br>");
	
        if(Button.equals("Update")) 
	{

	String searchField="PaymentId";
	int i;
	BasicDBObject searchQuery = new BasicDBObject();
	int PaymentId = Integer.parseInt(request.getParameter("PaymentId"));
	
		searchQuery.put(searchField,PaymentId);
	DBCursor cursor=myOrders.find(searchQuery);
	while(cursor.hasNext())
	{	BasicDBObject obj = (BasicDBObject)cursor.next();	
		
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			if(!(obj.get("ShippingStatus").equals("Cancelled")))
 			{
			out.println(" <form method=\"get\" action=\"UpdateCustomerPayment.jsp\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Payment Id: </td>");
			out.println("<td> <input type=\"text\" name=\"PaymentId\" value= "+obj.get("PaymentId")+" readonly> </td>");
			out.println("</tr>");		
			out.println("<tr>");
			out.println("<td>CustId: </td>");
			
			out.println("<td> <input type=\"text\" name=\"CustId\" value= "+obj.get("CustId")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>FirstName: </td>");
			out.println("<td> <input type=\"text\" name=\"FirstName\" value= "+obj.get("FirstName")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ProductName: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\" value= "+obj.get("ProductName")+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ShippingStatus: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingStatus\" value= "+obj.get("ShippingStatus")+"> </td>");
			out.println("</tr>");		

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");			
			out.println("<tr>");
			out.println("<td>ShippingDate: </td>");
			out.println("<td> <input type=\"text\" name=\"ShippingDate\" value= "+format.format(obj.get("ShippingDate"))+"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td></td>");
			out.println("<td> <input type=\"submit\" name=\"Update\" value= 'Submit'> </td>");
			out.println("</tr>");		
			
			out.println("</table>");
			}
			}
                     }
			out.println("</body>");
			out.println("</html>");
			 			
			
		} catch (MongoException e) {
			e.printStackTrace();
%>

