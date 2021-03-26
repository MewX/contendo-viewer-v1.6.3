package org.apache.batik.anim.dom;

import org.apache.batik.anim.values.AnimatableValue;
import org.w3c.dom.Element;

public interface AnimationTarget {
  public static final short PERCENTAGE_FONT_SIZE = 0;
  
  public static final short PERCENTAGE_VIEWPORT_WIDTH = 1;
  
  public static final short PERCENTAGE_VIEWPORT_HEIGHT = 2;
  
  public static final short PERCENTAGE_VIEWPORT_SIZE = 3;
  
  Element getElement();
  
  void updatePropertyValue(String paramString, AnimatableValue paramAnimatableValue);
  
  void updateAttributeValue(String paramString1, String paramString2, AnimatableValue paramAnimatableValue);
  
  void updateOtherValue(String paramString, AnimatableValue paramAnimatableValue);
  
  AnimatableValue getUnderlyingValue(String paramString1, String paramString2);
  
  short getPercentageInterpretation(String paramString1, String paramString2, boolean paramBoolean);
  
  boolean useLinearRGBColorInterpolation();
  
  float svgToUserSpace(float paramFloat, short paramShort1, short paramShort2);
  
  void addTargetListener(String paramString1, String paramString2, boolean paramBoolean, AnimationTargetListener paramAnimationTargetListener);
  
  void removeTargetListener(String paramString1, String paramString2, boolean paramBoolean, AnimationTargetListener paramAnimationTargetListener);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AnimationTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */