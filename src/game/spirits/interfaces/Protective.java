package game.spirits.interfaces;

import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.DataByJson;

import java.util.List;

public interface Protective extends Health {

    List<String> clashAnimations();

    List<AnimationData> showDataList();


}
