<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
	Turmas Cadastrados
</h1>
<p>  ${informacao} </p>

    <c:forEach items="${turmas}" var="turma">
       <c:out value="${turma.idTurma}"/>
       <c:out value="${turma.disciplina}"/> 
      <c:out value="${turma.departamento}"/> 
      <c:out value="${turma.cargaHoraria}"/> <br>
    </c:forEach>
   <br>
	<form action="turma" method="GET">
			<input type="submit" value="Voltar"> 
			</form>
</body>
</html>