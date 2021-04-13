package game.main.dialog.restart;

import game.config.BaseBuff;
import game.main.dialog.AbstractDialog;
import game.main.dialog.Block;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestartDialog extends AbstractDialog {

    private static List<BaseBuffBlock> baseBuffBlocks;


    public RestartDialog(double beginX, double beginY, int widthNum, int heightNum) {
        super(beginX, beginY, widthNum, heightNum);
        baseBuffBlocks = initBaseBuffBlock(leftX, topY);
    }


    private static List<BaseBuffBlock> initBaseBuffBlock(double leftX, double topY) {
        List<BaseBuffBlock> baseBuffBlocks = new ArrayList<>(BaseBuff.values().length);
        BaseBuffBlock restart = new BaseBuffBlock(BaseBuff.restart){
            @Override
            public void click() {


            }
        };
        baseBuffBlocks.add(restart);
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(50 + leftX, 50 + topY, 1200, 150));
        for (int col = 0; col < 2; col++) {
            int maxRow = (BaseBuff.values().length - 1) / 2;
            for (int row = 0; row < maxRow; row++) {
                int beginX = col * 600 + 50;
                int beginY = row * 60 + 150;
                blockList.add(new Block(beginX + leftX,
                        beginY + topY, 600, 60));
            }

        }
        Iterator<Block> iterator = blockList.iterator();
        baseBuffBlocks.get(0).setBlock(iterator.next());
        for (BaseBuff baseBuff : BaseBuff.values()) {
            if (baseBuff.equals(BaseBuff.restart)) continue;
            baseBuffBlocks.add(new BaseBuffBlock(baseBuff){
                @Override
                public void click() {

                }
            } .setBlock(iterator.next()));
        }
        baseBuffBlocks.add(restart);
        return baseBuffBlocks;

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
        for (BaseBuffBlock baseBuffBlock : baseBuffBlocks) {
            baseBuffBlock.draw(graphicsContext);
        }
    }


    @Override
    public Cursor mouseInDialog(double y, double x) {
        for (BaseBuffBlock baseBuffBlock : baseBuffBlocks) {
            Cursor cursor = baseBuffBlock.mouseOn(y, x);
            if (cursor != null) return cursor;
        }
        return Cursor.DEFAULT;
    }


    @Override
    public void click(int y, int x) {

    }

    @Override
    public boolean shouldClose() {
        return false;
    }


}
