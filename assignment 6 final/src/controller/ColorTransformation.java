package controller;

import java.io.IOException;

import model.IModel;
import model.ImageModelImpl;

/**
 * This represents the command class for the colortransformation method.
 */
public class ColorTransformation implements ICommand {
  String name;
  String newName;
  String transformationMethod;
  IModel model;

  /**
   * This represents the constructor which takes in the different fields necessary for this class.
   *
   * @param model                the model object
   * @param transformationMethod which colortransformation to run (greyscale or sepia)
   * @param name                 name of the image
   * @param newName              the new name of the image
   */
  public ColorTransformation(IModel model, String transformationMethod,
                             String name, String newName) {
    this.model = model;
    this.transformationMethod = transformationMethod;
    this.name = name;
    this.newName = newName;
  }

  /**
   * Second constructor which doesn't require the model as one of the parameters.
   *
   * @param transformationMethod which colortransformation to run (greyscale or sepia)
   * @param name                 name of the image
   * @param newName              the new name of the image
   */
  public ColorTransformation(String transformationMethod, String name, String newName) {
    this.model = new ImageModelImpl();
    this.transformationMethod = transformationMethod;
    this.name = name;
    this.newName = newName;
  }

  @Override
  public void commandOperation() throws IOException {
    model.colorTransformation(transformationMethod, name, newName);
  }
}
