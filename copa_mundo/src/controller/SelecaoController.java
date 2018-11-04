package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.lang.reflect.Type;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.DAO;
import dao.DAOFactory;
import dao.SelecaoDAO;
import model.Selecao;


@MultipartConfig()
@WebServlet(name = "SelecaoController", urlPatterns = {"/selecao",
    "/selecao/create",
    "/selecao/update",
    "/selecao/delete",
    "/selecao/read",
    "/selecao/json"
})
public class SelecaoController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Selecao> dao;
        Selecao selecao;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/selecao":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getSelecaoDAO();

                    List<Selecao> selecaoList = dao.all();
                    request.setAttribute("selecaoList", selecaoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/selecao.jsp");
                dispatcher.forward(request, response);
                break;

            case "/selecao/create":
            	
                dispatcher = request.getRequestDispatcher("/selecao");
                dispatcher.forward(request, response);

                break;

            case "/selecao/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getSelecaoDAO();

                    selecao = dao.read(request.getParameter("id"));
                    request.setAttribute("selecao", selecao);

                    dispatcher = request.getRequestDispatcher("../updateSelecao.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/selecao");
                }

                break;

            case "/selecao/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getSelecaoDAO();
                    dao.delete(request.getParameter("id"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/selecao");

                break;

            case "/selecao/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getSelecaoDAO();

                    selecao = dao.read(request.getParameter("id"));
                    
                    request.setAttribute("selecao", selecao);

                    dispatcher = request.getRequestDispatcher("../visualizaSelecao.jsp");
                    dispatcher.forward(request, response);

//                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
//                    String json = gson.toJson(usuario);

//                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
                
            case "/selecao/json":				
				SelecaoDAO selecaoDao;
				DAOFactory daoFactory = null;
				HttpSession session = request.getSession();
				Gson gson = new GsonBuilder().create();
				Type type = new TypeToken<List<Selecao>>(){}.getType();
				
				try {
					 
		            BufferedReader br = new BufferedReader(new FileReader("/home/eduardo/selecoes.json"));
		            List<Selecao> timeList = gson.fromJson(br,type);
		            
		            try {
		            	daoFactory = new DAOFactory();
//	                    response.sendRedirect(request.getContextPath() + "/time");
	                } catch (ClassNotFoundException | IOException | SQLException ex) {
//	                    session.setAttribute("error", ex.getMessage());
//	                    response.sendRedirect(request.getContextPath() + "/time/create");
	                }
		            
		            selecaoDao = daoFactory.getSelecaoDAO();

                    for (int i=0; i<timeList.size(); i++) {
                    	try {
                    		selecaoDao.create(timeList.get(i));                    		
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
		
		SelecaoDAO dao;
		Selecao selecao = new Selecao();
		
		switch (request.getServletPath()) {
	        case "/selecao/create": {
	        	 System.err.println("Nome :"+request.getParameter("nome"));
	            selecao.setNome(request.getParameter("nome"));
	            selecao.setBandeira(request.getParameter("bandeira"));
	            System.out.println("Nome :"+request.getParameter("nome"));
	
	            try (DAOFactory daoFactory = new DAOFactory()) {
	                
	
	                dao = daoFactory.getSelecaoDAO();
	
	                dao.create(selecao);
	
	                response.sendRedirect(request.getContextPath() + "/selecao");
	            } catch (ClassNotFoundException | IOException | SQLException ex) {
	//                session.setAttribute("error", ex.getMessage());
	                response.sendRedirect(request.getContextPath() + "/selecao/create");
	            } catch (ParseException ex) {
	//                session.setAttribute("error", "O formato de data aceito é dd/mm/aaaa. Por favor, tente novamente.");
	                response.sendRedirect(request.getContextPath() + "/selecao/create");
	            }
	
	            break;
	        }
	        case "/selecao/update": {
                selecao.setNome(request.getParameter("id"));
                selecao.setBandeira(request.getParameter("bandeira"));
                
                try (DAOFactory daoFactory = new DAOFactory()) {

                    dao = daoFactory.getSelecaoDAO();

                    dao.update(selecao);

                    response.sendRedirect(request.getContextPath() + "/selecao");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    response.sendRedirect(request.getContextPath() + "/selecao/update?id=" + selecao.getNome());
                } catch (ParseException ex) {
                    response.sendRedirect(request.getContextPath() + "/selecao/update?id=" + selecao.getNome());
                }

                break;
            }
		}
	}
		
		
}
