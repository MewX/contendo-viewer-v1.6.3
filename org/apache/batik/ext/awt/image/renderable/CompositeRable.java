package org.apache.batik.ext.awt.image.renderable;

import java.util.List;
import org.apache.batik.ext.awt.image.CompositeRule;

public interface CompositeRable extends FilterColorInterpolation {
  void setSources(List paramList);
  
  void setCompositeRule(CompositeRule paramCompositeRule);
  
  CompositeRule getCompositeRule();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/CompositeRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */