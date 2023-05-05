package com.example.whoU;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

//Connects the .FXML(Design, Features names) and the app (MAIN, and javafx setup)
//Collects DATA and puts them into Objects, objects mainipulate the DATA.

public class controller {
    //FXML Objects
    @FXML
    TextField filePicked;
    @FXML
    Label hashLabel;
    // Global File objects
    fileHandler fr = new fileHandler();
    FileChooser fc = new FileChooser();
    //Global api object, and binary IO
    api neo;
    byte[] importantBin;


    //Set the path to testFolder, and shows in the text field the file that has been selected. Byte Array ready to Output
    public void chooseFile(ActionEvent actionEvent) throws IOException, InterruptedException {
        File path = new File("C:/test folder");
        fc.setInitialDirectory(path);
        File file = fc.showOpenDialog(new Stage());
        filePicked.setText(file.toString());

       importantBin = fr.binFile(filePicked.getText());

    }
    @FXML
    //Scans url, ready for next action
    protected void scanURL(ActionEvent e) throws IOException, InterruptedException, URISyntaxException {
        neo = new api();
        String site = filePicked.getText();
        neo.urlScan(site);
    }

    @FXML
    //shows URL report
    protected void showReport(ActionEvent e) throws IOException, InterruptedException {
        neo = new api();
        String myReport = neo.report(fr.md5);
        fr.showReport(myReport);
    }


    //This is a static analysis that shows hex, sha-256, and md5, sha-1 Makes the binary, into hash code.
    @FXML
    protected  void showString(ActionEvent e) throws IOException, InterruptedException, NoSuchAlgorithmException {
        //sha-256
         fr.showSha256(importantBin);
         hashLabel.setText("SHA-256 =   " + fr.sha256);
    }

    @FXML
    protected  void showMd5(ActionEvent e)  {
        //Md5
        fr.showMd5(importantBin);
        hashLabel.setText("MD5 String =  " + fr.md5);
    }

    @FXML
    protected void showHex(ActionEvent e) throws IOException {
            fr.binToHex(importantBin);
    }

    @FXML
    protected void showSha1(ActionEvent e) throws IOException, NoSuchAlgorithmException {
            //showSha1
            fr.showSha1(importantBin);
            hashLabel.setText( "SHA-1 = "+ fr.sha1);
    }

    @FXML
    protected void fileUp(ActionEvent e) throws IOException{

    }

}
