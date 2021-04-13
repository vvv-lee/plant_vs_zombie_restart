package game.behavior;

import game.behavior.plant.BehaviorHandler;
import game.spirits.interfaces.Plant;
import game.spirits.interfaces.Sprite;
import game.spirits.util.Production;

import java.util.ArrayList;
import java.util.List;

public class SpriteBehavior {
    protected Sprite sprite;

    protected String defaultAction;

    protected List<BehaviorHandler> behaviorHandlerList = new ArrayList<>();

    public SpriteBehavior(Sprite sprite) {
        this(sprite, "idle");
    }

    public SpriteBehavior(Sprite sprite, String defaultAction) {
        this.sprite = sprite;
        this.defaultAction = defaultAction;
    }


    public String updateBehavior() {
        String nowAction = sprite.nowAction();

        for (BehaviorHandler behaviorHandler : behaviorHandlerList) {
            nowAction = behaviorHandler.updateBehavior(nowAction);
            if (nowAction == null) nowAction = defaultAction;
            if (!behaviorHandler.canUpdate(nowAction)){
                return nowAction;
            }
        }
        return nowAction;

    }

    public Production doAction(Production production) {
        for (BehaviorHandler behaviorHandler : behaviorHandlerList) {

            behaviorHandler.addNewSprites(production);
        }
        return production;
    }
    public void addBehaviorHandler(BehaviorHandler behaviorHandler){
        System.out.println(behaviorHandler);
        if(behaviorHandler==null){
            throw new RuntimeException();
        }
        this.behaviorHandlerList.add(behaviorHandler);
    }

}
