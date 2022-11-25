package model;

import org.junit.Test;

import java.awt.Color;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This represents the pixel class.
 */
public class PixelTest {

  @Test
  public void getColor() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    IPixel pixel2 = new Pixel(new Color(95, 255, 65));
    IPixel pixel3 = new Pixel(new Color(11, 43, 255));
    assertEquals(pixel1.getColor(), new Color(255, 76, 73));
    assertEquals(pixel2.getColor(), new Color(95, 255, 65));
    assertEquals(pixel3.getColor(), new Color(11, 43, 255));
  }

  @Test
  public void maxColorValue() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    IPixel pixel2 = new Pixel(new Color(95, 255, 65));
    IPixel pixel3 = new Pixel(new Color(11, 43, 255));
    assertEquals(pixel1.maxColorValue(), 255);
    assertEquals(pixel2.maxColorValue(), 255);
    assertEquals(pixel3.maxColorValue(), 255);
  }


  @Test
  public void setColor() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    pixel1.setColor(Color.orange);
    assertEquals(pixel1.getColor(), Color.orange);

    IPixel pixel2 = new Pixel(new Color(10, 76, 73));
    pixel2.setColor(Color.blue);
    assertEquals(pixel2.getColor(), Color.blue);

  }

  @Test
  public void intensity() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    IPixel pixel2 = new Pixel(new Color(95, 255, 65));
    IPixel pixel3 = new Pixel(new Color(11, 43, 255));
    assertEquals(pixel1.intensity(), 134);
    assertEquals(pixel2.intensity(), 138);
    assertEquals(pixel3.intensity(), 103);

  }

  @Test
  public void luma() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    IPixel pixel2 = new Pixel(new Color(95, 255, 65));
    IPixel pixel3 = new Pixel(new Color(11, 43, 255));
    assertEquals(pixel1.luma(), 116);
    assertEquals(pixel2.luma(), 216);
    assertEquals(pixel3.luma(), 53);
  }

  @Test
  public void pixelColorTransformationTest() {
    IPixel pixel1 = new Pixel(new Color(255, 76, 73));
    double[][] matrix =  {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    System.out.println(Arrays.toString(pixel1.pixelColorTransformation(matrix)));
    double[] transformation = pixel1.pixelColorTransformation(matrix);
    assertEquals(Math.round(transformation[0]), Math.round(transformation[1]),
            Math.round(transformation[2]));
  }
}