import React, { useState, useEffect } from 'react';
import { createTask, getTasks, updateTask, deleteTask } from './services/api';

function App() {
    const [taskName, setTaskName] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [tasks, setTasks] = useState([]);
    const [successMessage, setSuccessMessage] = useState('');
    const [editMode, setEditMode] = useState(false);
    const [taskIdToEdit, setTaskIdToEdit] = useState(null);

    const fetchTasks = async () => {
        try {
            const fetchedTasks = await getTasks();
            setTasks(fetchedTasks);
        } catch (error) {
            console.error('Failed to fetch tasks:', error);
        }
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    const handleCreateTask = async () => {
        if (editMode) {
            handleUpdateTask();
        } else {
            try {
                await createTask({ name: taskName, description: taskDescription });
                setTaskName('');
                setTaskDescription('');
                setSuccessMessage('Tarefa criada com sucesso');
                setTimeout(() => {
                    setSuccessMessage('');
                }, 3000);
                fetchTasks();
            } catch (error) {
                console.error('Failed to create task:', error);
            }
        }
    };

    const handleUpdateTask = async () => {
        try {
            await updateTask(taskIdToEdit, { name: taskName, description: taskDescription });
            setTaskName('');
            setTaskDescription('');
            setEditMode(false);
            setTaskIdToEdit(null);
            setSuccessMessage('Tarefa atualizada com sucesso');
            setTimeout(() => {
                setSuccessMessage('');
            }, 3000);
            fetchTasks();
        } catch (error) {
            console.error('Failed to update task:', error);
        }
    };

    const handleEditTask = (task) => {
        setTaskName(task.name);
        setTaskDescription(task.description);
        setEditMode(true);
        setTaskIdToEdit(task.id);
    };

    const handleDeleteTask = async (id) => {
        try {
            await deleteTask(id);
            setSuccessMessage('Tarefa excluída com sucesso');
            setTimeout(() => {
                setSuccessMessage('');
            }, 3000);
            fetchTasks();
        } catch (error) {
            console.error('Failed to delete task:', error);
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.formContainer}>
                <h2 style={styles.title}>{editMode ? 'Editar Tarefa' : 'Criar Tarefa'}</h2>
                <input
                    type="text"
                    value={taskName}
                    onChange={(e) => setTaskName(e.target.value)}
                    placeholder="Nome da tarefa"
                    style={styles.input}
                />
                <textarea
                    value={taskDescription}
                    onChange={(e) => setTaskDescription(e.target.value)}
                    placeholder="Descrição da tarefa"
                    rows={4}
                    style={styles.textarea}
                />
                <button onClick={handleCreateTask} style={styles.button}>
                    {editMode ? 'Atualizar Tarefa' : 'Criar Tarefa'}
                </button>
                {successMessage && (
                    <p style={styles.successMessage}>{successMessage}</p>
                )}
                <ul style={styles.taskList}>
                    {tasks.map(task => (
                        <li key={task.id} style={styles.taskItem}>
                            <div>
                                <strong>{task.name}</strong>: {task.description}
                            </div>
                            <div>
                                <button onClick={() => handleEditTask(task)} style={styles.editButton}>
                                    ✏️
                                </button>
                                <button onClick={() => handleDeleteTask(task.id)} style={styles.deleteButton}>
                                    ❌
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        backgroundColor: '#1e1e1e',
        color: '#f1f1f1',
    },
    formContainer: {
        width: '400px',
        padding: '20px',
        borderRadius: '10px',
        boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
        backgroundColor: '#2e2e2e',
    },
    title: {
        textAlign: 'center',
        marginBottom: '20px',
    },
    input: {
        width: '100%',
        marginBottom: '10px',
        padding: '10px',
        borderRadius: '5px',
        border: '1px solid #555',
        backgroundColor: '#3e3e3e',
        color: '#f1f1f1',
    },
    textarea: {
        width: '100%',
        marginBottom: '10px',
        padding: '10px',
        borderRadius: '5px',
        border: '1px solid #555',
        backgroundColor: '#3e3e3e',
        color: '#f1f1f1',
    },
    button: {
        width: '100%',
        padding: '10px',
        borderRadius: '5px',
        border: 'none',
        backgroundColor: '#007bff',
        color: '#fff',
        cursor: 'pointer',
    },
    successMessage: {
        color: 'green',
        marginTop: '10px',
        textAlign: 'center',
    },
    taskList: {
        marginTop: '20px',
        padding: '0',
        listStyleType: 'none',
    },
    taskItem: {
        marginBottom: '10px',
        padding: '10px',
        backgroundColor: '#4e4e4e',
        borderRadius: '5px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        border: 'none',  // Removendo qualquer borda extra
    },
    editButton: {
        marginRight: '10px',
        padding: '5px',
        backgroundColor: '#ffc107',
        color: '#fff',
        border: 'none',
        borderRadius: '3px',
        cursor: 'pointer',
    },
    deleteButton: {
        padding: '5px',
        backgroundColor: '#dc3545',
        color: '#fff',
        border: 'none',
        borderRadius: '3px',
        cursor: 'pointer',
    },
};

export default App;
