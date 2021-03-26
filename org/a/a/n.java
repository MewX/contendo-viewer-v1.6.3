/*     */ package org.a.a;
/*     */ 
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonPatch;
/*     */ import javax.json.JsonPatchBuilder;
/*     */ import javax.json.JsonPointer;
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonStructure;
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
/*     */ public class n
/*     */   implements JsonPatch
/*     */ {
/*     */   private final JsonArray a;
/*     */   
/*     */   public n(JsonArray patch) {
/*  81 */     this.a = patch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  92 */     if (this == obj)
/*  93 */       return true; 
/*  94 */     if (obj == null || obj.getClass() != n.class)
/*  95 */       return false; 
/*  96 */     return this.a.equals(((n)obj).a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 106 */     return this.a.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return this.a.toString();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonStructure apply(JsonStructure target) {
/* 130 */     JsonStructure result = target;
/*     */     
/* 132 */     for (JsonValue operation : this.a) {
/* 133 */       if (operation.getValueType() != JsonValue.ValueType.OBJECT) {
/* 134 */         throw new JsonException(h.y());
/*     */       }
/* 136 */       result = a(result, (JsonObject)operation);
/*     */     } 
/* 138 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArray toJsonArray() {
/* 143 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonArray a(JsonStructure source, JsonStructure target) {
/* 154 */     return (new a()).a(source, target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JsonStructure a(JsonStructure target, JsonObject operation) {
/*     */     JsonPointer from;
/*     */     String dest, src;
/* 165 */     JsonPointer pointer = a(operation, "path");
/*     */     
/* 167 */     switch (null.a[JsonPatch.Operation.fromOperationName(operation.getString("op")).ordinal()]) {
/*     */       case 1:
/* 169 */         return pointer.add(target, a(operation));
/*     */       case 2:
/* 171 */         return pointer.replace(target, a(operation));
/*     */       case 3:
/* 173 */         return pointer.remove(target);
/*     */       case 4:
/* 175 */         from = a(operation, "from");
/* 176 */         return pointer.add(target, from.getValue(target));
/*     */       
/*     */       case 5:
/* 179 */         dest = operation.getString("path");
/* 180 */         src = operation.getString("from");
/* 181 */         if (dest.startsWith(src) && src.length() < dest.length()) {
/* 182 */           throw new JsonException(h.a(src, dest));
/*     */         }
/* 184 */         from = a(operation, "from");
/*     */         
/* 186 */         if (!from.containsValue(target)) {
/* 187 */           throw new JsonException(h.d(src));
/*     */         }
/* 189 */         if (pointer.equals(from))
/*     */         {
/* 191 */           return target;
/*     */         }
/* 193 */         return pointer.add(from.remove(target), from.getValue(target));
/*     */       case 6:
/* 195 */         if (!a(operation).equals(pointer.getValue(target))) {
/* 196 */           throw new JsonException(h.z());
/*     */         }
/* 198 */         return target;
/*     */     } 
/* 200 */     throw new JsonException(h.e(operation.getString("op")));
/*     */   }
/*     */ 
/*     */   
/*     */   private JsonPointer a(JsonObject operation, String member) {
/* 205 */     JsonString pointerString = operation.getJsonString(member);
/* 206 */     if (pointerString == null) {
/* 207 */       a(operation.getString("op"), member);
/*     */     }
/* 209 */     return Json.createPointer(pointerString.getString());
/*     */   }
/*     */   
/*     */   private JsonValue a(JsonObject operation) {
/* 213 */     JsonValue value = (JsonValue)operation.get("value");
/* 214 */     if (value == null) {
/* 215 */       a(operation.getString("op"), "value");
/*     */     }
/* 217 */     return value;
/*     */   }
/*     */   
/*     */   private void a(String op, String member) {
/* 221 */     throw new JsonException(h.b(op, member));
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private JsonPatchBuilder a;
/*     */     
/*     */     JsonArray a(JsonStructure source, JsonStructure target) {
/* 228 */       this.a = Json.createPatchBuilder();
/* 229 */       a("", (JsonValue)source, (JsonValue)target);
/* 230 */       return this.a.build().toJsonArray();
/*     */     }
/*     */     
/*     */     private void a(String path, JsonValue source, JsonValue target) {
/* 234 */       if (source.equals(target)) {
/*     */         return;
/*     */       }
/* 237 */       JsonValue.ValueType s = source.getValueType();
/* 238 */       JsonValue.ValueType t = target.getValueType();
/* 239 */       if (s == JsonValue.ValueType.OBJECT && t == JsonValue.ValueType.OBJECT) {
/* 240 */         a(path, (JsonObject)source, (JsonObject)target);
/* 241 */       } else if (s == JsonValue.ValueType.ARRAY && t == JsonValue.ValueType.ARRAY) {
/* 242 */         a(path, (JsonArray)source, (JsonArray)target);
/*     */       } else {
/* 244 */         this.a.replace(path, target);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void a(String path, JsonObject source, JsonObject target) {
/* 249 */       source.forEach((key, value) -> {
/*     */             if (target.containsKey(key)) {
/*     */               a(path + '/' + key, value, (JsonValue)target.get(key));
/*     */             } else {
/*     */               this.a.remove(path + '/' + key);
/*     */             } 
/*     */           });
/* 256 */       target.forEach((key, value) -> {
/*     */             if (!source.containsKey(key)) {
/*     */               this.a.add(path + '/' + key, value);
/*     */             }
/*     */           });
/*     */     }
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
/*     */     private void a(String path, JsonArray source, JsonArray target) {
/* 274 */       int m = source.size();
/* 275 */       int n = target.size();
/* 276 */       int[][] c = new int[m + 1][n + 1]; int i;
/* 277 */       for (i = 0; i < m + 1; i++)
/* 278 */         c[i][0] = 0; 
/* 279 */       for (i = 0; i < n + 1; i++)
/* 280 */         c[0][i] = 0; 
/* 281 */       for (i = 0; i < m; i++) {
/* 282 */         for (int k = 0; k < n; k++) {
/* 283 */           if (((JsonValue)source.get(i)).equals(target.get(k))) {
/* 284 */             c[i + 1][k + 1] = (c[i][k] & 0xFFFFFFFE) + 3;
/*     */           } else {
/*     */             
/* 287 */             c[i + 1][k + 1] = Math.max(c[i + 1][k], c[i][k + 1]) & 0xFFFFFFFE;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 292 */       i = m;
/* 293 */       int j = n;
/* 294 */       while (i > 0 || j > 0) {
/* 295 */         if (i == 0) {
/* 296 */           j--;
/* 297 */           this.a.add(path + '/' + j, (JsonValue)target.get(j)); continue;
/* 298 */         }  if (j == 0) {
/* 299 */           i--;
/* 300 */           this.a.remove(path + '/' + i); continue;
/* 301 */         }  if ((c[i][j] & 0x1) == 1) {
/* 302 */           i--; j--; continue;
/*     */         } 
/* 304 */         int f = c[i][j - 1] >> 1;
/* 305 */         int g = c[i - 1][j] >> 1;
/* 306 */         if (f > g) {
/* 307 */           j--;
/* 308 */           this.a.add(path + '/' + j, (JsonValue)target.get(j)); continue;
/* 309 */         }  if (f < g) {
/* 310 */           i--;
/* 311 */           this.a.remove(path + '/' + i); continue;
/*     */         } 
/* 313 */         i--; j--;
/* 314 */         a(path + '/' + i, (JsonValue)source.get(i), (JsonValue)target.get(j));
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */