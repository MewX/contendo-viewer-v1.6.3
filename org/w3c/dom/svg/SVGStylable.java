package org.w3c.dom.svg;

import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;

public interface SVGStylable {
  SVGAnimatedString getClassName();
  
  CSSStyleDeclaration getStyle();
  
  CSSValue getPresentationAttribute(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGStylable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */