package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;

public interface PDAppearanceHandler {
  void generateAppearanceStreams();
  
  void generateNormalAppearance();
  
  void generateRolloverAppearance();
  
  void generateDownAppearance();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */