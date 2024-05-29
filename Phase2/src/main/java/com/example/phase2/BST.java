package com.example.phase2;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;

public class BST{
    private TNode root;

    public BST() {
        root = null;
    }

    public void insert(Object data) {
        if (isEmpty()) {
            root = new TNode(data);
        } else {
            insert(data, root);
        }
    }
    public TNode get(int index) {
        if (index < 0) {
            return null; // Index out of bounds
        }
        SingleLinkedList nodes = new SingleLinkedList();
        inOrderTraversal(root, nodes); // Perform in-order traversal and populate the list of nodes
        if (index >= nodes.size()) {
            return null; // Index out of bounds
        }
        return new TNode(nodes.get(index).getData()); // Return the node at the specified index
    }

    private void inOrderTraversal(TNode root, SingleLinkedList nodes) {
        if (root != null) {
            inOrderTraversal(root.getLeftNode(), nodes);
            nodes.addLast(root.getData()); // Add the current node's data to the list
            inOrderTraversal(root.getRightNode(), nodes);
        }
    }
    private void insert(Object data, TNode root) {
        TNode newNode=new TNode(data);
        if (newNode.compareTo(root) < 0) {
            if (root.hasLeftNode()) {
                insert(data, root.getLeftNode());
            } else {
                root.setLeftNode(new TNode(data));
            }
        } else {
            if (root.hasRightNode()) {
                insert(data, root.getRightNode());
            } else {
                root.setRightNode(new TNode(data));
            }
        }
    }

    public TNode find(Object data) {
        return find(data, root);
    }
    private String write;
    public String printToFile(){
        write=new String();
        return  printToFile(root);
    }
    public String printToFile(TNode root){
        if(root!=null){
            printToFile(root.getLeftNode());
            if(root.getData() instanceof District)
                write+=((District)root.getData()).getLocationTree().printToFile();
            else if(root.getData() instanceof Location)
                write+= ((Location)root.getData()).getDateList().printToFile();
            else if(root.getData() instanceof MartyrDate)
                write+=((MartyrDate)root.getData()).getMartyrsList().printList();
            printToFile(root.getRightNode());

        }
        return  write;
    }

    private TNode find(Object data, TNode root) {
        TNode newNode=new TNode(data);
        if (root == null) {
            return null;
        }
        if (newNode.compareTo(root) == 0) {
            return root;
        } else if (newNode.compareTo(root) < 0) {
            return find(data, root.getLeftNode());
        } else {
            return find(data, root.getRightNode());
        }
    }

    public void delete(Object data) {
        root = delete(data,root);
    }

    private TNode delete(Object data, TNode node) {
        TNode newNode=new TNode(data);
        if (node == null) {
            return null;
        }
        if (newNode.compareTo(node) < 0) {
            node.setLeftNode(delete(data, node.getLeftNode()));
        } else if (newNode.compareTo(node) > 0) {
            node.setRightNode(delete(data, node.getRightNode()));
        } else {

            if (node.getLeftNode() == null) {
                return node.getRightNode();
            } else if (node.getRightNode() == null) {
                return node.getLeftNode();
            }

            TNode temp = minValueNode(node.getRightNode());

            node.setData(temp.getData());

            node.setRightNode(delete(temp.getData(), node.getRightNode()));
        }
        return node;
    }

    private TNode minValueNode(TNode node) {
        TNode current = node;
        while (current != null && current.getLeftNode() != null) {
            current = current.getLeftNode();
        }
        return current;
    }




    private TNode largest(TNode root,TNode largest) {
        if (root != null) {
            largest(root.getLeftNode(),largest);
            if(((MartyrDate)root.getData()).getMartyrsList().size()>((MartyrDate)largest.getData()).getMartyrsList().size()){
                largest=root;
            }
            largest(root.getRightNode(),largest);
        }
        return largest;
    }
    public TNode largest() {

        if(root.getLeftNode()!=null) {
            if(largest(root,root.getLeftNode())!=null)
            return largest(root, root.getLeftNode());
            else
                return root;
        }
        else if(root.getRightNode()!=null)
        if(largest(root,root.getRightNode())!=null)
            return largest(root,root.getRightNode());
        else
            return  root;
        else{
            return root;
        }
    }

    private TNode smallest(TNode root,TNode smallest) {
        if (root != null) {
            smallest(root.getLeftNode(),smallest);
           if(((MartyrDate)root.getData()).getMartyrsList().size()<((MartyrDate)smallest.getData()).getMartyrsList().size()){
               smallest=root;
           }
            smallest(root.getRightNode(),smallest);
        }
        return smallest;
    }
    public TNode largestDate(){
        TNode current=root;
        while (current.getRightNode()!=null){
            current=current.getRightNode();
        }
        return current;
    }
    public TNode smallestDate(){
        TNode current=root;
        while (current.getLeftNode()!=null){
            current=current.getLeftNode();
        }
        return current;
    }




    public Queue fillQueue() {
        Queue queue=new Queue();
        return fillQueue(queue,root);
    }
    private Queue fillQueue(Queue queue,TNode root) {
        if (root != null) {
            fillQueue(queue,root.getLeftNode());
            queue.enqueue(root);
            fillQueue(queue,root.getRightNode());
        }
        return queue;
    }
    private void inOrderTraversal(TNode root) {
        if (root != null) {
            inOrderTraversal(root.getLeftNode());
            System.out.println(root.getData() + " ");
            inOrderTraversal(root.getRightNode());
        }
    }


    public Boolean isEmpty() {
        return root == null;
    }


    private void preOrderTraversal(TNode root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preOrderTraversal(root.getLeftNode());
            preOrderTraversal(root.getRightNode());
        }
    }



    private void postOrderTraversal(TNode root) {
        if (root != null) {
            postOrderTraversal(root.getLeftNode());
            postOrderTraversal(root.getRightNode());
            System.out.print(root.getData() + " ");
        }
    }



}