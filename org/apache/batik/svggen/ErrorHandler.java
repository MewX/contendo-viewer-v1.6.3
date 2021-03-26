package org.apache.batik.svggen;

public interface ErrorHandler {
  void handleError(SVGGraphics2DIOException paramSVGGraphics2DIOException) throws SVGGraphics2DIOException;
  
  void handleError(SVGGraphics2DRuntimeException paramSVGGraphics2DRuntimeException) throws SVGGraphics2DRuntimeException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */