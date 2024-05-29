package com.example.phase2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
    public class Main extends Application {
        public static  BST binarySearchTree = new BST();
        public static LocationScene locationFX;
        public static DistrictScreen districtFX;
        public static MartyrScreen martyrFX;

        @Override
        public void start(Stage primaryStage) throws IOException {
            boolean fileReadSuccessful = readFile();
            if (fileReadSuccessful) {
                districtFX = new DistrictScreen();
                districtFX.show();
            } else {
                showErrorStage("Error Reading File!!!");
            }
        }

        public static void main(String[] args) {
            launch();
        }

        public static boolean readFile(){
            File file =new FileChooser().showOpenDialog(new Stage());
            try{
                Scanner in=new Scanner(file);
                in.nextLine();
                while(in.hasNext())
                {
                    String [] str=in.nextLine().split(",");
                    District dis=new District(str[4].trim());
                    if(binarySearchTree.find(dis)==null) {
                        binarySearchTree.insert(dis);
                    }
                    Location loc=new Location(str[3].trim());
                    if(((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc)==null){
                        ((District)binarySearchTree.find(dis).getData()).getLocationTree().insert(loc);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

                    LocalDate date = LocalDate.parse(str[1].trim(), formatter);
                    MartyrDate Mdate=new MartyrDate(date);
                    if(((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).getDateList().find(Mdate)==null){
                        ((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).getDateList().insert(Mdate);
                    }
                    if(str[2].trim().isBlank())
                        str[2]="-1";
                    Martyr mar=new Martyr(str[0].trim(),LocalDate.parse(str[1].trim(), formatter),Integer.parseInt(str[2].trim()),str[3].trim(),str[4].trim(),str[5].trim().charAt(0));
                    if(((MartyrDate)((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).getDateList().find(Mdate).getData()).getMartyrsList().find(mar)==null){
                        ((MartyrDate)((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).getDateList().find(Mdate).getData()).getMartyrsList().addSorted(mar);
                    }
                    ((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).increaseCounter();
                    ((District)binarySearchTree.find(dis).getData()).incrementCount();
                    ((MartyrDate)((Location)((District)binarySearchTree.find(dis).getData()).getLocationTree().find(loc).getData()).getDateList().find(Mdate).getData()).addToAgeSum(mar.getAge());


                }
                return true;
            }catch(Exception e){
                return false;
            }
        }

        public static void writeToFile() {
            try (FileWriter fileWriter = new FileWriter(new File("Data.txt"))) {
                fileWriter.write(binarySearchTree.printToFile());
            } catch (IOException e) {
                showErrorStage("Error writing to file");
            }
        }

        public static void showErrorStage(String errorMessage) {
            Stage stage = new Stage();
            stage.setTitle("Error");

            Label label = new Label(errorMessage);
            label.setFont(Font.font("Arial", 16));
            label.setTextFill(Color.RED);

            StackPane root = new StackPane();
            root.getChildren().add(label);
            root.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 20px;");

            Scene scene = new Scene(root, 300, 200);
            stage.setScene(scene);
            stage.show();
        }
    }
