package services;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public ArrayList<Task> lastTenActions = new ArrayList<>();


    @Override
    public ArrayList<Task> getHistory() {

        return lastTenActions;
    }


    @Override
    public void add(Task task) {
        lastTenActions.add(task);
        if (lastTenActions.size() > 10) {
            lastTenActions.remove(0);
        }
    }
}
