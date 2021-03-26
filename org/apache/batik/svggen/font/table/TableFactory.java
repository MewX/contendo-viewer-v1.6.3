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
/*     */ 
/*     */ 
/*     */ public class TableFactory
/*     */ {
/*     */   public static Table create(DirectoryEntry de, RandomAccessFile raf) throws IOException {
/*  33 */     Table t = null;
/*  34 */     switch (de.getTag()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1196445523:
/*  50 */         t = new GposTable(de, raf);
/*     */         break;
/*     */       case 1196643650:
/*  53 */         t = new GsubTable(de, raf);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1330851634:
/*  64 */         t = new Os2Table(de, raf);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1668112752:
/*  71 */         t = new CmapTable(de, raf);
/*     */         break;
/*     */       case 1668707360:
/*  74 */         t = new CvtTable(de, raf);
/*     */         break;
/*     */       case 1718642541:
/*  77 */         t = new FpgmTable(de, raf);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1735162214:
/*  84 */         t = new GlyfTable(de, raf);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1751474532:
/*  89 */         t = new HeadTable(de, raf);
/*     */         break;
/*     */       case 1751672161:
/*  92 */         t = new HheaTable(de, raf);
/*     */         break;
/*     */       case 1752003704:
/*  95 */         t = new HmtxTable(de, raf);
/*     */         break;
/*     */       case 1801810542:
/*  98 */         t = new KernTable(de, raf);
/*     */         break;
/*     */       case 1819239265:
/* 101 */         t = new LocaTable(de, raf);
/*     */         break;
/*     */       case 1835104368:
/* 104 */         t = new MaxpTable(de, raf);
/*     */         break;
/*     */       case 1851878757:
/* 107 */         t = new NameTable(de, raf);
/*     */         break;
/*     */       case 1886545264:
/* 110 */         t = new PrepTable(de, raf);
/*     */         break;
/*     */       case 1886352244:
/* 113 */         t = new PostTable(de, raf);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return t;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/TableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */