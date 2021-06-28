// Negative
int main () { 
    char command[2048]{}; 
	cin.width(2048); 
	while (true) {
		cin >> command;
		if (checkExit(command)) {
			break;
		}
	}	
	return 0; 
}