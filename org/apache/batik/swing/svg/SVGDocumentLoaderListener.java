package org.apache.batik.swing.svg;

public interface SVGDocumentLoaderListener {
  void documentLoadingStarted(SVGDocumentLoaderEvent paramSVGDocumentLoaderEvent);
  
  void documentLoadingCompleted(SVGDocumentLoaderEvent paramSVGDocumentLoaderEvent);
  
  void documentLoadingCancelled(SVGDocumentLoaderEvent paramSVGDocumentLoaderEvent);
  
  void documentLoadingFailed(SVGDocumentLoaderEvent paramSVGDocumentLoaderEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGDocumentLoaderListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */