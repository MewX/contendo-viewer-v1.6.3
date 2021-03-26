package jp.cssj.sakae.sac.css;

public interface AttributeCondition extends Condition {
  String getNamespaceURI();
  
  String getLocalName();
  
  boolean getSpecified();
  
  String getValue();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/AttributeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */