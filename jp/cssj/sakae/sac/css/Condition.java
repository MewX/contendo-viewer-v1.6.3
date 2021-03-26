package jp.cssj.sakae.sac.css;

public interface Condition {
  public static final short SAC_AND_CONDITION = 0;
  
  public static final short SAC_OR_CONDITION = 1;
  
  public static final short SAC_NEGATIVE_CONDITION = 2;
  
  public static final short SAC_POSITIONAL_CONDITION = 3;
  
  public static final short SAC_ATTRIBUTE_CONDITION = 4;
  
  public static final short SAC_ID_CONDITION = 5;
  
  public static final short SAC_LANG_CONDITION = 6;
  
  public static final short SAC_ONE_OF_ATTRIBUTE_CONDITION = 7;
  
  public static final short SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION = 8;
  
  public static final short SAC_CLASS_CONDITION = 9;
  
  public static final short SAC_PSEUDO_CLASS_CONDITION = 10;
  
  public static final short SAC_ONLY_CHILD_CONDITION = 11;
  
  public static final short SAC_ONLY_TYPE_CONDITION = 12;
  
  public static final short SAC_CONTENT_CONDITION = 13;
  
  short getConditionType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/Condition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */