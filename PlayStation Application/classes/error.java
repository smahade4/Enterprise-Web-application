
	
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

public class error extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
PrintWriter out=response.getWriter();
response.setContentType("text/html");		



out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'>");
out.println("<html><head>");
out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
  
    out.println("<script type='text/javascript' src='javascript.js'></script>");    
out.println(

"</head><body>");        
   

out.println("<body onload='init()'>");
out.println("<div id='container'>");
out.println("<form name='autofillform' action='autocomplete'>");
out.println("<table><tr><td></td>");      
out.println("<td><input type='text' size='40' id='complete-field' onkeyup='doCompletion()'>");
    out.println("</td></tr>");
     out.println("<tr><td id='auto-row' colspan='2'>");
     out.println(" <table id='complete-table' class='popupBox' />");
      out.println("</td></tr></table></form>");
if(request.getSession().getAttribute("Username").equals("StoreManager"))
{out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));
out.println("<li><a href='/HW1_MahadevanSushma/login.html'>Logout</a>");
 out.println("</a></li></ul></nav>");
}
else
{
out.println("<nav><ul><li class=''><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW1_MahadevanSushma/Content?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder")+"</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/login.html'>LogOut</a></li>");
out.println("</ul></nav></div>");
}

    
out.println(

"<h2>Error</h2><p>An error occured while performing the search Please try again</p>");        
       
if(request.getSession().getAttribute("Username").equals("StoreManager"))
{
out.println(

"<p>Go back to <a href='/HW1_MahadevanSushma/StoreManager' class='link'>application home</a></p>");

}
else
{out.println(

"<p>Go back to <a href='/HW1_MahadevanSushma/Index' class='link'>application home</a></p>");


}

}
catch (MongoException e) {
			e.printStackTrace();
		}
}
public void destroy()	{
      // do nothing.
	}
	
}
