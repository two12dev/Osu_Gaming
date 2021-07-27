package com.gui;

import com.gui.intro.JemadIntroScene;
import com.gui.loadgame.LoadGameScene;
import com.gui.maingame.MainScreenSceneBuilder;
import com.gui.map.MapSceneBuilder;
import com.gui.savegame.SaveGameScene;
import com.gui.status.PlayerStatusSceneBuilder;
import com.gui.tutorial.TutorialSceneBuilder;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage stage;

    // handles only menuitems
    public static void switchSceneBaseOnMenuItemClick(ActionEvent event, Stage stage) throws IOException {
        MenuItem menuItemId = (MenuItem) event.getSource();
        String id = menuItemId.getId();

        MenuItem menuItemTarget = (MenuItem) event.getTarget();
        switch (id) {
            case "displayMapMenuItem":
                MapSceneBuilder mapSceneBuilder = new MapSceneBuilder();
                mapSceneBuilder.buildMapScene(stage);
                break;
            case "aboutJemad":
                PlayerStatusSceneBuilder playerSceneBuilder = new PlayerStatusSceneBuilder();
                playerSceneBuilder.buildPlayerStatusScene(stage);
                break;
            case "saveGame":
                SaveGameScene saveGameScene = new SaveGameScene();
                saveGameScene.buildSaveGameScene(stage);
                break;
            case "loadGame":
                LoadGameScene loadGameScene = new LoadGameScene();
                loadGameScene.buildLoadGameScene(stage);
                break;
        }
    }

    // handles only actual buttons
    public static void switchScenesBaseOnBtnClick(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switch (id) {
            case "north":
            case "south":
            case "east":
            case "west":
            case "continueBtn":
            case "goBackBtn":
            case "doNotSaveGameBtn":
            case "doNotLoadBtn":
            case "loadBtnOk":
            case "exitPlayerStatusButton":
                MainScreenSceneBuilder mainGameScene = new MainScreenSceneBuilder();
                mainGameScene.buildMainGameScene(stage);
                break;
            case "startBtn":
            case "tutorialToMainBtn":
                JemadIntroScene jemadIntroScene = new JemadIntroScene();
                jemadIntroScene.buildIntroScene(stage);
                break;
            // placeholder until we figure out menu item thing
            case "viewMapBtn":
                MapSceneBuilder mapSceneBuilder = new MapSceneBuilder();
                mapSceneBuilder.buildMapScene(stage);
                break;
            case "saveGameBtn":
                SaveGameScene saveGameScene = new SaveGameScene();
                saveGameScene.buildSaveGameSuccessScene(stage);
                break;
            case "saveGameBtnOk":
                stage.close();
                break;
            case "loadBtn":
                LoadGameScene loadGameScene = new LoadGameScene();
                loadGameScene.buildLoadGameWaitScene(stage);
                break;
            case "tutorialBtn":
                TutorialSceneBuilder tutorialScene = new TutorialSceneBuilder();
                tutorialScene.buildWinScene(stage);
                break;
            case "loadGameTitleBtn":
                loadGameScene = new LoadGameScene();
                loadGameScene.buildLoadGameRestartScene(stage);
                break;
            case "restartLoseButton":
                loadGameScene = new LoadGameScene();
                loadGameScene.buildLoseResetLoadGameScene(stage);

        }
    }
}