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

<%		try{
	MongoClient mongo = new MongoClient("localhost", 27017);
				
					
	DB db = mongo.getDB("CSP595Tutorial");
				
		DBCollection myOrders = db.getCollection("OrderDetails");
		response.setContentType("text/html");
		
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
		out.println("<body><div id='container'>");
out.println("<nav><ul><li class='start selected'><a href='/HW3_MahadevanSushma/Index.jsp'>Home</a></li>");
out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=Xbox'>XBox</a></li>");
 out.println("<li class=''><a href='/HW3_MahadevanSushma/Content.jsp?producthref=PS'>PS</a></li>");
   out.println("<li class='end'><a href='#'>Accessories</a></li>");
out.println("<li><a href='/HW3_MahadevanSushma/ViewCart.jsp'>");
out.println(request.getSession().getAttribute("Username"));
out.println(request.getSession().getAttribute("CartOrder"));
out.println("<li class=''><a href='/HW3_MahadevanSushma/ViewPayment.jsp'>MyOrders</a></li>");
 out.println("<li><a href='/HW3_MahadevanSushma/login.html'>logout</a>");
out.println("</a></li></ul></nav>");

  out.println("<div id='body'>");
          out.println("<section id='content'>");

                out.println("<article>");

BasicDBObject searchQuery = new BasicDBObject();
	String Payment[] = request.getParameterValues("PaymentId");
	String searchField="PaymentId";
	for( int i=0; i <Payment.length; i++){
	searchQuery.put(searchField,Integer.parseInt(Payment[i]));
	DBCursor cursor=myOrders.find(searchQuery);
	int ProductId=0;
	int Quantity=0;
	                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
 HashMap products = (HashMap)objectInputStream.readObject();
		String ProductNames="";	
	
	while(cursor.hasNext())
	{
	BasicDBObject obj = (BasicDBObject)cursor.next();
		Date date = new Date();
			Date ShippingDate=(Date)obj.get("ShippingDate");	
			int diffInDays = (int) ((ShippingDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	if((obj.getString("ShippingStatus").equals("Cancelled"))||diffInDays<=5)
	 	{out.println(request.getParameter("diffInDays")+obj.getString("ShippingStatus")); 
		out.println("Cannot cancel order");
		return;
		}
		ProductId=Integer.parseInt(obj.get("ProductId").toString());
	 ProductNames=ProductNames+obj.get("ProductName");
	 Quantity=Integer.parseInt(obj.get("Quantity").toString());
		Iterator it = products.entrySet().iterator();			
    		while (it.hasNext()) 
		{
		Map.Entry pi = (Map.Entry)it.next();
		if(pi!=null)
			{
			ProductDetails p=(ProductDetails)pi.getValue();
	if(p.getProductId()==ProductId)
			{
				p.setQuantityAvailable(p.getQuantityAvailable()+Quantity);
			}
			 }
		}
       FileOutputStream fileOutputStream2 = new FileOutputStream(new File("E:\\test12.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream2);
             objectOutputStream.writeObject(products);

		obj.put("ShippingStatus","Cancelled");
		myOrders.save(obj);	

		out.println("OrderCancelled for"+ProductNames);
					
			out.println("</article></section>");			



		out.println("</div></body>");

			out.println("</html>");
			 			
		
	

}
}


			
		} catch (MongoException e) {
			e.printStackTrace();
		}
%>

