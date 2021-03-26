/*     */ package org.a.a;
/*     */ 
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
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
/*     */ abstract class A
/*     */ {
/*     */   public abstract boolean a();
/*     */   
/*     */   public abstract JsonValue b();
/*     */   
/*     */   public abstract JsonStructure a(JsonValue paramJsonValue);
/*     */   
/*     */   public abstract JsonStructure c();
/*     */   
/*     */   public abstract JsonStructure b(JsonValue paramJsonValue);
/*     */   
/*     */   public static A a(JsonStructure structure) {
/* 134 */     return new c(structure);
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
/*     */   public static A a(JsonObject object, String name) {
/* 146 */     return new b(object, name);
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
/*     */   public static A a(JsonArray array, int index) {
/* 158 */     return new a(array, index);
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends A {
/*     */     private JsonStructure a;
/*     */     
/*     */     c(JsonStructure root) {
/* 166 */       this.a = root;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 171 */       return (this.a != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue b() {
/* 176 */       return (JsonValue)this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonStructure a(JsonValue value) {
/* 181 */       switch (A.null.a[value.getValueType().ordinal()]) {
/*     */         case 1:
/*     */         case 2:
/* 184 */           this.a = (JsonStructure)value;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 189 */           return this.a;
/*     */       } 
/*     */       throw new JsonException(h.w());
/*     */     }
/*     */     public JsonStructure c() {
/* 194 */       throw new JsonException(h.x());
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonStructure b(JsonValue value) {
/* 199 */       return a(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends A {
/*     */     private final JsonObject a;
/*     */     private final String b;
/*     */     
/*     */     b(JsonObject object, String key) {
/* 209 */       this.a = object;
/* 210 */       this.b = key;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 215 */       return (this.a != null && this.a.containsKey(this.b));
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue b() {
/* 220 */       if (!a()) {
/* 221 */         throw new JsonException(h.c(this.b));
/*     */       }
/* 223 */       return (JsonValue)this.a.get(this.b);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject c(JsonValue value) {
/* 228 */       return Json.createObjectBuilder(this.a).add(this.b, value).build();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject d() {
/* 233 */       if (!a()) {
/* 234 */         throw new JsonException(h.c(this.b));
/*     */       }
/* 236 */       return Json.createObjectBuilder(this.a).remove(this.b).build();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject d(JsonValue value) {
/* 241 */       if (!a()) {
/* 242 */         throw new JsonException(h.c(this.b));
/*     */       }
/* 244 */       return c(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends A {
/*     */     private final JsonArray a;
/*     */     private final int b;
/*     */     
/*     */     a(JsonArray array, int index) {
/* 254 */       this.a = array;
/* 255 */       this.b = index;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 260 */       return (this.a != null && this.b > -1 && this.b < this.a.size());
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue b() {
/* 265 */       if (!a()) {
/* 266 */         throw new JsonException(h.b(this.b, this.a.size()));
/*     */       }
/* 268 */       return (JsonValue)this.a.get(this.b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonArray c(JsonValue value) {
/* 275 */       JsonArrayBuilder builder = Json.createArrayBuilder(this.a);
/* 276 */       if (this.b == -1 || this.b == this.a.size()) {
/* 277 */         builder.add(value);
/*     */       }
/* 279 */       else if (this.b < this.a.size()) {
/* 280 */         builder.add(this.b, value);
/*     */       } else {
/* 282 */         throw new JsonException(h.b(this.b, this.a.size()));
/*     */       } 
/*     */       
/* 285 */       return builder.build();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonArray d() {
/* 290 */       if (!a()) {
/* 291 */         throw new JsonException(h.b(this.b, this.a.size()));
/*     */       }
/* 293 */       JsonArrayBuilder builder = Json.createArrayBuilder(this.a);
/* 294 */       return builder.remove(this.b).build();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonArray d(JsonValue value) {
/* 299 */       if (!a()) {
/* 300 */         throw new JsonException(h.b(this.b, this.a.size()));
/*     */       }
/* 302 */       JsonArrayBuilder builder = Json.createArrayBuilder(this.a);
/* 303 */       return builder.set(this.b, value).build();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */