<html>
<head>
  <title>Buy</title>
</head>
<body>
  <h1>Product Reviews</h1>
	
	<%!
		String productName = "";
		String imageLocation = " ";
		double productPrice = 0.0;		
	%>
	<%
		if (request.getParameter("XBox_Original") != null){
			productName = "X Box Original";
			imageLocation = "images/img_XBoxOriginal.jpg";
		}else if (request.getParameter("XBox_360") != null){
			productName = "X Box 360";
			imageLocation = "images/img_XBox360.jpg";
		}else if (request.getParameter("XBox_One") != null){
			productName = "X Box One";
			imageLocation = "images/img_XBoxOne.jpg";
		}else if (request.getParameter("PlayStation_2") != null){
			productName = "PlayStation 2";
			imageLocation = "images/img_PlayStation2.jpg";
		}else if (request.getParameter("PlayStation_3") != null){
			productName = "PlayStation 3";
			imageLocation = "images/img_PlayStation3.jpg";
		}else if (request.getParameter("PlayStation_4") != null){
			productName = "PlayStation 4";
			imageLocation = "images/img_PlayStation4.jpg";
		}
	%>
		
	<h3><%=productName%></h3>
		
	<form method="get" action="SubmitReview">
		
		<fieldset>
			<legend>Product information:</legend>
			<img src = <%=imageLocation%> width = "200" height = "200" alt = "X Box Orginal">
			<table>
				<tr>
					<td> Product Name: </td>
					<td> <%=productName%> </td>
				</tr>
				
				<tr>
					<td> Product Price: </td>
					<td> Some Price </td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>Reviews:</legend>
			<table>
				<tr>
					<td> Product Name: </td>
					<td> <input type="text" name="productName" value = '<%=productName%>'>  </td>
				</tr>
				
				<tr>
					<td> User Name: </td>
					<td> <input type="text" name="userName"> </td>
				</tr>
				
				<tr>
					<td> Review Rating: </td>
					<td>
						<select name="reviewRating">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>  
					</td>
				</tr>
				
				<tr>
					<td> Review Date: </td>
					<td> <input type="text" name="reviewDate"> </td>
				</tr>

				<tr>
					<td> Review Text: </td>
					<td><textarea name="reviewText" rows="4" cols="50"> </textarea></td>
				</tr>
				
			</table>
				
			<br><br>
			<input type="submit" value="Submit Review">
			
		</fieldset>
		
	</form>
		
	
</body>
</html>