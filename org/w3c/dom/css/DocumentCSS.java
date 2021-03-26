package org.w3c.dom.css;

import org.w3c.dom.Element;
import org.w3c.dom.stylesheets.DocumentStyle;

public interface DocumentCSS extends DocumentStyle {
  CSSStyleDeclaration getOverrideStyle(Element paramElement, String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/DocumentCSS.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */