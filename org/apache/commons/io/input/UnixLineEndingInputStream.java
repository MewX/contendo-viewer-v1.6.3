/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnixLineEndingInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private boolean slashNSeen = false;
/*     */   private boolean slashRSeen = false;
/*     */   private boolean eofSeen = false;
/*     */   private final InputStream target;
/*     */   private final boolean ensureLineFeedAtEndOfFile;
/*     */   
/*     */   public UnixLineEndingInputStream(InputStream in, boolean ensureLineFeedAtEndOfFile) {
/*  47 */     this.target = in;
/*  48 */     this.ensureLineFeedAtEndOfFile = ensureLineFeedAtEndOfFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int readWithUpdate() throws IOException {
/*  57 */     int target = this.target.read();
/*  58 */     this.eofSeen = (target == -1);
/*  59 */     if (this.eofSeen) {
/*  60 */       return target;
/*     */     }
/*  62 */     this.slashNSeen = (target == 10);
/*  63 */     this.slashRSeen = (target == 13);
/*  64 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  72 */     boolean previousWasSlashR = this.slashRSeen;
/*  73 */     if (this.eofSeen) {
/*  74 */       return eofGame(previousWasSlashR);
/*     */     }
/*     */     
/*  77 */     int target = readWithUpdate();
/*  78 */     if (this.eofSeen) {
/*  79 */       return eofGame(previousWasSlashR);
/*     */     }
/*  81 */     if (this.slashRSeen)
/*     */     {
/*  83 */       return 10;
/*     */     }
/*     */     
/*  86 */     if (previousWasSlashR && this.slashNSeen) {
/*  87 */       return read();
/*     */     }
/*     */     
/*  90 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int eofGame(boolean previousWasSlashR) {
/* 100 */     if (previousWasSlashR || !this.ensureLineFeedAtEndOfFile) {
/* 101 */       return -1;
/*     */     }
/* 103 */     if (!this.slashNSeen) {
/* 104 */       this.slashNSeen = true;
/* 105 */       return 10;
/*     */     } 
/* 107 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 117 */     super.close();
/* 118 */     this.target.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 126 */     throw new UnsupportedOperationException("Mark notsupported");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/UnixLineEndingInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */