package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.DAO;
import dao.DAOFactory;
import dao.RelatorioDAO;
import model.Historico;

@MultipartConfig()
@WebServlet(name = "RelatorioController", urlPatterns = {"/relatorio",
    "/relatorio/aprov",
    "/relatorio/medCartoes",
    "/relatorio/porcPart",
    "/relatorio/titulos"
})
public class RelatorioController extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Historico> dao;
        Historico historico;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
                
            case "/relatorio/aprov":
            	try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getRelatorioDAO();
                    List<Historico> historicoList = dao.aprov(); //Faz a consulta no DAO
                    ArrayList<String> selecao = new ArrayList();//Array de nomes de seleções
                    ArrayList<Double> aprov = new ArrayList();//Array com o valor do aproveitamento das seleções
                    
                    /*Preenchendo os vetores*/
                    for(int i=0;i<historicoList.size();i++) {
                    	selecao.add(historicoList.get(i).getNome_selecao());
                    	aprov.add(historicoList.get(i).getAproveitamento());
                    }
                    String label = null;
                    String data = null;
                    Gson gson = new GsonBuilder().create();
                    label = gson.toJson(selecao);
                    data = gson.toJson(aprov);
                    request.setAttribute("label", label);
                    request.setAttribute("data", data);
                    request.setAttribute("historicoList", historicoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/estatisticasAprov.jsp");
                dispatcher.forward(request, response);
               
            	break;
            	
            case "/relatorio/medCartoes":
            	try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getRelatorioDAO();
                    List<Historico> historicoList = dao.mediaCartoes(); //Faz a consulta no DAO
                    ArrayList<String> selecao = new ArrayList();//Array de nomes de seleções
                    ArrayList<Double> med = new ArrayList();//Array com o valor do aproveitamento das seleções
                    
                    /*Preenchendo os vetores*/
                    for(int i=0;i<historicoList.size();i++) {
                    	selecao.add(historicoList.get(i).getNome_selecao());
                    	med.add(historicoList.get(i).getMedia_cartoes());
                    }
                    String label = null;
                    String data = null;
                    Gson gson = new GsonBuilder().create();
                    label = gson.toJson(selecao);
                    data = gson.toJson(med);
                    request.setAttribute("label", label);
                    request.setAttribute("data", data);
                    request.setAttribute("historicoList", historicoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/mediaCartoes.jsp");
                dispatcher.forward(request, response);
            	break;
            	
            case "/relatorio/porcPart":
            	try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getRelatorioDAO();
                    List<Historico> historicoList = dao.porcPart(); //Faz a consulta no DAO
                    ArrayList<String> selecao = new ArrayList();//Array de nomes de seleções
                    ArrayList<Double> porc = new ArrayList();//Array com o valor do aproveitamento das seleções
                    
                    /*Preenchendo os vetores*/
                    for(int i=0;i<historicoList.size();i++) {
                    	selecao.add(historicoList.get(i).getNome_selecao());
                    	porc.add(historicoList.get(i).getPorc_part());
                    }
                    String label = null;
                    String data = null;
                    Gson gson = new GsonBuilder().create();
                    label = gson.toJson(selecao);
                    data = gson.toJson(porc);
                    request.setAttribute("label", label);
                    request.setAttribute("data", data);
                    request.setAttribute("historicoList", historicoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/porcPart.jsp");
                dispatcher.forward(request, response);
            	break;
            	
            case "/relatorio/titulos":
            	try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getRelatorioDAO();
                    List<Historico> historicoList = dao.titulos(); //Faz a consulta no DAO
                    ArrayList<String> selecao = new ArrayList();//Array de nomes de seleções
                    ArrayList<Integer> titulos = new ArrayList();//Array com o valor do aproveitamento das seleções
                    
                    /*Preenchendo os vetores*/
                    for(int i=0;i<historicoList.size();i++) {
                    	selecao.add(historicoList.get(i).getNome_selecao());
                    	titulos.add(historicoList.get(i).getTitulos());
                    }
                    String label = null;
                    String data = null;
                    Gson gson = new GsonBuilder().create();
                    label = gson.toJson(selecao);
                    data = gson.toJson(titulos);
                    request.setAttribute("label", label);
                    request.setAttribute("data", data);
                    request.setAttribute("historicoList", historicoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/titulos.jsp");
                dispatcher.forward(request, response);
            	break;
        }

    }
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//		
//		HistoricoDAO dao;
//		Historico historico = new Historico();
//		
//		switch (request.getServletPath()) {
//	        case "/historico/create": {
//	            historico.setNome_selecao(request.getParameter("selecao"));
//	            if(request.getParameter("titulos")!= "")
//	            historico.setTitulos(Integer.parseInt(request.getParameter("titulos")));
//	            historico.setParticipacoes(Integer.parseInt(request.getParameter("participacoes")));
//	            historico.setTotal_cartoes(Integer.parseInt(request.getParameter("total_cartoes")));
//	            historico.setCartoes_amarelos(Integer.parseInt(request.getParameter("cartoes_amarelos")));
//	            historico.setCartoes_vermelhos(Integer.parseInt(request.getParameter("cartoes_vermelhos")));
//	            historico.setPartidas(Integer.parseInt(request.getParameter("partidas")));
//	            historico.setPontuacao(Integer.parseInt(request.getParameter("pontuacao")));
//	            historico.setVitorias(Integer.parseInt(request.getParameter("vitorias")));
//	            historico.setDerrotas(Integer.parseInt(request.getParameter("derrotas")));
//	            historico.setEmpates(Integer.parseInt(request.getParameter("empates")));
//	            
//	            try (DAOFactory daoFactory = new DAOFactory()) {
//	                
//	
//	                dao = daoFactory.getHistoricoDAO();
//	
//	                dao.create(historico);
//	
//	                response.sendRedirect(request.getContextPath() + "/historico");
//	            } catch (ClassNotFoundException | IOException | SQLException ex) {
//	//                session.setAttribute("error", ex.getMessage());
//	                response.sendRedirect(request.getContextPath() + "/historico/create");
//	            } catch (ParseException ex) {
//	//                session.setAttribute("error", "O formato de data aceito é dd/mm/aaaa. Por favor, tente novamente.");
//	                response.sendRedirect(request.getContextPath() + "/historico/create");
//	            }
//	
//	            break;
//	        }
//	        case "/historico/update": {
//                historico.setNome_selecao(request.getParameter("id"));
//                historico.setTitulos(Integer.parseInt(request.getParameter("titulos")));
//                historico.setParticipacoes(Integer.parseInt(request.getParameter("participacoes")));
//                historico.setTotal_cartoes(Integer.parseInt(request.getParameter("total_cartoes")));
//                historico.setCartoes_amarelos(Integer.parseInt(request.getParameter("cartoes_amarelos")));
//                historico.setCartoes_vermelhos(Integer.parseInt(request.getParameter("cartoes_vermelhos")));
//                historico.setPartidas(Integer.parseInt(request.getParameter("partidas")));
//                historico.setPontuacao(Integer.parseInt(request.getParameter("pontuacao")));
//                historico.setVitorias(Integer.parseInt(request.getParameter("vitorias")));
//                historico.setDerrotas(Integer.parseInt(request.getParameter("derrotas")));
//                historico.setEmpates(Integer.parseInt(request.getParameter("empates")));
//
//                try (DAOFactory daoFactory = new DAOFactory()) {
//
//                    dao = daoFactory.getHistoricoDAO();
//
//                    dao.update(historico);
//
//                    response.sendRedirect(request.getContextPath() + "/historico");
//                } catch (ClassNotFoundException | IOException | SQLException ex) {
//                    response.sendRedirect(request.getContextPath() + "/historico/update?id=" + historico.getNome_selecao());
//                } catch (ParseException ex) {
//                    response.sendRedirect(request.getContextPath() + "/historico/update?id=" + historico.getNome_selecao());
//                }
//
//                break;
//            }
//            case "/historico/delete": {
//                String[] historicos = request.getParameterValues("delete");
//
////                try (DAOFactory daoFactory = new DAOFactory()) {
////                    dao = daoFactory.getHistoricoDAO();
//
////                    try {
////                        daoFactory.beginTransaction();
////
////                        for (String usuarioId : usuarios) {
////                            dao.delete(Integer.parseInt(usuarioId));
////                        }
////
////                        daoFactory.commitTransaction();
////                        daoFactory.endTransaction();
////                    } catch (SQLException ex) {
////                        session.setAttribute("error", ex.getMessage());
////                        daoFactory.rollbackTransaction();
////                    }
////                } catch (ClassNotFoundException | IOException ex) {
////                    session.setAttribute("error", ex.getMessage());
////                } catch (SQLException ex) {
////                    session.setAttribute("rollbackError", ex.getMessage());
////                }
////
////                response.sendRedirect(request.getContextPath() + "/usuario");
////
//                break;
//            }
//		}
		
//	}
}

