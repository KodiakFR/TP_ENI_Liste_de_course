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
import fr.eni.gestionlistecourses.bo.Article;
import fr.eni.gestionlistecourses.bo.ListeCourse;

/**
 * Servlet implementation class ServletNouvelleListe
 */
@WebServlet(
		urlPatterns = {"/NouvelleListe", "/Delete"}
		
		)
public class ServletNouvelleListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/NouvelleListe")){
			
		}
		
		if(request.getServletPath().equals("/Delete"))
				{
					try {
						ListeCourseManager ListeCourse = new ListeCourseManager();
				
						//récupération d'id article
						Integer idArticle = Integer.parseInt(request.getParameter("idArticle"));
						Integer idListe = Integer.parseInt(request.getParameter("idListe"));
						
						
						ListeCourse.deleteArticle(idArticle);
						
						
						//Affichage de ma page 

						ListeCourse RecupLC = ListeCourse.getListeArticle(idListe);
						System.out.println(RecupLC.toString());
						request.setAttribute("RecupLC", RecupLC);
					
						
						
					} catch (BLLException e) {
						e.printStackTrace();
					}
					
					
				}
		
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleListe.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			
			ListeCourseManager ListeCourse = new ListeCourseManager();
	
			String nomListe = request.getParameter("liste");
			System.out.println("j'ai récupérer mon nomliste");
			String nomArticle = request.getParameter("article");
			
			System.out.println("j'ai récupérer mon nomArticle");
			
			Integer idListe = null;			
			if (request.getParameter("id")!= "")
			{
				idListe = Integer.valueOf(request.getParameter("id"));
			}
			
			System.out.println("j'ai récupérer mon IDliste");
			System.out.println(idListe);
			
		
			
			//construction Article
					Article A = new Article(nomArticle, false, idListe);
					System.out.println("j'ai construit mon article");
			//construction Liste
			
			List<Article> listeArticle = new ArrayList<Article>();
			listeArticle.add(A);
			ListeCourse LC = new ListeCourse(idListe, nomListe, listeArticle);
			System.out.println(LC.toString());
			System.out.println("j'ai construit ma liste");
			
			//Appel de la méthode insert
			
			ListeCourse.insertArticle(A, LC);
			
			//Affichage de ma page 

			idListe = LC.getIdListeCourse();
			System.out.println(idListe);
			ListeCourse RecupLC = ListeCourse.getListeArticle(idListe);
			System.out.println(RecupLC.toString());
			request.setAttribute("RecupLC", RecupLC);
			
			
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleListe.jsp");
			rd.forward(request, response);
			
			
		} catch (NumberFormatException | BLLException e) {
			e.printStackTrace();
		}
		
	
		
		
//		doGet(request, response);
	}

}
