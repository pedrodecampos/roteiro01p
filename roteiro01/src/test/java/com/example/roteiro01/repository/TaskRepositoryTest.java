package com.example.roteiro01.repository;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFindByCompleted() {
        Task task1 = new Task();
        task1.setDescription("Task 1");
        task1.setCompleted(true);
        task1.setTaskType(TaskType.DATA.ordinal());
        task1.setDueDate(LocalDate.now().plusDays(10));
        task1.setDueDays(10);
        task1.setPriorityLevel(1);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setDescription("Task 2");
        task2.setCompleted(false);
        task2.setTaskType(TaskType.DATA.ordinal());
        task2.setDueDate(LocalDate.now().plusDays(10));
        task2.setDueDays(10);
        task2.setPriorityLevel(1);
        taskRepository.save(task2);

        List<Task> completedTasks = taskRepository.findByCompleted(true);

        assertNotNull(completedTasks);
        assertEquals(1, completedTasks.size());
        assertEquals("Task 1", completedTasks.get(0).getDescription());
    }
}
