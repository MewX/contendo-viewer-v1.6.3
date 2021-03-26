/*    */ package jp.cssj.homare.css;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.css.c.k;
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
/*    */ public class f
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long a = 0L;
/* 22 */   private List<k> b = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public f() {}
/*    */ 
/*    */   
/*    */   public f(f declaration) {
/* 29 */     a(declaration);
/*    */   }
/*    */   
/*    */   public Object clone() {
/* 33 */     f declaration = new f();
/* 34 */     declaration.b = new ArrayList<>(this.b);
/* 35 */     return declaration;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(f declaration) {
/* 45 */     if (declaration == null) {
/*    */       return;
/*    */     }
/* 48 */     for (int i = 0; i < declaration.a(); i++) {
/* 49 */       k property = declaration.a(i);
/* 50 */       a(property);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(k property) {
/* 60 */     this.b.add(property);
/*    */   }
/*    */   
/*    */   public k a(int i) {
/* 64 */     return this.b.get(i);
/*    */   }
/*    */   
/*    */   public int a() {
/* 68 */     return this.b.size();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(c style) {
/* 77 */     for (int i = 0; i < this.b.size(); i++) {
/* 78 */       k property = this.b.get(i);
/* 79 */       property.a(style);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 84 */     StringBuffer buff = new StringBuffer();
/* 85 */     for (int i = 0; i < this.b.size(); i++) {
/* 86 */       buff.append(this.b.get(i));
/* 87 */       buff.append(";\n");
/*    */     } 
/* 89 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */