package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;

import javax.swing.JLabel;

/**
 * This represents the view class which is meant to handle the presentation
 * of the data as well as user interaction.
 */
public class ViewImpl extends JFrame implements IView {

    private ActionListener listener;

    private JPanel mainPanel = new JPanel();
    private JPanel imagePanel = new JPanel();
    private Histogram histogram = new Histogram();
    private JLabel fileOpenDisplay;
    private Image image;
    private JTextField brightenOrDarken;

    public void display() {
        this.setSize(getToolkit().getScreenSize());
        this.setBackground(Color.black);
        this.setTitle("Image Processor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileOpenDisplay = new JLabel("File path will appear here");

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
        commandPanel.setBorder(BorderFactory.createTitledBorder("Image filter buttons"));
        commandPanel.setBackground(Color.white);
        mainPanel.add(commandPanel);

        JScrollPane imageScrollPane = new JScrollPane(imagePanel);
        imagePanel.setBackground(Color.white);
        imagePanel.setLayout(new BorderLayout());
        mainPanel.add(imageScrollPane);
        mainPanel.add(histogram);


        JButton greyscale = new JButton();
        greyscale.setActionCommand("greyscale");
        greyscale.addActionListener(listener);
        greyscale.setSize(100, 40);
        greyscale.setBackground(Color.lightGray);
        greyscale.setText("Greyscale");
        commandPanel.add(greyscale);

        JButton verticalFlip = new JButton();
        verticalFlip.setActionCommand("flip_vertical");
        verticalFlip.addActionListener(listener);
        verticalFlip.setBackground(Color.lightGray);
        verticalFlip.setText("vertical-flip");
        commandPanel.add(verticalFlip);

        JButton horizontalFlip = new JButton();
        horizontalFlip.setActionCommand("flip_horizontal");
        horizontalFlip.addActionListener(listener);
        horizontalFlip.setBackground(Color.lightGray);
        horizontalFlip.setText("Horizontal Flip");
        commandPanel.add(horizontalFlip);

        JButton sepia = new JButton();
        sepia.setActionCommand("sepia");
        sepia.addActionListener(listener);
        sepia.setBackground(Color.lightGray);
        sepia.setText("Sepia");
        commandPanel.add(sepia);

        JButton blur = new JButton();
        blur.setActionCommand("blur");
        blur.addActionListener(listener);
        blur.setBackground(Color.lightGray);
        blur.setLocation(117, 170);
        blur.setText("blur");
        commandPanel.add(blur);

        JButton sharpen = new JButton();
        sharpen.setActionCommand("sharpen");
        sharpen.addActionListener(listener);
        sharpen.setBackground(Color.lightGray);
        sharpen.setText("sharpen");
        commandPanel.add(sharpen);

        brightenOrDarken = new JTextField("Brighten or Darken");
        brightenOrDarken.setActionCommand("brighten-or-darken");
        brightenOrDarken.addActionListener(listener);
        brightenOrDarken.setMaximumSize(new Dimension(500, 50));
        commandPanel.add(brightenOrDarken);

        JButton load = new JButton();
        load.setActionCommand("load");
        load.addActionListener(listener);
        load.setBackground(Color.lightGray);
        load.setText("load");
        commandPanel.add(load);

        JButton save = new JButton();
        save.setActionCommand("save");
        save.addActionListener(listener);
        save.setBackground(Color.lightGray);
        save.setText("save");
        commandPanel.add(save);

        this.setVisible(true);
    }

    public ViewImpl() {
        super();
        this.image = null;
    }

    public String getInput(){
        return brightenOrDarken.getText();
    }


    public void displayImage(Image image) {
        JLabel label = new JLabel(new ImageIcon(image.toBufferedImage()));
        imagePanel.removeAll();
        imagePanel.add(label);
        imagePanel.revalidate();
        imagePanel.repaint();
        histogram.setImage(image);
        this.image = image;
    }

    public void showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPEG, PPM, PNG, & BMP files", "jpeg", "png", "ppm", "bmp");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(ViewImpl.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileOpenDisplay.setText(selectedFile.getAbsolutePath());
            try {
                if(selectedFile.getAbsolutePath().endsWith("ppm")){
                    Image image = new ImageUtil().loadPPM(selectedFile.getAbsolutePath(), "current");
                    listener.actionPerformed(new ActionEvent(image, 0, "opened-image"));
                }
                else {
                    BufferedImage bufferedImage = ImageIO.read(selectedFile);
                    Image image = new Image(bufferedImage, selectedFile.getName());
                    listener.actionPerformed(new ActionEvent(image, 0, "opened-image"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPEG, PPM, PNG, & BMP files", "jpeg", "png", "ppm", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue2 = fchooser.showSaveDialog(ViewImpl.this);
        if (retvalue2 == JFileChooser.APPROVE_OPTION) {
            Path pth = fchooser.getSelectedFile().toPath();
            JOptionPane.showMessageDialog(null, pth.toString());
            try {
                if (pth.toString().endsWith("ppm")) {
                    new ImageUtil().savePPM(pth.toString(), this.image);
                } else {
                    new ImageUtil().saveOtherTypes(pth.toString(), this.image);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void setListener(ActionListener listener) {
        this.listener = listener;
    }


}

