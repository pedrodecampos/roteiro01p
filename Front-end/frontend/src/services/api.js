import axios from 'axios';

// Configuração do Axios
const api = axios.create({
    baseURL: '/api', // Certifique-se de que o caminho base é '/api'
});

export const createTask = async (task) => {
    try {
        const response = await api.post('/tasks', task);
        return response.data;
    } catch (error) {
        console.error('Error creating task:', error);
        throw error;
    }
};
export const getTasks = async () => {
    const response = await api.get('/tasks');
    return response.data;
};

export const updateTask = async (id, updatedTask) => {
    const response = await api.put(`/tasks/${id}`, updatedTask);
    return response.data;
};

export const deleteTask = async (id) => {
    await api.delete(`/tasks/${id}`);
};
export default api;
