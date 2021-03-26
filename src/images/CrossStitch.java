package images;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;


public class CrossStitch extends ImageTransformerClass {
  Image image;
  String filename;
  List<ArrayList<String>> dmcColors;
  List<ArrayList<String>> legend;
  int[][][] transformedPixel;
  int[][][] dmcColored;
  BufferedWriter myWriter;

  public CrossStitch(Image image) throws IOException {
    super(image);
    // TODO Auto-generated constructor stub
    this.image = image;
    this.filename = null;
    dmcColors = new ArrayList<ArrayList<String>>();
    legend = new ArrayList<ArrayList<String>>();
    transformedPixel = null;
    myWriter = new BufferedWriter(new FileWriter("res/crossStitch.txt", true));
    //TODO: make this better
    myWriter.write(String.valueOf(image.getWidth()));
    myWriter.write(" x ");
    myWriter.write(String.valueOf(image.getHeight()));
  }
  
  // use the prep function to get the pixelated image limiting the colors
  
  // then find the closest color with the helper function 
  
  // then assign the unicode 
  
  
  // then there will be an output file 
  
  
//TODO: Write a better javadoc  
  /**
   * Assigning the seeds
   */
  @Override
  public void prepFunction() {
    
    //this will pixelate the image
    
    try {
      this.loadFile();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    System.out.println("Finished with load");
    
      try {
        Pixelation pixel = new Pixelation(image, 30);
        int[][][] transformedPixel = pixel.transform(this.image);
        this.transformedPixel = transformedPixel;
        // actually we need to update the current imager
        
        
        //TODO: should only have to edit the transformed pixel
        
        System.out.println("FInished with transform");

//        String[] names = this.pixelated.getName().split(".");
//        String returnName = "res/pixelatedCross." + names[1];
//        
//        this.pixelated.writeImage(transformedPixel, returnName);
//        this.filename = returnName;
        
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
      
      
      
    }
  

  @Override
  public int[][][] transform(Image image) throws IOException {
    this.prepFunction();
    int[][][] newImage = null;
    try {
      
      //TODO: or you could just go around width to height 
      //write to a new file with the new color at first
      for (int j = 0; j < image.getWidth(); j++) {
        for (int i = 0; i < image.getHeight(); i++) {

          
          newImage = this.specificTransform(this.transformedPixel, i, j);
          
        }
      }
    } catch (IOException e) {
      // Could not edit the image in the program
      newImage = img;
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
//    image.writeImage(newImage);
    this.dmcColored = newImage;
    return newImage;
  }
  
  
  @Override
  public int[][][] specificTransform(int[][][] newImg, int i, int j) {
    // assign to the new color 
    
    
    
    //find the closest dmc
    
    int redPixelImage = newImg[i][j][0];
    int greenPixelImage = newImg[i][j][1];
    int bluePixelImage = newImg[i][j][2];
    
    int distance = 100000;
    String symbol = null;
    String legendValue = null;
    
    
    for (ArrayList<String> dmc: this.dmcColors) {
      
//      System.out.println(Integer.valueOf(String.valueOf(dmc.get(0))));
      System.out.println(Double.valueOf(dmc.get(0).trim()));
      
      
      int rHat  = (int) (Double.valueOf(dmc.get(0)) + redPixelImage) / 2;
      
      int redDifference = (int) Math.abs(Double.valueOf(dmc.get(0)) - redPixelImage);
      int greenDifference = (int) Math.abs(Double.valueOf(dmc.get(1)) - greenPixelImage);
      int blueDifference = (int) Math.abs(Double.valueOf(dmc.get(2)) - bluePixelImage);
      
      int dis = (int) Math.sqrt((2 + (rHat / 256))
          * (redDifference * redDifference) 
          + 4 * (greenDifference * greenDifference)
          + (2 + ((255 - rHat) / 256))
          * (blueDifference * blueDifference));
      
      
      System.out.println("Equation worked");
      if (dis < distance) {
        distance = dis;
        symbol = dmc.get(3);
        legendValue = dmc.get(4);
        int index = this.dmcColors.indexOf(dmc);
        
      }
      
      
      
      
    }
    
    
    
    try {
      if (i== 0) {
        this.myWriter.newLine();
      }
      //TODO: this is where we will find the symbol

//      String symbol =  "\\u" + c;
//      this.myWriter.write(" ");
      
      ArrayList<String> legendRow = new ArrayList<String>();
      
      legendRow.add(symbol);
      legendRow.add(legendValue);
      this.legend.add(legendRow);
      
      System.out.println(symbol);
      this.myWriter.write(symbol);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    return newImg;
  }
  
  
  private void loadFile() throws IOException {
    
    File file = new File("res/bigData.txt");
    
    
    
    
    
    
    BufferedReader br = new BufferedReader(new FileReader(file));
    
    
    String st;
    while ((st = br.readLine()) != null) {

      String[] strArray;
      strArray = st.split(" ");
      
      
      ArrayList<String> row = new ArrayList<String>();
      
      // should add unicode here based on some number
      
     String r = strArray[1];
     String g = strArray[2];
     String b = strArray[3];
     String dmc = strArray[4];
     row.add(r);
     row.add(g);
     row.add(b);
     
     
     int tempInt = Integer.valueOf(strArray[0]);
     
     
     char unicode = (char) (tempInt + 200);
     
     
     
     String s = Character.toString((char)unicode);
     
     System.out.println(s);
     
     row.add(s);
     row.add(dmc);
     
//     row.add(unicode);
     
     dmcColors.add(row);
      

    
  }
  
  }
  

}
