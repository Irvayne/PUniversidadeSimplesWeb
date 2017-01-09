<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login.</title>
    </head>
    <body>
        <h4>User Login.</h4>
			<form action="login" method="POST">
				e-mail:<input type="text" name="email"/>
				<br>
				senha:<input type="password" name="senha"/>
				<br>
				<input type="submit" value="Logar">	
			</form>	 
    </body>
</html>