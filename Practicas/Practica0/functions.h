//Encryption functions
void encrypt (int alpha, int beta);
char * readMessage ();
void writeCiphertext (char * ciphertext);

//Decryption functions
void decrypt (int alpha, int beta);
char * readCiphertext ();
int multiplicativeInverse (int alpha);
int alg_euc_ext(int n1,int n2);

//Shared encryption/decryption functions
void menu ();
int validateNumbers (int alpha, int beta);
int gcd (int alpha, int alphabet);