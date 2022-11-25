package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import model.IModel;
import model.Image;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for save.
 */
public class SaveTest {

  @Test
  public void savePPM() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/Koala.ppm", "Koala");
    load.commandOperation();
    ICommand save = new Save( model, "res/newKoala.ppm", "Koala", "newKoala");
    save.commandOperation();
    assertTrue(new File("res/newKoala.ppm").exists());
  }

  @Test
  public void saveJPG() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/cat.jpeg", "Cat");
    load.commandOperation();
    ICommand save = new Save( model , "res/newCat.jpeg", "Cat", "newCat");
    save.commandOperation();
    assertTrue(new File("res/newCat.jpeg").exists());
  }

  @Test
  public void savePNG() throws IOException {
    HashMap<String, Image> imageMap = new HashMap<>();
    IModel model = new ImageModelImpl(imageMap);
    ICommand load = new Load(model, "res/dog.png", "Cat");
    load.commandOperation();
    ICommand save = new Save( model, "res/newDog.png", "Cat", "newCat");
    save.commandOperation();
    assertTrue(new File("res/newDog.png").exists());
  }
}
