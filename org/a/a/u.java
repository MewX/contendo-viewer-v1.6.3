/*     */ package org.a.a;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonNumber;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonLocation;
/*     */ import javax.json.stream.JsonParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class u
/*     */   implements JsonParser
/*     */ {
/*     */   private c a;
/*     */   private JsonParser.Event b;
/*  62 */   private final Deque<c> c = new ArrayDeque<>();
/*     */   
/*     */   u(JsonArray array) {
/*  65 */     this.a = new a(array);
/*     */   }
/*     */   
/*     */   u(JsonObject object) {
/*  69 */     this.a = new b(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString() {
/*  74 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  76 */         return b.a((b)this.a);
/*     */       case 2:
/*  78 */         return ((JsonString)this.a.b()).getString();
/*     */       case 3:
/*  80 */         return ((JsonNumber)this.a.b()).toString();
/*     */     } 
/*  82 */     throw new IllegalStateException(h.a(this.b));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIntegralNumber() {
/*  88 */     if (this.b == JsonParser.Event.VALUE_NUMBER) {
/*  89 */       return ((JsonNumber)this.a.b()).isIntegral();
/*     */     }
/*  91 */     throw new IllegalStateException(h.b(this.b));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  96 */     if (this.b == JsonParser.Event.VALUE_NUMBER) {
/*  97 */       return ((JsonNumber)this.a.b()).intValue();
/*     */     }
/*  99 */     throw new IllegalStateException(h.c(this.b));
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/* 104 */     if (this.b == JsonParser.Event.VALUE_NUMBER) {
/* 105 */       return ((JsonNumber)this.a.b()).longValue();
/*     */     }
/* 107 */     throw new IllegalStateException(h.d(this.b));
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getBigDecimal() {
/* 112 */     if (this.b == JsonParser.Event.VALUE_NUMBER) {
/* 113 */       return ((JsonNumber)this.a.b()).bigDecimalValue();
/*     */     }
/* 115 */     throw new IllegalStateException(h.e(this.b));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonLocation getLocation() {
/* 120 */     return f.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 125 */     return ((this.b != JsonParser.Event.END_OBJECT && this.b != JsonParser.Event.END_ARRAY) || !this.c.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonParser.Event next() {
/* 130 */     if (!hasNext()) {
/* 131 */       throw new NoSuchElementException();
/*     */     }
/* 133 */     a();
/* 134 */     return this.b;
/*     */   }
/*     */   
/*     */   private void a() {
/* 138 */     if (this.b == null) {
/* 139 */       this.b = (this.a instanceof a) ? JsonParser.Event.START_ARRAY : JsonParser.Event.START_OBJECT;
/*     */     } else {
/* 141 */       if (this.b == JsonParser.Event.END_OBJECT || this.b == JsonParser.Event.END_ARRAY) {
/* 142 */         this.a = this.c.pop();
/*     */       }
/* 144 */       if (this.a instanceof a) {
/* 145 */         if (this.a.hasNext()) {
/* 146 */           this.a.next();
/* 147 */           this.b = a(this.a.b());
/* 148 */           if (this.b == JsonParser.Event.START_ARRAY || this.b == JsonParser.Event.START_OBJECT) {
/* 149 */             this.c.push(this.a);
/* 150 */             this.a = c.a(this.a.b());
/*     */           } 
/*     */         } else {
/* 153 */           this.b = JsonParser.Event.END_ARRAY;
/*     */         }
/*     */       
/*     */       }
/* 157 */       else if (this.b == JsonParser.Event.KEY_NAME) {
/* 158 */         this.b = a(this.a.b());
/* 159 */         if (this.b == JsonParser.Event.START_ARRAY || this.b == JsonParser.Event.START_OBJECT) {
/* 160 */           this.c.push(this.a);
/* 161 */           this.a = c.a(this.a.b());
/*     */         }
/*     */       
/* 164 */       } else if (this.a.hasNext()) {
/* 165 */         this.a.next();
/* 166 */         this.b = JsonParser.Event.KEY_NAME;
/*     */       } else {
/* 168 */         this.b = JsonParser.Event.END_OBJECT;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipObject() {
/* 182 */     if (this.a instanceof b) {
/* 183 */       int depth = 1;
/*     */       do {
/* 185 */         if (this.b == JsonParser.Event.KEY_NAME) {
/* 186 */           this.b = a(this.a.b());
/* 187 */           switch (null.a[this.b.ordinal()]) {
/*     */             case 4:
/* 189 */               depth++;
/*     */               break;
/*     */             case 5:
/* 192 */               depth--;
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/* 198 */         } else if (this.a.hasNext()) {
/* 199 */           this.a.next();
/* 200 */           this.b = JsonParser.Event.KEY_NAME;
/*     */         } else {
/* 202 */           this.b = JsonParser.Event.END_OBJECT;
/* 203 */           depth--;
/*     */         }
/*     */       
/* 206 */       } while (this.b != JsonParser.Event.END_OBJECT && depth > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void skipArray() {
/* 212 */     if (this.a instanceof a) {
/* 213 */       int depth = 1;
/*     */       do {
/* 215 */         if (this.a.hasNext()) {
/* 216 */           this.a.next();
/* 217 */           this.b = a(this.a.b());
/* 218 */           switch (null.a[this.b.ordinal()]) {
/*     */             case 6:
/* 220 */               depth++;
/*     */               break;
/*     */             case 7:
/* 223 */               depth--;
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/*     */         } else {
/* 229 */           this.b = JsonParser.Event.END_ARRAY;
/* 230 */           depth--;
/*     */         } 
/* 232 */       } while (this.b != JsonParser.Event.END_ARRAY || depth != 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static JsonParser.Event a(JsonValue value) {
/* 237 */     switch (null.b[value.getValueType().ordinal()]) {
/*     */       case 1:
/* 239 */         return JsonParser.Event.START_ARRAY;
/*     */       case 2:
/* 241 */         return JsonParser.Event.START_OBJECT;
/*     */       case 3:
/* 243 */         return JsonParser.Event.VALUE_STRING;
/*     */       case 4:
/* 245 */         return JsonParser.Event.VALUE_NUMBER;
/*     */       case 5:
/* 247 */         return JsonParser.Event.VALUE_TRUE;
/*     */       case 6:
/* 249 */         return JsonParser.Event.VALUE_FALSE;
/*     */       case 7:
/* 251 */         return JsonParser.Event.VALUE_NULL;
/*     */     } 
/* 253 */     throw new JsonException(h.a(value.getValueType()));
/*     */   }
/*     */   
/*     */   private static abstract class c
/*     */     implements Iterator {
/*     */     private c() {}
/*     */     
/*     */     static c a(JsonValue value) {
/* 261 */       if (value instanceof JsonArray)
/* 262 */         return new u.a((JsonArray)value); 
/* 263 */       if (value instanceof JsonObject) {
/* 264 */         return new u.b((JsonObject)value);
/*     */       }
/* 266 */       throw new JsonException(h.a(value));
/*     */     }
/*     */     
/*     */     abstract JsonValue b(); }
/*     */   
/*     */   private static class a extends c {
/*     */     private final Iterator<JsonValue> a;
/*     */     
/*     */     a(JsonArray array) {
/* 275 */       this.a = array.iterator();
/*     */     }
/*     */     private JsonValue b;
/*     */     
/*     */     public boolean hasNext() {
/* 280 */       return this.a.hasNext();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue a() {
/* 285 */       this.b = this.a.next();
/* 286 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 291 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     JsonValue b() {
/* 296 */       return this.b;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class b
/*     */     extends c {
/*     */     private final Iterator<Map.Entry<String, JsonValue>> a;
/*     */     private JsonValue b;
/*     */     private String c;
/*     */     
/*     */     b(JsonObject object) {
/* 307 */       this.a = object.entrySet().iterator();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 312 */       return this.a.hasNext();
/*     */     }
/*     */ 
/*     */     
/*     */     public Map.Entry<String, JsonValue> a() {
/* 317 */       Map.Entry<String, JsonValue> next = this.a.next();
/* 318 */       this.c = next.getKey();
/* 319 */       this.b = next.getValue();
/* 320 */       return next;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 325 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     JsonValue b() {
/* 330 */       return this.b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */