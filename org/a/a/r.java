/*    */ package org.a.a;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import javax.json.JsonReader;
/*    */ import javax.json.JsonReaderFactory;
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
/*    */ class r
/*    */   implements JsonReaderFactory
/*    */ {
/* 57 */   private final Map<String, ?> a = Collections.emptyMap();
/*    */   private final a b;
/*    */   
/*    */   r(a bufferPool) {
/* 61 */     this.b = bufferPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonReader createReader(Reader reader) {
/* 66 */     return new s(reader, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonReader createReader(InputStream in) {
/* 71 */     return new s(in, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonReader createReader(InputStream in, Charset charset) {
/* 76 */     return new s(in, charset, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, ?> getConfigInUse() {
/* 81 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */