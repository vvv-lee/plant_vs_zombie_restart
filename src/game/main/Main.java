package game.main;

import game.config.PlantCard;
import game.main.map.BaseLightMap;
import game.main.map.BaseNightMap;
import game.main.map.GameMap;
import game.resourceUtil.Resources;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game.rouge = true;
        Game.init();

        GameScene gameScene = Game.getGameScene();
        primaryStage.setScene(gameScene);
        beforeStart();
        start(gameScene,primaryStage);


    }

    private void  beforeStart(){

//        Iterator<PlantCard> iterator =new HashSet<>(PlantCard.cardMap.values()).iterator();
//        for (int row = 0; row < 5; row++) {
//            for (int col = 0; col < 9; col++) {
//                if (iterator.hasNext()) {
//                    PlantCard next = iterator.next();
//                    Game.getGameMap().getLawn(row, col).addPlant(next);
////                    next.newPlant(Game.getGameMap().getLawn(row, col));
//
//                }
//            }
//        }


    }
    private void  start(GameScene gameScene,Stage primaryStage){
        primaryStage.setTitle("植物大战僵尸?    Restart");
        primaryStage.setWidth(1416);
        primaryStage.setHeight(638);
        primaryStage.show();

        gameScene.start(primaryStage);

    }

}
