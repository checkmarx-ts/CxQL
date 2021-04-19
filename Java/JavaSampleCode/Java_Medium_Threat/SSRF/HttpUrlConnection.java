// Positive
class HttpUrlConnection {
	public void main(){
		String urlString = request.getParameter("url");
		MultipartEntity reqEntity = new MultipartEntity();
		String response = multipost(urlString, reqEntity);
	}
	
	private static String multipost(String urlString, MultipartEntity reqEntity) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.addRequestProperty("Content-length", reqEntity.getContentLength()+"");
			conn.addRequestProperty(reqEntity.getContentType().getName(), reqEntity.getContentType().getValue());

			OutputStream os = conn.getOutputStream();
			reqEntity.writeTo(conn.getOutputStream());
			os.close();
			conn.connect();

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readStream(conn.getInputStream());
			}

		} catch (Exception e) {
			Log.e(TAG, "multipart post error " + e + "(" + urlString + ")");
		}
		return null;        
	}

	private static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	} 
	
}