<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
	<form method="get" action="Transfer">
		<table>
			<tr>
				<td>Enter the credit amount you want</td>
				<td><input type="text" name="amount"></td>
			</tr>
			<tr>
				<td>Select the user to transfer</td>
				<td>
					<select name="users">
						<option value="Sai">Sai</option>
						<option value="Kiran">Kiran</option>
						<option value="Rakesh">Rakesh</option>
						<option value="Khadhar">Khadhar</option>
						<option value="Kartheek">Kartheek</option>
						<option value="Ravi">Ravi</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Transfer" name="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>