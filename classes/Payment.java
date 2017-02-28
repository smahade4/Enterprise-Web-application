import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class Payment extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("CartDetails");
			String  searchField = "CustId";
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField,request.getSession().getAttribute("UserId"));
				DBCursor cursor = myCart.find(searchQuery);
						
				if(cursor.count() == 0){
				//request.getSession().setAttribute("CartOrder",0);
				}

			else
			{
			int Total=0;
			while(cursor.hasNext()) 
			{			
					BasicDBObject obj = (BasicDBObject)cursor.next();
			if(!obj.getString("OrderStatus").equals("unavailable"))	
			{Total=Total+(int)(obj.get("Total"));
			}	//PrintWriter out = response.getWriter();
				
			//out.println(Total);
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW1_MahadevanSushma/Content?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/Logout'>Logout</a>");
out.println("</a></li></ul></nav>");



				
			out.println(" <form method=\"get\" action=\"SubmitPayment\">");
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

				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
		} }catch (MongoException e) {
				e.printStackTrace();
		}
	}

	
	public void destroy(){
      // do nothing.
	}
}