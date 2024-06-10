//
//import com.example.roteiro01.entity.Task;
//import com.example.roteiro01.repository.TaskRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TaskControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void createTask_ShouldReturnCreatedTask() throws Exception {
//        Task task = new Task();
//        task.setDescription("Test Task");
//
//        mockMvc.perform(post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(task)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.description").value("Test Task"));
//    }
//
//    @Test
//    void getTaskById_ShouldReturnTask() throws Exception {
//        Task task = new Task();
//        task.setDescription("Test Task");
//        task = taskRepository.save(task);
//
//        mockMvc.perform(get("/tasks/{id}", task.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.description").value("Test Task"));
//    }
//
//    @Test
//    void getAllTasks_ShouldReturnAllTasks() throws Exception {
//        Task task1 = new Task();
//        task1.setDescription("Test Task 1");
//        Task task2 = new Task();
//        task2.setDescription("Test Task 2");
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//
//        mockMvc.perform(get("/tasks"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    void concluirTarefa_ShouldReturnUpdatedTask() throws Exception {
//        Task task = new Task();
//        task.setDescription("Test Task");
//        task = taskRepository.save(task);
//
//        mockMvc.perform(put("/tasks/concluir/{id}", task.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.completed").value(true));
//    }
//}
