/*     */ package org.apache.batik.dom.util;
/*     */ 
/*     */ import java.util.HashMap;
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
/*     */ public class HashTableStack
/*     */ {
/*  33 */   protected Link current = new Link(null);
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
/*     */   public void push() {
/*  45 */     this.current.pushCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pop() {
/*  52 */     if (this.current.pushCount-- == 0) {
/*  53 */       this.current = this.current.next;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String put(String s, String v) {
/*  61 */     if (this.current.pushCount != 0) {
/*  62 */       this.current.pushCount--;
/*  63 */       this.current = new Link(this.current);
/*     */     } 
/*  65 */     if (s.length() == 0) this.current.defaultStr = v; 
/*  66 */     return this.current.table.put(s, v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String get(String s) {
/*  73 */     if (s.length() == 0) return this.current.defaultStr;
/*     */     
/*  75 */     for (Link l = this.current; l != null; l = l.next) {
/*  76 */       String uri = (String)l.table.get(s);
/*  77 */       if (uri != null) {
/*  78 */         return uri;
/*     */       }
/*     */     } 
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Link
/*     */   {
/*     */     public HashMap table;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Link next;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String defaultStr;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     public int pushCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Link(Link n) {
/* 113 */       this.table = new HashMap<Object, Object>();
/* 114 */       this.next = n;
/* 115 */       if (this.next != null)
/* 116 */         this.defaultStr = this.next.defaultStr; 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/util/HashTableStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */