/*    */ package net.a.a.c.a;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.Locale;
/*    */ import net.a.a.e.d.a.a;
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
/*    */ public final class c
/*    */   extends a
/*    */ {
/*    */   private static final long a = 1L;
/* 40 */   private static final h d = new c();
/*    */ 
/*    */   
/*    */   private c() {
/* 44 */     super(Color.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static h b() {
/* 51 */     return d;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object a(String paramString) {
/* 57 */     Color color = a.a(paramString, null);
/* 58 */     if (color == null) {
/* 59 */       throw new IllegalArgumentException('<' + paramString + "> is not a valid color representation");
/*    */     }
/*    */     
/* 62 */     return color;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String b(Object paramObject) {
/* 69 */     if (paramObject == null) {
/* 70 */       return null;
/*    */     }
/* 72 */     String str = null;
/*    */ 
/*    */     
/*    */     try {
/* 76 */       for (Field field : Color.class.getFields()) {
/* 77 */         if (Modifier.isStatic(field.getModifiers()) && field
/* 78 */           .get(null) == paramObject) {
/* 79 */           str = field.getName().toLowerCase(Locale.ENGLISH);
/*    */           break;
/*    */         } 
/*    */       } 
/* 83 */     } catch (IllegalAccessException illegalAccessException) {
/* 84 */       str = null;
/*    */     } 
/* 86 */     if (str == null) {
/* 87 */       str = a.a((Color)paramObject);
/*    */     }
/* 89 */     return str;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/c/a/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */