package javax.json;

public interface JsonString extends JsonValue {
  String getString();
  
  CharSequence getChars();
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */