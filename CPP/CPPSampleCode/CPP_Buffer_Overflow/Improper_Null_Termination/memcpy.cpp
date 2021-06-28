
// Negative
static inline char* duplicateStringValue(const char* value, size_t length) {
	
	// Avoid an integer overflow in the call to malloc below by limiting length
	// to a sane value.
	if (length >= (size_t)Value::maxInt)
		length = Value::maxInt - 1;
	
	char* newString = static_cast<char*>(malloc(length + 1));
	if (newString == NULL) {
		throwRuntimeError("some error");
	}
	memcpy(newString, value, lenght);
	newString[lenght] = 0;
	return newString;
}