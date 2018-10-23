package er.modern.movies.demo;

import webobjectsexamples.businesslogic.movies.common.Talent;
import webobjectsexamples.businesslogic.movies.common.Voting;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WComponent;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.QueryPageInterface;

import er.directtoweb.pages.ERD2WQueryPage;
import er.extensions.eof.ERXGenericRecord;

public class MoviesNavigationController implements NextPageDelegate {

	public static final String MOVIE = "Movie";
	public static final String STUDIO = "Studio";
	public static final String REVIEW = "Review";
	
	private Session _session;

	private WOComponent nextPage;

	public MoviesNavigationController(Session s) {
		super();
		_session = s;
	}

	// NAV ACTIONS
	
	public WOComponent homeAction() {
        return D2W.factory().defaultPage(session());
    }
	
	// ADMIN
	
	public WOComponent adminAction() {
		return queryPageForEntityName(Talent.ENTITY_NAME);
	}
	
	// MOVIES
	
	public WOComponent queryMovieAction() {
		return queryPageForEntityName(MOVIE);
	}
	
	public WOComponent createMovieAction() {
		return newObjectForEntityName(MOVIE);
	}
	
	// STUDIOS
	
	public WOComponent queryStudioAction() {
		return queryPageForEntityName(STUDIO);
	}
	
	public WOComponent createStudioAction() {
		return newObjectForEntityName(STUDIO);
	}
	
	// TALENT
	
	public WOComponent queryTalentAction() {
		return queryPageForEntityName(Talent.ENTITY_NAME);
	}
	
	public WOComponent createTalentAction() {
		return newObjectForEntityName(Talent.ENTITY_NAME);
	}
	
	// VOTING
	
	public WOComponent queryVotingAction() {
		return queryPageForEntityName(Voting.ENTITY_NAME);
	}
	
	public WOComponent createVotingAction() {
		return newObjectForEntityName(Voting.ENTITY_NAME);
	}
	
	// REVIEW
	
	public WOComponent queryReviewAction() {
		return queryPageForEntityName(REVIEW);
	}
	
	public WOComponent createReviewAction() {
		return newObjectForEntityName(REVIEW);
	}
	
	// GENERIC ACTIONS
	
    public WOComponent queryPageForEntityName(String entityName) {
        QueryPageInterface newQueryPage = D2W.factory().queryPageForEntityNamed(entityName, session());
        return (WOComponent) newQueryPage;
    }
    
    public WOComponent newObjectForEntityName(String entityName) {
        WOComponent nextPage = null;
        try {
    		checkNextPage();
   		    EditPageInterface editPage = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, session());
   		    editPage.setNextPageDelegate(this);
            nextPage = (WOComponent) editPage;
        } catch (IllegalArgumentException e) {
            ErrorPageInterface epf = D2W.factory().errorPage(session());
            epf.setMessage(e.toString());
            epf.setNextPage(session().context().page());
            nextPage = (WOComponent) epf;
        }
        return nextPage;
    }
    
    // ACCESSORS
    
    public Session session() {
		return _session;
	}

    public WOContext context() {
    	WOContext context = session().context();
		return context;
	}

    public D2WComponent page() {
    	D2WComponent page = (D2WComponent) context().page();
		return page;
	}

    public D2WContext d2wContext() {
    	D2WContext context = page().d2wContext();
		return context;
	}

	public void setSession(Session s) {
		_session = s;
	}

	public void checkNextPage() {
		WOComponent page = context().page();
		
		nextPage = context().page();
		 if (page instanceof EditPageInterface || page instanceof QueryPageInterface) {
			 // we don't put query pages on the stack
			 // they are boring to cancel back to.
			 //
		 } else {
			nextPage = context().page();
		 }
	}
	
	public WOActionResults smartEditAction() {
		checkNextPage();
		EditPageInterface nextPage = D2W.factory().editPageForEntityNamed(d2wContext().entity().name(), session());
		 ERXGenericRecord object = (ERXGenericRecord) object();
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	private ERXGenericRecord object() {
		// TODO Auto-generated method stub
		return null;
	}

	public WOActionResults smartFindAction() {
		checkNextPage();
		 QueryPageInterface nextPage = D2W.factory().queryPageForEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartInspectAction(ERXGenericRecord object) {
		 InspectPageInterface nextPage = D2W.factory().inspectPageForEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	
	public WOActionResults smartCreateAction() {
		checkNextPage();
		 EditPageInterface nextPage = D2W.factory().editPageForNewObjectWithEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPage(context().page());
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	@Override
	public WOComponent nextPage(WOComponent sender) {
		 D2WPage page = (D2WPage) sender;
		 if (page instanceof QueryPageInterface) {
			 ERD2WQueryPage qp = (ERD2WQueryPage) page;
			 return qp.returnPage;
		 } else {
			 ERXGenericRecord object = (ERXGenericRecord) page.object();
			 if (!object.isNewObject()) {
				 return (WOComponent) smartInspectAction(object);
			 }
		 }

		 
		 return nextPage;
	}

	
	
	
	
	
	
	
}
