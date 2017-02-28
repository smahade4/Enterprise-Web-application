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

		int ProductId=Integer.parseInt(request.getParameter("ProductId"));	
			String productCategory = request.getParameter("productCategory");
			String productName = request.getParameter("productName");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String retailerName = request.getParameter("retailerName");
			String retailerZipcode = request.getParameter("retailerZipcode");
			String retailerCity = request.getParameter("retailerCity");
			String retailerState = request.getParameter("retailerState");
			String productOnSale = request.getParameter("productOnSale");
			String consoleManufacturer = request.getParameter("consoleManufacturer");
			String manufacturerRebate = request.getParameter("manufacturerRebate");
			
			String userName = request.getParameter("userName");
			int userAge = Integer.parseInt(request.getParameter("userAge"));
			String userGender = request.getParameter("userGender");
			String userOccupation = request.getParameter("userOccupation");
			
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
		String pageHeading = "Submit Review";
		String pageContent =" ";
		String myPage = " ";
		String tableData = " ";
		
		int count = 0;
			
			DB db = mongo.getDB("Tutorial_3");
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("myReviews");

			BasicDBObject doc = new BasicDBObject("title", "MongoDB").append("productCategory", productCategory)
					.append("productName", productName).append("productPrice", productPrice).append("ProductId", ProductId)
					.append("retailerName", retailerName).append("retailerZipcode", retailerZipcode).append("retailerCity", retailerCity)
					.append("retailerState", retailerState).append("productOnSale", productOnSale).append("userName", userName).append("userAge", userAge)
					.append("userGender", userGender).append("userOccupation", userOccupation)
					.append("consoleManufacturer", consoleManufacturer).append("manufacturerRebate", manufacturerRebate)
					.append("reviewRating", reviewRating).append("reviewDate", reviewDate)
					.append("reviewText", reviewText);
					
					
			myReviews.insert(doc);

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productName", productName);
			searchQuery.put("userName", userName);

			DBCursor dbCursor = myReviews.find(searchQuery);
			
			while (dbCursor.hasNext()) {
				BasicDBObject bobj = (BasicDBObject) dbCursor.next();
				tableData =  "<tr><td>Category</td><td>" + bobj.getString("productCategory")
						+ "</td></tr>" + "<tr><td>Name</td><td>" + bobj.getString("productName") + "</td></tr>"
						+ "<tr><td>Price</td><td>" + bobj.getInt("productPrice") + "</td></tr>"
						+ "<tr><td>Retailer</td><td>" + bobj.getString("retailerName") + "</td></tr>"
						+ "<tr><td>Retailer Zipcode</td><td>" + bobj.getString("retailerZipcode") + "</td></tr>"
						+ "<tr><td>Retailer City </td><td>" + bobj.getString("retailerCity") + "</td></tr>"
						+ "<tr><td>Retailer State</td><td>" + bobj.getString("retailerState") + "</td></tr>"
						+ "<tr><td>Sale</td><td>" + bobj.getString("productOnSale") + "</td></tr>"
						+ "<tr><td>User ID</td><td>" + bobj.getString("userID") + "</td></tr>"
						+ "<tr><td>User Age</td><td>" + bobj.getString("userAge") + "</td></tr>"
						+ "<tr><td>User Gender</td><td>" + bobj.getString("userGender") + "</td></tr>"
						+ "<tr><td>User Occupation</td><td>" + bobj.getString("userOccupation") + "</td></tr>"
						+ "<tr><td>Manufacturer</td><td>" + bobj.getString("consoleManufacturer") + "</td></tr>"
						+ "<tr><td>Manufacturer Rebate</td><td>" + bobj.getString("manufacturerRebate") + "</td></tr>"
						+ "<tr><td>Rating</td><td>" + bobj.getString("reviewRating") + "</td></tr>"
						+ "<tr><td>Date</td><td>" + bobj.getString("reviewDate") + "</td></tr>"
						+ "<tr><td>Review Text</td><td>" + bobj.getString("reviewText") + "</td></tr>";
			}
			
			pageContent = "<table>"+tableData+"</table>"+"<h1>"+count+"</h1>";

			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
						
						out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));

//out.println(request.getSession().getAttribute("UserId"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
//out.println(request.getSession().getAttribute("products"));
 //HashMap products = (HashMap)request.getSession().getAttribute("products");
out.println("<li><a href='/HW3_MahadevanSushma/login.html'>Logout</a>");
out.println("</a></li></ul></nav>");

out.println("<h1> Review submitted for:"+ productName + "</h1>");
			
			out.println("</body>");
			out.println("</html>");
		
		} catch (MongoException e) {
			e.printStackTrace();
		}
%>