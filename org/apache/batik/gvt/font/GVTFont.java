package org.apache.batik.gvt.font;

import java.awt.font.FontRenderContext;
import java.text.CharacterIterator;

public interface GVTFont {
  boolean canDisplay(char paramChar);
  
  int canDisplayUpTo(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  int canDisplayUpTo(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2);
  
  int canDisplayUpTo(String paramString);
  
  GVTGlyphVector createGlyphVector(FontRenderContext paramFontRenderContext, char[] paramArrayOfchar);
  
  GVTGlyphVector createGlyphVector(FontRenderContext paramFontRenderContext, CharacterIterator paramCharacterIterator);
  
  GVTGlyphVector createGlyphVector(FontRenderContext paramFontRenderContext, int[] paramArrayOfint, CharacterIterator paramCharacterIterator);
  
  GVTGlyphVector createGlyphVector(FontRenderContext paramFontRenderContext, String paramString);
  
  GVTFont deriveFont(float paramFloat);
  
  String getFamilyName();
  
  GVTLineMetrics getLineMetrics(char[] paramArrayOfchar, int paramInt1, int paramInt2, FontRenderContext paramFontRenderContext);
  
  GVTLineMetrics getLineMetrics(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, FontRenderContext paramFontRenderContext);
  
  GVTLineMetrics getLineMetrics(String paramString, FontRenderContext paramFontRenderContext);
  
  GVTLineMetrics getLineMetrics(String paramString, int paramInt1, int paramInt2, FontRenderContext paramFontRenderContext);
  
  float getSize();
  
  float getVKern(int paramInt1, int paramInt2);
  
  float getHKern(int paramInt1, int paramInt2);
  
  String toString();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */