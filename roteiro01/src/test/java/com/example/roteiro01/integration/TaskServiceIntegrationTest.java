package com.example.roteiro01.service;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void createTask_ShouldSaveTask() {
        Task task = new Task();
        task.setDescription("Test Task");

        Task savedTask = taskService.createTask(task);

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getDescription());
    }

    @Test
    void getTaskById_ShouldReturnTask() {
        Task task = new Task();
        task.setDescription("Test Task");
        task = taskRepository.save(task);

        Task foundTask = taskService.getTaskById(task.getId());

        assertNotNull(foundTask);
        assertEquals("Test Task", foundTask.getDescription());
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        Task task1 = new Task();
        task1.setDescription("Test Task 1");
        Task task2 = new Task();
        task2.setDescription("Test Task 2");
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
    }

    @Test
    void concluirTarefa_ShouldUpdateTask() {
        Task task = new Task();
        task.setDescription("Test Task");
        task = taskRepository.save(task);

        Task updatedTask = taskService.concluirTarefa(task.getId());

        assertNotNull(updatedTask);
        //assertTrue(updatedTask.isCompleted());
    }
}
