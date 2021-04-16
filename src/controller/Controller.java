package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.ImageModel;
import view.ImagesView;


/**
 * Controller which connects the ImagesModel and the ImagesView.
 * @author quaydragon
 *
 */
public class Controller implements ActionListener {
  private ImageModel model;
  private ImagesView view;

  
  /**
   * This controller handles the interactions between the images model and the images view. 
   * 
   * @param m ImageModel which allows us to manipulate images
   * @param v the view for the images
   */
  public Controller(ImageModel m, ImagesView v) {
    model = m;
    view = v;
    view.setListener(this);
    view.display();   
  }
  

  
  // Checks if file exists
  private boolean makeSureFileExists(String filename) {
    File f = new File(filename);
    if (f.exists() && !f.isDirectory()) { 
      return true;

    }
    return false;
  }
  
  /**
   * Controls the image being displayed.
   * @param filename the filename of the image that will be displayed
   * @throws IOException if the image does ot exist
   */
  private void controlImage(String filename) throws IOException {
    if (!makeSureFileExists(filename)) {
      return;
    }
    view.displayImage(filename);
    File myObj = new File("res/temp.jpeg");
    myObj.delete();


    

  }
  
  
  /**
   * Controls the txt file that will be displayed.
   * @param filename the name of the file to be displayed
   * @throws IOException the error thrown if the file does not exist
   */
  private void controlTxt(String filename) throws IOException {
    if (!makeSureFileExists(filename)) {
      return;
    }
    view.displayTxt(filename);    
    File myObj = new File(filename);
    myObj.delete();
  }
  
  
  
  /**
   * This function handles a batch file which is given to the view.
   * @param filename the name of the file to be handled
   * @throws IOException if the file cannot be found
   */
  private  void handleBatch(String filename) throws IOException {
    if (!makeSureFileExists(filename)) {
      return;
    }
    
    String inputFile = filename;

    File file = new File(inputFile);
    BufferedReader br = new BufferedReader(new FileReader(file)); 
    
    String st;
    
    
    while ((st = br.readLine()) != null) {
      List<String> actions = new ArrayList<String>();
      String command = st.split(" ")[0].trim();
      actions.add(command);
      if (st.split(" ").length > 1) {
        String component = st.split(" ")[1].trim();
        actions.add(component);
      }
      handleSubmit(actions);

      
     

    }
    br.close();
    
  }
  

  /**
   * Check if there is a loaded file.
   * @return if the file is loaded
   */
  private boolean checkLoaded() {
    return model.checkLoaded();
  }
  
  
  

  
  //load submit
  private void loadSubmit(List<String> submit) throws IOException {
    model.load(submit.get(1));
    view.displayImage(submit.get(1));
    view.refresh();
    
  }
  

  
  
  //sepia submit
  private void sepiaSubmit(List<String> submit) throws IOException {
    model.sepia();
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  // greyscale submit
  private void greySubmit(List<String> submit) throws IOException {
    model.grey();
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  // sharpen submit
  private void sharpenSubmit(List<String> submit) throws IOException {
    model.sharpen();
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  //blur submit
  private void blurSubmit(List<String> submit) throws IOException {
    model.blur();
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  // save submit
  private void saveSubmit(List<String> submit) throws IOException {
    if (submit.get(1).isBlank()) {
      view.showAlert("Add Input");
      view.displayFileInputs("Save");
      view.refresh();
      return;
    }
    model.save(submit.get(1));
    
  }
  
  // pattern submit
  private void patternSubmit(List<String> submit) throws IOException {


    
    if (submit.get(1).isBlank() == false && submit.get(2).isBlank()) {
      System.out.println("Replace with blank");
      model.replaceStitch(submit.get(1), null);
      
    } else if (submit.get(1).isBlank() == false && submit.get(2).isBlank() == false) {
      System.out.println("Replace with chosen color");
      model.replaceStitch(submit.get(1), submit.get(2));
    } else {
      model.pattern();
    }
    this.controlTxt("res/temp.txt");
    
    view.refresh();
    
  }
  
  // mosaic submit
  private void mosaicSubmit(List<String> submit) throws IOException {
    if (submit.get(1).isBlank()) {
      view.showAlert("Add Input");
      view.displayFileInputs("Mosaic");
      view.refresh();
      return;
    }
    int seed = Integer.valueOf(submit.get(1));
    model.mosaic(seed);
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  //pixelate submit
  private void pixelSubmit(List<String> submit) throws IOException {
    if (submit.get(1).isBlank()) {
      view.showAlert("Add Input");
      view.displayFileInputs("Pixelate");
      return;
    }
    
    model.pixelation(Integer.valueOf(submit.get(1)));
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  // dither submit
  private void ditherSubmit(List<String> submit) throws IOException {
    if (submit.get(1).isBlank()) {
      view.showAlert("Add Input");
      view.displayFileInputs("dither");
      return;
    }
    
    model.dither(Integer.valueOf(submit.get(1)));
    this.controlImage("res/temp.jpeg");
    view.refresh();
    
  }
  
  /**
   * Handles when the submit button is pressed.
   * @param submit the list of actions and commands given when the submit button is pressed
   * @throws IOException if the files cannot be loaded or saved
   */
  private void handleSubmit(List<String> submit) throws IOException {
    
    if (submit.size() < 1) {
      // Not Throwing Exception Because I do not want to stop program
      return;
    }
   
    String action = submit.get(0);
    
    //TODO: Should make it so you can load an image at any time
    


    if (!checkLoaded()) {
        
      switch (action.toLowerCase()) {
        case "load":
          System.out.println("load");
          this.loadSubmit(submit);
          break;
          
        case "batch":
          handleBatch(submit.get(1));    
          break;
          
        default:
          view.showAlert("Image not loaded");
      }
      
    } else if (submit.size() == 1) {
      
      switch (action.toLowerCase()) {
      

          
        case "sepia":
          this.sepiaSubmit(submit);
          break;
        case "blur":
          this.blurSubmit(submit);

          break;
        case "sharpen":
          this.sharpenSubmit(submit);

          break;
        case "greyscale":
          this.greySubmit(submit);

          break;
          
        default:
          view.showAlert("Requires input."); }
    } else {
      
        

      switch (action.toLowerCase()) {
      
        case "save":
          this.saveSubmit(submit);
          
          break;

          
        case "pattern":
          this.patternSubmit(submit);

          break;
          

        case "mosaic":
          this.mosaicSubmit(submit);

          break;
          
        case "pixelate":
          this.pixelSubmit(submit);

          break;
          
        case "dither":
          this.ditherSubmit(submit);

          break;
          
  
        
         
        default:
          break;
        
      }
      
    }
     
  }

  
  /**
   * Takes in all of the listeners from the ImagesViewClass.
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    
    switch (e.getActionCommand().toLowerCase()) {
    
      case "load":
        System.out.println("Load");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
      case "save":
        System.out.println("Save");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
      case "blur":
        view.displayFileInputs(e.getActionCommand());
        break;
        
      case "sharpen":
        System.out.println("Sharpen");
        view.displayFileInputs(e.getActionCommand());
        break;
        
        
      case "greyscale":
        System.out.println("Greyscale");
        view.displayFileInputs(e.getActionCommand());
        break;
        
      case "sepia":
        System.out.println("Sepia");
        view.displayFileInputs(e.getActionCommand());
        break;
        
        
      case "dither":
        System.out.println("Dither");
        view.displayFileInputs(e.getActionCommand());
        
        view.refresh();
        break;
        
      case "mosaic":
        System.out.println("Mosaic");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
        
      case "pixelate":
        System.out.println("Pixelate");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
      case "pattern":
        System.out.println("Pattern");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
      case "add batch file":
        System.out.println("Batch");
        view.displayFileInputs(e.getActionCommand());
        view.refresh();
        break;
        
      case "update file":
        System.out.println("Update case");
        
        try {
          model.save();
        } catch (IOException e2) {
          // need the try catch to avoid unneccessarily going back through the view
        }
        view.refresh();
        break;
        
      case "submit":
        System.out.println("Submit made");
                
        
        
        try {

          this.handleSubmit(view.getInputString());
        } catch (IOException e1) {
          // need the try catch to avoid unneccessarily going back through the view
        }
          
        view.refresh();
        break;
      
      
      default:
        view.showAlert("That is not a known command");
        break;
    
    }
  
    
  }

}
