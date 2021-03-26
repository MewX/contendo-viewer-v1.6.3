/*     */ package org.apache.batik.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.util.EncodingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StreamNormalizingReader
/*     */   extends NormalizingReader
/*     */ {
/*     */   protected CharDecoder charDecoder;
/*  46 */   protected int nextChar = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected int line = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int column;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamNormalizingReader(InputStream is) throws IOException {
/*  64 */     this(is, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamNormalizingReader(InputStream is, String enc) throws IOException {
/*  75 */     if (enc == null) {
/*  76 */       enc = "ISO-8859-1";
/*     */     }
/*  78 */     this.charDecoder = createCharDecoder(is, enc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamNormalizingReader(Reader r) throws IOException {
/*  86 */     this.charDecoder = new GenericDecoder(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StreamNormalizingReader() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 101 */     int c, result = this.nextChar;
/* 102 */     if (result != -1) {
/* 103 */       this.nextChar = -1;
/* 104 */       if (result == 13) {
/* 105 */         this.column = 0;
/* 106 */         this.line++;
/*     */       } else {
/* 108 */         this.column++;
/*     */       } 
/* 110 */       return result;
/*     */     } 
/* 112 */     result = this.charDecoder.readChar();
/* 113 */     switch (result) {
/*     */       case 13:
/* 115 */         this.column = 0;
/* 116 */         this.line++;
/* 117 */         c = this.charDecoder.readChar();
/* 118 */         if (c == 10) {
/* 119 */           return 10;
/*     */         }
/* 121 */         this.nextChar = c;
/* 122 */         return 10;
/*     */       
/*     */       case 10:
/* 125 */         this.column = 0;
/* 126 */         this.line++; break;
/*     */     } 
/* 128 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/* 135 */     return this.line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumn() {
/* 142 */     return this.column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 149 */     this.charDecoder.dispose();
/* 150 */     this.charDecoder = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CharDecoder createCharDecoder(InputStream is, String enc) throws IOException {
/* 158 */     CharDecoderFactory cdf = (CharDecoderFactory)charDecoderFactories.get(enc.toUpperCase());
/*     */     
/* 160 */     if (cdf != null) {
/* 161 */       return cdf.createCharDecoder(is);
/*     */     }
/* 163 */     String e = EncodingUtilities.javaEncoding(enc);
/* 164 */     if (e == null) {
/* 165 */       e = enc;
/*     */     }
/* 167 */     return new GenericDecoder(is, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   protected static final Map charDecoderFactories = new HashMap<Object, Object>(11);
/*     */   static {
/* 175 */     CharDecoderFactory cdf = new ASCIIDecoderFactory();
/* 176 */     charDecoderFactories.put("ASCII", cdf);
/* 177 */     charDecoderFactories.put("US-ASCII", cdf);
/* 178 */     charDecoderFactories.put("ISO-8859-1", new ISO_8859_1DecoderFactory());
/* 179 */     charDecoderFactories.put("UTF-8", new UTF8DecoderFactory());
/* 180 */     charDecoderFactories.put("UTF-16", new UTF16DecoderFactory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static interface CharDecoderFactory
/*     */   {
/*     */     CharDecoder createCharDecoder(InputStream param1InputStream) throws IOException;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ASCIIDecoderFactory
/*     */     implements CharDecoderFactory
/*     */   {
/*     */     public CharDecoder createCharDecoder(InputStream is) throws IOException {
/* 197 */       return new ASCIIDecoder(is);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class ISO_8859_1DecoderFactory
/*     */     implements CharDecoderFactory
/*     */   {
/*     */     public CharDecoder createCharDecoder(InputStream is) throws IOException {
/* 208 */       return new ISO_8859_1Decoder(is);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class UTF8DecoderFactory
/*     */     implements CharDecoderFactory
/*     */   {
/*     */     public CharDecoder createCharDecoder(InputStream is) throws IOException {
/* 219 */       return new UTF8Decoder(is);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class UTF16DecoderFactory
/*     */     implements CharDecoderFactory
/*     */   {
/*     */     public CharDecoder createCharDecoder(InputStream is) throws IOException {
/* 230 */       return new UTF16Decoder(is);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/StreamNormalizingReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */