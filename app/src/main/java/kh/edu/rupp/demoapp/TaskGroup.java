package kh.edu.rupp.demoapp;

public class TaskGroup {
    private String name;
    private int taskCount;
    private int progress;

    public class Task {
        private int userId;
        private int id;
        private String title;
        private boolean completed;

        public String getTitle() { return title; }
        public boolean isCompleted() { return completed; }
    }


    // Getters
    public String getName() { return name; }
    public int getTaskCount() { return taskCount; }
    public int getProgress() { return progress; }
}