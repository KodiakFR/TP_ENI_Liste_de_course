package fr.eni.gestionlistecourses.bll;

import java.util.List;

import fr.eni.gestionlistecourses.bo.Article;
import fr.eni.gestionlistecourses.bo.ListeCourse;
import fr.eni.gestionlistecourses.dal.DALException;
import fr.eni.gestionlistecourses.dal.DAOFactory;
import fr.eni.gestionlistecourses.dal.ListeCourseDAO;

public class ListeCourseManager {
	
	//Attributes 
	
	private ListeCourseDAO daoListeCourse;
	private static ListeCourseManager instance = null;
	
	//Constructors
	
	public ListeCourseManager() throws BLLException{
		
		daoListeCourse = DAOFactory.getListeCourseDAO();
		
	}
	
	/*
	 * Design partern Singleton 
	 */
	public static synchronized ListeCourseManager getInstance() throws BLLException {
		if (instance == null) {
			instance = new ListeCourseManager();
		}
		return instance;
	}
	
	/*
	 * Methode qui valide listes de courses
	 * 
	 */
	
	private void validerListe(ListeCourse L) throws BLLException{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		if (L.getNomListeCourse() ==null ||L.getNomListeCourse().trim().length() == 0)
		{
			sb.append("Le nom de la liste de course est obligatoire.\n");
			valide = false;
		}
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}
	
	/*
	 * Methode qui valide listes de courses
	 * 
	 */
	private void validerArticle(Article A) throws BLLException{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		if (A.getNomArticle() ==null ||A.getNomArticle().trim().length() == 0)
		{
			sb.append("Le nom de l'article est obligatoire.\n");
			valide = false;
		}
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}
	
	
	/*
	 * Methode ajout article 
	 */
	public void insertArticle(Article A, ListeCourse L) throws BLLException{
		if (L.getIdListeCourse() != null){
			System.out.println("utilisation du if ajout.article");
			
				try {
					validerArticle(A);
					System.out.println("article valider");
					daoListeCourse.insertArticle(A);
				} catch (DALException e) {
					throw new BLLException("Echec addArticle - article: " + A, e);
				}
		}		

		if(L.getIdListeCourse()	== null){
			
			try {
				validerArticle(A);
				validerListe(L);
				daoListeCourse.insertListeCourse(L);
				System.out.println(L.getIdListeCourse());
				A.setIdListeCourse(L.getIdListeCourse());
				daoListeCourse.insertArticle(A);
			} catch (DALException e) {
				throw new BLLException("Echec addArticle  et addliste - article: " + A + "Liste: "+L, e);
			}
		}
		
	}
	

	/*
	 * Méthode qui coche ou décoche la case article 
	 */
	public void cocheCase(boolean checkCase, Integer idArticle) throws BLLException {
		try {
			daoListeCourse.updateArticle(checkCase, idArticle);			
		} catch (DALException e) {
			throw new BLLException("Echec du changement de statut de l'article" +checkCase, e);
		}
	}
	
	/*
	 * Méthode qui affiche les listes de course
	 */
	public List<ListeCourse> getListeCourse() throws BLLException{
		try {
			return daoListeCourse.SelectAllListe();			
		} catch (DALException e) {
			throw new BLLException("Echec de l'affichage des listes de course", e);
		}
	}
	
	/*
	 * Méthode qui afficher une liste avec ses article
	 */
	public ListeCourse getListeArticle(int IdList) throws BLLException{
		try {
			return daoListeCourse.SelectAll(IdList);
		} catch (DALException e) {
			throw new BLLException("Echec de l'affichage des listes des articles", e);
		}
	}

	/*
	 * Méthode pour supprimer un article 
	 * 
	 */
	
	public void deleteArticle(int ID_article) throws BLLException{
		try {
			daoListeCourse.deleteArticle(ID_article);
		}catch (DALException e) {
			throw new BLLException("Echec de la supression de l'article", e);
		}
	}
	
	public void deleteListe(int ID_Liste) throws BLLException{
		try {
			daoListeCourse.deleteListe(ID_Liste);
		}catch (DALException e) {
			throw new BLLException("Echec de la supression de la liste", e);
		}
	}
}
