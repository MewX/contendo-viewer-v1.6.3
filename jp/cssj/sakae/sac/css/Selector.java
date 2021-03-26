package jp.cssj.sakae.sac.css;

public interface Selector {
  public static final short SAC_CONDITIONAL_SELECTOR = 0;
  
  public static final short SAC_ANY_NODE_SELECTOR = 1;
  
  public static final short SAC_ROOT_NODE_SELECTOR = 2;
  
  public static final short SAC_NEGATIVE_SELECTOR = 3;
  
  public static final short SAC_ELEMENT_NODE_SELECTOR = 4;
  
  public static final short SAC_TEXT_NODE_SELECTOR = 5;
  
  public static final short SAC_CDATA_SECTION_NODE_SELECTOR = 6;
  
  public static final short SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR = 7;
  
  public static final short SAC_COMMENT_NODE_SELECTOR = 8;
  
  public static final short SAC_PSEUDO_ELEMENT_SELECTOR = 9;
  
  public static final short SAC_DESCENDANT_SELECTOR = 10;
  
  public static final short SAC_CHILD_SELECTOR = 11;
  
  public static final short SAC_DIRECT_ADJACENT_SELECTOR = 12;
  
  short getSelectorType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/Selector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */