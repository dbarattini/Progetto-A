package tempLoginPackage;

import comunicazione.Client;

public class provaRecuperoPw {
    
    public static void main(String[] args) {
        Client client = new Client();
        new RecuperoPassword(client);
    }
}