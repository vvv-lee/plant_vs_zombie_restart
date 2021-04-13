package game.test;

import game.resourceUtil.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ReanimAdaptor extends JsonData {
    ReanimData reanimData;

    public ReanimAdaptor(String path, ReanimData reanimData) {
        super(path);
        this.reanimData = reanimData;
    }



    @Override
    public List<Fragment> getFragments(String action, int frame) {
        String actionKey = ReanimData.getActionKey(action);
        int frameIndex = reanimData.getFrameIndexInList(actionKey, frame);
        List<Fragment> result = new ArrayList<>(reanimData.dataMap.size());
        for (List<Fragment> reanimFrameList : reanimData.dataMap.values()) {
            result.add(reanimFrameList.get(frameIndex));
        }
        return result;
    }

    @Override
    public double xSpeed(String action, int frameNum) {
        String actionKey = ReanimData.getActionKey(action);
        int frameIndex = reanimData.getFrameIndexInList(actionKey, frameNum);
        List<Fragment> groundList = reanimData.dataMap.get("_ground");
        Fragment fragment = groundList.get(frameIndex);
        Fragment next = groundList.get((frameIndex + 1) % groundList.size());
        return next.getX() - fragment.getX();
    }


}
