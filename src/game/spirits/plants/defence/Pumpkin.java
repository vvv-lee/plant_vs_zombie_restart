package game.spirits.plants.defence;

import com.google.common.collect.ImmutableSortedMap;
import game.main.map.MapLawn;
import game.animation.MultiAnimation;
import game.animation.MultiAnimationBuilder;

public class Pumpkin extends AbstractDefence {

    private static final String health = "Pumpkin-main";
    private static final String damage1 = "Pumpkin-damage1";
    private static final String damage2 = "Pumpkin-damage2";
    private static final String damage3 = "Pumpkin-damage3";
    protected static MultiAnimationBuilder builder = MultiAnimationBuilder.builder()
                    .newData(health)
                    .newData(damage1)
                    .newData(damage2).newData(damage3);

    public Pumpkin(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected MultiAnimation buildMultiAnimation() {
        return builder.build(this,health);
    }

    @Override
    protected ImmutableSortedMap<Double, String> buildAnimationByHpMapData() {
        return animationKeyByHp = ImmutableSortedMap.of(
                0.75, health,
                0.5, damage1,
                0.25, damage2, 0.0, damage3);
    }
}
