package org.apache.batik.parser;

import java.awt.Shape;

public interface ShapeProducer {
  Shape getShape();
  
  void setWindingRule(int paramInt);
  
  int getWindingRule();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/ShapeProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */