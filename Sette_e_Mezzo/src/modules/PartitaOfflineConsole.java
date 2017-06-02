package modules;

import dominio.classi_dati.DifficoltaBot;
import partitaOffline.controller.PartitaOfflineController;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineConsoleView;


public class PartitaOfflineConsole {
    private PartitaOfflineModel model;
    private PartitaOfflineConsoleView view;
    private PartitaOfflineController controller;

    public PartitaOfflineConsole(int n_bot, DifficoltaBot difficolta_bot, int fiches_iniziali) {
        model = new PartitaOfflineModel(n_bot, difficolta_bot, fiches_iniziali);
        view = new PartitaOfflineConsoleView(model);
        controller = new PartitaOfflineController(model, view);
        
        controller.run();
    } 
}
