/*    */ package org.a.a;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.Writer;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Map;
/*    */ import javax.json.stream.JsonGenerator;
/*    */ import javax.json.stream.JsonGeneratorFactory;
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
/*    */ 
/*    */ class d
/*    */   implements JsonGeneratorFactory
/*    */ {
/*    */   private final boolean a;
/*    */   private final Map<String, ?> b;
/*    */   private final a c;
/*    */   
/*    */   d(Map<String, ?> config, boolean prettyPrinting, a bufferPool) {
/* 63 */     this.b = config;
/* 64 */     this.a = prettyPrinting;
/* 65 */     this.c = bufferPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonGenerator createGenerator(Writer writer) {
/* 70 */     return this.a ? 
/* 71 */       new p(writer, this.c) : 
/* 72 */       new e(writer, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonGenerator createGenerator(OutputStream out) {
/* 77 */     return this.a ? 
/* 78 */       new p(out, this.c) : 
/* 79 */       new e(out, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonGenerator createGenerator(OutputStream out, Charset charset) {
/* 84 */     return this.a ? 
/* 85 */       new p(out, charset, this.c) : 
/* 86 */       new e(out, charset, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, ?> getConfigInUse() {
/* 91 */     return this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */