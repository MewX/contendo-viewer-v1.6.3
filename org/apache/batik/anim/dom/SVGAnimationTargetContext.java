package org.apache.batik.anim.dom;

import org.apache.batik.dom.svg.SVGContext;

public interface SVGAnimationTargetContext extends SVGContext {
  void addTargetListener(String paramString, AnimationTargetListener paramAnimationTargetListener);
  
  void removeTargetListener(String paramString, AnimationTargetListener paramAnimationTargetListener);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGAnimationTargetContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */