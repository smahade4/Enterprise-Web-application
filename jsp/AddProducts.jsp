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
		

		int productNum=0;
  FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
 HashMap products = (HashMap)objectInputStream.readObject();

				
	if(products!=null)
		{
		Iterator it = products.entrySet().iterator();			
    		while (it.hasNext()) 
		{
		Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
			if(productNum<p.getProductId())
		productNum=p.getProductId();
		}
		}
		}
		%>
		<html><head>
			<link rel='stylesheet' href='styles.css' type='text/css'>
			<body><div id='container'><header>
		<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>
		<li class=''><a href='ViewProducts.jsp'>View Products</a></li>
		<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>
		<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>
			<li class=''><a href='SelectProductsDelete.jsp' Name='DeleteProducts'>DeleteProducts</a></li>
		<li><a href='/HW3_MahadevanSushma/login.html'>Logout</a>
 			</a></li></ul></nav>
		 
			
<%
	out.println(request.getSession().getAttribute("Username"));
	
out.println(" <form method=\"get\" action=\"SubmitProd.jsp\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Id: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductId\" value= "+(productNum+1)+" readonly> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product: </td>");
			out.println("<td> <input type=\"text\" name=\"Product\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Product Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>ProductDescription: </td>");
			out.println("<td> <input type=\"text\" name=\"ProductDescription\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Retailer Name: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Manufacturer Name: </td>");
			out.println("<td> <input type=\"text\" name=\"ManufacturerName\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Retailer State: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerState\"> </td>");
			out.println("</tr>");		


			out.println("<tr>");
			out.println("<td>Retailer City: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerCity\"> </td>");
			out.println("</tr>");


			out.println("<tr>");
			out.println("<td>Retailer Zip: </td>");
			out.println("<td> <input type=\"text\" name=\"RetailerZip\"> </td>");
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td>QuantityAvailable: </td>");
			out.println("<td> <input type=\"text\" name=\"QuantityAvailable\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Rebates: </td>");
			out.println("<td> <input type=\"text\" name=\"Rebates\"> </td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td>Price: </td>");
			out.println("<td> <input type=\"text\" name=\"Price\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Discount: </td>");
			out.println("<td> <input type=\"text\" name=\"Discounts\"> </td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>Warranty: </td>");
			out.println("<td> <input type=\"radio\" name=\"Warranty\" value=\"Yes\">Yes</input>"); 
			out.println("<input type=\"radio\" name=\"Warranty\" value=\"No\">No</input></td>");
			out.println("</tr>");
		


			out.println("<tr>");
			out.println("<td>WarrantyCost: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyCost\"> </td>");
			out.println("</tr>");
		

			out.println("<tr>");
			out.println("<td>WarrantyPeriod: </td>");
			out.println("<td> <input type=\"text\" name=\"WarrantyPeriod\"> </td>");
			out.println("</tr>");

			/*out.println("<tr>");
			out.println("<td>ShippingDate: </td>");
			out.println("<td> <input type=\"Date\" name=\"ShippingDate\"> </td>");
			out.println("</tr>");
			*/
			out.println("<tr>");
			out.println("<td><input type='Submit' name='AddProducts' value='Add'></td>");
						
					out.println("</tr>");

			out.println("</table>");				
			out.println("</body>");
				out.println("</html>");
						

			} catch (Exception e) {
			e.printStackTrace();
			
			}

%>