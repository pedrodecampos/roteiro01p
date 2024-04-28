package com.example.roteiro01.service;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        // Verifica se o tipo de tarefa está definido
        if (task.getTaskType() == null) {
            task.setTaskType(TaskType.DATA.ordinal()); // Por exemplo, definindo como tipo "Data"
        }
        // Salva a tarefa no banco de dados
        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        // Recupera a tarefa do banco de dados
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Métodos abaixo precisam ser implementados
    public List<Task> gerenciarTarefas() {
        return taskRepository.findAll();
    }

    public Task concluirTarefa(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true); // Define a tarefa como concluída
            return taskRepository.save(task); // Salva e retorna a tarefa atualizada
        }
        return null; // Retorna null se a tarefa não for encontrada
    }

    public List<Task> priorizarTarefas() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getPriorityLevel() != null)
                .sorted(Comparator.comparingInt(Task::getPriorityLevel).reversed())
                .collect(Collectors.toList());
    }

    public List<Task> categorizarTarefas() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getCategory() != null)
                .sorted(Comparator.comparing(Task::getCategory))
                .collect(Collectors.toList());
    }
}
