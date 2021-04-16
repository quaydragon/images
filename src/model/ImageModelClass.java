package model;

import images.Blur;
import images.CrossStitch;
import images.Dithering;
import images.Greyscale;
import images.Image;
import images.ImageClass;
import images.Mosaic;
import images.Pixelation;
import images.Sepia;
import images.Sharpen;
import java.io.File;
import java.io.IOException;




/**
 * The model for the ImageTransformationClass. 
 * @author quaydragon
 *
 */
public class ImageModelClass implements ImageModel {
  Image loadedImage;
  int[][][] currentTransform;
  String filename;
  
  

  @Override
  public boolean checkLoaded() {
    if (loadedImage == null) {
      return false;
    }
    return true;
  }
  
  // Checks if file exists
  private boolean makeSureFileExists(String filename) {
    File f = new File(filename);
    if (f.exists() && !f.isDirectory()) { 
      return true;

    }
    return false;
  }
  
  
  @Override
  public void replaceStitch(String oldColor, String newColor) throws IOException {
    if (oldColor == null || newColor == null) {
      // Only returning not to break the program
      return;
    }
    CrossStitch newPattern = new CrossStitch(this.loadedImage, 
        "res/temp.txt", 
        oldColor,
        newColor);
    newPattern.transform();
  }
  

  @Override
  public Image load(String filename) {
    if (!makeSureFileExists(filename)) {
      return null;
    }
    
    Image image = new ImageClass(filename);
    
    this.loadedImage = image;
    this.filename = filename;
    return image;
  }
  
  @Override
  public void save() throws IOException {
    this.save(this.filename);
  }

  @Override
  public void save(String newFile) throws IOException {
    loadedImage.writeImage(currentTransform, newFile);
    
  }

  //TODO: fix the temp file name 
  
  
  @Override
  public int[][][] blur() throws IOException {
    Blur newBlur = new Blur(this.loadedImage);
    int[][][] transformedBlur = newBlur.transform();
    System.out.println("transformed");
    currentTransform = transformedBlur;
    this.save("res/temp.jpeg");
    
    return transformedBlur;
  }

  @Override
  public void pattern() throws IOException {
    
    CrossStitch newPattern = new CrossStitch(this.loadedImage, "res/temp.txt");
    newPattern.transform();
    
    
  }

  @Override
  public int[][][] dither(int maxColors) throws IllegalArgumentException, IOException {
    Dithering newDither = new Dithering(this.loadedImage, maxColors);
    currentTransform =  newDither.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

  @Override
  public int[][][] grey() throws IOException {
    Greyscale newGrey = new Greyscale(this.loadedImage);
    currentTransform = newGrey.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

  @Override
  public int[][][] mosaic(int seed) throws IOException {
    Mosaic newMosaic = new Mosaic(this.loadedImage, seed);
    currentTransform = newMosaic.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

  @Override
  public int[][][] pixelation(int pixelsAcross) throws IOException {
    Pixelation newPixel = new Pixelation(this.loadedImage, pixelsAcross);
    currentTransform = newPixel.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

  @Override
  public int[][][] sepia() throws IOException {
    Sepia newSepia = new Sepia(this.loadedImage);
    currentTransform = newSepia.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

  @Override
  public int[][][] sharpen() throws IOException {
    Sharpen newSharpen = new Sharpen(this.loadedImage);
    currentTransform = newSharpen.transform();
    this.save("res/temp.jpeg");
    return currentTransform;
  }

}
