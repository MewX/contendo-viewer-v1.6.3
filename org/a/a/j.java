/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.StringWriter;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
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
/*     */ class j
/*     */   implements JsonObjectBuilder
/*     */ {
/*     */   private Map<String, JsonValue> a;
/*     */   private final a b;
/*     */   
/*     */   j(a bufferPool) {
/*  64 */     this.b = bufferPool;
/*     */   }
/*     */   
/*     */   j(JsonObject object, a bufferPool) {
/*  68 */     this.b = bufferPool;
/*  69 */     this.a = new LinkedHashMap<>();
/*  70 */     this.a.putAll((Map<? extends String, ? extends JsonValue>)object);
/*     */   }
/*     */   
/*     */   j(Map<String, Object> map, a bufferPool) {
/*  74 */     this.b = bufferPool;
/*  75 */     this.a = new LinkedHashMap<>();
/*  76 */     a(map);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, JsonValue value) {
/*  81 */     a(name);
/*  82 */     a(value);
/*  83 */     a(name, value);
/*  84 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, String value) {
/*  89 */     a(name);
/*  90 */     a(value);
/*  91 */     a(name, (JsonValue)new t(value));
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, BigInteger value) {
/*  97 */     a(name);
/*  98 */     a(value);
/*  99 */     a(name, (JsonValue)i.a(value));
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, BigDecimal value) {
/* 105 */     a(name);
/* 106 */     a(value);
/* 107 */     a(name, (JsonValue)i.a(value));
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, int value) {
/* 113 */     a(name);
/* 114 */     a(name, (JsonValue)i.a(value));
/* 115 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, long value) {
/* 120 */     a(name);
/* 121 */     a(name, (JsonValue)i.a(value));
/* 122 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, double value) {
/* 127 */     a(name);
/* 128 */     a(name, (JsonValue)i.a(value));
/* 129 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, boolean value) {
/* 134 */     a(name);
/* 135 */     a(name, value ? JsonValue.TRUE : JsonValue.FALSE);
/* 136 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder addNull(String name) {
/* 141 */     a(name);
/* 142 */     a(name, JsonValue.NULL);
/* 143 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
/* 148 */     a(name);
/* 149 */     if (builder == null) {
/* 150 */       throw new NullPointerException(h.q());
/*     */     }
/* 152 */     a(name, (JsonValue)builder.build());
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
/* 158 */     a(name);
/* 159 */     if (builder == null) {
/* 160 */       throw new NullPointerException(h.r());
/*     */     }
/* 162 */     a(name, (JsonValue)builder.build());
/* 163 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder addAll(JsonObjectBuilder builder) {
/* 168 */     if (builder == null) {
/* 169 */       throw new NullPointerException(h.q());
/*     */     }
/* 171 */     if (this.a == null) {
/* 172 */       this.a = new LinkedHashMap<>();
/*     */     }
/* 174 */     this.a.putAll((Map<? extends String, ? extends JsonValue>)builder.build());
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder remove(String name) {
/* 180 */     a(name);
/* 181 */     this.a.remove(name);
/* 182 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonObject build() {
/* 189 */     Map<String, JsonValue> snapshot = (this.a == null) ? Collections.<String, JsonValue>emptyMap() : Collections.<String, JsonValue>unmodifiableMap(this.a);
/* 190 */     this.a = null;
/* 191 */     return new a(snapshot, this.b);
/*     */   }
/*     */   
/*     */   private void a(Map<String, Object> map) {
/* 195 */     Set<String> fields = map.keySet();
/* 196 */     for (String field : fields) {
/* 197 */       Object value = map.get(field);
/* 198 */       if (value != null && value instanceof Optional) {
/* 199 */         ((Optional)value).ifPresent(v -> (JsonValue)this.a.put(field, z.a(v, this.b)));
/*     */         continue;
/*     */       } 
/* 202 */       this.a.put(field, z.a(value, this.b));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(String name, JsonValue value) {
/* 208 */     if (this.a == null) {
/* 209 */       this.a = new LinkedHashMap<>();
/*     */     }
/* 211 */     this.a.put(name, value);
/*     */   }
/*     */   
/*     */   private void a(String name) {
/* 215 */     if (name == null) {
/* 216 */       throw new NullPointerException(h.o());
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(Object value) {
/* 221 */     if (value == null)
/* 222 */       throw new NullPointerException(h.p()); 
/*     */   }
/*     */   
/*     */   private static final class a
/*     */     extends AbstractMap<String, JsonValue> implements JsonObject {
/*     */     private final Map<String, JsonValue> a;
/*     */     private final a b;
/*     */     
/*     */     a(Map<String, JsonValue> valueMap, a bufferPool) {
/* 231 */       this.a = valueMap;
/* 232 */       this.b = bufferPool;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonArray getJsonArray(String name) {
/* 237 */       return (JsonArray)a(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject getJsonObject(String name) {
/* 242 */       return (JsonObject)a(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonNumber getJsonNumber(String name) {
/* 247 */       return (JsonNumber)a(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonString getJsonString(String name) {
/* 252 */       return (JsonString)a(name);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String name) {
/* 257 */       return getJsonString(name).getString();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getString(String name, String defaultValue) {
/*     */       try {
/* 263 */         return getString(name);
/* 264 */       } catch (Exception e) {
/* 265 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(String name) {
/* 271 */       return getJsonNumber(name).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(String name, int defaultValue) {
/*     */       try {
/* 277 */         return getInt(name);
/* 278 */       } catch (Exception e) {
/* 279 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getBoolean(String name) {
/* 285 */       JsonValue value = a(name);
/* 286 */       if (value == null)
/* 287 */         throw new NullPointerException(); 
/* 288 */       if (value == JsonValue.TRUE)
/* 289 */         return true; 
/* 290 */       if (value == JsonValue.FALSE) {
/* 291 */         return false;
/*     */       }
/* 293 */       throw new ClassCastException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean getBoolean(String name, boolean defaultValue) {
/*     */       try {
/* 300 */         return getBoolean(name);
/* 301 */       } catch (Exception e) {
/* 302 */         return defaultValue;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isNull(String name) {
/* 308 */       return a(name).equals(JsonValue.NULL);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue.ValueType getValueType() {
/* 313 */       return JsonValue.ValueType.OBJECT;
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<String, JsonValue>> entrySet() {
/* 318 */       return this.a.entrySet();
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 323 */       StringWriter sw = new StringWriter();
/* 324 */       JsonWriter jw = new y(sw, this.b); Throwable throwable = null; 
/* 325 */       try { jw.write((JsonStructure)this); } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; }
/* 326 */       finally { if (throwable != null) { try { jw.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  } else { jw.close(); }  }
/* 327 */        return sw.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject asJsonObject() {
/* 332 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 337 */       return this.a.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonValue a(Object key) {
/* 342 */       return this.a.get(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean containsKey(Object key) {
/* 347 */       return this.a.containsKey(key);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */