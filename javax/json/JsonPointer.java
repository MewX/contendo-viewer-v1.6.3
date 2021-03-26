package javax.json;

public interface JsonPointer {
  <T extends JsonStructure> T add(T paramT, JsonValue paramJsonValue);
  
  <T extends JsonStructure> T remove(T paramT);
  
  <T extends JsonStructure> T replace(T paramT, JsonValue paramJsonValue);
  
  boolean containsValue(JsonStructure paramJsonStructure);
  
  JsonValue getValue(JsonStructure paramJsonStructure);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonPointer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */