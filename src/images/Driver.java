package images;

import java.io.IOException;

/**
 * This is the driver class for producing the edited images. 
 * @author quaydragon
 *
 */
public class Driver {
  
  /**
   * This class transforms the images using the filter class. 
   * @param args no args necessary this time
   * @throws IOException if program cannot load the image
   */
  public static void main(String[] args) throws IOException { 
    
    
   
    ImageClass me;
    
    me = new ImageClass("res/new.jpeg");
    
    int[][][] meInts = me.loadImage();
    
    Pixelation pixel;
    Mosaic mosaic;
    CrossStitch cross;
    
    pixel = new Pixelation(me, 30);
    
    int[][][] transformedPixel = pixel.transform();
    
    
    me.writeImage(transformedPixel, "res/newer2.jpeg");
//    System.out.println("Transformed");
    
//    mosaic = new Mosaic(me, 1000);
//    
//    int[][][] transformedMosaic = mosaic.transform(me);
//    
//    me.writeImage(transformedMosaic, "res/mosaic.jpeg");
//    
//    cross = new CrossStitch(me);
//    
//    int[][][] crossMade = cross.transform();
    
    
    
    
  }

}
