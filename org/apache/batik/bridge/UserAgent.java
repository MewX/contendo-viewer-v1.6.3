package org.apache.batik.bridge;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import org.apache.batik.gvt.event.EventDispatcher;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGAElement;
import org.w3c.dom.svg.SVGDocument;

public interface UserAgent {
  EventDispatcher getEventDispatcher();
  
  Dimension2D getViewportSize();
  
  void displayError(Exception paramException);
  
  void displayMessage(String paramString);
  
  void showAlert(String paramString);
  
  String showPrompt(String paramString);
  
  String showPrompt(String paramString1, String paramString2);
  
  boolean showConfirm(String paramString);
  
  float getPixelUnitToMillimeter();
  
  float getPixelToMM();
  
  float getMediumFontSize();
  
  float getLighterFontWeight(float paramFloat);
  
  float getBolderFontWeight(float paramFloat);
  
  String getDefaultFontFamily();
  
  String getLanguages();
  
  String getUserStyleSheetURI();
  
  void openLink(SVGAElement paramSVGAElement);
  
  void setSVGCursor(Cursor paramCursor);
  
  void setTextSelection(Mark paramMark1, Mark paramMark2);
  
  void deselectAll();
  
  String getXMLParserClassName();
  
  boolean isXMLParserValidating();
  
  AffineTransform getTransform();
  
  void setTransform(AffineTransform paramAffineTransform);
  
  String getMedia();
  
  String getAlternateStyleSheet();
  
  Point getClientAreaLocationOnScreen();
  
  boolean hasFeature(String paramString);
  
  boolean supportExtension(String paramString);
  
  void registerExtension(BridgeExtension paramBridgeExtension);
  
  void handleElement(Element paramElement, Object paramObject);
  
  ScriptSecurity getScriptSecurity(String paramString, ParsedURL paramParsedURL1, ParsedURL paramParsedURL2);
  
  void checkLoadScript(String paramString, ParsedURL paramParsedURL1, ParsedURL paramParsedURL2) throws SecurityException;
  
  ExternalResourceSecurity getExternalResourceSecurity(ParsedURL paramParsedURL1, ParsedURL paramParsedURL2);
  
  void checkLoadExternalResource(ParsedURL paramParsedURL1, ParsedURL paramParsedURL2) throws SecurityException;
  
  SVGDocument getBrokenLinkDocument(Element paramElement, String paramString1, String paramString2);
  
  void loadDocument(String paramString);
  
  FontFamilyResolver getFontFamilyResolver();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UserAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */