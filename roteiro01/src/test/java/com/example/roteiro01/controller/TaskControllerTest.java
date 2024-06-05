package com.example.roteiro01.controller;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task();
        task1.setDescription("Task 1");

        Task task2 = new Task();
        task2.setDescription("Task 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setDescription("New Task");

        when(taskService.createTask(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("New Task", response.getBody().getDescription());
    }
}