package er.modern.movies.demo;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.navigation.ERXNavigationManager;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
	}

    public void finishInitialization() {
    	super.finishInitialization();
    	
    	// Setup main navigation
    	// or not.
    	ERXNavigationManager.manager().configureNavigation();
    	
    }
}
