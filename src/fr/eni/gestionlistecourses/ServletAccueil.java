package fr.eni.gestionlistecourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletAccueil
 */
@WebServlet(

urlPatterns = {"/Accueil", "/DeleteListe"}

)
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
				ListeCourseManager ListeCourseManager = new ListeCourseManager();
				List<ListeCourse> ListeCourse = new ArrayList<ListeCourse>();
				ListeCourse = ListeCourseManager.getListeCourse();
				request.setAttribute("ListeCourse", ListeCourse);
			}
			catch (BLLException e) {
				e.printStackTrace();
			}	
		
		
		if (request.getServletPath().equals("/DeleteListe"))
		{
			try {
				
				ListeCourseManager ListeCourseManager = new ListeCourseManager();
				
				//Récupération de l'id de la liste
				
				Integer idListe = Integer.parseInt(request.getParameter("idListe"));
				ListeCourseManager.deleteListe(idListe);
				
				//afficher la liste
				List<ListeCourse> ListeCourse = new ArrayList<ListeCourse>();
				ListeCourse = ListeCourseManager.getListeCourse();
				request.setAttribute("ListeCourse", ListeCourse);
	
				
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		
		
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/ListesPredefinies.jsp");
		rd.forward(request, response);
	}


	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
