package com.example.roteiro01;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import com.example.roteiro01.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Test Task\",\"completed\":false,\"taskType\":0,\"dueDate\":\"" + LocalDate.now().plusDays(10) + "\",\"dueDays\":10,\"priorityLevel\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is("Test Task")))
                .andExpect(jsonPath("$.completed", is(false)))
                .andExpect(jsonPath("$.taskType", is(0)))
                .andExpect(jsonPath("$.dueDate", is(LocalDate.now().plusDays(10).toString())))
                .andExpect(jsonPath("$.dueDays", is(10)))
                .andExpect(jsonPath("$.priorityLevel", is(1)));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task1 = new Task();
        task1.setDescription("Task 1");
        task1.setCompleted(false);
        task1.setTaskType(TaskType.DATA.ordinal());
        task1.setDueDate(LocalDate.now().plusDays(10));
        task1.setDueDays(10);
        task1.setPriorityLevel(1);
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setDescription("Task 2");
        task2.setCompleted(false);
        task2.setTaskType(TaskType.PRAZO.ordinal());
        task2.setDueDate(LocalDate.now().plusDays(5));
        task2.setDueDays(5);
        task2.setPriorityLevel(2);
        taskService.createTask(task2);

        mockMvc.perform(MockMvcRequestBuilders.get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].description", is("Task 1")))
                .andExpect(jsonPath("$[1].description", is("Task 2")));
    }

    @Test
    public void testConcluirTarefas() throws Exception {
        Task task1 = new Task();
        task1.setDescription("Task 1");
        task1.setCompleted(true);
        task1.setTaskType(TaskType.DATA.ordinal());
        task1.setDueDate(LocalDate.now().plusDays(10));
        task1.setDueDays(10);
        task1.setPriorityLevel(1);
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setDescription("Task 2");
        task2.setCompleted(true);
        task2.setTaskType(TaskType.PRAZO.ordinal());
        task2.setDueDate(LocalDate.now().plusDays(5));
        task2.setDueDays(5);
        task2.setPriorityLevel(2);
        taskService.createTask(task2);

        mockMvc.perform(MockMvcRequestBuilders.get("/concluir-tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].description", is("Task 1")))
                .andExpect(jsonPath("$[1].description", is("Task 2")));
    }

    @Test
    public void testPriorizarTarefas() throws Exception {
        Task task1 = new Task();
        task1.setDescription("High Priority Task");
        task1.setCompleted(false);
        task1.setTaskType(TaskType.DATA.ordinal());
        task1.setDueDate(LocalDate.now().plusDays(10));
        task1.setDueDays(10);
        task1.setPriorityLevel(1);
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setDescription("Low Priority Task");
        task2.setCompleted(false);
        task2.setTaskType(TaskType.PRAZO.ordinal());
        task2.setDueDate(LocalDate.now().plusDays(5));
        task2.setDueDays(5);
        task2.setPriorityLevel(3);
        taskService.createTask(task2);

        mockMvc.perform(MockMvcRequestBuilders.get("/priorizar-tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].description", is("High Priority Task")))
                .andExpect(jsonPath("$[1].description", is("Low Priority Task")));
    }

    @Test
    public void testCategorizarTarefas() throws Exception {
        Task task1 = new Task();
        task1.setDescription("Work Task");
        task1.setCompleted(false);
        task1.setTaskType(TaskType.DATA.ordinal());
        task1.setDueDate(LocalDate.now().plusDays(10));
        task1.setDueDays(10);
        task1.setPriorityLevel(1);
        task1.setCategory("work");
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setDescription("Home Task");
        task2.setCompleted(false);
        task2.setTaskType(TaskType.PRAZO.ordinal());
        task2.setDueDate(LocalDate.now().plusDays(5));
        task2.setDueDays(5);
        task2.setPriorityLevel(2);
        task2.setCategory("home");
        taskService.createTask(task2);

        mockMvc.perform(MockMvcRequestBuilders.get("/categorizar-tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].description", is("Work Task")))
                .andExpect(jsonPath("$[1].description", is("Home Task")));
    }
}
