/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ public class af
/*    */   implements aa
/*    */ {
/*    */   private i a;
/*    */   private RandomAccessFile b;
/*    */   private short c;
/*    */   private int d;
/* 40 */   private Map<Integer, Short> e = null;
/*    */   
/*    */   protected af(i de, RandomAccessFile raf) throws IOException {
/* 43 */     this.a = de;
/* 44 */     this.b = raf;
/*    */   }
/*    */   
/*    */   protected void b() {
/* 48 */     if (this.b == null) {
/*    */       return;
/*    */     }
/* 51 */     synchronized (this.b) {
/*    */       try {
/* 53 */         this.b.seek(this.a.c());
/* 54 */         this.b.readUnsignedShort();
/* 55 */         this.b.readUnsignedShort();
/* 56 */         this.c = this.b.readShort();
/* 57 */         this.d = this.b.readUnsignedShort();
/*    */         
/* 59 */         this.e = new HashMap<>();
/* 60 */         for (int j = 0; j < this.d; j++) {
/* 61 */           int glyphIndex = this.b.readUnsignedShort();
/* 62 */           short vertOriginY = this.b.readShort();
/* 63 */           this.e.put(new Integer(glyphIndex), new Short(vertOriginY));
/*    */         } 
/* 65 */       } catch (IOException e) {
/* 66 */         throw new RuntimeException(e);
/*    */       } 
/* 68 */       this.b = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public short c() {
/* 73 */     b();
/* 74 */     return this.c;
/*    */   }
/*    */   
/*    */   public short a(int ix) {
/* 78 */     b();
/* 79 */     Short y = this.e.get(new Integer(ix));
/* 80 */     if (y == null) {
/* 81 */       return this.c;
/*    */     }
/* 83 */     return y.shortValue();
/*    */   }
/*    */   
/*    */   public int a() {
/* 87 */     return 1448038983;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/af.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */