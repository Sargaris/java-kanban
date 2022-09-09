package services;

public class ManagerServiceFabric implements Managers {

    @Override
    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
