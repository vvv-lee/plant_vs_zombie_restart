package game.spirits.plants.produce;

import game.main.map.MapLawn;
import game.spirits.plants.AbstractPlant;


public class SunFlower extends AbstractPlant {
    public SunFlower(MapLawn mapLawn) {
        super(mapLawn);
    }
    //        ProducePlant  implements Plant {
//
//
//    public SunFlower(MapLawn mapLawn) {
//        super(mapLawn);
//    }
//
//    @Override
//    public List<Recyclable> productionRecyclable() {
//        return Collections.singletonList(new Sun(this.getY()-50,this.getX()+50).throwIt());
//    }
//
//    @Override
//    public void draw(GraphicsContext graphicsContext) {
//        ColorAdjust colorAdjust = new ColorAdjust();
//        colorAdjust.setBrightness(getBrightness());
////        colorAdjust.setSaturation(1);
////        graphicsContext.setEffect(colorAdjust);
//        DropShadow shadow=new DropShadow();
//        shadow.setBlurType(BlurType.THREE_PASS_BOX);
//        shadow.setColor(Color.color(0.3,0.3,0.3,0.3));
//        InnerShadow innerShadow=new InnerShadow();
//        innerShadow.setChoke(1);
//        innerShadow.setHeight(50);
//        innerShadow.setWidth(50);
//        innerShadow.setColor(Color.color(0,0,1,0.5));
////        graphicsContext.setEffect(innerShadow);
//        super.draw(graphicsContext);
//        graphicsContext.setEffect(null);
//
//
//    }
//
//
//
//    public static double map(double value, double start, double stop, double targetStart, double targetStop) {
//        return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
//    }

}
