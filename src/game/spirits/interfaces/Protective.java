package game.spirits.interfaces;

import game.animation.data.AnimationData;

import java.util.List;

public interface Protective extends Health {

    List<String> clashAnimations();

    List<AnimationData> showDataList();


}
