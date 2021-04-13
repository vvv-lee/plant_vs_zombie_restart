package game.test;

import game.resourceUtil.Fragment;

public class DrawUtil {

    private JsonData jsonData;

    public DrawUtil(JsonData jsonData) {
        this.jsonData = jsonData;
    }

//    public DrawUtil(ReanimData reanimData) {
//        this.jsonData = new ReanimAdaptor(reanimData);
//    }

    public void draw(String action, int frame, double y, double x) {
        for (Fragment fragment : jsonData.getFragments(action, frame)) {
            fragment.drawImage(x, y);
        }
    }

//    public static void main(String[] args) {
//        DrawUtil drawUtil = new DrawUtil(new ReanimAdaptor(
//                new ReanimData("reanim/zombie/zombie.reanim")));
//        drawUtil.draw("eat", 0, 0, 0);
//    }


}
