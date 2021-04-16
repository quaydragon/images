package model;

import images.Image;
import java.io.IOException;



public interface ImageModel {
  
  /**
   * Loads the images based on the file name.
   * @param filename to load
   * @return that image as an Image object
   */
  public Image load(String filename);
  
  /**
   * Saves the image.
   * @throws IOException if unable to save
   */
  public void save() throws IOException;
  
  /**
   * Saves the image with a new filename. 
   * @param newFile the new name of the file
   * @throws IOException unable to save
   */
  public void save(String newFile) throws IOException;
  
  /**
   * Blurs the image.
   * @return the rgb matrix of that image
   * @throws IOException if unable to read the int[][][] for the image
   */
  public int[][][] blur() throws IOException;
  
  /**
   * Creates a cross stitch pattern of the image.
   * @throws IOException unable to load the image
   */
  public void pattern() throws IOException;
  
  
  /**
   * Dithers the image to a limited number of colors.
   * @param maxColors the maximum number of colors within each channel the user wants
   * @return the rgb matrix associated with the updated image
   * @throws IllegalArgumentException if max colors is negative
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] dither(int maxColors) throws IllegalArgumentException, IOException;
  
  
  /**
   * Turns the image into greyscale.
   * @return the rgb matrix associated with the updated image
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] grey() throws IOException;
  
  /**
   * Creates a mosaic of the image. 
   * @param seed how many mosaic seeds the user wants
   * @return the rgb matrix associated with the updated image
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] mosaic(int seed) throws IOException;
  
  
  /**
   * Pixelates the image.
   * @param pixelsAcross how many pixels the user wants height and width wise. 
   * @return the rgb matrix associated with the updated image
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] pixelation(int pixelsAcross) throws IOException;
  
  /**
   * Creates a sepia version of the image. 
   * @return the rgb matrix associated with the updated image
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] sepia() throws IOException;
  
  /**
   * Sharpens the image.
   * @return the rgb matrix associated with the updated image
   * @throws IOException if the rgb matrix cannot be loaded
   */
  public int[][][] sharpen() throws IOException;
  
  /**
   * Replaces colors in the cross stitch pattern.
   * @param oldColor color to replace
   * @param newColor new color for the pattern
   * @throws IOException if the image or txt file cannot be loaded
   */
  public void replaceStitch(String oldColor, String newColor) throws IOException;
  
  /**
   * Check if an image has been loaded.
   * @return true if loaded
   */
  public boolean checkLoaded();
}
