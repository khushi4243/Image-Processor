package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for image.
 */
public class ImageTest {

  private Image image1;
  private Image image2;
  private Image image3;
  private Pixel pixel1;
  private Image nullImage;
  private Pixel[][] image1Pixels;
  private Pixel[][] image2Pixels;
  private Pixel[][] image3Pixels;

  @Before
  public void initialization() {
    Pixel[] pixels1 = new Pixel[]{new Pixel(new Color(255, 30, 40)),
      new Pixel(new Color(30, 255, 40))};
    Pixel[] pixels2 = new Pixel[]{new Pixel(new Color(255, 30, 40)),
      new Pixel(new Color(30, 40, 255))};

    image1Pixels = new Pixel[][]{pixels1, pixels2};

    image1 = new Image(image1Pixels, "image1");

    Pixel[] pixels3 = new Pixel[]{new Pixel(new Color(100, 100, 100)),
      new Pixel(new Color(50, 50, 50))};
    Pixel[] pixels4 = new Pixel[]{new Pixel(new Color(75, 75, 75)),
      new Pixel(new Color(150, 150, 150))};
    image2Pixels = new Pixel[][]{pixels3, pixels4};
    image2 = new Image(image2Pixels, "image2");

    Pixel[] pixels5 = new Pixel[]{new Pixel(new Color(35, 45, 55)),
      new Pixel(new Color(65, 75, 85))};
    Pixel[] pixels6 = new Pixel[]{new Pixel(new Color(95, 105, 115)),
      new Pixel(new Color(125, 135, 145))};
    image3Pixels = new Pixel[][]{pixels5, pixels6};
    image3 = new Image(image3Pixels, "image3");

    nullImage = null;
  }

  @Test
  public void getName() {
    assertEquals(image1.getName(), "image1");
    assertEquals(image2.getName(), "image2");
    assertEquals(image3.getName(), "image3");
  }


  @Test
  public void copyPixels() {
    assertEquals(image2.copyPixels(), image2Pixels);
    assertEquals(image3.copyPixels(), image3Pixels);

  }


  @Test
  public void getWidth() {
    assertEquals(2, image1.getWidth());
    assertEquals(2, image2.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(image1.getHeight(), 2);
    assertEquals(image2.getHeight(), 2);
  }

  @Test
  public void getPixelInvalid() {
    try {
      image1.getPixel(15, 15);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel coordinate", e.getMessage());
    }

    try {
      image1.getPixel(-15, 15);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel coordinate", e.getMessage());
    }

    try {
      image1.getPixel(15, -15);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel coordinate", e.getMessage());
    }

    try {
      image1.getPixel(-15, -15);
      fail("No illegal argument thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel coordinate", e.getMessage());
    }
  }


  @Test
  public void getPixel() {
    assertEquals(image1.getPixel(0, 0).getColor(), new Color(255, 30, 40));
    assertEquals(image1.getPixel(0, 1).getColor(), new Color(255, 30, 40));
    assertEquals(image2.getPixel(1, 1).getColor(), new Color(150, 150, 150));
  }

  @Test
  public void testGetHistogram() {
    Pixel[] pixels1 = new Pixel[]{new Pixel(new Color(255, 0, 0)),
            new Pixel(new Color(255, 0, 0))};
    Pixel[] pixels2 = new Pixel[]{new Pixel(new Color(255, 0, 0)),
            new Pixel(new Color(255, 0, 0))};
    image1Pixels = new Pixel[][]{pixels1, pixels2};
    image1 = new Image(image1Pixels, "image1");
    // there are 4, 255 RED pixel in image1
    assertEquals(4, image1.getHistogram()[0][255]);

    Pixel[] pixels3 = new Pixel[]{new Pixel(new Color(0, 255,0)),
            new Pixel(new Color(0, 255,0))};
    Pixel[] pixels4 = new Pixel[]{new Pixel(new Color(0, 255,0)),
            new Pixel(new Color(0, 255,0))};
    image2Pixels = new Pixel[][]{pixels3, pixels4};
    image2 = new Image(image2Pixels, "image2");
    // there are 4, 255 GREEN pixel in image2
    assertEquals(4, image2.getHistogram()[1][255]);
    System.out.println(image1.getHistogram()[0][255]);
  }
}