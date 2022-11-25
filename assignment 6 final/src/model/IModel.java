package model;

/**
 * This represents the model interface which provides the functionality for the program.
 */
public interface IModel {

  /**
   * Loads the image using the name of the file.
   * @param image the image that we are loading
   * @param name the name of the file
   */
  void load(Image image, String name);

  /**
   * Creates a deep copy of the given image.
   *
   * @param version the version of the image to create a copy of.
   * @param newName new file name of the deep copy.
   * @return deep copy of the given image.
   */
  Image imageCopy(String version, String newName);

  /**
   * This flips the image either horizontally or vertically.
   * @param direction the direction of the image flip
   * @param name      the name of the image
   * @param newName   the new name of the image
   * @return the flipped Image
   * @throws IllegalArgumentException if the direction is neither flip_vertical or flip_horizontal
   */
  Image flip(String direction, String name, String newName) throws IllegalArgumentException;

  /**
   * Greyscales the image based on the different components.
   *
   * @param component the different components from which to represent the greyscale
   * @param name      the name of the image
   * @param newName   the new name of the image
   * @throws IllegalArgumentException when the component invalid
   */
  Image greyScale(String component, String name, String newName) throws IllegalArgumentException;

  /**
   * This method brightens or darkens the image based on the constant.
   *
   * @param constant the integer by which to brighten or darken the image
   * @param name     the name of the image
   * @param newName  the new name of the image
   */
  Image brighteningOrDarkening(int constant, String name, String newName)
          throws IllegalArgumentException;

  /**
   * This method blurs and sharpens the image based on the user input.
   * @param name the name of the file
   * @param newName the new name of the file
   * @param filterMethod specifies whether the image will be blurred or sharpened
   * @return an image that is blurred or sharpened
   * @throws IllegalArgumentException when the user does not specify whether to blur or sharpen
   */
  Image filter(String filterMethod, String name, String newName) throws IllegalArgumentException;

  /**
   * This method greyscaled or sepia-s the image based on the user's input.
   * @param transformationMethod specifies whether the image will be grescaled or sepia-ed
   * @param name the name of the file
   * @param newName the new name of the file
   * @return an image that is blurred or sepia-ed
   */
  Image colorTransformation(String transformationMethod, String name, String newName);

  /**
   * Saves the image.
   * @param name name of the image
   */
  void save(String name);
}
