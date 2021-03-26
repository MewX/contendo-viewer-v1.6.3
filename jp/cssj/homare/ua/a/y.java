/*    */ package jp.cssj.homare.ua.a;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
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
/*    */ public class y
/*    */ {
/*    */   public static void a(Map<Object, Object> props) {
/* 20 */     Field[] fields = B.class.getFields();
/* 21 */     for (int i = 0; i < fields.length; i++) {
/* 22 */       Field field = fields[i];
/* 23 */       if (z.class.isAssignableFrom(field.getType())) {
/*    */         z prop;
/*    */         try {
/* 26 */           prop = (z)field.get(B.class);
/* 27 */         } catch (IllegalAccessException e) {
/* 28 */           throw new RuntimeException(e);
/*    */         } 
/* 30 */         String value = prop.b();
/* 31 */         if (value != null) {
/* 32 */           String name = prop.a();
/* 33 */           props.put(name, value);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void b(Map<Object, Object> props) {
/* 45 */     Map<Object, Object> map = new HashMap<>();
/* 46 */     a(map);
/* 47 */     for (Iterator<?> i = map.entrySet().iterator(); i.hasNext(); ) {
/* 48 */       Map.Entry<?, ?> e = (Map.Entry<?, ?>)i.next();
/* 49 */       String name = (String)e.getKey();
/* 50 */       String value = (String)e.getValue();
/* 51 */       if (value.equals(props.get(name))) {
/* 52 */         props.remove(name);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void c(Map<Object, Object> props) {
/* 61 */     Field[] fields = B.class.getFields();
/* 62 */     for (int i = 0; i < fields.length; i++) {
/* 63 */       Field field = fields[i];
/* 64 */       if (b.class.isAssignableFrom(field.getType())) {
/*    */         b prop;
/*    */         try {
/* 67 */           prop = (b)field.get(B.class);
/* 68 */         } catch (IllegalAccessException e) {
/* 69 */           throw new RuntimeException(e);
/*    */         } 
/* 71 */         String name = prop.a();
/* 72 */         props.put(name, "false");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */