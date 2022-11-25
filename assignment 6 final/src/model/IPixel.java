package model;

import java.awt.Color;

/**
 * This represents the Pixel interface which contains all of the methods
 * relating to the Pixel object.
 */
public interface IPixel {
  /**
   * Gets the color of a pixel.
   *
   * @return RGB value of the pixel.
   */
  Color getColor();

  /**
   * Gets the maximum RGB value of a pixel.
   *
   * @return int value of the max RGB values of the pixel.
   */
  int maxColorValue();

  /**
   * Sets the pixel's color to the given RGB values.
   *
   * @param colors the RGB values of the given color.
   * @throws IllegalArgumentException if the RGB values are invalid.
   */

  void setColor(Color colors) throws IllegalArgumentException;

  /**
   * Average of the RGB values of the color of a pixel.
   *
   * @return int value of the average of the RGB values.
   */
  int intensity();

  /**
   * This represents the luma value of the pixel.
   *
   * @return the luma value
   */
  int luma();

  /**
   * Does the matrix multiplication of the given matrix and pixel used in
   * {@code colorTransformation}.
   * @param matrix the given matrix to be used (either greyscale or sepia)
   * @return new RGB values after the color transformation has been implemented
   */
  double[] pixelColorTransformation(double[][] matrix);

}
