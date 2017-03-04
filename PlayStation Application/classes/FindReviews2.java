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
import java.util.*;
import java.text.*;

public class FindReviews2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
					
		DB db = mongo.getDB("Tutorial_3");
		
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myReviews = db.getCollection("myReviews");
		
		BasicDBObject query = new BasicDBObject();
				
		try {
			
			// Get the form data
			String productName = request.getParameter("productName");
			String productCategory = request.getParameter("productCategory");
			String retailerZipcode = request.getParameter("retailerZipcode");
			String retailerCity = request.getParameter("retailerCity");
			String retailerState=request.getParameter("retailerState");
			String userOccupation=request.getParameter("userOccupation");
			int userAge=Integer.parseInt(request.getParameter("userAge"));
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String compareDate = request.getParameter("compareDate");
			String consoleManufacturer=request.getParameter("consoleManufacturer");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
			String compareRating = request.getParameter("compareRating");
			String comparePrice = request.getParameter("comparePrice");
			String userName=request.getParameter("userName");
			//String compareDate = request.getParameter("compareDate");
			String compareAge = request.getParameter("compareAge");
			//SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
			String[] filters = request.getParameterValues("queryCheckBox");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String conditionValueDropdown=request.getParameter("conditionValue");
			String productOnSale=request.getParameter("productOnSale");
			//output.println("productSales"+productOnSale);	
			String userGender=request.getParameter("userGender");
			String manufacturerRebate=request.getParameter("manufacturerRebate");
			String dataValueDropdown=request.getParameter("dataValue");
			String sortValue=request.getParameter("sortValue");
			String returnValueDropdown = request.getParameter("returnValue");
			String groupByDropdown = request.getParameter("groupByDropdown");
			String retailerName=request.getParameter("retailerName");
			boolean noFilter = false;
			boolean filterByProduct = false;
			boolean filterByPrice = false;
			boolean filterByZip = false;
			boolean filterByUserName=false;
			boolean filterByProductCategory=false;
			boolean filterByCity = false;
			boolean filterByState = false;
			boolean filterByRating = false;
			boolean filterByRetailerName = false;
			boolean filterByconsoleManufacturer=false;
			boolean filterByUserOccupation=false;
			
			boolean groupBy = false;
			boolean groupByCity = false;
			boolean groupByProduct = false;
			boolean groupByRetailerName=false;
			boolean countOnly = false;
			boolean sortData=false;
			boolean likedProduct = false;
			boolean dislikedProduct = false;
				
			String[] extraSettings = request.getParameterValues("extraSettings");
			
			DBCursor dbCursor = null;
			AggregationOutput aggregateData = null;
			
			int returnLimit=0;
				if (returnValueDropdown.equals("TOP_2"))
				{
				returnLimit=2;
				}	
				else if (returnValueDropdown.equals("TOP_3"))
				{
				returnLimit=3;
				}
				else if (returnValueDropdown.equals("TOP_5"))
				{
					returnLimit = 5;
					}
				else if (returnValueDropdown.equals("TOP_10")){
					returnLimit=10;	
				}	

			constructPageTop(output,request);
				
			//Check for extra settings(Grouping Settings)
			if(extraSettings != null){				
				//User has selected extra settings
				
				for(int x = 0; x <extraSettings.length; x++){
					switch (extraSettings[x]){						
						case "COUNT_ONLY":
							//Not implemented functionality to return count only
							countOnly = true;				
							break;		
						case "liked_ONLY" :
							likedProduct=true;
							break;
						case "disliked_ONLY" :
							dislikedProduct=true;
							break;
						case	"SORT_BY":
							sortData=true;
							break;	
						case   "GROUP_BY":
							groupBy = true;
							break;
						}
						
						}				
				}
			//Construct the top of the page
			if(filters != null && groupBy != true)
				{
				for (int i = 0; i < filters.length; i++)
				 {
					//Check what all filters are ON
					//Build the query accordingly
					switch (filters[i]){										
						case "productName":							
							filterByProduct = true;
							if(!productName.equals("ALL_PRODUCTS")){
								query.put("productName", productName);
							}						
							break;
						
							case "productCategory":							
							filterByProductCategory = true;
							if(!productCategory.equals("ALL_PRODUCTS")){
								query.put("productCategory", productCategory);
							}						
							break;
									
						case "consoleManufacturer":							
							filterByconsoleManufacturer = true;
							if(!consoleManufacturer.equals("ALL_ManufacturerName")){
								query.put("consoleManufacturer", consoleManufacturer);
							}						
							break;
									
						case "userName" :
							filterByUserName = true;
							if(!userName.equals("ALL_UserName")){
								query.put("userName", userName);
							}						
							break;
							
						case "productPrice":
							filterByPrice = true;
							if (comparePrice.equals("EQUALS_TO")) {
								query.put("productPrice", productPrice);
							}else if(comparePrice.equals("GREATER_THAN")){
								query.put("productPrice", new BasicDBObject("$gt", productPrice));
							}else if(comparePrice.equals("LESS_THAN")){
								query.put("productPrice", new BasicDBObject("$lt", productPrice));
							}
							break;
						
						case "manufacturerRebate" :
							query.put("manufacturerRebate",manufacturerRebate);
							break;
						case "userGender" :
							query.put("userGender",userGender);
							break;
						case "productOnSale" :
							query.put("productOnSale",productOnSale);
							break;
						
						case "userAge":
							filterByPrice = true;
							if (compareAge.equals("EQUALS_TO")) {
								query.put("userAge", userAge);
							}else if(compareAge.equals("GREATER_THAN")){
								query.put("userAge", new BasicDBObject("$gt",userAge));
							}else if(compareAge.equals("LESS_THAN")){
								query.put("userAge", new BasicDBObject("$lt",userAge));
							}
							break;
											
						/*case "reviewDate":
					//SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");

							filterByPrice = true;
							if (compareDate.equals("EQUALS_TO")) {
								query.put("reviewDate", reviewDate);
							}else if(compareDate.equals("GREATER_THAN")){
								query.put("reviewDate", new BasicDBObject("$gt",reviewDate));
							}else if(compareDate.equals("LESS_THAN")){
								query.put("reviewDate", new BasicDBObject("$lt",reviewDate));
							}
							break;
						*/						
						case "retailerZipcode":
							filterByZip = true;
							query.put("retailerZipcode", retailerZipcode);
							break;
												
						case "retailerCity": 
							filterByCity = true;
							if(!retailerCity.equals("All")){
								query.put("retailerCity", retailerCity);
							}							
							break;
						case "retailerState": 
							filterByState = true;
							if(!retailerState.equals("All")){
								query.put("retailerState", retailerState);
							}							
							break;
						
						case "retailerName": 
							filterByRetailerName = true;
							if(!retailerName.equals("All")){
								query.put("retailerName", retailerName);
							}							
							break;
						case "userOccupation": 
							filterByUserOccupation = true;
						
							if(!userOccupation.equals("ALL_UserOccupation")){
								query.put("userOccupation", userOccupation);
							}							
							break;
											
						case "reviewRating":	
							filterByRating = true;
							if (compareRating.equals("EQUALS_TO")) {
								query.put("reviewRating", reviewRating);
							}else if(compareRating.equals("GREATER_THAN")){
								query.put("reviewRating", new BasicDBObject("$gt", reviewRating));
							}
							else if(compareRating.equals("LESS_THAN")){
								query.put("reviewRating", new BasicDBObject("$lt", reviewRating));
							}
							break;
													
						default:
							//Show all the reviews if nothing is selected
							noFilter = true;
							break;						
					}				
				}
				}		
			//Run the query 
			if(groupBy){		
				//Run the query using aggregate function
				DBObject matchFields=new BasicDBObject();
				DBObject match = new BasicDBObject();
				DBObject limit=new BasicDBObject();
				DBObject orderby=new BasicDBObject();
			DBObject sort1=new BasicDBObject();			
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				AggregationOutput aggregate = null;
				AggregationOutput aggregate1 = null;
					
				groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$"+request.getParameter("groupByDropdown"));				
					
					if(conditionValueDropdown.equals("count")||conditionValueDropdown.equals("none"))
					{
					conditionValueDropdown="count";
					groupFields.put("count",new BasicDBObject("$sum",1));
					
					}			
					else 
					{
					groupFields.put(conditionValueDropdown, new BasicDBObject("$"+conditionValueDropdown,"$"+request.getParameter("dataValue")));
					}
				
					if(filters != null){
					for (int i = 0; i < filters.length; i++) {
					switch (filters[i]){								
					case "productName": 
						if(!productName.equals("ALL_PRODUCTS"))
						{
						matchFields.put("productName", new BasicDBObject("$eq",productName));
						}							
						break;

					case "productCategory":							
							if(!productCategory.equals("ALL_PRODUCTS")){
							matchFields.put("productCategory", new BasicDBObject("$eq",productCategory));
							}						
							break;

					case "consoleManufacturer":							
							if(!consoleManufacturer.equals("ALL_ManufacturerName")){
							matchFields.put("consoleManufacturer", new BasicDBObject("$eq",consoleManufacturer));
							}						
							break;
									
						case "userName" :
							filterByUserName = true;
							if(!userName.equals("ALL_UserName")){
							matchFields.put("userName", new BasicDBObject("$eq",userName));
							}						
							break;
						
						
							
					case "productPrice":
					if (comparePrice.equals("EQUALS_TO")) {
					matchFields.put("productPrice",new BasicDBObject("$eq",productPrice));
					}
					else if(comparePrice.equals("GREATER_THAN")){
					matchFields.put("productPrice",new BasicDBObject("$gt",productPrice));					
					}
					else if(comparePrice.equals("LESS_THAN")){
					matchFields.put("productPrice",new BasicDBObject("$lt",productPrice));
							}
						break;

						case "userAge":
							if (compareAge.equals("EQUALS_TO")) {
							matchFields.put("userAge",new BasicDBObject("$eq",userAge));					
							}else if(compareAge.equals("GREATER_THAN")){
							matchFields.put("userAge",new BasicDBObject("$gt",userAge));					
							}else if(compareAge.equals("LESS_THAN")){
							matchFields.put("userAge",new BasicDBObject("$lt",userAge));
							}
							break;
											
						/*case "reviewDate":
					//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

							if (compareDate.equals("EQUALS_TO")) {
							matchFields.put("reviewDate",new BasicDBObject("$eq",reviewDate.getTime()));					
							}else if(compareDate.equals("GREATER_THAN")){
							matchFields.put("reviewDate",new BasicDBObject("$gt",reviewDate.getTime()));					
							}else if(compareDate.equals("LESS_THAN")){
							matchFields.put("reviewDate",new BasicDBObject("$lt",reviewDate.getTime()));					
							}
							break;*/

							case "retailerZipcode":
							matchFields.put("retailerZipcode",new BasicDBObject("$eq",retailerZipcode));
							break;
												
						case "retailerCity": 
							
							if(!retailerCity.equals("All")){
							matchFields.put("retailerCity",new BasicDBObject("$eq",retailerCity));
							}							
							break;
						
						case "retailerName": 
							if(!retailerName.equals("All")){
							matchFields.put("retailerName",new BasicDBObject("$eq", retailerName));
							}							
							break;
						case "userOccupation": 
							if(!userOccupation.equals("ALL_UserOccupation")){
							matchFields.put("userOccupation",new BasicDBObject("$eq", userOccupation));
							}							
							break;

						case "retailerState": 
							if(!retailerState.equals("All")){
							matchFields.put("retailerState",new BasicDBObject("$eq", retailerState));
							}							
							break;
										
						case "manufacturerRebate" :
							matchFields.put("manufacturerRebate",new BasicDBObject("$eq",manufacturerRebate));
							break;
						case "userGender" :
							matchFields.put("userGender",new BasicDBObject("$eq", userGender));
							break;
						case "productOnSale" :
							matchFields.put("productOnSale",new BasicDBObject("$eq",productOnSale));
							break;				
						
					case "reviewRating":	
							
							if (compareRating.equals("EQUALS_TO")) {
							matchFields.put("reviewRating",new BasicDBObject("$eq", reviewRating));
							}else if(compareRating.equals("GREATER_THAN"))
								{
								matchFields.put("reviewRating", new BasicDBObject("$gt", reviewRating));
							}
							else if(compareRating.equals("LESS_THAN"))
								{matchFields.put("reviewRating", new BasicDBObject("$lt", reviewRating));
								}			
							break;
							  }				
							}
						}
													
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating",new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("productPrice", new BasicDBObject("$push", "$productPrice"));

																		
				
					groupFields.put("retailerCity", new BasicDBObject("$push", "$retailerCity"));
					groupFields.put("retailerName",new BasicDBObject("$push", "$retailerName"));
					groupFields.put("retailerZipcode",new BasicDBObject("$push", "$retailerZipcode"));
 					groupFields.put("retailerState",new BasicDBObject("$push", "$retailerState"));
 					
					groupFields.put("userName", new BasicDBObject("$push", "$userName"));
					groupFields.put("userOccupation", new BasicDBObject("$push", "$userOccupation"));
					groupFields.put("userAge",new BasicDBObject("$push", "$userAge"));
 					groupFields.put("consoleManufacturer",new BasicDBObject("$push", "$consoleManufacturer"));
 					groupFields.put("reviewDate",new BasicDBObject("$push", "$reviewDate"));
  					groupFields.put("reviewText",new BasicDBObject("$push", "$reviewText"));
  					match=new BasicDBObject("$match",matchFields);
					group = new BasicDBObject("$group", groupFields);
					
					
					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("value", "$_id");
					projectFields.put("ReviewValue", "$"+conditionValueDropdown);
					projectFields.put("Product", "$productName");
					projectFields.put("userName", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");

					projectFields.put("productPrice", "$productPrice");
					projectFields.put("retailerCity", "$retailerCity");
					projectFields.put("retailerName","$retailerName");
					projectFields.put("retailerZipcode","$retailerZipcode");
 					projectFields.put("retailerState","$retailerState");
 					
					projectFields.put("userOccupation", "$userOccupation");
					projectFields.put("userAge","$userAge");
 					projectFields.put("consoleManufacturer","$consoleManufacturer");
 					projectFields.put("reviewDate","$reviewDate");
  					projectFields.put("reviewText","$reviewText");
  					project = new BasicDBObject("$project", projectFields);
					//output.println("Trending value "+request.getParameter("TrendingButton"));
					String buttonvalue=request.getParameter("TrendingButton");
					sort1.put("ReviewValue",-1);
					if(!sortValue.equals(null)||!(sortValue.equals("ALL")) && sortData)
					{
					sort1.put(sortValue,-1);
					} 
					orderby=new BasicDBObject("$sort",sort1);
					if(buttonvalue.equals("FindData")&& !(likedProduct) &&!(dislikedProduct))
					{
					if(returnLimit!=0)
					{limit=new BasicDBObject("$limit",returnLimit);	
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby,limit);
					}
					else
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby);
					constructGroupByContent(aggregate, output, countOnly,request);	
					}
					
					if(buttonvalue.equals("TrendingButton")||likedProduct||dislikedProduct)
					{	
								
					Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
					dbObjIdMap.put(request.getParameter("groupByDropdown"), "$"+request.getParameter("groupByDropdown"));
					dbObjIdMap.put("productName","$productName");
					List cursorProduct1;
					cursorProduct1 = myReviews.distinct(request.getParameter("groupByDropdown"));				
					
					for(int i=0;i<cursorProduct1.size();i++)
					{
					//output.println("prodis"+cursorProduct1.get(i));
					//groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productName");				
					groupFields.put("count",new BasicDBObject("$sum",1));
					if(dislikedProduct)
					{//output.println("inside dislike");
					matchFields.put("reviewRating",new BasicDBObject("$eq",1));
					}
					else
					{matchFields.put("reviewRating",new BasicDBObject("$eq",5));
					}
					matchFields.put(request.getParameter("groupByDropdown"),new BasicDBObject("$eq",cursorProduct1.get(i)));
					//projectFields = new BasicDBObject("_id", 0);
					projectFields.put("value","$_id");
					projectFields.put(request.getParameter("groupByDropdown"),"$"+request.getParameter("groupByDropdown"));
					if(conditionValueDropdown.equals("max"))
					{//output.println("condition value"+request.getParameter("dataValue"));
					sort1.put(request.getParameter("dataValue"),-1);
					}
					projectFields.put("ReviewValue","$count");
					projectFields.put("ReviewValue1","$max");
					
					group = new BasicDBObject("$group", groupFields);
					match=new BasicDBObject("$match",matchFields);
					orderby=new BasicDBObject("$sort",sort1);
					project = new BasicDBObject("$project", projectFields);
					
					output.println("<h2>Grouped On"+cursorProduct1.get(i)+"</h2>");
										
					if(buttonvalue.equals("TrendingButton"))
					{returnLimit=1;
					}
					if (!returnValueDropdown.equals("ALL"))
					{
					limit=new BasicDBObject("$limit",returnLimit);
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby,limit);	
					constructGroupByContent(aggregate, output, countOnly,request);			
					}
					else if(returnValueDropdown.equals("ALL")&&!(buttonvalue.equals("TrendingButton")))
					{
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby);
					constructGroupByContent(aggregate, output, countOnly,request);			
					}
					
				
					//output.println(" count is "+request.getAttribute("countData"));	
					int countData=Integer.parseInt(request.getAttribute("countData").toString());
					//output.println(" count Data is "+countData);
					groupFields.put("_id", "$productName");				
					groupFields.put("count",new BasicDBObject("$sum",1));
					if(dislikedProduct)
					{//output.println("inside dislike");
					matchFields.put("reviewRating",new BasicDBObject("$eq",2));
					}
					else
					{matchFields.put("reviewRating",new BasicDBObject("$eq",4));
					}
					matchFields.put(request.getParameter("groupByDropdown"),new BasicDBObject("$eq",cursorProduct1.get(i)));
					//projectFields = new BasicDBObject("_id", 0);
					projectFields.put("value","$_id");
					projectFields.put(request.getParameter("groupByDropdown"),"$"+request.getParameter("groupByDropdown"));
					group = new BasicDBObject("$group", groupFields);
					if(conditionValueDropdown.equals("max"))
					{//output.println("condition value"+request.getParameter("dataValue"));
					sort1.put(request.getParameter("dataValue"),-1);
					}
					projectFields.put("ReviewValue","$count");
					projectFields.put("ReviewValue1","$"+conditionValueDropdown);
					
					orderby=new BasicDBObject("$sort",sort1);
					
					match=new BasicDBObject("$match",matchFields);
					project = new BasicDBObject("$project", projectFields);
					if(buttonvalue.equals("TrendingButton"))
					{returnLimit=1;
					}
					
					if(countData<returnLimit)
					{
					limit=new BasicDBObject("$limit",returnLimit-countData);
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby,limit);
					constructGroupByContent(aggregate, output, countOnly,request);			
					}
					else if (returnValueDropdown.equals("ALL")&&!(buttonvalue.equals("TrendingButton")))
					{
					aggregate = myReviews.aggregate(match,orderby,group,project,orderby);
					constructGroupByContent(aggregate, output, countOnly,request);			
					}
					
					}	
					}
												
				}

				//Check the main filters only if the 'groupBy' option is not selected
				else
				{					
			
				//Check the return value selected
				//int returnLimit = 0;
				
				//Create sort variable
				DBObject sort = new BasicDBObject();
				if(!sortValue.equals(null)||!(sortValue.equals("ALL")) && sortData)
					{
					sort.put(sortValue,-1);
					}						
				if (returnValueDropdown.equals("TOP_5")){
					//Top 5 - Sorted by review rating
					returnLimit = 5;
					sort.put("reviewRating",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("TOP_10")){
					//Top 10 - Sorted by review rating
					returnLimit = 10;
					sort.put("reviewRating",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_5")){
					//Latest 5 - Sort by date
					returnLimit = 5;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_10")){
					//Latest 10 - Sort by date
					returnLimit = 10;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}
					
				else {	
					//Run the simple search query(default result)
					dbCursor = myReviews.find(query).sort(sort);
				}		
				
				//Construct the page content
				constructDefaultContent(dbCursor, output, countOnly);
			}			
			
			//Construct the bottom of the page
			//constructPageBottom(output);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void constructPageTop(PrintWriter output,HttpServletRequest request){
		String pageHeading = "Query Result";
		String myPageTop = "<!DOCTYPE html>" + "<html lang=\"en\">"
					+ "<head>	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
					+ "<title>Game Speed</title>"
					+ "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"
					+ "</head>"
					+ "<body>"
					+ "<div id=\"container\">"
					+ "<header>"
					+ "<h1><a href=\"/\">GameSpeed<span></span></a></h1><h2>Tutorial 3</h2>"
					+ "</header>"
					+ "<body><div id='container'>"
					+ "<nav><ul><li class='start selected'><a href='/HW1_MahadevanSushma/Index'>Home</a></li>"
					+ "<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=Xbox'>XBox</a></li>"
 					+ "<li class=''><a href='/HW1_MahadevanSushma/Content?producthref=PS'>PS</a></li>"
   					+ "<li class='end'><a href='#'>Accessories</a></li>"
					+ "<li><a href='/HW1_MahadevanSushma/ViewCart'>"
					+ request.getSession().getAttribute("Username")
					+ request.getSession().getAttribute("CartOrder")
 					+ "<li class=''><a href='ViewPayment'>MyOrder</a></li>"
					+ "<li class=''><a href='/HW1_MahadevanSushma/login.html'>logout</a></li>"
					+ "</a></li></ul></nav>";

						
					/*+ "<nav>"
					+ "<ul>"
					+ "<li class=\"\"><a href=\"Index.html\">Home</a></li>"
					+ "<li class = \"\"><a href=\"WriteReview.html\">Write Review</a></li>"
					+ "<li class = \"start selected\"><a href=\"DataAnalytics.html\">Data Analytics</a></li>"
					+ "</ul>"
					+ "</nav>"
					+ "<div id=\"body\">"
					+ "<section id=\"review-content\">"
					+ "<article>"
					+ "<h2 style=\"color:#DE2D3A;font-weight:700;\">" +pageHeading + "</h2>";*/
		
		output.println(myPageTop);		
	}
	
	/*public void constructPageBottom(PrintWriter output){
		String myPageBottom = "</article>"
					+ "</section>"
                    + "<div class=\"clear\"></div>"
					+ "</div>"
					+ "<footer>"
					+ "<div class=\"footer-content\">"
					+ "<ul>"
                    + "<li>"
                    + "<h4>Dummy Link Section 1</h4>"
                    + "</li>"
                    + "<li><a href=\"#\">Dummy Link 1</a>"
                    + "</li>"
                    + "<li><a href=\"#\">Dummy Link 2</a>"
                    + "</li>"
                    + "<li><a href=\"#\">Dummy Link  3</a>"
                    + "</li>"
					+ "</ul>"
					+ "<div class=\"clear\"></div>"
					+ "</div>"
					+ "<div class=\"footer-bottom\">"
					+ "<p>CSP 595 - Enterprise Web Application - Assignment#3</p>"
					+ "</div>"
					+ "</footer>"
					+ "</div>"
					+ "</body>"
					+ "</html>";
		
		output.println(myPageBottom);
	}
	*/
	public void constructDefaultContent(DBCursor dbCursor, PrintWriter output, boolean countOnly){
		int count = 0;
		String tableData = " ";
		String pageContent = " ";

		while (dbCursor.hasNext()) {		
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			tableData =  "<tr><td>Name: <b>     " + bobj.getString("productName") + " </b></td></tr>"
						+ "<tr><td>Price:       " + bobj.getInt("productPrice") + "</br>"
						+ "Retailer:            " + bobj.getString("retailerName") + "</br>"
						+ "Retailer Zipcode:    " + bobj.getString("retailerZipcode") + "</br>"
						+ "Retailer City:       " + bobj.getString("retailerCity") + "</br>"
						+ "Retailer State:      " + bobj.getString("retailerState") + "</br>"
						+ "Sale:                " + bobj.getString("productOnSale") + "</br>"
						+ "User ID:             " + bobj.getString("userID") + "</br>"
						+ "User Age:            " + bobj.getString("userAge") + "</br>"
						+ "User Gender:         " + bobj.getString("userGender") + "</br>"
						+ "User Occupation:     " + bobj.getString("userOccupation") + "</br>"
						+ "Manufacturer:        " + bobj.getString("consoleManufacturer") + "</br>"
						+ "Manufacturer Rebate: " + bobj.getString("manufacturerRebate") + "</br>"
						+ "Rating:              " + bobj.getString("reviewRating") + "</br>"
						+ "Date:                " + bobj.getString("reviewDate") + "</br>"
						+ "Review Text:         " + bobj.getString("reviewText") + "</td></tr>";
				
			count++;
				
				output.println("<h3>"+count+"</h3>");
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
		}

		//No data found
		if(count == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	
	public void constructGroupByContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly,HttpServletRequest request){
		int rowCount = 0;
		int CountData = 0;
		String tableData = " ";
		String pageContent = " ";
		
			output.println("<h4> Grouped By - "+request.getParameter("groupByDropdown")+"</h4>");
			//output.println(request.getParameter("groupByDropdown"));			
			

		for (DBObject result : aggregate.results()) {
				
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList)bobj.get("productPrice"); 
				BasicDBList retailerCity = (BasicDBList)bobj.get("retailerCity");
									
						BasicDBList retailerName = (BasicDBList)bobj.get("retailerName");
						BasicDBList retailerZipcode = (BasicDBList)bobj.get("retailerZipcode") ;
						BasicDBList retailerState = (BasicDBList)bobj.get("retailerState");
						//BasicDBList productOnSale = (BasicDBList)bobj.get("productOnSale");
						BasicDBList userName = (BasicDBList)bobj.get("userName");
						BasicDBList userAge = (BasicDBList)bobj.get("userAge");
					//BasicDBList userGender = (BasicDBList)bobj.get("userGender");
						BasicDBList userOccupation = (BasicDBList)bobj.get("userOccupation");
					BasicDBList consoleManufacturer = (BasicDBList)bobj.get("consoleManufacturer");
						//BasicDBList manufacturerRebate = (BasicDBList)bobj.get("manufacturerRebate");
						BasicDBList reviewDate = (BasicDBList)bobj.get("reviewDate");
						BasicDBList reviewText = (BasicDBList)bobj.get("reviewText");
									
				rowCount++;
				tableData = "<tr><td> "+bobj.getString("value")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("ReviewValue")+"</td>"
						+"<td>Reviews Found: "+bobj.getString("ReviewValue1")+"</td></tr>";
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				//String conditionValueDropdown=request.getParameter("conditionValue");
				//String dataValueDropdown=request.getParameter("dataValue");
			request.setAttribute("countData",rowCount);	
			if(!countOnly)
			{	
				//Now print the products with the given review rating
				while (CountData < productReview.size()) {

				//output.println("countOnly is"+countOnly);	
				/*if(!(conditionValueDropdown.equals("count")&&!(conditionValueDropdown.equals("none"))))
				{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(0)+"</br>"
							+   "Rating: "+rating.get(CountData)+"</br>"
							+	"Review: "+productReview.get(CountData)+ "</br>"
							+"ProductPrice: "+productPrice.get(CountData) + "</br>"
						+ "Retailer City:       " + retailerCity.get(CountData) + "</br>"
						+ "Retailer:            " + retailerName.get(CountData) + "</br>"
						+ "Retailer Zipcode:    " + retailerZipcode.get(CountData) + "</br>"
						+ "Retailer State:      " + retailerState.get(CountData) + "</br>"
						+ "User ID:             " + userName.get(CountData) + "</br>"
						
						//+ "Sale:    " + bobj.getString("productOnSale") + "</br>"
						+ "User Age:            " +userAge.get(CountData) + "</br>"
						//+ "User Gender:         " + productReview.get(CountData) + "</br>"
						+ "User Occupation:     " + userOccupation.get(CountData) + "</br>"
						+ "Manufacturer:        " + consoleManufacturer.get(CountData) + "</br>"
						//+ "Manufacturer Rebate: " + productReview.get(CountData) + "</br>"
						//+ "Date:                " + reviewDate.get(CountData) + "</br>"
						+ "Review Text:         " + reviewText.get(CountData) + "</td></tr>";
									
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					CountData++;				
					break;					
				
					}
					else
					{*/
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(CountData)+"</br>"
							+   "Rating: "+rating.get(CountData)+"</br>"
							+	"Review: "+productReview.get(CountData)+ "</br>"
							+"ProductPrice: "+productPrice.get(CountData) + "</br>"
						+ "Retailer City:       " + retailerCity.get(CountData) + "</br>"
						+ "Retailer:            " + retailerName.get(CountData) + "</br>"
						+ "Retailer Zipcode:    " + retailerZipcode.get(CountData) + "</br>"
						+ "Retailer State:      " + retailerState.get(CountData) + "</br>"
						+ "User ID:             " + userName.get(CountData) + "</br>"
						
						//+ "Sale:    " + bobj.getString("productOnSale") + "</br>"
						+ "User Age:            " +userAge.get(CountData) + "</br>"
						//+ "User Gender:         " + productReview.get(CountData) + "</br>"
						+ "User Occupation:     " + userOccupation.get(CountData) + "</br>"
						+ "Manufacturer:        " + consoleManufacturer.get(CountData) + "</br>"
						//+ "Manufacturer Rebate: " + productReview.get(CountData) + "</br>"
						//+ "Date:                " + reviewDate.get(CountData) + "</br>"
						+ "Review Text:         " + reviewText.get(CountData) + "</td></tr>";
									
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					CountData++;				
				//}	
					
				//Reset product count	
			}
			CountData =0;
		   }		
		}
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
			request.setAttribute("countData",rowCount);	
			
		}
		
	}
	

}