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
        <h4>Inserir Aluno</h4>
			<form action="inserirAluno" method="POST">
				Nome:<input type="text" name="nome"/>
				<br>
				Matricula:<input type="text" name="matricula"/>
				<br>
				Curso:<input type="text" name="curso"/>
				<br>
				<input type="submit" value="Cadastrar">		
			</form>	
			<form action="aluno" method="GET">
			<input type="submit" value="Voltar"> 
			</form>
</body>
</html>