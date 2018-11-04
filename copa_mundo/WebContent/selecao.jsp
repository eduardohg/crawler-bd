<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<title>Seleção</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>
<br>
<br>
<br>
<div class="container">
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/insertSelecao.jsp">
                    Inserir nova Seleção
                </a>
            </div>

            <form class="form_excluir_usuarios" action="${pageContext.servletContext.contextPath}/selecao/delete" method="POST">

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-5 h4">Seleção</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="selecao" items="${requestScope.selecaoList}">
                            <tr>
                                <td>
                                    <a class="link_visualizar_usuario" href="${pageContext.servletContext.contextPath}/selecao/read?id=${selecao.nome}" data-href="">
                                        <span class="h4"><c:out value="${selecao.nome}"/></span>
                                    </a>
                                </td>
                                <td class="text-center">
                                    <a class="btn btn-default"
                                       href="${pageContext.servletContext.contextPath}/selecao/update?id=${selecao.nome}"
                                       data-toggle="tooltip"
                                       data-original-title="Editar">
                                        <i class="glyphicon glyphicon-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_usuario"
                                       href="${pageContext.servletContext.contextPath}/selecao/delete?id=${selecao.nome}"
                                       data-href=""
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="glyphicon glyphicon-trash"></i>
                                    </a> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
</body>
</html>