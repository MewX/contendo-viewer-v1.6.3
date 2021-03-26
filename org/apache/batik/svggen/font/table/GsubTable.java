/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
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
/*     */ 
/*     */ public class GsubTable
/*     */   implements LookupSubtableFactory, Table
/*     */ {
/*     */   private ScriptList scriptList;
/*     */   private FeatureList featureList;
/*     */   private LookupList lookupList;
/*     */   
/*     */   protected GsubTable(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  36 */     raf.seek(de.getOffset());
/*     */ 
/*     */     
/*  39 */     raf.readInt();
/*  40 */     int scriptListOffset = raf.readUnsignedShort();
/*  41 */     int featureListOffset = raf.readUnsignedShort();
/*  42 */     int lookupListOffset = raf.readUnsignedShort();
/*     */ 
/*     */     
/*  45 */     this.scriptList = new ScriptList(raf, de.getOffset() + scriptListOffset);
/*     */ 
/*     */     
/*  48 */     this.featureList = new FeatureList(raf, de.getOffset() + featureListOffset);
/*     */ 
/*     */     
/*  51 */     this.lookupList = new LookupList(raf, de.getOffset() + lookupListOffset, this);
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
/*     */   public LookupSubtable read(int type, RandomAccessFile raf, int offset) throws IOException {
/*  64 */     LookupSubtable s = null;
/*  65 */     switch (type) {
/*     */       case 1:
/*  67 */         s = SingleSubst.read(raf, offset);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/*  76 */         s = LigatureSubst.read(raf, offset);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  92 */     return 1196643650;
/*     */   }
/*     */   
/*     */   public ScriptList getScriptList() {
/*  96 */     return this.scriptList;
/*     */   }
/*     */   
/*     */   public FeatureList getFeatureList() {
/* 100 */     return this.featureList;
/*     */   }
/*     */   
/*     */   public LookupList getLookupList() {
/* 104 */     return this.lookupList;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     return "GSUB";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GsubTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */