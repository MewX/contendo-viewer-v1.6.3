package org.apache.http.nio;

@Deprecated
public interface NHttpServerIOTarget extends NHttpServerConnection {
  void consumeInput(NHttpServiceHandler paramNHttpServiceHandler);
  
  void produceOutput(NHttpServiceHandler paramNHttpServiceHandler);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpServerIOTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */