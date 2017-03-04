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
			//Get the values from the form
	int Price=0;
	String ProductName = "";
	String Product="";
	String imageLocation = " ";
	int ProductId=0;
	String RetailerName="";
	String 	ManufacturerName="";
	String RetailerZip="";
	String RetailerState="";
	int QuantityAvailable=0;
	int Rebates=0;
	String RetailerCity="";
		MongoClient mongo = new MongoClient("localhost", 27017);
						
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("Tutorial_3");
		
		DBCollection myReviews = db.getCollection("myReviews");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
			
		 FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
	 HashMap products = (HashMap)objectInputStream.readObject();

	for(int i=1;i<=products.size();i++)
        			{     
			 ProductDetails p=(ProductDetails)products.get(i);
		
			if (request.getParameter(String.valueOf(p.getProductId()))!=null)
			{ 
			ProductId=p.getProductId();
			ProductName=p.getProductName();
			Product=p.getProduct();
			Price=p.getPrice();
			RetailerName=p.getRetailerName();
			ManufacturerName=p.getManufacturerName();
			RetailerCity=p.getRetailerCity();
			QuantityAvailable=p.getQuantityAvailable();
			Rebates=p.getRebates();
			RetailerZip=p.getRetailerZip();
			RetailerState=p.getRetailerState();
			RetailerCity=p.getRetailerCity();
			}
			}	
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("</head>");
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
 
			out.println("<h1>Write Review</h1>");							
			out.println(" <h3>" +ProductName+ "</h3> ");
			out.println("<form method=\"get\" action=\"SubmitReview.jsp\">");
			out.println("<fieldset>");
			out.println("<legend>Product information:</legend>");
			out.println("<legend>Reviews:</legend>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td> Product Name: </td>");
			out.println("<td> <input type=\"text\" name= \"productName\" value = \""+ProductName+"\" readonly>  </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Product Category: </td>");
			out.println("<td> <input type=\"text\" name= \"productCategory\" value = \""+Product+"\" readonly>  </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Product Price: </td>");
			out.println("<td> <input type=\"text\" name= \"productPrice\" value = \""+Price+"\" readonly>  </td>");
			out.println(" <input type=\"hidden\" name= \"ProductId\" value = \""+ProductId+"\"></tr>");

			out.println("<tr>");
			out.println("<td> RetailerName: </td>");
			out.println("<td> <input type=\"text\" name= \"retailerName\" value = \""+RetailerName+"\" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> RetailerZip: </td>");
			out.println("<td> <input type=\"text\" name= \"retailerZipcode\" value = \""+RetailerZip+"\" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> RetailerState: </td>");
			out.println("<td> <input type=\"text\" name= \"retailerState\" value = \""+RetailerState+"\" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> RetailerCity: </td>");
			out.println("<td> <input type=\"text\" name= \"retailerCity\" value = \""+RetailerCity+"\" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>ManufacturerName: </td>");
			out.println("<td> <input type=\"text\" name= \"consoleManufacturer\" value = \""+ManufacturerName+"\" readonly> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Manufacturer Rebate: </td>");
			
			if(Rebates>=0)
			{out.println("<input type='hidden' name='manufacturerRebate' value='Yes'");
			out.println("<td>Yes</td>");
			}
			else 
			{out.println("<input type='hidden' name='manufacturerRebate' value='No'");
			
			out.println("<td>No</td>");
			}
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>ProductOnSale:</td>");
			if(QuantityAvailable>=0)
			{
			out.println("<input type='hidden' name='productOnSale' value='Yes'");
			out.println("<td>Yes</td>");
			}
			else 
			{out.println("<input type='hidden' name='productOnSale' value='No'");
			out.println("<td>No</td>");
			}
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> User Name: </td>");
			out.println("<td> <input type=\"text\" name=\"userName\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td> User Age: </td>");
			out.println("<td> <input type=\"text\" name=\"userAge\"> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> User Gender: </td>");
			out.println("<td> <input type=\"radio\" name=\"userGender\" value=\"Male\">Male&nbsp&nbsp");
			out.println(" <input type=\"radio\" name=\"userGender\" value=\"Female\">Female </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<tr>");
			out.println("<td> User Occupation: </td>");
			out.println("<td> <input type=\"text\" name=\"userOccupation\"> </td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td> Review Rating: </td>");
			out.println("<td>");
			out.println("<select name=\"reviewRating\">");
			out.println("<option value=\"1\" selected>1</option>");
			out.println("<option value=\"2\">2</option>");
			out.println("<option value=\"3\">3</option>");
			out.println("<option value=\"4\">4</option>");
			out.println("<option value=\"5\">5</option>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Review Date: </td>");
			out.println("<td> <input type=\"date\" name=\"reviewDate\"> </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Review Text: </td>");
			out.println("<td><textarea name=\"reviewText\" rows=\"4\" cols=\"50\"> </textarea></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<input type=\"submit\" value=\"Submit Review\">");
			out.println("</fieldset>");
			out.println("</form>");
out.println("</section></div></body></html>");
						
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
%>