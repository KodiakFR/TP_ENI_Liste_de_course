package fr.eni.gestionlistecourses.bo;

import java.util.List;

public class ListeCourse {	
	
	//Attributes 
	
		private Integer idListeCourse = null;
		private String nomListeCourse;
		private List<Article> ListeArticle;
		
		
	
	//Constructors 
		
		public ListeCourse() {
		}
		
		public ListeCourse(String nomListeCourse) {
			this.nomListeCourse = nomListeCourse;
		}
		
		public ListeCourse(String nomListeCourse, List<Article> listeArticle) {
			this.nomListeCourse = nomListeCourse;
			this.ListeArticle = listeArticle;
		}
		
		public ListeCourse(Integer idListeCourse, String nomListeCourse, List<Article> listeArticle) {
			this.idListeCourse = idListeCourse;
			this.nomListeCourse = nomListeCourse;
			this.ListeArticle = listeArticle;
		}
		
		public ListeCourse(Integer idListeCourse, String nomListeCourse) {
			this.idListeCourse = idListeCourse;
			this.nomListeCourse = nomListeCourse;
		}

		
	//Getter Setter
		
		public Integer getIdListeCourse() {
			return idListeCourse;
		}

		public void setIdListeCourse(Integer idListeCourse) {
			this.idListeCourse = idListeCourse;
		}

		public String getNomListeCourse() {
			return nomListeCourse;
		}

		public void setNomListeCourse(String nomListeCourse) {
			this.nomListeCourse = nomListeCourse;
		}

		
		public List<Article> getListeArticle() {
			return ListeArticle;
		}

		public void setListeArticle(List<Article> listeArticle) {
			ListeArticle = listeArticle;
		}
		
		
		//toString

		@Override
		public String toString() {
			return "ListeCourse [idListeCourse=" + idListeCourse + ", nomListeCourse=" + nomListeCourse
					+ ", listeArticle=]" + getListeArticle();
		}


}
