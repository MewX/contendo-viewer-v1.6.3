package org.apache.batik.transcoder;

public interface ErrorHandler {
  void error(TranscoderException paramTranscoderException) throws TranscoderException;
  
  void fatalError(TranscoderException paramTranscoderException) throws TranscoderException;
  
  void warning(TranscoderException paramTranscoderException) throws TranscoderException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/ErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */