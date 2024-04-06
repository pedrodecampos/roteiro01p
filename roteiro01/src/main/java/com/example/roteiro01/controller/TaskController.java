package com.example.roteiro01.controller;
import com.example.roteiro01.entity.Task;
import com.example.roteiro01.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;
    @GetMapping("/task")
        @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<List<Task>> listAll() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/gerenciar-tarefas")
    @Operation(summary = "Gerencie as tarefas da lista")
    public ResponseEntity<List<Task>> GerenciarTarefas() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }
    @GetMapping("/concluir-tarefas")
    @Operation(summary = "Concluir tarefas da lista")
    public ResponseEntity<List<Task>> ConcluirTarefa() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/priorizar-tarefas")
    @Operation(summary = "Priozar tarefas da lista")
    public ResponseEntity<List<Task>> PriorizarTarefas() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/categorizar-tarefas")
    @Operation(summary = "Categorizar tarefas da lista")
    public ResponseEntity<List<Task>> CategorizarTarefas() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }
}
