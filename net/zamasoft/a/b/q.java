/*     */ package net.zamasoft.a.b;
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
/*     */ public class q
/*     */   implements J, aa
/*     */ {
/*     */   private U a;
/*     */   private k b;
/*     */   private H c;
/*     */   
/*     */   protected q(i de, RandomAccessFile raf) throws IOException {
/*  37 */     synchronized (raf) {
/*  38 */       raf.seek(de.c());
/*     */ 
/*     */       
/*  41 */       raf.readInt();
/*  42 */       int scriptListOffset = raf.readUnsignedShort();
/*  43 */       int featureListOffset = raf.readUnsignedShort();
/*  44 */       int lookupListOffset = raf.readUnsignedShort();
/*     */ 
/*     */       
/*  47 */       this.a = new U(raf, de.c() + scriptListOffset);
/*     */ 
/*     */       
/*  50 */       this.b = new k(raf, de.c() + featureListOffset);
/*     */ 
/*     */       
/*  53 */       this.c = new H(raf, de.c() + lookupListOffset, this);
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
/*     */   public I a(int type, RandomAccessFile raf, int offset) throws IOException {
/*  65 */     I s = null;
/*  66 */     switch (type) {
/*     */       case 1:
/*  68 */         s = X.a(raf, offset);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/*  77 */         s = D.a(raf, offset);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/*  95 */     return 1196643650;
/*     */   }
/*     */   
/*     */   public U b() {
/*  99 */     return this.a;
/*     */   }
/*     */   
/*     */   public k c() {
/* 103 */     return this.b;
/*     */   }
/*     */   
/*     */   public H d() {
/* 107 */     return this.c;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 111 */     return "GSUB";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */