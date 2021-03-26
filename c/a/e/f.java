package c.a.e;

import java.awt.Point;

public interface f {
  int getTileWidth();
  
  int getTileHeight();
  
  int getNomTileWidth();
  
  int getNomTileHeight();
  
  int getImgWidth();
  
  int getImgHeight();
  
  int getNumComps();
  
  int getCompSubsX(int paramInt);
  
  int getCompSubsY(int paramInt);
  
  int getTileCompWidth(int paramInt1, int paramInt2);
  
  int getTileCompHeight(int paramInt1, int paramInt2);
  
  int getCompImgWidth(int paramInt);
  
  int getCompImgHeight(int paramInt);
  
  int getNomRangeBits(int paramInt);
  
  void setTile(int paramInt1, int paramInt2);
  
  void nextTile();
  
  Point getTile(Point paramPoint);
  
  int getTileIdx();
  
  int getTilePartULX();
  
  int getTilePartULY();
  
  int getCompULX(int paramInt);
  
  int getCompULY(int paramInt);
  
  int getImgULX();
  
  int getImgULY();
  
  Point getNumTiles(Point paramPoint);
  
  int getNumTiles();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */