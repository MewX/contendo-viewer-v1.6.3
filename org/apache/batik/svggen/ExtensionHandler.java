package org.apache.batik.svggen;

import java.awt.Composite;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.image.BufferedImageOp;

public interface ExtensionHandler {
  SVGPaintDescriptor handlePaint(Paint paramPaint, SVGGeneratorContext paramSVGGeneratorContext);
  
  SVGCompositeDescriptor handleComposite(Composite paramComposite, SVGGeneratorContext paramSVGGeneratorContext);
  
  SVGFilterDescriptor handleFilter(BufferedImageOp paramBufferedImageOp, Rectangle paramRectangle, SVGGeneratorContext paramSVGGeneratorContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ExtensionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */