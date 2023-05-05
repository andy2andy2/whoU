package com.example.whoU;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;

public class fileHandler implements Initializable {
    // Stage builders
    FileChooser fc = new FileChooser();
    Stage window = new Stage();
    Button close = new Button("Close Window");
    VBox layout = new VBox(10);
    //Global Strings
    String md5 = "";
    String sha256 = "";
    String sha1 = "";

    String hexy="";
    TextArea ta = new TextArea();
    //File to binary Array
    public byte[] binFile(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        byte[] bytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
        return bytes;
    }
    //Binary Array, to HexArray
    public void binToHex(byte[] binFile) throws IOException {

        StringBuilder hexString = new StringBuilder();
        for (byte b : binFile) {
            hexString.append(Integer.toHexString(b));
        }
        hexy = String.valueOf(hexString);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hex String");
        window.setMinWidth(1000);
        ta.appendText(hexy);
        close.setOnAction(e -> window.close());
        ta.autosize();
        layout.getChildren().addAll(ta, close);
        layout.setAlignment(Pos.CENTER);
        Scene display = new Scene(layout);
        window.setScene(display);
        window.showAndWait();
    }

    //converts binary array to sha1, setting string to SHA1
    public void showSha1(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(input);
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);
        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        sha1 =hashtext;
    }
    //converts binary array to sha256, setting string to SHA256
    public void showSha256(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input);
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);
        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        sha256 =hashtext;
    }

    //converts binary array to MD5, setting string to MD5
    public void showMd5(byte[] input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input);
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            md5 =hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //show apiReport
    public void showReport(String apiReport){
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Report");
        window.setMinWidth(1000);

        ta.getScrollTop();
        ta.autosize();

       ta.appendText(apiReport);

        close.setOnAction(e -> window.close());
        layout.getChildren().addAll(ta, close);
        layout.setAlignment(Pos.CENTER);
        Scene display = new Scene(layout);
        window.setScene(display);
        window.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}