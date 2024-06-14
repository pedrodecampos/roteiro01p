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

export default api;
