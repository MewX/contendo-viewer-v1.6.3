package org.apache.xmlgraphics.image.loader;

import java.io.FileNotFoundException;
import javax.xml.transform.Source;

public interface ImageSessionContext {
  ImageContext getParentContext();
  
  float getTargetResolution();
  
  Source newSource(String paramString);
  
  Source getSource(String paramString);
  
  Source needSource(String paramString) throws FileNotFoundException;
  
  void returnSource(String paramString, Source paramSource);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageSessionContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */