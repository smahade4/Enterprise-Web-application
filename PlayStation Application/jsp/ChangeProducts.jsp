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
		try{
	MongoClient mongo;
	    
						
				out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='StoreManager.jsp'>Home</a></li>");
out.println("<li class=''><a href='ViewProducts.jsp'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class=''><a href='SelectProductsDelete.jsp' Name='DeleteProducts'>DeleteProducts</a></li>");
out.println("<li class='end'><a href='login.html' Name='login'>Logout</a></li>");

 out.println("</a></li></ul></nav>");
	
out.println(request.getSession().getAttribute("Username"));
	
			out.println(" <form method=\"get\" action=\"UpdateProducts.jsp\">");
			FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
	 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 		HashMap products = (HashMap)objectInputStream.readObject();

			Iterator it = products.entrySet().iterator();			
    			while (it.hasNext()) 
			{
			Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(p.getProductId()==Integer.parseInt(request.getParameter("ProductId")))
			{
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductId\" value= "+request.getParameter("ProductId")+" readonly> </td>");
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
			out.println("<td>ProductDescription: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductDescription\" value= '"+p.getProductDescription()+"'> </td>");
			out.println("</tr>");		
			
			
			out.println("<tr>");
			out.println("<td>QuantityAvailable: </td>");
			out.println("<td> <input type=\"text\" name=\"QuantityAvailable\" value="+p.getQuantityAvailable()+"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Rebates: </td>");
			out.println("<td> <input type=\"text\" name=\"Rebates\" value="+p.getRebates()+"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Price: </td>");
			out.println("<td> <input type=\"text\" name=\"Price\" value="+p.getPrice()+"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Discount: </td>");
			out.println("<td> <input type=\"text\" name=\"Discounts\"  value="+p.getDiscounts()+"> </td>");
			out.println("</tr>");
					
			out.println("<tr>");
			out.println("<td>Warranty: </td>");
			out.println("<td> <input type=\"radio\" name=\"Warranty\" value=\"Yes\">Yes</input>"); 
			out.println("<input type=\"radio\" name=\"Warranty\" value=\"No\">No</input></td>");
			out.println("</tr>");
		

			/*out.println("<tr>");
			out.println("<td>ShippingDate: </td>");

			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			out.println("<td> <input type=\"text\" name=\"ShippingDate\" value='"+format.format(p.getShippingDate())+"'> Date Should be in MM/dd/yyyy format</td>");
			out.println("</tr>");
			*/
			if(p.getWarranty().equals("Yes"))
			{
			out.println("<tr>");
			out.println("<td>WarrantyCost: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyCost\" value="+p.getWarrantyCost()+"> </td>");
			out.println("</tr>");
		

			out.println("<tr>");
			out.println("<td>WarrantyPeriod: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyPeriod\" value="+p.getWarrantyPeriod()+"> </td>");
			out.println("</tr>");
			}
						
			else
			{
			out.println("<tr>");
			out.println("<td>WarrantyCost: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyCost\"> </td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>WarrantyPeriod: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyPeriod\"> </td>");
			out.println("</tr>");
			
			}

			out.println("<tr>");
			out.println("<td><input type='Submit' name='Update' value='Update'></td>");
			out.println("</tr>");

			out.println("</table>");				
			out.println("</body>");
				out.println("</html>");
			}}
			}
			} catch (MongoException e) {
			e.printStackTrace();
				}

%>