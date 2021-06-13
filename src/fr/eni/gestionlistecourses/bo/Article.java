package fr.eni.gestionlistecourses.bo;

public class Article {
	
	//Attributes
	
		private Integer idArticle;
		private String nomArticle;
		private boolean checkCase;
		private Integer idListeCourse;
	
	// Constructors
		
		public Article() {
		}
		
		public Article(String nomArticle,boolean checkCase) {
			this.nomArticle =nomArticle;
			this.checkCase = checkCase;
		}
		
		public Article(String nomArticle,boolean checkCase, Integer idListeCourse) {
			this.nomArticle =nomArticle;
			this.checkCase = checkCase;
			this.idListeCourse = idListeCourse;
		}
		
		public Article(Integer idArticle, String nomArticle, boolean checkCase) {
			this.idArticle = idArticle;
			this.nomArticle =nomArticle;
			this.checkCase = checkCase;

		}
		
	// Getter Setter

		public Integer getIdArticle() {
			return idArticle;
		}

		public void setIdArticle(Integer idArticle) {
			this.idArticle = idArticle;
		}

		public String getNomArticle() {
			return nomArticle;
		}

		public void setNomArticle(String nomArticle) {
			this.nomArticle = nomArticle;
		}

		
		public boolean getCheckCase() {
			return checkCase;
		}

		public void setCheckCase(boolean checkCase) {
			this.checkCase = checkCase;
		}
		
		
		
		public Integer getIdListeCourse() {
			return idListeCourse;
		}

		public void setIdListeCourse(Integer idListeCourse) {
			this.idListeCourse = idListeCourse;
		}

		// toString 
		@Override
		public String toString() {
			return "Article [idArticle=" + idArticle + ", nomArticle=" + nomArticle + "]";
		}
		
}
