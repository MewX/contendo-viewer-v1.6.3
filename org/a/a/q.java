/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonBuilderFactory;
/*     */ import javax.json.JsonMergePatch;
/*     */ import javax.json.JsonNumber;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonPatch;
/*     */ import javax.json.JsonPatchBuilder;
/*     */ import javax.json.JsonPointer;
/*     */ import javax.json.JsonReader;
/*     */ import javax.json.JsonReaderFactory;
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonStructure;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.JsonWriter;
/*     */ import javax.json.JsonWriterFactory;
/*     */ import javax.json.spi.JsonProvider;
/*     */ import javax.json.stream.JsonGenerator;
/*     */ import javax.json.stream.JsonGeneratorFactory;
/*     */ import javax.json.stream.JsonParser;
/*     */ import javax.json.stream.JsonParserFactory;
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
/*     */ public class q
/*     */   extends JsonProvider
/*     */ {
/*  69 */   private final a a = new a();
/*     */ 
/*     */   
/*     */   public JsonGenerator createGenerator(Writer writer) {
/*  73 */     return new e(writer, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator createGenerator(OutputStream out) {
/*  78 */     return new e(out, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonParser createParser(Reader reader) {
/*  83 */     return new l(reader, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonParser createParser(InputStream in) {
/*  88 */     return new l(in, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonParserFactory createParserFactory(Map<String, ?> config) {
/*  93 */     a pool = null;
/*  94 */     if (config != null && config.containsKey(a.class.getName())) {
/*  95 */       pool = (a)config.get(a.class.getName());
/*     */     }
/*  97 */     if (pool == null) {
/*  98 */       pool = this.a;
/*     */     }
/* 100 */     return new k(pool);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
/*     */     Map<String, Object> providerConfig;
/*     */     boolean prettyPrinting;
/*     */     a pool;
/* 108 */     if (config == null) {
/* 109 */       providerConfig = Collections.emptyMap();
/* 110 */       prettyPrinting = false;
/* 111 */       pool = this.a;
/*     */     } else {
/* 113 */       providerConfig = new HashMap<>();
/* 114 */       if (prettyPrinting = a(config)) {
/* 115 */         providerConfig.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
/*     */       }
/* 117 */       pool = (a)config.get(a.class.getName());
/* 118 */       if (pool != null) {
/* 119 */         providerConfig.put(a.class.getName(), pool);
/*     */       } else {
/* 121 */         pool = this.a;
/*     */       } 
/* 123 */       providerConfig = Collections.unmodifiableMap(providerConfig);
/*     */     } 
/*     */     
/* 126 */     return new d(providerConfig, prettyPrinting, pool);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonReader createReader(Reader reader) {
/* 131 */     return new s(reader, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonReader createReader(InputStream in) {
/* 136 */     return new s(in, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonWriter createWriter(Writer writer) {
/* 141 */     return new y(writer, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonWriter createWriter(OutputStream out) {
/* 146 */     return new y(out, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonWriterFactory createWriterFactory(Map<String, ?> config) {
/*     */     Map<String, Object> providerConfig;
/*     */     boolean prettyPrinting;
/*     */     a pool;
/* 154 */     if (config == null) {
/* 155 */       providerConfig = Collections.emptyMap();
/* 156 */       prettyPrinting = false;
/* 157 */       pool = this.a;
/*     */     } else {
/* 159 */       providerConfig = new HashMap<>();
/* 160 */       if (prettyPrinting = a(config)) {
/* 161 */         providerConfig.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
/*     */       }
/* 163 */       pool = (a)config.get(a.class.getName());
/* 164 */       if (pool != null) {
/* 165 */         providerConfig.put(a.class.getName(), pool);
/*     */       } else {
/* 167 */         pool = this.a;
/*     */       } 
/* 169 */       providerConfig = Collections.unmodifiableMap(providerConfig);
/*     */     } 
/* 171 */     return new x(providerConfig, prettyPrinting, pool);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonReaderFactory createReaderFactory(Map<String, ?> config) {
/* 176 */     a pool = null;
/* 177 */     if (config != null && config.containsKey(a.class.getName())) {
/* 178 */       pool = (a)config.get(a.class.getName());
/*     */     }
/* 180 */     if (pool == null) {
/* 181 */       pool = this.a;
/*     */     }
/* 183 */     return new r(pool);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder createObjectBuilder() {
/* 188 */     return new j(this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder createObjectBuilder(JsonObject object) {
/* 193 */     return new j(object, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObjectBuilder createObjectBuilder(Map<String, Object> map) {
/* 198 */     return new j(map, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder createArrayBuilder() {
/* 203 */     return new b(this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder createArrayBuilder(JsonArray array) {
/* 208 */     return new b(array, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArrayBuilder createArrayBuilder(Collection<?> collection) {
/* 213 */     return new b(collection, this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonPointer createPointer(String jsonPointer) {
/* 218 */     return new o(jsonPointer);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder createPatchBuilder() {
/* 223 */     return new m();
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder createPatchBuilder(JsonArray array) {
/* 228 */     return new m(array);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonPatch createPatch(JsonArray array) {
/* 233 */     return new n(array);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonPatch createDiff(JsonStructure source, JsonStructure target) {
/* 238 */     return new n(n.a(source, target));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonMergePatch createMergePatch(JsonValue patch) {
/* 243 */     return new g(patch);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonMergePatch createMergeDiff(JsonValue source, JsonValue target) {
/* 248 */     return new g(g.a(source, target));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonString createValue(String value) {
/* 253 */     return new t(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonNumber createValue(int value) {
/* 258 */     return i.a(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonNumber createValue(long value) {
/* 263 */     return i.a(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonNumber createValue(double value) {
/* 268 */     return i.a(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonNumber createValue(BigInteger value) {
/* 273 */     return i.a(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonNumber createValue(BigDecimal value) {
/* 278 */     return i.a(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
/* 283 */     a pool = null;
/* 284 */     if (config != null && config.containsKey(a.class.getName())) {
/* 285 */       pool = (a)config.get(a.class.getName());
/*     */     }
/* 287 */     if (pool == null) {
/* 288 */       pool = this.a;
/*     */     }
/* 290 */     return new c(pool);
/*     */   }
/*     */   
/*     */   static boolean a(Map<String, ?> config) {
/* 294 */     return config.containsKey("javax.json.stream.JsonGenerator.prettyPrinting");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */