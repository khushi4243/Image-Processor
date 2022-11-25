package controller;

import org.junit.Test;

import java.io.IOException;

import model.IModel;

import static org.junit.Assert.assertEquals;

/**
 * Test for the Flip class.
 */
public class FlipTest {
  @Test
  public void commandOperation1() throws IOException {
    StringBuilder modelLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    ICommand horizontalflip = new Flip(modelMock, "horizontal", "name",
            "newName");
    horizontalflip.commandOperation();
    //controller.start();
    assertEquals("The image name was flipped in horizontal direction as newName",
            modelLog.toString());
  }

  @Test
  public void commandOperation2() throws IOException {
    StringBuilder modelLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    ICommand verticalflip = new Flip(modelMock, "vertical", "name",
            "newName");
    verticalflip.commandOperation();
    //controller.start();
    assertEquals("The image name was flipped in vertical direction as newName",
            modelLog.toString());
  }
}
