package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageConverter implements TextGraphicsConverter {
    private double maxRatio = 4;
    private final int maxValue = 100;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        TextColorSchema schema = new ColorSchema('#', '$', '@', '%', '*', '+', '-', '\'');
        BufferedImage img = ImageIO.read(new URL(url));
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        double ratio = newHeight > newWidth ? (double) (newHeight / newWidth) : (double) (newWidth / newHeight);
        this.setMaxRatio(maxRatio);
        if (ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        if (newWidth > maxValue || newHeight > maxValue) {
            if (newWidth > maxValue) {
                newHeight = newHeight / (newWidth / maxValue);
                newWidth = maxValue;
            } else if (newHeight > maxValue) {
                newWidth = newWidth / (newHeight / maxValue);
                newHeight = maxValue;
            }
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        BufferedImage bwImgGrey = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImgGrey, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {

                int color = bwRaster.getPixel(j, i, new int[3])[0];
                char c = schema.convert(color);
                stringBuilder.append(c).append(c);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    @Override
    public void setMaxWidth(int width) {

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
