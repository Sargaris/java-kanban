package services;

public class ManagerServiceFabric implements ManagersFabric{

    @Override
    public TaskManagerServices getDefault() {
        return new InMemoryTaskManager();
    }
}
