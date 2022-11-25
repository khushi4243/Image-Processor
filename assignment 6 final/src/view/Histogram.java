package view;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import model.Image;
import model.Pixel;

/**
 *
 */
public class Histogram extends JPanel {
  private static final int BAR  = 5;
  private Image image;

  /**
   */
  public Histogram() {
    super();
    this.setPreferredSize(new Dimension(266,200));
  }

  public void setImage(Image image) {
    this.image = image;
    this.repaint();
  }

  /**
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph = (Graphics2D) g;
    int xWidth =  this.getWidth() / 256;
    graph.setColor(Color.WHITE);
    if (this.image == null) {
      return;
    }

    int[][] histogram = this.image.getHistogram();


    // draw line graph from redPixels hashmap
    graph.setBackground(Color.WHITE);
    int[] xPoints = new int[256];
    for (int i = 0; i < 256; i++) {
      xPoints[i] = i * xWidth;
    }
    graph.setPaint(Color.RED);
    graph.drawPolyline(xPoints, histogram[0], 256);
    graph.setPaint(Color.GREEN);
    graph.drawPolyline(xPoints, histogram[1], 256);
    graph.setPaint(Color.BLUE);
    graph.drawPolyline(xPoints, histogram[2], 256);


  }
}
