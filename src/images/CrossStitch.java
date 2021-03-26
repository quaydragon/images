package images;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the CrossStitch class. This class takes in an outputs a .txt file 
 * with the image's cross stitch.
 * @author quaydragon
 *
 */
public class CrossStitch extends ImageTransformerClass {
  Image image;
  String filename;
  List<ArrayList<String>> dmcColors;
  List<ArrayList<String>> legend;
  int[][][] transformedPixel;
  int[][][] dmcColored;
  BufferedWriter myWriter;

  /**
   * Constructs the CrossStitch class using a controller. 
   * @param image the image to be transformed into a cross stitch design
   * @throws IOException if the image cannot be loaded
   */
  public CrossStitch(Image image) throws IOException {
    super(image);
    this.image = image;
    this.filename = null;
    dmcColors = new ArrayList<ArrayList<String>>();
    legend = new ArrayList<ArrayList<String>>();
    transformedPixel = null;
    myWriter = new BufferedWriter(new FileWriter("res/crossStitch.txt", true));
    myWriter.write(String.valueOf(image.getWidth()));
    myWriter.write(" x ");
    myWriter.write(String.valueOf(image.getHeight()));
  }
  
  
  

  /**
   * The CrossStitch prep function pixelates the image in order to create
   * a better base for the cross stitch pattern. 
   */
  @Override
  public void prepFunction() throws IOException {
    
    this.loadFile();

    Pixelation pixel = new Pixelation(image, 30);
    int[][][] transformedPixel = pixel.transform();
    this.transformedPixel = transformedPixel;
            
      
  }
  

  /**
   * The cross stitch transform function must have a different output than the main 
   * transform functions used by the other sub classes.
   * The cross stitch transform saves the cross stitch to a text file.
   * @throws IOException if the writer cannot be opened
   */
  @Override
  public int[][][] transform() throws IOException {
    this.prepFunction();
    int[][][] newImage = null;


    for (int j = 0; j < width; j++) {
      for (int i = 0; i < height; i++) {

        
        newImage = this.specificTransform(this.transformedPixel, i, j);
        
      }
    }

    
    this.myWriter.newLine();
    this.myWriter.newLine();
    
    this.myWriter.write("LEGEND");
    
    for (ArrayList<String> legendRow : this.legend) {
      this.myWriter.newLine();
      this.myWriter.write(legendRow.get(0));
      this.myWriter.write(": ");
      this.myWriter.write(legendRow.get(1));
    }
    
    this.myWriter.close();
    this.dmcColored = newImage;
    //TODO: Is there any reason we should be returning the new image?
    return newImage;
  }
  
  
  /**
   * The cross stitch specific transform function calculates the distance
   * between the dmc colors and the colors in the image. 
   * @exception IOException if writer cannot be opened
   */
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) throws IOException {
    int redPixelImage = newImg[i][j][0];
    int greenPixelImage = newImg[i][j][1];
    int bluePixelImage = newImg[i][j][2];
    
    int distance = 100000;
    String symbol = null;
    String legendValue = null;
    
    
    for (ArrayList<String> dmc: this.dmcColors) {
      
      int redAverage  = (int) (Double.valueOf(dmc.get(0)) + redPixelImage) / 2;
      
      int redDifference = (int) Math.abs(Double.valueOf(dmc.get(0)) - redPixelImage);
      int greenDifference = (int) Math.abs(Double.valueOf(dmc.get(1)) - greenPixelImage);
      int blueDifference = (int) Math.abs(Double.valueOf(dmc.get(2)) - bluePixelImage);
      
      int dis = (int) Math.sqrt((2 + (redAverage / 256))
          * (redDifference * redDifference) 
          + 4 * (greenDifference * greenDifference)
          + (2 + ((255 - redAverage) / 256))
          * (blueDifference * blueDifference));
      if (dis < distance) {
        distance = dis;
        symbol = dmc.get(3);
        legendValue = dmc.get(4);        
      }
      
      
      
      
    }
       
    
    
    if (i == 0) {
      this.myWriter.newLine();
    }
    ArrayList<String> legendRow = new ArrayList<String>();
    
    legendRow.add(symbol);
    legendRow.add(legendValue);
    this.legend.add(legendRow);
    
    this.myWriter.write(symbol);

    
    
    return newImg;
  }
  
  /**
   * This private function loads the dmc color txt file and saves it to the class. 
   * @throws IOException if the file is unable to be read.
   */
  private void loadFile() throws IOException {

    
    File file = new File("res/dmc.txt");
    BufferedReader br = new BufferedReader(new FileReader(file)); 
    String st;
    while ((st = br.readLine()) != null) {

      String[] strArray;
      strArray = st.split(" ");
      ArrayList<String> row = new ArrayList<String>();
      String r = strArray[1];
      String g = strArray[2];
      String b = strArray[3];
      String dmc = strArray[4];
      row.add(r);
      row.add(g);
      row.add(b);
      int tempInt = Integer.valueOf(strArray[0]);
      char unicode = (char) (tempInt + 200);
      String s = Character.toString((char) unicode);

      row.add(s);
      row.add(dmc);
      dmcColors.add(row);
      

    
    }
  
  }
  

}
