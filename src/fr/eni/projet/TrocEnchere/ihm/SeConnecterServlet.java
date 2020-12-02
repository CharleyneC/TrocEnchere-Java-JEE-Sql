package fr.eni.projet.TrocEnchere.ihm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.TrocEnchere.dal.UtilisateurDAO;
import fr.eni.projet.TrocEnchere.dal.UtilisateurDaoJdbcImpl;

/**
 * Servlet implementation class SeConnecterServlet
 */
@WebServlet("/SeConnecterServlet")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeConnecterServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String p=request.getParameter("pseudo");
	    String em=request.getParameter("email");
		String m=request.getParameter("mdp");

		// Créer un nouvel UtilisateurDaoJdbcImpl pour utiliser ses fonctions non instanciées
		UtilisateurDaoJdbcImpl uUser = new UtilisateurDaoJdbcImpl();
		
		
		try {	
			if (uUser.selectPseudo(p, m) != null || uUser.selectEmail(em, m) != null ) {
			
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
				rd.forward(request, response);		
			}
			
		
		} catch (Exception e) {
			System.out.println("erreur");
			System.out.println(e.getMessage());
	        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/pageLogin.jsp");
	        rd.include(request,response);
		}
		
	}
	
}








