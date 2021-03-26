/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonPointer;
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
/*     */ public final class o
/*     */   implements Serializable, JsonPointer
/*     */ {
/*     */   private static final long a = -8123110179640843141L;
/*     */   private final String[] b;
/*     */   private final String c;
/*     */   
/*     */   public o(String jsonPointer) {
/*  87 */     this.c = jsonPointer;
/*  88 */     this.b = jsonPointer.split("/", -1);
/*  89 */     if (!"".equals(this.b[0])) {
/*  90 */       throw new JsonException(h.v());
/*     */     }
/*  92 */     for (int i = 1; i < this.b.length; i++) {
/*  93 */       String token = this.b[i];
/*  94 */       StringBuilder reftoken = new StringBuilder();
/*  95 */       for (int j = 0; j < token.length(); j++) {
/*  96 */         char ch = token.charAt(j);
/*  97 */         if (ch == '~' && j < token.length() - 1) {
/*  98 */           char ch1 = token.charAt(j + 1);
/*  99 */           if (ch1 == '0') {
/* 100 */             ch = '~'; j++;
/* 101 */           } else if (ch1 == '1') {
/* 102 */             ch = '/'; j++;
/*     */           } 
/*     */         } 
/* 105 */         reftoken.append(ch);
/*     */       } 
/* 107 */       this.b[i] = reftoken.toString();
/*     */     } 
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
/* 119 */     if (this == obj)
/* 120 */       return true; 
/* 121 */     if (obj == null || obj.getClass() != o.class)
/* 122 */       return false; 
/* 123 */     return this.c.equals(((o)obj).c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 134 */     return this.c.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(JsonStructure target) {
/* 145 */     A[] refs = a(target);
/* 146 */     return refs[0].a();
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
/*     */   public JsonValue getValue(JsonStructure target) {
/* 159 */     A[] refs = a(target);
/* 160 */     return refs[0].b();
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
/*     */   public JsonStructure add(JsonStructure target, JsonValue value) {
/* 190 */     return a(A::a, target, value);
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
/*     */   
/*     */   public JsonStructure replace(JsonStructure target, JsonValue value) {
/* 206 */     return a(A::b, target, value);
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
/*     */   public JsonStructure remove(JsonStructure target) {
/* 220 */     return a((r, v) -> r.c(), target, null);
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
/*     */   private JsonStructure a(BiFunction<A, JsonValue, JsonStructure> op, JsonStructure target, JsonValue value) {
/* 233 */     A[] refs = a(target);
/* 234 */     JsonStructure result = op.apply(refs[0], value);
/* 235 */     for (int i = 1; i < refs.length; i++) {
/* 236 */       result = refs[i].b((JsonValue)result);
/*     */     }
/* 238 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private A[] a(JsonStructure target) {
/* 248 */     if (this.b.length == 1) {
/* 249 */       A[] arrayOfA = new A[1];
/* 250 */       arrayOfA[0] = A.a(target);
/* 251 */       return arrayOfA;
/*     */     } 
/*     */     
/* 254 */     A[] references = new A[this.b.length - 1];
/* 255 */     JsonStructure jsonStructure = target;
/* 256 */     int s = this.b.length;
/* 257 */     for (int i = 1; i < s; i++) {
/*     */       JsonValue jsonValue; JsonObject object; int index; JsonArray array;
/* 259 */       switch (null.a[jsonStructure.getValueType().ordinal()]) {
/*     */         case 1:
/* 261 */           object = (JsonObject)jsonStructure;
/* 262 */           references[s - i - 1] = A.a(object, this.b[i]);
/* 263 */           if (i < s - 1) {
/* 264 */             jsonValue = (JsonValue)object.get(this.b[i]);
/* 265 */             if (jsonValue == null)
/*     */             {
/* 267 */               throw new JsonException(h.a(object, this.b[i]));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         case 2:
/* 272 */           index = a(this.b[i]);
/* 273 */           array = (JsonArray)jsonValue;
/* 274 */           references[s - i - 1] = A.a(array, index);
/* 275 */           if (i < s - 1 && index != -1)
/*     */           {
/*     */             
/* 278 */             jsonValue = (JsonValue)array.get(index);
/*     */           }
/*     */           break;
/*     */         default:
/* 282 */           throw new JsonException(h.b(jsonValue.getValueType()));
/*     */       } 
/*     */     } 
/* 285 */     return references;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(String token) {
/* 295 */     if (token == null || token.length() == 0) {
/* 296 */       throw new JsonException(h.a(token));
/*     */     }
/* 298 */     if (token.equals("-")) {
/* 299 */       return -1;
/*     */     }
/* 301 */     if (token.equals("0")) {
/* 302 */       return 0;
/*     */     }
/* 304 */     if (token.charAt(0) == '+' || token.charAt(0) == '-') {
/* 305 */       throw new JsonException(h.a(token));
/*     */     }
/*     */     try {
/* 308 */       return Integer.parseInt(token);
/* 309 */     } catch (NumberFormatException ex) {
/* 310 */       throw new JsonException(h.b(token), ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/o.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */