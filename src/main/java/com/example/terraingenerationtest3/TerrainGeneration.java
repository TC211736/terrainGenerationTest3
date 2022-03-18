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
    }

    private Path lineGeneration() {
        double screenHeight = findScreenHeight();
        int x = 40;
        Path path = new Path();
        MoveTo moveTo = new MoveTo(0, 250);
        LineTo line2 = new LineTo();
        double p = perlinNoiseGeneration();
        LineTo line1 = new LineTo(x, (screenHeight - (p * (screenHeight * 2 / 3))));
        for (int i = 0; i < 500; i++) {
            while (moveTo.getY() == line1.getY()) {
                double p2 = perlinNoiseGeneration();
                line2.setX(x);
                line2.setY(screenHeight - (p2 * (screenHeight / 2 / 3)));
                moveTo.setY(line1.getY());
                moveTo.setX(line1.getX());
                path.getElements().add(moveTo);
                path.getElements().addAll(line1, line2);
            }
            line1.setY(line2.getY());
            line1.setX(line2.getX());
            x = x + 40;
        }
        return path;
    }

    private Path firstLine() {
        double screenHeight = findScreenHeight();
        Path path = new Path();
        MoveTo moveTo = new MoveTo(0, (screenHeight - (screenHeight / 3)));
        LineTo line1 = new LineTo(20, (screenHeight - (screenHeight / 3)));
        path.getElements().add(moveTo);
        path.getElements().add(line1);
        return path;
    }

    public static double randomNum() {
        double low = 3.001;
        int high = 4;
        double randomNum = (Math.random() * (high - low)) + low;
        return randomNum;
    }

    private double perlinNoiseGeneration() {
        double randomNum = randomNum();
        double p = PerlinNoise.noise(randomNum, 42, 7);
        return p;
    }


    private double findScreenHeight() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenHeight = screenBounds.getHeight();
        System.out.println(screenHeight);
        return screenHeight;

    }

    private double findScreenWidth() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        return screenWidth;
    }


}

