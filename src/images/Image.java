package images;

//TODO: make a better exception
import java.io.IOException;

/**
 * This interface outlines the functions for the image class. 
 * @author quaydragon
 *
 */
public interface Image {
  
  /**
   * Loads the image using image utilities. 
   * @return the RGB array values
   * @throws IOException if the program cannot load image
   */
  int[][][] loadImage() throws IOException;
  
  /**
   * Writes the image back into its file after editing. 
   * @param imageMatrix the RGB array to be written
   * @throws IOException if the program cannot write image
   */
  void writeImage(int[][][] imageMatrix, String file) throws IOException;
  
  /**
   * Gets the height in rows of the image.
   * @return the integer value of the height
   * @throws IOException if the program cannot get the height of the image
   */
  int getHeight() throws IOException;
  
  /**
   * Gets the width in columns of the image.
   * @return the integer value of the width
   * @throws IOException if the program cannot get the width of the imafe
   */
  int getWidth() throws IOException;
  
  
  String getName();
 

}
