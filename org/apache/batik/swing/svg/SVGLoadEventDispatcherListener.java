package org.apache.batik.swing.svg;

public interface SVGLoadEventDispatcherListener {
  void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent paramSVGLoadEventDispatcherEvent);
  
  void svgLoadEventDispatchCompleted(SVGLoadEventDispatcherEvent paramSVGLoadEventDispatcherEvent);
  
  void svgLoadEventDispatchCancelled(SVGLoadEventDispatcherEvent paramSVGLoadEventDispatcherEvent);
  
  void svgLoadEventDispatchFailed(SVGLoadEventDispatcherEvent paramSVGLoadEventDispatcherEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGLoadEventDispatcherListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */