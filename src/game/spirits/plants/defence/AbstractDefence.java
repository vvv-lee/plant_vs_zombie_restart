package game.spirits.plants.defence;

import com.google.common.collect.ImmutableSortedMap;
import game.main.map.MapLawn;
import game.animation.Animation;
import game.animation.MultiAnimation;
import game.spirits.plants.AbstractPlant;

import java.util.Map;

public abstract class AbstractDefence extends AbstractPlant {

    protected MultiAnimation multiAnimation;

    protected ImmutableSortedMap<Double, String> animationKeyByHp;


    public AbstractDefence(MapLawn mapLawn) {
        super(mapLawn);
        this.animationKeyByHp = buildAnimationByHpMapData();
    }

    @Override
    protected void update() {
        super.update();
        tryChangeAnimation();
    }

    protected void tryChangeAnimation() {
        multiAnimation.useByKey(getAnimationKeyByHp());
    }

    @Override
    protected Animation buildAnimation() {
        multiAnimation = buildMultiAnimation();
        return multiAnimation;
    }


    protected String getAnimationKeyByHp() {
        double value = hp / maxHp;
        Map.Entry<Double, String> findValue = getAnimationMap().lowerEntry(value);
        String result;
        if (findValue == null) result = beforeDieAnimationDataKey();
        else result = findValue.getValue();
        return result;
    }


    protected String beforeDieAnimationDataKey() {
        return getAnimationMap().firstEntry().getValue();
    }

    protected String healthAnimationDataKey() {
        return getAnimationMap().lastEntry().getValue();
    }

    protected ImmutableSortedMap<Double, String> getAnimationMap() {
        if (animationKeyByHp == null) animationKeyByHp = buildAnimationByHpMapData();
        return animationKeyByHp;
    }

    protected abstract MultiAnimation buildMultiAnimation();

    protected abstract ImmutableSortedMap<Double, String> buildAnimationByHpMapData();


}
