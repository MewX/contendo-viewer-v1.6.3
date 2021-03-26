package org.apache.batik.parser;

public interface FragmentIdentifierHandler extends PreserveAspectRatioHandler, TransformListHandler {
  void startFragmentIdentifier() throws ParseException;
  
  void idReference(String paramString) throws ParseException;
  
  void viewBox(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) throws ParseException;
  
  void startViewTarget() throws ParseException;
  
  void viewTarget(String paramString) throws ParseException;
  
  void endViewTarget() throws ParseException;
  
  void zoomAndPan(boolean paramBoolean);
  
  void endFragmentIdentifier() throws ParseException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/FragmentIdentifierHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */