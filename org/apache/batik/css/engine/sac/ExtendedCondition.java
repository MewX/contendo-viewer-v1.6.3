package org.apache.batik.css.engine.sac;

import java.util.Set;
import org.w3c.css.sac.Condition;
import org.w3c.dom.Element;

public interface ExtendedCondition extends Condition {
  boolean match(Element paramElement, String paramString);
  
  int getSpecificity();
  
  void fillAttributeSet(Set paramSet);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/ExtendedCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */