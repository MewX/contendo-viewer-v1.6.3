/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerializerTraceWriter
/*     */   extends Writer
/*     */ {
/*     */   private final Writer m_writer;
/*     */   private final SerializerTrace m_tracer;
/*     */   private int buf_length;
/*     */   private byte[] buf;
/*     */   private int count;
/*     */   
/*     */   private void setBufferSize(int size) {
/*  77 */     this.buf = new byte[size + 3];
/*  78 */     this.buf_length = size;
/*  79 */     this.count = 0;
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
/*     */   public SerializerTraceWriter(Writer out, SerializerTrace tracer) {
/*  95 */     this.m_writer = out;
/*  96 */     this.m_tracer = tracer;
/*  97 */     setBufferSize(1024);
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
/*     */   private void flushBuffer() throws IOException {
/* 112 */     if (this.count > 0) {
/*     */       
/* 114 */       char[] chars = new char[this.count];
/* 115 */       for (int i = 0; i < this.count; i++) {
/* 116 */         chars[i] = (char)this.buf[i];
/*     */       }
/* 118 */       if (this.m_tracer != null) {
/* 119 */         this.m_tracer.fireGenerateEvent(12, chars, 0, chars.length);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       this.count = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 136 */     if (this.m_writer != null) {
/* 137 */       this.m_writer.flush();
/*     */     }
/*     */     
/* 140 */     flushBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 150 */     if (this.m_writer != null) {
/* 151 */       this.m_writer.close();
/*     */     }
/*     */     
/* 154 */     flushBuffer();
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
/*     */   public void write(int c) throws IOException {
/* 172 */     if (this.m_writer != null) {
/* 173 */       this.m_writer.write(c);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (this.count >= this.buf_length) {
/* 181 */       flushBuffer();
/*     */     }
/* 183 */     if (c < 128) {
/*     */       
/* 185 */       this.buf[this.count++] = (byte)c;
/*     */     }
/* 187 */     else if (c < 2048) {
/*     */       
/* 189 */       this.buf[this.count++] = (byte)(192 + (c >> 6));
/* 190 */       this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */     }
/*     */     else {
/*     */       
/* 194 */       this.buf[this.count++] = (byte)(224 + (c >> 12));
/* 195 */       this.buf[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
/* 196 */       this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */     } 
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
/*     */   public void write(char[] chars, int start, int length) throws IOException {
/* 215 */     if (this.m_writer != null) {
/* 216 */       this.m_writer.write(chars, start, length);
/*     */     }
/*     */     
/* 219 */     int lengthx3 = (length << 1) + length;
/*     */     
/* 221 */     if (lengthx3 >= this.buf_length) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       flushBuffer();
/* 229 */       setBufferSize(2 * lengthx3);
/*     */     } 
/*     */ 
/*     */     
/* 233 */     if (lengthx3 > this.buf_length - this.count)
/*     */     {
/* 235 */       flushBuffer();
/*     */     }
/*     */     
/* 238 */     int n = length + start;
/* 239 */     for (int i = start; i < n; i++) {
/*     */       
/* 241 */       char c = chars[i];
/*     */       
/* 243 */       if (c < '') {
/* 244 */         this.buf[this.count++] = (byte)c;
/* 245 */       } else if (c < 'ࠀ') {
/*     */         
/* 247 */         this.buf[this.count++] = (byte)(192 + (c >> 6));
/* 248 */         this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */       }
/*     */       else {
/*     */         
/* 252 */         this.buf[this.count++] = (byte)(224 + (c >> 12));
/* 253 */         this.buf[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
/* 254 */         this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */       } 
/*     */     } 
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
/*     */   public void write(String s) throws IOException {
/* 270 */     if (this.m_writer != null) {
/* 271 */       this.m_writer.write(s);
/*     */     }
/*     */     
/* 274 */     int length = s.length();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     int lengthx3 = (length << 1) + length;
/*     */     
/* 282 */     if (lengthx3 >= this.buf_length) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 289 */       flushBuffer();
/* 290 */       setBufferSize(2 * lengthx3);
/*     */     } 
/*     */     
/* 293 */     if (lengthx3 > this.buf_length - this.count)
/*     */     {
/* 295 */       flushBuffer();
/*     */     }
/*     */     
/* 298 */     for (int i = 0; i < length; i++) {
/*     */       
/* 300 */       char c = s.charAt(i);
/*     */       
/* 302 */       if (c < '') {
/* 303 */         this.buf[this.count++] = (byte)c;
/* 304 */       } else if (c < 'ࠀ') {
/*     */         
/* 306 */         this.buf[this.count++] = (byte)(192 + (c >> 6));
/* 307 */         this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */       }
/*     */       else {
/*     */         
/* 311 */         this.buf[this.count++] = (byte)(224 + (c >> 12));
/* 312 */         this.buf[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
/* 313 */         this.buf[this.count++] = (byte)(128 + (c & 0x3F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/SerializerTraceWriter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */