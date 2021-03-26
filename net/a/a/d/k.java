/*    */ package net.a.a.d;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.TreeSet;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.imageio.ImageWriter;
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
/*    */ public final class k
/*    */   implements d
/*    */ {
/*    */   public void a(f paramf) {
/* 50 */     String[] arrayOfString = ImageIO.getWriterMIMETypes();
/*    */     
/* 52 */     TreeSet<String> treeSet = new TreeSet();
/* 53 */     treeSet.add("image/jpeg");
/* 54 */     treeSet.add("image/bmp");
/*    */     
/* 56 */     for (String str : arrayOfString) {
/*    */       
/* 58 */       Iterator<ImageWriter> iterator = ImageIO.getImageWritersByMIMEType(str);
/* 59 */       if (iterator != null)
/* 60 */         while (iterator.hasNext()) {
/* 61 */           ImageWriter imageWriter = iterator.next();
/*    */           
/* 63 */           String[] arrayOfString1 = imageWriter.getOriginatingProvider().getFileSuffixes();
/* 64 */           if (arrayOfString1 != null) {
/* 65 */             for (String str1 : arrayOfString1) {
/* 66 */               paramf.a(str, str1, false);
/*    */             }
/*    */           }
/*    */           
/* 70 */           paramf.a(str, new j(imageWriter, treeSet
/*    */                 
/* 72 */                 .contains(str)), false);
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */