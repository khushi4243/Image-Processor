package model;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an image which is a 2D array of pixels.
 */
public class Image {
  private String name;
  private IPixel[][] pixels;


  /**
   * This represents the constructor of the image.
   *
   * @param pixels the 2D array of pixels
   * @param name   the name of the image
   * @throws IllegalArgumentException when either pixels or the name is null
   */
  public Image(IPixel[][] pixels, String name) throws IllegalArgumentException {
    if (pixels == null || name == null) {
      throw new IllegalArgumentException("Pixels or the name of the image can't be null");
    }
    this.pixels = new IPixel[pixels.length][pixels[pixels.length - 1].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        this.pixels[i][j] = pixels[i][j];
      }
    }
    this.name = name;
  }

  public Image(BufferedImage bufferedImage, String name) {
    if (bufferedImage == null) {
      throw new IllegalArgumentException("Buffered image can't be null");
    }
    // buffered image to 2d pixels
    this.pixels = new IPixel[bufferedImage.getHeight()][bufferedImage.getWidth()];
    WritableRaster raster = bufferedImage.getRaster();
    for (int i = 0; i < bufferedImage.getHeight(); i++) {
      for (int j = 0; j < bufferedImage.getWidth(); j++) {
        int clr = bufferedImage.getRGB(j, i);
        // convert clr to Color
        this.pixels[i][j] = new Pixel(new Color(clr));
      }
    }

    this.name = name;
  }

  public BufferedImage toBufferedImage() {
    BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
    WritableRaster raster = (WritableRaster) image.getData();
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        Color c = this.getPixel(col, row).getColor();
        raster.setPixel(col, row, new int[]{c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()});
      }
    }
    image.setData(raster);
    return image;
  }

  /**
   * Returns the name of the image.
   *
   * @return a string which represents the name if the image
   */
  public String getName() {
    return new String(name);
  }

  /**
   * Creates a new list of a list of pixels of the image to avoid manipulating an actual/original
   * image.
   *
   * @return a ne list of a list of pixels of the image.
   */
  public IPixel[][] copyPixels() throws IllegalArgumentException {
    IPixel[][] coppiedPixels = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        if (this.pixels[i][j] == null) {
          throw new IllegalArgumentException("Invalid pixels");
        }
        coppiedPixels[i][j] = new Pixel(new Color(this.pixels[i][j].getColor().getRed(),
                this.pixels[i][j].getColor().getGreen(), this.pixels[i][j].getColor().getBlue()));
      }
    }
    return coppiedPixels;
  }

  /**
   * The width of an image.
   *
   * @return int value of the image's width.
   */
  public int getWidth() {
    return this.pixels[0].length;
  }

  /**
   * The height of an image.
   *
   * @return int value of the image's height.
   */
  public int getHeight() {
    return this.pixels.length;
  }

  /**
   * Gets the coordinate of a specific pixel.
   *
   * @param x the x position of the pixel.
   * @param y the y position of the pixel.
   * @return the pixel at the given x,y coordinate.
   * @throws IllegalArgumentException if the coordinate is invalid.
   */
  public IPixel getPixel(int x, int y) throws IllegalArgumentException {
    if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
      return this.pixels[y][x];
    } else {
      throw new IllegalArgumentException("Invalid pixel coordinate");
    }
  }

  // gets an 3x256 int array of the image's histogram
  // where the first dimension is r,g,b
  // and the second dimension is the actual occurence of each of the 256 values
    public int[][] getHistogram() {
      int[][] histogram = new int[][]{new int[256], new int[256], new int[256]};
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
            Color c = this.getPixel(j, i).getColor();
            histogram[0][c.getRed()]++;
            histogram[1][c.getGreen()]++;
            histogram[2][c.getBlue()]++;
            }
        }

      return histogram;
    }



  /**
   * Counts the amount of pixels with each red value (from 0 to 255).
   * @return
   */
  public HashMap<Integer,Integer> countRedPixel() {
    HashMap<Integer,Integer> redPixels = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      redPixels.put(i,0);
      for (int x = 0; i < this.getWidth(); x++) {
        for (int y = 0; y < this.getHeight(); y++) {
        /*if (i == this.getPixel(x,y).getColor().getRed()) {
          redPixels2 = redPixels2 + 1;
        }*/
          redPixels.put(i,
                  this.getPixel(x,y).getColor().getRed()+1);
        }
      }
    }
    return redPixels;
  }

  /**
   *
   * @return
   */
  public HashMap<Integer, Integer> countGreenPixel() {
    HashMap<Integer,Integer> greenPixels = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      greenPixels.put(i,0);
      for (int x = 0; i < this.getWidth(); x++) {
        for (int y = 0; y < this.getHeight(); y++) {
          greenPixels.put(this.getPixel(x,y).getColor().getGreen(),
                  this.getPixel(x,y).getColor().getGreen()+1);
        }
      }
    }
    return greenPixels;
  }

  /**
   *
   * @return
   */
  public HashMap<Integer, Integer> countBluePixel() {
    HashMap<Integer,Integer> bluePixels = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      bluePixels.put(i,0);
      for (int x = 0; i < this.getWidth(); x++) {
        for (int y = 0; y < this.getHeight(); y++) {
          bluePixels.put(this.getPixel(x,y).getColor().getBlue(),
                  this.getPixel(x,y).getColor().getBlue()+1);
        }
      }
    }
    return bluePixels;
  }

}
