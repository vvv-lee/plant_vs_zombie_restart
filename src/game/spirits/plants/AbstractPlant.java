package game.spirits.plants;

import game.config.PlantCard;
import game.main.map.MapLawn;
import game.animation.Animation;
import game.spirits.AbstractSprite;
import game.spirits.interfaces.Plant;
import game.spirits.interfaces.Zombie;
import game.behavior.plant.base.AbstractPlantBehaviorHandler;
import game.behavior.PlantBehavior;
import game.behavior.plant.ProduceBehaviorHandler;
import game.behavior.plant.shoot.ShootBehaviorHandler;
import game.spirits.util.Production;

public abstract class AbstractPlant extends AbstractSprite implements Plant {

    protected MapLawn mapLawn;

    protected double hp;

    protected double maxHp;

    protected PlantBehavior plantBehavior;



    public AbstractPlant(MapLawn mapLawn) {
        this.mapLawn = mapLawn;
        PlantCard plantCard = plantCard();
        this.maxHp = plantCard.getMaxHp();
        this.hp = this.maxHp;
        this.plantBehavior = new PlantBehavior(this, plantCard.getDefaultAction());
        this.x = mapLawn.getX() + plantCard().getShowOffSetX();
        this.y = mapLawn.getY() + plantCard().getShowOffSetY();
        this.setAction(plantCard.getDefaultAction());

    }

    @Override
    public double getHp() {
        return hp;
    }

    @Override
    public double beAttack(double attack) {
        this.hp = hp - attack;
        flashNum = 10;
        return 0;
    }

    protected void addShootBehavior(ShootBehaviorHandler shootBehaviorHandler) {
        plantBehavior.addBehaviorHandler(shootBehaviorHandler);
    }


    protected void addProduceBehavior(ProduceBehaviorHandler produceBehavior) {

    }

    protected void addBaseBehavior(AbstractPlantBehaviorHandler baseBehavior) {
        plantBehavior.addBehaviorHandler(baseBehavior);
    }

    @Override
    protected void update() {
        updateBehavior();
    }

    @Override
    protected Production doAction() {
        return plantBehavior.doAction(super.doAction());
    }

    @Override
    protected Animation buildAnimation() {
        return plantCard().createAnimation(this);
    }


    @Override
    public boolean needRemove() {
        return hp <= 0;
    }

    @Override
    public void attackByZombie(Zombie zombie) {

    }

    @Override
    protected double getThisActionFrameSpeed() {
        return 1 / 2.0;
    }

    @Override
    public void updateBehavior() {
        String newAction = plantBehavior.updateBehavior();

        this.setAction(newAction);
    }


}
