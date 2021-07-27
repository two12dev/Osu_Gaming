package com.gui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class Controller implements Initializable {
    @FXML private Pane pane;
    @FXML private Label songLabel;
    @FXML private Button playButton;
    @FXML private Slider volumeSlider;
    // test
    @FXML
    private Button tutorialBtn;
    @FXML
    private Button loadGameTitleBtn;
    @FXML
    private Button loseRestartBtn;
    // end of test
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private boolean running;
    private Media media;
    private static MediaPlayer mediaPlayer;
    private static Controller instance;

    // test for jar
    private ArrayList<String> fileNames;
    // end of test for jar

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        media();
        // playMedia();
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void media(){
        // testing for jar
        fileNames = new ArrayList<>();
        try (
                InputStream in = Controller.class.getResourceAsStream("/Music");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            // loop
            while ((resource = bufferedReader.readLine()) != null) {
                fileNames.add(resource);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // media = new Media(Controller.class.getResource("/Music/"+ fileNames.get(songNumber)).toString());
        media = new Media(Controller.class.getResource("/Music/1Paris.wav").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void volume() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                mediaPlayer.setVolume(volumeSlider.getValue() *.01);
            }
        });
    }

    public Controller() {
        instance = this;
    }
    public static Controller getInstance() {
        return instance;
    }


    public void playMedia(){
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();
        System.out.println("Playing music");
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                nextMedia();
                ;            }
        });
    }

    public void playFinalBossMusic() {
        mediaPlayer.stop();
        media = new Media(Controller.class.getResource("/finalBossFight.wav").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void pauseMedia(){
        mediaPlayer.pause();
    }
    public void nextMedia() {
        if (songNumber < 1) {
            songNumber++;
            mediaPlayer.stop();

            // test
            media = new Media(Controller.class.getResource("/Music/2Hydrogen.wav").toString());
            // end of test

            // media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
            mediaPlayer.play();
            // playMedia();
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            // test
            media = new Media(Controller.class.getResource("/Music/1Paris.wav").toString());
            // end of test

            // media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
            mediaPlayer.play();
            // playMedia();
        }
    }

    public void switchToMainScreen(ActionEvent event) throws IOException {
        SceneController.switchScenesBaseOnBtnClick(event);
    }

}