package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mosaic extends ImageTransformerClass{
  int seed;
  int[][][] seedArray;
  int[][][] imageArray;
  List<ArrayList<Integer>> seedAssignment;

  
  public Mosaic(Image image, int seed) throws IOException {
    super(image);
    // for now the number of seeds cannot be greater than the number of combinations
    this.seed = seed;
    this.imageArray = image.loadImage();
    this.seedAssignment = new ArrayList<ArrayList<Integer>>();
    
    // generate the points based on the picture 
    
    
  }
  
  
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    

    double distance = height * width;
    // go through each of the seeds
    // update which one is the closest with the color from that seed
    
    System.out.println("specific");
    System.out.println(this.seedAssignment);
    
    for (ArrayList seed: this.seedAssignment) {
      // calculate the distance
      
      
      System.out.println(seed);
      
      double dis = Math.sqrt((i - (int) seed.get(0))*(i - (int) seed.get(0))
          + (j - (int) seed.get(1))*(j - (int) seed.get(1)));
      
     System.out.println(dis);
      
      //compare the distance
      
      if (dis < distance) {
        distance = (int) dis;
        
        System.out.println("We found shorter distance");
        
        newImg[i][j][0] = this.img[(int) seed.get(0)][(int) seed.get(1)][0];
        newImg[i][j][1] = this.img[(int) seed.get(0)][(int) seed.get(1)][1];
        newImg[i][j][2] = this.img[(int) seed.get(0)][(int) seed.get(1)][2];
        
      }
      
      // update the color
      
    }

    return newImg;
  }
  
  
  // I think that assigning the seeds at random is actually the best
  
//TODO: Write a better javadoc  
  /**
   * Assigning the seeds
   */
  @Override
  public void prepFunction() {
    
    int minimum = 0;
    int widthMax = this.width;
    int heightMax = this.height;
    
    //iterate through the number of seeds
   
    
    
    for(int s = 0; s < this.seed; s++) {
      ArrayList<Integer> seed = new ArrayList();
      Random rand = new Random();
      int randomWidth = rand.nextInt(widthMax);
      int randomHeight = rand.nextInt(heightMax);
      seed.add(randomHeight);
      seed.add(randomWidth);
      
      this.seedAssignment.add(seed);
      
      
      
    }
    
    
//    return null;
  }
  
  

}
