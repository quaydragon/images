package images;

import java.io.IOException;
import java.util.concurrent.Callable;

public interface ImageTransformer {
  
  public int[][][] specificTransform(int[][][] img, int i, int j);

  public void prepFunction();


}
