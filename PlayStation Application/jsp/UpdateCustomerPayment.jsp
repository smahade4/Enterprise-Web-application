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
	
int PaymentId=Integer.parseInt(request.getParameter("PaymentId"));
response.setContentType("text/html");		

out.println("<html><head>");
out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='/SalesHome'>Home</a></li>");
out.println("<li class=''><a href='AddOrders.jsp'>Add Order</a></li>");
out.println("<li class=''><a href='ViewOrders.jsp'>View Order</a></li>");
out.println("<li class=''><a href='AddCustomers.jsp' Name='AddCustomers'>AddCustomers</a></li>");
out.println("<li class=''><a href='UpdateCustomerOrder.jsp' Name='UpdateOrders'>UpdateCustomerOrder</a></li>");
out.println("<li class=''><a href='CancelCustomerOrder.jsp' Name='CancelOrders'>CancelCustomerOrder</a></li>");
out.println("<li class='end'><a href='UpdateCart.jsp' Name='UpdateCart'>UpdateCart</a></li>");


 out.println("</a></li></ul></nav>");
	
	DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("OrderDetails");
		
SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

Date ShippingDate=format.parse(request.getParameter("ShippingDate"));
String ShippingStatus=request.getParameter("ShippingStatus");	

			DBCollection myOrder = db.getCollection("OrderDetails");
			String searchField = "PaymentId";
			// Find and display 
			
			BasicDBObject searchQuery = new BasicDBObject();
 
			searchQuery.put(searchField,Integer.parseInt(request.getParameter("PaymentId")));


			DBCursor cursor = myOrder.find(searchQuery);

			while(cursor.hasNext())
			{
			BasicDBObject obj = (BasicDBObject)cursor.next();
            		obj.put("ShippingDate",ShippingDate);
            		obj.put("ShippingStatus",ShippingStatus);
	
	 			myOrder.save(obj);

out.println(request.getSession().getAttribute("Username")+"<br><br>");
	
		out.println("OrderUpdated for"+obj.getString("ProductName"));
					

			}


			
		out.println("</body></html>");
			
				} catch (Exception e) {
			e.printStackTrace();
			}
%>