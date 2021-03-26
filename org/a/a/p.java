/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.charset.Charset;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonGenerator;
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
/*     */ 
/*     */ public class p
/*     */   extends e
/*     */ {
/*     */   private int a;
/*     */   private static final String b = "    ";
/*     */   
/*     */   public p(Writer writer, a bufferPool) {
/*  58 */     super(writer, bufferPool);
/*     */   }
/*     */   
/*     */   public p(OutputStream out, a bufferPool) {
/*  62 */     super(out, bufferPool);
/*     */   }
/*     */   
/*     */   public p(OutputStream out, Charset encoding, a bufferPool) {
/*  66 */     super(out, encoding, bufferPool);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartObject() {
/*  71 */     super.writeStartObject();
/*  72 */     this.a++;
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartObject(String name) {
/*  78 */     super.writeStartObject(name);
/*  79 */     this.a++;
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartArray() {
/*  85 */     super.writeStartArray();
/*  86 */     this.a++;
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartArray(String name) {
/*  92 */     super.writeStartArray(name);
/*  93 */     this.a++;
/*  94 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeEnd() {
/*  99 */     e();
/* 100 */     this.a--;
/* 101 */     d();
/* 102 */     super.writeEnd();
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   private void d() {
/* 107 */     for (int i = 0; i < this.a; i++) {
/* 108 */       b("    ");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a() {
/* 114 */     super.a();
/* 115 */     a('\n');
/* 116 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b() {
/* 121 */     super.b();
/* 122 */     a(' ');
/*     */   }
/*     */   
/*     */   private void e() {
/* 126 */     a('\n');
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */