package controller;

import java.io.IOException;

import model.IModel;
import model.Image;
import view.ImageUtil;

/**
 * This represents the commands class for the save method which saves the file.
 */
public class Save implements ICommand {
  private IModel model;
  private String filePath;
  private String name;
  private String newName;

  /**
   * This is the constructor for the save class.
   *
   * @param model    represents the model object
   * @param filePath represents the path of the file
   * @param name     the name of the image
   */
  public Save(IModel model, String filePath, String name, String newName) {
    if (model == null || filePath == null || name == null) {
      throw new IllegalArgumentException("Arguments can't be null lol.");
    }

    this.model = model;
    this.filePath = filePath;
    this.name = name;
    this.newName = newName;
  }

  /**
   * This saves the image by creating a new copy of the image on your computer.
   *
   * @param filePath the file path on which to save
   * @param name     the name of the image
   */
  private void saveHelper(String filePath, String name, String newName) throws IOException {
    Image copiedImage = model.imageCopy(name, newName);
    new ImageUtil().savePPM(filePath, copiedImage);
  }

  private void saveOther(String filePath, String name, String newName) {
    Image oldImg = model.imageCopy(name, newName);
    new ImageUtil().saveOtherTypes(filePath, oldImg);
  }

  /**
   * The commands operation for this method. This calls the method from the model.
   */
  @Override
  public void commandOperation() throws IOException {
    if (filePath.endsWith("ppm")) {
      saveHelper(filePath, name, newName);
    } else {
      saveOther(filePath, name, newName);
    }
  }
}
