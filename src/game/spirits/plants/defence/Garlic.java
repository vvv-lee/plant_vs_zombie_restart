package game.spirits.plants.defence;

import com.google.common.collect.ImmutableSortedMap;
import game.main.map.MapLawn;
import game.animation.MultiAnimation;
import game.animation.MultiAnimationBuilder;

public class Garlic extends AbstractDefence {
    private static final String health = "garlic-main";
    private static final String cracked1 = "garlic-cracked1";
    private static final String cracked2 = "garlic-cracked2";
    protected static MultiAnimationBuilder builder = MultiAnimationBuilder.builder()
                    .newData(health)
                    .newData(cracked1)
                    .newData(cracked2);
    public Garlic(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected MultiAnimation buildMultiAnimation() {
        return builder.build(this, health);
    }

    @Override
    protected ImmutableSortedMap<Double, String> buildAnimationByHpMapData() {
        animationKeyByHp = ImmutableSortedMap.of(
                0.666667, health,
                0.333333, cracked1,
                0.0, cracked2);
        return animationKeyByHp;
    }

}
