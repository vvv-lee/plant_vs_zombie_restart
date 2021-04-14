package game.spirits.plants.leaf;

import game.config.animation.Animations;
import game.animation.AbstractAnimation;

import game.animation.data.AnimationData;
import game.spirits.interfaces.Plant;


public class SingleLeaf extends AbstractAnimation implements Leaf {

    static AnimationData cacheAnimationData =
            Animations.getByKeyAndFile("PeaShooterSingle", "leaf");


    public SingleLeaf(Plant plant) {
        this.coordinate = plant;
        super.animationData = cacheAnimationData;
        this.newAction("leaf_idle");

    }

    public double[] getXYOffset() {
        int realPicFrameNum = getRealPicFrameNum();
        return new double[]{
                xList[realPicFrameNum], yList[realPicFrameNum]
        };

    }

    private double[] xList = new double[]{
            0, 0.4, 0.7, 1, 1.5,
            2, 2.4, 3.5, 4.5, 5.6,
            7, 8.4, 9.8, 8.4, 7,
            5.6, 4.5, 3.5, 2.4, 2,
            1.5, 1, 0.7, 0.4, 0
    };

    private double[] yList = new double[]{
            0.0, -1.7, -3.2, -4.9, -5.2,
            -5.6, -5.9, -5.6, -5.2, -4.9,
            -4.1, -3.2, -2.4, -3.2, -4.1,
            -4.9, -5.2, -5.6, -5.9, -5.6,
            -5.2, -4.9, -3.2, -1.7, 0.0};


}
