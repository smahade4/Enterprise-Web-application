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
	
	                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.txt"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	      
 HashMap products = (HashMap)objectInputStream.readObject();


				
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css'>");
			out.println("<body><div id='container'><header>");
out.println("<nav><ul><li class='start selected'><a href='index.html'>Home</a></li>");
out.println("<li class=''><a href='viewproducts.jsp'>View Products</a></li>");
out.println("<li class=''><a href='AddProducts.jsp' Name='AddProducts'>AddProducts</a></li>");
out.println("<li class=''><a href='SelectProductsUpdate.jsp' Name='UpdateProducts'>UpdateProducts</a></li>");
out.println("<li class='end'>"+request.getSession().getAttribute("Username"));
out.println("<li><a href='/HW3_MahadevanSushma/login.html'>Logout</a>");
 out.println("</a></li></ul></nav>");

/*   Set set = products.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
out.println("insideout");         
Map.Entry mentry = (Map.Entry)iterator.next();
       out.print("key: "+ mentry.getKey() + " & Value: ");
        out.println(mentry.getValue());
      }*/
int i;
for(i=1;i<=products.size();i++)
{//out.println("insidehash");
//out.println(products.get(i));
ProductDetails p=(ProductDetails)products.get(i);  
out.println("<br>"+"ProductId"+ p.getProductId()+"<br>");
out.println("ProductName"+ p.getProductName()+"<br>");       
out.println("ProductPrice"+ p.getPrice()+"<br>");       

/*<jsp:useBean id="ProductDetails" type="java.util.HashMap"  class="assign3.ProductDetails">
<jsp:getProperty name="ProductDetails"  property="ProductName"/>
</jsp:useBean>
*/
}
}	 catch (Exception e) {
			e.printStackTrace();
		}
		
%>

