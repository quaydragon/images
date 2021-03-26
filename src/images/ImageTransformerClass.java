package images;

import java.io.IOException;


/**
 * The ImageTransformerClass is the parent class to all of the image transformations.
 * @author quaydragon
 *
 */
public class ImageTransformerClass implements ImageTransformer {
  int clampMinimum;
  int clampMaximum;
  int[][][] transformedImage;
  int height;
  int width;
  int[][][] img;

  /**
   * Takes in the image to be transformed by the sub-classes. 
   * @param image the image to be transformed
   * @throws IOException thrown if the image cannot be loaded
   */
  public ImageTransformerClass(Image image) throws IOException {
    this.clampMinimum = 0;
    this.clampMaximum = 255;
    this.height = image.getHeight();
    this.width = image.getWidth();
    this.img = image.loadImage();  
  }
  

  @Override
  public int[][][] transform() throws IOException {
    //TODO: Think about putting the prep function in the constructor
    this.prepFunction();
    int[][][] newImage = null;


    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {

        
        newImage = this.specificTransform(img, i, j);
        
      }
    }

    return newImage;
  }


  @Override
  public int[][][] specificTransform(int[][][] img, int i, int j) throws IOException {

    return img;
  }
  



  @Override
  public int clampValue(int value) {
    if (value < this.clampMinimum) {
      return 0;
    } 
    if (value > this.clampMaximum) {
      return 255;
    }
    return value;
  }

  @Override
  public void prepFunction() throws IOException {
    //Prep function used in all of the subclasses
  }

}
