package main.java.graphics;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import main.java.Board;

public class Sprite2 {
    private final int SIZE;
    private int x, y;
    private int[] pixels; // diem anh
    private SpriteSheet2 sheet = SpriteSheet2.tiles;

    public Sprite2(int size, int xx, int yy) {
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

    public static Sprite2 wall = new Sprite2(16, 737, 110);
    public static Sprite2 brick = new Sprite2(16, 721,110);
    public static Sprite2 grass = new Sprite2(16, 689, 110);
    public static Sprite2 bomb_0 = new Sprite2(16, 470, 0);
    public static Sprite2 bomb_1 = new Sprite2(16, 486, 0);
    public static Sprite2 bomb_2 = new Sprite2(16, 502, 0);

    //
    public static Sprite2 bomb_exploded0 = new Sprite2(16, 454, 32);
    public static Sprite2 bomb_exploded1 = new Sprite2(16, 422, 32);
    public static Sprite2 bomb_exploded2 = new Sprite2(16, 390, 32);
    //
    public static Sprite2 explosion_vertical0 = new Sprite2(16, 630, 16);
    public static Sprite2 explosion_vertical1 = new Sprite2(16, 614, 16);
    public static Sprite2 explosion_vertical2 = new Sprite2(16, 598, 16);
    public static Sprite2 explosion_vertical3 = new Sprite2(16, 582, 16);
    //
    public static Sprite2 explosion_horizontal0 = new Sprite2(16, 374, 32);
    public static Sprite2 explosion_horizontal1 = new Sprite2(16, 358, 32);
    public static Sprite2 explosion_horizontal2 = new Sprite2(16, 342, 32);
    public static Sprite2 explosion_horizontal3 = new Sprite2(16, 326, 32);
    //
    public static Sprite2 explosion_horizontal_left_last0 = new Sprite2(16, 566, 16);
    public static Sprite2 explosion_horizontal_left_last1 = new Sprite2(16, 550, 16);
    public static Sprite2 explosion_horizontal_left_last2 = new Sprite2(16, 534, 16);
    public static Sprite2 explosion_horizontal_left_last3 = new Sprite2(16, 518, 16);
    //
    public static Sprite2 explosion_horizontal_right_last0 = new Sprite2(16, 438, 16);
    public static Sprite2 explosion_horizontal_right_last1 = new Sprite2(16, 422, 16);
    public static Sprite2 explosion_horizontal_right_last2 = new Sprite2(16, 406, 16);
    public static Sprite2 explosion_horizontal_right_last3 = new Sprite2(16, 390, 16);
    //
    public static Sprite2 explosion_vertical_top_last0 = new Sprite2(16, 374, 16);
    public static Sprite2 explosion_vertical_top_last1 = new Sprite2(16, 358, 16);
    public static Sprite2 explosion_vertical_top_last2 = new Sprite2(16, 342, 16);
    public static Sprite2 explosion_vertical_top_last3 = new Sprite2(16, 326, 16);
    //
    public static Sprite2 explosion_vertical_down_last0 = new Sprite2(16, 502, 16);
    public static Sprite2 explosion_vertical_down_last1 = new Sprite2(16, 486, 16);
    public static Sprite2 explosion_vertical_down_last2 = new Sprite2(16, 470, 16);
    public static Sprite2 explosion_vertical_down_last3 = new Sprite2(16, 454, 16);

    //item
    public static Sprite2 bomb_item = new Sprite2(16, 16, 48);
    public static Sprite2 flame_item = new Sprite2(16, 0, 48);
    public static Sprite2 speed_item = new Sprite2(16, 48, 48);

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
