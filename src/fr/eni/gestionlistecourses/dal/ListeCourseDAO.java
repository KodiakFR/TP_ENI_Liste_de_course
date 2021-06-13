package fr.eni.gestionlistecourses.dal;

import java.util.List;

import fr.eni.gestionlistecourses.bo.Article;
import fr.eni.gestionlistecourses.bo.ListeCourse;

public interface ListeCourseDAO {
	
	public void insertArticle(Article A) throws DALException;
	
	public void insertListeCourse (ListeCourse L) throws DALException;
	
	public void deleteArticle(Integer IdArt) throws DALException;
	
	public void deleteListe(Integer idList) throws DALException;
	
	public List<ListeCourse> SelectAllListe() throws DALException;
	
	public List<Article> SelectArticleByIDList(int IdList) throws DALException;
	
	public void updateArticle(Boolean checkCase, int idArticle) throws DALException;
	
	public ListeCourse SelectAll(int idListe) throws DALException;

}
