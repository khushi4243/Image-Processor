package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import model.Image;
import model.ImageModelImpl;
import view.IView;
import view.ViewImpl;

public class FeaturesImpl implements IFeatures, ActionListener {
  private IModel model;
  private IView view;
  private Image currImage;

  /**
   * This takes in the different components necessary in a controller in order for it to perform its
   * job.
   *
   * @param view  takes in the view
   * @param model takes in the model
   * @throws IllegalArgumentException when the input, view, or model are invalid
   */
  public FeaturesImpl(IView view, IModel model)
          throws IllegalArgumentException {
    if (view == null || model == null) {
      throw new IllegalArgumentException("The inputs can't be null");
    }
    this.view = view;
    this.model = model;
    this.currImage = null;
    this.view.setListener(this);
  }

  public void start2() {
    this.view.display();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load": {
        this.view.showFileChooser();
        break;
      }
      case "opened-image": {
        Image image = (Image) e.getSource();
        model.load(image, "");
        this.view.displayImage(image);
        break;
      }
      case "save": {
        this.view.saveFile();
        break;
      }

      case "greyscale":
      case "sepia": {
        Image newImage = model.colorTransformation(e.getActionCommand(), "", "");
        model.load(newImage, "");
        this.view.displayImage(newImage);
        break;
      }
      case "blur":
      case "sharpen": {
        Image newImage = model.filter(e.getActionCommand(), "", "");
        model.load(newImage, "");
        this.view.displayImage(newImage);
        break;
      }
      case "flip_horizontal":
      case "flip_vertical": {
        Image newImage = model.flip(e.getActionCommand(), "", "");
        model.load(newImage, "");
        this.view.displayImage(newImage);
        break;
      }
      case "brighten-or-darken": {
        Image newImage = model.brighteningOrDarkening(Integer.parseInt(view.getInput()), "", "");
        model.load(newImage, "");
        this.view.displayImage(newImage);
        break;
      }

    }
  }
}
