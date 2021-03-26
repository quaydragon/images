package images;

import java.io.IOException;


public class ImageTransformerClass implements ImageTransformer {
  int clampMinimum;
  int clampMaximum;
  int[][][] transformedImage;
  int height;
  int width;
  int[][][] img;

  
  public ImageTransformerClass(Image image) throws IOException {
    this.clampMinimum = 0;
    this.clampMaximum = 255;
    this.height = image.getHeight();
    this.width = image.getWidth();
    this.img = image.loadImage();
//    this.transformedImage = transform(image);
    
  }
  
  //TODO: Consider taking out the reading and writing part of this image
  
  //TODO: Better document what this is doing
  

  //TODO: this would take in a function

  //TODO: Consider making this a private function
  
  public int[][][] transform(Image image) throws IOException {
    this.prepFunction();
    int[][][] newImage = null;
    try {
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {

          
          newImage = this.specificTransform(img, i, j);
          
        }
      }
    } catch (IOException e) {
      // Could not edit the image in the program
      newImage = img;
    }
//    image.writeImage(newImage);
    return newImage;
  }


  @Override
  public int[][][] specificTransform(int[][][] img, int i, int j) {

    return img;
  }
  



  private int clampValue(int value) {
    if (value < this.clampMinimum) {
      return 0;
    } 
    if (value > this.clampMaximum) {
      return 255;
    }
    return value;
  }

  @Override
  public void prepFunction() {
    // TODO Auto-generated method stub
    
  }


  
  
  
  //mosaic - will be called depending on what they want
  
  
  
  // pixelation
  
  
  //crossstitch

}
