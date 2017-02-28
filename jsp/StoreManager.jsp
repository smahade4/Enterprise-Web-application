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

<%
		try{
			
    
	MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("ProductDetails");
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
 
			//String CustId=request.getSession().getAttribute("UserId").toString();
			///searchQuery.put(searchField,CustId);

			//DBCursor cursor = myCart.find(searchQuery);
			
				
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts.jsp'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class=''><a href='SelectProductsDelete.jsp' Name='DeleteProducts'>DeleteProducts</a></li>");
out.println("<li class='end'><a href='/HW3_MahadevanSushma/login.html'>Logout</a>");
 out.println("</a></li></ul></nav>");


out.println(request.getSession().getAttribute("Username"));		
	out.println("</div></body></html>");

}

catch (MongoException e) {
			e.printStackTrace();
		}
	
%>

