package org.apache.xerces.dom3.as;

import java.io.OutputStream;
import org.w3c.dom.ls.LSSerializer;

public interface DOMASWriter extends LSSerializer {
  void writeASModel(OutputStream paramOutputStream, ASModel paramASModel) throws Exception;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/DOMASWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */