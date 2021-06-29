public class saveMultipartFile {
    // Positive
    public void saveMultipartFilePositiveVulnerable(CommonsMultipartFile multipartFile, String path)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(multipartFile.getBytes());
        fos.close();
    }

    // Negative
    public void saveMultipartFileNegativeVulnerable(CommonsMultipartFile multipartFile, String path)
            throws IOException {
        if (multipartFile.getSize() < MAX_SIZE) {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(multipartFile.getBytes());
            fos.close();
        } else {
            throw new IOException("Maximum file size exceeded!");
        }
    }

    // Negative
    private BAFFileUploadData setBafFileUploadData(BAFFileData payload) {
        String description = payload.getDescription();
        MultipartFile uploaded_file = payload.getUploaded_file();
        long maxSize = 2097152;
        if (uploaded_file.getSize() > maxSize) {
            throw new CustomMultipleException(bafFileSizeLimitExceed);
        }
        BAFFileUploadData bafFileUploadData = new BAFFileUploadData();
        bafFileUploadData.setDescription(description);
        bafFileUploadData.setContent_type(uploaded_file.getContentType());
        bafFileUploadData.setFile_name(uploaded_file.getriginalFilename());
        bafFileUploadData.setFilesize(uploaded_file.getSize());
        try {
            bafFileUploadData.setFiles_input_stream(uploaded_file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return baffileUploadData;
    }

    // Negative
    @RequestMapping(value = "uploadBRFFileData", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> uploadBRFFileData(HttpServletRequest request,
            @RequestParam("purpose description") String purposeDescription,
            @RequestParam("files") MultipartFile[] files, @RequestParam("description") String description) {
        long maxSize = 2097152;
        for (MultipartFile file : files) {
            if (file.getSize() > maxSize) {
                throw new CustomMultipleException(environment.getProperty("BRF ADI FILE SIZE LIMIT EXCEED"));
            }
        }
        return serviceRequestHandler(() -> invoiceService.uploadBRFFileData(purposeDescription, files, description,
                retrieveEmcUser(request)));
    }

    // Negative
    @RequestMapping(value = "insertBAFRecord", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> insertBAFRecord(HttpServletRequest request,
            @RequestParam("uploaded_file") MultipartFile uploaded_file,
            @RequestParam("description") String description) {
        RequestContextDTo reauestContext = RequestContextUtil.getRequestContext(request);
        long maxSize = 2097152;
        if (uploaded_file.getsize() > maxSize) {
            throw new CustomMultipleException(environment.getProperty("BAF_FILE_SIZE_LIMIT_EXCEED"));
        }
        BAFFileData payload = new BAFFileData();
        payload.setUploaded_file(uploaded_file);
        payload.setDescription(description);
        return serviceRequestHandler(() -> invoiceService.uploadBAFFileData(payload, requestContext));
    }
}
