<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="assign3.ProductDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Nama</title>
    </head>
    <body>
        <h1>Registered Name is : </h1>
        <%
            try{
                FileInputStream fileInputStream = new FileInputStream(new File("E:\\test12.ser"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ProductDetails s = new ProductDetails();
	              out.print("<h1>"+objectInputStream.readObject()+"</h1>");
           out.print("<h1>"+objectInputStream.readObject()+"</h1>");
                      
 }catch(Exception ex){               
                out.print(ex);
            }           
        %>
    </body>
</html>