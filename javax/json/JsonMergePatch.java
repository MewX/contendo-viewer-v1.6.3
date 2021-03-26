package javax.json;

public interface JsonMergePatch {
  JsonValue apply(JsonValue paramJsonValue);
  
  JsonValue toJsonValue();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonMergePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */