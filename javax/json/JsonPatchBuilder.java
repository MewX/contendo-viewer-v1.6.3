package javax.json;

public interface JsonPatchBuilder {
  JsonPatchBuilder add(String paramString, JsonValue paramJsonValue);
  
  JsonPatchBuilder add(String paramString1, String paramString2);
  
  JsonPatchBuilder add(String paramString, int paramInt);
  
  JsonPatchBuilder add(String paramString, boolean paramBoolean);
  
  JsonPatchBuilder remove(String paramString);
  
  JsonPatchBuilder replace(String paramString, JsonValue paramJsonValue);
  
  JsonPatchBuilder replace(String paramString1, String paramString2);
  
  JsonPatchBuilder replace(String paramString, int paramInt);
  
  JsonPatchBuilder replace(String paramString, boolean paramBoolean);
  
  JsonPatchBuilder move(String paramString1, String paramString2);
  
  JsonPatchBuilder copy(String paramString1, String paramString2);
  
  JsonPatchBuilder test(String paramString, JsonValue paramJsonValue);
  
  JsonPatchBuilder test(String paramString1, String paramString2);
  
  JsonPatchBuilder test(String paramString, int paramInt);
  
  JsonPatchBuilder test(String paramString, boolean paramBoolean);
  
  JsonPatch build();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonPatchBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */