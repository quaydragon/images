package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates the Sepia class which transforms images into sepia. 
 * @author quaydragon
 *
 */
public class Sepia extends ImageTransformerClass {


  /**
   * Constructs the sepia class from the image that it will transform.
   * @param image to be transformed
   * @throws IOException if the image cannot be loaded
   */
  public Sepia(Image image) throws IOException {
    super(image);

    
  }
  
  /**
   * Transforms each of the pixels and their surrounding pixels by multiplying the arrays.
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    
    List<Double> filterList = new ArrayList<>();
    
    double redNumber = newImg[i][j][0];
    double greenNumber = newImg[i][j][1];
    double blueNumber = newImg[i][j][2];
          
    filterList.add(redNumber);
    filterList.add(greenNumber);
    filterList.add(blueNumber);
    
    

      
    newImg[i][j][0] = this.sepiaMatrixMultiplication(filterList, "Red");
    newImg[i][j][1] = this.sepiaMatrixMultiplication(filterList, "Green");
    newImg[i][j][2] = this.sepiaMatrixMultiplication(filterList, "Blue");
      



          
    return newImg;
  }
  
  /**
   * Computes the sepia color transformation with matrix multiplication.
   * @param list the list of values from the image array to go into the transformation
   * @param type the type of transformation being done
   * @return
   */
  private int sepiaMatrixMultiplication(List<Double> list, String type) {
    
    int ans = 0;
    
    Double red = list.get(0);
    Double green = list.get(1);
    Double blue = list.get(2);
    
    if (type.equals("Red")) {
      
      double newRed = red * 0.393;
      double newGreen = green * 0.769;
      double newBlue = blue * 0.189;
      
      ans += newRed + newGreen + newBlue;
      
    } else if (type.equals("Green")) {
      
      double newRed = red * 0.349;
      double newGreen = green * 0.686;
      double newBlue = blue * 0.168;
      
      ans += newRed + newGreen + newBlue;
      
    } else {
      
      double newRed = red * 0.272;
      double newGreen = green * 0.534;
      double newBlue = blue * 0.131;
      
      ans += newRed + newGreen + newBlue;
      
    }
    
    return this.clampValue(ans);
  }
  
  

}
