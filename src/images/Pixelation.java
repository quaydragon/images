package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Pixelation class. It pixelates an image. 
 * @author quaydragon
 *
 */
public class Pixelation extends ImageTransformerClass {
  int squares;
  int heightInterval;
  int widthInterval;
  int heightRemainder;
  int widthRemainder;
  List<ArrayList<Integer>> colorIntervalsRed;
  List<ArrayList<Integer>> colorIntervalsGreen;
  List<ArrayList<Integer>> colorIntervalsBlue;
  
  /**
   * This constructs the pixelation class. 
   * It takes in an image and the number of squares across and down that should 
   * be pixelated. 
   * 
   * @param image to pixelate
   * @param squares of squares across and down
   * @throws IOException if the image cannot be loaded
   */
  public Pixelation(Image image, int squares) throws IOException {
    super(image);
    this.squares = squares;
    this.heightInterval = this.height / squares;
    this.widthInterval = this.width / squares;
    this.colorIntervalsRed = new ArrayList<ArrayList<Integer>>();
    this.colorIntervalsGreen = new ArrayList<ArrayList<Integer>>();
    this.colorIntervalsBlue = new ArrayList<ArrayList<Integer>>();
       
    this.heightRemainder = this.height % squares;
    this.widthRemainder = this.width % squares;
  }
  
  

  /**
   * This function is used to generate the super pixels within their intervals. 
   */
  @Override
  public void prepFunction() { 
    
    for (int i = 0; i < this.height - heightInterval; i += heightInterval) {
      for (int j = 0; j < this.width - widthInterval; j += widthInterval) {

        
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        for (int x = i; x < i + heightInterval; x++) {
          for (int y = j; y < j + widthInterval; y++) {
            


            
            
            int red = img[x][y][0];
            redSum += red;
            int green = img[x][y][1];
            greenSum += green;
            int blue = img[x][y][2];
            blueSum += blue;
           
           
            
          }
            
        }
        
        ArrayList<Integer> red = new ArrayList<Integer>();
        int heightIntervalAdd = heightInterval;
        int widthIntervalAdd = widthInterval;
        
        if (heightRemainder > 0) {
          heightIntervalAdd += 1;
          this.heightRemainder -= 1;
        }
        
        if (widthRemainder > 0) {
          widthIntervalAdd += 1;
          this.widthRemainder -= 1;
        }
        
        int redAverage = redSum / (heightIntervalAdd * widthIntervalAdd);
        

        
        red.add(i + heightIntervalAdd);
        red.add(j + widthIntervalAdd);
        red.add(redAverage);
        colorIntervalsRed.add(red);
        
        int greenAverage = greenSum / (heightIntervalAdd * widthIntervalAdd);

        ArrayList<Integer> green = new ArrayList<Integer>();
        green.add(i + heightIntervalAdd);
        green.add(j + widthIntervalAdd);
        green.add(greenAverage);
        
        colorIntervalsGreen.add(green);
        
        int blueAverage = blueSum / (heightIntervalAdd * widthIntervalAdd);
        
        ArrayList<Integer> blue = new ArrayList<Integer>();
        blue.add(i + heightIntervalAdd);
        blue.add(j + widthIntervalAdd);
        blue.add(blueAverage);
        
        colorIntervalsBlue.add(blue);
        
        
      }
    }
    
    
  
  }
  

  /**
   * This function reassigns the color of the pixel to the correct color interval.
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    
    //TODO: Potentially combine into 1 for loop
    // It will be computationally better
 
    for (ArrayList<Integer> lilListRed: this.colorIntervalsRed) {
      if (i <= (int) lilListRed.get(0) & j <= (int) lilListRed.get(1)) {
        newImg[i][j][0] = (int) lilListRed.get(2);
        break;
      }
      
      
      
    }
      
    for (ArrayList<Integer> lilListGreen: this.colorIntervalsGreen) {
      if (i <= (int) lilListGreen.get(0) & j <= (int) lilListGreen.get(1)) {
        
        newImg[i][j][1] = (int) lilListGreen.get(2);
        break;
      }
      
    }
      
    for (ArrayList<Integer> lilListBlue: this.colorIntervalsBlue) {
      if (i <= (int) lilListBlue.get(0) & j <= (int) lilListBlue.get(1)) {
   
        newImg[i][j][2] = (int) lilListBlue.get(2);
        break;
      }
    
  
      
      
    }
   
    return newImg;
  }
    
    
    
    

}
