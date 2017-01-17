<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Bem Vindo ao controle acadêmico
</h1>

<P>  Hora do servidor ${serverTime}. </P>
<p> Usuario: ${emailUsuario} </p>
<br>
			<form action="aluno" method="GET">
				<input type="submit" value="Aluno"> 
			</form>
			<form action="professor" method="GET">
				<input type="submit" value="Professor"> 
			</form>
			<form action="turma" method="GET">
				<input type="submit" value="Turma"> 
			</form>
			<form action="deslogado" method="GET">
				<input type="submit" value="Sair"> 
			</form>
</body>
</html>
