//package com.example.task.queue;
//
//import com.example.task.MockFetchingService;
//import com.example.task.factory.TaskCommand;
//import com.example.task.processor.TaskProcessor;
//
//public class TaskDispatcher {
//    private final TaskQueueManager taskQueueManager;
//    private final MockFetchingService fetchingService;
//
//    public TaskDispatcher(TaskQueueManager taskQueueManager, MockFetchingService fetchingService, TaskProcessor taskProcessor) {
//        this.taskQueueManager = taskQueueManager;
//        this.fetchingService = fetchingService;
//    }
//
//    public void dispatch() {
//        while (!taskQueueManager.isQueueEmpty()) {
//            TaskCommand task = taskQueueManager.getFromQueue();
//            if (task != null) {
//                fetchingService.fetchData(task.toServiceRequest());
//                System.out.println("Dispatched task: " + task);
//            }
//        }
//    }
//}