package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates the blur class.
 * @author quaydragon
 *
 */
public class Blur extends ImageTransformerClass {
  List<Double> blur;
  int tailLengths;
  
  /**
   * Constructs the Blur class using an image. Applies a blur to that image.
   * @param image the image to be blurred
   * @throws IOException if the image cannot be loaded
   */
  public Blur(Image image) throws IOException {
    super(image);
    blur = Arrays.asList(0.0625, 0.125, 0.0625, 0.125, 0.25, 0.25, 0.0625, 0.125, 0.0625);
    this.tailLengths = 1;
  }
  
  /**
   * Transforms each of the pixels and their surrounding pixels by multiplying the arrays.
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j)  {
    
    
    
    List<Double> red = new ArrayList<>();
    List<Double> green = new ArrayList<>();
    List<Double> blue = new ArrayList<>();
    
    for (int x = -this.tailLengths; x <= this.tailLengths; x++) {
      for (int y =  -this.tailLengths; y <= this.tailLengths; y++) {
        
        if (i + x > height || j + y > width) {
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
   * The dot product function to calculate sharpen or blur.
   * @param list of values to be used in the function
   * @return the calculated integer
   */
  private int dotProduct(List<Double> list) {
    int ans = 0;

    for (int i = 0; i < this.blur.size(); i++) {
      ans += (int) Math.floor(blur.get(i) * list.get(i));
    }
    
    return clampValue(ans);
    
  }

}
