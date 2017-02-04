<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>Informacoes da Turma</h4>
${turma.idTurma}
${turma.disciplina}
${turma.departamento}
${turma.cargaHoraria}

			<form action="buscarTurma" method="GET">
				<input type="submit" value="Voltar"> 
			</form>
</body>
</html>