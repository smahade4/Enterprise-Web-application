
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
public class SelectProductsUpdate extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
   protected HashMap products;
    
	public void init() throws ServletException{
	


	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));

 out.println("</a></li></ul></nav>");
	

			out.println(" <form method=\"get\" action=\"ChangeProducts\">");
			
 			HashMap products = (HashMap)request.getSession().getAttribute("products");
		
			if(products==null)
			{ out.println("There are no Products to Update");
				}
			else
			{for(int i=1;i<=products.size();i++)
        		{     
	 		ProductDetails p=(ProductDetails)products.get(i);
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"radio\" name=\"ProductId\" value= "+p.getProductId()+">"+p.getProductId() +"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product: </td>");
			out.println("<td> <input type=\"text\" name=\"Product\" value= '"+p.getProduct()+"' readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\" value= '"+p.getProductName()+"' readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td><input type='Submit' name='Update' value='Update'></td>");
						
					out.println("</tr>");
				}

			out.println("</table>");				
			out.println("</body>");
				out.println("</html>");
}
			} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}