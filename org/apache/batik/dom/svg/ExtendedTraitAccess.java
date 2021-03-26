package org.apache.batik.dom.svg;

public interface ExtendedTraitAccess extends TraitAccess {
  boolean hasProperty(String paramString);
  
  boolean hasTrait(String paramString1, String paramString2);
  
  boolean isPropertyAnimatable(String paramString);
  
  boolean isAttributeAnimatable(String paramString1, String paramString2);
  
  boolean isPropertyAdditive(String paramString);
  
  boolean isAttributeAdditive(String paramString1, String paramString2);
  
  boolean isTraitAnimatable(String paramString1, String paramString2);
  
  boolean isTraitAdditive(String paramString1, String paramString2);
  
  int getPropertyType(String paramString);
  
  int getAttributeType(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/ExtendedTraitAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */