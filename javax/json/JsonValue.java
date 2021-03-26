/*     */ package javax.json;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface JsonValue
/*     */ {
/*  61 */   public static final JsonObject EMPTY_JSON_OBJECT = Json.createObjectBuilder().build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final JsonArray EMPTY_JSON_ARRAY = Json.createArrayBuilder().build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ValueType
/*     */   {
/*  77 */     ARRAY,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     OBJECT,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     STRING,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     NUMBER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     TRUE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     FALSE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     NULL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public static final JsonValue NULL = new JsonValueImpl(ValueType.NULL);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static final JsonValue TRUE = new JsonValueImpl(ValueType.TRUE);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final JsonValue FALSE = new JsonValueImpl(ValueType.FALSE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ValueType getValueType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default JsonObject asJsonObject() {
/* 141 */     return JsonObject.class.cast(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default JsonArray asJsonArray() {
/* 153 */     return JsonArray.class.cast(this);
/*     */   }
/*     */   
/*     */   String toString();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */