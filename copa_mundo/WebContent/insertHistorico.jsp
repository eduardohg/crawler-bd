<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<link href="${pageContext.servletContext.contextPath}/css/insert.css" rel="stylesheet">
<title>Inserir Histórico</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>

	<form action="${pageContext.servletContext.contextPath}/historico/create" method="POST" class="formHistorico">
		<div class="form-group">
		
		<input type="text" placeholder="Nome seleção" name="selecao" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="titulos" name="titulos" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="participacoes" name="participacoes" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Numero de cartões" name="total_cartoes" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Cartões amarelos" name="cartoes_amarelos" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Cartões vermelhos" name="cartoes_vermelhos" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Numero de partidas" name="partidas" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Pontuação total" name="pontuacao" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Vitorias" name="vitorias" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Derrotas" name="derrotas" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="text" placeholder="Empates" name="empates" class="form-control">
		</div>
		<div class="form-group">
		<input type="submit" value="Cadastrar">
		</div>
	</form>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
</body>
</html>