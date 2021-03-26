package org.apache.batik.svggen;

import java.util.List;
import org.apache.batik.ext.awt.g2d.GraphicContext;

public interface SVGConverter extends SVGSyntax {
  SVGDescriptor toSVG(GraphicContext paramGraphicContext);
  
  List getDefinitionSet();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */