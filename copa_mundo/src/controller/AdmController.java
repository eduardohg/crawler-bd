package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.DAO;
import dao.DAOFactory;
import dao.AdmDAO;
import model.Adm;



@MultipartConfig()
@WebServlet(name = "AdmController", urlPatterns = {"/adm",
    "/adm/create",
    "/adm/update",
    "/adm/delete",
    "/adm/read",
    "/adm/authenticate"
})
public class AdmController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Adm> dao;
        Adm adm;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/adm":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getAdmDAO();
                    List<Adm> admList = dao.all();
                    request.setAttribute("admList", admList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/indexAdm.jsp");
                dispatcher.forward(request, response);
                break;

            case "/adm/create":
            	
                dispatcher = request.getRequestDispatcher("/adm");
                dispatcher.forward(request, response);

                break;

            case "/adm/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getAdmDAO();

                    adm = dao.read(request.getParameter("login"));
                    request.setAttribute("adm", adm);

                    dispatcher = request.getRequestDispatcher("../updateHistorico.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/adm");
                }

                break;

            case "/adm/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getAdmDAO();
                    dao.delete(request.getParameter("login"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/adm");

                break;

            case "/adm/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getAdmDAO();

                    adm = dao.read(request.getParameter("id"));
                    request.setAttribute("adm", adm);

                    dispatcher = request.getRequestDispatcher("../visualizaHistorico.jsp");
                    dispatcher.forward(request, response);

//                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
//                    String json = gson.toJson(usuario);

//                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/adm");
                }

                break;

        }

    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		AdmDAO dao;
		Adm adm = new Adm();
		
		switch (request.getServletPath()) {
	        case "/adm/create": {
	            adm.setLogin(request.getParameter("login"));
	            adm.setSenha(request.getParameter("senha"));
	           
	            
	            try (DAOFactory daoFactory = new DAOFactory()) {
	                
	
	                dao = daoFactory.getAdmDAO();
	
	                dao.create(adm);
	
	                response.sendRedirect(request.getContextPath() + "/adm");
	            } catch (ClassNotFoundException | IOException | SQLException ex) {
	//                session.setAttribute("error", ex.getMessage());
	                response.sendRedirect(request.getContextPath() + "/adm/create");
	            } catch (ParseException ex) {
	//                session.setAttribute("error", "O formato de data aceito é dd/mm/aaaa. Por favor, tente novamente.");
	                response.sendRedirect(request.getContextPath() + "/adm/create");
	            }
	
	            break;
	        }
	        case "/adm/update": {
                adm.setLogin(request.getParameter("login"));
                adm.setSenha(request.getParameter("titulos"));
                

                try (DAOFactory daoFactory = new DAOFactory()) {

                    dao = daoFactory.getAdmDAO();

                    dao.update(adm);

                    response.sendRedirect(request.getContextPath() + "/adm");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    response.sendRedirect(request.getContextPath() + "/adm/update?id=" + adm.getLogin());
                } catch (ParseException ex) {
                    response.sendRedirect(request.getContextPath() + "/adm/update?id=" + adm.getLogin());
                }

                break;
            }
	        case "/adm/authenticate": {
	        		adm.setLogin(request.getParameter("login"));
	        		adm.setSenha(request.getParameter("senha"));
	        		String senha = request.getParameter("senha");
	        		String conf = request.getParameter("senhaconfirm");
	        		if(conf.compareTo(senha)==0) {
	        			try(DAOFactory daoFactory = new DAOFactory()){
		        			dao = daoFactory.getAdmDAO();
		        			dao.authenticate(adm);
		        			response.sendRedirect(request.getContextPath()+ "/adm");
		        			
		        		}catch(ClassNotFoundException | IOException | SQLException ex) {
		        			response.sendRedirect(request.getContextPath() + "/adm/");
		        		}catch(ParseException ex) {
		        			response.sendRedirect(request.getContextPath() + "/adm/");
		        		}
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
