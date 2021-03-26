/*     */ package javax.json;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.json.spi.JsonProvider;
/*     */ import javax.json.stream.JsonGenerator;
/*     */ import javax.json.stream.JsonGeneratorFactory;
/*     */ import javax.json.stream.JsonParser;
/*     */ import javax.json.stream.JsonParserFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Json
/*     */ {
/*     */   public static JsonParser createParser(Reader reader) {
/*  95 */     return JsonProvider.provider().createParser(reader);
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
/*     */   public static JsonParser createParser(InputStream in) {
/* 109 */     return JsonProvider.provider().createParser(in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonGenerator createGenerator(Writer writer) {
/* 119 */     return JsonProvider.provider().createGenerator(writer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonGenerator createGenerator(OutputStream out) {
/* 129 */     return JsonProvider.provider().createGenerator(out);
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
/*     */   public static JsonParserFactory createParserFactory(Map<String, ?> config) {
/* 153 */     return JsonProvider.provider().createParserFactory(config);
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
/*     */   public static JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
/* 178 */     return JsonProvider.provider().createGeneratorFactory(config);
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
/*     */   public static JsonWriter createWriter(Writer writer) {
/* 190 */     return JsonProvider.provider().createWriter(writer);
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
/*     */   public static JsonWriter createWriter(OutputStream out) {
/* 203 */     return JsonProvider.provider().createWriter(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonReader createReader(Reader reader) {
/* 213 */     return JsonProvider.provider().createReader(reader);
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
/*     */   public static JsonReader createReader(InputStream in) {
/* 225 */     return JsonProvider.provider().createReader(in);
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
/*     */   public static JsonReaderFactory createReaderFactory(Map<String, ?> config) {
/* 239 */     return JsonProvider.provider().createReaderFactory(config);
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
/*     */   public static JsonWriterFactory createWriterFactory(Map<String, ?> config) {
/* 253 */     return JsonProvider.provider().createWriterFactory(config);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonArrayBuilder createArrayBuilder() {
/* 262 */     return JsonProvider.provider().createArrayBuilder();
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
/*     */   public static JsonArrayBuilder createArrayBuilder(JsonArray array) {
/* 274 */     return JsonProvider.provider().createArrayBuilder(array);
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
/*     */   public static JsonArrayBuilder createArrayBuilder(Collection<?> collection) {
/* 290 */     return JsonProvider.provider().createArrayBuilder(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObjectBuilder createObjectBuilder() {
/* 299 */     return JsonProvider.provider().createObjectBuilder();
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
/*     */   public static JsonObjectBuilder createObjectBuilder(JsonObject object) {
/* 311 */     return JsonProvider.provider().createObjectBuilder(object);
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
/*     */   public static JsonObjectBuilder createObjectBuilder(Map<String, Object> map) {
/* 327 */     return JsonProvider.provider().createObjectBuilder(map);
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
/*     */   public static JsonPointer createPointer(String jsonPointer) {
/* 346 */     return JsonProvider.provider().createPointer(jsonPointer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonPatchBuilder createPatchBuilder() {
/* 357 */     return JsonProvider.provider().createPatchBuilder();
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
/*     */   public static JsonPatchBuilder createPatchBuilder(JsonArray array) {
/* 371 */     return JsonProvider.provider().createPatchBuilder(array);
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
/*     */   public static JsonPatch createPatch(JsonArray array) {
/* 384 */     return JsonProvider.provider().createPatch(array);
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
/*     */   public static JsonPatch createDiff(JsonStructure source, JsonStructure target) {
/* 399 */     return JsonProvider.provider().createDiff(source, target);
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
/*     */   public static JsonMergePatch createMergePatch(JsonValue patch) {
/* 412 */     return JsonProvider.provider().createMergePatch(patch);
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
/*     */   public static JsonMergePatch createMergeDiff(JsonValue source, JsonValue target) {
/* 427 */     return JsonProvider.provider().createMergeDiff(source, target);
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
/*     */   public static JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
/* 443 */     return JsonProvider.provider().createBuilderFactory(config);
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
/*     */   public static JsonString createValue(String value) {
/* 455 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static JsonNumber createValue(int value) {
/* 467 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static JsonNumber createValue(long value) {
/* 479 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static JsonNumber createValue(double value) {
/* 491 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static JsonNumber createValue(BigDecimal value) {
/* 503 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static JsonNumber createValue(BigInteger value) {
/* 515 */     return JsonProvider.provider().createValue(value);
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
/*     */   public static String encodePointer(String pointer) {
/* 528 */     return pointer.replace("~", "~0").replace("/", "~1");
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
/*     */   public static String decodePointer(String escaped) {
/* 541 */     return escaped.replace("~1", "/").replace("~0", "~");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/Json.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */