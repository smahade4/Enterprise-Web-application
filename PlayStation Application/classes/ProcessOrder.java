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
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
public class ProcessOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
				
						
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myCart = db.getCollection("CartDetails");
			String Button = request.getParameter("ProcessOrder");

	if(Button.equals("CheckOut")) 
	{
			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/Payment");
			 		rd.forward(request, response);	
	}
	if(Button.equals("Cancel")) 
	{
		PrintWriter out=response.getWriter();
		String searchField="OrderId";
	int i;
	BasicDBObject searchQuery = new BasicDBObject();
	String Order[] = request.getParameterValues("Order");
	for(i=0; i <Order.length; i++){
	
	searchQuery.put(searchField,Integer.parseInt(Order[i]));
	DBCursor cursor=myCart.find(searchQuery);
	int ProductId=0;
	int Quantity=0;
	 HashMap products = (HashMap)request.getSession().getAttribute("products");
		String ProductNames="";	
	while(cursor.hasNext())
	{
	BasicDBObject obj = (BasicDBObject)cursor.next();								
	ProductId=(int)obj.get("ProductId");
	ProductNames=ProductNames+obj.get("ProductName");
	Quantity=(int)obj.get("Quantity");
		
			for(i=1;i<=products.size();i++)
        		{     
	 		ProductDetails p=(ProductDetails)products.get(i);
			if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()+Quantity);
		
			}
			 }
			}
						 
	myCart.remove(searchQuery);					
		request.getSession().setAttribute("products",products);
	
					searchField = "CustId";
					searchQuery = new BasicDBObject();
			int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
					searchQuery.put(searchField,CustId);
			int CartOrder=0;
        		DBCursor cursorOrder = myCart.find(searchQuery);
			if(cursorOrder.count() == 0){
				request.getSession().setAttribute("CartOrder",0);
				}

			else
			{
			while (cursorOrder.hasNext()) 
				{			 
			CartOrder++;
			cursorOrder.next();
				}	
			request.getSession().setAttribute("CartOrder",CartOrder);
			}



						response.setContentType("text/html");
			//Send the response back to the JSP
			out.println("<html>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='ViewPayment'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");
out.println("</a></li></ul></nav>");

 out.println("<div id='body'>");
          out.println("<section id='content'>");
                out.println("<article>");

			out.println("<h1> Order Cancelled for:");
		
			out.println(ProductNames);
			
			out.println("</h1>");
				
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
 			
			
		
	}   
		

			 	/*RequestDispatcher rd=getServletContext().getRequestDispatcher("/ViewCart");
			 		rd.forward(request, response);	*/

	}	


			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	
}	
	public void destroy()	{
      // do nothing.
	}
	
}


