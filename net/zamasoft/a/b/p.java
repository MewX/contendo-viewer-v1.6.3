/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class p
/*    */   implements J, aa
/*    */ {
/*    */   private U a;
/*    */   private k b;
/*    */   private H c;
/*    */   
/*    */   protected p(i de, RandomAccessFile raf) throws IOException {
/* 37 */     synchronized (raf) {
/* 38 */       raf.seek(de.c());
/*    */ 
/*    */       
/* 41 */       raf.readInt();
/* 42 */       int scriptListOffset = raf.readUnsignedShort();
/* 43 */       int featureListOffset = raf.readUnsignedShort();
/* 44 */       int lookupListOffset = raf.readUnsignedShort();
/*    */ 
/*    */       
/* 47 */       this.a = new U(raf, de.c() + scriptListOffset);
/*    */ 
/*    */       
/* 50 */       this.b = new k(raf, de.c() + featureListOffset);
/*    */ 
/*    */       
/* 53 */       this.c = new H(raf, de.c() + lookupListOffset, this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public I a(int type, RandomAccessFile raf, int offset) throws IOException {
/* 58 */     I s = null;
/* 59 */     switch (type) {
/*    */     
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     return s;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 76 */     return 1196445523;
/*    */   }
/*    */   
/*    */   public U b() {
/* 80 */     return this.a;
/*    */   }
/*    */   
/*    */   public k c() {
/* 84 */     return this.b;
/*    */   }
/*    */   
/*    */   public H d() {
/* 88 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 92 */     return "GPOS";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */