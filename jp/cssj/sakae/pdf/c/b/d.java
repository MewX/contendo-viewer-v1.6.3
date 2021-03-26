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
/*    */ public class d
/*    */   implements Serializable
/*    */ {
/*    */   private static final long d = 0L;
/*    */   public final String a;
/*    */   public final a[] b;
/*    */   public final Map<String, a> c;
/*    */   
/*    */   public static class a
/*    */     implements Serializable
/*    */   {
/*    */     private static final long c = 0L;
/*    */     public final int a;
/*    */     public final String b;
/*    */     
/*    */     public a(int gid, String name) {
/* 28 */       this.a = gid;
/* 29 */       this.b = name;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public d(String name, Map<String, a> nameToCodeMap) {
/* 40 */     this.a = name;
/* 41 */     this.c = nameToCodeMap;
/* 42 */     this.b = (a[])nameToCodeMap.values().toArray((Object[])new a[nameToCodeMap.size()]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static d a(InputStream _in) throws IOException {
/*    */     String name;
/* 53 */     Map<String, a> map = new HashMap<>();
/*    */     
/* 55 */     try (BufferedReader in = new BufferedReader(new InputStreamReader(_in, "ISO-8859-1"))) {
/* 56 */       name = in.readLine();
/* 57 */       for (String line = in.readLine(); line != null; line = in.readLine()) {
/* 58 */         if (line.charAt(0) != '#') {
/*    */ 
/*    */           
/* 61 */           String[] pair = line.split(";", 2);
/* 62 */           String gname = pair[0].trim();
/* 63 */           int gid = Integer.parseInt(pair[1], 16);
/* 64 */           a codeMap = new a(gid, gname);
/* 65 */           map.put(codeMap.b, codeMap);
/*    */         } 
/*    */       } 
/* 68 */     }  return new d(name, Collections.unmodifiableMap(map));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */