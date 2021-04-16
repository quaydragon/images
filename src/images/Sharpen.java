package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Sharpen class sharpens an image.
 * @author quaydragon
 *
 */
public class Sharpen extends ImageTransformerClass {
  protected List<Double> sharpen;
  protected int tailLengths;

  /**
   * Constructs the sharpen class from an image that will be sharpened. 
   * @param image the image to be sharpened
   * @throws IOException if the image can not be loaded
   */
  public Sharpen(Image image) throws IOException {
    super(image);
    sharpen = Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125,
        -0.125, .25, .25, .25, -0.125,
        -0.125, .25, 1.0, .25, -0.125,
        -0.125, .25, .25, .25, -0.125,
        -0.125, -0.125, -0.125, -0.125, -0.125);
    tailLengths = 2;

  }
  
  /**
   * Transforms each of the pixels and their surrounding pixels by multiplying the arrays.
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    
    if (i > height || j > width || i < 0 || j < 0) {
      return newImg;
    }
    
    List<Double> red = new ArrayList<>();
    List<Double> green = new ArrayList<>();
    List<Double> blue = new ArrayList<>();
    
    for (int x = -this.tailLengths; x <= this.tailLengths; x++) {
      for (int y =  -this.tailLengths; y <= this.tailLengths; y++) {
        
        if (i + x >= height 
            || j + y >= width
            || i + x < 0
            || j + y < 0) {
          red.add((double) 0);
          green.add((double) 0);
          blue.add((double) 0);

        } else {
          double redInt = newImg[i + x][j + y][0];
          red.add(redInt);
          double greenInt = newImg[i + x][j + y][1];
          green.add(greenInt);
          double blueInt = newImg[i + x][j + y][2];
          blue.add(blueInt);

        }
        
      }
    }
      
  
    int redInt = this.dotProduct(red);
    int greenInt = this.dotProduct(green);
    int blueInt = this.dotProduct(blue);
    
    
    newImg[i][j][0] = redInt;
    newImg[i][j][1] = greenInt;
    newImg[i][j][2] = blueInt;
    
    
    return newImg;
    
    
    
  }
  
  /**
   * The dot product function to calculate sharpen.
   * @param list of values to be used in the function
   * @return the calculated integer
   */
  private int dotProduct(List<Double> list) {
    int ans = 0;

    for (int i = 0; i < this.sharpen.size(); i++) {
      ans += (int) Math.floor(sharpen.get(i) * list.get(i));
    }
    
    return clampValue(ans);
    
  }
  

}
