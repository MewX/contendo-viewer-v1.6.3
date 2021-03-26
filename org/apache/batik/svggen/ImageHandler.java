package org.apache.batik.svggen;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import org.w3c.dom.Element;

public interface ImageHandler extends SVGSyntax {
  void handleImage(Image paramImage, Element paramElement, SVGGeneratorContext paramSVGGeneratorContext);
  
  void handleImage(RenderedImage paramRenderedImage, Element paramElement, SVGGeneratorContext paramSVGGeneratorContext);
  
  void handleImage(RenderableImage paramRenderableImage, Element paramElement, SVGGeneratorContext paramSVGGeneratorContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ImageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */