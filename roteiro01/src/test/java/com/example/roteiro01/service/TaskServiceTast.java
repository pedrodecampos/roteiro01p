package com.example.roteiro01.service;


import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_ShouldSaveTaskWithDefaultType() {
        Task task = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals(TaskType.DATA.ordinal(), result.getTaskType());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void getTaskById_ShouldReturnTask_WhenTaskExists() {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getTaskById_ShouldReturnNull_WhenTaskDoesNotExist() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task result = taskService.getTaskById(1L);

        assertNull(result);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void concluirTarefa_ShouldReturnUpdatedTask_WhenTaskExists() {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.concluirTarefa(1L);

        assertNotNull(result);
       // assertTrue(result.isCompleted());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void concluirTarefa_ShouldReturnNull_WhenTaskDoesNotExist() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task result = taskService.concluirTarefa(1L);

        assertNull(result);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void priorizarTarefas_ShouldReturnTasksSortedByPriority() {
        Task task1 = new Task();
        task1.setPriorityLevel(1);
        Task task2 = new Task();
        task2.setPriorityLevel(2);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> result = taskService.priorizarTarefas();

        assertEquals(2, result.size());
        assertEquals(task2, result.get(0)); // task2 has higher priority
        assertEquals(task1, result.get(1)); // task1 has lower priority
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void categorizarTarefas_ShouldReturnTasksSortedByCategory() {
        Task task1 = new Task();
        task1.setCategory("B");
        Task task2 = new Task();
        task2.setCategory("A");
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> result = taskService.categorizarTarefas();

        assertEquals(2, result.size());
        assertEquals(task2, result.get(0)); // task2 has category "A"
        assertEquals(task1, result.get(1)); // task1 has category "B"
        verify(taskRepository, times(1)).findAll();
    }
}
