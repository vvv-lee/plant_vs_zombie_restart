package game.spirits.plants.shroom;

import game.config.animation.Animations;
import game.main.Game;
import game.animation.AbstractAnimation;
import game.animation.data.AnimationData;
import game.spirits.interfaces.Coordinate;


public class ZZZ extends AbstractAnimation{

    private static AnimationData zzz = Animations.getFromMap("zzz-main");


    public ZZZ(Coordinate coordinate) {
        this.coordinate=coordinate;
        this.animationData=zzz;
        this.newAction("zzz");
    }


    @Override
    public void drawWithOffset(double offSetX, double offSetY) {
        Game.getGraphicsContext().setGlobalAlpha(0.75);
        super.drawWithOffset(offSetX, offSetY);
        Game.getGraphicsContext().setGlobalAlpha(1);
    }
}
