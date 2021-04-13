package game.test;

import game.resourceUtil.animation.data.AnimationData;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractDataBuilder {


    private Collection<String> includeActions;

    private Collection<String> includeImages;

    private Collection<Integer> includeFrames;

    private Map<String, String> replaceMap;


    public abstract void readDataFromFile(String file);

    public abstract boolean haveInitData();


    public void checkFile() {
        if (!haveInitData()) {
            throw new RuntimeException("尚未读取到动画文件，无法设置");
        }

    }


    public void includeActions(Collection<String> includeActions) {
        if (includeActions != null && includeActions.size() == 0) {
            throw new RuntimeException("这样创建出来的动画为空");
        }
        checkFile();
        this.includeActions = includeActions;

    }

    public void includeImages(Collection<String> includeImages) {
        if (includeImages != null && includeImages.size() == 0) {
            throw new RuntimeException("这样创建出来的动画为空");
        }
        checkFile();
        this.includeImages = includeImages;

    }


    public void replaceMap(Map<String, String> replaceMap) {
        checkFile();
        this.replaceMap = replaceMap;


    }

    public abstract AnimationData build();


}
