// service/api.js

import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tasks';

const getAllTasks = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Failed to fetch tasks:', error);
        throw error;
    }
};

const createTask = async (task) => {
    try {
        const response = await axios.post(API_URL, task);
        return response.data; // Retorna os dados da nova tarefa criada
    } catch (error) {
        console.error('Failed to create task:', error);
        throw error;
    }
};

const updateTask = async (id, task) => {
    try {
        const response = await axios.put(`${API_URL}/${id}`, task);
        return response.data; // Retorna os dados da tarefa atualizada
    } catch (error) {
        console.error(`Failed to update task with ID ${id}:`, error);
        throw error;
    }
};

const deleteTask = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data; // Retorna os dados da tarefa excluída (opcional)
    } catch (error) {
        console.error(`Failed to delete task with ID ${id}:`, error);
        throw error;
    }
};

export { getAllTasks, createTask, updateTask, deleteTask };
