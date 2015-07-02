package be.vdab.dao;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DAOException(Throwable cause) {
		super(cause);
	}

}

// Exception class die we in catch blokken kunnen throwen naar de servlets. De
// servlet
// vangt geen SQLExceptions meer en is zo niet meer zo hard gecodeerd gekoppeld
// aan JDBC
