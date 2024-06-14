//package com.example.roteiro01.controller;
//
//import com.example.roteiro01.entity.Task;
//import com.example.roteiro01.service.TaskService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(TaskController.class)
//@AutoConfigureMockMvc
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TaskService taskService;
//
//    @Test
//    public void testGetAllTasks() throws Exception {
//        Task task1 = new Task(1L, "Task 1", "Description 1");
//        Task task2 = new Task(2L, "Task 2", "Description 2");
//
//        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
//    }
//
//    @Test
//    public void testCreateTask() throws Exception {
//        Task task = new Task(1L, "Task 1", "Description 1");
//
//        when(taskService.createTask(any(Task.class))).thenReturn(task);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
//                        .content("{\"name\": \"Task 1\", \"description\": \"Description 1\"}")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Task 1"));
//    }
//
//    @Test
//    public void testUpdateTask() throws Exception {
//        Task task = new Task(1L, "Updated Task", "Updated Description");
//
//        when(taskService.updateTask(any(Long.class), any(Task.class))).thenReturn(task);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/{id}", 1L)
//                        .content("{\"name\": \"Updated Task\", \"description\": \"Updated Description\"}")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Task"));
//    }
//
//    @Test
//    public void testDeleteTask() throws Exception {
//        when(taskService.deleteTask(any(Long.class))).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/{id}", 1L))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//    @Test
//    public void testGerenciarTarefas() throws Exception {
//        Task task1 = new Task(1L, "Task 1", "Description 1");
//        Task task2 = new Task(2L, "Task 2", "Description 2");
//
//        when(taskService.gerenciarTarefas()).thenReturn(Arrays.asList(task1, task2));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/manage")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
//    }
//
//    @Test
//    public void testConcluirTarefas() throws Exception {
//        Task task = new Task(1L, "Task 1", "Description 1");
//        task.setCompleted(true);
//
//        when(taskService.concluirTarefa(any(Long.class))).thenReturn(task);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/complete")
//                        .param("taskId", "1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(true));
//    }
//
//    @Test
//    public void testPriorizarTarefas() throws Exception {
//        Task task1 = new Task(1L, "Task 1", "Description 1");
//        Task task2 = new Task(2L, "Task 2", "Description 2");
//
//        when(taskService.priorizarTarefas()).thenReturn(Arrays.asList(task2, task1)); // reversed order
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/prioritize")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Task 2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Task 1"));
//    }
//
//    @Test
//    public void testCategorizarTarefas() throws Exception {
//        Task task1 = new Task(1L, "Task 1", "Description 1");
//        Task task2 = new Task(2L, "Task 2", "Description 2");
//
//        when(taskService.categorizarTarefas()).thenReturn(Arrays.asList(task1, task2));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/categorize")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
//    }
//}
