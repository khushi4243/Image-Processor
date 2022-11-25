package controller;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import model.IModel;
import model.Image;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Test for load.
 */
public class LoadTest {

  @Test
  public void commandOperation() throws IOException {
    StringBuilder modelLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    ICommand load = new Load(modelMock, "res/map.bmp", "map");
    load.commandOperation();
    assertEquals("The image was loaded as map", modelLog.toString());
  }

  @Test
  public void testLoadJPG1() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/cat.jpeg", "cat");

    assertFalse(imageMap.containsKey("cat"));
    load.commandOperation();
    assertTrue(model.imageCopy("cat", "NewCat") != null);
  }

  @Test
  public void testLoadJPG2() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/catBlur.jpeg", "cat");

    try {
      model.imageCopy("cat", "NewCat");
      fail("Should throw error.");
    }
    catch (IllegalArgumentException e) {
      load.commandOperation();
      assertTrue(model.imageCopy("cat", "NewCat") != null);
    }

  }

  @Test
  public void testLoadPNG() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/dog.png", "dog");

    assertFalse(imageMap.containsKey("dog"));
    load.commandOperation();
    assertTrue(model.imageCopy("dog", "NewDog") != null);
  }

  @Test
  public void testLoadPPM() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/Koala.ppm", "Koala");

    assertFalse(imageMap.containsKey("Koala"));
    load.commandOperation();
    assertTrue(model.imageCopy("Koala", "NewKoala") != null);
  }

}
