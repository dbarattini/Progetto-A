package dominio.gioco;

// StatoGioco serve per sapere in ogni momento in che stato si trova il gioco
// per permettere alla GUI e al controller di sapere cosa fare
public enum StatoGioco {
    menu,
    menu_pre_offline,
    menu_pre_online,
    regole,
    opzioni,
    gioco_offline,
    gioco_online
}