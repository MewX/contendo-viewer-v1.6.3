/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.text.Collator;
/*     */ import java.util.Locale;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xml.utils.LocaleUtility;
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
/*     */ public class NodeSortRecordFactory
/*     */ {
/*  32 */   private static int DESCENDING = "descending".length();
/*  33 */   private static int NUMBER = "number".length();
/*     */ 
/*     */ 
/*     */   
/*     */   private final DOM _dom;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String _className;
/*     */ 
/*     */ 
/*     */   
/*     */   private Class _class;
/*     */ 
/*     */ 
/*     */   
/*     */   private SortSettings _sortSettings;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Collator _collator;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSortRecordFactory(DOM dom, String className, Translet translet, String[] order, String[] type) throws TransletException {
/*  59 */     this(dom, className, translet, order, type, null, null);
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
/*     */   public NodeSortRecordFactory(DOM dom, String className, Translet translet, String[] order, String[] type, String[] lang, String[] caseOrder) throws TransletException {
/*     */     
/*  75 */     try { this._dom = dom;
/*  76 */       this._className = className;
/*     */       
/*  78 */       this._class = translet.getAuxiliaryClass(className);
/*     */       
/*  80 */       if (this._class == null) {
/*  81 */         this._class = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
/*     */       }
/*     */ 
/*     */       
/*  85 */       int levels = order.length;
/*  86 */       int[] iOrder = new int[levels];
/*  87 */       int[] iType = new int[levels];
/*  88 */       for (int i = 0; i < levels; i++) {
/*  89 */         if (order[i].length() == DESCENDING) {
/*  90 */           iOrder[i] = 1;
/*     */         }
/*  92 */         if (type[i].length() == NUMBER) {
/*  93 */           iType[i] = 1;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       String[] emptyStringArray = null;
/* 101 */       if (lang == null || caseOrder == null) {
/* 102 */         int numSortKeys = order.length;
/* 103 */         emptyStringArray = new String[numSortKeys];
/*     */ 
/*     */ 
/*     */         
/* 107 */         for (int k = 0; k < numSortKeys; k++) {
/* 108 */           emptyStringArray[k] = "";
/*     */         }
/*     */       } 
/*     */       
/* 112 */       if (lang == null) {
/* 113 */         lang = emptyStringArray;
/*     */       }
/* 115 */       if (caseOrder == null) {
/* 116 */         caseOrder = emptyStringArray;
/*     */       }
/*     */       
/* 119 */       int length = lang.length;
/* 120 */       Locale[] locales = new Locale[length];
/* 121 */       Collator[] collators = new Collator[length];
/* 122 */       for (int j = 0; j < length; j++) {
/* 123 */         locales[j] = LocaleUtility.langToLocale(lang[j]);
/* 124 */         collators[j] = Collator.getInstance(locales[j]);
/*     */       } 
/*     */       
/* 127 */       this._sortSettings = new SortSettings((AbstractTranslet)translet, iOrder, iType, locales, collators, caseOrder); } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/*     */       
/* 131 */       throw new TransletException(e); }
/*     */   
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
/*     */   public NodeSortRecord makeNodeSortRecord(int node, int last) throws ExceptionInInitializerError, LinkageError, IllegalAccessException, InstantiationException, SecurityException, TransletException {
/* 149 */     NodeSortRecord sortRecord = this._class.newInstance();
/*     */     
/* 151 */     sortRecord.initialize(node, last, this._dom, this._sortSettings);
/* 152 */     return sortRecord;
/*     */   }
/*     */   
/*     */   public String getClassName() {
/* 156 */     return this._className;
/*     */   }
/*     */   
/*     */   private final void setLang(String[] lang) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/NodeSortRecordFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */