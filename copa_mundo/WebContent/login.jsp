<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<link href="${pageContext.servletContext.contextPath}/css/insert.css" rel="stylesheet">
<title>Inserir Seleção</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>
	<br><br><br>
	<form action="${pageContext.servletContext.contextPath}/adm/authenticate" method="POST" class="formHistorico">
		<div class="form-group">
			<input type="text" placeholder="Login" name="login" class="form-control">
		</div>
		<div class="form-group">
			<input type="password" placeholder="Senha" name="senha" class="form-control">
		</div>
		<div class="form-group">
			<input type="password" placeholder="Confirme sua senha" name="senhaconfirm" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="Login">
		</div>
	</form>
	
	
</body>
</html>