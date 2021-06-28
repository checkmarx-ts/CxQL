// Positive
void test () {
	
	fseek(fp, 0, SEEK_END);
	int size = ftell(fp);
	rewind(fp);
	int readsize = 0;
	char* ar = nullptr;
	
	if (size > 0) {
		ar = (char*)malloc(sizeof(char) * size);
		if (ar != nullptr) {
			readsize = fread(ar, 1, size, fp);
		}
	}
	
	if (ar != nullptr) {
		string base = CefBase64Encode(ar, size);
		strres += base;
		free(ar);
	}

	string iconbase64 = strres;

	strcpy(node->iconpath, iconbase64.c_str());
	
	fclose(fp);
}