package com.example.phase2;

import java.util.Objects;

public class Node implements Comparable<Node> {
    // Private fields
    private Object data;
    private Node nextNode;

    // Constructor
    public Node(Object data) {
        this.data = data;
    }

    // Getter for data
    public Object getData() {
        return data;
    }

    // Setter for data
    public void setData(Object data) {
        this.data = data;
    }

    // Getter for nextNode
    public Node getNextNode() {
        return nextNode;
    }

    // Setter for nextNode
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Martyr)
            return ((Martyr) this.data).getName().equalsIgnoreCase(((Martyr) o).getName());
        else if(this.data instanceof  Martyr)
            return ((Martyr) this.data).getName().equalsIgnoreCase(((Martyr)((Node) o).getData()).getName());
        else
            return false;
    }

    @Override
    public int compareTo(Node o) {
        return ((Martyr)this.data).compareTo((Martyr)o.getData());
    }
    public int compareByName(Node o) {
        return ((Martyr)this.data).getName().compareToIgnoreCase(((Martyr)o.getData()).getName());
    }
}
