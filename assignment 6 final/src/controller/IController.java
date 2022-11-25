package controller;

import java.io.IOException;

/**
 * This represents the controller interface which starts the program.
 * This interface is meant to pull the data from the model and provide data
 * to the user.
 */
public interface IController {
  /**
   * This method starts the program.
   */
  void start() throws IOException;

}
