/*    */ package jp.cssj.d.a;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class n
/*    */ {
/*    */   public String a;
/*    */   public i[] b;
/*    */   
/*    */   public i[] a() {
/* 28 */     List<i> points = new ArrayList<>();
/* 29 */     a(this.b, points);
/* 30 */     return points.<i>toArray(new i[points.size()]);
/*    */   }
/*    */   
/*    */   private void a(i[] navPoints, List<i> points) {
/* 34 */     for (i navPoint : navPoints) {
/* 35 */       points.add(navPoint);
/* 36 */       a(navPoint.f, points);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */