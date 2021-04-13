package game.main.dialog.restart;

import com.google.common.collect.Lists;
import game.config.BaseBuff;
import game.main.dialog.Block;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.text.MessageFormat;
import java.util.List;

public abstract class BaseBuffBlock {

    private BaseBuff baseBuff;

    private List<String> showUpdate;


    private String showDescription;

    private Block block;

    private UpdateBtn moneyBtn;

    private UpdateBtn diamondBtn;

    public BaseBuffBlock(BaseBuff baseBuff) {
        this.baseBuff = baseBuff;
        showDescription = MessageFormat.format(
                baseBuff.getDescription(),
                baseBuff.getValues().get(baseBuff.getNowLevel()));
    }

    public BaseBuffBlock setBlock(Block block) {
        this.block = block;
        moneyBtn = new UpdateBtn(block.getBeginX() + 250 + 40
                , block.getBeginY() + 10,
                true, baseBuff.getCost().get(baseBuff.getNowLevel() + 1));
        diamondBtn = new UpdateBtn(block.getBeginX() + 150 + 250
                , block.getBeginY() + 10, false, 50);
        return this;
    }


    public void draw(GraphicsContext graphicsContext) {
        double drawX = block.getBeginX();
        double drawY = block.getBeginY();


        graphicsContext.setFont(Font.font("Microsoft JhengHei Bold", 20));

        graphicsContext.setFill(Paint.valueOf("#01e901"));
        graphicsContext.fillText(baseBuff.name(), drawX, drawY);


        graphicsContext.setFont(Font.font("Microsoft JhengHei Bold", 14));
        graphicsContext.setFill(Paint.valueOf("#2342da"));
        graphicsContext.fillText(getShowUpdate().get(0), drawX + 150, drawY);


        graphicsContext.setFill(Paint.valueOf("#dad423"));
        graphicsContext.fillText(getShowUpdate().get(1), drawX + 200, drawY);

        graphicsContext.setFill(Paint.valueOf("#FFFFFF"));
        drawY = drawY + 25;
        graphicsContext.fillText(showDescription, drawX, drawY);
        moneyBtn.draw(graphicsContext);
        diamondBtn.draw(graphicsContext);
    }


    public List<String> getShowUpdate() {
        if (showUpdate == null) {
            if (baseBuff.getNowLevel() == baseBuff.getCost().size() - 1) {
                showUpdate = Lists.newArrayList("已经满级", "");
            } else {
                double value = baseBuff.getValues().get(baseBuff.getNowLevel() + 1);

                showUpdate = Lists.newArrayList("下一级 ", String.valueOf(((int) value)));
            }
        }
        return showUpdate;
    }

    public String getShowDescription() {
        if (showDescription == null) {
            showDescription = MessageFormat.format(
                    baseBuff.getDescription(),
                    baseBuff.getValues().get(baseBuff.getNowLevel()));
        }
        return showDescription;

    }

    public abstract void click();


    public Cursor mouseOn(double y, double x) {
        if (block.mouseOn(y, x)) {
            if (diamondBtn.mouseOn(y, x) || moneyBtn.mouseOn(y, x)) return Cursor.HAND;
            return Cursor.DEFAULT;
        }
        return null;

    }

}
