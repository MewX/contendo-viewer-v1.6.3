/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements Augmentations
/*     */ {
/*  46 */   protected final Hashtable a = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   a(Augmentations augs) {
/*  60 */     for (Enumeration keys = augs.keys(); keys.hasMoreElements(); ) {
/*  61 */       String key = keys.nextElement();
/*  62 */       Object value = augs.getItem(key);
/*  63 */       if (value instanceof h.c) {
/*  64 */         value = new h.c((h.c)value);
/*     */       }
/*  66 */       this.a.put(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllItems() {
/*  74 */     this.a.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/*  81 */     this.a.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object putItem(String key, Object item) {
/*  99 */     return this.a.put(key, item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getItem(String key) {
/* 113 */     return this.a.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object removeItem(String key) {
/* 124 */     return this.a.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration keys() {
/* 131 */     return this.a.keys();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/a.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */