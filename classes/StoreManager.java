	
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


public class StoreManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//protected Map Products = new HashMap();
    
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB

		mongo = new MongoClient("localhost", 27017);
		//Products.put(New ProductDetails());
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myCart = db.getCollection("ProductDetails");
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
 
			//String CustId=request.getSession().getAttribute("UserId").toString();
			///searchQuery.put(searchField,CustId);

			//DBCursor cursor = myCart.find(searchQuery);
			
			PrintWriter out = response.getWriter();
response.setContentType("text/html");	
out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");

out.println(" <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
    out.println("<title>Auto-Completion using AJAX</title>");

    out.println("<script type='text/javascript' src='javascript.js'></script>");
  out.println("</head>");

out.println("<body onload='init()'>");
out.println("<div id='container'>");
out.println("<form name='autofillform' action='autocomplete'>");
out.println("<table><tr><td></td>");      
out.println("<td><input type='text' size='40' id='complete-field' onkeyup='doCompletion()'>");
    out.println("</td></tr>");
     out.println("<tr><td id='auto-row' colspan='2'>");
     out.println(" <table id='complete-table' class='popupBox' />");
      out.println("</td></tr></table></form>");
			
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));
out.println("<li><a href='/HW1_MahadevanSushma/login.html'>Logout</a>");
 out.println("</a></li></ul></nav>");
	

				    
	
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


