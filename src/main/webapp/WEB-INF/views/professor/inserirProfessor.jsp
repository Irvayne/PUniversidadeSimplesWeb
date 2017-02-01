<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>  ${informacao} </p>
        <h4>Inserir Professro</h4>
			<form action="inserirProfessor" method="POST">
				CPF:<input type="text" name="cpf"/>
				<br>
				Nome:<input type="text" name="nome"/>
				<br>
				Lotação:<input type="text" name="lotacao"/>
				<br>
				Titulo:<input type="text" name="titulo"/>
				<br>
				<input type="submit" value="Cadastrar">		
			</form>	
			<form action="professor" method="GET">
			<input type="submit" value="Voltar"> 
			</form>
</body>
</html>