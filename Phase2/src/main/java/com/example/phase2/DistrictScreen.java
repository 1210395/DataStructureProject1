package com.example.phase2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class DistrictScreen {
    private TNode currentDistrict;
    private Label totalMartyrsLabel;
    private Label districtTitleLabel;
    private Queue districtQueue;
    private Stack prevStack;
    private Stack nextStack;
    private Button insertButton;
    private Button updateButton;
    private Button deleteButton;
    private Button nextButton;
    private Button prevButton;
    private Button loadLocationsButton;
    private Button saveToFileButton;
    private ComboBox<String> districtComboBox;
    private Stage stage;
    private BorderPane root;
    public DistrictScreen() {
        initializeComponents();
        createUI();
        resetDistrict();
    }
    public void show() {
        stage.show();
    }
    public TNode getDistrictNode(){
        return currentDistrict;
    }
    private void initializeComponents() {
        totalMartyrsLabel = new Label();
        districtTitleLabel = new Label();
        districtQueue = new Queue();
        prevStack = new Stack();
        nextStack = new Stack();
        insertButton = new Button("Insert District");
        updateButton = new Button("Update District");
        deleteButton = new Button("Delete District");
        nextButton = new Button("Next District");
        prevButton = new Button("Previous District");
        loadLocationsButton = new Button("Load Locations");
        saveToFileButton = new Button("Save to File");
        districtComboBox = new ComboBox<>();
        stage = new Stage();
        root = new BorderPane();
        Queue districtQueue = Main.binarySearchTree.fillQueue();
        while (!districtQueue.isEmpty()) {
            District district = (District) ((TNode) districtQueue.dequeue()).getData();
            districtComboBox.getItems().add(district.getName());
        }
    }
    private void createUI() {
        root.setStyle("-fx-background-color: #F0F0F0;");
        VBox districtInfoVBox = new VBox(10, districtTitleLabel, totalMartyrsLabel);
        districtInfoVBox.setAlignment(Pos.CENTER);
        districtInfoVBox.setStyle("-fx-background-color: #6699CC;"); // Steel Blue
        HBox buttonHBox = new HBox(10, insertButton, updateButton, deleteButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setStyle("-fx-background-color: #FFD700;"); // Gold
        VBox comboBoxVBox = new VBox(10, districtComboBox, loadLocationsButton, saveToFileButton);
        comboBoxVBox.setAlignment(Pos.CENTER);
        comboBoxVBox.setStyle("-fx-background-color: #FF6347;");
        HBox navButtonHBox = new HBox(10, prevButton, nextButton);
        navButtonHBox.setAlignment(Pos.CENTER);
        navButtonHBox.setStyle("-fx-background-color: #8FBC8F;");
        VBox leftVBox = new VBox(10, navButtonHBox, comboBoxVBox, buttonHBox);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setStyle("-fx-background-color: #F08080;");
        root.setCenter(leftVBox);
        root.setTop(districtInfoVBox);


        insertButton.setOnAction(e -> addDistrict());
        updateButton.setOnAction(e -> updateDistrict());
        deleteButton.setOnAction(e -> deleteDistrict());
        nextButton.setOnAction(e -> nextDistrict());
        prevButton.setOnAction(e -> prevDistrict());
        loadLocationsButton.setOnAction(e -> loadLocations());
        saveToFileButton.setOnAction(e -> Main.writeToFile());

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
    }
    private  void resetDistrict(){
        districtQueue=Main.binarySearchTree.fillQueue();
        Queue queue=new Queue();
        currentDistrict=(TNode)districtQueue.dequeue();
        prevStack=new Stack();
        nextStack=new Stack();
        totalMartyrsLabel.setText("Total Number of Martyrs: "+((District)currentDistrict.getData()).getCount());
        districtTitleLabel.setText("District Name: "+currentDistrict.getData().toString());
        nextButton.setDisable(false);
        prevButton.setDisable(true);
        Queue districtQueue = Main.binarySearchTree.fillQueue();
        while (!districtQueue.isEmpty()) {
            District district = (District) ((TNode) districtQueue.dequeue()).getData();
            districtComboBox.getItems().add(district.getName());
        }
    }
    private  void addDistrict(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label districtlbl = new Label("Insert Name: ");
        TextField districttxt = new TextField();
        VBox vbox = new VBox(10, districtlbl, districttxt);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(10, ok, cancel);
        vbox1.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(vbox1);
        Stage stage=new Stage();
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
        cancel.setOnAction(event ->{
            stage.close();
        });
        ok.setOnAction(event -> {
            String name=districttxt.getText().trim();
            District district=new District(name);
            if(Main.binarySearchTree.find(district)==null) {
                Main.binarySearchTree.insert(district);
                resetDistrict();
                stage.close();
            }
        });
    }
    private  void updateDistrict(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label districtlbl = new Label("Update Name: ");
        TextField districttxt = new TextField();
        VBox vbox = new VBox(10, districtlbl, districttxt);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(10, ok, cancel);
        vbox1.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(vbox1);
        Stage stage=new Stage();
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
        cancel.setOnAction(event ->{
            stage.close();
        });
        ok.setOnAction(event -> {
            District district=((District)currentDistrict.getData());
            district.setName(districttxt.getText().trim());
            Main.binarySearchTree.delete(currentDistrict.getData());
            Main.binarySearchTree.insert(district);
            resetDistrict();
            stage.close();
        });
    }
    private  void deleteDistrict(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label districtlbl = new Label("Are you sure you want to delete : "+currentDistrict.getData().toString());
        VBox vbox = new VBox(10, districtlbl);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(10, ok, cancel);
        vbox1.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(vbox1);
        Stage stage=new Stage();
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
        cancel.setOnAction(event ->{
            stage.close();
        });
        ok.setOnAction(event -> {
            Main.binarySearchTree.delete(currentDistrict.getData());
            resetDistrict();
            stage.close();
        });
    }
    private void prevDistrict() {
        if (!prevStack.isEmpty()) {
            nextStack.push(currentDistrict);
            currentDistrict = (TNode) prevStack.pop();
            totalMartyrsLabel.setText("Total Number of Martyrs: " + ((District) currentDistrict.getData()).getCount());
            districtTitleLabel.setText("District Name: " + currentDistrict.getData().toString());
            nextButton.setDisable(false);
        } else {
            nextButton.setDisable(false);
            prevButton.setDisable(true);

        }
    }
    private void nextDistrict(){
        if(!districtQueue.isEmpty()){
            prevStack.push(currentDistrict);
            currentDistrict=(TNode)districtQueue.dequeue();
            totalMartyrsLabel.setText("Total Number of Martyrs: "+((District)currentDistrict.getData()).getCount());
            districtTitleLabel.setText("District Name: "+currentDistrict.getData().toString());
            prevButton.setDisable(false);

        }else if(!nextStack.isEmpty()) {
            prevStack.push(currentDistrict);
            currentDistrict=(TNode)nextStack.pop();
            totalMartyrsLabel.setText("Total Number of Martyrs: "+((District)currentDistrict.getData()).getCount());
            districtTitleLabel.setText("District Name: "+currentDistrict.getData().toString());
            prevButton.setDisable(false);

        }else{
            nextButton.setDisable(true);
            prevButton.setDisable(false);
        }

    }
    private void loadLocations(){

        if(districtComboBox.getSelectionModel().getSelectedItem()!=null) {
            currentDistrict = Main.binarySearchTree.find(new District(districtComboBox.getSelectionModel().getSelectedItem()));
        }
        Main.locationFX = new LocationScene();
        Main.locationFX.show();
    }
}
