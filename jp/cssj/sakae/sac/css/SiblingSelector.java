package jp.cssj.sakae.sac.css;

public interface SiblingSelector extends Selector {
  public static final short ANY_NODE = 201;
  
  short getNodeType();
  
  Selector getSelector();
  
  SimpleSelector getSiblingSelector();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/SiblingSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */