package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Tests for imagemodelimp.
 */
public class ImageModelImplTest {
  private ImageModelImpl imageModel1;

  private Image image1;

  private Pixel[][] image1Pixels;

  @Before
  public void initialization() {
    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(200, 30, 40)),
      new Pixel(new Color(30, 200, 40)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(200, 30, 40)),
      new Pixel(new Color(30, 40, 200)) };
    image1Pixels = new Pixel[][]{ pixels1, pixels2 };
    image1 = new Image(image1Pixels, "image1");

    Pixel[] pixels3 = new Pixel[]{ new Pixel(new Color(100, 100, 100)),
      new Pixel(new Color(50, 50, 50)) };
    Pixel[] pixels4 = new Pixel[]{ new Pixel(new Color(75, 75, 75)),
      new Pixel(new Color(150, 150, 150)) };
    Pixel[][] image2Pixels = new Pixel[][]{ pixels3, pixels4 };
    Image image2 = new Image(image2Pixels, "image2");

    Pixel[] pixels5 = new Pixel[]{ new Pixel(new Color(35, 45, 55)),
      new Pixel(new Color(65, 75, 85)) };
    Pixel[] pixels6 = new Pixel[]{ new Pixel(new Color(95, 105, 115)),
      new Pixel(new Color(125, 135, 145)) };
    Pixel[][] image3Pixels = new Pixel[][]{ pixels5, pixels6 };
    Image image3 = new Image(image3Pixels, "image3");

    HashMap<String, Image> imageMap1 = new HashMap<>();
    imageMap1.put("image1", image1);
    imageModel1 = new ImageModelImpl(imageMap1);

    HashMap<String, Image> imageMap2 = new HashMap<>();
    imageMap2.put("image1", image1);
    imageMap2.put("image2", image2);
    ImageModelImpl imageModel2 = new ImageModelImpl(imageMap2);

    HashMap<String, Image> imageMap3 = new HashMap<>();
    imageMap3.put("Koala", image1);
    ImageModelImpl imageModel3 = new ImageModelImpl(imageMap3);


  }

  @Test
  public void verticalFlip() {
    Image image2 = imageModel1.flip("flip_vertical", "image1", "imageNew1");
    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(200, 30, 40)),
      new Pixel(new Color(30, 40, 200))};
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(200, 30, 40)),
      new Pixel(new Color(30, 200, 40))};

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");
    for (int x = 0; x < image1Pixels[0].length; x++) {
      for (int y = 0; y < image1Pixels.length; y++) {
        IPixel newImage1 = newImage.getPixel(x, y);
        IPixel flippedImage = image2.getPixel(x, y);
        assertEquals(flippedImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(flippedImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(flippedImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }


  @Test
  public void horizontalFlip() {
    Image image2 = imageModel1.flip("flip_horizontal", "image1", "imageNew1");
    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(30, 200, 40)),
      new Pixel(new Color(200, 30, 40)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(30, 40, 200)),
      new Pixel(new Color(200, 30, 40)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");
    for (int x = 0; x < image1Pixels[0].length; x++) {
      for (int y = 0; y < image1Pixels.length; y++) {
        IPixel newImage1 = newImage.getPixel(x, y);
        IPixel flippedImage = image2.getPixel(x, y);
        assertEquals(flippedImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(flippedImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(flippedImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }

  @Test
  public void greyScaleRed() {
    Image red = imageModel1.greyScale("red", "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(200, 200, 200)),
      new Pixel(new Color(30, 30, 30)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(200, 200, 200)),
      new Pixel(new Color(30, 30, 30)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage1 = newImage.getPixel(i, j);
        IPixel redImage = red.getPixel(i, j);
        assertEquals(redImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(redImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(redImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }

  @Test
  public void greyScaleGreen() {
    Image green = imageModel1.greyScale("green", "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(30, 30, 30)),
      new Pixel(new Color(200, 200, 200)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(30, 30, 30)),
      new Pixel(new Color(40, 40, 40)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage1 = newImage.getPixel(i, j);
        IPixel greenImage = green.getPixel(i, j);
        assertEquals(greenImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(greenImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(greenImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }

  @Test
  public void greyScaleBlue() {
    Image grey = imageModel1.greyScale("blue", "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(40, 40, 40)),
      new Pixel(new Color(40, 40, 40)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(40, 40, 40)),
      new Pixel(new Color(200, 200, 200)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage1 = newImage.getPixel(i, j);
        IPixel greyImage = grey.getPixel(i, j);
        assertEquals(greyImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(greyImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(greyImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }

  @Test
  public void greyScaleLuma() {
    Image grey = imageModel1.greyScale("blue", "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(40, 40, 40)),
      new Pixel(new Color(40, 40, 40)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(40, 40, 40)),
      new Pixel(new Color(200, 200, 200)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage1 = newImage.getPixel(i, j);
        IPixel greyImage = grey.getPixel(i, j);
        assertEquals(greyImage.luma(), newImage1.luma());
        assertEquals(greyImage.luma(), newImage1.luma());
        assertEquals(greyImage.luma(), newImage1.luma());
      }
    }
  }

  @Test
  public void greyScaleIntensity() {
    Image red = imageModel1.greyScale("intensity", "image1", "imageNew1");
    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel redImage = red.getPixel(i, j);
        assertEquals(redImage.getColor().getRed(), redImage.intensity());
        assertEquals(redImage.getColor().getBlue(), redImage.intensity());
        assertEquals(redImage.getColor().getGreen(), redImage.intensity());
      }
    }
  }

  @Test
  public void greyScaleValue() {
    Image red = imageModel1.greyScale("value", "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(200, 200, 200)),
      new Pixel(new Color(200, 200, 200)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(200, 200, 200)),
      new Pixel(new Color(200, 200, 200)) };

    Pixel[][] pixelArray = new Pixel[][]{pixels1, pixels2};

    Image newImage = new Image(pixelArray, "newImage");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage1 = newImage.getPixel(i, j);
        IPixel redImage = red.getPixel(i, j);
        assertEquals(redImage.getColor().getRed(), newImage1.getColor().getRed());
        assertEquals(redImage.getColor().getBlue(), newImage1.getColor().getBlue());
        assertEquals(redImage.getColor().getGreen(), newImage1.getColor().getGreen());
      }
    }
  }

  @Test
  public void Brightening() {
    Image bright = imageModel1.brighteningOrDarkening(30, "image1", "imageNew1");
    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(230, 60, 70)),
      new Pixel(new Color(60, 230, 70)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(230, 60, 70)),
      new Pixel(new Color(60, 70, 230)) };
    Pixel[][] pixelArray = new Pixel[][]{ pixels1, pixels2 };
    Image constructedImage = new Image(pixelArray, "image1");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage = constructedImage.getPixel(i, j);
        IPixel brightImage = bright.getPixel(i, j);

        assertEquals(brightImage.getColor().getRed(), newImage.getColor().getRed());
        assertEquals(brightImage.getColor().getGreen(), newImage.getColor().getGreen());
        assertEquals(brightImage.getColor().getBlue(), newImage.getColor().getBlue());

      }
    }
  }

  @Test
  public void Darkening() {
    Image bright = imageModel1.brighteningOrDarkening(-30, "image1", "imageNew1");

    Pixel[] pixels1 = new Pixel[]{ new Pixel(new Color(170, 0, 10)),
      new Pixel(new Color(0, 170, 10)) };
    Pixel[] pixels2 = new Pixel[]{ new Pixel(new Color(170, 0, 10)),
      new Pixel(new Color(0, 10, 170)) };

    Pixel[][] pixelArray = new Pixel[][]{ pixels1, pixels2 };
    Image constructedImage = new Image(pixelArray, "image1");

    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel newImage = constructedImage.getPixel(i, j);
        IPixel brightImage = bright.getPixel(i, j);

        assertEquals(brightImage.getColor().getRed(), newImage.getColor().getRed());
        assertEquals(brightImage.getColor().getGreen(), newImage.getColor().getGreen());
        assertEquals(brightImage.getColor().getBlue(), newImage.getColor().getBlue());

      }
    }
  }

  @Test
  public void imageCopy() {
    Image original = image1;
    Image newImage1 = imageModel1.imageCopy("image1", "imageNew1");
    for (int i = 0; i < image1Pixels.length; i++) {
      for (int j = 0; j < image1Pixels[0].length; j++) {
        IPixel oldImage = original.getPixel(i, j);
        IPixel newImage = newImage1.getPixel(i, j);
        assertEquals(oldImage.getColor().getRed(), newImage.getColor().getRed());
        assertEquals(oldImage.getColor().getBlue(), newImage.getColor().getBlue());
        assertEquals(oldImage.getColor().getGreen(), newImage.getColor().getGreen());
      }
    }
  }

  @Test
  public void filterTest1() {
    Image image2 = imageModel1.filter("blur",
            "image1", "imageNew1");
    IPixel blurredImage = image2.getPixel(0,0);
    assertEquals(Math.round(blurredImage.getColor().getRed()), 80);
    assertEquals(Math.round(blurredImage.getColor().getGreen()), 38);
    assertEquals(Math.round(blurredImage.getColor().getBlue()), 32);
  }

  @Test
  public void filterTest2() {
    Image image2 = imageModel1.filter("sharpen",
            "image1", "imageNew1");
    IPixel blurredImage = image2.getPixel(0,0);
    assertEquals(Math.round(blurredImage.getColor().getRed()), 255);
    assertEquals(Math.round(blurredImage.getColor().getGreen()), 97);
    assertEquals(Math.round(blurredImage.getColor().getBlue()), 110);
  }

  @Test
  public void colorTransformationTest1() {
    Image image2 = imageModel1.colorTransformation("greyscale",
            "image1", "imageNew1");

    IPixel greyscaledImage = image2.getPixel(0,0);
    assertEquals(greyscaledImage.getColor().getRed(), greyscaledImage.getColor().getGreen(),
            greyscaledImage.getColor().getBlue());

    IPixel greyscaledImage2 = image2.getPixel(0,1);
    assertEquals(greyscaledImage2.getColor().getRed(), greyscaledImage2.getColor().getGreen(),
            greyscaledImage2.getColor().getBlue());

    IPixel greyscaledImage3 = image2.getPixel(1,0);
    assertEquals(greyscaledImage3.getColor().getRed(), greyscaledImage3.getColor().getGreen(),
            greyscaledImage3.getColor().getBlue());

    IPixel greyscaledImage4 = image2.getPixel(1,1);
    assertEquals(greyscaledImage4.getColor().getRed(), greyscaledImage4.getColor().getGreen(),
            greyscaledImage4.getColor().getBlue());
  }

  @Test
  public void colorTransformationTest2() {
    Image image2 = imageModel1.colorTransformation("sepia", "image1", "imageNew1");

    IPixel sepiaImage = image2.getPixel(0,0);
    assertEquals(sepiaImage.getColor().getRed(), 109);
    assertEquals(sepiaImage.getColor().getGreen(), 97);
    assertEquals(sepiaImage.getColor().getBlue(), 75);

    IPixel sepiaImage2 = image2.getPixel(0,1);
    assertEquals(sepiaImage2.getColor().getRed(), 109);
    assertEquals(sepiaImage2.getColor().getGreen(), 97);
    assertEquals(sepiaImage2.getColor().getBlue(), 75);

    IPixel sepiaImage3 = image2.getPixel(1,0);
    assertEquals(sepiaImage3.getColor().getRed(), 173);
    assertEquals(sepiaImage3.getColor().getGreen(), 154);
    assertEquals(sepiaImage3.getColor().getBlue(), 120);

    IPixel sepiaImage4 = image2.getPixel(1,1);
    assertEquals(sepiaImage4.getColor().getRed(), 80);
    assertEquals(sepiaImage4.getColor().getGreen(), 71);
    assertEquals(sepiaImage4.getColor().getBlue(), 55);
  }
}
