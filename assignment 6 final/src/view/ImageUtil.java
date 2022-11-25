package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.IModel;
import model.IPixel;
import model.Image;
import model.ImageModelImpl;
import model.Pixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {
   public ImageUtil(){}

  public void saveOtherTypes(String filePath, Image image) {
    BufferedImage img;
    try {
      if (filePath.endsWith("png")) {
        img = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
      } else {
        img = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
      }
      for (int j = 0; j < image.getHeight(); j++) {
        for (int i = 0; i < image.getWidth(); i++) {
          Color p = image.getPixel(i, j).getColor();
          img.setRGB(i, j, p.getRGB());
        }
      }
      File output = new File(filePath);
      ImageIO.write(img, filePath.substring(filePath.lastIndexOf('.') + 1), output);
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  public void savePPM(String filePath, Image image) throws IOException{
    Writer write = new FileWriter(filePath);
    write.append("P3\n");
    write.append(image.getWidth() + " " + image.getHeight() + " 255\n");
    for (int j = 0; j < image.getHeight(); j++) {
      for (int i = 0; i < image.getWidth(); i++) {
        Color p = image.getPixel(i, j).getColor();
        write.append(p.getRed() + " " + p.getGreen() + " " + p.getBlue() + "\n");
      }
    }
    write.append("\n");
    write.close();
  }

  public Image loadPPM(String filePath, String imageName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File " + filePath + " not found!");

    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    IPixel[][] imagePixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imagePixels[i][j] = new Pixel(new Color(r, g, b));
      }
    }
    return new Image(imagePixels, imageName);
  }
}

