package game.main.map;

public class MapUtil {


    public static int[] baseRowYNum = {180, 275, 380, 470, 570};
    public static int[] colXNum = {250, 335, 410, 495, 575,
            657, 735, 815, 895
    };

    public static int getRow(String name, double y) {
        return getRowByY(getRowArrayByName(name), y);
    }

    public static int getCol(String name, double x) {
        return getColByX(getColArrayByName(name), x);
    }


    public static int[] getRowArrayByName(String name) {
        return baseRowYNum;
    }

    public static int[] getColArrayByName(String name) {
        return colXNum;
    }

    public static int getXByCol(String name, int colNum) {
        if (colNum > 9) colNum = 8;
        return getXByCol(getColArrayByName(name), colNum);
    }

    public static int getYByRow(String name, int rowNum) {
        return getYByRow(getRowArrayByName(name), rowNum);

    }


    private static int getRowByY(int[] baseRowYNum, double y) {
        int i = 0;
        while (i < baseRowYNum.length) {
            if (y <= baseRowYNum[i]) {
                return i;
            }
            i++;
        }
        return 4;


    }


    private static int getColByX(int[] colXNum, double x) {
        int i = 0;
        if (x < colXNum[0]) return 0;
        while (i < colXNum.length - 1) {
            int num = i;
            if (colXNum[num] <= x && x < colXNum[num + 1]) {
                return i;
            }
            i++;
        }
        return i;
    }



    private static int getXByCol(int[] colXNum, int colNum) {
        if (colNum > 9) colNum = 8;
        return colXNum[colNum];
    }

    private static int getYByRow(int[] rowYNum, int rowNum) {

        if (rowNum >= 5) rowNum = 4;
        return rowYNum[rowNum];
    }

//    public static int getPlantRowNumByY(int y) {
//        int i = 0;
//        while (i < 5) {
//            int num = i;
//            if (plantsRowYNum[num] >= y) {
//                return i;
//            }
//            i++;
//        }
//        return 4;
//    }
//
//

//
//    public static int getPlantsYByRowNum(String map, int rowNum) {
//        if (map != null) throw new RuntimeException("");
//        if (rowNum >= 5) rowNum = 1;
//        return plantsRowYNum[rowNum];
//    }
//
//

//
//    public static int getColNumByX(int x) {
//        int result = 0;
//        for (int col : colXNum) {
//            if (col >= x) return result;
//            result++;
//        }
//        return 8;
//
//    }
}
