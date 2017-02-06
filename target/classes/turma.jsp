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
<h1>Tela Turma</h1>
<p> Usuario: ${emailUsuario} </p>
<p>  ${informacao} </p>
<br>

			<form action="inserirTurma" method="GET">
				<input type="submit" value="Inserir"> 
			</form>
			<form action="buscarTurma" method="GET">
				<input type="submit" value="Buscar"> 
			</form>
			<form action="listarTurmas" method="GET">
				<input type="submit" value="Listar"> 
			</form>
			<form action="alterarTurma" method="GET">
				<input type="submit" value="Alterar"> 
			</form>
			<form action="removerTurma" method="GET">
				<input type="submit" value="Remover"> 
			</form>
			<form action="quantidadeTurmas" method="GET">
				<input type="submit" value="Quantidade de Turmas"> 
			</form>
			<form action="matricularAluno" method="GET">
				<input type="submit" value="Matricular Aluno"> 
			</form>
			<form action="listarAlunosTurma" method="GET">
				<input type="submit" value="Listar Alunos de uma Turma"> 
			</form>
			<form action="associarProfessorTurma" method="GET">
				<input type="submit" value="Associar Professor a Turma"> 
			</form>
			<form action="home" method="GET">
				<input type="submit" value="Voltar"> 
			</form>
			
			

</body>
</html>