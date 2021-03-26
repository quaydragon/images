package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pixelation extends ImageTransformerClass {
  int squares;
  int heightInterval;
  int widthInterval;
  List<ArrayList<Integer>> colorIntervalsRed;
  List<ArrayList<Integer>> colorIntervalsGreen;
  List<ArrayList<Integer>> colorIntervalsBlue;
  
  
  public Pixelation(Image image, int squares) throws IOException {
    super(image);
    this.squares = squares;
    this.heightInterval = this.height / squares;
    this.widthInterval = this.width / squares;
    this.colorIntervalsRed = new ArrayList<ArrayList<Integer>>();
    this.colorIntervalsGreen = new ArrayList<ArrayList<Integer>>();
    this.colorIntervalsBlue = new ArrayList<ArrayList<Integer>>();
    
    //issue is the application transform method is called prior to this
    //TODO call it in the method
  }
  
  
  // generate the colors for those intervals
  
  private void colorIntervalCreator() { 
    

    
    int[][][] colors = new int[height][width][3];
    
    

      for (int i = 0; i < this.height - heightInterval; i += heightInterval) {
        for (int j = 0; j < this.width - widthInterval; j += widthInterval) {

          
         int redSum = 0;
         int greenSum = 0;
         int blueSum = 0;


         // This gets all of the colors within that square 
          for(int x = i; x < i + heightInterval; x++) {
            for(int y = j; y < j + widthInterval; y++) {

              
              
              int red = img[x][y][0];
              redSum += red;
              int green = img[x][y][1];
              greenSum += green;
              int blue = img[x][y][2];
              blueSum += blue;
             
             
              
            }
              
          }
          
          
          int redAverage = redSum / (heightInterval * widthInterval);
          int greenAverage = greenSum / (heightInterval * widthInterval);
          int blueAverage = blueSum / (heightInterval * widthInterval);
          

          
          ArrayList<Integer> red = new ArrayList();
          red.add(i+heightInterval);
          red.add(j+widthInterval);
          red.add(redAverage);
          
          colorIntervalsRed.add(red);
          
          ArrayList<Integer> green = new ArrayList();
          green.add(i+heightInterval);
          green.add(j+widthInterval);
          green.add(greenAverage);
          
          colorIntervalsGreen.add(green);
          
          ArrayList<Integer> blue = new ArrayList();
          blue.add(i+heightInterval);
          blue.add(j+widthInterval);
          blue.add(blueAverage);
          
          colorIntervalsBlue.add(blue);
          
          
        }
      }

    
    

    
//    return colors;
    
    
  
  }
  
  
  @Override
  public int[][][] transform(Image image) throws IOException {
    
//    int[][][] colors = this.colorIntervalCreator();
    this.colorIntervalCreator();
    // TODO: There are repeats in here
//    System.out.println(colorIntervalsRed.toString());
//    System.out.println("Created the colors");
    
    int[][][] img = image.loadImage();
    int[][][] newImage = null;
    
    try {
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          //take the interval between both of the is
          // take the interval between both of the js 
          
          // send it to another method for the average
          
          // but that doesnt assign a specific color to each so we still need this
          
          
          newImage = this.specificTransform(img, i, j);
          
        }
      }
    } catch (IOException e) {
      // Could not edit the image in the program
      newImage = img;
    }

    return newImage;
  }
  
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {

    
    
    
    
    
    for(ArrayList lilListRed: this.colorIntervalsRed) {

      
      
      if (i <= (int) lilListRed.get(0) & j <= (int) lilListRed.get(1)) {
        
        newImg[i][j][0] = (int) lilListRed.get(2);
        break;
      }
      
      
      
    }
      
    for(ArrayList lilListGreen: this.colorIntervalsGreen) {
      if (i <= (int) lilListGreen.get(0) & j <= (int) lilListGreen.get(1)) {
        
        newImg[i][j][1] = (int) lilListGreen.get(2);
        break;
      }
      
    }
      
      for(ArrayList lilListBlue: this.colorIntervalsBlue) {
        if (i <= (int) lilListBlue.get(0) & j <= (int) lilListBlue.get(1)) {
          
          newImg[i][j][2] = (int) lilListBlue.get(2);
          break;
        }
    
  
      
      
    }
   
    return newImg;
  }
    
    
    
    

}
