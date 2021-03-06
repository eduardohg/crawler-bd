<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/include/head.jsp"%>
<title>Estatisticas</title>
</head>
<body>
<%@include file="/include/navbarUser.jsp"%>
<br><br><br>
<table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-5 h4">Seleção</th>
                            <th class="col-lg-4 h4">Participacoes</th>
                            <th class="col-lg-4 h4">% de Participações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="historico" items="${requestScope.historicoList}">
                            <tr id="tabela">
                            	<td>
                                    <a class="link_visualizar_usuario" href="${pageContext.servletContext.contextPath}/historico/read?id=${historico.nome_selecao}" data-href="">
                                        <h4><c:out value="${historico.nome_selecao}"/></h4>
                                    </a>
                                </td>
                                <td>
                                	<span class="h4"><c:out value="${historico.participacoes}"/></span>
                                </td>
                                <td>
                                	<span class="h4"><c:out value="${historico.porc_part}"/></span>
                                </td>                                            
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
               <canvas class="line-chart"></canvas>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>

    <script>
        var ctx = document.getElementsByClassName("line-chart");    
		var valores = document.getElementsByTagName("span")[0];
        // Type, Data & Options

        function generateGraph(ctx, labels, data, colors){
            return new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: "",
                    data: data,
                   
                    backgroundColor: colors
                }]
            },
            options: {
                title: {
                    display: true,
                    fontSize: 20,
                    text: 'Percentual de participação de seleção em Copas'
                },
                labels: {
                    fontStyle: "bold"
                },
                scales: {
                	yAxes: [{ticks: {beginAtZero: true}}]
                }
            }
        });
        }

        var chartGraph = generateGraph(ctx, 
        <%String label = (String)request.getAttribute("label");out.print(label);%>, 
        <%String data = (String)request.getAttribute("data");out.print(data);%>,'yellow');
        
       
    </script>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
</body>
</html>