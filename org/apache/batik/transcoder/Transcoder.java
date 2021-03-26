package org.apache.batik.transcoder;

import java.util.Map;

public interface Transcoder {
  void transcode(TranscoderInput paramTranscoderInput, TranscoderOutput paramTranscoderOutput) throws TranscoderException;
  
  TranscodingHints getTranscodingHints();
  
  void addTranscodingHint(TranscodingHints.Key paramKey, Object paramObject);
  
  void removeTranscodingHint(TranscodingHints.Key paramKey);
  
  void setTranscodingHints(Map paramMap);
  
  void setTranscodingHints(TranscodingHints paramTranscodingHints);
  
  void setErrorHandler(ErrorHandler paramErrorHandler);
  
  ErrorHandler getErrorHandler();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/Transcoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */