package org.apache.commons.io.input;

public interface TailerListener {
  void init(Tailer paramTailer);
  
  void fileNotFound();
  
  void fileRotated();
  
  void handle(String paramString);
  
  void handle(Exception paramException);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/TailerListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */