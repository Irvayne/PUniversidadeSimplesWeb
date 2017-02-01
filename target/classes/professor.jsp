<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Tela Professor</h1>
<p> Usuario: ${emailUsuario} </p>
<p>  ${informacao} </p>
<br>

			<form action="inserirProfessor" method="GET">
				<input type="submit" value="Inserir"> 
			</form>
			<form action="buscarProfessor" method="GET">
				<input type="submit" value="Buscar"> 
			</form>
			<form action="listarProfessores" method="GET">
				<input type="submit" value="Listar"> 
			</form>
			<form action="alterarProfessor" method="GET">
				<input type="submit" value="Alterar"> 
			</form>
			<form action="removerProfessor" method="GET">
				<input type="submit" value="Remover"> 
			</form>
			<form action="quantidadeProfessores" method="GET">
				<input type="submit" value="Quantidade de Professores"> 
			</form>
			<form action="home" method="GET">
				<input type="submit" value="Voltar"> 
			</form>
			
			

</body>
</html>