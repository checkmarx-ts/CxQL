public class logForging {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
		String color = request.getParameter("color");
		
		if (color != null) cleanColor = color.replace('\t', '_').replace('\n', '_').replace('\r', '_');
		
		
		logger.info("{} was picked", cleanColor);
		if colorList.contains(cleanColor){
			// Handle Response
		}else{
			// Handle Response
		}
	}
	
}