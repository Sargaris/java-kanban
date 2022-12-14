package services;

public class ManagerServiceFabric implements Managers {

    @Override
    public TaskManager getDefault() {return new InMemoryTaskManager();}

    public static HistoryManager getDefaultHistory() {return new InMemoryHistoryManager();}
}
