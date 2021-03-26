/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.Charset;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonReader;
/*     */ import javax.json.JsonStructure;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonParser;
/*     */ import javax.json.stream.JsonParsingException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class s
/*     */   implements JsonReader
/*     */ {
/*     */   private final l a;
/*     */   private boolean b;
/*     */   private final a c;
/*     */   
/*     */   s(Reader reader, a bufferPool) {
/*  68 */     this.a = new l(reader, bufferPool);
/*  69 */     this.c = bufferPool;
/*     */   }
/*     */   
/*     */   s(InputStream in, a bufferPool) {
/*  73 */     this.a = new l(in, bufferPool);
/*  74 */     this.c = bufferPool;
/*     */   }
/*     */   
/*     */   s(InputStream in, Charset charset, a bufferPool) {
/*  78 */     this.a = new l(in, charset, bufferPool);
/*  79 */     this.c = bufferPool;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonStructure read() {
/*  84 */     if (this.b) {
/*  85 */       throw new IllegalStateException(h.n());
/*     */     }
/*  87 */     this.b = true;
/*  88 */     if (this.a.hasNext()) {
/*     */       try {
/*  90 */         JsonParser.Event e = this.a.next();
/*  91 */         if (e == JsonParser.Event.START_ARRAY)
/*  92 */           return (JsonStructure)this.a.getArray(); 
/*  93 */         if (e == JsonParser.Event.START_OBJECT) {
/*  94 */           return (JsonStructure)this.a.getObject();
/*     */         }
/*  96 */       } catch (IllegalStateException ise) {
/*  97 */         throw new JsonParsingException(ise.getMessage(), ise, this.a.c());
/*     */       } 
/*     */     }
/* 100 */     throw new JsonException(h.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObject readObject() {
/* 105 */     if (this.b) {
/* 106 */       throw new IllegalStateException(h.n());
/*     */     }
/* 108 */     this.b = true;
/* 109 */     if (this.a.hasNext()) {
/*     */       try {
/* 111 */         this.a.next();
/* 112 */         return this.a.getObject();
/* 113 */       } catch (IllegalStateException ise) {
/* 114 */         throw new JsonParsingException(ise.getMessage(), ise, this.a.c());
/*     */       } 
/*     */     }
/* 117 */     throw new JsonException(h.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArray readArray() {
/* 122 */     if (this.b) {
/* 123 */       throw new IllegalStateException(h.n());
/*     */     }
/* 125 */     this.b = true;
/* 126 */     if (this.a.hasNext()) {
/*     */       try {
/* 128 */         this.a.next();
/* 129 */         return this.a.getArray();
/* 130 */       } catch (IllegalStateException ise) {
/* 131 */         throw new JsonParsingException(ise.getMessage(), ise, this.a.c());
/*     */       } 
/*     */     }
/* 134 */     throw new JsonException(h.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue readValue() {
/* 139 */     if (this.b) {
/* 140 */       throw new IllegalStateException(h.n());
/*     */     }
/* 142 */     this.b = true;
/* 143 */     if (this.a.hasNext()) {
/*     */       try {
/* 145 */         this.a.next();
/* 146 */         return this.a.getValue();
/* 147 */       } catch (IllegalStateException ise) {
/* 148 */         throw new JsonParsingException(ise.getMessage(), ise, this.a.c());
/*     */       } 
/*     */     }
/* 151 */     throw new JsonException(h.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 156 */     this.b = true;
/* 157 */     this.a.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/s.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */