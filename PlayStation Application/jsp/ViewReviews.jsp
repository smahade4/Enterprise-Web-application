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
			//Get the values from the form
	
		MongoClient mongo = new MongoClient("localhost", 27017);
	
		String searchField = "ProductId";
		int ProductId=0;
		String ProductName="";	
			//Get the product selected
				
		 FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
	 HashMap products = (HashMap)objectInputStream.readObject();

		for(int i=1;i<=products.size();i++)
        			{     
			 ProductDetails p=(ProductDetails)products.get(i);
		
			if (request.getParameter(String.valueOf(p.getProductId()))!=null)
			{ProductId=p.getProductId();
			ProductName=p.getProductName();
			}
			}	
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("Tutorial_3");
			
			DBCollection myReviews = db.getCollection("myReviews");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField, ProductId);

			DBCursor cursor = myReviews.find(searchQuery);
			
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("</head>");
out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));

 out.println("<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/Login.html'>Logout</a>");
out.println("</a></li></ul></nav>");


			out.println("<h1> Reviews For:"+ ProductName+ "</h1>");
			
			out.println("<br><br><hr>");
			
			if(cursor.count() == 0){
				out.println("There are no reviews for this product.");
			}else{
			
				out.println("<table>");
				
				String UserName = "";
				String reviewRating = "";
				String reviewDate;
				String reviewText = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					ProductName = obj.getString("productName");
					out.println("<td>" +ProductName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> UserName: </td>");
					UserName = obj.getString("userName");
					out.println("<td>" +UserName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Rating: </td>");
					reviewRating = obj.getString("reviewRating").toString();
					out.println("<td>" +reviewRating+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Date: </td>");
					reviewDate = obj.getString("reviewDate");
					out.println("<td>" +reviewDate+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Text: </td>");
					reviewText = obj.getString("reviewText");
					out.println("<td>" +reviewText+ "</td>");
					out.println("</tr>");
					
				}
			}	
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
		} catch (MongoException e) {
				e.printStackTrace();
		}
%>