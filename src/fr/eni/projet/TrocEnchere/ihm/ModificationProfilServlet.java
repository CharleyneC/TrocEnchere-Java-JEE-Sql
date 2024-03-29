package fr.eni.projet.TrocEnchere.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.TrocEnchere.bll.BllException;
import fr.eni.projet.TrocEnchere.bll.UtilisateurManager;
import fr.eni.projet.TrocEnchere.bo.Utilisateur;


@WebServlet("/update")
public class ModificationProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pageModifierProfil.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		// Je recupère les informations en param�tres
		try {
					
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String cpo = request.getParameter("cpo");
			String ville = request.getParameter("ville");
			String mdp = request.getParameter("mdp");
		
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, cpo, ville, mdp);
			Utilisateur userSession = (Utilisateur) session.getAttribute("Utilisateur");
			utilisateur.setNoUser(userSession.getNoUser());
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			utilisateurManager.updateProfil(utilisateur);
			
			session.setAttribute("Utilisateur", utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("AffichageProfil");
			rd.forward(request, response);

		} catch ( SQLException | BllException e) {
			RequestDispatcher rd = request.getRequestDispatcher("update");
			rd.forward(request, response);
			e.printStackTrace();
		}

		}
	}
	
	

