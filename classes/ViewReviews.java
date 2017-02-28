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
public class ViewReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get the values from the form
			String searchField = "ProductId";
		int ProductId=0;
		String ProductName="";	
			//Get the product selected
			 HashMap products = (HashMap)request.getSession().getAttribute("products");
			for(int i=1;i<=products.size();i++)
        			{     
			 ProductDetails p=(ProductDetails)products.get(i);
		
			if (request.getParameter(String.valueOf(p.getProductId()))!=null)
			{ProductId=p.getProductId();
			ProductName=p.getProductName();
			}
			}	
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myReviews = db.getCollection("myReviews");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField, ProductId);

			DBCursor cursor = myReviews.find(searchQuery);
			
			PrintWriter out = response.getWriter();
			//out.println(cursor);
						out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
		out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='/PageLayoutCSS/Index'>Home</a></li>");
out.println("<li class=''><a href='/PageLayoutCSS/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/PageLayoutCSS/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/PageLayoutCSS/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
		out.println("</a></li></ul></nav>");


			out.println("<h1> Reviews For:"+ ProductName+ "</h1>");
			
			out.println("<br><br><hr>");
			
			if(cursor.count() == 0){
				out.println("There are no reviews for this product.");
			}else{
			
				out.println("<table>");
				
				String UserName = "";
				String reviewRating = "";
				Date reviewDate;
				String reviewText = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					ProductName = obj.getString("ProductName");
					out.println("<td>" +ProductName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> UserName: </td>");
					UserName = obj.getString("UserName");
					out.println("<td>" +UserName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Rating: </td>");
					reviewRating = obj.getString("reviewRating").toString();
					out.println("<td>" +reviewRating+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Date: </td>");
					reviewDate = (Date)obj.get("reviewDate");
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
	}

	
	public void destroy(){
      // do nothing.
	}
}