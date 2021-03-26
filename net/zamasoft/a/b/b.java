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
/*    */ public class b
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   protected b(RandomAccessFile raf) throws IOException {
/* 36 */     this.a = raf.readUnsignedShort();
/* 37 */     this.b = raf.readUnsignedShort();
/* 38 */     this.c = raf.readInt();
/*    */   }
/*    */   
/*    */   public int a() {
/* 42 */     return this.b;
/*    */   }
/*    */   
/*    */   public int b() {
/* 46 */     return this.c;
/*    */   }
/*    */   
/*    */   public int c() {
/* 50 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String platform, encoding = "";
/*    */     
/* 57 */     switch (this.a) {
/*    */       case 1:
/* 59 */         platform = " (Macintosh)";
/*    */         break;
/*    */       case 3:
/* 62 */         platform = " (Windows)";
/*    */         break;
/*    */       default:
/* 65 */         platform = ""; break;
/*    */     } 
/* 67 */     if (this.a == 3)
/*    */     {
/* 69 */       switch (this.b) {
/*    */         case 0:
/* 71 */           encoding = " (Symbol)";
/*    */           break;
/*    */         case 1:
/* 74 */           encoding = " (Unicode)";
/*    */           break;
/*    */         case 2:
/* 77 */           encoding = " (ShiftJIS)";
/*    */           break;
/*    */         case 3:
/* 80 */           encoding = " (Big5)";
/*    */           break;
/*    */         case 4:
/* 83 */           encoding = " (PRC)";
/*    */           break;
/*    */         case 5:
/* 86 */           encoding = " (Wansung)";
/*    */           break;
/*    */         case 6:
/* 89 */           encoding = " (Johab)";
/*    */           break;
/*    */         default:
/* 92 */           encoding = "";
/*    */           break;
/*    */       }  } 
/* 95 */     return "platform id: " + this.a + platform + ", encoding id: " + this.b + 
/* 96 */       encoding + ", offset: " + this.c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */