package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

import model.Image;
import view.IView;

class ViewMock implements IView {
  private final StringBuilder log;

  public ViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void setListener(ActionListener listener) {
    log.append(String.format("This calls setListener(ActionListener listener)"));
  }

  @Override
  public String getInput() {
    log.append(String.format("This calls getInput()"));
    return "30";
  }

  @Override
  public void displayImage(Image image) {
    log.append(String.format("This calls displayImage(Image image)"));
  }

  @Override
  public void showFileChooser() {
    log.append(String.format("This calls showFileChooser()"));
  }

  @Override
  public void saveFile() {
    log.append(String.format("This calls saveFile()"));
  }

  @Override
  public void display() {
    log.append(String.format("This calls display()"));
  }
}
