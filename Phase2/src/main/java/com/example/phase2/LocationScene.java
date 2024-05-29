package com.example.phase2;
import javafx.geometry.Insets;
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


public class LocationScene{
    private TNode current;
    private Queue queueLocation;
    private Stack prevS;
    private Stack nextS;
    private ComboBox<String> comb=new ComboBox<>();
    private Label max=new Label();
    private Label early=new Label();
    private Label late=new Label();
    private Label totalMLocation = new Label();
    private Label titleLocation = new Label();
    private Button insert = new Button("Insert Location");
    private Button update = new Button("Update Location");
    private Button delete = new Button("Delete Location");
    private Button next = new Button("Next Location");
    private Button prev = new Button("Previous Location");
    private Button load = new Button("Load Martyrs");
    private BorderPane root = new BorderPane();
    private Stage stage=new Stage();
    public LocationScene(){
        rootMaker();
    }
    private void rootMaker() {
        resetLocation();
        updateLabels();
        insert.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        update.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        delete.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        load.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        next.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        prev.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");

        root.setStyle("-fx-background-color: #8B0000;");
        Queue queue = ((District) Main.districtFX.getDistrictNode().getData()).getLocationTree().fillQueue();
        while (!queue.isEmpty())
            comb.getItems().add(queue.dequeue().toString());
        comb.getSelectionModel().select(1);
        load.setOnAction(e -> {
            Location dis = new Location(comb.getSelectionModel().getSelectedItem());
            if (((District) Main.districtFX.getDistrictNode().getData()).getLocationTree().find(dis) == null) {
                Main.martyrFX = new MartyrScreen();
                Main.martyrFX.show();
            } else {
                current = ((District) Main.districtFX.getDistrictNode().getData()).getLocationTree().find(dis);
                Main.martyrFX = new MartyrScreen();
            }
        });
        insert.setOnAction(e -> {
            insertLocation();
        });
        update.setOnAction(e -> {
            updateLocation();
        });
        delete.setOnAction(e -> {
            deleteLocation();
        });
        next.setOnAction(e -> {
            boolean flag = toNext();
            if (!flag) {
                prev.setDisable(false);
                next.setDisable(true);
            } else {
                next.setDisable(false);
                prev.setDisable(false);
            }
        });
        prev.setOnAction(e -> {
            boolean flag = toPrevious();
            if (!flag) {
                prev.setDisable(true);
                next.setDisable(false);
            } else {
                prev.setDisable(false);
                next.setDisable(false);
            }
        });
        VBox vbox = new VBox(next);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(prev);
        vbox1.setAlignment(Pos.CENTER);
        VBox vbox2 = new VBox(10, titleLocation, max, early, late, totalMLocation);
        vbox2.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(10, insert, update, delete);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox3 = new VBox(10, hbox, comb, load);
        vbox3.setAlignment(Pos.CENTER);
        root.setLeft(vbox1);
        root.setRight(vbox);
        root.setTop(vbox2);
        root.setBottom(vbox3);
        String buttonStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px;";
        root.setStyle("-fx-background-color: #f0f2f5;");
        insert.setStyle(buttonStyle);
        update.setStyle(buttonStyle);
        delete.setStyle(buttonStyle);
        load.setStyle(buttonStyle);
        next.setStyle(buttonStyle);
        prev.setStyle(buttonStyle);
        insert.setPrefWidth(100);
        update.setPrefWidth(100);
        delete.setPrefWidth(100);
        load.setPrefWidth(100);
        next.setPrefWidth(100);
        prev.setPrefWidth(100);
        insert.setOnMouseEntered(e -> insert.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        insert.setOnMouseExited(e -> insert.setStyle(buttonStyle));
        update.setOnMouseEntered(e -> update.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        update.setOnMouseExited(e -> update.setStyle(buttonStyle));
        delete.setOnMouseEntered(e -> delete.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        delete.setOnMouseExited(e -> delete.setStyle(buttonStyle));
        load.setOnMouseEntered(e -> load.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        load.setOnMouseExited(e -> load.setStyle(buttonStyle));
        next.setOnMouseEntered(e -> next.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        next.setOnMouseExited(e -> next.setStyle(buttonStyle));
        prev.setOnMouseEntered(e -> prev.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);"));
        prev.setOnMouseExited(e -> prev.setStyle(buttonStyle));
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);

    }
    public void show(){
        stage.show();
    }
    public void close(){
        close();
    }
    public  TNode getCurrentLocation() {
        return current;
    }
    public  void setCurrent(TNode current) {
        this.current = current;
    }
    private void resetLocation(){
        queueLocation =((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().fillQueue();
        current=(TNode)queueLocation.dequeue();
        nextS=new Stack();
        prevS=new Stack();
        next.setDisable(false);
        prev.setDisable(true);
    }
    private boolean updateLabels(){
        totalMLocation.setText("Total Number of Martyrs: "+((Location)current.getData()).getCounter());
        titleLocation.setText("Location Name: "+current.getData().toString());
        try {
            max.setText("The Max Date :" + ((MartyrDate) ((Location) current.getData()).getDateList().largest().getData()).toString());
            early.setText("Earliest Date :"+ ((MartyrDate) ((Location)current.getData()).getDateList().smallestDate().getData()).toString());
            late.setText("Latest Date :"+((MartyrDate) ((Location)current.getData()).getDateList().largestDate().getData()).toString());
        }catch (Exception ex){
            return false;

        }
        return true;
    }
    private boolean toPrevious(){
        if(!prevS.isEmpty()){
            nextS.push(current);
            current=(TNode)prevS.pop();
            return updateLabels();
        }
        return false;
    }
    private boolean toNext(){
        if(!queueLocation.isEmpty()){
            prevS.push(current);
            current=(TNode)queueLocation.dequeue();
            return updateLabels();

        }else if(!nextS.isEmpty()) {
            prevS.push(current);
            current=(TNode)nextS.pop();
            return updateLabels();

        }
        return false;
    }




    private  void insertLocation(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label locationlbl = new Label(" Name: ");
        TextField locationtxt = new TextField();
        VBox vbox = new VBox(10, locationlbl, locationtxt);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        VBox vbox1 = new VBox(10, ok, cancel);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(20));
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
            String name=locationtxt.getText().trim();
            Location location=new Location(name);
            if(((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().find(location)==null) {
                ((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().insert(location);
                updateLabels();
                resetLocation();
                stage.close();
            }

        });

    }
    private void updateLocation(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label locationlbl = new Label("Insert Name: ");
        TextField locationtxt = new TextField();
        VBox vbox = new VBox(10, locationlbl, locationtxt);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(10, ok, cancel);
        vbox1.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(vbox1);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
        cancel.setOnAction(event ->{
            stage.close();
        });
        ok.setOnAction(event -> {
            Location location=((Location)current.getData());
            location.setPlaceName(locationtxt.getText().trim());
            ((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().delete(current.getData());
            ((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().insert(location);
            resetLocation();
            boolean flag= updateLabels();
            if(!flag)
                Main.showErrorStage("Something went wrong");
            stage.close();
        });
    }
    private void deleteLocation(){
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Label locationlbl = new Label("Delete "+current.getData().toString()+" ?");
        VBox vbox = new VBox(10, locationlbl);
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
            ((District)Main.districtFX.getDistrictNode().getData()).getLocationTree().delete(current.getData());
            resetLocation();
            boolean flag=updateLabels();
            if(!flag)
                Main.showErrorStage("Something went wrong");
            stage.close();

        });
    }


}
