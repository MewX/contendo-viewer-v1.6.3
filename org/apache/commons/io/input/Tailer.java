/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tailer
/*     */   implements Runnable
/*     */ {
/*     */   private static final int DEFAULT_DELAY_MILLIS = 1000;
/*     */   private static final String RAF_MODE = "r";
/*     */   private static final int DEFAULT_BUFSIZE = 4096;
/* 129 */   private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final byte[] inbuf;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final File file;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Charset cset;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final long delayMillis;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean end;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final TailerListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean reOpen;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile boolean run = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tailer(File file, TailerListener listener) {
/* 177 */     this(file, listener, 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tailer(File file, TailerListener listener, long delayMillis) {
/* 187 */     this(file, listener, delayMillis, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tailer(File file, TailerListener listener, long delayMillis, boolean end) {
/* 198 */     this(file, listener, delayMillis, end, 4096);
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
/*     */   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
/* 211 */     this(file, listener, delayMillis, end, reOpen, 4096);
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
/*     */   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, int bufSize) {
/* 224 */     this(file, listener, delayMillis, end, false, bufSize);
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
/*     */   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
/* 238 */     this(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
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
/*     */   public Tailer(File file, Charset cset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
/* 254 */     this.file = file;
/* 255 */     this.delayMillis = delayMillis;
/* 256 */     this.end = end;
/*     */     
/* 258 */     this.inbuf = new byte[bufSize];
/*     */ 
/*     */     
/* 261 */     this.listener = listener;
/* 262 */     listener.init(this);
/* 263 */     this.reOpen = reOpen;
/* 264 */     this.cset = cset;
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
/*     */   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, int bufSize) {
/* 279 */     return create(file, listener, delayMillis, end, false, bufSize);
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
/*     */   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
/* 296 */     return create(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
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
/*     */   public static Tailer create(File file, Charset charset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
/* 314 */     Tailer tailer = new Tailer(file, charset, listener, delayMillis, end, reOpen, bufSize);
/* 315 */     Thread thread = new Thread(tailer);
/* 316 */     thread.setDaemon(true);
/* 317 */     thread.start();
/* 318 */     return tailer;
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
/*     */   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end) {
/* 332 */     return create(file, listener, delayMillis, end, 4096);
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
/*     */   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
/* 347 */     return create(file, listener, delayMillis, end, reOpen, 4096);
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
/*     */   public static Tailer create(File file, TailerListener listener, long delayMillis) {
/* 359 */     return create(file, listener, delayMillis, false);
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
/*     */   public static Tailer create(File file, TailerListener listener) {
/* 371 */     return create(file, listener, 1000L, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 380 */     return this.file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean getRun() {
/* 390 */     return this.run;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getDelay() {
/* 399 */     return this.delayMillis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 407 */     RandomAccessFile reader = null;
/*     */     try {
/* 409 */       long last = 0L;
/* 410 */       long position = 0L;
/*     */       
/* 412 */       while (getRun() && reader == null) {
/*     */         try {
/* 414 */           reader = new RandomAccessFile(this.file, "r");
/* 415 */         } catch (FileNotFoundException e) {
/* 416 */           this.listener.fileNotFound();
/*     */         } 
/* 418 */         if (reader == null) {
/* 419 */           Thread.sleep(this.delayMillis);
/*     */           continue;
/*     */         } 
/* 422 */         position = this.end ? this.file.length() : 0L;
/* 423 */         last = this.file.lastModified();
/* 424 */         reader.seek(position);
/*     */       } 
/*     */       
/* 427 */       while (getRun()) {
/* 428 */         boolean newer = FileUtils.isFileNewer(this.file, last);
/*     */         
/* 430 */         long length = this.file.length();
/* 431 */         if (length < position) {
/*     */           
/* 433 */           this.listener.fileRotated();
/*     */ 
/*     */           
/* 436 */           try (RandomAccessFile save = reader) {
/* 437 */             reader = new RandomAccessFile(this.file, "r");
/*     */ 
/*     */             
/*     */             try {
/* 441 */               readLines(save);
/* 442 */             } catch (IOException ioe) {
/* 443 */               this.listener.handle(ioe);
/*     */             } 
/* 445 */             position = 0L;
/* 446 */           } catch (FileNotFoundException e) {
/*     */             
/* 448 */             this.listener.fileNotFound();
/* 449 */             Thread.sleep(this.delayMillis);
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 455 */         if (length > position) {
/*     */           
/* 457 */           position = readLines(reader);
/* 458 */           last = this.file.lastModified();
/* 459 */         } else if (newer) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 464 */           position = 0L;
/* 465 */           reader.seek(position);
/*     */ 
/*     */           
/* 468 */           position = readLines(reader);
/* 469 */           last = this.file.lastModified();
/*     */         } 
/*     */         
/* 472 */         if (this.reOpen && reader != null) {
/* 473 */           reader.close();
/*     */         }
/* 475 */         Thread.sleep(this.delayMillis);
/* 476 */         if (getRun() && this.reOpen) {
/* 477 */           reader = new RandomAccessFile(this.file, "r");
/* 478 */           reader.seek(position);
/*     */         } 
/*     */       } 
/* 481 */     } catch (InterruptedException e) {
/* 482 */       Thread.currentThread().interrupt();
/* 483 */       this.listener.handle(e);
/* 484 */     } catch (Exception e) {
/* 485 */       this.listener.handle(e);
/*     */     } finally {
/*     */       try {
/* 488 */         if (reader != null) {
/* 489 */           reader.close();
/*     */         }
/*     */       }
/* 492 */       catch (IOException e) {
/* 493 */         this.listener.handle(e);
/*     */       } 
/* 495 */       stop();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 503 */     this.run = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long readLines(RandomAccessFile reader) throws IOException {
/* 514 */     try (ByteArrayOutputStream lineBuf = new ByteArrayOutputStream(64)) {
/* 515 */       long pos = reader.getFilePointer();
/* 516 */       long rePos = pos;
/*     */       
/* 518 */       boolean seenCR = false; int num;
/* 519 */       while (getRun() && (num = reader.read(this.inbuf)) != -1) {
/* 520 */         for (int i = 0; i < num; i++) {
/* 521 */           byte ch = this.inbuf[i];
/* 522 */           switch (ch) {
/*     */             case 10:
/* 524 */               seenCR = false;
/* 525 */               this.listener.handle(new String(lineBuf.toByteArray(), this.cset));
/* 526 */               lineBuf.reset();
/* 527 */               rePos = pos + i + 1L;
/*     */               break;
/*     */             case 13:
/* 530 */               if (seenCR) {
/* 531 */                 lineBuf.write(13);
/*     */               }
/* 533 */               seenCR = true;
/*     */               break;
/*     */             default:
/* 536 */               if (seenCR) {
/* 537 */                 seenCR = false;
/* 538 */                 this.listener.handle(new String(lineBuf.toByteArray(), this.cset));
/* 539 */                 lineBuf.reset();
/* 540 */                 rePos = pos + i + 1L;
/*     */               } 
/* 542 */               lineBuf.write(ch); break;
/*     */           } 
/*     */         } 
/* 545 */         pos = reader.getFilePointer();
/*     */       } 
/*     */       
/* 548 */       reader.seek(rePos);
/*     */       
/* 550 */       if (this.listener instanceof TailerListenerAdapter) {
/* 551 */         ((TailerListenerAdapter)this.listener).endOfFileReached();
/*     */       }
/*     */       
/* 554 */       return rePos;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/Tailer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */