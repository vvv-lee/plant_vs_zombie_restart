package game.cards;


import game.config.PlantCard;
import game.main.Game;
import game.main.GameCards;
import game.main.map.GameMap;
import game.main.map.MapLawn;
import javafx.scene.canvas.GraphicsContext;

public class Glove extends ToolCard {


    private MapLawn holdMapLawn;

    public Glove() {
        this(0, 0, 0);

    }

    public Glove(double y, double x, int residue) {
        super(y, x, residue);

    }

    @Override
    public Card successUse() {
        if (haveSelectPlant()) return this;
        haveSelect = false;
        if (residue > 0) residue--;
        return this;
    }

    @Override
    public void notUse() {
        super.notUse();
        this.holdMapLawn = null;
    }


    @Override
    protected boolean canSelect() {
        return true;
    }

    @Override
    protected boolean effectToMap(double mouseY, double mouseX, int btn) {
        MapLawn clickMapLawn = Game.getGameMap().getLawnByYX(mouseY, mouseX);
        if (!haveSelectPlant()) {
            if (clickMapLawn.havePlant()) {
                this.holdMapLawn = clickMapLawn;
                return true;
            } else {
                return false;
            }
        }
        if (clickMapLawn.havePlant()) return false;
        clickMapLawn.addPlant(this.holdMapLawn.getPlant());
        this.holdMapLawn.removePlant();
        this.holdMapLawn = null;
        return true;
    }


    @Override
    public void showInMap(double mouseY, double mouseX) {
        PlantCard usePlantCard = getUsePlantCard();
        if (usePlantCard != null) {
            Game.getGameMap().getLawnByYX(mouseY, mouseX).drawImage(usePlantCard.getOpacityImage());
            Game.getGraphicsContext().drawImage(
                    usePlantCard.getPlantImage(),
                    mouseX - 36, mouseY - 36
            );
        } else {
            Game.getGraphicsContext().drawImage(
                    toolImage, mouseX - 36, mouseY - 36
            );
        }


    }

    //    @Override
//    public void showInMap(GameMap gameMap, int mouseY, int mouseX) {
//        gameMap.getOffScreenGraphics().drawImage(showInMapImage(), mouseX, mouseY - 40, null);
//        if (getUsePlantCard() != null)
//            gameMap.getLawnByYX(mouseY, mouseX).drawTransparentPlant(getUsePlantCard());
//    }
//
//    @Override
//    public BufferedImage showInMapImage() {
//        if (!haveSelectPlant()) return super.showInMapImage();
//        else return mapLawn.getUsePlantCard().getSelectImage();
//    }
//
//    private boolean haveSelectPlant() {
//        return this.mapLawn != null;
//    }
//
//    @Override
//    public boolean effectToMap(GameMap gameMap, int mouseY, int mouseX) {
//        int row = MapUtil.getMouseRow(mouseY);
//        int col = MapUtil.getMouseCol(mouseX);
//        return this.clickMapLawn(gameMap.getLawn(row, col));
//    }
//
    @Override
    protected boolean needKeep() {
        return true;
    }

    //
//    private boolean clickMapLawn(MapLawn clickMapLawn) {
//        if (!haveSelectPlant()) {
//            if (clickMapLawn.havePlant()) {
//                this.mapLawn = clickMapLawn;
//                return true;
//            } else {
//                return false;
//            }
//        }
//        if (clickMapLawn.havePlant()) return false;
//        clickMapLawn.addPlant(this.mapLawn.getUsePlantCard());
//        this.mapLawn.removePlant();
//        this.mapLawn = null;
//        belongGameCards.addSunPower(-getNeedSunPower());
//        accurateNeedSunPower = 50;
//        return true;
//    }
//
    private PlantCard getUsePlantCard() {
        if (haveSelectPlant()) return this.holdMapLawn.getPlant().plantCard();
        return null;
    }

    private boolean haveSelectPlant() {
        return this.holdMapLawn != null;
    }
}

