/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlyfTable
/*     */   implements Table
/*     */ {
/*  31 */   private byte[] buf = null;
/*     */   private GlyfDescript[] descript;
/*     */   
/*     */   protected GlyfTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  35 */     raf.seek(de.getOffset());
/*  36 */     this.buf = new byte[de.getLength()];
/*  37 */     raf.read(this.buf);
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
/*     */   public void init(int numGlyphs, LocaTable loca) {
/*  71 */     if (this.buf == null) {
/*     */       return;
/*     */     }
/*  74 */     this.descript = new GlyfDescript[numGlyphs];
/*  75 */     ByteArrayInputStream bais = new ByteArrayInputStream(this.buf); int i;
/*  76 */     for (i = 0; i < numGlyphs; i++) {
/*  77 */       int len = loca.getOffset(i + 1) - loca.getOffset(i);
/*  78 */       if (len > 0) {
/*  79 */         bais.reset();
/*  80 */         bais.skip(loca.getOffset(i));
/*  81 */         short numberOfContours = (short)(bais.read() << 8 | bais.read());
/*  82 */         if (numberOfContours >= 0) {
/*  83 */           this.descript[i] = new GlyfSimpleDescript(this, numberOfContours, bais);
/*     */         } else {
/*  85 */           this.descript[i] = new GlyfCompositeDescript(this, bais);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     this.buf = null;
/*     */     
/*  92 */     for (i = 0; i < numGlyphs; i++) {
/*  93 */       if (this.descript[i] != null)
/*     */       {
/*  95 */         this.descript[i].resolve(); } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public GlyfDescript getDescription(int i) {
/* 100 */     return this.descript[i];
/*     */   }
/*     */   
/*     */   public int getType() {
/* 104 */     return 1735162214;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyfTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */