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


public class ViewCart extends HttpServlet {
	
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
			String searchField = "CustId";
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
 
			int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			searchQuery.put(searchField,CustId);

			DBCursor cursor = myCart.find(searchQuery);
			
			PrintWriter out = response.getWriter();
			//out.println(cursor);
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
out.println("<li><a href='/HW1_MahadevanSushma/Logout'>Logout</a>");
out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

			
			if(cursor.count() == 0){
				out.println("<h2 align='center'>There are no Order Placed In your cart.</h2>");
					//out.println(CustId+"okk");	

					}else{
				String OrderId="";
				String ProductName = "";
				String Price = "";
				String Quantity="";
				String Discount="";
				String Rebates="";
				String Total="";
				String Warranty="";
				String WarrantyPeriod="";
	
				String ProductDescription="";

				while (cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<form name='ProcessOrder' method='Get' action='ProcessOrder'>");
					out.println("<table>");
					
					if(obj.getString("OrderStatus").equals("Unavailable"))
					{
				out.println("<tr>");
					
				out.println("<td><input type='checkbox' name='Order' value="+OrderId);
				out.println("Product is unavailable for check out Only Available will be checked out </td>");
				out.println("</tr>");
						
					}
					else
					{out.println("<tr>");
					
					OrderId = obj.getString("OrderId");
					out.println("<td><input type='checkbox' name='Order' value="+OrderId);
					out.println("<td>"+OrderId);
					
					out.println("</td></input>");
					out.println("</tr>");
					}
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					ProductName = obj.getString("ProductName");
					out.println("<td>" +ProductName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Price: </td>");
					Price = obj.getString("Price");
					out.println("<td>" +Price+ "</td>");
					out.println("</tr>");
					
					
					
					out.println("<tr>");
					out.println("<td> Order Quantity: </td>");
					Quantity = obj.getString("Quantity");
					out.println("<td>" +Quantity+ "</td>");
					out.println("</tr>");
					
				
				
					out.println("<tr>");
					out.println("<td><input type='Submit' name='ProcessOrder' value='Cancel'>Remove Order </td>");
				//out.println("<td><input type='Submit' name='Cancel'>Update Order </td>");
						
					out.println("</tr>");
						
				
				
					out.println("<tr>");
					out.println("<td> Total: </td>");
					Total = obj.getString("Total");
					out.println("<td>" +Total+ "</td>");
					out.println("</tr>");
		}		
	out.println("<br><br><tr><td><input type='Submit' name='ProcessOrder' value='CheckOut'>Proceed to Check Out</td></tr>");
				out.println("</table></form>");
				
		} 
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



			out.println("</div></body>");
				out.println("</html>");
			
		
}catch (MongoException e) {
				e.printStackTrace();
		}
	
}
	
	public void destroy(){
      // do nothing.
	}
}