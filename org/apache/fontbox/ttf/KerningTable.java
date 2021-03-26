/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KerningTable
/*     */   extends TTFTable
/*     */ {
/*  32 */   private static final Log LOG = LogFactory.getLog(KerningTable.class);
/*     */ 
/*     */   
/*     */   public static final String TAG = "kern";
/*     */ 
/*     */   
/*     */   private KerningSubtable[] subtables;
/*     */ 
/*     */ 
/*     */   
/*     */   KerningTable(TrueTypeFont font) {
/*  43 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  56 */     int version = data.readUnsignedShort();
/*  57 */     if (version != 0)
/*     */     {
/*  59 */       version = version << 16 | data.readUnsignedShort();
/*     */     }
/*  61 */     int numSubtables = 0;
/*  62 */     if (version == 0) {
/*     */       
/*  64 */       numSubtables = data.readUnsignedShort();
/*     */     }
/*  66 */     else if (version == 1) {
/*     */       
/*  68 */       numSubtables = (int)data.readUnsignedInt();
/*     */     }
/*     */     else {
/*     */       
/*  72 */       LOG.debug("Skipped kerning table due to an unsupported kerning table version: " + version);
/*     */     } 
/*  74 */     if (numSubtables > 0) {
/*     */       
/*  76 */       this.subtables = new KerningSubtable[numSubtables];
/*  77 */       for (int i = 0; i < numSubtables; i++) {
/*     */         
/*  79 */         KerningSubtable subtable = new KerningSubtable();
/*  80 */         subtable.read(data, version);
/*  81 */         this.subtables[i] = subtable;
/*     */       } 
/*     */     } 
/*  84 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KerningSubtable getHorizontalKerningSubtable() {
/*  94 */     return getHorizontalKerningSubtable(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KerningSubtable getHorizontalKerningSubtable(boolean cross) {
/* 105 */     if (this.subtables != null)
/*     */     {
/* 107 */       for (KerningSubtable s : this.subtables) {
/*     */         
/* 109 */         if (s.isHorizontalKerning(cross))
/*     */         {
/* 111 */           return s;
/*     */         }
/*     */       } 
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/KerningTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */