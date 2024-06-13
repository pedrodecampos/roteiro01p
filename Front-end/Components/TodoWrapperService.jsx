import React, { useState, useEffect } from 'react';

export const TodoWrapperService = () => {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        const savedTodos = JSON.parse(localStorage.getItem('todos')) || [];
        setTodos(savedTodos);
    }, []);

    const addTodo = (todo) => {
        const newTodo = { id: uuidv4(), description: todo, completed: false };
        setTodos([...todos, newTodo]);
        localStorage.setItem('todos', JSON.stringify([...todos, newTodo]));
    }

    return (
        <div className='TodoWrapper'>
            <h1>Lista de Tarefas! (Service)</h1>
            <TodoForm addTodo={addTodo} /> {/* Passando a função addTodo como propriedade */}
            {todos.map((todo, index) => (
                <TodoList task={todo} key={index} />
            ))}
        </div>
    );
}