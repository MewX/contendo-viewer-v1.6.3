package org.apache.batik.bridge;

import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.text.AttributedCharacterIterator;

public interface TextLayoutFactory {
  TextSpanLayout createTextLayout(AttributedCharacterIterator paramAttributedCharacterIterator, int[] paramArrayOfint, Point2D paramPoint2D, FontRenderContext paramFontRenderContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextLayoutFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */