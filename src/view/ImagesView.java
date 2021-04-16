package view;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Organizes the methods used by the view. 
 * @author quaydragon
 *
 */
public interface ImagesView {
  
  /**
   * Gets the inputs from the individual image process.
   * @return A list of actions for the controller and model to take
   */
  public List<String> getInputString();
  
  /**
   * Sets up the button listeners in the class.
   * @param listner the listener that will transfer to the controller
   */
  public void setListener(ActionListener listner);
  
  /**
   * Sets the display to visible.
   */
  public void display();
  
  /**
   * Sets up the individual text boxes for each of the process choices.
   * @param command what text to set up
   */
  public void displayFileInputs(String command);
  
  /**
   * Displays the image.
   * @param filename of the image to be displayed
   * @throws IOException if the image cannot be loaded
   */
  public void displayImage(String filename) throws IOException;
  
  /**
   * Refreshes the view.
   */
  public void refresh();
  
  /**
   * Displays the pattern text. 
   * @param filename of the pattern text
   * @throws IOException if the file cannot be loaded
   */
  public void displayTxt(String filename) throws IOException;
  
  /**
   * Shows alerts on the screen.
   * @param alert the alert to display
   */
  public void showAlert(String alert);

}
