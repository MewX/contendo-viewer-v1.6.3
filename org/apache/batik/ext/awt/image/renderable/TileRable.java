package org.apache.batik.ext.awt.image.renderable;

import java.awt.geom.Rectangle2D;

public interface TileRable extends FilterColorInterpolation {
  Rectangle2D getTileRegion();
  
  void setTileRegion(Rectangle2D paramRectangle2D);
  
  Rectangle2D getTiledRegion();
  
  void setTiledRegion(Rectangle2D paramRectangle2D);
  
  boolean isOverflow();
  
  void setOverflow(boolean paramBoolean);
  
  void setSource(Filter paramFilter);
  
  Filter getSource();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/TileRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */