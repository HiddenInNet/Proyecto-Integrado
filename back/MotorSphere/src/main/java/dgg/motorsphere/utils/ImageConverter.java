package dgg.motorsphere.utils;

import java.io.*;
import java.nio.file.*;

public class ImageConverter {

    // Convierte una imagen en un arreglo de bytes
    public static byte[] imageToBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

    // Convierte un arreglo de bytes en una imagen y la guarda en el directorio especificado
    public static void bytesToImage(byte[] bytes, String outputImagePath) throws IOException {
        Path path = Paths.get(outputImagePath);
        Files.write(path, bytes);
    }
}

