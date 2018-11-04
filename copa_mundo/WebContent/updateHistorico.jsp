<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<title>Update Histórico</title>
</head>
<body>
<%@include file="/include/navbar.jsp"%>
<br>
<br>
<br>
<div class="container">
            <h2 class="text-center">Edição de um histórico: <c:out value="${historico.nome_selecao}"/></h2>

            <form class="form-group" action="${pageContext.servletContext.contextPath}/historico/update" method="POST">

                <input type="hidden" name="id" value="${historico.nome_selecao}">

                <div class="form-group">
                    <label class="h4">Titulos</label>
                    <input class="form-control" type="text" name="titulos" value="${historico.titulos}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Participações</label>
                    <input class="form-control" type="text" name="participacoes" value="${historico.participacoes}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Total Cartões</label>
                    <input class="form-control" type="text" name="total_cartoes" value="${historico.total_cartoes}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Cartões Amarelos</label>
                    <input class="form-control" type="text" name="cartoes_amarelos" value="${historico.cartoes_amarelos}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Cartões Vermelhos</label>
                    <input class="form-control" type="text" name="cartoes_vermelhos" value="${historico.cartoes_vermelhos}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Partidas</label>
                    <input class="form-control" type="text" name="partidas" value="${historico.partidas}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Pontuação</label>
                    <input class="form-control" type="text" name="pontuacao" value="${historico.pontuacao}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Vitórias</label>
                    <input class="form-control" type="text" name="vitorias" value="${historico.vitorias}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Derrotas</label>
                    <input class="form-control" type="text" name="derrotas" value="${historico.derrotas}" required autofocus/>
                </div>
                
                <div class="form-group">
                    <label class="h4">Empates</label>
                    <input class="form-control" type="text" name="empates" value="${historico.empates}" required autofocus/>
                </div>

                
                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Editar</button>
                </div>
            </form>

            
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