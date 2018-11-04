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

	<form action="${pageContext.servletContext.contextPath}/selecao/create" method="POST" class="formHistorico">
		<div class="form-group">
			<input type="text" placeholder="Nome Seleção" name="nome" class="form-control">
		</div>
		<div class="form-group">
			<input type="text" placeholder="Bandeira" name="bandeira" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="Vai">
		</div>
	</form>
	
	<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
	
</body>
</html>