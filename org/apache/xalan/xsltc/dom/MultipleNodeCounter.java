/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.util.IntegerArray;
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
/*     */ public abstract class MultipleNodeCounter
/*     */   extends NodeCounter
/*     */ {
/*  32 */   private DTMAxisIterator _precSiblings = null;
/*     */ 
/*     */   
/*     */   public MultipleNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  36 */     super(translet, document, iterator);
/*     */   }
/*     */   
/*     */   public NodeCounter setStartNode(int node) {
/*  40 */     this._node = node;
/*  41 */     this._nodeType = this._document.getExpandedTypeID(node);
/*  42 */     this._precSiblings = this._document.getAxisIterator(12);
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public String getCounter() {
/*  47 */     if (this._value != Integer.MIN_VALUE) {
/*  48 */       return formatNumbers(this._value);
/*     */     }
/*     */     
/*  51 */     IntegerArray ancestors = new IntegerArray();
/*     */ 
/*     */     
/*  54 */     int next = this._node;
/*  55 */     ancestors.add(next);
/*  56 */     while ((next = this._document.getParent(next)) > -1 && !matchesFrom(next))
/*     */     {
/*  58 */       ancestors.add(next);
/*     */     }
/*     */ 
/*     */     
/*  62 */     int nAncestors = ancestors.cardinality();
/*  63 */     int[] counters = new int[nAncestors];
/*  64 */     for (int i = 0; i < nAncestors; i++) {
/*  65 */       counters[i] = Integer.MIN_VALUE;
/*     */     }
/*     */ 
/*     */     
/*  69 */     for (int j = 0, k = nAncestors - 1; k >= 0; k--, j++) {
/*  70 */       int counter = counters[j];
/*  71 */       int ancestor = ancestors.at(k);
/*     */       
/*  73 */       if (matchesCount(ancestor)) {
/*  74 */         this._precSiblings.setStartNode(ancestor);
/*  75 */         while ((next = this._precSiblings.next()) != -1) {
/*  76 */           if (matchesCount(next)) {
/*  77 */             counters[j] = (counters[j] == Integer.MIN_VALUE) ? 1 : (counters[j] + 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/*  82 */         counters[j] = (counters[j] == Integer.MIN_VALUE) ? 1 : (counters[j] + 1);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  87 */     return formatNumbers(counters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeCounter getDefaultNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  93 */     return new DefaultMultipleNodeCounter(translet, document, iterator);
/*     */   }
/*     */   
/*     */   static class DefaultMultipleNodeCounter
/*     */     extends MultipleNodeCounter
/*     */   {
/*     */     public DefaultMultipleNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/* 100 */       super(translet, document, iterator);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/MultipleNodeCounter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */