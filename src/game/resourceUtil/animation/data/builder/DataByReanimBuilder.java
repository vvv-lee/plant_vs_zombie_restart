package game.resourceUtil.animation.data.builder;

import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.DataByJson;

public class DataByReanimBuilder extends AbstractDataBuilder {


    public DataByReanimBuilder(String cacheKey, String path) {
        super(cacheKey, path);
    }

    public DataByReanimBuilder(String path) {
        super(path);
    }

    @Override
    public DataBuilder readDataFromFile(String file) {
        return null;
    }

    @Override
    public boolean haveInitData() {
        return false;
    }

    @Override
    protected DataByJson buildData() {
        return null;
    }
}
