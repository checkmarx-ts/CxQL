class absolutePathTraversal {
	
	// Negative
	public void test_1() {
		
		String runId = request.getParameter("runId");
		
		String fileName = environment.getProperty("SOME_THING") + File.separator + filePrefix;
		
		fileName += FilenameUtils.normalize(runId) + "." + fileExtention;
		
		File file = new File(fileName);
		
	}
	
}