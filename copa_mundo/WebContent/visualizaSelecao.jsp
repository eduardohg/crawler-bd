<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/head.jsp"%>
<title>Visualiza Seleção</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>
<br>
<br>
<br>
<div class="container">
            <h2 class="text-center">Seleção: <c:out value="${selecao.nome}"/></h2>

            
                <div class="form-group">
                    <label class="h4">Bandeira: <c:out value="${selecao.bandeira}"/></label>
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