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
/*     */ public abstract class SingleNodeCounter
/*     */   extends NodeCounter
/*     */ {
/*  31 */   private static final int[] EmptyArray = new int[0];
/*  32 */   DTMAxisIterator _countSiblings = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public SingleNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  37 */     super(translet, document, iterator);
/*     */   }
/*     */   
/*     */   public NodeCounter setStartNode(int node) {
/*  41 */     this._node = node;
/*  42 */     this._nodeType = this._document.getExpandedTypeID(node);
/*  43 */     this._countSiblings = this._document.getAxisIterator(12);
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public String getCounter() {
/*     */     byte b;
/*  49 */     if (this._value != Integer.MIN_VALUE) {
/*  50 */       b = this._value;
/*     */     } else {
/*     */       
/*  53 */       int next = this._node;
/*  54 */       b = 0;
/*  55 */       if (!matchesCount(next)) {
/*  56 */         while ((next = this._document.getParent(next)) > -1 && 
/*  57 */           !matchesCount(next)) {
/*     */ 
/*     */           
/*  60 */           if (matchesFrom(next)) {
/*  61 */             next = -1;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*  67 */       if (next != -1) {
/*  68 */         this._countSiblings.setStartNode(next);
/*     */         do {
/*  70 */           if (!matchesCount(next)) continue;  b++;
/*  71 */         } while ((next = this._countSiblings.next()) != -1);
/*     */       }
/*     */       else {
/*     */         
/*  75 */         return formatNumbers(EmptyArray);
/*     */       } 
/*     */     } 
/*  78 */     return formatNumbers(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeCounter getDefaultNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  84 */     return new DefaultSingleNodeCounter(translet, document, iterator);
/*     */   }
/*     */   
/*     */   static class DefaultSingleNodeCounter
/*     */     extends SingleNodeCounter {
/*     */     public DefaultSingleNodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  90 */       super(translet, document, iterator);
/*     */     }
/*     */     
/*     */     public NodeCounter setStartNode(int node) {
/*  94 */       this._node = node;
/*  95 */       this._nodeType = this._document.getExpandedTypeID(node);
/*  96 */       this._countSiblings = this._document.getTypedAxisIterator(12, this._document.getExpandedTypeID(node));
/*     */ 
/*     */       
/*  99 */       return this;
/*     */     }
/*     */     
/*     */     public String getCounter() {
/*     */       byte b;
/* 104 */       if (this._value != Integer.MIN_VALUE) {
/* 105 */         b = this._value;
/*     */       }
/*     */       else {
/*     */         
/* 109 */         b = 1;
/* 110 */         this._countSiblings.setStartNode(this._node); int next;
/* 111 */         while ((next = this._countSiblings.next()) != -1) {
/* 112 */           b++;
/*     */         }
/*     */       } 
/* 115 */       return formatNumbers(b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SingleNodeCounter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */