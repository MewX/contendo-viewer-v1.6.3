package org.apache.batik.apps.svgbrowser;

import javax.swing.Action;

public interface Application {
  JSVGViewerFrame createAndShowJSVGViewerFrame();
  
  void closeJSVGViewerFrame(JSVGViewerFrame paramJSVGViewerFrame);
  
  Action createExitAction(JSVGViewerFrame paramJSVGViewerFrame);
  
  void openLink(String paramString);
  
  String getXMLParserClassName();
  
  boolean isXMLParserValidating();
  
  void showPreferenceDialog(JSVGViewerFrame paramJSVGViewerFrame);
  
  String getLanguages();
  
  String getUserStyleSheetURI();
  
  String getDefaultFontFamily();
  
  String getMedia();
  
  boolean isSelectionOverlayXORMode();
  
  boolean canLoadScriptType(String paramString);
  
  int getAllowedScriptOrigin();
  
  int getAllowedExternalResourceOrigin();
  
  void addVisitedURI(String paramString);
  
  String[] getVisitedURIs();
  
  String getUISpecialization();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/Application.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */