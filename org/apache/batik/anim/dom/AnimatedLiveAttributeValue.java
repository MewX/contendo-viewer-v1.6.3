package org.apache.batik.anim.dom;

import org.apache.batik.anim.values.AnimatableValue;
import org.apache.batik.dom.svg.LiveAttributeValue;

public interface AnimatedLiveAttributeValue extends LiveAttributeValue {
  String getNamespaceURI();
  
  String getLocalName();
  
  AnimatableValue getUnderlyingValue(AnimationTarget paramAnimationTarget);
  
  void addAnimatedAttributeListener(AnimatedAttributeListener paramAnimatedAttributeListener);
  
  void removeAnimatedAttributeListener(AnimatedAttributeListener paramAnimatedAttributeListener);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AnimatedLiveAttributeValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */