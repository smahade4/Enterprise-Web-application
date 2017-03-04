	<%@ page import="java.io.*,javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="com.mongodb.MongoClient"%>
<%@ page import="com.mongodb.MongoException"%>
<%@ page import="com.mongodb.WriteConcern"%>
<%@ page import="com.mongodb.DB"%>
<%@ page import="com.mongodb.DBCollection"%>
<%@ page import="com.mongodb.BasicDBObject"%>
<%@ page import="com.mongodb.DBObject"%>
<%@ page import="com.mongodb.DBCursor"%>
<%@ page import="com.mongodb.ServerAddress"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set,java.util.Date,javax.servlet.http.*,javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="assign3.*"%>


<%	
try {	
	MongoClient mongo;
	
		mongo = new MongoClient("localhost", 27017);
		
SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
 HashMap products = (HashMap)objectInputStream.readObject();

		
			
	
out.println("<html><head><link rel='stylesheet' href='styles.css' type='text/css'></head><body>");
out.println("<div id='container'>");
out.println("<img class='header-image' src='images/img_XBox.jpg' width = '100%' height = '100%' alt='X Box' />");
out.println("<p>Following are the different models of"+ request.getParameter("producthref")+" for sale.</p>");	
           
            	out.println("<p>Click on a model to purchase or check the reviews.</p>");
           
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Nintendo'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
//out.println(request.getSession().getAttribute("UserId"));
 out.println("<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/login.html'>logout</a></li>");

out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

		Iterator it = products.entrySet().iterator();			
    		while (it.hasNext()) 
		{
		Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
		{
			ProductDetails p=(ProductDetails)pi.getValue();
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
					out.println("<td><form class = 'submit-button' method = 'get' action = 'WriteReview.jsp'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+"  value = 'Write Review'></form>");
					out.println("<form class = 'submit-button' method = 'get' action = 'ViewReviews.jsp'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+"  value = 'View Reviews'>");
						out.println("</form>");
					if(p.getQuantityAvailable()<=0)
					{out.println("Out Of Stock");
					}
					else
					{
					out.println("<form class = 'submit-button' name ='Buy'  method = 'get' action = 'Buy.jsp'>");
					out.println("<input class = 'submit-button' type = 'submit' name ="+p.getProductId()+" value='Buy'></form>");
					}
					
					out.println("</td></tr></table>");
  
	
	}
	}
	}    
            out.println("</article></section>");
	

	out.println("</div></body></html>");
	}
catch (MongoException e) {
			e.printStackTrace();
		}
	
	
	

%>

