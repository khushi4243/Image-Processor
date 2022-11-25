package controller;

import org.junit.Test;

import java.io.IOException;

import model.IModel;

import static org.junit.Assert.assertEquals;

/**
 * Test for brightening or darkening.
 */
public class BrighteningOrDarkeningTest {

  @Test
  public void commandOperation() throws IOException {
    StringBuilder modelLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    ICommand brightenordarken = new BrighteningOrDarkening(modelMock, 30, "name", "newName");
    brightenordarken.commandOperation();
    assertEquals("The image name was brightened or darkened with constant 30 as newName",
            modelLog.toString());
  }
}