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
public class SignUp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		MongoClient mongo = new MongoClient("localhost", 27017);
	
	public void init(){
			
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
				DB db = mongo.getDB("CSP595Tutorial");

		DBCollection LoginDetails = db.getCollection("LoginDetails");
		
	

		int UserId=0;
		String Username=request.getParameter("Username");	
		String Password=request.getParameter("Password");
		String FirstName=request.getParameter("FirstName");
		String EmailId=request.getParameter("EmailId");
		String PhoneNo=request.getParameter("PhoneNo");
		
		DBCursor cursor = LoginDetails.find();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(Username.equals("")||Password.equals(""))		
		{
		out.println("UserName Password Cannot be blank");
		out.println("<li class=''><a href='login.html'>login</a></li>");
		out.println("<li class=''><a href='Register.html'>SignUp</a></li>");
		return;
		}
	

		while(cursor.hasNext()){
		BasicDBObject searchQuery = (BasicDBObject)cursor.next();
		
		
		if(searchQuery.get("Username").equals(Username))		
		{
		out.println("Userid already exists Please choose different name");
		out.println("<li class=''><a href='login.html'>login</a></li>");
		out.println("<li class=''><a href='Register.html'>SignUp</a></li>");

		return;
		}

		UserId++;		
		}
			BasicDBObject doc = new BasicDBObject("title", "LoginDetails").
				append("UserId", UserId+1).
				append("Username",Username).
				append("Password",Password).
				append("FirstName", FirstName).
				append("EmailId", EmailId).
				append("PhoneNo",PhoneNo);
	
		response.setContentType("text/html");
		LoginDetails.insert(doc);
		
									
	out.println("Added User");
				
	
									
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
}