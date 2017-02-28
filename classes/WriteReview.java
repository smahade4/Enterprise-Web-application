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
public class WriteReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int Price=0;
	String ProductName = "";
	String Product="";
	String imageLocation = " ";
	int ProductId=0;
	String RetailerName="";
	String 	ManufacturerName="";
	String RetailerZip="";
	String RetailerState="";
	int QuantityAvailable;
	int Rebates;
	String RetailerCity="";
	public void init(){
		//Connect to Mongo DB
		MongoClient mongo = new MongoClient("localhost", 27017);
						
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("CustomerReviews");
		
		DBCollection myReviews = db.getCollection("myReviews");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
						
		try{
			//Get the values from the form
			
			 HashMap products = (HashMap)request.getSession().getAttribute("products");
			for(int i=1;i<=products.size();i++)
        			{     
			 ProductDetails p=(ProductDetails)products.get(i);
		
			if (request.getParameter(String.valueOf(p.getProductId()))!=null)
			{ProductId=p.getProductId();
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
out.println("<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>");
out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='/HW1_MahadevanSushma/Content?producthref=Accesories'>Accessories</a></li>");
out.println("<li><a href='/HW1_MahadevanSushma/ViewCart'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));

//out.println(request.getSession().getAttribute("UserId"));
 out.println("<li class=''><a href='/HW1_MahadevanSushma/ViewPayment'>MyOrder</a></li>");
//out.println(request.getSession().getAttribute("products"));
out.println("<li><a href='/HW1_MahadevanSushma/Logout'>Logout</a>");
out.println("</a></li></ul></nav>");
 
			out.println("<h1>Write Review</h1>");							
			out.println(" <h3>" +ProductName+ "</h3> ");
			out.println("<form method=\"get\" action=\"SubmitReview\">");
			out.println("<fieldset>");
			out.println("<legend>Product information:</legend>");
			//out.println("<img src =\" "+imageLocation+" \"width = \"200\" height = \"200\" alt = \"X Box Orginal\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td> Product Name: </td>");
			out.println("<td>"+ProductName+"</td>");
			out.println("</tr>");						
			out.println("<tr>");
			out.println("<td> Product Price: </td>");
			out.println("<td>" +Price+ "</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</fieldset>");
			out.println("<fieldset>");
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

	}
}

