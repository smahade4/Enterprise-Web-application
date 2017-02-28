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

public class Map extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
	private static HashMap<String, String> map = new HashMap<>();
		List<HashMap> list = new ArrayList(); 	
	List<HashMap> list = new ArrayList(); 	
		map.put(productname,XBOX); 
		list.add(map);
		map.put(productname,PlayStation);

		list.add(map);
		map.put(productname,Accesories);
		list.add(map);
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myProducts = db.getCollection("myProducts");
			System.out.println("Collection myReviews selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "myProducts");
												

		
			//Send the response back to the JSP
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();
						
				
		out.println("<form method='get' action='SubmitReview'>");
		<%for(int i=0;i<list.size();i++)
        {
		out.println("<fieldset>");
			out.println("<legend>Product information:</legend>");
			out.println("<img src=");  //width = '200' height = '200' alt = 'X Box Orginal'>");
			out.println(imageLocation);					
				out.println("<table>");
				out.println("<tr>");
					out.println("<td> Product Name:");
					out.println(productName);
					out.println("</td>");
					out.println("<td> </td>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td> Product Price: </td>");
					out.println("<td> Some Price </td>");
				out.println("</tr>");
			out.println("</table>");
		out.println("</fieldset>");
		
		out.println("<fieldset>");
			out.println("<legend>Reviews:</legend>");
			out.println("<table>");
				out.println("<tr>");
					out.println("<td> Product Name: </td>");
					out.println("<td> <input type='text' name='productName' value = >  </td>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td> User Name: </td>");
					out.println("<td> <input type='text' name='userName'> </td>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td> Review Rating: </td>");
					out.println("<td>");
						out.println("<select name='reviewRating'>");
						out.println("<option value='1' selected>1</option>");
						out.println("<option value='2'>2</option>");
						out.println("<option value='3'>3</option>");
						out.println("<option value='4'>4</option>");
						out.println("<option value='5'>5</option>");  
					out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
					out.println("<td> Review Date: </td>");
					out.println("<td> <input type='text' name='reviewDate'> </td>");
			out.println("</tr>");

				out.println("<tr>");
					out.println("<td> Review Text: </td>");
					out.println("<td><textarea name='reviewText' rows='4' cols='50'> </textarea></td>");
				out.println("</tr>");
				
			out.println("</table>");
				
			out.println("<br><br>");
			out.println("<input type='submit' value='Submit Review'>");
			
		out.println("</fieldset>");
		
	out.println("</form>");	

		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}