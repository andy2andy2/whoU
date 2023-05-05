package com.example.whoU;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
//Main functions and sets up fxml

public class whouApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

       // Parent root = null;
        controller controller = null;
        FXMLLoader loader = null;

        System.out.println(getClass());
        loader = new FXMLLoader(HelloApplication.class.getResource("javafxml.fxml"));

       Parent root = loader.load();
       Scene scene = new Scene(root, 1000, 1000);
        stage.setTitle("WHOU");
        stage.setScene(scene);
        stage.show();

    }
}
