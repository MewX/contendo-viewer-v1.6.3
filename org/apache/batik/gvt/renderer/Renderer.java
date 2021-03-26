package org.apache.batik.gvt.renderer;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import org.apache.batik.ext.awt.geom.RectListManager;
import org.apache.batik.gvt.GraphicsNode;

public interface Renderer {
  void setTree(GraphicsNode paramGraphicsNode);
  
  GraphicsNode getTree();
  
  void repaint(Shape paramShape);
  
  void repaint(RectListManager paramRectListManager);
  
  void setTransform(AffineTransform paramAffineTransform);
  
  AffineTransform getTransform();
  
  boolean isDoubleBuffered();
  
  void setDoubleBuffered(boolean paramBoolean);
  
  void dispose();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/Renderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */