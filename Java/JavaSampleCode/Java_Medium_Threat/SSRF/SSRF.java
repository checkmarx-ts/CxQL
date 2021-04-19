// Negative
class SSRF {
	
	final private static String SEARCH_CONTACTS_DETAILS_URL = "/some_posix_url";
	
	public String run() {
		
		String requestParameterts = request.getParameter("param");
		
		searchContacts(requestParameterts);
		
	}
	
	
	public String searchContacts(String requestParameterts) {
		
		HttpClient httpCLientWrapper = new HttpClient();
		HttpGet httpget = getRequestSearchContacts(requestParameterts, SEARCH_CONTACTS_DETAILS_URL);
		
		clientResponsePair = httpCLientWrapper.execute(httpget);
		
	}
	
	private HttpGet getRequestSearchContacts(String requestParameterts, String postFixResource) {
		
		URIBuilder builder = new URIBuilder(env.getRequiredProperty(ENV_KEY_USER_API_URL) + postFixResource);
		
		if (requestParameterts != null) {
			
			for (Map.Entry<String, String> entry : requestParameterts.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
		}
		
		HttpGet httpget = new HttpGet(builder.build());
		
		return httpget;
	}
	
}