/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.StringWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonNumber;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonStructure;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.JsonWriter;
/*     */ import org.a.a.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class b
/*     */   implements JsonArrayBuilder
/*     */ {
/*     */   private ArrayList<JsonValue> a;
/*     */   private final a b;
/*     */   
/*     */   b(a bufferPool) {
/*  68 */     this.b = bufferPool;
/*     */   }
/*     */   
/*     */   b(JsonArray array, a bufferPool) {
/*  72 */     this.b = bufferPool;
/*  73 */     this.a = new ArrayList<>();
/*  74 */     this.a.addAll((Collection<? extends JsonValue>)array);
/*     */   }
/*     */   
/*     */   b(Collection<?> collection, a bufferPool) {
/*  78 */     this.b = bufferPool;
/*  79 */     this.a = new ArrayList<>();
/*  80 */     a(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(JsonValue value) {
/*  85 */     a(value);
/*  86 */     a(value);
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(String value) {
/*  92 */     a(value);
/*  93 */     a((JsonValue)new t(value));
/*  94 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(BigDecimal value) {
/*  99 */     a(value);
/* 100 */     a((JsonValue)i.a(value));
/* 101 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(BigInteger value) {
/* 106 */     a(value);
/* 107 */     a((JsonValue)i.a(value));
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int value) {
/* 113 */     a((JsonValue)i.a(value));
/* 114 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(long value) {
/* 119 */     a((JsonValue)i.a(value));
/* 120 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(double value) {
/* 125 */     a((JsonValue)i.a(value));
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(boolean value) {
/* 131 */     a(value ? JsonValue.TRUE : JsonValue.FALSE);
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder addNull() {
/* 137 */     a(JsonValue.NULL);
/* 138 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(JsonObjectBuilder builder) {
/* 143 */     if (builder == null) {
/* 144 */       throw new NullPointerException(h.t());
/*     */     }
/* 146 */     a((JsonValue)builder.build());
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(JsonArrayBuilder builder) {
/* 152 */     if (builder == null) {
/* 153 */       throw new NullPointerException(h.u());
/*     */     }
/* 155 */     a((JsonValue)builder.build());
/* 156 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder addAll(JsonArrayBuilder builder) {
/* 161 */     if (builder == null) {
/* 162 */       throw new NullPointerException(h.u());
/*     */     }
/* 164 */     if (this.a == null) {
/* 165 */       this.a = new ArrayList<>();
/*     */     }
/* 167 */     this.a.addAll((Collection<? extends JsonValue>)builder.build());
/* 168 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, JsonValue value) {
/* 173 */     a(value);
/* 174 */     a(index, value);
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, String value) {
/* 180 */     a(value);
/* 181 */     a(index, (JsonValue)new t(value));
/* 182 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, BigDecimal value) {
/* 187 */     a(value);
/* 188 */     a(index, (JsonValue)i.a(value));
/* 189 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, BigInteger value) {
/* 194 */     a(value);
/* 195 */     a(index, (JsonValue)i.a(value));
/* 196 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, int value) {
/* 201 */     a(index, (JsonValue)i.a(value));
/* 202 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, long value) {
/* 207 */     a(index, (JsonValue)i.a(value));
/* 208 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, double value) {
/* 213 */     a(index, (JsonValue)i.a(value));
/* 214 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, boolean value) {
/* 219 */     a(index, value ? JsonValue.TRUE : JsonValue.FALSE);
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder addNull(int index) {
/* 225 */     a(index, JsonValue.NULL);
/* 226 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, JsonObjectBuilder builder) {
/* 231 */     if (builder == null) {
/* 232 */       throw new NullPointerException(h.t());
/*     */     }
/* 234 */     a(index, (JsonValue)builder.build());
/* 235 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder add(int index, JsonArrayBuilder builder) {
/* 240 */     if (builder == null) {
/* 241 */       throw new NullPointerException(h.t());
/*     */     }
/* 243 */     a(index, (JsonValue)builder.build());
/* 244 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, JsonValue value) {
/* 249 */     a(value);
/* 250 */     b(index, value);
/* 251 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, String value) {
/* 256 */     a(value);
/* 257 */     b(index, (JsonValue)new t(value));
/* 258 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, BigDecimal value) {
/* 263 */     a(value);
/* 264 */     b(index, (JsonValue)i.a(value));
/* 265 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, BigInteger value) {
/* 270 */     a(value);
/* 271 */     b(index, (JsonValue)i.a(value));
/* 272 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, int value) {
/* 277 */     b(index, (JsonValue)i.a(value));
/* 278 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, long value) {
/* 283 */     b(index, (JsonValue)i.a(value));
/* 284 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, double value) {
/* 289 */     b(index, (JsonValue)i.a(value));
/* 290 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, boolean value) {
/* 295 */     b(index, value ? JsonValue.TRUE : JsonValue.FALSE);
/* 296 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder setNull(int index) {
/* 301 */     b(index, JsonValue.NULL);
/* 302 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, JsonObjectBuilder builder) {
/* 307 */     if (builder == null) {
/* 308 */       throw new NullPointerException(h.t());
/*     */     }
/* 310 */     b(index, (JsonValue)builder.build());
/* 311 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder set(int index, JsonArrayBuilder builder) {
/* 316 */     if (builder == null) {
/* 317 */       throw new NullPointerException(h.t());
/*     */     }
/* 319 */     b(index, (JsonValue)builder.build());
/* 320 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder remove(int index) {
/* 325 */     if (this.a == null) {
/* 326 */       throw new IndexOutOfBoundsException(h.a(index, 0));
/*     */     }
/* 328 */     this.a.remove(index);
/* 329 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArray build() {
/*     */     List<JsonValue> snapshot;
/* 335 */     if (this.a == null) {
/* 336 */       snapshot = Collections.emptyList();
/*     */     }
/*     */     else {
/*     */       
/* 340 */       snapshot = Collections.unmodifiableList(this.a);
/*     */     } 
/* 342 */     this.a = null;
/* 343 */     return new a(snapshot, this.b);
/*     */   }
/*     */   
/*     */   private void a(Collection<?> collection) {
/* 347 */     for (Object value : collection) {
/* 348 */       if (value != null && value instanceof Optional) {
/* 349 */         ((Optional)value).ifPresent(v -> this.a.add(z.a(v, this.b)));
/*     */         continue;
/*     */       } 
/* 352 */       this.a.add(z.a(value, this.b));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(JsonValue value) {
/* 358 */     if (this.a == null) {
/* 359 */       this.a = new ArrayList<>();
/*     */     }
/* 361 */     this.a.add(value);
/*     */   }
/*     */   
/*     */   private void a(int index, JsonValue value) {
/* 365 */     if (this.a == null) {
/* 366 */       this.a = new ArrayList<>();
/*     */     }
/* 368 */     this.a.add(index, value);
/*     */   }
/*     */   
/*     */   private void b(int index, JsonValue value) {
/* 372 */     if (this.a == null) {
/* 373 */       throw new IndexOutOfBoundsException(h.a(index, 0));
/*     */     }
/* 375 */     this.a.set(index, value);
/*     */   }
/*     */   
/*     */   private void a(Object value) {
/* 379 */     if (value == null)
/* 380 */       throw new NullPointerException(h.s()); 
/*     */   }
/*     */   
/*     */   private static final class a
/*     */     extends AbstractList<JsonValue> implements JsonArray {
/*     */     private final List<JsonValue> a;
/*     */     private final a b;
/*     */     
/*     */     a(List<JsonValue> valueList, a bufferPool) {
/* 389 */       this.a = valueList;
/* 390 */       this.b = bufferPool;
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 395 */       return this.a.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject getJsonObject(int index) {
/* 400 */       return (JsonObject)this.a.get(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonArray getJsonArray(int index) {
/* 405 */       return (JsonArray)this.a.get(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonNumber getJsonNumber(int index) {
/* 410 */       return (JsonNumber)this.a.get(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonString getJsonString(int index) {
/* 415 */       return (JsonString)this.a.get(index);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends JsonValue> List<T> getValuesAs(Class<T> clazz) {
/* 421 */       return (List)this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(int index) {
/* 426 */       return getJsonString(index).getString();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(int index, String defaultValue) {
/*     */       try {
/* 432 */         return getString(index);
/* 433 */       } catch (Exception e) {
/* 434 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(int index) {
/* 440 */       return getJsonNumber(index).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(int index, int defaultValue) {
/*     */       try {
/* 446 */         return getInt(index);
/* 447 */       } catch (Exception e) {
/* 448 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getBoolean(int index) {
/* 454 */       JsonValue jsonValue = a(index);
/* 455 */       if (jsonValue == JsonValue.TRUE)
/* 456 */         return true; 
/* 457 */       if (jsonValue == JsonValue.FALSE) {
/* 458 */         return false;
/*     */       }
/* 460 */       throw new ClassCastException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean getBoolean(int index, boolean defaultValue) {
/*     */       try {
/* 467 */         return getBoolean(index);
/* 468 */       } catch (Exception e) {
/* 469 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isNull(int index) {
/* 475 */       return ((JsonValue)this.a.get(index)).equals(JsonValue.NULL);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue.ValueType getValueType() {
/* 480 */       return JsonValue.ValueType.ARRAY;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue a(int index) {
/* 485 */       return this.a.get(index);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 490 */       StringWriter sw = new StringWriter();
/* 491 */       JsonWriter jw = new y(sw, this.b); Throwable throwable = null; 
/* 492 */       try { jw.write((JsonStructure)this); } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; }
/* 493 */       finally { if (throwable != null) { try { jw.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  } else { jw.close(); }  }
/* 494 */        return sw.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonArray asJsonArray() {
/* 499 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */