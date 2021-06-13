package fr.eni.gestionlistecourses.bll;

public class BLLException extends Exception {

/**
 * 
 */
private static final long serialVersionUID = 1L;

public BLLException() {
	super();
}

public BLLException(String message, Throwable cause) {
	super(message, cause);
}

public BLLException(String message) {
	super(message);
}

//Méthodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche BLL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}

}
