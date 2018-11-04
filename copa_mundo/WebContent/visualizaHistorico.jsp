<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<title>Visualiza Histórico</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>
<br>
<br>
<br>
<div class="container">
            <h2 class="text-center">Histórico: <c:out value="${historico.nome_selecao}"/></h2>

            
                <div class="form-group">
                    <label class="h4">Titulos: <c:out value="${historico.titulos}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Participações: <c:out value="${historico.participacoes}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Total Cartões: <c:out value="${historico.total_cartoes}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Cartões Amarelos: <c:out value="${historico.cartoes_amarelos}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Cartões Vermelhos: <c:out value="${historico.cartoes_vermelhos}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Partidas: <c:out value="${historico.partidas}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Pontuação: <c:out value="${historico.pontuacao}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Vitórias: <c:out value="${historico.vitorias}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Derrotas: <c:out value="${historico.derrotas}"/></label>
                </div>
                
                <div class="form-group">
                    <label class="h4">Empates: <c:out value="${historico.empates}"/></label>
                </div>

        </div>
        
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
		<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
		<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
    </body>
</html>

</body>
</html>