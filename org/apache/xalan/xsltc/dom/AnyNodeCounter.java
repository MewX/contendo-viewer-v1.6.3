/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
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
/*     */ public abstract class AnyNodeCounter
/*     */   extends NodeCounter
/*     */ {
/*     */   public AnyNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  33 */     super(translet, document, iterator);
/*     */   }
/*     */   
/*     */   public NodeCounter setStartNode(int node) {
/*  37 */     this._node = node;
/*  38 */     this._nodeType = this._document.getExpandedTypeID(node);
/*  39 */     return this;
/*     */   }
/*     */   
/*     */   public String getCounter() {
/*     */     byte b;
/*  44 */     if (this._value != Integer.MIN_VALUE) {
/*  45 */       b = this._value;
/*     */     } else {
/*     */       
/*  48 */       int next = this._node;
/*  49 */       int root = this._document.getDocument();
/*  50 */       b = 0;
/*  51 */       while (next >= root && !matchesFrom(next)) {
/*  52 */         if (matchesCount(next)) {
/*  53 */           b++;
/*     */         }
/*  55 */         next--;
/*     */       } 
/*     */     } 
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
/*  68 */     return formatNumbers(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeCounter getDefaultNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  74 */     return new DefaultAnyNodeCounter(translet, document, iterator);
/*     */   }
/*     */   
/*     */   static class DefaultAnyNodeCounter
/*     */     extends AnyNodeCounter {
/*     */     public DefaultAnyNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  80 */       super(translet, document, iterator);
/*     */     }
/*     */     
/*     */     public String getCounter() {
/*     */       byte b;
/*  85 */       if (this._value != Integer.MIN_VALUE) {
/*  86 */         b = this._value;
/*     */       } else {
/*     */         
/*  89 */         int next = this._node;
/*  90 */         b = 0;
/*  91 */         int ntype = this._document.getExpandedTypeID(this._node);
/*  92 */         int root = this._document.getDocument();
/*  93 */         while (next >= 0) {
/*  94 */           if (ntype == this._document.getExpandedTypeID(next)) {
/*  95 */             b++;
/*     */           }
/*     */ 
/*     */           
/*  99 */           if (next == root) {
/*     */             break;
/*     */           }
/*     */           
/* 103 */           next--;
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       return formatNumbers(b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/AnyNodeCounter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */