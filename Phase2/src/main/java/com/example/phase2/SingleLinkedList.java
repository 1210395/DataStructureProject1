package com.example.phase2;

public class SingleLinkedList {
	// Private fields
	private Node first, last;
	private int count = 0;

	// Constructor
	public SingleLinkedList() {
	}

	// Getter for the first node
	public Node getFirstNode() {
		return first;
	}

	// Method to get the node at the specified index
	public Node get(int index) {
		if (index < 0 || index >= count) {
			return null; // Index out of bounds
		}
		Node current = first;
		for (int i = 0; i < index; i++) {
			current = current.getNextNode();
		}
		return current; // Return the node at the specified index
	}

	// Method to find a node with a specific element
	public Node find(Object element) {
		Node current = first;
		while (current != null) {
			if (current.getData().equals(element)) {
				return current;
			}
			current = current.getNextNode();
		}
		return null; // Element not found
	}

	// Method to add an element in a sorted manner
	public void addSorted(Object element) {
		Node newNode = new Node(element);
		if (count == 0 || newNode.compareTo(first) <= 0) {
			addFirst(element); // Add to the beginning if the list is empty or new element is smaller than the first
			return;
		}
		if (newNode.compareTo(last) >= 0) {
			addLast(element); // Add to the end if new element is greater than the last
			return;
		}

		Node current = first;
		while (current.getNextNode() != null && newNode.compareTo(current.getNextNode()) > 0) {
			current = current.getNextNode(); // Move to the next node until finding the right position
		}
		newNode.setNextNode(current.getNextNode());
		current.setNextNode(newNode);
		count++;
	}
	public void addSortedName(Object element) {
		Node newNode = new Node(element);
		if (count == 0 || newNode.compareByName(first) <= 0) {
			addFirst(element); // Add to the beginning if the list is empty or new element is smaller than the first
			return;
		}
		if (newNode.compareByName(last) >= 0) {
			addLast(element); // Add to the end if new element is greater than the last
			return;
		}

		Node current = first;
		while (current.getNextNode() != null && newNode.compareByName(current.getNextNode()) > 0) {
			current = current.getNextNode(); // Move to the next node until finding the right position
		}
		newNode.setNextNode(current.getNextNode());
		current.setNextNode(newNode);
		count++;
	}
	// Method to add an element at the beginning of the list
	public void addFirst(Object element) {
		if (first == null) {
			first = last = new Node(element);
		} else {
			Node temp = new Node(element);
			temp.setNextNode(first);
			first = temp;
		}
		count++;
	}

	// Method to add an element at the end of the list
	public void addLast(Object element) {
		if (count == 0) {
			first = last = new Node(element);
		} else {
			Node temp = new Node(element);
			last.setNextNode(temp);
			last = temp;
		}
		count++;
	}

	// Method to remove the first element of the list
	public Object removeFirst() {
		if (count == 0) {
			return null;
		}
		Node temp = first;
		if (count == 1) {
			first = last = null;
		} else {
			first = first.getNextNode();
		}
		count--;
		return temp.getData();
	}

	// Method to remove the last element of the list
	public Object removeLast() {
		if (count == 0) {
			return null;
		}
		Node current = first;
		Node temp = last;
		if (count == 1) {
			first = last = null;
		} else {
			while (current.getNextNode() != last) {
				current = current.getNextNode();
			}
			last = current;
			last.setNextNode(null);
		}
		count--;
		return temp.getData();
	}

	// Method to remove the element at a specific index
	public Object remove(int index) {
		if (index < 0 || index >= count) {
			return false;
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == count - 1) {
			return removeLast();
		}
		Node current = first;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNextNode();
		}
		Node temp = current.getNextNode();
		current.setNextNode(temp.getNextNode());
		count--;
		return temp.getData();
	}

	// Method to remove a specific element from the list
	public boolean remove(Object element) {
		if (count == 0) {
			return false;
		}
		if (first.getData().equals(element)) {
			removeFirst();
			return true;
		}
		Node current = first;
		while (current.getNextNode() != null && !current.getNextNode().getData().equals(element)) {
			current = current.getNextNode();
		}
		if (current.getNextNode() == null) {
			return false;
		}
		current.setNextNode(current.getNextNode().getNextNode());
		count--;
		return true;
	}

	// Method to get the first element of the list
	public Object getFirst() {
		return (first != null) ? first.getData() : null;
	}

	// Method to get the last element of the list
	public Object getLast() {
		return (last != null) ? last.getData() : null;
	}

	// Method to get the size of the list
	public int size() {
		return count;
	}

	// Method to check if the list is empty
	public boolean isEmpty() {
		return count == 0;
	}

	// Method to print the list
	public String printList() {
		StringBuilder stringBuilder = new StringBuilder();
		Node current = first;
		while (current != null) {
			stringBuilder.append(current.getData()).append("\n");
			current = current.getNextNode();
		}
		return stringBuilder.toString();
	}
}
