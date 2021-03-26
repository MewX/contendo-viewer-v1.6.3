/*    */ package org.a.a;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import javax.json.JsonArray;
/*    */ import javax.json.JsonObject;
/*    */ import javax.json.stream.JsonParser;
/*    */ import javax.json.stream.JsonParserFactory;
/*    */ import org.a.a.a.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class k
/*    */   implements JsonParserFactory
/*    */ {
/* 59 */   private final Map<String, ?> a = Collections.emptyMap();
/*    */   private final a b;
/*    */   
/*    */   k(a bufferPool) {
/* 63 */     this.b = bufferPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonParser createParser(Reader reader) {
/* 68 */     return new l(reader, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonParser createParser(InputStream in) {
/* 73 */     return new l(in, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonParser createParser(InputStream in, Charset charset) {
/* 78 */     return new l(in, charset, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonParser createParser(JsonArray array) {
/* 83 */     return new u(array);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, ?> getConfigInUse() {
/* 88 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonParser createParser(JsonObject object) {
/* 93 */     return new u(object);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */