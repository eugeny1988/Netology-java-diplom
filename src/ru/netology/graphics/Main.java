package ru.netology.graphics;

import ru.netology.graphics.image.ImageConverter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {

        TextGraphicsConverter converter = new ImageConverter();
        converter.setMaxRatio(4);
        GServer server = new GServer(converter);
        server.start();

    }
}
