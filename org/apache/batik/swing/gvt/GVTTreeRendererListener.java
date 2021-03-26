package org.apache.batik.swing.gvt;

public interface GVTTreeRendererListener {
  void gvtRenderingPrepare(GVTTreeRendererEvent paramGVTTreeRendererEvent);
  
  void gvtRenderingStarted(GVTTreeRendererEvent paramGVTTreeRendererEvent);
  
  void gvtRenderingCompleted(GVTTreeRendererEvent paramGVTTreeRendererEvent);
  
  void gvtRenderingCancelled(GVTTreeRendererEvent paramGVTTreeRendererEvent);
  
  void gvtRenderingFailed(GVTTreeRendererEvent paramGVTTreeRendererEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/GVTTreeRendererListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */