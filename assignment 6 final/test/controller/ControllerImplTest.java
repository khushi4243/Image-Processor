package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.IModel;
import model.ImageModelImpl;
import view.IViewText;
import view.ViewTextImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test for controllerimpl.
 */
public class ControllerImplTest {

  @Test
  public void start() throws IOException {
    Readable read = new StringReader("load res/Koala.ppm Koala");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IViewText viewMock = new ViewMock2(viewLog);
    IController controller = new ControllerImpl(read, viewMock, modelMock);
    controller.start();
    assertEquals("The image was loaded as Koala", modelLog.toString());
  }

  @Test
  public void start2() throws IOException {
    Readable read = new StringReader("load res/dog.png Dog");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IViewText viewMock = new ViewMock2(viewLog);
    IController controller = new ControllerImpl(read, viewMock, modelMock);
    controller.start();
    assertEquals("The image was loaded as Dog", modelLog.toString());
  }

  @Test
  public void start3() throws IOException {
    Readable read = new StringReader("load res/cat.jpeg Cat");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IViewText viewMock = new ViewMock2(viewLog);
    IController controller = new ControllerImpl(read, viewMock, modelMock);
    controller.start();
    assertEquals("The image was loaded as Cat", modelLog.toString());
  }

  @Test(expected = IOException.class)
  public void testBadAppendable() throws IOException {
    StringReader read = new StringReader("load res/Koala.ppm Koala");
    IModel model = new ImageModelImpl();
    IViewText viewMock = new ViewTextImpl(model, new BadAppendable());
    IController cont = new ControllerImpl(read, viewMock, model);
    cont.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() throws IOException {
    StringReader read = new StringReader("load res/Koala.ppm Koala");
    StringBuilder viewLog = new StringBuilder();
    IModel model = null;
    IViewText viewMock = new ViewMock2(viewLog);
    IController cont = new ControllerImpl(read, viewMock, model);
    cont.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() throws IOException {
    StringReader read = new StringReader("load res/Koala.ppm Koala");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IViewText viewMock = null;
    IController cont = new ControllerImpl(read, viewMock, modelMock);
    cont.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRead() throws IOException {
    StringReader read = null;
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IViewText viewMock = new ViewMock2(viewLog);
    IController cont = new ControllerImpl(read, viewMock, modelMock);
    cont.start();
  }
}