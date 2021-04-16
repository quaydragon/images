package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Dithering class extends the filter class. 
 * The dithering class uses the Floyd-Steinburg algorithm to reduce the possible values per channel.
 * @author quaydragon
 *
 */
public class Dithering extends ImageTransformerClass {
  protected int maxColors;
  protected List<Integer> intervalList;
  
  /**
   * Constructs the dithering filter class with maxColors 
   * to define how many colors allowed in the channel.
   * 
   * @param maxColors the maximum number of colors allowed within a channel
   * @throws IOException if the image cannot be loaded
   */
  public Dithering(Image image, int maxColors) throws IllegalArgumentException, IOException {
    super(image);
    if (maxColors < 0) {
      throw new IllegalArgumentException("max colors cannot be negative");
    }
    this.maxColors = maxColors;
    this.intervalList = this.interval(maxColors);
    
  }
  
  /**
   * Determines the interval for the maximum amount of colors. 
   * @param maxColors the max colors allowed in the channel
   * @return the list of intervals
   */
  private List<Integer> interval(int maxColors) {
    List<Integer> intervals = new ArrayList<>();
    int numberIntervals = 256 / maxColors;
    
    for (int i = 0; i < maxColors; i++) {
      intervals.add(i * numberIntervals);
      
    }
    
    return intervals;
  }
  
  /**
   * This function rounds the original colors to the interval numbers. 
   * @param numberToRound number that needs rounding
   * @return the rounded number
   */ 
  private int round(int numberToRound) {
    for (int i = 0; i < intervalList.size(); i++) {
      
      if (i == intervalList.size() - 1) {
        return intervalList.get(i);
      }
      if (numberToRound < intervalList.get(i + 1)) {
        return intervalList.get(i);
      }

      
      
    }
    return numberToRound;
  }
  
  
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    
    if (i > height || j > width || i < 0 || j < 0) {
      return newImg;
    }
    int oldColorR = newImg[i][j][0];
    int oldColorG = newImg[i][j][1];
    int oldColorB = newImg[i][j][2];
    
   
    
    int newColorR = this.round(oldColorR);
    int newColorG = this.round(oldColorG);
    int newColorB = this.round(oldColorB);
    

    
    
    

    
    newImg[i][j][0] = newColorR;
    newImg[i][j][1] = newColorG;
    newImg[i][j][2] = newColorB;
    
    int redError = oldColorR - newColorR;
    int greenError = oldColorG - newColorG;
    int blueError = oldColorB - newColorB;
    int[][][] redImg = this.floydSteinburg(newImg, 0, redError, i, j);
    int[][][] greenImg = this.floydSteinburg(redImg, 1, greenError, i, j);
    int[][][] finishedImg = this.floydSteinburg(greenImg, 1, blueError, i, j);
 
    
    return finishedImg;
    
  }
  
  /**
   * This function computes the floyfSteinburg algorithm. 
   * @param img the rgb matrix for the image
   * @param colorNumber the number of the row within the final array to access red, blue, or green
   * @param error the error term calculated in the filter helper 
   * @param i the row value
   * @param j the column value
   * @return
   */
  private int[][][] floydSteinburg(int[][][] img, int colorNumber, int error, int i, int j) {
    
    
    if (j + 1 < width) {
      int errorEquation = (int) ((7.0 / 16.0) * error) + img[i][j + 1][colorNumber];
      img[i][j + 1][colorNumber] = this.clampValue(errorEquation);
      
    }
    
    if (i + 1 < height && j - 1 > 0) {
      int errorEquation = (int) ((3.0 / 16.0) * error) + img[i + 1][j - 1][colorNumber];
      img[i + 1 ][j - 1][colorNumber] = this.clampValue(errorEquation);
      
    }
    
    if (i + 1 < height) {
      
      int errorEquation = (int) ((5.0 / 16.0) * error) + img[i + 1][j][colorNumber];
      img[i + 1][j][colorNumber] = this.clampValue(errorEquation);
      
    }
    
    if (i + 1 < height && j + 1 < width) {
      
      int errorEquation = (int ) ((1.0 / 16.0) * error) + img[i + 1][j + 1][colorNumber];
      img[i + 1][j + 1][colorNumber] = this.clampValue(errorEquation);
      
    }
    
    return img;
  }
  
  

}
