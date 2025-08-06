package com.example.practice.item;

public class TodoItem {
    private String todoName;

    public TodoItem(String name){
        this.todoName = name;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }
}
