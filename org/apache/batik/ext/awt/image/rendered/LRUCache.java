/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import org.apache.batik.util.DoublyLinkedList;
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
/*     */ public class LRUCache
/*     */ {
/*     */   public class LRUNode
/*     */     extends DoublyLinkedList.Node
/*     */   {
/*  58 */     private LRUCache.LRUObj obj = null; public LRUCache.LRUObj getObj() {
/*  59 */       return this.obj;
/*     */     } protected void setObj(LRUCache.LRUObj newObj) {
/*  61 */       if (this.obj != null) this.obj.lruRemove();
/*     */       
/*  63 */       this.obj = newObj;
/*  64 */       if (this.obj != null) this.obj.lruSet(this);
/*     */     
/*     */     }
/*     */   }
/*  68 */   private DoublyLinkedList free = null;
/*  69 */   private DoublyLinkedList used = null;
/*  70 */   private int maxSize = 0;
/*     */   
/*     */   public LRUCache(int size) {
/*  73 */     if (size <= 0) size = 1; 
/*  74 */     this.maxSize = size;
/*     */     
/*  76 */     this.free = new DoublyLinkedList();
/*  77 */     this.used = new DoublyLinkedList();
/*     */     
/*  79 */     while (size > 0) {
/*  80 */       this.free.add(new LRUNode());
/*  81 */       size--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getUsed() {
/*  86 */     return this.used.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setSize(int newSz) {
/*  91 */     if (this.maxSize < newSz) {
/*     */       
/*  93 */       for (int i = this.maxSize; i < newSz; i++) {
/*  94 */         this.free.add(new LRUNode());
/*     */       }
/*  96 */     } else if (this.maxSize > newSz) {
/*     */       
/*  98 */       for (int i = this.used.getSize(); i > newSz; i--) {
/*  99 */         LRUNode nde = (LRUNode)this.used.getTail();
/* 100 */         this.used.remove(nde);
/* 101 */         nde.setObj(null);
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     this.maxSize = newSz;
/*     */   }
/*     */   
/*     */   public synchronized void flush() {
/* 109 */     while (this.used.getSize() > 0) {
/* 110 */       LRUNode nde = (LRUNode)this.used.pop();
/* 111 */       nde.setObj(null);
/* 112 */       this.free.add(nde);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void remove(LRUObj obj) {
/* 117 */     LRUNode nde = obj.lruGet();
/* 118 */     if (nde == null)
/* 119 */       return;  this.used.remove(nde);
/* 120 */     nde.setObj(null);
/* 121 */     this.free.add(nde);
/*     */   }
/*     */   
/*     */   public synchronized void touch(LRUObj obj) {
/* 125 */     LRUNode nde = obj.lruGet();
/* 126 */     if (nde == null)
/* 127 */       return;  this.used.touch(nde);
/*     */   }
/*     */   
/*     */   public synchronized void add(LRUObj obj) {
/* 131 */     LRUNode nde = obj.lruGet();
/*     */ 
/*     */     
/* 134 */     if (nde != null) {
/* 135 */       this.used.touch(nde);
/*     */       
/*     */       return;
/*     */     } 
/* 139 */     if (this.free.getSize() > 0) {
/* 140 */       nde = (LRUNode)this.free.pop();
/* 141 */       nde.setObj(obj);
/* 142 */       this.used.add(nde);
/*     */     } else {
/* 144 */       nde = (LRUNode)this.used.getTail();
/* 145 */       nde.setObj(obj);
/* 146 */       this.used.touch(nde);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected synchronized void print() {
/* 151 */     System.out.println("In Use: " + this.used.getSize() + " Free: " + this.free.getSize());
/*     */     
/* 153 */     LRUNode nde = (LRUNode)this.used.getHead();
/* 154 */     if (nde == null)
/*     */       return;  do {
/* 156 */       System.out.println(nde.getObj());
/* 157 */       nde = (LRUNode)nde.getNext();
/* 158 */     } while (nde != this.used.getHead());
/*     */   }
/*     */   
/*     */   public static interface LRUObj {
/*     */     void lruSet(LRUCache.LRUNode param1LRUNode);
/*     */     
/*     */     LRUCache.LRUNode lruGet();
/*     */     
/*     */     void lruRemove();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/LRUCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */