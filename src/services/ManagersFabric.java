package services;


public interface ManagersFabric {

    TaskManagerServices getDefault();


    static HistoryManager getDefaultHistory() {

        return new InMemoryHistoryManager();
    }
}

