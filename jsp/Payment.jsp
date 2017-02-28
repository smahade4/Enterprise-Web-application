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
			
	int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			HashMap	orders = (HashMap)session.getAttribute("orders");	
			

			response.setContentType("text/html");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'><header>");
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
			if(orders!=null)
			{
			int Total=0;
			Iterator entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			 //out.println("entry"+entries.next());
			Map.Entry er = (Map.Entry)entries.next();
			out.println(er);
			if(er!=null)
			{
			out.println("entry"+er.getKey()+er.getValue());
			OrderDetails o=(OrderDetails)er.getValue();
			if(o.getCustId()==CustId)
			{
			if(!o.getOrderStatus().equals("unavailable"))	
			{Total=Total+o.getTotal();
			}	
			}	
			}
			}

				
			out.println(" <form method=\"get\" action=\"SubmitPayment.jsp\">");
			out.println("<fieldset>");
			out.println("<table>");
			out.println("<tr>");			
			out.println("<td>Total Amount: </td>");

			out.println("<td> <input type=\"text\" name=\"TotalPrice\" value= "+Total+" readonly> </td>");
			out.println("</tr>");	
			out.println("</fieldset>");
			out.println("<legend>Personal information:</legend>");
			out.println("<table>");
		
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
						;				
			out.println("<tr>");
			out.println("<td> State: </td>");
			out.println("<td> <input type=\"text\" name=\"State\"> </td>");
			out.println("</tr>");				
												
								;				
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
			out.println("<br><br><tr><td><input type='Submit' name='SubmitPayment' value='Pay'></td></tr>");
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
						
		
	 }catch (MongoException e) {
				e.printStackTrace();
		}
	
%>