package com.example.terraingenerationtest3;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;

public class TerrainGeneration extends Application {
    @Override
    public void start(Stage stage) {

        double screenHeight = findScreenHeight();
        double screenWidth = findScreenWidth();
        Path path = lineGeneration();
        Group root = new Group(path);
        Scene scene = new Scene(root, screenHeight, screenWidth);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        terrainGeneration();
    }

    private Path lineGeneration() {
        boolean aaa = false;
        double screenHeight = findScreenHeight();
        int x = 0;
        Path path = new Path();
        MoveTo moveTo = new MoveTo(0, screenHeight - (screenHeight / 3));
            LineTo line1 = new LineTo(x, screenHeight - (screenHeight / 3));
            while (aaa == false) {
            x = x + 1;
            LineTo line2 = new LineTo(x,20 +(screenHeight - (screenHeight / 3)));
            path.getElements().add(moveTo);
            path.getElements().addAll(line1, line2);
        }
        return path;
    }

    private void terrainGeneration() {
        double p = PerlinNoise.noise(3.14, 42, 7);
        System.out.println(p);
    }


    private double findScreenHeight() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenHeight = screenBounds.getHeight();
        return screenHeight;
    }

    private double findScreenWidth() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        return screenWidth;
    }
}

