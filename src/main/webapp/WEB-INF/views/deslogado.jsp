<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuário não logado</title>
    </head>
    <body>
        <h4>Usuário deslogado</h4>
			<form action="login" method="POST">
				e-mail:<input type="text" name="email"/>
				senha:<input type="password" name="senha"/>
				<input type="submit" value="Logar">	
			</form>	
    </body>
</html>