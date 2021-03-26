/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.util.DoublyIndexedTable;
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
/*     */ public class AttributeInitializer
/*     */ {
/*     */   protected String[] keys;
/*     */   protected int length;
/*  44 */   protected DoublyIndexedTable values = new DoublyIndexedTable();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeInitializer(int capacity) {
/*  50 */     this.keys = new String[capacity * 3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String ns, String prefix, String ln, String val) {
/*  61 */     int len = this.keys.length;
/*  62 */     if (this.length == len) {
/*  63 */       String[] t = new String[len * 2];
/*  64 */       System.arraycopy(this.keys, 0, t, 0, len);
/*  65 */       this.keys = t;
/*     */     } 
/*  67 */     this.keys[this.length++] = ns;
/*  68 */     this.keys[this.length++] = prefix;
/*  69 */     this.keys[this.length++] = ln;
/*  70 */     this.values.put(ns, ln, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeAttributes(AbstractElement elt) {
/*  77 */     for (int i = this.length - 1; i >= 2; i -= 3) {
/*  78 */       resetAttribute(elt, this.keys[i - 2], this.keys[i - 1], this.keys[i]);
/*     */     }
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
/*     */   public boolean resetAttribute(AbstractElement elt, String ns, String prefix, String ln) {
/*  93 */     String val = (String)this.values.get(ns, ln);
/*  94 */     if (val == null) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (prefix != null) {
/*  98 */       ln = prefix + ':' + ln;
/*     */     }
/* 100 */     elt.setUnspecifiedAttribute(ns, ln, val);
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AttributeInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */