/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Map;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class y
/*     */   implements JsonWriter
/*     */ {
/*     */   private final e a;
/*     */   private boolean b;
/*     */   private final a c;
/*     */   
/*     */   y(Writer writer, a bufferPool) {
/*  66 */     this(writer, false, bufferPool);
/*     */   }
/*     */   
/*     */   y(Writer writer, boolean prettyPrinting, a bufferPool) {
/*  70 */     this
/*     */       
/*  72 */       .a = prettyPrinting ? new p(writer, bufferPool) : new e(writer, bufferPool);
/*  73 */     this.c = null;
/*     */   }
/*     */   
/*     */   y(OutputStream out, a bufferPool) {
/*  77 */     this(out, StandardCharsets.UTF_8, false, bufferPool);
/*     */   }
/*     */   
/*     */   y(OutputStream out, boolean prettyPrinting, a bufferPool) {
/*  81 */     this(out, StandardCharsets.UTF_8, prettyPrinting, bufferPool);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   y(OutputStream out, Charset charset, boolean prettyPrinting, a bufferPool) {
/*  88 */     this.c = new a(out);
/*  89 */     this
/*     */       
/*  91 */       .a = prettyPrinting ? new p(this.c, charset, bufferPool) : new e(this.c, charset, bufferPool);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeArray(JsonArray array) {
/*  96 */     if (this.b) {
/*  97 */       throw new IllegalStateException(h.m());
/*     */     }
/*  99 */     this.b = true;
/* 100 */     this.a.writeStartArray();
/* 101 */     for (JsonValue value : array) {
/* 102 */       this.a.write(value);
/*     */     }
/* 104 */     this.a.writeEnd();
/*     */ 
/*     */     
/* 107 */     this.a.c();
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (this.c != null) {
/* 112 */       this.a.flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeObject(JsonObject object) {
/* 118 */     if (this.b) {
/* 119 */       throw new IllegalStateException(h.m());
/*     */     }
/* 121 */     this.b = true;
/* 122 */     this.a.writeStartObject();
/* 123 */     for (Map.Entry<String, JsonValue> entry : (Iterable<Map.Entry<String, JsonValue>>)object.entrySet()) {
/* 124 */       this.a.write(entry.getKey(), entry.getValue());
/*     */     }
/* 126 */     this.a.writeEnd();
/*     */ 
/*     */     
/* 129 */     this.a.c();
/*     */ 
/*     */ 
/*     */     
/* 133 */     if (this.c != null) {
/* 134 */       this.a.flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(JsonStructure value) {
/* 140 */     if (value instanceof JsonArray) {
/* 141 */       writeArray((JsonArray)value);
/*     */     } else {
/* 143 */       writeObject((JsonObject)value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(JsonValue value) {
/* 149 */     switch (null.a[value.getValueType().ordinal()]) {
/*     */       case 1:
/* 151 */         writeObject((JsonObject)value);
/*     */         return;
/*     */       case 2:
/* 154 */         writeArray((JsonArray)value);
/*     */         return;
/*     */     } 
/* 157 */     if (this.b) {
/* 158 */       throw new IllegalStateException(h.m());
/*     */     }
/* 160 */     this.b = true;
/* 161 */     this.a.write(value);
/* 162 */     this.a.c();
/* 163 */     if (this.c != null) {
/* 164 */       this.a.flush();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 171 */     this.b = true;
/* 172 */     this.a.close();
/*     */   }
/*     */   
/*     */   private static final class a extends FilterOutputStream {
/*     */     public a(OutputStream out) {
/* 177 */       super(out);
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(byte[] b, int off, int len) throws IOException {
/* 182 */       this.out.write(b, off, len);
/*     */     }
/*     */     
/*     */     public void flush() {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */