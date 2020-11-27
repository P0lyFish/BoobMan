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
        agent_sprites.put("bomberman1_EAST_STEP_LEFT", Sprite2.player_right_2.getCurrentTexture());
        agent_sprites.put("bomberman1_EAST_STEP_RIGHT", Sprite2.player_right_1.getCurrentTexture());
        agent_sprites.put("bomberman1_EAST_STOP", Sprite2.player_right.getCurrentTexture());

        agent_sprites.put("bomberman1_NORTH_STEP_LEFT", Sprite2.player_up_2.getCurrentTexture());
        agent_sprites.put("bomberman1_NORTH_STEP_RIGHT", Sprite2.player_up_1.getCurrentTexture());
        agent_sprites.put("bomberman1_NORTH_STOP", Sprite2.player_up.getCurrentTexture());

        agent_sprites.put("bomberman1_WEST_STEP_LEFT", Sprite2.player_left_2.getCurrentTexture());
        agent_sprites.put("bomberman1_WEST_STEP_RIGHT", Sprite2.player_left_1.getCurrentTexture());
        agent_sprites.put("bomberman1_WEST_STOP", Sprite2.player_left.getCurrentTexture());

        agent_sprites.put("bomberman1_SOUTH_STEP_LEFT", Sprite2.player_down_2.getCurrentTexture());
        agent_sprites.put("bomberman1_SOUTH_STEP_RIGHT", Sprite2.player_down_1.getCurrentTexture());
        agent_sprites.put("bomberman1_SOUTH_STOP", Sprite2.player_down.getCurrentTexture());

        agent_sprites.put("bomberman1_VANISHING_1", Sprite2.player_dead4.getCurrentTexture());
        agent_sprites.put("bomberman1_VANISHING_2", Sprite2.player_dead2.getCurrentTexture());
        agent_sprites.put("bomberman1_VANISHING_3", Sprite2.player_dead.getCurrentTexture());
    }

    static {
        agent_sprites.put("bomberman2_EAST_STEP_LEFT", Sprite2.player_right_2__2.getCurrentTexture());
        agent_sprites.put("bomberman2_EAST_STEP_RIGHT", Sprite2.player_right_1__2.getCurrentTexture());
        agent_sprites.put("bomberman2_EAST_STOP", Sprite2.player_right__2.getCurrentTexture());

        agent_sprites.put("bomberman2_NORTH_STEP_LEFT", Sprite2.player_up_2__2.getCurrentTexture());
        agent_sprites.put("bomberman2_NORTH_STEP_RIGHT", Sprite2.player_up_1__2.getCurrentTexture());
        agent_sprites.put("bomberman2_NORTH_STOP", Sprite2.player_up__2.getCurrentTexture());

        agent_sprites.put("bomberman2_WEST_STEP_LEFT", Sprite2.player_left_2__2.getCurrentTexture());
        agent_sprites.put("bomberman2_WEST_STEP_RIGHT", Sprite2.player_left_1__2.getCurrentTexture());
        agent_sprites.put("bomberman2_WEST_STOP", Sprite2.player_left__2.getCurrentTexture());

        agent_sprites.put("bomberman2_SOUTH_STEP_LEFT", Sprite2.player_down_2__2.getCurrentTexture());
        agent_sprites.put("bomberman2_SOUTH_STEP_RIGHT", Sprite2.player_down_1__2.getCurrentTexture());
        agent_sprites.put("bomberman2_SOUTH_STOP", Sprite2.player_down__2.getCurrentTexture());

        agent_sprites.put("bomberman2_VANISHING_1", Sprite2.player_dead__2.getCurrentTexture());
        agent_sprites.put("bomberman2_VANISHING_2", Sprite2.player_dead1__2.getCurrentTexture());
        agent_sprites.put("bomberman2_VANISHING_3", Sprite2.player_dead2__2.getCurrentTexture());
    }



    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOON
    static {
        agent_sprites.put("balloon_EAST_STEP_LEFT", Sprite2.enemy1_2.getCurrentTexture());
        agent_sprites.put("balloon_EAST_STEP_RIGHT", Sprite2.enemy1_1.getCurrentTexture());
        agent_sprites.put("balloon_EAST_STOP", Sprite2.enemy1_3.getCurrentTexture());

        agent_sprites.put("balloon_NORTH_STEP_LEFT", Sprite2.enemy1_2.getCurrentTexture());
        agent_sprites.put("balloon_NORTH_STEP_RIGHT", Sprite2.enemy1_1.getCurrentTexture());
        agent_sprites.put("balloon_NORTH_STOP", Sprite2.enemy1_3.getCurrentTexture());

        agent_sprites.put("balloon_WEST_STEP_LEFT", Sprite2.enemy1_2.getCurrentTexture());
        agent_sprites.put("balloon_WEST_STEP_RIGHT", Sprite2.enemy1_1.getCurrentTexture());
        agent_sprites.put("balloon_WEST_STOP", Sprite2.enemy1_3.getCurrentTexture());

        agent_sprites.put("balloon_SOUTH_STEP_LEFT", Sprite2.enemy1_2.getCurrentTexture());
        agent_sprites.put("balloon_SOUTH_STEP_RIGHT", Sprite2.enemy1_1.getCurrentTexture());
        agent_sprites.put("balloon_SOUTH_STOP", Sprite2.enemy1_3.getCurrentTexture());

        agent_sprites.put("balloon_VANISHING_1", Sprite2.enemy1_dead1.getCurrentTexture());
        agent_sprites.put("balloon_VANISHING_2", Sprite2.enemy1_dead2.getCurrentTexture());
        agent_sprites.put("balloon_VANISHING_3", Sprite2.enemy1_dead2.getCurrentTexture());
    }

    //ONEAL
    static {
        agent_sprites.put("oneal_EAST_STEP_LEFT", Sprite2.oneal_EAST_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("oneal_EAST_STEP_RIGHT", Sprite2.oneal_EAST_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("oneal_EAST_STOP", Sprite2.oneal_EAST_STOP.getCurrentTexture());

        agent_sprites.put("oneal_NORTH_STEP_LEFT", Sprite2.oneal_NORTH_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("oneal_NORTH_STEP_RIGHT", Sprite2.oneal_NORTH_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("oneal_NORTH_STOP", Sprite2.oneal_NORTH_STOP.getCurrentTexture());

        agent_sprites.put("oneal_WEST_STEP_LEFT", Sprite2.oneal_WEST_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("oneal_WEST_STEP_RIGHT", Sprite2.oneal_WEST_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("oneal_WEST_STOP", Sprite2.oneal_WEST_STOP.getCurrentTexture());

        agent_sprites.put("oneal_SOUTH_STEP_LEFT", Sprite2.oneal_SOUTH_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("oneal_SOUTH_STEP_RIGHT", Sprite2.oneal_SOUTH_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("oneal_SOUTH_STOP", Sprite2.oneal_SOUTH_STOP.getCurrentTexture());

        agent_sprites.put("oneal_VANISHING_1", Sprite2.oneal_VANISHING_1.getCurrentTexture());
        agent_sprites.put("oneal_VANISHING_2", Sprite2.oneal_VANISHING_2.getCurrentTexture());
        agent_sprites.put("oneal_VANISHING_3", Sprite2.oneal_VANISHING_3.getCurrentTexture());
    }


    //dragon
    static {
        agent_sprites.put("dragon_WEST_STEP_LEFT", Sprite2.dragon_WEST_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("dragon_WEST_STEP_RIGHT", Sprite2.dragon_WEST_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("dragon_WEST_STOP", Sprite2.dragon_WEST_STOP.getCurrentTexture());

        agent_sprites.put("dragon_NORTH_STEP_LEFT", Sprite2.dragon_NORTH_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("dragon_NORTH_STEP_RIGHT", Sprite2.dragon_NORTH_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("dragon_NORTH_STOP", Sprite2.dragon_NORTH_STOP.getCurrentTexture());

        agent_sprites.put("dragon_EAST_STEP_LEFT", Sprite2.dragon_EAST_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("dragon_EAST_STEP_RIGHT", Sprite2.dragon_EAST_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("dragon_EAST_STOP", Sprite2.dragon_EAST_STOP.getCurrentTexture());

        agent_sprites.put("dragon_SOUTH_STEP_LEFT", Sprite2.dragon_SOUTH_STEP_LEFT.getCurrentTexture());
        agent_sprites.put("dragon_SOUTH_STEP_RIGHT", Sprite2.dragon_SOUTH_STEP_RIGHT.getCurrentTexture());
        agent_sprites.put("dragon_SOUTH_STOP", Sprite2.dragon_SOUTH_STOP.getCurrentTexture());

        agent_sprites.put("dragon_VANISHING_1", Sprite2.dragon_VANISHING_1.getCurrentTexture());
        agent_sprites.put("dragon_VANISHING_2", Sprite2.dragon_VANISHING_2.getCurrentTexture());
        agent_sprites.put("dragon_VANISHING_3", Sprite2.dragon_VANISHING_3.getCurrentTexture());

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
    public static HashMap<String, Image> static_sprites = new HashMap<>();

    /*
    |--------------------------------------------------------------------------
    | Board sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("grass", Sprite2.grass.getCurrentTexture());
        static_sprites.put("brick", Sprite2.brick.getCurrentTexture());
        static_sprites.put("wall", Sprite2.wall.getCurrentTexture());
        static_sprites.put("portal1", Sprite2.portal.getCurrentTexture());
        static_sprites.put("portal2", Sprite2.portal2.getCurrentTexture());
    }

    /*
    |--------------------------------------------------------------------------
    | Bomb sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_0",Sprite2.bomb_0.getCurrentTexture());
        static_sprites.put("bomb_1", Sprite2.bomb_1.getCurrentTexture());
        static_sprites.put("bomb_2", Sprite2.bomb_2.getCurrentTexture());
    }
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_exploded0", Sprite2.bomb_exploded0.getCurrentTexture());
        static_sprites.put("bomb_exploded1", Sprite2.bomb_exploded1.getCurrentTexture());
        static_sprites.put("bomb_exploded2", Sprite2.bomb_exploded2.getCurrentTexture());
        static_sprites.put("explosion_vertical0", Sprite2.explosion_vertical0.getCurrentTexture());
        static_sprites.put("explosion_vertical1", Sprite2.explosion_vertical1.getCurrentTexture());
        static_sprites.put("explosion_vertical2", Sprite2.explosion_vertical2.getCurrentTexture());
        static_sprites.put("explosion_horizontal0", Sprite2.explosion_horizontal0.getCurrentTexture());
        static_sprites.put("explosion_horizontal1", Sprite2.explosion_horizontal1.getCurrentTexture());
        static_sprites.put("explosion_horizontal2", Sprite2.explosion_horizontal2.getCurrentTexture());
        static_sprites.put("explosion_horizontal_left_last0", Sprite2.explosion_horizontal_left_last0.getCurrentTexture());
        static_sprites.put("explosion_horizontal_left_last1", Sprite2.explosion_horizontal_left_last1.getCurrentTexture());
        static_sprites.put("explosion_horizontal_left_last2", Sprite2.explosion_horizontal_left_last2.getCurrentTexture());
        static_sprites.put("explosion_horizontal_right_last0", Sprite2.explosion_horizontal_right_last0.getCurrentTexture());
        static_sprites.put("explosion_horizontal_right_last1", Sprite2.explosion_horizontal_right_last1.getCurrentTexture());
        static_sprites.put("explosion_horizontal_right_last2", Sprite2.explosion_horizontal_right_last2.getCurrentTexture());
        static_sprites.put("explosion_vertical_top_last0", Sprite2.explosion_vertical_top_last0.getCurrentTexture());
        static_sprites.put("explosion_vertical_top_last1", Sprite2.explosion_vertical_top_last1.getCurrentTexture());
        static_sprites.put("explosion_vertical_top_last2", Sprite2.explosion_vertical_top_last2.getCurrentTexture());
        static_sprites.put("explosion_vertical_down_last0", Sprite2.explosion_vertical_down_last0.getCurrentTexture());
        static_sprites.put("explosion_vertical_down_last1", Sprite2.explosion_vertical_down_last1.getCurrentTexture());
        static_sprites.put("explosion_vertical_down_last2", Sprite2.explosion_vertical_down_last2.getCurrentTexture());

    }

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment put to hashmap
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("brick_exploded0",Sprite2.brick_exploded0.getCurrentTexture());
        static_sprites.put("brick_exploded1",Sprite2.brick_exploded1.getCurrentTexture());
        static_sprites.put("brick_exploded2",Sprite2.brick_exploded2.getCurrentTexture());
    }

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    static {
        static_sprites.put("bomb_item", Sprite2.bomb_item.getCurrentTexture());
        static_sprites.put("flame_item", Sprite2.flame_item.getCurrentTexture());
        static_sprites.put("speed_item", Sprite2.speed_item.getCurrentTexture());
//        static_sprites.put("pass_wall_item", pass_wall_item.getCurrentTexture());
//        static_sprites.put("detonator_item", detonator_item.getCurrentTexture());
//        static_sprites.put("powerup_bombpass", powerup_bombpass.getCurrentTexture());
//        static_sprites.put("pass_flame_item", pass_flame_item.getCurrentTexture());
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
