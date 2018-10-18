package er.modern.movies.demo.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WPage;

import er.extensions.components.ERXComponent;

public class PageWrapper extends ERXComponent {
	
    public PageWrapper(WOContext context) {
        super(context);
    }
    
    public D2WContext d2wContext() {
    	if (context().page() instanceof D2WPage) {
			D2WPage d2wPage = (D2WPage) context().page();
			return d2wPage.d2wContext();
		}
    	return null;
    }

	public String bodyClass() {
		return "Body";
	}
}