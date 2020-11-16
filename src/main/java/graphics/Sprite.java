package main.java.graphics;

//import com.sun.deploy.cache.CacheEntry;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import main.java.Board;

import java.util.HashMap;

public class Sprite {

    private static final int TRANSPARENT_COLOR = 0xffff00ff; // xu li diem anh (xoa background)
    public final int SIZE;
    private int x, y;
    public int[] pixels; // diem anh
    private SpriteSheet sheet = SpriteSheet.tiles;

    // LAY ANH CON TU 1 ANH LON



    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static HashMap<String, Image> agent_sprites = new HashMap<>();

    static {
        agent_sprites.put("bomberman_EAST_STEP_LEFT", new Sprite(1, 2).getCurrentTexture());
        agent_sprites.put("bomberman_EAST_STEP_RIGHT", new Sprite(1, 1).getCurrentTexture());
        agent_sprites.put("bomberman_EAST_STOP", new Sprite(1, 0).getCurrentTexture());

        agent_sprites.put("bomberman_NORTH_STEP_LEFT", new Sprite(0, 2).getCurrentTexture());
        agent_sprites.put("bomberman_NORTH_STEP_RIGHT", new Sprite(0, 1).getCurrentTexture());
        agent_sprites.put("bomberman_NORTH_STOP", new Sprite(0, 0).getCurrentTexture());

        agent_sprites.put("bomberman_WEST_STEP_LEFT", new Sprite(3, 2).getCurrentTexture());
        agent_sprites.put("bomberman_WEST_STEP_RIGHT", new Sprite(3, 1).getCurrentTexture());
        agent_sprites.put("bomberman_WEST_STOP", new Sprite(3, 0).getCurrentTexture());

        agent_sprites.put("bomberman_SOUTH_STEP_LEFT", new Sprite(2, 2).getCurrentTexture());
        agent_sprites.put("bomberman_SOUTH_STEP_RIGHT", new Sprite(2, 1).getCurrentTexture());
        agent_sprites.put("bomberman_SOUTH_STOP", new Sprite(2, 0).getCurrentTexture());

        agent_sprites.put("bomberman_VANISHING_1", new Sprite(4, 2).getCurrentTexture());
        agent_sprites.put("bomberman_VANISHING_2", new Sprite(5, 2).getCurrentTexture());
        agent_sprites.put("bomberman_VANISHING_3", new Sprite(6, 2).getCurrentTexture());
    }

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOON
    static {
        agent_sprites.put("balloon_EAST_STEP_LEFT", new Sprite(10, 2).getCurrentTexture());
        agent_sprites.put("balloon_EAST_STEP_RIGHT", new Sprite(10, 1).getCurrentTexture());
        agent_sprites.put("balloon_EAST_STOP", new Sprite(10, 0).getCurrentTexture());

        agent_sprites.put("balloon_NORTH_STEP_LEFT", new Sprite(10, 2).getCurrentTexture());
        agent_sprites.put("balloon_NORTH_STEP_RIGHT", new Sprite(10, 1).getCurrentTexture());
        agent_sprites.put("balloon_NORTH_STOP", new Sprite(10, 0).getCurrentTexture());

        agent_sprites.put("balloon_WEST_STEP_LEFT", new Sprite(9, 2).getCurrentTexture());
        agent_sprites.put("balloon_WEST_STEP_RIGHT", new Sprite(9, 1).getCurrentTexture());
        agent_sprites.put("balloon_WEST_STOP", new Sprite(9, 0).getCurrentTexture());

        agent_sprites.put("balloon_SOUTH_STEP_LEFT", new Sprite(9, 2).getCurrentTexture());
        agent_sprites.put("balloon_SOUTH_STEP_RIGHT", new Sprite(9, 1).getCurrentTexture());
        agent_sprites.put("balloon_SOUTH_STOP", new Sprite(9, 0).getCurrentTexture());

        agent_sprites.put("balloon_VANISHING_1", new Sprite(9, 3).getCurrentTexture());
        agent_sprites.put("balloon_VANISHING_2", new Sprite(9, 3).getCurrentTexture());
        agent_sprites.put("balloon_VANISHING_3", new Sprite(9, 3).getCurrentTexture());
    }

    //ONEAL
    static {
        agent_sprites.put("oneal_EAST_STEP_LEFT", new Sprite(12, 2).getCurrentTexture());
        agent_sprites.put("oneal_EAST_STEP_RIGHT", new Sprite(12, 1).getCurrentTexture());
        agent_sprites.put("oneal_EAST_STOP", new Sprite(12, 0).getCurrentTexture());

        agent_sprites.put("oneal_NORTH_STEP_LEFT", new Sprite(12, 2).getCurrentTexture());
        agent_sprites.put("oneal_NORTH_STEP_RIGHT", new Sprite(12, 1).getCurrentTexture());
        agent_sprites.put("oneal_NORTH_STOP", new Sprite(12, 0).getCurrentTexture());

        agent_sprites.put("oneal_WEST_STEP_LEFT", new Sprite(11, 2).getCurrentTexture());
        agent_sprites.put("oneal_WEST_STEP_RIGHT", new Sprite(11, 1).getCurrentTexture());
        agent_sprites.put("oneal_WEST_STOP", new Sprite(11, 0).getCurrentTexture());

        agent_sprites.put("oneal_SOUTH_STEP_LEFT", new Sprite(11, 2).getCurrentTexture());
        agent_sprites.put("oneal_SOUTH_STEP_RIGHT", new Sprite(11, 1).getCurrentTexture());
        agent_sprites.put("oneal_SOUTH_STOP", new Sprite(11, 0).getCurrentTexture());

        agent_sprites.put("oneal_VANISHING_1", new Sprite(11, 3).getCurrentTexture());
        agent_sprites.put("oneal_VANISHING_2", new Sprite(11, 3).getCurrentTexture());
        agent_sprites.put("oneal_VANISHING_3", new Sprite(11, 3).getCurrentTexture());
    }

    //Doll
    public static Sprite doll_left1 = new Sprite(13, 0);
    public static Sprite doll_left2 = new Sprite(13, 1);
    public static Sprite doll_left3 = new Sprite(13, 2);

    public static Sprite doll_right1 = new Sprite(14, 0);
    public static Sprite doll_right2 = new Sprite(14, 1);
    public static Sprite doll_right3 = new Sprite(14, 2);

    public static Sprite doll_dead = new Sprite(13, 3);

    //Minvo
    public static Sprite minvo_left1 = new Sprite(8, 5);
    public static Sprite minvo_left2 = new Sprite(8, 6);
    public static Sprite minvo_left3 = new Sprite(8, 7);

    public static Sprite minvo_right1 = new Sprite(9, 5);
    public static Sprite minvo_right2 = new Sprite(9, 6);
    public static Sprite minvo_right3 = new Sprite(9, 7);

    public static Sprite minvo_dead = new Sprite(8, 8);

    //Kondoria
    public static Sprite kondoria_left1 = new Sprite(10, 5);
    public static Sprite kondoria_left2 = new Sprite(10, 6);
    public static Sprite kondoria_left3 = new Sprite(10, 7);

    public static Sprite kondoria_right1 = new Sprite(11, 5);
    public static Sprite kondoria_right2 = new Sprite(11, 6);
    public static Sprite kondoria_right3 = new Sprite(11, 7);

    public static Sprite kondoria_dead = new Sprite(10, 8);

    //ALL
    public static Sprite mob_dead1 = new Sprite(15, 0);
    public static Sprite mob_dead2 = new Sprite(15, 1);
    public static Sprite mob_dead3 = new Sprite(15, 2);




    //hash map chứa static entities
    public static HashMap<String, Sprite> static_sprites = new HashMap<>();
    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */


    public static Sprite grass = new Sprite(6, 0);
    public static Sprite brick = new Sprite(7, 0);
    public static Sprite wall = new Sprite(5, 0);
    public static Sprite portal = new Sprite(4, 0);


    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_0 = new Sprite(0, 3);
    public static Sprite bomb_1 = new Sprite(1, 3);
    public static Sprite bomb_2 = new Sprite(2, 3);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded0 = new Sprite(0, 4);
    public static Sprite bomb_exploded1 = new Sprite(0, 5);
    public static Sprite bomb_exploded2 = new Sprite(0, 6);

    public static Sprite explosion_vertical0 = new Sprite(1, 5);
    public static Sprite explosion_vertical1 = new Sprite(2, 5);
    public static Sprite explosion_vertical2 = new Sprite(3, 5);

    public static Sprite explosion_horizontal0 = new Sprite(1, 7);
    public static Sprite explosion_horizontal1 = new Sprite(1, 8);
    public static Sprite explosion_horizontal2 = new Sprite(1, 9);

    public static Sprite explosion_horizontal_left_last0 = new Sprite(0, 7);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(0, 8);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(0, 9);

    public static Sprite explosion_horizontal_right_last0 = new Sprite(2, 7);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(2, 8);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(2, 9);

    public static Sprite explosion_vertical_top_last0 = new Sprite(1, 4);
    public static Sprite explosion_vertical_top_last1 = new Sprite(2, 4);
    public static Sprite explosion_vertical_top_last2 = new Sprite(3, 4);

    public static Sprite explosion_vertical_down_last0 = new Sprite(1, 6);
    public static Sprite explosion_vertical_down_last1 = new Sprite(2, 6);
    public static Sprite explosion_vertical_down_last2 = new Sprite(3, 6);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded0 = new Sprite(7, 1);
    public static Sprite brick_exploded1 = new Sprite(7, 2);
    public static Sprite brick_exploded2 = new Sprite(7, 3);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_item = new Sprite(0, 10);
    public static Sprite flame_item = new Sprite(1, 10);
    public static Sprite speed_item = new Sprite(2, 10);
    public static Sprite pass_wall_item = new Sprite(3, 10);
    public static Sprite detonator_item = new Sprite(4, 10);
    public static Sprite powerup_bombpass = new Sprite(5, 10);
    public static Sprite pass_flame_item = new Sprite(6, 10);

    /*
    |--------------------------------------------------------------------------
    | Board sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("grass", grass);
        static_sprites.put("brick", brick);
        static_sprites.put("wall", wall);
        static_sprites.put("portal", portal);
    }

    /*
    |--------------------------------------------------------------------------
    | Bomb sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_0", bomb_0);
        static_sprites.put("bomb_1", bomb_1);
        static_sprites.put("bomb_2", bomb_2);
    }
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_exploded0", bomb_exploded0);
        static_sprites.put("bomb_exploded1", bomb_exploded1);
        static_sprites.put("bomb_exploded2", bomb_exploded2);
        static_sprites.put("explosion_vertical0", explosion_vertical0);
        static_sprites.put("explosion_vertical1", explosion_vertical1);
        static_sprites.put("explosion_vertical2", explosion_vertical2);
        static_sprites.put("explosion_horizontal0", explosion_horizontal0);
        static_sprites.put("explosion_horizontal1", explosion_horizontal1);
        static_sprites.put("explosion_horizontal2", explosion_horizontal2);
        static_sprites.put("explosion_horizontal_left_last0", explosion_horizontal_left_last0);
        static_sprites.put("explosion_horizontal_left_last1", explosion_horizontal_left_last1);
        static_sprites.put("explosion_horizontal_left_last2", explosion_horizontal_left_last2);
        static_sprites.put("explosion_horizontal_right_last0", explosion_horizontal_right_last0);
        static_sprites.put("explosion_horizontal_right_last1", explosion_horizontal_right_last1);
        static_sprites.put("explosion_horizontal_right_last2", explosion_horizontal_right_last2);
        static_sprites.put("explosion_vertical_top_last0", explosion_vertical_top_last0);
        static_sprites.put("explosion_vertical_top_last1", explosion_vertical_top_last1);
        static_sprites.put("explosion_vertical_top_last2", explosion_vertical_top_last2);
        static_sprites.put("explosion_vertical_down_last0", explosion_vertical_down_last0);
        static_sprites.put("explosion_vertical_down_last1", explosion_vertical_down_last1);
        static_sprites.put("explosion_vertical_down_last2", explosion_vertical_down_last2);

    }

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("brick_exploded0",brick_exploded0);
        static_sprites.put("brick_exploded1",brick_exploded1);
        static_sprites.put("brick_exploded2",brick_exploded2);
    }

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_item", bomb_item);
        static_sprites.put("flame_item", flame_item);
        static_sprites.put("speed_item", speed_item);
        static_sprites.put("pass_wall_item", pass_wall_item);
        static_sprites.put("detonator_item", detonator_item);
        static_sprites.put("powerup_bombpass", powerup_bombpass);
        static_sprites.put("pass_flame_item", pass_flame_item);
    }
    public Sprite(int xx, int yy) {
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
