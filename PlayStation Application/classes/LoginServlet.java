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
import java.text.*;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	protected HashMap products = new HashMap();
    	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
			//Get the values from the form
			String Username = request.getParameter("Username");
			 //response.getWriter().println("inside collection"+ Username);	
			String Password = request.getParameter("Password");
			
			String ButtonVal = request.getParameter("ContinueAsGuest");
			if(ButtonVal!=null)
			{PrintWriter out=response.getWriter();
			HttpSession session = request.getSession(true);	
			session.setAttribute("Username","Guest");
			session.setAttribute("UserId",0);
						session.setAttribute("CartOrder",0);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/Index");
			 		rd.forward(request, response);
			return;
			}

			if(Username.equals("SalesMen") && Password.equals("SalesMen"))
			{					HttpSession session = request.getSession(true);	

					session.setAttribute("Username","SalesMen");
			 		session.setAttribute("UserId",0);					
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/SalesHome");
			 		rd.forward(request, response);
			}	
			if(Username.equals("StoreManager") && Password.equals("StoreManager"))
			{
					HttpSession session = request.getSession(true);	

				session.setAttribute("Username","StoreManager");
			 		session.setAttribute("UserId",0);
					
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/StoreManager");
			 		rd.forward(request, response);

			}				
					
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myLogin = db.getCollection("LoginDetails");

        
        		DBCursor cursor = myLogin.find();
			if(cursor.count() == 0){
			
  			response.sendRedirect("/PageLayoutCSS/login.html"); 
			return;	
			}	
			while (cursor.hasNext()) 
				{			 

					BasicDBObject obj = (BasicDBObject) cursor.next();				

				if(obj.getString("Username").equals(Username) && obj.getString("Password").equals(Password))
				{int productNum=0;
			    	Iterator it = products.entrySet().iterator();
 	 			while (it.hasNext()) 
				{	
				productNum++;
				    it.next();
					}
					ProductDetails p= new ProductDetails();
				p.setProduct("Xbox");
				p.setProductId(productNum+1);
				p.setProductName("XBOX 360");
				p.setPrice(300);
				p.setProductDescription("Xbox color blue");
				p.setQuantityAvailable(7);
				p.setRebates(5);
				p.setDiscounts(5);
				p.setWarranty("No");
				SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
				//p.setShippingDate(format.parse("10-29-2015"));
               			products.put(productNum+1, p);
				productNum++;

		 		p=new ProductDetails();
				p.setProduct("Xbox");
				p.setProductId(productNum+1);
				p.setProductName("XBOX Original");
				p.setPrice(300);
				p.setProductDescription("Xbox color black");
				p.setQuantityAvailable(4);
				p.setRebates(4);
				p.setDiscounts(5);
				p.setWarranty("Yes");
				p.setWarrantyCost(50);
				p.setWarrantyPeriod(4);
				//p.setShippingDate(format.parse("10-14-2015"));
               			
               			products.put(productNum+1, p);
				productNum++;

				 p=new ProductDetails();
				p.setProductId(productNum+1);	
				p.setProduct("PS");
				p.setProductName("PS1");
				p.setPrice(300);
				p.setProductDescription("black Console");
				p.setQuantityAvailable(4);
				p.setRebates(3);
				p.setDiscounts(4);
				p.setWarranty("No");
				//p.setShippingDate(format.parse("10-29-2015"));
               			
				products.put(productNum+1, p);

				productNum++;
				 p=new ProductDetails();
				p.setProduct("PS");
				p.setProductId(productNum+1);
				p.setProductName("PS2");
				p.setPrice(300);
				p.setProductDescription("Blue Console");
				p.setQuantityAvailable(3);
				p.setRebates(2);
				p.setDiscounts(4);
				p.setWarranty("No");
				//p.setShippingDate(format.parse("10-29-2015"));
               			
				products.put(productNum+1, p);
					
				mongo = new MongoClient("localhost", 27017);

					HttpSession session = request.getSession(true);	
					session.setAttribute("Username",obj.getString("Username"));
						int UserId=(int)obj.get("UserId");
			 		session.setAttribute("UserId",UserId);
					session.setAttribute("products",products);

					DBCollection myCart = db.getCollection("CartDetails");
					String searchField = "CustId";
						BasicDBObject searchQuery = new BasicDBObject();
						searchQuery.put(searchField,UserId);
			int CartOrder=0;
        		DBCursor cursorOrder = myCart.find(searchQuery);
			if(cursorOrder.count() == 0){
				session.setAttribute("CartOrder",0);
			}	

			else
			{
			while (cursorOrder.hasNext()) 
				{			 
			CartOrder++;
			cursorOrder.next();
				}	
			session.setAttribute("CartOrder",CartOrder);
			}

				

			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/Index");
			 		rd.forward(request, response);

							}
				}

			 	RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
			 		rd.forward(request, response);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	

}

	
}