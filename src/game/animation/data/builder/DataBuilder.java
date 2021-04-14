package game.animation.data.builder;

import game.animation.data.DataByJson;

import java.util.Collection;
import java.util.Map;

public interface DataBuilder {


    DataBuilder height(double height);

    DataBuilder width(double width);


    DataBuilder includeActions(Collection<String> includeActions);

    DataBuilder includeImages(Collection<String> includeImages);

//    DataBuilder includeFrames(Collection<Integer> includeFrames);

    DataBuilder replaceMap(Map<String, String> replaceMap);

    DataByJson build();

//    AnimationData build();


}
