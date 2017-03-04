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
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class DataAnalytics1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter out= response.getWriter();
					
		DB db = mongo.getDB("Tutorial_3");
		
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myReviews = db.getCollection("myReviews");
		
					try {
				
		
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
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
out.println("<li><a href='/HW1_MahadevanSushma/login.html'>Logout</a>");
out.println("</a></li></ul></nav>");

      out.println("<div id='body'>");

            out.println("<section id='content'>");
	out.println("<article>");

                    out.println("<h3> Find Data </h3>");

                    out.println("<form method='get' class='searchform' action='FindReviews2'>");

                        out.println("<table class = 'query-table'>");
			out.println("<tr>");
			out.println("<td colspan = '4'> <b> Simple Search </b> </td></tr>");
  			out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity' > Select </td>");
                               out.println(" <td> Retailer City: </td>");
                               out.println(" <td>");                         
             		List cursorCity = myReviews.distinct("retailerCity");
       
 			out.println("  <select name='retailerCity'>");
				out.println("<option value='ALL_CITIES'>All CITIES</option>");
        		for(int i=0;i<cursorCity.size();i++)
				{			
					out.println("<option value="+cursorCity.get(i)+">"+cursorCity.get(i)+"</option>");
			}
			out.println("</td></tr>");
   

					out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerState' > Select </td>");
                               out.println(" <td> Retailer State: </td>");
                               out.println(" <td>");                         
             		List cursorState = myReviews.distinct("retailerState");
       
 			out.println("  <select name='retailerState'>");
				out.println("<option value='ALL_STATES'>All STATES</option>");
        		for(int i=0;i<cursorState.size();i++)
				{			
					out.println("<option value="+cursorState.get(i)+">"+cursorState.get(i)+"</option>");
			}
			out.println("</td></tr>");
			
				out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipCode' > Select </td>");
                               out.println(" <td> Retailer Zip Code: </td>");
                               out.println(" <td>");                         
             		List cursorZipCode = myReviews.distinct("retailerZipcode");
       
 			out.println("  <select name='retailerZipCode'>");
				out.println("<option value='ALL_ZIPCODE'>All ZIP CODE</option>");
        		for(int i=0;i<cursorZipCode.size();i++)
				{			
					out.println("<option value="+cursorZipCode.get(i)+">"+cursorZipCode.get(i)+"</option>");
			}
			out.println("</td></tr>");
			
			out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='consoleManufacturer' > Select </td>");
                               out.println(" <td> ManufacturerName: </td>");
                               out.println(" <td>");                         
             		List cursorManufacturerName = myReviews.distinct("consoleManufacturer");
       
 			out.println("  <select name='consoleManufacturer'>");
				out.println("<option value='ALL_ManufacturerName'>AllManufacturerName</option>");
        		for(int i=0;i<cursorManufacturerName.size();i++)
				{			
					out.println("<option value="+cursorManufacturerName.get(i)+">"+cursorManufacturerName.get(i)+"</option>");
			}
			out.println("</td></tr>");
	
			out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='userName' > Select </td>");
                               out.println(" <td> userName: </td>");
                               out.println(" <td>");                         
             		List cursorUserName = myReviews.distinct("userName");
       
 			out.println("  <select name='userName'>");
				out.println("<option value='ALL_UserName'>AllUserName</option>");
        		for(int i=0;i<cursorUserName.size();i++)
				{			
					out.println("<option value="+cursorUserName.get(i)+">"+cursorUserName.get(i)+"</option>");
			}
			out.println("</td></tr>");
			
			out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='userOccupation' > Select </td>");
                               out.println(" <td> userOccupation: </td>");
                               out.println(" <td>");                         
             		List cursorUserOccupation = myReviews.distinct("userOccupation");
       
 			out.println("  <select name='userOccupation'>");
				out.println("<option value='ALL_UserOccupation'>AllUserOccupation</option>");
        		for(int i=0;i<cursorUserOccupation.size();i++)
				{			
					out.println("<option value="+cursorUserOccupation.get(i)+">"+cursorUserOccupation.get(i)+"</option>");
			}
			out.println("</td></tr>");
		
			
      	

   		List cursorProduct = myReviews.distinct("productName");
       
			  out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='productName'> Select </td>");
                               out.println(" <td> Product Name: </td>");
                               out.println(" <td>");
                                  out.println("  <select name='productName'>");
				out.println("<option value='ALL_PRODUCTS'>All Products</option>");
		        for(int i=0;i<cursorProduct.size();i++)
				{			
					out.println("<option value="+cursorProduct.get(i)+">"+cursorProduct.get(i)+"</option>");
			}
			out.println("</td></tr>");
        
	 cursorProduct = myReviews.distinct("productCategory");
       
			  out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='productModel'> Select </td>");
                               out.println(" <td> Product Model: </td>");
                               out.println(" <td>");
                                  out.println("  <select name='productModel'>");
				out.println("<option value='ALL_PRODUCTS'>All Products</option>");
		        for(int i=0;i<cursorProduct.size();i++)
				{			
					out.println("<option value="+cursorProduct.get(i)+">"+cursorProduct.get(i)+"</option>");
			}
			out.println("</td></tr>");
                            
		out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
   	out.println("<td> Product Price: </td>");
   	  out.println("<td><input type='number' name='productPrice' value = '0' size=10 class='s' /> </td>");
	out.println("<td><input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
	out.println("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
	out.println("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than <br></td>");
      out.println("</tr>");

out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='productOnSale'> Select </td>");
   	out.println("<td> Product on sale: </td>");
   	  out.println("<td><input type='radio' name='productOnSale' value = 'Yes' />Yes </td>");
	  out.println("<td><input type='radio' name='productOnSale' value = 'No'  />No </td>");
	 out.println("</tr>");
out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='manufacturerRebate'> Select </td>");
   	out.println("<td> Manufacturer Rebate: </td>");
   	  out.println("<td><input type='radio' name='manufacturerRebate' value = 'Yes'  />Yes </td>");
	  out.println("<td><input type='radio' name='manufacturerRebate' value = 'No'  />No </td>");
	 out.println("</tr>");

out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='userGender'> Select </td>");
   	out.println("<td>User Gender: </td>");
   	  out.println("<td><input type='radio' name='userGender' value = 'Male' />Male </td>");
	  out.println("<td><input type='radio' name='userGender' value = 'Female' />Female </td>");
	 out.println("</tr>");

	
	List	 cursorRetailer = myReviews.distinct("retailerName");
       
			  out.println("<tr>");
			out.println("<td> <input type='checkbox' name='queryCheckBox' value='retailerName'> Select </td>");
                               out.println(" <td> retailerName: </td>");
                               out.println(" <td>");
                                  out.println("  <select name='retailerName'>");
				out.println("<option value='retailerName'>All</option>");
		        for(int i=0;i<cursorRetailer.size();i++)
				{			
					out.println("<option value="+cursorRetailer.get(i)+">"+cursorRetailer.get(i)+"</option>");
			}
			out.println("</td></tr>");
        


out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='userAge'> Select </td>");
   out.println("<td> userAge: </td>");
     out.println("<td><input type='number' name='userAge' value = '0' size=10 class='s' /> </td>");
	out.println("<td><input type='radio' name='compareAge' value='EQUALS_TO' checked> Equals <br>");
	out.println("<input type='radio' name='compareAge' value='GREATER_THAN'> Greater Than <br>");
out.println("<input type='radio' name='compareAge' value='LESS_THAN'> Less Than <br></td>");
      out.println("</tr>");


         out.println("<tr>");
	out.println("<td> <input type='checkbox' name='queryCheckBox' value='reviewText'> Select </td>");
                 out.println("<td> Review Text: </td>");
                          out.println(" <td>");
                        out.println(" <input type='text' name='reviewText' value = 'All' class='s' /> </td>");	
		out.println("<td><input type='radio' name='compareText' value='EQUALS_TO' checked> Match Equals <br>");
	out.println("</tr>");
	         out.println("<tr>");	
 		out.println("<td> <input type='checkbox' name='queryCheckBox' value='reviewDate'> Select </td>");
		out.println("<td> Review Date: </td>");
                          out.println(" <td>");
                        out.println(" <input type='text' name='reviewDate' /> </td>");	
		out.println("<td><input type='radio' name='compareDate' value='EQUALS_TO' checked> Match Equals <br>");
	
			out.println("<input type='radio' name='compareDate' value='GREATER_THAN'> Greater Than<br>"); 
		out.println("<input type='radio' name='compareDate' value='LESS_THAN'> Less Than"); 
		out.println("</td></tr>");
		
  		 out.println(" <tr><td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
                             out.println(" <td> Review Rating: </td>");
                              out.println("  <td>");
                                  out.println("  <select name='reviewRating'>");
                                  out.println(" <option value='1'>1</option>");
  				    out.println("<option value='2'>2</option>");
                                      out.println("  <option value='3'>3</option>");
                                       out.println(" <option value='4'>4</option>");
                                      out.println(" <option value='5'>5</option>");
                               out.println("   </td>");
					       out.println(" <td>");
		       out.println("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
					out.println("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than<br>"); 
		out.println("<input type='radio' name='compareRating' value='LESS_THAN'> Less Than"); 
								
			out.println("</td>");
                            out.println("</tr>");
							out.println("<tr><td>");
						out.println("Return:</td>");
					out.println("<td colspan = '2'>");
					out.println("<select name='returnValue'>");
					out.println("<option value='ALL'>All</option>");
					out.println("<option value='TOP_2'>Top 2 </option>");
					out.println("<option value='TOP_3'>Top 3 </option>");
                                        out.println("<option value='TOP_5'>Top 5 </option>");
                                        out.println("<option value='TOP_10'>Top 10 </option>");
						out.println("<option value='LATEST_5'>Latest 5 </option>");
					out.println("<option value='LATEST_10'>Latest 10 </option>");
                               out.println(" </td> </tr>");							
							
				out.println("<tr>");
				out.println("<td>");
				out.println("Select Operation:	</td>");


					out.println("<td>");
					out.println("<select name='conditionValue'>");
					out.println("<option value='none'>None</option>");
                                      	out.println(" <option value='count'>Count</option>");
                                        out.println("<option value='sum'>Sum </option>");
                                        out.println("<option value='avg'>Avg</option>");
					out.println("<option value='max'>Max </option>");
				out.println("<option value='min'>Min </option>");
				out.println("</td><td>");
				out.println("Select column:</td>");
				out.println("<td> ");
					out.println("<select name='dataValue'>");
					out.println("<option value='ALL'>All</option>");
                                      out.println("<option value='productPrice'>ProductPrice</option>");
                                       out.println("<option value='reviewRating'>ReviewRating</option>");
                                         out.println("<option value='userAge'>userAge</option>"); 
				out.println("<option value='reviewDate'>reviewDate</option>");                                           	   
					out.println("</td></tr>");							
				out.println("<tr><td> <b> Grouping </b> </td>");
				out.println("</tr><tr>");
			out.println("<td><input type='checkbox' name='extraSettings' value='COUNT_ONLY'> Count Only");
			out.println("</td>");
			out.println("<td><input type='checkbox' name='extraSettings' value='liked_ONLY'>liked");
			out.println("</td>");
			out.println("<td><input type='checkbox' name='extraSettings' value='disliked_ONLY'>Disliked");
			out.println("</td>");
			out.println("<td><input type='checkbox' name='extraSettings' value='SORT_BY'> Sort By");
					out.println("<select name='sortValue'>");
					out.println("<option value='ALL' selected>All</option>");
                                      out.println("<option value='retailerCity' selected>City</option>");
                               out.println("<option value='productName'>Product Name</option>");                                       
                           	out.println("<option value='retailerName'>Retailer Name</option>");                                        
                            out.println("<option value='retailerZipcode'>Zip Code</option>"); 
			    out.println("<option value='retailerState'>State</option>"); 
			    out.println("<option value='consoleManufacturer'>Manufacturer Name</option>"); 
			    out.println("<option value='manufacturerRebate'>ManufacturerRebate</option>"); 
			    out.println("<option value='productPrice'>productPrice</option>"); 
			    out.println("<option value='reviewRating'>reviewRating</option>"); 
			    out.println("<option value='productCategory'>productCategory</option>"); 
			    out.println("<option value='productOnSale'>productOnSale</option>"); 
			    out.println("<option value='userName'>userName</option>"); 
			    out.println("<option value='userAge'>userAge</option>"); 
			    out.println("<option value='userOccupation'>userOccupation</option>"); 
			    out.println("<option value='userGender'>userGender</option>"); 
			    out.println("<option value='reviewDate'>reviewDate</option>");                                           
			out.println("</td>");
			out.println("<td><input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By");
			out.println("</td>");	
			out.println("<td>");
			out.println("<select name='groupByDropdown'>");
                             out.println("<option value='retailerCity' selected>City</option>");
                                out.println("<option value='productName'>Product Name</option>");                                       
                           out.println("<option value='retailerName'>Retailer Name</option>");                                        
                            out.println("<option value='retailerZipcode'>Zip Code</option>"); 
			    out.println("<option value='retailerState'>State</option>"); 
			    out.println("<option value='consoleManufacturer'>Manufacturer Name</option>"); 
			    out.println("<option value='manufacturerRebate'>ManufacturerRebate</option>"); 
			    out.println("<option value='productPrice'>productPrice</option>"); 
			    out.println("<option value='reviewRating'>reviewRating</option>"); 
			    out.println("<option value='productCategory'>productCategory</option>"); 
			    out.println("<option value='productOnSale'>productOnSale</option>"); 
			    out.println("<option value='userName'>userName</option>"); 
			    out.println("<option value='userAge'>userAge</option>"); 
			    out.println("<option value='userOccupation'>userOccupation</option>"); 
			    out.println("<option value='userGender'>userGender</option>"); 
			    out.println("<option value='reviewDate'>reviewDate</option>");                                           
			out.println("</td></tr>");
			out.println("<tr>");
	out.println("<td colspan = '4'> <input type='submit' name='TrendingButton' value='TrendingButton' class='formbutton' /></td>");
         
           out.println("<td colspan = '4'> <input type='submit' name='TrendingButton' value='FindData' class='formbutton' /></td>");
                                
                            out.println("</tr>");

                        out.println("</table></form>");

                out.println("</article></section>");


	out.println("<aside class='sidebar'>");
			
				out.println("<ul>");	
					   out.println("<li>");
							out.println("<h4>Options</h4>");
							out.println("<ul>");
							out.println("<li><a href='WriteReview.html'>Write Reviews</a></li>");
							out.println("<li><a href='/HW1_MahadevanSushma/DataAnalytics1'>Data Analytics</a></li>");
							
							out.println("</ul>");
						out.println("</li>");	
						
					out.println("</ul>");
				
			out.println("</aside>");

out.println("</div></body></html>");

	
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
			
	


}