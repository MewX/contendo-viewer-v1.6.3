package org.w3c.css.sac;

public interface AttributeCondition extends Condition {
  String getNamespaceURI();
  
  String getLocalName();
  
  boolean getSpecified();
  
  String getValue();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/AttributeCondition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */