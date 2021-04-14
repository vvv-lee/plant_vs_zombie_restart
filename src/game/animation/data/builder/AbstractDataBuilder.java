package game.animation.data.builder;

import game.config.animation.Animations;
import game.animation.data.DataByJson;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractDataBuilder implements DataBuilder {

    protected String cacheKey;

    protected double height;

    protected double width;

    protected double crashX;

    protected double crashY;

    @Override
    public DataBuilder crashX(double crashX) {
         this.crashX=crashX;
         return this;
    }

    @Override
    public DataBuilder crashY(double crashY) {
        this.crashY=crashY;
        return this;
    }

    protected Collection<String> includeActions;

    protected Collection<String> includeImages;

    protected Collection<Integer> includeFrames;

    protected Map<String, String> replaceMap;

    public AbstractDataBuilder(String cacheKey, String path) {
        this(path);
        this.cacheKey = cacheKey;
    }

    public AbstractDataBuilder(String path) {
        readDataFromFile(path);
    }

    public abstract DataBuilder readDataFromFile(String file);

    public abstract boolean haveInitData();

    public void checkFile() {
        if (!haveInitData()) {
            throw new RuntimeException("尚未读取到动画文件，无法设置");
        }

    }

    public DataBuilder includeActions(Collection<String> includeActions) {
        if (includeActions != null && includeActions.size() == 0) {
            throw new RuntimeException("这样创建出来的动画为空");
        }
        checkFile();
        this.includeActions = includeActions;
        return this;

    }

    public DataBuilder includeImages(Collection<String> includeImages) {
        if (includeImages != null && includeImages.size() == 0) {
            throw new RuntimeException("这样创建出来的动画为空");
        }
        checkFile();
        this.includeImages = includeImages;
        return this;

    }

    public DataBuilder replaceMap(Map<String, String> replaceMap) {
        checkFile();
        this.replaceMap = replaceMap;
        return this;
    }

    @Override
    public DataBuilder height(double height) {
        checkFile();
        this.height = height;
        return this;
    }

    @Override
    public DataBuilder width(double width) {
        checkFile();
        this.width = width;
        return this;
    }


    public DataByJson build() {
        DataByJson result = buildData();
        if (cacheKey != null) Animations.cacheMap.put(cacheKey, result);
        return result;

    }

    protected abstract DataByJson buildData();
}