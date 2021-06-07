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

}
