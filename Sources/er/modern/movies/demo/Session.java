package er.modern.movies.demo;

import er.extensions.appserver.ERXSession;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.appserver.navigation.ERXNavigationState;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;

	private MoviesNavigationController _navController;
	
	public Session() {
	}
	
	public MoviesNavigationController navController() {
		if (_navController == null) {
			_navController = new MoviesNavigationController(this);
		}
		return _navController;
	}
	
	public String navigationState() {
		ERXNavigationState nstate = ERXNavigationManager.manager().navigationStateForSession(session());
		String string = nstate + "";

		return string;
	}


}
