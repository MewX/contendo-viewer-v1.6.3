/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.text.Collator;
/*     */ import java.util.Locale;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
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
/*     */ final class SortSettings
/*     */ {
/*     */   private AbstractTranslet _translet;
/*     */   private int[] _sortOrders;
/*     */   private int[] _types;
/*     */   private Locale[] _locales;
/*     */   private Collator[] _collators;
/*     */   private String[] _caseOrders;
/*     */   
/*     */   SortSettings(AbstractTranslet translet, int[] sortOrders, int[] types, Locale[] locales, Collator[] collators, String[] caseOrders) {
/*  82 */     this._translet = translet;
/*  83 */     this._sortOrders = sortOrders;
/*  84 */     this._types = types;
/*  85 */     this._locales = locales;
/*  86 */     this._collators = collators;
/*  87 */     this._caseOrders = caseOrders;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AbstractTranslet getTranslet() {
/*  94 */     return this._translet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int[] getSortOrders() {
/* 102 */     return this._sortOrders;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int[] getTypes() {
/* 110 */     return this._types;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Locale[] getLocales() {
/* 118 */     return this._locales;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Collator[] getCollators() {
/* 126 */     return this._collators;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String[] getCaseOrders() {
/* 134 */     return this._caseOrders;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SortSettings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */