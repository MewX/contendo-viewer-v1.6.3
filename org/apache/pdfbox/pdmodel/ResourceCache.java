package org.apache.pdfbox.pdmodel;

import java.io.IOException;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

public interface ResourceCache {
  PDFont getFont(COSObject paramCOSObject) throws IOException;
  
  PDColorSpace getColorSpace(COSObject paramCOSObject) throws IOException;
  
  PDExtendedGraphicsState getExtGState(COSObject paramCOSObject);
  
  PDShading getShading(COSObject paramCOSObject) throws IOException;
  
  PDAbstractPattern getPattern(COSObject paramCOSObject) throws IOException;
  
  PDPropertyList getProperties(COSObject paramCOSObject);
  
  PDXObject getXObject(COSObject paramCOSObject) throws IOException;
  
  void put(COSObject paramCOSObject, PDFont paramPDFont) throws IOException;
  
  void put(COSObject paramCOSObject, PDColorSpace paramPDColorSpace) throws IOException;
  
  void put(COSObject paramCOSObject, PDExtendedGraphicsState paramPDExtendedGraphicsState);
  
  void put(COSObject paramCOSObject, PDShading paramPDShading) throws IOException;
  
  void put(COSObject paramCOSObject, PDAbstractPattern paramPDAbstractPattern) throws IOException;
  
  void put(COSObject paramCOSObject, PDPropertyList paramPDPropertyList);
  
  void put(COSObject paramCOSObject, PDXObject paramPDXObject) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/ResourceCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */