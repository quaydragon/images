package test;

import static org.junit.Assert.assertEquals;

import images.Blur;
import images.CrossStitch;
import images.Dithering;
import images.Greyscale;
import images.ImageClass;
import images.Mosaic;
import images.Pixelation;
import images.Sepia;
import images.Sharpen;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Sets up testing the controller. 
 * The images should still be looked at.
 * @author quaydragon
 *
 */
public class TestController {
  ImageClass testImage;
  Blur testBlur;
  CrossStitch testPattern;
  Dithering testDither;
  Greyscale testGrey;
  Mosaic testMosaic;
  Pixelation testPixelation;
  Sepia testSepia;
  Sharpen testSharpen;

  /**
   * Setting up the classes.
   * @throws Exception if an image does not exist
   */
  @Before
  public void setUp() throws Exception {
    
    testImage = new ImageClass("res/bestie.jpeg");
    
    //blur set up
    testBlur = new Blur(testImage);
    int[][][] transformedBlur = testBlur.transform();
    testImage.writeImage(transformedBlur, "test/testblur.jpeg");
    
    
    //cross stitch set up
    testPattern = new CrossStitch(testImage, "test/testPattern.txt");
    int[][][] newPatern = testPattern.transform();

    
    // dithering set up
    
    testDither = new Dithering(testImage, 2);
    int[][][] newDither = testDither.transform();
    testImage.writeImage(newDither, "test/testDither.jpeg");
    
    // greyscale set up
    
    testGrey = new Greyscale(testImage);
    int[][][] newGrey = testGrey.transform();
    testImage.writeImage(newGrey, "test/testGrey.jpeg");
    
    //mosaic setup
    testMosaic = new Mosaic(testImage, 1400);
    int[][][] newMosaic = testMosaic.transform();
    testImage.writeImage(newMosaic, "test/testMosaic.jpeg");
    //pixelation setup
    testPixelation = new Pixelation(testImage, 30);
    int[][][] newPixel = testPixelation.transform();
    testImage.writeImage(newPixel, "test/testPixel.jpeg");
    
    //sepia setup
    testSepia = new Sepia(testImage);
    int[][][] newSepia = testSepia.transform();
    testImage.writeImage(newSepia, "test/testSepia.jpeg");
    
    
    //sharpen
    testSharpen = new Sharpen(testImage);
    int[][][] newSharpen = testSharpen.transform();
    testImage.writeImage(newSharpen, "test/testSharpen.jpeg");
    
    
  }


  
  @Test
  public void test() {
    File blur = new File("test/testblur.jpeg");
    assertEquals(true, blur.exists() && !blur.isDirectory());
    
    
    File pattern = new File("test/testPattern.txt");
    
    assertEquals(true, pattern.exists() && !pattern.isDirectory());
    
    
    
    File dither = new File("test/testDither.jpeg");
    assertEquals(true, dither.exists() && !dither.isDirectory());
    
    
    
    File grey = new File("test/testGrey.jpeg");
    assertEquals(true, grey.exists() && !grey.isDirectory());
    
    
    File mosaic = new File("test/testMosaic.jpeg");
    assertEquals(true, mosaic.exists() && !mosaic.isDirectory());

    
    File pixel = new File("test/testPixel.jpeg");
    assertEquals(true, pixel.exists() && !pixel.isDirectory());

    
    File sepia = new File("test/testSepia.jpeg");
    assertEquals(true, sepia.exists() && !sepia.isDirectory());
    
    
    File sharpen = new File("test/testSharpen.jpeg");
    assertEquals(true, sharpen.exists() && !sharpen.isDirectory());
    
    
    
  }
  
  
  //test image sizes
  
  @Test
  public void testSizes() throws IOException {
    ImageClass blur = new ImageClass("test/testblur.jpeg");
    assertEquals(testImage.getHeight(), blur.getHeight());
    assertEquals(testImage.getWidth(), blur.getWidth());
    
    
    
    ImageClass dither = new ImageClass("test/testDither.jpeg");
    assertEquals(testImage.getHeight(), dither.getHeight());
    assertEquals(testImage.getWidth(), dither.getWidth());
    
    
    
    ImageClass grey = new ImageClass("test/testGrey.jpeg");
    assertEquals(testImage.getHeight(), grey.getHeight());
    assertEquals(testImage.getWidth(), grey.getWidth());
    
    
    
    
    ImageClass mosaic = new ImageClass("test/testMosaic.jpeg");
    assertEquals(testImage.getHeight(), mosaic.getHeight());
    assertEquals(testImage.getWidth(), mosaic.getWidth());
    
    
    
    ImageClass pixel = new ImageClass("test/testPixel.jpeg");
    assertEquals(testImage.getHeight(), mosaic.getHeight());
    assertEquals(testImage.getWidth(), mosaic.getWidth());
    
    
    
    
    ImageClass sepia = new ImageClass("test/testSepia.jpeg");
    assertEquals(testImage.getHeight(), sepia.getHeight());
    assertEquals(testImage.getWidth(), sepia.getWidth());
    
    
    
    ImageClass sharpen = new ImageClass("test/testSharpen.jpeg");
    assertEquals(testImage.getHeight(), sharpen.getHeight());
    assertEquals(testImage.getWidth(), sharpen.getWidth());
    
  }
  
  

  
  

}
