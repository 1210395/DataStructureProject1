package com.example.phase2;

public class Stack {
    // Private field
    private SingleLinkedList elements;

    // Constructor
    public Stack() {
        elements = new SingleLinkedList();
    }

    // Method to push an element onto the stack
    public void push(Object element) {
        elements.addLast(element);
    }

    // Method to pop an element from the stack
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.removeLast();
    }

    // Method to peek at the top element of the stack without removing it
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.getLast();
    }

    // Method to get the size of the stack
    public int size() {
        return elements.size();
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
