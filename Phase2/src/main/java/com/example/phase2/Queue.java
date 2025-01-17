package com.example.phase2;

public class Queue {
    // Private field
    private SingleLinkedList elements;

    // Constructor
    public Queue() {
        elements = new SingleLinkedList();
    }

    // Method to enqueue an element into the queue
    public void enqueue(Object element) {
        elements.addLast(element);
    }

    // Method to dequeue an element from the queue
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.removeFirst();
    }

    // Method to peek at the front element of the queue without removing it
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.getFirst();
    }

    // Method to get the size of the queue
    public int size() {
        return elements.size();
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
