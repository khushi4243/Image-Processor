package controller;

import model.IModel;
import model.Image;

class ModelMock implements IModel {
  private final StringBuilder log;

  public ModelMock(StringBuilder log) {
    this.log = log;
  }

  /**
   * Loads the image.
   *
   * @param image the image we are tying to load
   * @param name the name of the file
   * @return the image
   */
  @Override
  public void load(Image image, String name) {
    log.append(String.format("The image was loaded as %s", name));
  }

  /**
   * Creates a deep copy of the given image.
   *
   * @param version the version of the image to create a copy of.
   * @param newName new file name of the deep copy.
   * @return deep copy of the given image.
   */
  public Image imageCopy(String version, String newName) {
    log.append(String.format("The image %s was copied as %s", version, newName));
    return null;
  }

  @Override
  public Image flip(String direction, String name, String newName)
          throws IllegalArgumentException {
    log.append(String.format("The image %s was flipped in %s direction as %s",
            name, direction, newName));
    return null;
  }

  /**
   * Greyscales the image based on the different components.
   *
   * @param component the different components from which to represent the greyscale
   * @param name      the name of the image
   * @param newName   the new name of the image
   * @throws IllegalArgumentException when the component invalid
   */
  public Image greyScale(String component, String name, String newName)
          throws IllegalArgumentException {
    log.append(String.format("The image %s was greyscaled with component %s as %s", name, component,
            newName));
    return null;
  }

  /**
   * This method brightens or darkens the image based on the constant.
   *
   * @param constant the integer by which to brighten or darken the image
   * @param name     the name of the image
   * @param newName  the new name of the image
   */
  public Image brighteningOrDarkening(int constant, String name, String newName)
          throws IllegalArgumentException {
    log.append(String.format("The image %s was brightened or darkened with constant %s as %s", name,
            constant, newName));
    return null;
  }

  @Override
  public Image filter(String name, String newName, String filterMethod)
          throws IllegalArgumentException {
    return null;
  }

  @Override
  public Image colorTransformation(String transformationMethod, String name, String newName) {
    return null;
  }


  @Override
  public void save(String name) {
    log.append(String.format("The image %s was saved", name));
  }

}
