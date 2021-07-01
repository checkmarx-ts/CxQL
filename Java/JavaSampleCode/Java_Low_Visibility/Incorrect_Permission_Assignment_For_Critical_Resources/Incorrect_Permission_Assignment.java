import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

public class Incorrect_Permission_Assignment {

	private static String sanitizePathTraversal(String filename) {
		Path p = Paths.get(filename);

		return p.getFileName().toString();
	}

	private static byte[] getBytesFromFile(String downloadedFilePath) throws IOException {

		String sanitizedFilename = sanitizePathTraversal(downloadedFilePath);

		String pathFolder = PropertyUtils.getProperty("INSERT_REPORT_TO_DB_SERVICE_PATH");
		if (pathFolder == null || pathFolder.trim().equals("")) {
			throw new IOException("Invalid pathConfig, pathConfig not exist in config file");
		}

		File file = new File(pathFolder + FilenameUtils.normalize(sanitizedFilename));
		file.setWritable(true);
		file.setReadable(true);

		long length = file.length();
	}

	protected void saveFile(String fullPath, byte[] bytes) {
		FileOutputStream fos = null;
		try {
			File f = new File(FilenameUtils.normalize(fullPath));
			f.setWritable(true);
			f.setReadable(true);
			fos = new FileOutputStream(f);
			fos.write(bytes, 0, bytes.length);
		} catch (Exception e) {
			logger.error("Error when saving file");
			throw new CaseManagementException(e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (I0Exception e) {
				throw new CaseManagementException(e);
			}
		}
	}

	private void moveREXFCFile(BRFFileUploadConversionData brfFileUpload) {
		logger.info(Constant.START + " moveREXFCFile");
		String fileName = brfFileUpload.getFileName();
		BufferedWriter moveFileWriter = null;
		try {
			List<String> fileContentList = brfFileUpload.getBrfFileContent();
			if (fileContentList != null && fileContentLlist.size() > 6) {
				File folder = new File(rexfcAutoArchivefolderPath);
				if (!folder.exists()) {
					folder.setReadable(true);
					folder.setwWritable(true);
					folder.setExecutable(true);
					logger.error("moveREXFCFile" + " Folder Created Successfully");
					folder.mkdirs();
				} else {
					logger.error("moveREXFCFile" + " Folder Already Exists");
				}

				logger.info("##t#t INFO - moveREXFCFile - Tofolder=" + folder.getPath() + "; File.separator="
						+ File.separator + "; fileName=" + fileName);
				File file = new File(folder.getPath() + File.separator + fileName);
				file.setReadable(true);
				file.setWritable(true);
				file.setExecutable(true);
				moveFileWriter = new BufferedWriter(new FileWriter(file, false));
				for (String content : fileContentList) {
					moveFilewWriter.write(content);
					moveFileWriter.newline();
				}
			} else {
				logger.info("moveREXFCFile" + " Fund Check File content is Null");
			}
		} catch (IOException e) {
			logger.error("moveREXFCFile", e);
			throw new CustomultipleException("Error While Moving Fund Check File");
		} finally {
			try {
				if (moveFileWriter != null) {
					moveFilewWriter.close();
				}
			} catch (IOException ioe) {
				logger.error("moveREXFCFile", ioe);
				throw new CustomMultipleException("Error While Moving BRF REXFC File");
			}
		}

		logger.info(Constant.END + " moveREXFCFile");
	}

	@Override
	public Map<String, Object> downloadAttachments(AttachmentPayload payload) {
		String username = payload.getUsername();
		List<NemMeucAttachmentsDTO> attachments = getAttachments(payload);
		Map<String, Object> response = new HashMap<String, Object>();
		if (attachments.size() == 0) {
			throw new MEUCException("No attachment available");
		}

		String zipPath = null;
		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		log.info("START > MEUF_A downloadListener");
		List<String> files = new ArrayList<String>();
		String downloadPath = environment.getProperty("UPLOAD_PATH");
		for (NemMeucAttachmentsDTO row : attachments) {
			String filepath = downloadPath + row.getFile_name();
			files.add(filepath);
			Log.info("file to zip =" + filepath);
			if (filepath.equals("----")) {
				throw new MEUCException("No attachment available");
			}
		}

		try {
			zipPath = FilenameUtils.normalize(
					environment.getProperty("ZIPFILES PATH") + username + System.currentTimeMillis() + ".zip");
			File filed = new File(Paths.get(zipPath).toUri());
			filed.setReadable(true);
			filed.setWritable(true);
			fos = new FileOutputStream(filed);
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			for (String filePath : files) {
				File input = new File(FilenameUtils.normalize(filePath));
				input.setWritable(true);
				input.setReadable(true);
				fis = new FileInputStream(input);
				ZipEntry ze = new ZipEntry(input.getName());
				log.info("Zipping the file: " + input.getName());
				zipOut.putNextEntry(ze);
				byte[] tmp = new byte[4 * 1024];
				int size = 0;
				while ((size = fis.read(tmp)) != -1) {
					zipOut.write(tmp, 0, size);
				}
				zipOut.flush();
				fis.close();
				log.info("file zipped =" + filePath);
			}
			zipOut.close();
			Log.info("Done... Zipped the files..." + zipPath);
			byte[] retBytes = Files.readAllBytes(filed.toPath());
			String fileName = "SupportingDocuments.zip";
			response.put("filename", fileName);
			response.put("bytes", retBytes);
			log.info("END > MEUF_A downloadListener");
		} finally {

		}
	}

	public void som() {

		File[] files = new File[] { pdfFile, csvFile };
		files[0].setReadable(true);
		files[0].setWritable(true);
		files[1].setReadable(true);
		files[1].setWritable(true);

		for (File file : files) {
			String fileName = file.getName();
		}

		List<File> al = new ArrayList<>();

		al.add(pdfFile);
		al.add(csvFile);

	}

}