package org.apache.batik.swing.svg;

public interface GVTTreeBuilderListener {
  void gvtBuildStarted(GVTTreeBuilderEvent paramGVTTreeBuilderEvent);
  
  void gvtBuildCompleted(GVTTreeBuilderEvent paramGVTTreeBuilderEvent);
  
  void gvtBuildCancelled(GVTTreeBuilderEvent paramGVTTreeBuilderEvent);
  
  void gvtBuildFailed(GVTTreeBuilderEvent paramGVTTreeBuilderEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/GVTTreeBuilderListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */