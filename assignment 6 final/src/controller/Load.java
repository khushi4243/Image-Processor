package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IModel;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * This represents the command class for load which is able to load the different
 * types of files.
 */
public class Load implements ICommand {
  private String filePath;
  private IModel model;
  private String imageName;
  private IPixel[][] imagePixels;
  private Image image;

  /**
   * This represents the constructor which takes in the different fields necessary for this class.
   *
   * @param model    this represents the model object
   * @param filePath the nameof the file
   */
  public Load(IModel model, String filePath, String imageName) {
    this.filePath = filePath;
    this.model = model;
    this.imageName = imageName;
  }


  private void loadPPM(String filePath, String imageName) {
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
    this.model.load(new Image(imagePixels, imageName), imageName);
  }

  private void loadOther(String filePath, String imageName) throws FileNotFoundException {
    BufferedImage readable;
    try {
      readable = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    IPixel[][] imagePixels = new Pixel[readable.getHeight()][readable.getWidth()];
    for (int i = 0; i < readable.getHeight(); i++) {
      for (int j = 0; j < readable.getWidth(); j++) {
        int rgb = readable.getRGB(j, i);
        imagePixels[i][j] = new Pixel(new Color(rgb));
      }
    }
    this.model.load(new Image(imagePixels, imageName), imageName);
  }

  /**
   * The commands operation for this method. This calls the method from the model.
   */
  @Override
  public void commandOperation() throws FileNotFoundException {
    if (filePath.endsWith("ppm")) {
      loadPPM(filePath, imageName);
    }
    else {
      loadOther(filePath, imageName);
    }
  }
}
