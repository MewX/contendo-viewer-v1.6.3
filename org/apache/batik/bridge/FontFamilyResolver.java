package org.apache.batik.bridge;

import java.io.InputStream;
import org.apache.batik.gvt.font.GVTFontFamily;

public interface FontFamilyResolver {
  GVTFontFamily resolve(String paramString);
  
  GVTFontFamily resolve(String paramString, FontFace paramFontFace);
  
  GVTFontFamily loadFont(InputStream paramInputStream, FontFace paramFontFace) throws Exception;
  
  GVTFontFamily getDefault();
  
  GVTFontFamily getFamilyThatCanDisplay(char paramChar);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FontFamilyResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */