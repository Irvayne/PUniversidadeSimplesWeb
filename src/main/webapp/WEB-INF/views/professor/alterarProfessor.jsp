<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>Alterar Professor</h4>
<p>  ${informacao} </p>
			<form action="alterarProfessor" method="POST">
				CPF:<input type="text" name="cpf"/>
				<br>
				
				<input type="submit" value="Buscar">		
			</form>	
			
			<form action="professor" method="GET">
				<input type="submit" value="Voltar"> 
			</form>
</body>
</html>