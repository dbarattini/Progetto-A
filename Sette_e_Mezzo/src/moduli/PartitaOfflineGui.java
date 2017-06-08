package moduli;

import dominio.classi_dati.DifficoltaBot;
import partitaOffline.controller.PartitaOfflineController;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineGuiView;


public class PartitaOfflineGui {
    private final PartitaOfflineModel model;
    private final PartitaOfflineGuiView view;
    private final PartitaOfflineController controller;

    public PartitaOfflineGui(int n_bot, DifficoltaBot difficolta_bot, int fiches_iniziali) {
        model = new PartitaOfflineModel(n_bot, difficolta_bot, fiches_iniziali);
        view = new PartitaOfflineGuiView(model);
        controller = new PartitaOfflineController(model, view);
        Runnable runnable = new Runnable(){ //il controller va lanciato su un nuovo thread altrimenti entra in conflitto con la GUI
            @Override
            public void run() {
                controller.run();
            }
        };
        
        Thread thread = new Thread(runnable);
        thread.start();
    } 
}
