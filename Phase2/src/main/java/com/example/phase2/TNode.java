package com.example.phase2;

public class TNode implements Comparable<TNode> {
    // Private fields
    private TNode leftNode, rightNode;
    private Object data;

    // Constructor
    public TNode(Object data) {
        this.data = data;
    }

    // Getter for leftNode
    public TNode getLeftNode() {
        return leftNode;
    }

    // Setter for leftNode
    public void setLeftNode(TNode leftNode) {
        this.leftNode = leftNode;
    }

    // Getter for rightNode
    public TNode getRightNode() {
        return rightNode;
    }

    // Setter for rightNode
    public void setRightNode(TNode rightNode) {
        this.rightNode = rightNode;
    }

    // Getter for data
    public Object getData() {
        return data;
    }

    // Setter for data
    public void setData(Object data) {
        this.data = data;
    }

    // Method to check if the node has a left child
    public boolean hasLeftNode() {
        return leftNode != null;
    }

    // Method to check if the node has a right child
    public boolean hasRightNode() {
        return rightNode != null;
    }

    // String representation of TNode object
    @Override
    public String toString() {
        return this.data.toString();
    }


    @Override
    public int compareTo( TNode o) {
        if(this.data instanceof District)
            return ((District)this.data).compareTo((District) o.getData());
        else if(this.data instanceof  Location)
            return ((Location)this.data).compareTo((Location) o.getData());
        else if(this.data instanceof  MartyrDate)
            return ((MartyrDate) this.data).compareTo((MartyrDate) o.getData());
        else
            return this.compareTo(o);
    }
}
