package model;

import java.awt.Color;

/**
 * This class represents the pixel object which contains the color values
 * in the RGB format. This is the most base class within our program and is used
 * to build the images and the list of images.
 */
public class Pixel implements IPixel {
  private Color color;

  /**
   * This is the constructor for the pixel class.
   *
   * @param color the rgb values which represent the pixel color
   */
  public Pixel(Color color) throws IllegalArgumentException {
    this.color = color;
  }

  /**
   * Gets the color of a pixel.
   *
   * @return RGB value of the pixel.
   */

  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Gets the maximum RGB value of a pixel.
   *
   * @return int value of the max RGB values of the pixel.
   */
  public int maxColorValue() {
    int maxValue1 = Math.max(color.getRed(), color.getGreen());
    return Math.max(maxValue1, color.getBlue());
  }

  /**
   * Sets the pixel's color to the given RGB values.
   *
   * @param colors the RGB values of the given color.
   * @throws IllegalArgumentException if the RGB values are invalid.
   */
  public void setColor(Color colors) throws IllegalArgumentException {
    if (colors.getRed() >= 0 && colors.getRed() <= 255
            && colors.getGreen() >= 0 && colors.getGreen() <= 255
            && colors.getBlue() >= 0 && colors.getBlue() <= 255) {
      this.color = new Color(colors.getRed(), colors.getGreen(), colors.getBlue());
    } else {
      throw new IllegalArgumentException("Invalid color");
    }
  }

  /**
   * Average of the RGB values of the color of a pixel.
   *
   * @return int value of the average of the RGB values.
   */
  public int intensity() {
    return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
  }

  /**
   * This represents the luma value of the pixel.
   *
   * @return the luma value
   */
  public int luma() {
    return (int) ((0.2126 * color.getRed()) + (0.7512 * color.getGreen()) + (0.0722
            * color.getBlue()));
  }

  @Override
  public double[] pixelColorTransformation(double[][] matrix) {
    double[] afterTransformation = new double[3];
    double[] beforeTransformation = new double[]{this.color.getRed(),
            this.color.getGreen(), this.color.getBlue()};

    for (int i = 0; i < matrix[0].length; i++) {
      for (int r = 0; r < matrix.length; r++) {
        afterTransformation[r] += matrix[r][i] * beforeTransformation[i];
      }
    }

    return afterTransformation;
  }
}
