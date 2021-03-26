/*     */ package org.a.a;
/*     */ 
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonMergePatch;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonValue;
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
/*     */ public final class g
/*     */   implements JsonMergePatch
/*     */ {
/*     */   private JsonValue a;
/*     */   
/*     */   public g(JsonValue patch) {
/*  61 */     this.a = patch;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue apply(JsonValue target) {
/*  66 */     return b(target, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue toJsonValue() {
/*  71 */     return this.a;
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
/*     */   private static JsonValue b(JsonValue target, JsonValue patch) {
/*     */     JsonObject jsonObject1;
/*  84 */     if (patch.getValueType() != JsonValue.ValueType.OBJECT) {
/*  85 */       return patch;
/*     */     }
/*  87 */     if (target.getValueType() != JsonValue.ValueType.OBJECT) {
/*  88 */       jsonObject1 = JsonValue.EMPTY_JSON_OBJECT;
/*     */     }
/*  90 */     JsonObject targetJsonObject = jsonObject1.asJsonObject();
/*     */     
/*  92 */     JsonObjectBuilder builder = Json.createObjectBuilder(targetJsonObject);
/*  93 */     patch.asJsonObject().forEach((key, value) -> {
/*     */           if (value == JsonValue.NULL) {
/*     */             if (targetJsonObject.containsKey(key)) {
/*     */               builder.remove(key);
/*     */             }
/*     */           } else if (targetJsonObject.containsKey(key)) {
/*     */             builder.add(key, b((JsonValue)targetJsonObject.get(key), value));
/*     */           } else {
/*     */             builder.add(key, b((JsonValue)JsonValue.EMPTY_JSON_OBJECT, value));
/*     */           } 
/*     */         });
/* 104 */     return (JsonValue)builder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static JsonValue a(JsonValue source, JsonValue target) {
/* 114 */     if (source.getValueType() != JsonValue.ValueType.OBJECT || target
/* 115 */       .getValueType() != JsonValue.ValueType.OBJECT) {
/* 116 */       return target;
/*     */     }
/* 118 */     JsonObject s = (JsonObject)source;
/* 119 */     JsonObject t = (JsonObject)target;
/* 120 */     JsonObjectBuilder builder = Json.createObjectBuilder();
/*     */     
/* 122 */     s.forEach((key, value) -> {
/*     */           if (t.containsKey(key)) {
/*     */             if (!value.equals(t.get(key))) {
/*     */               builder.add(key, a(value, (JsonValue)t.get(key)));
/*     */             }
/*     */           } else {
/*     */             builder.addNull(key);
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 134 */     t.forEach((key, value) -> {
/*     */           if (!s.containsKey(key))
/*     */             builder.add(key, value); 
/*     */         });
/* 138 */     return (JsonValue)builder.build();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */