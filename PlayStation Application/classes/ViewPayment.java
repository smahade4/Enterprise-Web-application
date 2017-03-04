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
import java.util.*;
import java.text.*;
public class ViewPayment extends HttpServlet {
	
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
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

			DBCollection myOrder = db.getCollection("OrderDetails");
			String  searchField = "CustId";
			BasicDBObject searchQuery = new BasicDBObject();
		int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			searchQuery.put(searchField,CustId);
				DBCursor cursor = myOrder.find(searchQuery);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
	out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW1_MahadevanSushma/Content?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/login.html'>Logout</a>");
out.println("</a></li></ul></nav>");			


  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

	out.println("<legend>Payment information:</legend>");
		
				if(cursor.count() == 0){
				out.println("<h2 align='center'>There Are no Order Placed By you<h2>");
				}

			else
			{
			int Total=0;
			out.println(" <form method=\"get\" action=\"CancelPayment\">");
			
			while(cursor.hasNext()) 
			{			
					BasicDBObject obj = (BasicDBObject)cursor.next();
					Total=Total+(int)(obj.get("Total"));
				
			
			//out.println(request.getSession().getAttribute("UserId"));
			

			
			out.println("<table>");
			Date date = new Date();
			Date ShippingDate=(Date)obj.get("ShippingDate");	
			int diffInDays = (int) ((ShippingDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
			out.println("<input type='hidden' name='diffInDays' value="+diffInDays+">");
			out.println(diffInDays);
			out.println("<tr>");
			out.println("<td>Payment Id:</td>");
			if(diffInDays>=5 && (!(obj.getString("ShippingStatus").equals("Cancelled"))))
			{
			out.println("<td><input type='checkbox' name='PaymentId' value="+obj.get("PaymentId"));		
			out.println("</td></input>");
			 out.println((int)obj.get("PaymentId"));		
				}
			else
			{		
			out.println("<td> <input type=\"text\" name=\"PayId\" value= "+obj.get("PaymentId")+" readonly> </td>");
			}

			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Order Id: </td>");
			out.println("<td> <input type=\"text\" name=\"OrderId\" value= "+obj.get("OrderId")+" readonly> </td>");
			out.println("</tr>");		
			out.println("<tr>");

			out.println("<tr>");
			out.println("<td> Shipping Address: </td>");
			out.println("<td>"+obj.getString("ShippingAddress")+ "</td>");
			out.println("</tr>");	
				out.println("<tr>");
					out.println("<td> ShippingStatus: </td>");
					out.println("<td>" + obj.getString("ShippingStatus")+ "</td>");
					out.println("</tr>");
				out.println("<tr>");
					out.println("<td> Discounts: </td>");
					
					out.println("<td>" +obj.getString("Discounts")+ "</td>");
					out.println("</tr>");
								
				out.println("<tr>");
					out.println("<td> Rebates: </td>");
					out.println("<td>" +obj.getString("Rebates")+ "</td>");
					out.println("</tr>");
				out.println("<tr>");
					out.println("<td> ShippingDate: </td>");
					out.println("<td>" + format.format(obj.get("ShippingDate"))+ "</td>");
					out.println("</tr>");				
			out.println("<tr>");
					out.println("<td> OrderDate: </td>");
					out.println("<td>" + format.format(obj.get("OrderDate"))+ "</td>");
					out.println("</tr>");
				out.println("<tr>");
			out.println("<td> ProductName: </td>");
			out.println("<td> "+obj.getString("ProductName")+" </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td> ProductPrice: </td>");
			out.println("<td> "+obj.get("Price")+"</td>");
			out.println("</tr>");				
						
			if(diffInDays>=5  && (!(obj.getString("ShippingStatus").equals("Cancelled"))))
				{out.println("<br><br><tr><td><input type='Submit' name='CancelOrder' value='Cancel'></td></tr>");
				}
			else if(diffInDays<5)
			{out.println("Order Cannot be cancelled as it is inside 5 date");}
				}
			
			out.println("<fieldset>");
			out.println("<table>");
			out.println("<tr>");			
			out.println("<td>Total Amount: </td>");

			out.println("<td> <input type=\"text\" name=\"TotalPrice\" value= "+Total+" readonly> </td>");
			out.println("</tr>");	
			out.println("</fieldset>");

				out.println("</table>");
                out.println("</article></section>");


out.println("<aside class='sidebar'>");
			
				out.println("<ul>");	
					   out.println("<li>");
							out.println("<h4>Options</h4>");
							out.println("<ul>");
							out.println("<li><a href='WriteReview.html'>Write Reviews</a></li>");
							out.println("<li><a href='/HW1_MahadevanSushma/DataAnalytics1'>Data Analytics</a></li>");
							
							out.println("</ul>");
						out.println("</li>");	
						
					out.println("</ul>");
				
			out.println("</aside>");



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