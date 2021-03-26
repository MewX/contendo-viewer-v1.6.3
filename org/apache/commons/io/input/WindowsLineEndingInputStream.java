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
/*     */ public class WindowsLineEndingInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private boolean slashRSeen = false;
/*     */   private boolean slashNSeen = false;
/*     */   private boolean injectSlashN = false;
/*     */   private boolean eofSeen = false;
/*     */   private final InputStream target;
/*     */   private final boolean ensureLineFeedAtEndOfFile;
/*     */   
/*     */   public WindowsLineEndingInputStream(InputStream in, boolean ensureLineFeedAtEndOfFile) {
/*  48 */     this.target = in;
/*  49 */     this.ensureLineFeedAtEndOfFile = ensureLineFeedAtEndOfFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int readWithUpdate() throws IOException {
/*  58 */     int target = this.target.read();
/*  59 */     this.eofSeen = (target == -1);
/*  60 */     if (this.eofSeen) {
/*  61 */       return target;
/*     */     }
/*  63 */     this.slashRSeen = (target == 13);
/*  64 */     this.slashNSeen = (target == 10);
/*  65 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  73 */     if (this.eofSeen)
/*  74 */       return eofGame(); 
/*  75 */     if (this.injectSlashN) {
/*  76 */       this.injectSlashN = false;
/*  77 */       return 10;
/*     */     } 
/*  79 */     boolean prevWasSlashR = this.slashRSeen;
/*  80 */     int target = readWithUpdate();
/*  81 */     if (this.eofSeen) {
/*  82 */       return eofGame();
/*     */     }
/*  84 */     if (target == 10 && 
/*  85 */       !prevWasSlashR) {
/*     */       
/*  87 */       this.injectSlashN = true;
/*  88 */       return 13;
/*     */     } 
/*     */     
/*  91 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int eofGame() {
/* 101 */     if (!this.ensureLineFeedAtEndOfFile) {
/* 102 */       return -1;
/*     */     }
/* 104 */     if (!this.slashNSeen && !this.slashRSeen) {
/* 105 */       this.slashRSeen = true;
/* 106 */       return 13;
/*     */     } 
/* 108 */     if (!this.slashNSeen) {
/* 109 */       this.slashRSeen = false;
/* 110 */       this.slashNSeen = true;
/* 111 */       return 10;
/*     */     } 
/* 113 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 123 */     super.close();
/* 124 */     this.target.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 132 */     throw new UnsupportedOperationException("Mark not supported");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/WindowsLineEndingInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */