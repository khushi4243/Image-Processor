package controller;

import java.io.IOException;

import view.IViewText;

public class ViewMock2 implements IViewText {
  private final StringBuilder log;

  public ViewMock2(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(String.format("This calls renderMessage %s", message));
  }
}
