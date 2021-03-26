package org.apache.pdfbox.pdmodel.font;

import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.TrueTypeFont;

public interface FontMapper {
  FontMapping<TrueTypeFont> getTrueTypeFont(String paramString, PDFontDescriptor paramPDFontDescriptor);
  
  FontMapping<FontBoxFont> getFontBoxFont(String paramString, PDFontDescriptor paramPDFontDescriptor);
  
  CIDFontMapping getCIDFont(String paramString, PDFontDescriptor paramPDFontDescriptor, PDCIDSystemInfo paramPDCIDSystemInfo);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */