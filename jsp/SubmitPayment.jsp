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

<%!
  public Date addDays(Date d, int days)
    {
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        return d;
    }
%>
<%
  
		try{
				
			MongoClient mongo = new MongoClient("localhost", 27017);					
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			DBCollection myOrder = db.getCollection("OrderDetails");
				
			// If the collection does not exists, MongoDB will create it for you
			//DBCollection myCart = db.getCollection("CartDetails");
			HashMap orders=(HashMap)session.getAttribute("orders");
			int CustId=Integer.parseInt(request.getSession().getAttribute("UserId").toString());
			int PaymentId=0;
			DBCursor cursorPayment=myOrder.find();
			while(cursorPayment.hasNext())
			{
			BasicDBObject obj = (BasicDBObject) cursorPayment.next();	
			if(PaymentId<Integer.parseInt(obj.get("PaymentId").toString()));
				PaymentId=Integer.parseInt(obj.get("PaymentId").toString());
			}
				String ProductNames=""; 
				
				
			Iterator entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			Map.Entry er = (Map.Entry)entries.next();
			if(er!=null)
			{
			OrderDetails o=(OrderDetails)er.getValue();
			if(o.getCustId()==CustId)
			{
			if(!(o.getOrderStatus().equals("Unavailable")))
			{
			String ShippingStatus="Pending";	
				
			ProductNames=ProductNames+o.getProductName();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date dt=new Date();					  
			Date date=new Date();	
			 %>				  
			<% BasicDBObject doc = new BasicDBObject("title", "myOrder").append("PaymentId", PaymentId+1).
				append("FirstName",request.getParameter("FirstName")).
				append("LastName", request.getParameter("LastName")).
				append("Price", o.getPrice()).
				append("OrderId", o.getOrderId()).
				append("CustId", o.getCustId()).
				append("ProductId",o.getProductId()).
				append("ProductName", o.getProductName()).

				//append("ProductDesc", o.getProductDesc()).
				append("Discounts", o.getDiscounts()).
				append("Rebates", o.getRebates()).
				append("WarrantyPurchase", o.getWarrantyPurchase()).
				append("WarrantyPeriod", o.getWarrantyPeriod()).
				append("WarrantyCost", o.getWarrantyCost()).
				append("Total", o.getTotal()).
				append("Quantity", o.getQuantity()).
				append("CardNumber",request.getParameter("CardNumber")).
				append("ExpirationDate",request.getParameter("ExpirationDate")).
				append("CVNO",request.getParameter("CVNO")).
				append("ShippingAddress",request.getParameter("ShippingAddress")).	
				append("City",request.getParameter("City")).	
				append("State",request.getParameter("State")).	
				append("ZipCode",request.getParameter("ZipCode")).	
				append("PhoneNo",request.getParameter("PhoneNo")).		
				append("EmailId",request.getParameter("EmailId")).
				append("ShippingStatus","Pending").
				append("OrderDate",date).
				append("ShippingDate",addDays(dt,20));
				myOrder.insert(doc);
				PaymentId++;
				entries.remove();
			}		
				}
		}
		}
			int CartOrder=0;
        		if(orders!= null)
			{
			 entries = orders.entrySet().iterator();
			while (entries.hasNext()) 
			{
			 //out.println("entry"+entries.next());
			Map.Entry er = (Map.Entry)entries.next();
			out.println(er);
			if(er!=null)
			{
			out.println("entry"+er.getKey()+er.getValue());
			OrderDetails o=(OrderDetails)er.getValue();
			if(o.getCustId()==CustId)
			{CartOrder++;
			}
			}
			}	
			}
			request.getSession().setAttribute("CartOrder",CartOrder);
			

			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			
			out.println("<html>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
		out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
 out.println("<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrder</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/Login.html'>Logout</a>");
out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");


			out.println("<h1> Payment Done for:");
		
			out.println(ProductNames);
			
			out.println("</h1>");
			                out.println("</article></section>");			

			
			out.println("</div></body>");
			out.println("</html>");
			 			
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}

%>
