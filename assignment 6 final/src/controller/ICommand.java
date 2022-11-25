package controller;

import java.io.IOException;

/**
 * This represents the command interface for the different function objects. It allows for the
 * function objects to be called in the controller.
 */
public interface ICommand {
  /**
   * This calls the different methods from the model in order for the controller to use.
   */
  void commandOperation() throws IOException;
}
