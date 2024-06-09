package com.example.roteiro01.repository;

import com.example.roteiro01.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TaskRepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void saveTask_ShouldReturnSavedTask() {
        Task task = new Task();
        task.setDescription("Test Task");

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getDescription());
    }

    @Test
    void findById_ShouldReturnTask() {
        Task task = new Task();
        task.setDescription("Test Task");
        task = taskRepository.save(task);

        Task foundTask = taskRepository.findById(task.getId()).orElse(null);

        assertNotNull(foundTask);
        assertEquals("Test Task", foundTask.getDescription());
    }

    @Test
    void findAll_ShouldReturnAllTasks() {
        Task task1 = new Task();
        task1.setDescription("Test Task 1");
        Task task2 = new Task();
        task2.setDescription("Test Task 2");
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        assertEquals(2, tasks.size());
    }
}
