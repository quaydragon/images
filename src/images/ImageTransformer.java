package images;

import java.io.IOException;

/**
 * The interface for the ImageTransformer. 
 * Creates the important image transforming methods. 
 * @author quaydragon
 *
 */
public interface ImageTransformer {
  
  /**
   * Transforms the image into a matrix of the new rgb values. 
   * @return the changed matrix
   * @throws IOException if the image cannot load
   */
  public int[][][] transform() throws IOException;
  
  /**
   * A helper function that takes the individual points and changes
   * them into their desired form. 
   * @param img the img matrix of points pre transformation
   * @param i the height location of the specific point
   * @param j the width location of the specific point
   * @return a transformed matrix
   */
  public int[][][] specificTransform(int[][][] img, int i, int j) throws IOException;

  /**
   * A function that loads any needed data for the image transformation.
   */
  public void prepFunction() throws IOException;

  
  /**
   * Clamps the color values to be between 0 - 255 to match the rgb scale.
   * @param value the value to be clamped
   * @return the clamped value
   */
  public int clampValue(int value);

}
