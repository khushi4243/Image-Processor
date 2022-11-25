package controller;

import model.IModel;


/**
 * This represents the command class for greyscale.
 */
public class GreyScale implements ICommand {
  private String component;
  private String name;
  private String newName;
  private IModel model;

  /**
   * This represents the constructor which takes in the different fields necessary for this class.
   *
   * @param model     the model object
   * @param component the command with which to visualize greyscale
   * @param name      the name of the image
   * @param newName   the new name of the image
   */
  public GreyScale(IModel model, String component, String name, String newName) {
    this.component = component;
    this.name = name;
    this.newName = newName;
    this.model = model;
  }

  /**
   * The commands operation for this method. This calls the method from the model.
   */
  @Override
  public void commandOperation() {
    model.greyScale(component, name, newName);
  }

}
