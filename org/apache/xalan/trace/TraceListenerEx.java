package org.apache.xalan.trace;

import javax.xml.transform.TransformerException;

public interface TraceListenerEx extends TraceListener {
  void selectEnd(EndSelectionEvent paramEndSelectionEvent) throws TransformerException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/TraceListenerEx.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */