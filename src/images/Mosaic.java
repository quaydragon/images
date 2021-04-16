package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class transforms an image into a mosaic.
 * @author quaydragon
 *
 */
public class Mosaic extends ImageTransformerClass {
  protected int seed;
  protected int[][][] seedArray;
  protected int[][][] imageArray;
  protected List<List<Integer>> seedAssignment;

  /**
   * The Mosaic constructor creates the Mosaic class object.
   * @param image the image to be transformed
   * @param seed the number of seeds in the mosaic
   * @throws IOException thrown if the image cannot be loaded
   */
  public Mosaic(Image image, int seed) throws IOException {
    super(image);
    // for now the number of seeds cannot be greater than the number of combinations
    this.seed = seed;
    this.imageArray = image.loadImage();
    this.seedAssignment = new ArrayList<List<Integer>>();
    
    // generate the points based on the picture 
    
    
  }
  
  
  /**
   * Calculates the distance from the point to a seed value.
   * Returns the color from the closest seed. 
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
    
    //Distance could never be greater than the height * width
    double distance = height * width;
    for (List<Integer> seed: this.seedAssignment) {
      // Calculates the distance from the seeds
      double dis = Math.sqrt((i - (int) seed.get(0)) * (i - (int) seed.get(0))
          + (j - (int) seed.get(1)) * (j - (int) seed.get(1)));
      // Updates if there is a closer seed
      if (dis < distance) {
        distance = (int) dis;
        
        newImg[i][j][0] = this.img[(int) seed.get(0)][(int) seed.get(1)][0];
        newImg[i][j][1] = this.img[(int) seed.get(0)][(int) seed.get(1)][1];
        newImg[i][j][2] = this.img[(int) seed.get(0)][(int) seed.get(1)][2];
        
      }
      
    }

    return newImg;
  }
  

  /**
   * Generates random seeds across the image.
   */
  @Override
  public void prepFunction() {
    
    int widthMax = this.width;
    int heightMax = this.height;
        
    for (int s = 0; s < this.seed; s++) {
      List<Integer> seed = new ArrayList<Integer>();
      Random rand = new Random();
      int randomWidth = rand.nextInt(widthMax);
      int randomHeight = rand.nextInt(heightMax);
      seed.add(randomHeight);
      seed.add(randomWidth);
      
      this.seedAssignment.add(seed);
      
      
      
    }

  }
  
  

}
