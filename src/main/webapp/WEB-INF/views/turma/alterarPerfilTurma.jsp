<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>Alterar Turma</h4>
			<form action="alterarPerfilTurma" method="POST">
				Id:<input type="text" name="identificador" value="${turma.idTurma}" readonly="readonly"/>
				<br>
				Disciplina:<input type="text" name="disciplina" value="${turma.disciplina}"/>
				<br>
				Departamento:<input type="text" name="departamento" value="${turma.departamento}"/>
				<br>
				Carga Horaria:<input type="text" name="horario" value="${turma.cargaHoraria}"/>
				<br>
				<input type="submit" value="Alterar">		
			</form>	
			<form action="turma" method="GET">
			<input type="submit" value="Voltar"> 
			</form>
</body>
</html>