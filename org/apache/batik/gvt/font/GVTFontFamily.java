package org.apache.batik.gvt.font;

import java.text.AttributedCharacterIterator;
import java.util.Map;

public interface GVTFontFamily {
  String getFamilyName();
  
  GVTFontFace getFontFace();
  
  GVTFont deriveFont(float paramFloat, AttributedCharacterIterator paramAttributedCharacterIterator);
  
  GVTFont deriveFont(float paramFloat, Map paramMap);
  
  boolean isComplex();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTFontFamily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */