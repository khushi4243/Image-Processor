package controller;

import org.junit.Test;

import java.awt.event.ActionEvent;

import model.IModel;
import view.IView;

import static org.junit.Assert.assertEquals;

public class ControllerFeaturesTest {

  @Test
  public void actionPerformedLoad() {
    ActionEvent action = new ActionEvent(new Object(), 0, "load");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls showFileChooser()", viewLog.toString());
  }

  @Test
  public void save() {
    ActionEvent action = new ActionEvent(new Object(), 0, "save");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls saveFile()", viewLog.toString());
  }

  @Test
  public void greyscale() {
    ActionEvent action = new ActionEvent(new Object(), 0, "greyscale");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void sepia() {
    ActionEvent action = new ActionEvent(new Object(), 0, "sepia");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void blur() {
    ActionEvent action = new ActionEvent(new Object(), 0, "blur");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void sharpen() {
    ActionEvent action = new ActionEvent(new Object(), 0, "sharpen");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void flipVertical() {
    ActionEvent action = new ActionEvent(new Object(), 0, "flip_vertical");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void flipHorizontal() {
    ActionEvent action = new ActionEvent(new Object(), 0, "flip_horizontal");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls displayImage(Image image)", viewLog.toString());
  }

  @Test
  public void brightenOrDarken() {
    ActionEvent action = new ActionEvent(new Object(), 0, "brighten-or-darken");
    StringBuilder modelLog = new StringBuilder();
    StringBuilder viewLog = new StringBuilder();
    IModel modelMock = new ModelMock(modelLog);
    IView viewMock = new ViewMock(viewLog);
    IFeatures controller = new FeaturesImpl(viewMock, modelMock);
    controller.actionPerformed(action);
    assertEquals("This calls setListener(ActionListener listener)This calls getInput()This calls displayImage(Image image)", viewLog.toString());
  }

}
