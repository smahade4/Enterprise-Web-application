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

public class ViewPurchase extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get the values from the form
			
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myReviews = db.getCollection("myPurchase");
			
			DBCursor cursor = myReviews.find();
			
			PrintWriter out = response.getWriter();
			//out.println(cursor);
						
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			out.println("<h1> Reviews For:"+ searchParameter+ "</h1>");
			
			out.println("<table>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='index.html'> Index </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='XBox.html'> X Box </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='PlayStation.html'> Play Station </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<br><br><hr>");
			
			if(cursor.count() == 0){
				out.println("There are no reviews for this product.");
			}else{
			
				out.println("<table>");
				
				String productName = "";
				String userName = "";
				String reviewRating = "";
				String reviewDate =  "";
				String reviewText = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					productName = obj.getString("productName");
					out.println("<td>" +productName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Product Cost: </td>");
					Cost = obj.getString("Cost");
					out.println("<td>" +Cost+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Quantity </td>");
					Quantity = obj.getString("Quantity").toString();
					out.println("<td>" +Quantity+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Product Description: </td>");
					ProdDesc = obj.getString("ProdDesc");
					out.println("<td>" +ProdDesc+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Shipping Date: </td>");
					ShippingDate = obj.getString("ShippingDate");
					out.println("<td>" +ShippingDate+ "</td>");
					out.println("</tr>");
					

					out.println("<tr>");
					out.println("<td> Shipping Status: </td>");
					ShippingStatus = obj.getString("ShippingStatus");
					out.println("<td>" +ShippingStatus+ "</td>");
					out.println("</tr>");
				}
			}	
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
		} catch (MongoException e) {
				e.printStackTrace();
		}
	}

	
	public void destroy(){
      // do nothing.
	}
}