package org.apache.batik.swing.svg;

import org.apache.batik.bridge.ExternalResourceSecurity;
import org.apache.batik.bridge.ScriptSecurity;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.Element;

public interface SVGUserAgent {
  void displayError(String paramString);
  
  void displayError(Exception paramException);
  
  void displayMessage(String paramString);
  
  void showAlert(String paramString);
  
  String showPrompt(String paramString);
  
  String showPrompt(String paramString1, String paramString2);
  
  boolean showConfirm(String paramString);
  
  float getPixelUnitToMillimeter();
  
  float getPixelToMM();
  
  String getDefaultFontFamily();
  
  float getMediumFontSize();
  
  float getLighterFontWeight(float paramFloat);
  
  float getBolderFontWeight(float paramFloat);
  
  String getLanguages();
  
  String getUserStyleSheetURI();
  
  String getXMLParserClassName();
  
  boolean isXMLParserValidating();
  
  String getMedia();
  
  String getAlternateStyleSheet();
  
  void openLink(String paramString, boolean paramBoolean);
  
  boolean supportExtension(String paramString);
  
  void handleElement(Element paramElement, Object paramObject);
  
  ScriptSecurity getScriptSecurity(String paramString, ParsedURL paramParsedURL1, ParsedURL paramParsedURL2);
  
  void checkLoadScript(String paramString, ParsedURL paramParsedURL1, ParsedURL paramParsedURL2) throws SecurityException;
  
  ExternalResourceSecurity getExternalResourceSecurity(ParsedURL paramParsedURL1, ParsedURL paramParsedURL2);
  
  void checkLoadExternalResource(ParsedURL paramParsedURL1, ParsedURL paramParsedURL2) throws SecurityException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGUserAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */