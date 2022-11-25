package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * This class helps manipulating images and saving them to a list of different versions of that
 * image.
 */
public class ImageModelImpl implements IModel {
  //  private final String name;
  private final Map<String, Image> image;

  /**
   * Creates a list of different versions of the given image.
   *
   * @param image different versions of the image.
   * @throws IllegalArgumentException if any of the versions is null.
   */
  public ImageModelImpl(Map<String, Image> image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image files can't be null");
    }

    this.image = new HashMap<>(image);


  }

  /**
   * This represents an empty constructor for use in the controller.
   */
  public ImageModelImpl() {
    this.image = new HashMap<>();
  }

  @Override
  public Image flip(String direction, String name, String newName) throws IllegalArgumentException {
    Image copiedImage1 = this.imageCopy(name, newName);
    Image copiedImage2 = this.imageCopy(name, newName);
    int width = copiedImage1.getWidth();
    int height = copiedImage1.getHeight();
    if (Objects.equals(direction, "flip_vertical")) {
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          copiedImage1.getPixel(x, y)
                  .setColor(copiedImage2.getPixel(copiedImage1.getWidth() - x - 1, y).getColor());
        }
      }
      image.put(newName, copiedImage1);
    } else if (Objects.equals(direction, "flip_horizontal")) {
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          copiedImage1.getPixel(x, y)
                  .setColor(copiedImage2.getPixel(x, copiedImage1.getHeight() - y - 1).getColor());
        }
      }
      image.put(newName, copiedImage1);
    } else {
      throw new IllegalArgumentException("please type in either " +
              "'flip_vertical' or 'flip_horizontal'");
    }
    return copiedImage1;
  }

  /**
   * Greyscales the image based on the different components.
   *
   * @param component the different components from which to represent the greyscale
   * @param name      the name of the image
   * @param newName   the new name of the image
   * @throws IllegalArgumentException when the component invalid
   */
  @Override
  public Image greyScale(String component, String name, String newName)
          throws IllegalArgumentException {
    Image copiedImage = this.imageCopy(name, newName);
    for (int x = 0; x < copiedImage.getWidth(); x++) {
      for (int y = 0; y < copiedImage.getHeight(); y++) {
        IPixel currentPixel = copiedImage.getPixel(x, y);
        Color currentColor = currentPixel.getColor();
        switch (component) {
          case "red":
            currentColor =
                    new Color(currentColor.getRed(), currentColor.getRed(), currentColor.getRed());
            break;
          case "green":
            currentColor = new Color(currentColor.getGreen(), currentColor.getGreen(),
                    currentColor.getGreen());
            break;
          case "blue":
            currentColor = new Color(currentColor.getBlue(), currentColor.getBlue(),
                    currentColor.getBlue());
            break;
          case "value":
            currentColor = new Color(currentPixel.maxColorValue(), currentPixel.maxColorValue(),
                    currentPixel.maxColorValue());
            break;
          case "intensity":
            currentColor = new Color(currentPixel.intensity(), currentPixel.intensity(),
                    currentPixel.intensity());
            break;
          case "luma":
            currentColor = new Color(currentPixel.luma(), currentPixel.luma(), currentPixel.luma());
            break;
          default:
            throw new IllegalArgumentException("Invalid component");
        }
        copiedImage.getPixel(x, y).setColor(currentColor);
      }
    }
    image.put(newName, copiedImage);
    return copiedImage;
  }


  /**
   * This method brightens or darkens the image based on the constant.
   *
   * @param constant the integer by which to brighten or darken the image
   * @param name     the name of the image
   * @param newName  the new name of the image
   */
  @Override
  public Image brighteningOrDarkening(int constant, String name, String newName) {
    Image copiedImage = this.imageCopy(name, newName);
    for (int x = 0; x < copiedImage.getWidth(); x++) {
      for (int y = 0; y < copiedImage.getHeight(); y++) {
        Color copiedColor = copiedImage.getPixel(x, y).getColor();
        copiedColor = new Color(clamp(copiedColor.getRed() + constant),
                clamp(copiedColor.getGreen() + constant), clamp(copiedColor.getBlue() + constant));
        copiedImage.getPixel(x, y).setColor(copiedColor);
      }
    }
    image.put(newName, copiedImage);
    return copiedImage;
  }

  /**
   * Makes sure the RGB values are within the range of 0 and 255.
   *
   * @param value value of the individual RGB value that we are inputting
   * @return one of 0, @param value, 255
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    } else {
      return Math.min(value, 255);
    }
  }

  /**
   * This method blurs and sharpens the image based on the user input.
   *
   * @param name         the name of the file
   * @param newName      the new name of the file
   * @param filterMethod specifies whether the image will be blurred or sharpened
   * @return an image
   * @throws IllegalArgumentException when the user does not specify whether to blur or sharpen
   */
  @Override
  public Image filter(String filterMethod, String name, String newName)
          throws IllegalArgumentException {
    Image copiedImage = this.imageCopy(name, newName);
    IPixel[][] pixels = copiedImage.copyPixels();
    IPixel[][] newPixels = new Pixel[pixels.length][pixels[0].length];
    double[][] kernel;
    switch (filterMethod) {
      case "blur":
        kernel = new double[][]{
                { 0.0625, 0.125, 0.0625 },
                { 0.125, 0.25, 0.125 },
                { 0.0625, 0.125, 0.0625 } };
        break;
      case "sharpen":
        kernel = new double[][]{
                { -0.125, -0.125, -0.125, -0.125, -0.125 },
                { -0.125, 0.25, 0.25, 0.25, -0.125 },
                { -0.125, 0.25, 1, 0.25, -0.125 },
                { -0.125, 0.25, 0.25, 0.25, -0.125 },
                { -0.125, -0.125, -0.125, -0.125, -0.125 } };
        break;
      default:
        throw new IllegalArgumentException("Only blue and sharpen methods are available");
    }
    IPixel center = null;
    for (int x = 0; x < pixels[0].length; x++) {
      for (int y = 0; y < pixels.length; y++) {
        double red = 0;
        double green = 0;
        double blue = 0;
        for (int row = 0; row < kernel.length; row++) {
          for (int col = 0; col < kernel[0].length; col++) {
            int relX = x - kernel[0].length / 2 + col;
            int relY = y - kernel.length / 2 + row;
            if (relX >= pixels[0].length || relX < 0 || relY >= pixels.length || relY < 0) {
              continue;
            }
            red += kernel[row][col] * pixels[relY][relX].getColor().getRed();
            green += kernel[row][col] * pixels[relY][relX].getColor().getGreen();
            blue += kernel[row][col] * pixels[relY][relX].getColor().getBlue();
          }
        }
        center = new Pixel(new Color(clamp((int) red), clamp((int) green), clamp((int) blue)));
        newPixels[y][x] = center;
      }
    }
    image.put(newName, new Image(newPixels, newName));
    return image.get(newName);
  }


  @Override
  public Image colorTransformation(String transformationMethod, String name, String newName)
          throws IllegalArgumentException {
    Image copiedImage = this.imageCopy(name, newName);
    double[][] matrix;
    switch (transformationMethod) {
      case "greyscale":
        matrix = new double[][]{
                { 0.2126, 0.7152, 0.0722 },
                { 0.2126, 0.7152, 0.0722 },
                { 0.2126, 0.7152, 0.0722 } };
        break;
      case "sepia":
        matrix = new double[][]{
                { 0.393, 0.769, 0.189 },
                { 0.349, 0.686, 0.168 },
                { 0.272, 0.534, 0.131 } };
        break;
      default:
        throw new IllegalArgumentException("Only greyscale and sepia methods are available");
    }
    for (int x = 0; x < this.image.get(name).getWidth(); x++) {
      for (int y = 0; y < this.image.get(name).getHeight(); y++) {
        double[] afterTransformation = copiedImage.getPixel(x, y).pixelColorTransformation(matrix);
        Color afterTransformationColors = new Color(clamp((int) afterTransformation[0]),
                clamp((int) afterTransformation[1]), clamp((int) afterTransformation[2]));
        copiedImage.getPixel(x, y).setColor(afterTransformationColors);
      }
    }
    image.put(newName, copiedImage);
    return copiedImage;
  }


  /**
   * Creates a copy of the image.
   *
   * @param version the version of the image to create a copy of.
   * @param newName new file name of the deep copy.
   * @return a new image copy
   * @throws IllegalArgumentException when the image is invalid
   */
  @Override
  public Image imageCopy(String version, String newName) throws IllegalArgumentException {
    if (image.containsKey(version)) {
      return new Image(image.get(version).copyPixels(), newName);
    }
    throw new IllegalArgumentException("The image is invalid");
  }


  @Override
  public void load(Image image, String name) {
    this.image.put(name, image);
  }

  @Override
  public void save(String name) {
    this.image.get(name);
  }

}


