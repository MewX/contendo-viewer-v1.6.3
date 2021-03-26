/*    */ package jp.cssj.sakae.pdf.g;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class a
/*    */ {
/* 15 */   private static final byte[] a = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
/*    */ 
/*    */   
/*    */   public static byte[] a(String s, String encoding) throws UnsupportedEncodingException {
/* 19 */     boolean encode = false;
/* 20 */     byte[] b = s.getBytes(encoding);
/* 21 */     for (int i = 0; i < b.length; ) {
/* 22 */       byte c = b[i];
/* 23 */       if (c >= 33 && c <= 126)
/* 24 */         switch (c) {
/*    */           case 35:
/*    */           case 37:
/*    */           case 40:
/*    */           case 41:
/*    */           case 47:
/*    */           case 60:
/*    */           case 62:
/*    */           case 91:
/*    */           case 93:
/*    */           case 123:
/*    */           case 125:
/*    */             break;
/*    */           default:
/*    */             i++;
/*    */             continue;
/*    */         }  
/* 41 */       encode = true;
/*    */     } 
/*    */     
/* 44 */     if (!encode) {
/* 45 */       return b;
/*    */     }
/* 47 */     ByteArrayOutputStream buff = new ByteArrayOutputStream();
/* 48 */     for (int j = 0; j < b.length; j++) {
/* 49 */       byte c = b[j];
/* 50 */       if (c >= 33 && c <= 126) {
/* 51 */         short h; short l; switch (c) {
/*    */           case 35:
/*    */           case 37:
/*    */           case 40:
/*    */           case 41:
/*    */           case 47:
/*    */           case 60:
/*    */           case 62:
/*    */           case 91:
/*    */           case 93:
/*    */           case 123:
/*    */           case 125:
/* 63 */             buff.write(35);
/* 64 */             h = (short)(c >> 4 & 0xF);
/* 65 */             l = (short)(c & 0xF);
/* 66 */             buff.write(a[h]);
/* 67 */             buff.write(a[l]);
/*    */             break;
/*    */           
/*    */           default:
/* 71 */             buff.write(c);
/*    */             break;
/*    */         } 
/*    */       } else {
/* 75 */         buff.write(35);
/* 76 */         short h = (short)(c >> 4 & 0xF);
/* 77 */         short l = (short)(c & 0xF);
/* 78 */         buff.write(a[h]);
/* 79 */         buff.write(a[l]);
/*    */       } 
/*    */     } 
/* 82 */     return buff.toByteArray();
/*    */   }
/*    */   
/*    */   public static String b(String s, String encoding) throws UnsupportedEncodingException {
/* 86 */     char[] ch = s.toCharArray();
/* 87 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 88 */     for (int i = 0; i < ch.length; i++) {
/* 89 */       char c = ch[i];
/* 90 */       if (c != '#') {
/* 91 */         out.write(c);
/*    */       } else {
/* 93 */         char h = s.charAt(++i);
/* 94 */         char l = s.charAt(++i);
/* 95 */         out.write(Integer.parseInt("" + h + l, 16));
/*    */       } 
/*    */     } 
/* 98 */     return new String(out.toByteArray(), encoding);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */