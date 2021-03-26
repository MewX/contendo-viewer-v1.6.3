package org.w3c.css.sac;

public interface DescendantSelector extends Selector {
  Selector getAncestorSelector();
  
  SimpleSelector getSimpleSelector();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/DescendantSelector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */