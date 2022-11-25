package controller;

import model.IModel;

/**
 * This represents the command class for brightening or darkening.
 */
public class BrighteningOrDarkening implements ICommand {
  private int constant;
  private String name;
  private String newName;
  private IModel model;

  /**
   * This represents the constructor which takes in the different fields for this class.
   *
   * @param model    this is the model object
   * @param constant the value by which the image will brighten or darken
   * @param name     the file name
   * @param newName  the new name of the file
   */
  public BrighteningOrDarkening(IModel model, int constant, String name, String newName) {
    this.constant = constant;
    this.name = name;
    this.newName = newName;
    this.model = model;
  }

  /**
   * The constructor without the model object.
   *
   * @param constant the value by which the image will brighten or darken
   * @param name     the file name
   * @param newName  the new name of the file
   */
  public BrighteningOrDarkening(int constant, String name, String newName) {
    this.constant = constant;
    this.name = name;
    this.newName = newName;
    this.model = model;
  }

  /**
   * The commands operation for this method. This calls the method from the model.
   */
  @Override
  public void commandOperation() {
    model.brighteningOrDarkening(constant, name, newName);
  }
}
