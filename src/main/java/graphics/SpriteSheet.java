package main.java.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String filePath;
    public final int SIZE;
    public int[] pixels;
    public BufferedImage image;

    public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);

    public SpriteSheet(String filePath, int size) {
        this.filePath = filePath;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];

        // loadImage
        try {
            URL a = getClass().getResource(filePath);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            System.out.println("loi");
            System.exit(0);
        }
    }
}
