package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates the Greyscale class which transforms images into greyscale. 
 * @author quaydragon
 *
 */
public class Greyscale extends ImageTransformerClass {
  protected List<Double> greyscale;

  /**
   * Constructs the Greyscale class from the image that it will transform.
   * @param image the image to be transformed
   * @throws IOException if the image cannot be loaded
   */
  public Greyscale(Image image) throws IOException {
    super(image);
    greyscale = Arrays.asList(0.2126, 0.7152, 0.0722);
  }
  
  
  /**
   * Transforms each of the pixels and their surrounding pixels by multiplying the arrays.
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) 
      throws IllegalArgumentException {
    if (newImg == null) {
      new IllegalArgumentException("Cannot Have A Null RGB MATRIX");
    }
    if (i > height || j > width || i < 0 || j < 0) {
      return newImg;
    }
    
    List<Double> filterList = new ArrayList<>();
    
    double redNumber = newImg[i][j][0];
    double greenNumber = newImg[i][j][1];
    double blueNumber = newImg[i][j][2];
          
    filterList.add(redNumber);
    filterList.add(greenNumber);
    filterList.add(blueNumber);
    
    
    int changedNumber = this.matrixMultiplication(filterList);

    
    img[i][j][0] = changedNumber;
    img[i][j][1] = changedNumber;
    img[i][j][2] = changedNumber;
      



          
    return newImg;
  }
  
  /**
   * This function computes a matrix multiplication for the  greyscale color transformation.
   * @param list the needed surrounding RGB values and the current value in order to compute
   * @return integer with the new RGB number value
   */
  private int matrixMultiplication(List<Double> list) {
    int ans = 0;

      
    for (int i = 0; i < this.greyscale.size(); i++) {
      ans += (int) Math.floor(greyscale.get(i) * list.get(i));
 
    }
    
    return this.clampValue(ans);
    

  }
}
