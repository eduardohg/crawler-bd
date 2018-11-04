package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.DAO;
import dao.DAOFactory;
import dao.HistoricoDAO;
import model.Historico;



@MultipartConfig()
@WebServlet(name = "HistoricoController", urlPatterns = {"/historico",
    "/historico/create",
    "/historico/update",
    "/historico/delete",
    "/historico/read",
    "/historico/json"
})
public class HistoricoController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Historico> dao;
        Historico historico;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/historico":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getHistoricoDAO();
                    List<Historico> historicoList = dao.all();
                    request.setAttribute("historicoList", historicoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/historico.jsp");
                dispatcher.forward(request, response);
                break;

            case "/historico/create":
            	
                dispatcher = request.getRequestDispatcher("/historico");
                dispatcher.forward(request, response);

                break;

            case "/historico/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getHistoricoDAO();

                    historico = dao.read(request.getParameter("id"));
                    request.setAttribute("historico", historico);

                    dispatcher = request.getRequestDispatcher("../updateHistorico.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/historico");
                }

                break;

            case "/historico/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getHistoricoDAO();
                    dao.delete(request.getParameter("id"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/historico");

                break;

            case "/historico/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getHistoricoDAO();

                    historico = dao.read(request.getParameter("id"));
                    request.setAttribute("historico", historico);

                    dispatcher = request.getRequestDispatcher("../visualizaHistorico.jsp");
                    dispatcher.forward(request, response);

//                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
//                    String json = gson.toJson(usuario);

//                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/historico");
                }

                break;
                
            case "/historico/json":				
				HistoricoDAO historicoDao;
				DAOFactory daoFactory = null;
				HttpSession session = request.getSession();
				Gson gson = new GsonBuilder().create();
				Type type = new TypeToken<List<Historico>>(){}.getType();
				
				try {
					 
		            BufferedReader br = new BufferedReader(new FileReader("/home/eduardo/crawler.json"));
		            List<Historico> timeList = gson.fromJson(br,type);
		            
		            try {
		            	daoFactory = new DAOFactory();
//	                    response.sendRedirect(request.getContextPath() + "/time");
	                } catch (ClassNotFoundException | IOException | SQLException ex) {
//	                    session.setAttribute("error", ex.getMessage());
//	                    response.sendRedirect(request.getContextPath() + "/time/create");
	                }
		            
		            historicoDao = daoFactory.getHistoricoDAO();

                    for (int i=0; i<timeList.size(); i++) {
                    	try {
                    		historicoDao.create(timeList.get(i));                    		
                    	} catch (Exception e) {
//                    		
                    	}
                    }
		            
		 
//		            System.out.println(timeList.get(0).getNome());
		 
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			
			    break;

        }

    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HistoricoDAO dao;
		Historico historico = new Historico();
		
		switch (request.getServletPath()) {
	        case "/historico/create": {
	            historico.setNome_selecao(request.getParameter("selecao"));
	            if(request.getParameter("titulos")!= "")
	            historico.setTitulos(Integer.parseInt(request.getParameter("titulos")));
	            historico.setParticipacoes(Integer.parseInt(request.getParameter("participacoes")));
	            historico.setTotal_cartoes(Integer.parseInt(request.getParameter("total_cartoes")));
	            historico.setCartoes_amarelos(Integer.parseInt(request.getParameter("cartoes_amarelos")));
	            historico.setCartoes_vermelhos(Integer.parseInt(request.getParameter("cartoes_vermelhos")));
	            historico.setPartidas(Integer.parseInt(request.getParameter("partidas")));
	            historico.setPontuacao(Integer.parseInt(request.getParameter("pontuacao")));
	            historico.setVitorias(Integer.parseInt(request.getParameter("vitorias")));
	            historico.setDerrotas(Integer.parseInt(request.getParameter("derrotas")));
	            historico.setEmpates(Integer.parseInt(request.getParameter("empates")));
	            
	            try (DAOFactory daoFactory = new DAOFactory()) {
	                
	
	                dao = daoFactory.getHistoricoDAO();
	
	                dao.create(historico);
	
	                response.sendRedirect(request.getContextPath() + "/historico");
	            } catch (ClassNotFoundException | IOException | SQLException ex) {
	//                session.setAttribute("error", ex.getMessage());
	                response.sendRedirect(request.getContextPath() + "/historico/create");
	            } catch (ParseException ex) {
	//                session.setAttribute("error", "O formato de data aceito é dd/mm/aaaa. Por favor, tente novamente.");
	                response.sendRedirect(request.getContextPath() + "/historico/create");
	            }
	
	            break;
	        }
	        case "/historico/update": {
                historico.setNome_selecao(request.getParameter("id"));
                historico.setTitulos(Integer.parseInt(request.getParameter("titulos")));
                historico.setParticipacoes(Integer.parseInt(request.getParameter("participacoes")));
                historico.setTotal_cartoes(Integer.parseInt(request.getParameter("total_cartoes")));
                historico.setCartoes_amarelos(Integer.parseInt(request.getParameter("cartoes_amarelos")));
                historico.setCartoes_vermelhos(Integer.parseInt(request.getParameter("cartoes_vermelhos")));
                historico.setPartidas(Integer.parseInt(request.getParameter("partidas")));
                historico.setPontuacao(Integer.parseInt(request.getParameter("pontuacao")));
                historico.setVitorias(Integer.parseInt(request.getParameter("vitorias")));
                historico.setDerrotas(Integer.parseInt(request.getParameter("derrotas")));
                historico.setEmpates(Integer.parseInt(request.getParameter("empates")));

                try (DAOFactory daoFactory = new DAOFactory()) {

                    dao = daoFactory.getHistoricoDAO();

                    dao.update(historico);

                    response.sendRedirect(request.getContextPath() + "/historico");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    response.sendRedirect(request.getContextPath() + "/historico/update?id=" + historico.getNome_selecao());
                } catch (ParseException ex) {
                    response.sendRedirect(request.getContextPath() + "/historico/update?id=" + historico.getNome_selecao());
                }

                break;
            }
            case "/historico/delete": {
                String[] historicos = request.getParameterValues("delete");

//                try (DAOFactory daoFactory = new DAOFactory()) {
//                    dao = daoFactory.getHistoricoDAO();

//                    try {
//                        daoFactory.beginTransaction();
//
//                        for (String usuarioId : usuarios) {
//                            dao.delete(Integer.parseInt(usuarioId));
//                        }
//
//                        daoFactory.commitTransaction();
//                        daoFactory.endTransaction();
//                    } catch (SQLException ex) {
//                        session.setAttribute("error", ex.getMessage());
//                        daoFactory.rollbackTransaction();
//                    }
//                } catch (ClassNotFoundException | IOException ex) {
//                    session.setAttribute("error", ex.getMessage());
//                } catch (SQLException ex) {
//                    session.setAttribute("rollbackError", ex.getMessage());
//                }
//
//                response.sendRedirect(request.getContextPath() + "/usuario");
//
                break;
            }
		}
		
	}
}
