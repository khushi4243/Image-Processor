package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

import model.IModel;
import model.Image;
import model.ImageModelImpl;
import view.IView;
import view.IViewText;
import view.ImageUtil;
import view.ViewImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This represents the controller class which takes information from the model and performs the
 * functionality.
 */
public class ControllerImpl implements IController {

  private IModel model;
  private IViewText view;
  private Readable input;
  private HashMap<String, Function<Scanner, ICommand>> commands;

  /**
   * This takes in the different components necessary in a controller in order for it to perform its
   * job.
   *
   * @param input the readable which reads the inputs
   * @param view  takes in the view
   * @param model takes in the model
   * @throws IllegalArgumentException when the input, view, or model are invalid
   */
  public ControllerImpl(Readable input, IViewText view, IModel model)
          throws IllegalArgumentException {
    if (input == null || view == null || model == null) {
      throw new IllegalArgumentException("The inputs can't be null");
    }
    this.input = input;
    this.view = view;
    this.model = model;
    commands();
  }

  /**
   * Starts the program and executes the command method.
   *
   * @throws IllegalStateException when the function map is null
   */
  public void start() throws IllegalStateException, IOException {
    Scanner s = new Scanner(input);
    while (s.hasNext()) {
      ICommand c;
      String in = s.next();
      Function<Scanner, ICommand> cmd =
              commands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(s);
        c.commandOperation();
      }
    }
    view.renderMessage("");
  }

  private void commands() {
    commands = new HashMap<>();
    commands.put("load", s -> new Load(this.model, s.next(), s.next()));
    commands.put("save", s -> new Save(this.model, s.next(), s.next(), s.next()));
    commands.put("brighten-or-darken", s -> new BrighteningOrDarkening(this.model, s.nextInt(),
            s.next(), s.next()));
    commands.put("greyscale", s -> new GreyScale(this.model, s.next(), s.next(), s.next()));
    commands.put("flip", s -> new Flip(this.model, s.next(), s.next(), s.next()));
    commands.put("filter", s -> new Filter(this.model, s.next(), s.next(), s.next()));
    commands.put("color-transformation", s -> new ColorTransformation(this.model, s.next(),
            s.next(), s.next()));
  }
}



