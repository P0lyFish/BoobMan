package main.java.graphics;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import main.java.Board;

public class AllSprite {
    private final int SIZE;
    private int x, y;
    private int[] pixels; // diem anh
    private SpriteSheet2 sheet = SpriteSheet2.tiles;

    public AllSprite(int size, int xx, int yy) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        x = xx;
        y = yy;
        sheet = SpriteSheet2.tiles;

        // luu tru diem anh
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                pixels[i + j * SIZE] = sheet.pixels[(i + x) + (j + y) * sheet.SIZE];
            }
        }
    }

    public static AllSprite portal = new AllSprite(16, 393, 789);
    public static AllSprite portal2 = new AllSprite(16, 409, 789);
    public static AllSprite wall = new AllSprite(16, 737, 110);
    public static AllSprite brick = new AllSprite(16, 721,110);
    public static AllSprite brick_exploded0 = new AllSprite(16, 495,159);
    public static AllSprite brick_exploded1 = new AllSprite(16, 543,159);
    public static AllSprite brick_exploded2 = new AllSprite(16, 591,159);

    public static AllSprite grass = new AllSprite(16, 689, 110);
    public static AllSprite bomb_0 = new AllSprite(16, 470, 0);
    public static AllSprite bomb_1 = new AllSprite(16, 486, 0);
    public static AllSprite bomb_2 = new AllSprite(16, 502, 0);

    //
    public static AllSprite bomb_exploded0 = new AllSprite(16, 454, 32);
    public static AllSprite bomb_exploded1 = new AllSprite(16, 422, 32);
    public static AllSprite bomb_exploded2 = new AllSprite(16, 390, 32);
    //
    public static AllSprite explosion_vertical0 = new AllSprite(16, 630, 16);
    public static AllSprite explosion_vertical1 = new AllSprite(16, 614, 16);
    public static AllSprite explosion_vertical2 = new AllSprite(16, 598, 16);
    public static AllSprite explosion_vertical3 = new AllSprite(16, 582, 16);
    //
    public static AllSprite explosion_horizontal0 = new AllSprite(16, 374, 32);
    public static AllSprite explosion_horizontal1 = new AllSprite(16, 358, 32);
    public static AllSprite explosion_horizontal2 = new AllSprite(16, 342, 32);
    public static AllSprite explosion_horizontal3 = new AllSprite(16, 326, 32);
    //
    public static AllSprite explosion_horizontal_left_last0 = new AllSprite(16, 566, 16);
    public static AllSprite explosion_horizontal_left_last1 = new AllSprite(16, 550, 16);
    public static AllSprite explosion_horizontal_left_last2 = new AllSprite(16, 534, 16);
    public static AllSprite explosion_horizontal_left_last3 = new AllSprite(16, 518, 16);
    //
    public static AllSprite explosion_horizontal_right_last0 = new AllSprite(16, 438, 16);
    public static AllSprite explosion_horizontal_right_last1 = new AllSprite(16, 422, 16);
    public static AllSprite explosion_horizontal_right_last2 = new AllSprite(16, 406, 16);
    public static AllSprite explosion_horizontal_right_last3 = new AllSprite(16, 390, 16);
    //
    public static AllSprite explosion_vertical_top_last0 = new AllSprite(16, 374, 16);
    public static AllSprite explosion_vertical_top_last1 = new AllSprite(16, 358, 16);
    public static AllSprite explosion_vertical_top_last2 = new AllSprite(16, 342, 16);
    public static AllSprite explosion_vertical_top_last3 = new AllSprite(16, 326, 16);
    //
    public static AllSprite explosion_vertical_down_last0 = new AllSprite(16, 502, 16);
    public static AllSprite explosion_vertical_down_last1 = new AllSprite(16, 486, 16);
    public static AllSprite explosion_vertical_down_last2 = new AllSprite(16, 470, 16);
    public static AllSprite explosion_vertical_down_last3 = new AllSprite(16, 454, 16);

    //item
    public static AllSprite bomb_item = new AllSprite(16, 16, 48);
    public static AllSprite flame_item = new AllSprite(16, 0, 48);
    public static AllSprite speed_item = new AllSprite(16, 48, 48);


    //player1
    public static AllSprite player_up = new AllSprite(21,217, 0);
    public static AllSprite player_down = new AllSprite(21, 1, 0);
    public static AllSprite player_left = new AllSprite(21, 145, 0);
    public static AllSprite player_right = new AllSprite(21, 72, 0);

    public static AllSprite player_up_1 = new AllSprite(21, 240, 0);
    public static AllSprite player_up_2 = new AllSprite(21, 266, 0);

    public static AllSprite player_down_1 = new AllSprite(21, 26, 0);
    public static AllSprite player_down_2 = new AllSprite(21, 48, 0);

    public static AllSprite player_left_1 = new AllSprite(21, 168, 0);
    public static AllSprite player_left_2 = new AllSprite(21, 192, 0);

    public static AllSprite player_right_1 = new AllSprite(21, 97, 0);
    public static AllSprite player_right_2 = new AllSprite(21, 121, 0);

    public static AllSprite player_dead = new AllSprite(21, 168, 25);
    public static AllSprite player_dead1 = new AllSprite(21, 145, 25);
    public static AllSprite player_dead2 = new AllSprite(21, 121, 25);
    public static AllSprite player_dead3 = new AllSprite(21, 97, 25);
    public static AllSprite player_dead4 = new AllSprite(21, 49, 24);
    public static AllSprite player_dead5 = new AllSprite(21, 25, 24);
    public static AllSprite player_dead6 = new AllSprite(21, 1, 24);

    //player__2
    public static AllSprite player_up__2 = new AllSprite(21,75, 244);
    public static AllSprite player_down__2 = new AllSprite(21, 171, 220);
    public static AllSprite player_left__2 = new AllSprite(21, 3, 244);
    public static AllSprite player_right__2 = new AllSprite(21, 241, 220);

    public static AllSprite player_up_1__2 = new AllSprite(21, 98, 244);
    public static AllSprite player_up_2__2 = new AllSprite(21, 124, 244);

    public static AllSprite player_down_1__2 = new AllSprite(21, 196, 220);
    public static AllSprite player_down_2__2 = new AllSprite(21, 218, 220);

    public static AllSprite player_left_1__2 = new AllSprite(21, 25, 244);
    public static AllSprite player_left_2__2 = new AllSprite(21, 50, 244);

    public static AllSprite player_right_1__2 = new AllSprite(21, 267, 220);
    public static AllSprite player_right_2__2 = new AllSprite(21, 290, 220);

    public static AllSprite player_dead__2 = new AllSprite(21, 146, 244);
    public static AllSprite player_dead1__2 = new AllSprite(21, 169, 244);
    public static AllSprite player_dead2__2 = new AllSprite(21, 193, 244);
    public static AllSprite player_dead3__2 = new AllSprite(21, 217, 244);
    public static AllSprite player_dead4__2 = new AllSprite(21, 242, 244);
    public static AllSprite player_dead5__2 = new AllSprite(21, 266, 244);
    public static AllSprite player_dead6__2 = new AllSprite(21, 289, 244);

    //enemy
    public static AllSprite enemy1_1 = new AllSprite(16, 426, 215);
    public static AllSprite enemy1_2 = new AllSprite(16, 442, 215);
    public static AllSprite enemy1_3 = new AllSprite(16, 458, 215);

    public static AllSprite enemy1_dead1 = new AllSprite(16, 474, 215);
    public static AllSprite enemy1_dead2 = new AllSprite(16, 490, 215);


    //enemy2
    public static AllSprite oneal_EAST_STEP_LEFT = new AllSprite(16, 330, 324);
    public static AllSprite oneal_EAST_STEP_RIGHT = new AllSprite(16, 362, 324);
    public static AllSprite oneal_EAST_STOP = new AllSprite(16, 346, 324);

    public static AllSprite oneal_NORTH_STEP_LEFT = new AllSprite(16, 602, 305);
    public static AllSprite oneal_NORTH_STEP_RIGHT = new AllSprite(16, 314, 324);
    public static AllSprite oneal_NORTH_STOP = new AllSprite(16, 618, 306);

    public static AllSprite oneal_WEST_STEP_LEFT = new AllSprite(16, 554, 306);
    public static AllSprite oneal_WEST_STEP_RIGHT = new AllSprite(16, 586, 306);
    public static AllSprite oneal_WEST_STOP = new AllSprite(16, 570, 306);

    public static AllSprite oneal_SOUTH_STEP_LEFT = new AllSprite(16, 378, 324);
    public static AllSprite oneal_SOUTH_STEP_RIGHT = new AllSprite(16, 410, 324);
    public static AllSprite oneal_SOUTH_STOP = new AllSprite(16, 394, 324);


    public static AllSprite oneal_VANISHING_1 = new AllSprite(16, 426, 323);
    public static AllSprite oneal_VANISHING_2 = new AllSprite(16, 442, 324);
    public static AllSprite oneal_VANISHING_3 = new AllSprite(16, 442, 324);


    //dragon
    public static AllSprite dragon_WEST_STEP_LEFT = new AllSprite(16, 474, 233);
    public static AllSprite dragon_WEST_STEP_RIGHT = new AllSprite(16, 490, 233);
    public static AllSprite dragon_WEST_STOP = new AllSprite(16, 506, 233);

    public static AllSprite dragon_NORTH_STEP_LEFT = new AllSprite(16, 522, 233);
    public static AllSprite dragon_NORTH_STEP_RIGHT = new AllSprite(16, 538, 233);
    public static AllSprite dragon_NORTH_STOP = new AllSprite(16, 554, 233);

    public static AllSprite dragon_EAST_STEP_LEFT = new AllSprite(16, 571, 233);
    public static AllSprite dragon_EAST_STEP_RIGHT = new AllSprite(16, 586, 233);
    public static AllSprite dragon_EAST_STOP = new AllSprite(16, 603, 233);

    public static AllSprite dragon_SOUTH_STEP_LEFT = new AllSprite(16, 618, 233);
    public static AllSprite dragon_SOUTH_STEP_RIGHT = new AllSprite(16, 314, 251);
    public static AllSprite dragon_SOUTH_STOP = new AllSprite(16, 330, 251);

    public static AllSprite dragon_VANISHING_1 = new AllSprite(16, 346, 251);
    public static AllSprite dragon_VANISHING_2 = new AllSprite(16, 362, 251);
    public static AllSprite dragon_VANISHING_3 = new AllSprite(16, 395, 251);


    //level2
    public static AllSprite grass__lv2 = new AllSprite(16,367,176);
    public static AllSprite brick__lv2 = new AllSprite(16,399,176);
    public static AllSprite wall__lv2 = new AllSprite(16,415,176);
    public static AllSprite brick_exploded0__lv2 = new AllSprite(16,495,192);
    public static AllSprite brick_exploded1__lv2 = new AllSprite(16,543,192);
    public static AllSprite brick_exploded2__lv2 = new AllSprite(16,576,192);

    //level3
    public static AllSprite grass__lv3 = new AllSprite(16,689,77);
    public static AllSprite brick__lv3 = new AllSprite(16,721,77);
    public static AllSprite wall__lv3 = new AllSprite(16,737,77);
    public static AllSprite brick_exploded0__lv3 = new AllSprite(16,817,93);
    public static AllSprite brick_exploded1__lv3 = new AllSprite(16,849,93);
    public static AllSprite brick_exploded2__lv3 = new AllSprite(16,896,93);

    public Image getCurrentTexture() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                // xu li diem anh
                if ( pixels[x + y * SIZE] == 0xffffffff) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        Image output = resample(input, Board.SCALED_SIZE / Board.DEFAULT_SIZE);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(output);
        Image base = iv.snapshot(params, null);

        return base;
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }
        return output;
    }
}
