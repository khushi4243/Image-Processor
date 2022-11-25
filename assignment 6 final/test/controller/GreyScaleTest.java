package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.IModel;

import static org.junit.Assert.assertEquals;

/**
 * Test for grescale.
 */
public class GreyScaleTest {

  @Test
  public void commandOperation() throws IOException {
    Readable read = new StringReader("greyscale red name newName");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder modelLog2 = new StringBuilder();
    StringBuilder modelLog3 = new StringBuilder();
    StringBuilder modelLog4 = new StringBuilder();
    StringBuilder modelLog5 = new StringBuilder();
    StringBuilder modelLog6 = new StringBuilder();

    IModel modelMock = new ModelMock(modelLog);
    IModel modelMock2 = new ModelMock(modelLog2);
    IModel modelMock3 = new ModelMock(modelLog3);
    IModel modelMock4 = new ModelMock(modelLog4);
    IModel modelMock5 = new ModelMock(modelLog5);
    IModel modelMock6 = new ModelMock(modelLog6);

    ICommand greyscale = new GreyScale(modelMock, "red", "name", "newName");
    greyscale.commandOperation();
    assertEquals("The image name was greyscaled with component red as newName",
            modelLog.toString());

    ICommand greyscale2 = new GreyScale(modelMock2, "green", "name", "newName");
    greyscale2.commandOperation();
    assertEquals("The image name was greyscaled with component green as newName",
            modelLog2.toString());

    ICommand greyscale3 = new GreyScale(modelMock3, "blue", "name", "newName");
    greyscale3.commandOperation();
    assertEquals("The image name was greyscaled with component blue as newName",
            modelLog3.toString());

    ICommand greyscale4 = new GreyScale(modelMock4, "value", "name", "newName");
    greyscale4.commandOperation();
    assertEquals("The image name was greyscaled with component value as newName",
            modelLog4.toString());

    ICommand greyscale5 = new GreyScale(modelMock5, "intensity", "name", "newName");
    greyscale5.commandOperation();
    assertEquals("The image name was greyscaled with component intensity as newName",
            modelLog5.toString());

    ICommand greyscale6 = new GreyScale(modelMock6, "luma", "name", "newName");
    greyscale6.commandOperation();
    assertEquals("The image name was greyscaled with component luma as newName",
            modelLog6.toString());
  }
}

