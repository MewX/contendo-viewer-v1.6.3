package org.w3c.css.sac;

public interface SiblingSelector extends Selector {
  public static final short ANY_NODE = 201;
  
  short getNodeType();
  
  Selector getSelector();
  
  SimpleSelector getSiblingSelector();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/SiblingSelector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */