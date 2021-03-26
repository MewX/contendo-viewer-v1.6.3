package org.apache.xalan.trace;

import java.util.EventListener;
import javax.xml.transform.TransformerException;

public interface TraceListener extends EventListener {
  void trace(TracerEvent paramTracerEvent);
  
  void selected(SelectionEvent paramSelectionEvent) throws TransformerException;
  
  void generated(GenerateEvent paramGenerateEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/TraceListener.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */