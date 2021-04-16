package images;

import java.io.File;
import java.io.IOException;

/**
 * Creates the image class which implements image and uses image utilities. 
 * @author quaydragon
 *
 */
public class ImageClass implements Image {
  public String filename;
  

  /**
   * Constructs the image class using the filename of the image. 
   * @param filename location and name of the file. 
   */
  public ImageClass(String filename) {
    
    File f = new File(filename);
    if (f.exists() && !f.isDirectory()) { 
      this.filename = filename;
    }
    
    
  }

  @Override
  public int[][][] loadImage() throws IOException {
    return ImageUtilities.readImage(this.filename);
    
  }

  @Override
  public void writeImage(int[][][] imageMatrix, String file) 
      throws IOException, IllegalArgumentException {
    if (imageMatrix == null || file == null) {
      throw new IllegalArgumentException("RGB Matrix or filename null");
    }
    
    int width = ImageUtilities.getWidth(this.filename);
    int height = ImageUtilities.getHeight(this.filename);
    
    ImageUtilities.writeImage(imageMatrix, width, height, file);
  }

  @Override
  public int getHeight() throws IOException {
    return ImageUtilities.getHeight(this.filename);
  }

  @Override
  public int getWidth() throws IOException {
    return ImageUtilities.getWidth(this.filename);
  }
  
  
  @Override
  public String getName() {
    return this.filename;
  }
  
  
  
  
  

}
