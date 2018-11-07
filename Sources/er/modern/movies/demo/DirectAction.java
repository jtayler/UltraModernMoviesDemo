package er.modern.movies.demo;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.directtoweb.D2W;
import com.webobjects.foundation.NSLog;

import er.directtoweb.ERD2WDirectAction;
import er.modern.movies.demo.components.Main;


public class DirectAction extends ERD2WDirectAction {
	public DirectAction(WORequest request) {
		super(request);
	}

	@Override
	public WOActionResults defaultAction() {
		WOActionResults page = pageWithName(Main.class);
		
		((Session) session()).navController();
		return page;
	}
	
    /**
     * Checks if a page configuration is allowed to render.
     * Provide a more intelligent access scheme as the default just returns false. And
     * be sure to read the javadoc to the super class.
     * @param pageConfiguration
     * @return
     */
    @Override
    protected boolean allowPageConfiguration(String pageConfiguration) {
        return false;
    }
    
	public WOActionResults loginAction() {
		
		String username = request().stringFormValueForKey("username");
		String password = request().stringFormValueForKey("password");
		
		NSLog.out.appendln("***DirectAction.loginAction - username: " + username + " : password: " + password + "***");
		
		// ENHANCEME - add appropriate login behaviour here
		
        return D2W.factory().pageForConfigurationNamed("QueryMovie", session());
	}
	
}
