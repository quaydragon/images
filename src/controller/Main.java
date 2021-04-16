package controller;


import model.ImageModelClass;
import view.ImagesViewClass;

/**
 * Drives the program.
 * @author quaydragon
 *
 */
public class Main {
  
  /**
   * Runs the ImageModelClass and the ImagesViewClass as a program.
   * 
   * @param args any string arguments
   */
  public static void main(String[] args) {
    
    ImageModelClass model = new ImageModelClass();
    
    ImagesViewClass imageView = new ImagesViewClass();
    
    new Controller(model, imageView);
  }
  

}
