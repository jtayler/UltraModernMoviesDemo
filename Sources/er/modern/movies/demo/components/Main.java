package er.modern.movies.demo.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOModelGroup;

import er.extensions.components.ERXComponent;
import er.modern.look.components.ERMODComponent;

public class Main extends ERMODComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _username;
	private String _password;
	private String _errorMessage;
	
	public Main(WOContext context) {
		super(context);
	}
	
    private D2WContext d2wContext;

	public D2WContext d2wContext() {	
    	if (d2wContext == null) {
    		d2wContext = new D2WContext();
        	EOEntity entity = EOModelGroup.defaultGroup().entityNamed("Login");
        	d2wContext().setEntity(entity);
        	d2wContext().setTask("Log In");

    	}
    	return d2wContext;
    }
	
	public void setUsername(String username) {
		_username = username;
	}

	public String username() {
		return _username;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public String password() {
		return _password;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public String errorMessage() {
		return _errorMessage;
	}
	
}
