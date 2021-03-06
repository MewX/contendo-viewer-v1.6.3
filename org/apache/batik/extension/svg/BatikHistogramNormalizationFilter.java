package org.apache.batik.extension.svg;

import org.apache.batik.ext.awt.image.renderable.Filter;
import org.apache.batik.ext.awt.image.renderable.FilterColorInterpolation;

public interface BatikHistogramNormalizationFilter extends FilterColorInterpolation {
  Filter getSource();
  
  void setSource(Filter paramFilter);
  
  float getTrim();
  
  void setTrim(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikHistogramNormalizationFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */