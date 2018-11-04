<%-- 
    Document   : navbar
    Created on : 03/07/2018, 11:55:04
    Author     : Du
--%>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand"><a href="${pageContext.servletContext.contextPath}/">World Cup</a></span>
        </div>
        <div class="navbar-collapse collapse">
            
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/index.jsp">Home</a>
                    </li>
                    
                    <li>
                    	<a href="${pageContext.servletContext.contextPath}/selecao">Seleções</a>
                    </li>
                    
                    <li>
                    	<a href="${pageContext.servletContext.contextPath}/historico">Histórico</a>
                    </li>
                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown">
                            Relatórios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                        	<li><a href="${pageContext.servletContext.contextPath}/relatorio/aprov">Aproveitamento cada Seleção</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/relatorio/medCartoes">Média cartões por jogo</a></li>
                        	<li><a href="${pageContext.servletContext.contextPath}/relatorio/porcPart">Porcentagem Participação em Copas</a></li>                        
                       		<li><a href="${pageContext.servletContext.contextPath}/relatorio/titulos">Títulos</a></li>
                        </ul>
                    </li>  
                </ul>

            
        </div>
    </div>
</div>