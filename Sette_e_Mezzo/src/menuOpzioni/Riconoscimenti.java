package menuOpzioni;

import dominio.classi_dati.Banners;
import java.util.Scanner;


public class Riconoscimenti {
    Banners banner;
    
    private String dbarattini = 
"            _           _     _   _                   _   _   _      _ \n" +
"         __| |__ _ _ _ (_)___| | | |__  __ _ _ _ __ _| |_| |_(_)_ _ (_)\n" +
"        / _` / _` | ' \\| / -_) | | '_ \\/ _` | '_/ _` |  _|  _| | ' \\| |\n" +
"        \\__,_\\__,_|_||_|_\\___|_| |_.__/\\__,_|_| \\__,_|\\__|\\__|_|_||_|_|\n" +
"                                                                       ";
    private String mprina =
"                                                   _           \n" +
"                  _ __  __ _ _ _ __ ___   _ __ _ _(_)_ _  __ _ \n" +
"                 | '  \\/ _` | '_/ _/ _ \\ | '_ \\ '_| | ' \\/ _` |\n" +
"                 |_|_|_\\__,_|_| \\__\\___/ | .__/_| |_|_||_\\__,_|\n" +
"                                         |_|                   ";
    private String mguido =
"                                                    _    _     \n" +
"                  _ __  __ _ _ _ __ ___   __ _ _  _(_)__| |___ \n" +
"                 | '  \\/ _` | '_/ _/ _ \\ / _` | || | / _` / _ \\\n" +
"                 |_|_|_\\__,_|_| \\__\\___/ \\__, |\\_,_|_\\__,_\\___/\n" +
"                                         |___/                 ";
    private String mbergamaschi =
"                                        _   _          \n" +
"                         _ __  __ _ _ _| |_(_)_ _  ___ \n" +
"                        | '  \\/ _` | '_|  _| | ' \\/ _ \\\n" +
"                        |_|_|_\\__,_|_|  \\__|_|_||_\\___/\n" +
"                                                       \n" +
"                _                                         _    _ \n" +
"               | |__  ___ _ _ __ _ __ _ _ __  __ _ ___ __| |_ (_)\n" +
"               | '_ \\/ -_) '_/ _` / _` | '  \\/ _` (_-</ _| ' \\| |\n" +
"               |_.__/\\___|_| \\__, \\__,_|_|_|_\\__,_/__/\\__|_||_|_|\n" +
"                             |___/                               ";
    private String smbaye = 
"                            _                  _                   \n" +
"             ___ __ _ _ __ | |__  __ _   _ __ | |__  __ _ _  _ ___ \n" +
"            (_-</ _` | '  \\| '_ \\/ _` | | '  \\| '_ \\/ _` | || / -_)\n" +
"            /__/\\__,_|_|_|_|_.__/\\__,_| |_|_|_|_.__/\\__,_|\\_, \\___|\n" +
"                                                          |__/     ";
    private String scolmi=
"                                       _              _       _ \n" +
"                ___ __ _ _ __ _  _ ___| |___   __ ___| |_ __ (_)\n" +
"               (_-</ _` | '  \\ || / -_) / -_) / _/ _ \\ | '  \\| |\n" +
"               /__/\\__,_|_|_|_\\_,_\\___|_\\___| \\__\\___/_|_|_|_|_|\n" +
"";                                                                

    public Riconoscimenti() {
        banner = new Banners();
    }
    
    public void printRiconoscimenti() {
        System.out.println(banner.randomBanner());
        System.out.println(dbarattini);
        System.out.println(mprina);
        System.out.println(mguido);
        System.out.println(mbergamaschi);
        System.out.println(smbaye);
        System.out.println(scolmi);
        Scanner scanner = new Scanner(System.in);
        String continua = scanner.nextLine();
        System.out.print("\n");
    }
}
