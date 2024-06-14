//package com.example.roteiro01.integration;
//
//import com.example.roteiro01.entity.Task;
//import com.example.roteiro01.entity.TaskType;
//import com.example.roteiro01.service.TaskService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskControllerTest {
//
//    @Autowired
//    private TaskService taskService;
//
//    public TaskControllerTest(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Task>> getAllTasks() {
//        List<Task> tasks = taskService.getAllTasks();
//        tasks.forEach(task -> {
//            if (task.getCompleted() == null) {
//                task.setCompleted(false);
//            }
//            if (task.isTaskTypeNull()) {
//                task.setTaskType(TaskType.DATA.ordinal());
//            }
//        });
//        return new ResponseEntity<>(tasks, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//        Task createdTask = taskService.createTask(task);
//        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//        Task updatedTask = taskService.updateTask(id, taskDetails);
//        if (updatedTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updatedTask);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//        boolean isRemoved = taskService.deleteTask(id);
//        if (!isRemoved) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/manage")
//    @Operation(summary = "Gerencie as tarefas da lista")
//    public ResponseEntity<List<Task>> gerenciarTarefas() {
//        List<Task> taskList = taskService.gerenciarTarefas();
//        if (taskList.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(taskList);
//    }
//
//    @PutMapping("/complete")
//    @Operation(summary = "Concluir tarefas da lista")
//    public ResponseEntity<Task> concluirTarefas(@RequestParam Long taskId) {
//        Task completedTask = taskService.concluirTarefa(taskId);
//        if (completedTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(completedTask);
//    }
//
//    @GetMapping("/prioritize")
//    @Operation(summary = "Priorizar tarefas da lista")
//    public ResponseEntity<List<Task>> priorizarTarefas() {
//        List<Task> taskList = taskService.priorizarTarefas();
//        if (taskList.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(taskList);
//    }
//
//    @GetMapping("/categorize")
//    @Operation(summary = "Categorizar tarefas da lista")
//    public ResponseEntity<List<Task>> categorizarTarefas() {
//        List<Task> taskList = taskService.categorizarTarefas();
//        if (taskList.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(taskList);
//    }
//}
