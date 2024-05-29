package com.example.phase2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MartyrScreen{
private TNode current;
    private  Queue queueDate;
    private  Stack prevD;
    private  Stack nextD;
    private Label youngest;
    private Label oldest;
    private Button next=new Button("Next Date");
    private Button prev=new Button("Prev Date");
    private Stage stage=new Stage();
    private  TableView<Martyr> tableView = new TableView<>();
    public MartyrScreen(){
        rootMaker();
    }
    public void show(){
        stage.show();
    }
    public void close(){
        stage.close();
    }
private  void resetMartyr(){
    queueDate =((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().fillQueue();
    if(!queueDate.isEmpty())
        current=(TNode)queueDate.dequeue();
    nextD=new Stack();
    prevD=new Stack();
    next.setDisable(false);
    prev.setDisable(true);
    fillTable("");
}
private void updateLabels(){
    youngest.setText("Oldest Martyrs : "+((MartyrDate)current.getData()).getMartyrsList().getLast());
    oldest.setText("Youngest Martyrs : "+((MartyrDate)current.getData()).getMartyrsList().getFirst());
    fillTable("");
}
private void nextMartyr(){
    if(!queueDate.isEmpty()){
        prevD.push(current);
        current=(TNode)queueDate.dequeue();
        updateLabels();
        prev.setDisable(false);

    }else if(!nextD.isEmpty()) {
        prevD.push(current);
        current=(TNode)nextD.pop();
        updateLabels();
        prev.setDisable(false);

    }else{
        next.setDisable(true);
        prev.setDisable(false);
    }
}
private void prevMartyr(){
    if(!prevD.isEmpty()){
        nextD.push(current);
        current=(TNode)prevD.pop();
        updateLabels();
        next.setDisable(false);

    }else {
        prev.setDisable(true);
        next.setDisable(false);
    }
}
private void deleteMartyr(){
    if(tableView.getSelectionModel().getSelectedItem()!=null)
        deleteMartyr(tableView.getSelectionModel().getSelectedItem());
    else
        Main.showErrorStage("Select a Martyr from the table");
}
private void updateMartyr(){
    if(tableView.getSelectionModel().getSelectedItem()!=null)
        updateMartyr(tableView.getSelectionModel().getSelectedItem());
    else
        Main.showErrorStage("Select a Martyr from the table");
}
    private void rootMaker() {
        resetMartyr();
        youngest=new Label("Youngest Martyrs : "+((MartyrDate)current.getData()).getMartyrsList().getLast());
        oldest=new Label("Youngest Martyrs : "+((MartyrDate)current.getData()).getMartyrsList().getFirst());

        Label avg=new Label("Average Age : ");
        Button insert=new Button("Insert Martyr");
        Button update=new Button("Update Martyr");
        Button delete=new Button("Delete Martyr");
        TextField searchtxt=new TextField();
        Button search=new Button("Search");
        TableColumn<Martyr, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Martyr, LocalDate> eventCol = new TableColumn<>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
        TableColumn<Martyr, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Martyr, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<Martyr, String> districtCol = new TableColumn<>("District");
        districtCol.setCellValueFactory(new PropertyValueFactory<>("district"));
        TableColumn<Martyr, Character> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableView.getColumns().addAll(nameCol, eventCol, ageCol, locationCol, districtCol, genderCol);
        BorderPane root=new BorderPane();
        VBox vbox=new VBox(10,avg,youngest,oldest);
        vbox.setAlignment(Pos.CENTER);
        root.setTop(vbox);
        root.setCenter(tableView);

        // Button and TextField styles
        String buttonStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;";
        insert.setStyle(buttonStyle);
        update.setStyle(buttonStyle);
        delete.setStyle(buttonStyle);
        search.setStyle(buttonStyle);
        searchtxt.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #333333;");

        // Button HBox
        HBox buttonBox = new HBox(10, insert, update, delete, searchtxt, search,prev,next);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setBottom(buttonBox);

        // Table styles
        tableView.setStyle("-fx-background-color: #FFFFFF; -fx-text-inner-color: #333333;");
        nameCol.setStyle("-fx-text-fill: #4CAF50;");
        eventCol.setStyle("-fx-text-fill: #4CAF50;");
        ageCol.setStyle("-fx-text-fill: #4CAF50;");
        locationCol.setStyle("-fx-text-fill: #4CAF50;");
        districtCol.setStyle("-fx-text-fill: #4CAF50;");
        genderCol.setStyle("-fx-text-fill: #4CAF50;");

        Scene scene=new Scene(root,800,600);
        scene.setFill(Color.rgb(30, 30, 30));

        stage.setScene(scene);

        next.setOnAction(e->{
            nextMartyr();
        });
        prev.setOnAction(e->{
            prevMartyr();
        });

        insert.setOnAction(e->{
            insertMartyr();
        });
        delete.setOnAction(e->{
            deleteMartyr();
        });
        update.setOnAction(e->{
            updateMartyr();
        });
        search.setOnAction(e ->{
            String name=searchtxt.getText().trim();
            fillTable(name);
        });
        fillTable("");

        stage.show();
    }
private SingleLinkedList SortByName(){
    SingleLinkedList sll=new SingleLinkedList();
    Node current=((MartyrDate)this.current.getData()).getMartyrsList().getFirstNode();
    while(current!=null){
        sll.addSortedName((Martyr) current.getData());
        current=current.getNextNode();
    }
    return sll;
}
private void fillTable(String name){
    tableView.getItems().clear();
    Node current=SortByName().getFirstNode();
    while(current!=null){
        if(((Martyr)current.getData()).getName().contains(name))
            tableView.getItems().add((Martyr) current.getData());
        current=current.getNextNode();
    }
    tableView.refresh();
}
private void deleteMartyr(Martyr old){
    Label namelbl=new Label("Are you sure you want to Delete :\n"+old.getName());
    HBox hbox=new HBox(10,namelbl);
    Button ok=new Button("Confrim");
    Button cancel=new Button("Cancel");
    HBox hbox4=new HBox(10,ok,cancel);
    VBox vbox=new VBox(10,hbox,hbox4);
    vbox.setAlignment(Pos.CENTER);
    hbox.setAlignment(Pos.CENTER);
    hbox4.setAlignment(Pos.CENTER);
    BorderPane root=new BorderPane();
    root.setCenter(vbox);
    Stage stage=new Stage();
    stage.setTitle("Insert Martyr");
    stage.setScene(new Scene(root,500,500));
    stage.show();
    cancel.setOnAction(e ->{
        stage.close();
    });
    ok.setOnAction(e ->{
        if(((MartyrDate)current.getData()).getMartyrsList().find(old)==null){
            Main.showErrorStage("No Martyr Found");
        }else {
            ((MartyrDate) current.getData()).getMartyrsList().remove(old);
            if(((MartyrDate) current.getData()).getMartyrsList().isEmpty())
                ((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().delete(current.getData());
            resetMartyr();

            stage.close();
        }
    });
}
private void updateMartyr(Martyr old){
    Label namelbl=new Label("Name :");
    Label eventlbl=new Label("date :");
    Label agelbl=new Label("age :");
    Label genderlbl=new Label("gender ");
    TextField nametxt=new TextField();
    DatePicker eventtxt=new DatePicker();
    TextField agetxt=new TextField();
    TextField gendertxt=new TextField();
    HBox hbox=new HBox(10,namelbl,nametxt);
    HBox hbox1=new HBox(10,eventlbl,eventtxt);
    HBox hbox2=new HBox(10,agelbl,agetxt);
    HBox hbox3=new HBox(10,genderlbl,gendertxt);
    Button ok=new Button("Confrim");
    Button cancel=new Button("Cancel");
    HBox hbox4=new HBox(10,ok,cancel);
    VBox vbox=new VBox(10,hbox,hbox1,hbox2,hbox3,hbox4);
    vbox.setAlignment(Pos.CENTER);
    hbox.setAlignment(Pos.CENTER);
    hbox1.setAlignment(Pos.CENTER);
    hbox2.setAlignment(Pos.CENTER);
    hbox3.setAlignment(Pos.CENTER);
    hbox4.setAlignment(Pos.CENTER);
    BorderPane root=new BorderPane();
    root.setCenter(vbox);
    Stage stage=new Stage();
    stage.setTitle("Insert Martyr");
    stage.setScene(new Scene(root,500,500));
    stage.show();
    cancel.setOnAction(e ->{
        stage.close();
    });
    ok.setOnAction(e ->{
        String name=nametxt.getText().trim();
        int age=0;
        try {
            age = Integer.parseInt(agetxt.getText().trim());
            if(age<0 || age>100)
                Main.showErrorStage("Age is not a vaild age");
        }catch (Exception ex){
            Main.showErrorStage("Age is not a number");
        }
        char gender=gendertxt.getText().trim().charAt(0);
        if(gender!='M' && gender!='F')
            Main.showErrorStage("Gender does not exist");

        else{

            LocalDate date = eventtxt.getValue();
            if(date==null)
                Main.showErrorStage("No Date Selected");
            else{
                MartyrDate dateM=new MartyrDate(date);
                Martyr m=new Martyr(name,date,age,Main.locationFX.getCurrentLocation().getData().toString(),Main.districtFX.getDistrictNode().getData().toString(),gender);
                if(((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().find(dateM)==null){
                    ((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().insert(dateM);
                }
                if(((MartyrDate)current.getData()).getMartyrsList().find(old)==null){
                    Main.showErrorStage("No Martyr Found");
                }else {
                    ((MartyrDate) current.getData()).getMartyrsList().remove(old);
                    if(((MartyrDate) current.getData()).getMartyrsList().isEmpty())
                        ((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().delete(current.getData());
                    ((MartyrDate) ((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().find(dateM).getData()).getMartyrsList().addSorted(m);
                    resetMartyr();
                    stage.close();
                }
            }
        }

    });
}
private void insertMartyr(){
    Label namelbl=new Label("Name ");
    Label eventlbl=new Label("Date ");
    Label agelbl=new Label("Age ");
    Label genderlbl=new Label("Gender ");
    TextField nametxt=new TextField();
    DatePicker eventtxt=new DatePicker();
    TextField agetxt=new TextField();
    TextField gendertxt=new TextField();
    HBox hbox=new HBox(10,namelbl,nametxt);
    HBox hbox1=new HBox(10,eventlbl,eventtxt);
    HBox hbox2=new HBox(10,agelbl,agetxt);
    HBox hbox3=new HBox(10,genderlbl,gendertxt);
    Button ok=new Button("Confrim");
    Button cancel=new Button("Cancel");
    HBox hbox4=new HBox(10,ok,cancel);
    VBox vbox=new VBox(10,hbox,hbox1,hbox2,hbox3,hbox4);
    vbox.setAlignment(Pos.CENTER);
    hbox.setAlignment(Pos.CENTER);
    hbox1.setAlignment(Pos.CENTER);
    hbox2.setAlignment(Pos.CENTER);
    hbox3.setAlignment(Pos.CENTER);
    hbox4.setAlignment(Pos.CENTER);
    BorderPane root=new BorderPane();
    root.setCenter(vbox);
    Stage stage=new Stage();
    stage.setTitle("Insert Martyr");
    stage.setScene(new Scene(root,500,500));
    stage.show();
    cancel.setOnAction(e ->{
        stage.close();
    });
    ok.setOnAction(e ->{
        String name=nametxt.getText().trim();
        int age=0;
        try {
            age = Integer.parseInt(agetxt.getText().trim());
            if(age<0 || age>100)
                Main.showErrorStage("Age is not a vaild age");
        }catch (Exception ex){
            Main.showErrorStage("Age is not a number");
        }
        char gender=gendertxt.getText().trim().charAt(0);
        if(gender!='M' && gender!='F')
            Main.showErrorStage("Gender does not exist");

        else{
            LocalDate date=null;
            try {
                date = eventtxt.getValue();
            }catch (Exception ex){
                Main.showErrorStage("The Date is not valid");
            }
            if(date==null)
                Main.showErrorStage("No Date Selected");
            else{
                MartyrDate dateM=new MartyrDate(date);
                Martyr m=new Martyr(name,date,age,Main.locationFX.getCurrentLocation().getData().toString(),Main.districtFX.getDistrictNode().getData().toString(),gender);
                if(((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().find(dateM)==null)
                    ((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().insert(dateM);
                if(((MartyrDate)((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().find(dateM).getData()).getMartyrsList().find(m)==null){
                    ((MartyrDate)current.getData()).getMartyrsList().addSorted(m);
                }else{
                    Main.showErrorStage("Martyr already Exists");
                }
                resetMartyr();
                ((Location)Main.locationFX.getCurrentLocation().getData()).increaseCounter();
                ((Location)Main.locationFX.getCurrentLocation().getData()).increaseCounter();
                ((MartyrDate)((Location)Main.locationFX.getCurrentLocation().getData()).getDateList().find(dateM).getData()).addToAgeSum(m.getAge());



            }
        }

    });
}
}
