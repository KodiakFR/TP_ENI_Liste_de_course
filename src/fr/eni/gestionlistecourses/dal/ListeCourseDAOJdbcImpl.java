package fr.eni.gestionlistecourses.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.gestionlistecourses.bo.Article;
import fr.eni.gestionlistecourses.bo.ListeCourse;

public class ListeCourseDAOJdbcImpl implements ListeCourseDAO{
	
	//Instruction SQL
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLE"+
			"(NOM_ARTICLE, CHECK_CASE, ID_LISTE)"+
			"VALUES(?, ?, ?)";
	
	private static final String INSERTE_LISTE = "INSERT INTO LISTE"+
			"(NOM_LISTE)"+
			"VALUES(?)";

	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLE WHERE ID_ARTICLE= ?";
	
	private static final String DELETE_LISTE = "DELETE FROM LISTE WHERE ID_LISTE= ?";
	
	private static final String SELECT_ALL_LISTE = "SELECT ID_LISTE, NOM_LISTE FROM LISTE";
	
	private static final String SELECT_ARTICLE_BY_ID_LISTE = "SELECT NOM_ARTICLE, CHECK_CASE"+
			"FROM ARTICLE WHERE ID_LISTE= ?";
	
	private static final String UPDATE_CHECK_CASE = "UPDATE ARTICLE SET CHECK_CASE =? WHERE ID_ARTICLE=?";
	
	private static final String SELECT_ARTICLE_BY_ID_PLUS_LISTE = "SELECT a.ID_ARTICLE, a.NOM_ARTICLE, a.CHECK_CASE, l.NOM_LISTE, l.ID_LISTE"
						+ " FROM ARTICLE a INNER JOIN LISTE l ON a.ID_LISTE = l.ID_LISTE WHERE l.ID_LISTE= ?";
	
	//INSERTION D'UN ARTICLE
	public void insertArticle(Article A) throws DALException{
		try(Connection cnx = ConnectionProvider.getConnection(); 
			PreparedStatement stmt = cnx.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);) {
			setParameterArticle (stmt, A);
			int nbRow = stmt.executeUpdate();
			if (nbRow !=1) {
				throw new DALException("Echec de l'enregistrement - ");
			}else {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					A.setIdArticle(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Echec de l'enregistrement de l'article - "+e.getMessage());
		}
	}
	
	private void setParameterArticle (PreparedStatement stmt, Article art) throws SQLException{
		stmt.setString(1, art.getNomArticle());
		stmt.setBoolean(2, art.getCheckCase());
		stmt.setInt(3, art.getIdListeCourse());
	}
	
	//INSERTION D'UNE LISTE DE COURSE
	public void insertListeCourse (ListeCourse L) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement stmt = cnx.prepareStatement(INSERTE_LISTE, Statement.RETURN_GENERATED_KEYS);){
			setParameterListe (stmt, L);
			int nbRow = stmt.executeUpdate();
			if (nbRow !=1) {
				throw new DALException("Echec de l'enregistrement - ");
			}else {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					L.setIdListeCourse(rs.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new DALException("Echec de l'enregistrement de la liste de course - "+e.getMessage());
		}
	}

	private void setParameterListe(PreparedStatement stmt, ListeCourse list) throws SQLException{
		stmt.setString(1, list.getNomListeCourse());
	}

	//SUPPRESSION D'UN ARTICLE
	public void deleteArticle(Integer IdArt) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(DELETE_ARTICLE);){
			stmt.setInt(1, IdArt);
			int nbRow = stmt.executeUpdate();
			if (nbRow !=1) {
				throw new DALException("Echec de l'enregistrement - ");
			}
		} catch (Exception e) {
			throw new DALException("Echec de l'enregistrement de la liste de course - "+e.getMessage());
		}
	}
	
	//SUPPRESSION D'UNE LISTE DE COURSE
	public void deleteListe(Integer idList) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(DELETE_LISTE);){
				stmt.setInt(1, idList);
				int nbRow = stmt.executeUpdate();
				if (nbRow !=1) {
					throw new DALException("Echec de l'enregistrement - ");
				}
			} catch (Exception e) {
				throw new DALException("Echec de l'enregistrement de la liste de course - "+e.getMessage());
			}
	}
		
	//SELECT TOUTES LES LISTES
	
	public List<ListeCourse> SelectAllListe() throws DALException{
		List<ListeCourse> listeCourse = new ArrayList<ListeCourse>();
		try (Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();){
			ResultSet rs =  stmt.executeQuery(SELECT_ALL_LISTE);
			ListeCourse liste = null;
			while (rs.next()) {
				liste = mappingListe(rs);
				listeCourse.add(liste);
			}	
		} catch (SQLException e) {
			throw new DALException("Echec de la recherche - " +e.getMessage()); 
		}
		
		return listeCourse;
	}

	private ListeCourse mappingListe(ResultSet rs) throws SQLException{
		ListeCourse liste = null;
		Integer IdListe = rs.getInt("ID_LISTE");
		String Nomliste = rs.getString("NOM_LISTE");
		liste = new ListeCourse(IdListe, Nomliste);
		return liste;
	}

	//SELECT TOUTS LES ARTICLE D'UNE LISTE
	public List<Article> SelectArticleByIDList(int IdList) throws DALException{
		List<Article> listeArticle = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt= cnx.prepareStatement(SELECT_ARTICLE_BY_ID_LISTE);){
			stmt.setInt(1, IdList);
			Article A = null;
			try (ResultSet rs = stmt.executeQuery();){
				while(rs.next()) {
					A = mappingArticle(rs);
					listeArticle.add(A);
				}
			} 
		}catch (SQLException e) {
			throw new DALException("Echec de la recherche - " +e.getMessage()); 
				
		}
		return listeArticle;
	}

	private Article mappingArticle(ResultSet rs) throws SQLException{
		Article A = null;
		Integer idArticle = rs.getInt("ID_ARTICLE");
		String nomArticle = rs.getString("NOM_ARTICLE");
		boolean check = rs.getBoolean("CHECK_CASE");
		A = new Article(idArticle, nomArticle, check) ;
		return A;
	}
	
	//Changer l'état de l'article (coché / non coché)
	
	public void updateArticle(Boolean checkCase, int idArticle) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_CHECK_CASE);) {
			stmt.setBoolean(1, checkCase);
			stmt.setInt(2, idArticle);
			int nbRow = stmt.executeUpdate();
			if (nbRow !=1) {
				throw new DALException("Echec de l'enregistrement - ");
			}
		} catch (SQLException e) {
			throw new DALException("Echec de l'enregistrement de l'article - "+e.getMessage());
		}
	}
	// Select ALL
	public ListeCourse SelectAll(int idListe) throws DALException{
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID_PLUS_LISTE);){
			stmt.setInt(1, idListe);
			ListeCourse L = null;
			try (ResultSet rs = stmt.executeQuery();){
					L = mappingListeCourse(rs);
			}
			return L;	
		}
		catch (SQLException e) {
			throw new DALException("Echec du Select All - "+e.getMessage());
		}
		
	}

	private ListeCourse mappingListeCourse(ResultSet rs) throws SQLException{
		ListeCourse L = null;
		String nomListe = null;
		Integer ID = null;
		List<Article> ListA = new ArrayList<Article>();
		Article A = null;
		while(rs.next()) {
			nomListe = rs.getString("NOM_LISTE");
			ID = rs.getInt("ID_LISTE");
			A = mappingArticle(rs);
			ListA.add(A);		
		}
		L = new ListeCourse(ID, nomListe, ListA);
		return L;
	}
}

		