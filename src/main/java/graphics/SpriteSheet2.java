package main.java.graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class SpriteSheet2 {
    private String filePath;
    private BufferedImage image;

    public int SIZE;
    public int[] pixels;
    public static SpriteSheet2 tiles = new SpriteSheet2("/textures/sprites.png", 1009);

    public SpriteSheet2(String filePath, int size) {
        this.filePath = filePath;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];

        // loadImage
        try {
            URL a = SpriteSheet2.class.getResource(filePath);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
