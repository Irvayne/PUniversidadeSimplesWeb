<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>Cadastrar Usuario</h4>
			<form action="cadastrar" method="POST">
				nome:<input type="text" name="nome"/>
				<br>
				e-mail:<input type="text" name="email"/>
				<br>
				senha:<input type="password" name="senha"/>
				<br>
				<input type="submit" value="Cadastrar">		
			</form>	
			
			<form action="login" method="GET">
				<input type="submit" value="Voltar"> 
			</form>
</body>
</html>