/*    */ package net.a.a.c.a;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Locale;
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
/*    */ public final class g
/*    */   extends a
/*    */ {
/*    */   public static final String a = ",";
/*    */   private static final long d = 1L;
/* 45 */   private static final h e = new g();
/*    */ 
/*    */   
/*    */   private g() {
/* 49 */     super(List.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static h b() {
/* 56 */     return e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(String paramString) {
/* 62 */     if (paramString == null) {
/* 63 */       return null;
/*    */     }
/*    */     
/* 66 */     String[] arrayOfString = paramString.split("\\s*,\\s*");
/*    */     
/* 68 */     ArrayList<String> arrayList = new ArrayList(arrayOfString.length);
/* 69 */     for (String str : arrayOfString) {
/* 70 */       arrayList.add(str.trim().toLowerCase(Locale.ENGLISH).intern());
/*    */     }
/* 72 */     return arrayList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String b(Object paramObject) {
/* 79 */     if (paramObject == null) {
/* 80 */       return null;
/*    */     }
/* 82 */     StringBuilder stringBuilder = new StringBuilder();
/* 83 */     boolean bool = true;
/* 84 */     for (Object object : paramObject) {
/* 85 */       if (bool) {
/* 86 */         bool = false;
/*    */       } else {
/* 88 */         stringBuilder.append(",");
/* 89 */         stringBuilder.append(' ');
/*    */       } 
/* 91 */       stringBuilder.append(object);
/*    */     } 
/* 93 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/a/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */