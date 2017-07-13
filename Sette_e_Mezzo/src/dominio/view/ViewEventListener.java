package dominio.view;


public interface ViewEventListener {
    
    /**
     * Gestisce il verificarsi di un evento nella vista.
     * 
     * @param evt evento generato nella vista
     */
    public void ViewEventReceived(ViewEvent evt);
    
}
