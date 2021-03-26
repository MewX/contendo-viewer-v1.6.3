/*    */ package jp.cssj.sakae.pdf.c.b;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements Serializable
/*    */ {
/*    */   private static final long b = 0L;
/*    */   public final Map<String, a> a;
/*    */   
/*    */   public static class a
/*    */     implements Serializable
/*    */   {
/*    */     private static final long c = 0L;
/*    */     public final int[] a;
/*    */     public final String b;
/*    */     
/*    */     public a(int[] codes, String name) {
/* 27 */       this.a = codes;
/* 28 */       this.b = name;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public f(Map<String, a> nameToCodes) {
/* 35 */     this.a = nameToCodes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static f a(InputStream _in) throws IOException {
/* 46 */     Map<String, a> map = new HashMap<>();
/* 47 */     try (BufferedReader in = new BufferedReader(new InputStreamReader(_in, "ISO-8859-1"))) {
/* 48 */       for (String line = in.readLine(); line != null; line = in.readLine()) {
/* 49 */         if (line.charAt(0) != '#') {
/*    */ 
/*    */           
/* 52 */           String[] pair = line.split(";", 2);
/* 53 */           String gname = pair[0].trim();
/* 54 */           String[] s = pair[1].trim().split("[\\s]+");
/* 55 */           int[] gids = new int[s.length];
/* 56 */           for (int i = 0; i < s.length; i++) {
/* 57 */             gids[i] = Integer.parseInt(s[i], 16);
/*    */           }
/* 59 */           a codes = new a(gids, gname);
/* 60 */           map.put(codes.b, codes);
/*    */         } 
/*    */       } 
/* 63 */     }  return new f(Collections.unmodifiableMap(map));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */