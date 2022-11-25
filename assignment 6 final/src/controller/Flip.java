package controller;

import model.IModel;
import model.ImageModelImpl;


/**
 * This represents the command class for vertical flip.
 */
public class Flip implements ICommand {
  private IModel model;
  private String direction;
  private String name;
  private String newName;

  /**
   * This represents the constructor which takes in the different fields necessary for this class.
   *
   * @param model     the model object
   * @param direction the direction of the flip
   * @param name      the name of the image
   * @param newName   the new name of the image
   */
  public Flip(IModel model, String direction, String name, String newName)
          throws IllegalArgumentException {
    if (model == null || direction == null || name == null || newName == null) {
      throw new IllegalArgumentException("None of the fields can be null");
    }
    this.model = model;
    this.direction = direction;
    this.name = name;
    this.newName = newName;
  }

  /**
   * This represents the constructor which takes in the different fields necessary for this class
   * without the model.
   *
   * @param direction the direction of the flip
   * @param name      the name of the image
   * @param newName   the new name of the image
   */
  public Flip(String direction, String name, String newName)
          throws IllegalArgumentException {
    if (model == null || direction == null || name == null || newName == null) {
      throw new IllegalArgumentException("None of the fields can be null");
    }
    this.model = new ImageModelImpl();
    this.direction = direction;
    this.name = name;
    this.newName = newName;
  }

  /**
   * The commands operation for this method. This calls the method from the model.
   */
  @Override
  public void commandOperation() {
    model.flip(direction, name, newName);
  }

}
