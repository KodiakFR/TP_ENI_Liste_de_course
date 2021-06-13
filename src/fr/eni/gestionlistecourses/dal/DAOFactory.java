package fr.eni.gestionlistecourses.dal;

public class DAOFactory {

	public static ListeCourseDAO getListeCourseDAO() {
		 return new ListeCourseDAOJdbcImpl();
	 }
}
