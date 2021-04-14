package game.animation.data.builder;

import game.animation.data.DataByJson;

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
