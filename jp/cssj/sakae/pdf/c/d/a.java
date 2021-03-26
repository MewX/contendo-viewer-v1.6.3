/*    */ package jp.cssj.sakae.pdf.c.d;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import jp.cssj.sakae.a.g;
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
/*    */ public final class a
/*    */ {
/*    */   public static void a(Map<String, Object> map, String key, g e) {
/* 23 */     List<g> list = (List<g>)map.get(key);
/* 24 */     if (list == null) {
/* 25 */       list = new ArrayList<>();
/* 26 */       map.put(key, list);
/*    */     } 
/* 28 */     list.add(e);
/*    */   }
/*    */   
/*    */   public static void b(Map<String, Object> map, String key, g e) {
/* 32 */     g[] fonts = (g[])map.get(key);
/* 33 */     if (fonts == null) {
/* 34 */       fonts = new g[] { e };
/*    */     } else {
/* 36 */       g[] dest = new g[fonts.length + 1];
/* 37 */       System.arraycopy(fonts, 0, dest, 0, fonts.length);
/* 38 */       dest[fonts.length] = e;
/* 39 */       fonts = dest;
/*    */     } 
/* 41 */     map.put(key, fonts);
/*    */   }
/*    */   
/*    */   public static g[] a(Map<String, Object> map, String key) {
/* 45 */     return (g[])map.get(key);
/*    */   }
/*    */   
/*    */   public static Map<String, Object> a(Map<String, Object> map) {
/* 49 */     for (Iterator<Map.Entry<String, Object>> i = map.entrySet().iterator(); i.hasNext(); ) {
/* 50 */       Map.Entry<String, Object> entry = i.next();
/*    */       
/* 52 */       List<g> list = (List<g>)entry.getValue();
/* 53 */       g[] fonts = list.<g>toArray(new g[list.size()]);
/* 54 */       entry.setValue(fonts);
/*    */     } 
/* 56 */     return Collections.unmodifiableMap(map);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */