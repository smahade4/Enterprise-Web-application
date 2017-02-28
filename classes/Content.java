	
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

public class Content extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
 HashMap products = (HashMap)request.getSession().getAttribute("products");
		
			PrintWriter out = response.getWriter();
	
out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");

out.println(" <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
    out.println("<title>Auto-Completion using AJAX</title>");

    out.println("<script type='text/javascript' src='javascript.js'></script>");
  out.println("</head>");

out.println("<body onload='init()'>");
out.println("<div id='container'>");
out.println("<img class='header-image' src='images/img_XBox.jpg' width = '100%' height = '100%' alt='X Box' />");
out.println("<form name='autofillform' action='autocomplete'>");
out.println("<table><tr>");      
out.println("<td><input type='text' size='40' id='complete-field' onkeyup='doCompletion()'>");
    out.println("</td></tr>");
     out.println("<tr><td id='auto-row' colspan='2'>");
     out.println(" <table id='complete-table' class='popupBox' />");
      out.println("</td></tr></table></form>");

    	

	out.println("<p>Following are the different models of"+ request.getParameter("producthref")+" for sale.</p>");	
           
            	out.println("<p>Click on a model to purchase or check the reviews.</p>");
           
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW1_MahadevanSushma/Content?producthref=Nintendo'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
//out.println(request.getSession().getAttribute("UserId"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>");

out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

	for(int i=1;i<=products.size();i++)
        	{     
	 	ProductDetails p=(ProductDetails)products.get(i);
		if(((String)p.getProduct()).equals((String)request.getParameter("producthref")))
		{	
            					
            	out.println("<table><tr><td>");
		out.println("<img src = 'images/img_XBoxOriginal.jpg' width = '200' height = '200' alt = 'X Box Orginal'>");

					out.println("</td><td>");
					out.println(" Specifications <br>");
					out.println(p.getProductId());
					out.println("<br> ProductDetails:");
					out.println("<br> ProductPrice:");
					out.println(p.getPrice());
					out.println("<br> ProductDiscounts:");
					out.println(p.getDiscounts());
					out.println("<br> ProductWarranty:");
					out.println(p.getWarranty());
					out.println("<br> ProductWarrantyCost:");
					out.println(p.getWarrantyCost());
					
					out.println(p.getProductDescription());
					out.println("<br> Quantity Available:"); out.println(p.getQuantityAvailable());
				 	out.println("<br> Product:"); out.println(p.getProductName());
					//out.println("<br> ShippingDate : " +format.format(p.getShippingDate()));
					out.println("<td><form class = 'submit-button' method = 'get' action = 'WriteReview'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+"  value = 'Write Review'></form>");
					out.println("<form class = 'submit-button' method = 'get' action = 'ViewReviews'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+"  value = 'View Reviews'>");
						out.println("</form>");
					if(p.getQuantityAvailable()<=0)
					{out.println("Out Of Stock");
					}
					else
					{
					out.println("<form class = 'submit-button' name ='Buy'  method = 'get' action = 'Buy'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+" value='Buy'></form>");
					}
					
					out.println("</td></tr></table>");
  
	
	}
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


	out.println("</div></body></html>");
	}
catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}


