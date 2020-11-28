package main.java.graphics;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import main.java.Board;

import java.util.HashMap;

public class SpriteLv2 {
    private static final int TRANSPARENT_COLOR = 0xffff00ff; // xu li diem anh (xoa background)
    public final int SIZE;
    private int x, y;
    public int[] pixels; // diem anh
    private SpriteSheet sheet = SpriteSheet.tiles;

    public static HashMap<String, Image> static_sprites = new HashMap<>();

    static {
        static_sprites.put("grass", AllSprite.grass__lv2.getCurrentTexture());
        static_sprites.put("brick", AllSprite.brick__lv2.getCurrentTexture());
        static_sprites.put("wall", AllSprite.wall__lv2.getCurrentTexture());
        static_sprites.put("brick_exploded0", AllSprite.brick_exploded0__lv2.getCurrentTexture());
        static_sprites.put("brick_exploded1", AllSprite.brick_exploded1__lv2.getCurrentTexture());
        static_sprites.put("brick_exploded2", AllSprite.brick_exploded2__lv2.getCurrentTexture());

    }

    public SpriteLv2(int xx, int yy) {
        SIZE = Board.DEFAULT_SIZE;
        pixels = new int[SIZE * SIZE];
        x = xx * SIZE;
        y = yy * SIZE;
        sheet = SpriteSheet.tiles;

        // luu tru diem anh
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                pixels[i + j * SIZE] = sheet.pixels[(i + x) + (j + y) * sheet.SIZE];
            }
        }
    }

    public Image getCurrentTexture() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                // xu li diem anh
                if ( pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
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
