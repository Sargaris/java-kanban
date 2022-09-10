package services;


public interface Managers {

    TaskManager getDefault();
    static HistoryManager getDefaultHistory();

}

