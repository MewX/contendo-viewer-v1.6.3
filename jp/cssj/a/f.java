/*    */ package jp.cssj.a;
/*    */ 
/*    */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*    */ import org.cyberneko.html.a.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends c
/*    */ {
/*    */   public f() {
/*    */     try {
/* 15 */       setFeature("http://cyberneko.org/html/features/balance-tags", false);
/* 16 */       g balancer = new g();
/* 17 */       XMLDocumentFilter[] filters = { balancer };
/* 18 */       setProperty("http://cyberneko.org/html/properties/filters", filters);
/* 19 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */