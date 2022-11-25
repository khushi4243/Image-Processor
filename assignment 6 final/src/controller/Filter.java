package controller;

import model.IModel;

/**
 * This represents the command class for filter.
 */
public class Filter implements ICommand {
  private String name;
  private String newName;
  private String filterMethod;
  private IModel model;

  /**
   * This represents the second constructor for the filter methods which doesn't require IModel as
   * one of the parameters.
   *
   * @param model        represents the IModel
   * @param filterMethod the type of filter that is being applied
   * @param name         the name of the file
   * @param newName      the new name of the file
   */
  Filter(IModel model, String filterMethod, String name, String newName) {
    this.name = name;
    this.newName = newName;
    this.filterMethod = filterMethod;
    this.model = model;
  }

  /**
   * This calls the filter method from the model.
   */
  @Override
  public void commandOperation() {
    model.filter(filterMethod, name, newName);
  }
}
