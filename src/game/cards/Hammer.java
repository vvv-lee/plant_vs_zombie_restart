package game.cards;


import game.main.Game;
import game.main.GameCards;
import game.main.map.GameMap;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.List;

public class Hammer extends ToolCard {

    private static List<Image> imageList = new ArrayList<>();

    static {
        for (int index = 0; index < 8; index++) {
            imageList.add(new Image("pic/Cards/hammerHit/" + index + ".png"));
        }

    }


    private int hitTimer = 0;

    private boolean isHitting = false;

    public Hammer( double y, double x, int residue) {
        super(y, x, residue);
    }

    @Override
    public void showInMap(double mouseY, double mouseX) {
        Image image;
        if (isHitting) image = imageList.get(hitTimer);
        else image = toolImage;
        Game.getGraphicsContext().drawImage(image, mouseX - 36, mouseY - 36);
    }

    @Override
    public boolean needKeep() {
        return residue > 0 || residue == -1;
    }

    @Override
    protected boolean canSelect() {
        return true;
    }

    @Override
    public void notUse() {
        super.notUse();
        hitTimer = 0;
        isHitting = false;
    }

    @Override
    public void update() {
        if (hitTimer == 7) {
            isHitting = false;
            hitTimer = 0;
        }
        hitTimer++;
    }

    private void hit() {
        isHitting = true;
        hitTimer = 0;
    }


    @Override
    public Card successUse() {
        hit();
        return this;
    }

    @Override
    protected boolean effectToMap(double mouseY, double mouseX, int bytn) {
        return true;
    }

}
