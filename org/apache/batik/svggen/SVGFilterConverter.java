package org.apache.batik.svggen;

import java.awt.Rectangle;
import java.awt.image.BufferedImageOp;
import java.util.List;

public interface SVGFilterConverter extends SVGSyntax {
  SVGFilterDescriptor toSVG(BufferedImageOp paramBufferedImageOp, Rectangle paramRectangle);
  
  List getDefinitionSet();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGFilterConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */