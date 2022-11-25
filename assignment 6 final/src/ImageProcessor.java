import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControllerImpl;
import controller.FeaturesImpl;
import controller.IController;
import controller.IFeatures;
import model.IModel;
import model.ImageModelImpl;
import view.IView;
import view.ViewImpl;

/**
 * This class is the class for the main method.
 */
public class ImageProcessor {

  /**
   * This is the main method.
   * @param args the arguments
   * @throws IOException when it throws the exception
   */
  public static void main(String[] args) throws IOException {
    Readable input = null;
    List<String> argList = new ArrayList<>(Arrays.asList(args));

    if (argList.indexOf("-command") != -1) {
      input = new FileReader(argList.get(argList.indexOf("-command") + 1));
    }
    if (input == null) {
      input = new InputStreamReader(System.in);
    }

    IModel model = new ImageModelImpl();
    ViewImpl view = new ViewImpl();

    IFeatures controller = new FeaturesImpl(view, model);
    controller.start2();
  }
}
