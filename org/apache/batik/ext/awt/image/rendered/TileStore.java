package org.apache.batik.ext.awt.image.rendered;

import java.awt.image.Raster;

public interface TileStore {
  void setTile(int paramInt1, int paramInt2, Raster paramRaster);
  
  Raster getTile(int paramInt1, int paramInt2);
  
  Raster getTileNoCompute(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */