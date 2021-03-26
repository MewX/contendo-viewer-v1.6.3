package org.apache.batik.css.engine;

import org.apache.batik.css.engine.value.Value;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;

public interface CSSContext {
  Value getSystemColor(String paramString);
  
  Value getDefaultFontFamily();
  
  float getLighterFontWeight(float paramFloat);
  
  float getBolderFontWeight(float paramFloat);
  
  float getPixelUnitToMillimeter();
  
  float getPixelToMillimeter();
  
  float getMediumFontSize();
  
  float getBlockWidth(Element paramElement);
  
  float getBlockHeight(Element paramElement);
  
  void checkLoadExternalResource(ParsedURL paramParsedURL1, ParsedURL paramParsedURL2) throws SecurityException;
  
  boolean isDynamic();
  
  boolean isInteractive();
  
  CSSEngine getCSSEngineForElement(Element paramElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */