package tempLoginPackage;

import comunicazione.Client;

public class provaConfReg {
    
    public static void main(String[] args) {
        Client client = new Client();
        new RecuperoPassword(client);
    }
}