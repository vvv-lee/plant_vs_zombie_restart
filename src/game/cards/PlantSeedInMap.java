package game.cards;

import game.config.PlantCard;
import game.main.GameCards;
import game.spirits.util.ThrowStatus;

public class PlantSeedInMap extends PlantSeed {

    protected ThrowStatus throwStatus;

    protected double xSpeed = 0;
    protected double ySpeed = 0;
    protected boolean useSuccess = false;

    public PlantSeedInMap( double y, double x, PlantCard plantCard) {
        super( y, x, plantCard);
    }

    public boolean needRemove() {
        return useSuccess;
    }


    public PlantSeedInMap throwIt(double throwHeight, int throwToTopTime, double xSpeed) {
        throwStatus = new ThrowStatus(throwHeight, throwToTopTime, xSpeed);
        this.xSpeed = 0;
        this.ySpeed = 0;
        return this;
    }



    //    public PlantSeedInMap(PlantCard plantCard, int y, int x) {
//        super();
//        super.plantCard = plantCard;
//        super.y = y;
//        super.x = x;
//    }
//
//    @Override
//    public boolean effectToMap(GameMap gameMap, int mouseY, int mouseX) {
//        useSuccess = super.effectToMap(gameMap, mouseY, mouseX);
//        return useSuccess;
//    }
//
//    @Override
//    public void update() {
//    }
//

//
//
//    @Override
//    public void draw(Graphics2D graphics) {
//        if (!haveSelect) super.draw(graphics);
//    }
//
//
//    @Override
//    public boolean needRemove() {
//        return useSuccess;
//    }
//
//    @Override
//    public List<? extends Spirit> doActionAndUpdate() {
//        if (throwUtil == null) return null;
//        this.x = x + throwUtil.getXSpeed();
//        this.y = y + throwUtil.getYSpeed();
//        throwUtil.update();
//        return null;
//    }
//
//
//    @Override
//    public Card useAndBeginCd() {
//        return null;
//    }
//    @Override
//    public PlantSeedInMap throwIt() {
//        return throwIt(50, 10, ThrowUtil.randomXMoveValue(0.5));
//    }
//
//    @Override
//    protected boolean canSelect() {
//        return true;
//    }
}
