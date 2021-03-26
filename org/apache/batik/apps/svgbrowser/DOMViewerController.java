package org.apache.batik.apps.svgbrowser;

import org.apache.batik.swing.gvt.Overlay;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface DOMViewerController {
  void performUpdate(Runnable paramRunnable);
  
  ElementOverlayManager createSelectionManager();
  
  void removeSelectionOverlay(Overlay paramOverlay);
  
  Document getDocument();
  
  void selectNode(Node paramNode);
  
  boolean canEdit();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/DOMViewerController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */