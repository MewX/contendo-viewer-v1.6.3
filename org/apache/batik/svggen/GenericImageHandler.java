package org.apache.batik.svggen;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import org.w3c.dom.Element;

public interface GenericImageHandler {
  void setDOMTreeManager(DOMTreeManager paramDOMTreeManager);
  
  Element createElement(SVGGeneratorContext paramSVGGeneratorContext);
  
  AffineTransform handleImage(Image paramImage, Element paramElement, int paramInt1, int paramInt2, int paramInt3, int paramInt4, SVGGeneratorContext paramSVGGeneratorContext);
  
  AffineTransform handleImage(RenderedImage paramRenderedImage, Element paramElement, int paramInt1, int paramInt2, int paramInt3, int paramInt4, SVGGeneratorContext paramSVGGeneratorContext);
  
  AffineTransform handleImage(RenderableImage paramRenderableImage, Element paramElement, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, SVGGeneratorContext paramSVGGeneratorContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/GenericImageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */