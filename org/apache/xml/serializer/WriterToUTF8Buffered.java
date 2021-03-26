/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ public final class WriterToUTF8Buffered
/*     */   extends Writer
/*     */ {
/*     */   private static final int BYTES_MAX = 16384;
/*     */   private static final int CHARS_MAX = 5461;
/*     */   private final OutputStream m_os;
/*     */   private final byte[] m_outputBytes;
/*     */   private final char[] m_inputChars;
/*     */   private int count;
/*     */   
/*     */   public WriterToUTF8Buffered(OutputStream out) throws UnsupportedEncodingException {
/*  79 */     this.m_os = out;
/*     */ 
/*     */     
/*  82 */     this.m_outputBytes = new byte[16387];
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.m_inputChars = new char[5462];
/*  87 */     this.count = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 134 */     if (this.count >= 16384) {
/* 135 */       flushBuffer();
/*     */     }
/* 137 */     if (c < 128) {
/*     */       
/* 139 */       this.m_outputBytes[this.count++] = (byte)c;
/*     */     }
/* 141 */     else if (c < 2048) {
/*     */       
/* 143 */       this.m_outputBytes[this.count++] = (byte)(192 + (c >> 6));
/* 144 */       this.m_outputBytes[this.count++] = (byte)(128 + (c & 0x3F));
/*     */     }
/*     */     else {
/*     */       
/* 148 */       this.m_outputBytes[this.count++] = (byte)(224 + (c >> 12));
/* 149 */       this.m_outputBytes[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
/* 150 */       this.m_outputBytes[this.count++] = (byte)(128 + (c & 0x3F));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(char[] chars, int start, int length) throws IOException {
/* 174 */     int lengthx3 = 3 * length;
/*     */     
/* 176 */     if (lengthx3 >= 16384 - this.count) {
/*     */ 
/*     */       
/* 179 */       flushBuffer();
/*     */       
/* 181 */       if (lengthx3 >= 16384) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 189 */         int chunks = 1 + length / 5461;
/* 190 */         for (int chunk = 0; chunk < chunks; chunk++) {
/*     */           
/* 192 */           int start_chunk = start + length * chunk / chunks;
/* 193 */           int end_chunk = start + length * (chunk + 1) / chunks;
/* 194 */           int len_chunk = end_chunk - start_chunk;
/* 195 */           write(chars, start_chunk, len_chunk);
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     int n = length + start;
/* 204 */     byte[] buf_loc = this.m_outputBytes;
/* 205 */     int count_loc = this.count;
/* 206 */     int i = start;
/*     */ 
/*     */ 
/*     */     
/*     */     char c;
/*     */ 
/*     */ 
/*     */     
/* 214 */     for (; i < n && (c = chars[i]) < ''; i++) {
/* 215 */       buf_loc[count_loc++] = (byte)c;
/*     */     }
/* 217 */     for (; i < n; i++) {
/*     */ 
/*     */       
/* 220 */       c = chars[i];
/*     */       
/* 222 */       if (c < '') {
/* 223 */         buf_loc[count_loc++] = (byte)c;
/* 224 */       } else if (c < 'ࠀ') {
/*     */         
/* 226 */         buf_loc[count_loc++] = (byte)(192 + (c >> 6));
/* 227 */         buf_loc[count_loc++] = (byte)(128 + (c & 0x3F));
/*     */       }
/*     */       else {
/*     */         
/* 231 */         buf_loc[count_loc++] = (byte)(224 + (c >> 12));
/* 232 */         buf_loc[count_loc++] = (byte)(128 + (c >> 6 & 0x3F));
/* 233 */         buf_loc[count_loc++] = (byte)(128 + (c & 0x3F));
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     this.count = count_loc;
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
/*     */   private void directWrite(char[] chars, int start, int length) throws IOException {
/* 254 */     if (length >= 16384 - this.count) {
/*     */ 
/*     */       
/* 257 */       flushBuffer();
/*     */       
/* 259 */       if (length >= 16384) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 267 */         int chunks = 1 + length / 5461;
/* 268 */         for (int chunk = 0; chunk < chunks; chunk++) {
/*     */           
/* 270 */           int start_chunk = start + length * chunk / chunks;
/* 271 */           int end_chunk = start + length * (chunk + 1) / chunks;
/* 272 */           int len_chunk = end_chunk - start_chunk;
/* 273 */           directWrite(chars, start_chunk, len_chunk);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 279 */     int n = length + start;
/* 280 */     byte[] buf_loc = this.m_outputBytes;
/* 281 */     int count_loc = this.count;
/* 282 */     for (int i = start; i < n; i++) {
/* 283 */       buf_loc[count_loc++] = buf_loc[i];
/*     */     }
/* 285 */     this.count = count_loc;
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
/*     */   public void write(String s) throws IOException {
/* 301 */     int length = s.length();
/* 302 */     int lengthx3 = 3 * length;
/*     */     
/* 304 */     if (lengthx3 >= 16384 - this.count) {
/*     */ 
/*     */       
/* 307 */       flushBuffer();
/*     */       
/* 309 */       if (lengthx3 >= 16384) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 315 */         int start = 0;
/* 316 */         int chunks = 1 + length / 5461;
/* 317 */         for (int chunk = 0; chunk < chunks; chunk++) {
/*     */           
/* 319 */           int start_chunk = 0 + length * chunk / chunks;
/* 320 */           int end_chunk = 0 + length * (chunk + 1) / chunks;
/* 321 */           int len_chunk = end_chunk - start_chunk;
/* 322 */           s.getChars(start_chunk, end_chunk, this.m_inputChars, 0);
/* 323 */           write(this.m_inputChars, 0, len_chunk);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     s.getChars(0, length, this.m_inputChars, 0);
/* 331 */     char[] chars = this.m_inputChars;
/* 332 */     int n = length;
/* 333 */     byte[] buf_loc = this.m_outputBytes;
/* 334 */     int count_loc = this.count;
/* 335 */     int i = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     char c;
/*     */ 
/*     */ 
/*     */     
/* 343 */     for (; i < n && (c = chars[i]) < ''; i++) {
/* 344 */       buf_loc[count_loc++] = (byte)c;
/*     */     }
/* 346 */     for (; i < n; i++) {
/*     */ 
/*     */       
/* 349 */       c = chars[i];
/*     */       
/* 351 */       if (c < '') {
/* 352 */         buf_loc[count_loc++] = (byte)c;
/* 353 */       } else if (c < 'ࠀ') {
/*     */         
/* 355 */         buf_loc[count_loc++] = (byte)(192 + (c >> 6));
/* 356 */         buf_loc[count_loc++] = (byte)(128 + (c & 0x3F));
/*     */       }
/*     */       else {
/*     */         
/* 360 */         buf_loc[count_loc++] = (byte)(224 + (c >> 12));
/* 361 */         buf_loc[count_loc++] = (byte)(128 + (c >> 6 & 0x3F));
/* 362 */         buf_loc[count_loc++] = (byte)(128 + (c & 0x3F));
/*     */       } 
/*     */     } 
/*     */     
/* 366 */     this.count = count_loc;
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
/*     */   public void flushBuffer() throws IOException {
/* 378 */     if (this.count > 0) {
/*     */       
/* 380 */       this.m_os.write(this.m_outputBytes, 0, this.count);
/*     */       
/* 382 */       this.count = 0;
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
/*     */   public void flush() throws IOException {
/* 399 */     flushBuffer();
/* 400 */     this.m_os.flush();
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
/*     */   public void close() throws IOException {
/* 414 */     flushBuffer();
/* 415 */     this.m_os.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/* 426 */     return this.m_os;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void directWrite(String s) throws IOException {
/* 437 */     int length = s.length();
/*     */     
/* 439 */     if (length >= 16384 - this.count) {
/*     */ 
/*     */       
/* 442 */       flushBuffer();
/*     */       
/* 444 */       if (length >= 16384) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 452 */         int start = 0;
/* 453 */         int chunks = 1 + length / 5461;
/* 454 */         for (int chunk = 0; chunk < chunks; chunk++) {
/*     */           
/* 456 */           int start_chunk = 0 + length * chunk / chunks;
/* 457 */           int end_chunk = 0 + length * (chunk + 1) / chunks;
/* 458 */           int len_chunk = end_chunk - start_chunk;
/* 459 */           s.getChars(start_chunk, end_chunk, this.m_inputChars, 0);
/* 460 */           directWrite(this.m_inputChars, 0, len_chunk);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 467 */     s.getChars(0, length, this.m_inputChars, 0);
/* 468 */     char[] chars = this.m_inputChars;
/* 469 */     byte[] buf_loc = this.m_outputBytes;
/* 470 */     int count_loc = this.count;
/* 471 */     int i = 0;
/* 472 */     while (i < length) {
/* 473 */       buf_loc[count_loc++] = (byte)chars[i++];
/*     */     }
/*     */ 
/*     */     
/* 477 */     this.count = count_loc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/WriterToUTF8Buffered.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */