package fr.eni.gestionlistecourses.dal;

public class DALException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DALException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	//Méthodes
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer("Couche DAL - ");
			sb.append(super.getMessage());
			
			return sb.toString() ;
		}

}
