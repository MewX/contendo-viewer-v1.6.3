package org.apache.xml.serializer;

import javax.xml.transform.Transformer;
import org.w3c.dom.Node;

public interface TransformStateSetter {
  void setCurrentNode(Node paramNode);
  
  void resetState(Transformer paramTransformer);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/TransformStateSetter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */