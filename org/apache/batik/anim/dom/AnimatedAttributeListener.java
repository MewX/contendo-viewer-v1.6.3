package org.apache.batik.anim.dom;

import org.w3c.dom.Element;

public interface AnimatedAttributeListener {
  void animatedAttributeChanged(Element paramElement, AnimatedLiveAttributeValue paramAnimatedLiveAttributeValue);
  
  void otherAnimationChanged(Element paramElement, String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AnimatedAttributeListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */