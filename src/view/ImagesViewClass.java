package view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/**
 * The ImageViewClass used to display and manipulate images. 
 * @author quaydragon
 *
 */
public class ImagesViewClass extends JFrame implements ImagesView {
  private static final long serialVersionUID = -7083924619099998893L;
  
  //TODO: Make Dynamic
    
  //buttons
  private JButton blur;
  private JButton sharpen;
  private JButton greyscale;
  private JButton sepia;
  private JButton dither;
  private JButton mosaic;
  private JButton pixelation;
  private JButton crossStitch;
  private JButton load;
  private JButton save;
  private JButton batch;
  // submit button
  private JButton submit;
  private JButton update;
  // Input text fields
  private JTextField loadFilename;
  private JTextField saveFilename;
  private JTextField batchFilename;
  private JTextField differentColor;
  private JTextField removeColor;
  private JTextField mosaicSeeds;
  private JTextField pixelationUnits;
  private JTextField ditheringColorLimit;
  // Labels
  private JLabel filename;
  private JLabel colorToRemove;
  private JLabel newColor;
  private JLabel numberOfSeeds;
  private JLabel numberOfPixels;
  private JLabel ditherColorLimit;
  // Layouts
  private GridBagLayout layout;
  private GridBagConstraints constraints;
  private JTextField submitPressed;
  private JTextField submitPressedSecond;
  private String action;
  
  
  // The image
  
  private BufferedImage image;
  private JLabel picLabel;
  
  // Text Area
  JTextArea stitch;
  JScrollPane scrollPane;
  FileReader reader;
  
  /**
   * Initializes the ImageViewClass.
   */
  public ImagesViewClass() {
    
    // Submit Pressed flag
    submitPressed = null;

    
    
    // Dimension and Layout
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(1000, 700));
    layout = new GridBagLayout();
    this.setLayout(layout);
    constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 0.75;
    setResizable(false);
    

    // Button Creation
    
    //Load
    load = new JButton("Load");
    loadSetup();
    //save
    save = new JButton("Save");
    saveSetup();
    //Blur
    blur = new JButton("Blur");
    blurSetup();
    //Sharpen
    sharpen = new JButton("Sharpen");
    sharpenSetup();
    //greyscale
    greyscale = new JButton("Greyscale");
    greySetup();
    //sepia set up
    sepia = new JButton("Sepia");
    sepiaSetup();
    //mosaic setup
    mosaic = new JButton("Mosaic");
    mosaicSetup();
    //pixel setup
    pixelation = new JButton("Pixelate");
    pixelSetup();
    //dither setup
    dither = new JButton("Dither");
    ditherSetup();
    //pattern setup
    crossStitch = new JButton("Pattern");
    patternSetup();

    batch = new JButton("Add Batch File");
    batchSetup();
    pack();
    
    update = new JButton("Update file");
    
    updateSetup();
    pack();
    
    // Text Inputs
    
    loadFilename = new JTextField(5);
    saveFilename = new JTextField(5);
    batchFilename = new JTextField(5);
    differentColor = new JTextField(5);
    removeColor = new JTextField(5);
    mosaicSeeds = new JTextField(5);
    pixelationUnits = new JTextField(5);
    ditheringColorLimit = new JTextField(5);
    
    
    // Labels
    
    filename = new JLabel("Please enter the file name:");
    colorToRemove = new JLabel("Please enter the color to remove or replace:");
    newColor = new JLabel("Replacement Color:");
    numberOfSeeds = new JLabel("Number of Mosaic Seeds:");
    numberOfPixels = new JLabel("Number of Pixels:");
    ditherColorLimit = new JLabel("Limit on image colors:"); 
    // submit
    submit = new JButton("Submit");

    
    // stitch area
    
    stitch = new JTextArea(20, 20);
    scrollPane = new JScrollPane(stitch);
  }

  // Checks if file exists
  private boolean makeSureFileExists(String filename) {
    File f = new File(filename);
    if (f.exists() && !f.isDirectory()) { 
      return true;

    }
    return false;
  }
  
  
  
  @Override
  public void displayImage(String filename) throws IOException {
    
    if (!makeSureFileExists(filename)) {
      this.showAlert("File does not exist");
      return;
    }
    if (picLabel != null) {
      this.remove(picLabel);
      
    }
    image = ImageIO.read(new File(filename));
    picLabel = new JLabel(new ImageIcon(image));
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 30;
    constraints.gridy = 60;
    this.add(picLabel, constraints);
    
  }
  
  
  @Override
  public void displayTxt(String filename) throws IOException {
    if (!makeSureFileExists(filename)) {
      this.showAlert("File does not exist");
      return;
    }
    reader = new FileReader("res/temp.txt");
    stitch.read(reader, "stitch.txt");
    
    constraints.gridx = 20;
    constraints.gridy = 60;
    this.add(scrollPane, constraints);
  }
  

  
  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
  }
  
  @Override
  public void display() {
    setVisible(true);
    
  }
  
  // listeners
  
  @Override
  public void setListener(ActionListener listner) {
    blur.addActionListener(listner);
    sharpen.addActionListener(listner);
    greyscale.addActionListener(listner);
    sepia.addActionListener(listner);
    dither.addActionListener(listner);
    mosaic.addActionListener(listner);
    pixelation.addActionListener(listner);
    crossStitch.addActionListener(listner);
    load.addActionListener(listner);
    save.addActionListener(listner);
    batch.addActionListener(listner);
    loadFilename.addActionListener(listner);
    submit.addActionListener(listner);
    update.addActionListener(listner);
    
    
  }

 
  // Input and direction from text fields
  
  @Override
  public List<String> getInputString() {
    List<String> returnList = new ArrayList<String>();
    returnList.add(action);
    
    
    if (submitPressed != null) {
      returnList.add(submitPressed.getText());
    }
    
    if (submitPressedSecond != null) {
      returnList.add(submitPressedSecond.getText());
    }
    
    System.out.println("Input string created");
    
    submitPressed = null;
    submitPressedSecond = null;
    
    return returnList;
  }


  @Override
  public void displayFileInputs(String command) {
    this.removeOldFields();
    
    constraints.gridx = 20;
    constraints.gridy = 20;
    
    switch (command) {
      case "Load":
        loadInput();
        break;
      case "Save":
        saveInput();
        break;
      case "Blur":
        blurInput();
        break;
      case "Sharpen":
        sharpenInput();
        break;
      case "Greyscale":
        greyInput();
        break;
      case "Sepia":
        sepiaInput();
        break;
      case "Add Batch File":
        batchInput();
        break;
      case "Pattern":
        patternInput();
        break;
        
      case "Mosaic":
        mosaicInput();
        break;
      case "Pixelate":
        pixelInput();
        
        break;
        
      case "Dither":
        ditherInput();
        break;
        
        
      default:
        break;
    }
    
    

    
    
    
  }
  
  
  // File Setups + Removals
  // Load Setup
  private void loadSetup() {
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 0;
    this.add(load, constraints);
  }
  
  
  // save setup
  private void saveSetup() {
    constraints.gridx = 10;
    constraints.gridy = 0;
    this.add(save, constraints);
  }
  
  // blur setup
  private void blurSetup() {
    constraints.gridx = 0;
    constraints.gridy = 10;
    this.add(blur, constraints);
  }
  
  //sharpen setup
  private void sharpenSetup() {
    constraints.gridx = 10;
    constraints.gridy = 10;
    this.add(sharpen, constraints);
  }
  
  //greyscale setup
  private void greySetup() {
    constraints.gridx = 0;
    constraints.gridy = 20;
    this.add(greyscale, constraints);
  }
  
  //sepia setup
  private void sepiaSetup() {
    constraints.gridx = 10;
    constraints.gridy = 20;
    this.add(sepia, constraints);
  }
  
  //mosaic setup
  private void mosaicSetup() {
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 30;
    this.add(mosaic, constraints);
  }
  
  //pixel setup
  private void pixelSetup() {
    constraints.gridx = 10;
    constraints.gridy = 30;
    this.add(pixelation, constraints);
  }
  
  // dither setup
  private void ditherSetup() {
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 40;
    this.add(dither, constraints);

  }
  
  //pattern setup
  private void patternSetup() {
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 10;
    constraints.gridy = 40;
    this.add(crossStitch, constraints);
  }
  
  // batch setup
  private void batchSetup() { 
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 50;
    this.add(batch, constraints);
  }
   
  // submit setup
  private void addSubmit() {
    constraints.gridx = 0;
    constraints.gridy = 60;
    this.add(submit, constraints);
  }
  
  // update setup
  
  private void updateSetup() {
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 10;
    constraints.gridy = 50;
    this.add(update, constraints);
  }
  
  
  /**
   * Removes the old fields when there is a transition.
   */
  private void removeOldFields() {
    
    this.remove(filename);
    this.remove(loadFilename);
    this.remove(saveFilename);
    this.remove(batchFilename);
    this.remove(colorToRemove);
    this.remove(removeColor);
    this.remove(newColor);
    this.remove(differentColor);
    this.remove(numberOfSeeds);
    this.remove(mosaicSeeds);
    this.remove(numberOfPixels);
    this.remove(pixelationUnits);
    this.remove(ditherColorLimit);
    this.remove(ditheringColorLimit);
    this.remove(batchFilename);
    this.remove(submit);
    this.remove(scrollPane);
    this.remove(stitch);
    pack();
    
  }

  // Handles display file inputs
  
  // load
  private void loadInput() {
    System.out.println("In view Load");
    this.add(filename, constraints);
    constraints.gridx = 30;
    this.add(loadFilename, constraints);
    this.addSubmit();
    this.submitPressed = loadFilename;
    this.action = "Load";
    pack();
  }
  
  //save
  private void saveInput() {
    this.add(filename, constraints);
    constraints.gridx = 30;
    this.add(saveFilename, constraints);
    this.addSubmit();
    this.submitPressed = saveFilename;
    this.action = "Save";
    pack();
    
  }

  // blur
  private void blurInput() {
    this.addSubmit();
    this.action = "Blur";
    pack();
    
  }
  
  //sharpen
  private void sharpenInput() {
    this.addSubmit();
    this.action = "Sharpen";
    pack();
    
  }
  
  // grey
  private void greyInput() {
    this.addSubmit();
    this.action = "Greyscale";
    pack();
    
  }
  
  //sepia
  private void sepiaInput() {
    this.addSubmit();
    this.action = "Sepia";
    pack();
    
  }
  
  // dither
  private void ditherInput() {
    this.add(ditherColorLimit, constraints);
    constraints.gridx = 30;
    this.add(ditheringColorLimit, constraints);
    this.addSubmit();
    this.submitPressed = ditheringColorLimit;
    this.action = "Dither";
    pack();
    
  }
  
  //mosaic
  private void mosaicInput() {
    this.add(numberOfSeeds, constraints);
    constraints.gridx = 30;
    this.add(mosaicSeeds, constraints);
    this.addSubmit();
    this.submitPressed = mosaicSeeds;
    this.action = "Mosaic";
    pack();
    
  }

  //pixelation
  private void pixelInput() {
    this.add(numberOfPixels, constraints);
    constraints.gridx = 30;
    this.add(pixelationUnits, constraints);
    this.addSubmit();
    this.submitPressed = pixelationUnits;
    this.action = "Pixelate";
    pack();
  }
  
  // pattern 
  private void patternInput() {
    this.add(colorToRemove, constraints);
    constraints.gridx = 30;
    this.add(removeColor, constraints);
    constraints.gridx = 20;
    constraints.gridy = 30;
    this.add(newColor, constraints);
    constraints.gridx = 30;
    this.add(differentColor, constraints);
    this.addSubmit();
    
    this.submitPressed = removeColor;
    this.submitPressedSecond = differentColor;
    this.action = "Pattern";
    pack();
    
  }
  
  // batch
  private void batchInput() {
    this.add(filename, constraints);
    constraints.gridx = 30;
    this.add(batchFilename, constraints);
    this.addSubmit();
    this.submitPressed = batchFilename;
    this.action = "Batch";
    pack();
    
  }

  @Override
  public void showAlert(String alert) { 
    showMessageDialog(null, alert);
    
  }
}
