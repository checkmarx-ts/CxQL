class Sample {
	
	// Positive
	public void test_creat_URI() {
		String url = request.getParameter("url");
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URI(url);
		HttpResponse httpResponse = client.execute(new HttpGet(uri));
	}
	
	// Negative
	public void test_creat_URI_1() {
		String query = request.getParameter("query");
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URI("http://", "user_info", "test.com", 443, "/dosomething", query, "#1");
		HttpResponse httpResponse = client.execute(new HttpGet(uri));
	}
	
	// Negative
	public void test_creat_URI_2() {
		String query = request.getParameter("query");
		CloseableHttpClient client = HttpClients.createDefault();
		
		URI u = new URI("http://java.sun.com/j2se/1.3");
		
		URI uri = new URI(u.getScheme(),
			u.getAuthority(),
			u.getPath(), query,
			u.getFragment());
		
		HttpResponse httpResponse = client.execute(new HttpGet(uri));
	}
	
	
	// Negative
	public void validateUserInfo(String ticket) {
		String gotoUrl = request.getParameter("url");
		URL url;

		url = new URL(gotoUrl);

		String craftUrl = String.format("%s://%s", url.getProtocol(), url.getHost());
		String validateUrl = null;

		//validateUrl = String.format("%s", craftUrl);
		validateUrl = "http://www.nobody.com";

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(validateUrl);

		HttpResponse httpResponse = client.execute(get);
	}

	// Negative
	public void test_string_add() {
		String param = request.getParameter("param");
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "http://www.someone.com?" + "key=" + param;
		HttpResponse httpResponse = client.execute(new HttpGet(url));
	}

	// Negative
	public void test_string_builder_1() {
		String param = request.getParameter("param");
		CloseableHttpClient client = HttpClients.createDefault();
		for (int i = 1; i <= 10; i++) {
			StringBuilder sb = new StringBuilder("http://www.some.com?");
			sb.append("key1=1").append("&").append("key2=").append(param);
			sb.append("something");
		}
		HttpResponse httpResponse = client.execute(new HttpGet(sb.toString()));
	}

	// Negative
	public void test_string_builder_2() {
		String param = request.getParameter("param");
		CloseableHttpClient client = HttpClients.createDefault();
		String s = "http://www.some.com?";
		StringBuilder sb = new StringBuilder(s);
		sb.append("key1=1").append("&").append("key2=").append(param);
		HttpResponse httpResponse = client.execute(new HttpGet(sb.toString()));
	}
	
	
	public void test_string_builder_3() {
		// Negative
		String param = request.getParameter("param");
		// Positive
		String s = request.getParameter("url");
		CloseableHttpClient client = HttpClients.createDefault();
		//String s = "http://www.some.com?";
		StringBuilder sb = new StringBuilder(s);
		sb.append("key1=1").append("&").append("key2=").append(param);
		HttpResponse httpResponse = client.execute(new HttpGet(sb.toString()));
	}

	// Negative
	public void test_string_builder() {
		String param = request.getParameter("param");
		CloseableHttpClient client = HttpClients.createDefault();
		StringBuilder sb = new StringBuilder("http://www.some.com");
		sb.append("/path").append("?").append("key=").append(param);
		HttpResponse httpResponse = client.execute(new HttpGet(sb.toString()));
	}

	// Negative
	public void test_string_concat() {
		CloseableHttpClient client = HttpClients.createDefault();
		String queryString = request.getParameter("queryString");
		String url3 = "http://www.some.com?".concat("one").concat(queryString);
		HttpResponse httpResponse = client.execute(new HttpGet(url3));
	}

	// Negative
	public void test_string_concat_2() {
		CloseableHttpClient client = HttpClients.createDefault();
		String queryString = request.getParameter("queryString");
		String url = "http://www.some.com?";
		String url3 = url.concat("one").concat(queryString);
		HttpResponse httpResponse = client.execute(new HttpGet(url3));
	}

	// Negative
	public void test_string_concat_3() {
		CloseableHttpClient client = HttpClients.createDefault();
		String queryString = request.getParameter("queryString");
		String url = "http://www.some.com";
		String url3 = url.concat("one?").concat(queryString);
		HttpResponse httpResponse = client.execute(new HttpGet(url3));
	}

	// Negative
	public void test_string_format() {
		CloseableHttpClient client = HttpClients.createDefault();
		String param = request.getParameter("param");
		String u = "http://www.some.net";
		String url4 = String.format("%s ?key=%s", u, param);
		HttpResponse httpResponse = client.execute(new HttpGet(url4));
	}

	// Positive
	public void test2() {
		CloseableHttpClient client = HttpClients.createDefault();
		String validateUrl = request.getParameter("url");
		HttpResponse httpResponse = client.execute(new HttpPost(validateUrl));
	}

	// Negative
	public void test3() {
		CloseableHttpClient client = HttpClients.createDefault();
		String gotoUrl = request.getParameter("url");
		HttpGet request = new HttpGet();
		String s = "https://www.googleapis.com/shopping/search/v1/public/products/?";
		request.setURI(new URI(s.concat(gotoUrl)));
		request.setUri("http://www.baidu.com/?" + gotoUrl);
		response = client.execute(request);
	}

	// Positive
	public void test4() {
		CloseableHttpClient client = HttpClients.createDefault();
		String gotoUrl = request.getParameter("url");
		HttpUrl.Builder urlBuilder = HttpUrl.parse(gotoUrl).newBuilder();
		urlBuilder.addQueryParameter("v", "1.0");
		urlBuilder.addQueryParameter("user", "vogella");
		String url = urlBuilder.build().toString();

		Request request = new Request.Builder().url(url).build();

		Response response = eagerClient.newCall(request).execute();
	}

	// Negative
	private void sendGETSync() throws IOException {
		OkHttpClient httpClient = new OkHttpClient();

		Request request = new Request.Builder().url("https://httpbin.org/get").addHeader("custom-key", "mkyong")
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response headers
			Headers responseHeaders = response.headers();
			for (int i = 0; i < responseHeaders.size(); i++) {
				System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
			}

			// Get response body
			System.out.println(response.body().string());
		}

	}

	// Positive
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("url")) {
			String url = request.getParameter("url");
			PrintWriter out = response.getWriter();
			URL u = new URL(url);
			InputStreamReader sr = new InputStreamReader(u.openConnection().getInputStream());
			BufferedReader reader = new BufferedReader(sr);
			String line = reader.readLine();
			while (line != null) {
				out.write(line);
				line = reader.readLine();
			}
		}
	}

}