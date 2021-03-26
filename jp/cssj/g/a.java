/*    */ package jp.cssj.g;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public final String b;
/*    */   public final String c;
/*    */   public final String d;
/*    */   public final String e;
/*    */   public final String f;
/*    */   public final String g;
/*    */   
/*    */   protected a() {
/* 19 */     try (BufferedReader in = new BufferedReader(new InputStreamReader(
/* 20 */             getClass().getResourceAsStream("VERSION"), "UTF-8"))) {
/* 21 */       this.b = in.readLine();
/* 22 */       this.c = in.readLine();
/* 23 */       this.d = in.readLine();
/* 24 */       this.e = in.readLine();
/* 25 */       StringBuffer credits = new StringBuffer();
/*    */       String line;
/* 27 */       while ((line = in.readLine()) != null) {
/* 28 */         credits.append(line);
/* 29 */         credits.append('\n');
/*    */       } 
/* 31 */       this.f = credits.toString();
/*    */     }
/* 33 */     catch (Exception e) {
/* 34 */       throw new RuntimeException(e);
/*    */     } 
/* 36 */     this.g = this.b + " " + this.c + "/" + this.d;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/g/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */