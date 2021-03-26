/*    */ package org.a.a;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.Writer;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Map;
/*    */ import javax.json.JsonWriter;
/*    */ import javax.json.JsonWriterFactory;
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
/*    */ 
/*    */ class x
/*    */   implements JsonWriterFactory
/*    */ {
/*    */   private final Map<String, ?> a;
/*    */   private final boolean b;
/*    */   private final a c;
/*    */   
/*    */   x(Map<String, ?> config, boolean prettyPrinting, a bufferPool) {
/* 62 */     this.a = config;
/* 63 */     this.b = prettyPrinting;
/* 64 */     this.c = bufferPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonWriter createWriter(Writer writer) {
/* 69 */     return new y(writer, this.b, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonWriter createWriter(OutputStream out) {
/* 74 */     return new y(out, this.b, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonWriter createWriter(OutputStream out, Charset charset) {
/* 79 */     return new y(out, charset, this.b, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, ?> getConfigInUse() {
/* 84 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */