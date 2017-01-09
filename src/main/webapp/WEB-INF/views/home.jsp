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
<a href="aluno">Aluno</a>
<a href="professor">Professor</a>
<a href="turma">Turma</a>
<a href="deslogado">Sair</a>

</body>
</html>
