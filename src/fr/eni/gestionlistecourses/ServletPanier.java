package fr.eni.gestionlistecourses;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.gestionlistecourses.bll.BLLException;
import fr.eni.gestionlistecourses.bll.ListeCourseManager;
import fr.eni.gestionlistecourses.bo.ListeCourse;

/**
 * Servlet implementation class ServletPanier
 */
@WebServlet(
		urlPatterns = {"/Panier", "/Check"}
		)
public class ServletPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ListeCourseManager ListeCourse = new ListeCourseManager();	
			
		//Répuérer Id Liste
			Integer IdListe = Integer.parseInt(request.getParameter("idListe"));
		
		//Affichage de ma page 

		ListeCourse RecupLC = ListeCourse.getListeArticle(IdListe);
		System.out.println(RecupLC.toString());
		request.setAttribute("RecupLC", RecupLC);
		
		}
		catch (BLLException e) {
			e.printStackTrace();
		}
		
		if(request.getServletPath().equals("/Check")) {
			try {
				ListeCourseManager ListeCourse = new ListeCourseManager();
				
				// récuper l'id l'article, id de la liste et boolen de la case
				
				Integer idListe = Integer.parseInt(request.getParameter("idListe"));
				Integer idArticle = Integer.parseInt(request.getParameter("idArticle"));
				Boolean checkCase = Boolean.valueOf(request.getParameter("boolean"));
				System.out.println(checkCase);
				checkCase = !checkCase;
				System.out.println(checkCase);
				
				//methode mise à jour
				
				ListeCourse.cocheCase(checkCase, idArticle);
				
				//affiche ma liste
				
				ListeCourse RecupLC = ListeCourse.getListeArticle(idListe);
				System.out.println(RecupLC.toString());
				request.setAttribute("RecupLC", RecupLC);
			}
			catch (BLLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Panier.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
