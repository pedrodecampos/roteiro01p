import React, { useState } from 'react';
import { createTask } from './service/api'; // Certifique-se de que o caminho está correto

function App() {
    const [taskName, setTaskName] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [tasks, setTasks] = useState([]);
    const [successMessage, setSuccessMessage] = useState('');

    const handleCreateTask = async () => {
        try {
            const newTask = await createTask({ name: taskName, description: taskDescription });
            setTasks([...tasks, newTask]);
            setTaskName(''); // Limpa o campo de nome da tarefa
            setTaskDescription(''); // Limpa o campo de descrição da tarefa
            setSuccessMessage('Tarefa criada com sucesso');
            setTimeout(() => {
                setSuccessMessage('');
            }, 3000); // Limpa a mensagem após 3 segundos
        } catch (error) {
            console.error('Failed to create task:', error);
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <div style={{ width: '400px', padding: '20px', border: '1px solid #ccc', borderRadius: '5px', boxShadow: '0 2px 5px rgba(0,0,0,0.1)' }}>
                <h2>Create Task</h2>
                <input
                    type="text"
                    value={taskName}
                    onChange={(e) => setTaskName(e.target.value)}
                    placeholder="Task name"
                    style={{ width: '100%', marginBottom: '10px', padding: '8px' }}
                />
                <textarea
                    value={taskDescription}
                    onChange={(e) => setTaskDescription(e.target.value)}
                    placeholder="Task description"
                    rows={4}
                    style={{ width: '100%', marginBottom: '10px', padding: '8px' }}
                />
                <button onClick={handleCreateTask} style={{ width: '100%', padding: '10px', backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: '3px', cursor: 'pointer' }}>
                    Create Task
                </button>
                {successMessage && (
                    <p style={{ color: 'green', marginTop: '10px' }}>{successMessage}</p>
                )}
                <ul style={{ marginTop: '20px', padding: '0', listStyleType: 'none' }}>
                    {tasks.map(task => (
                        <li key={task.id} style={{ marginBottom: '10px', padding: '10px', backgroundColor: '#f0f0f0', borderRadius: '3px' }}>
                            <strong>{task.name}</strong>: {task.description}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default App;
