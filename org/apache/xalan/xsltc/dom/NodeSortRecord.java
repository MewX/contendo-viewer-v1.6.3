/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.text.Collator;
/*     */ import java.util.Locale;
/*     */ import org.apache.xalan.xsltc.CollatorFactory;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xml.utils.StringComparable;
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
/*     */ public abstract class NodeSortRecord
/*     */ {
/*     */   public static final int COMPARE_STRING = 0;
/*     */   public static final int COMPARE_NUMERIC = 1;
/*     */   public static final int COMPARE_ASCENDING = 0;
/*     */   public static final int COMPARE_DESCENDING = 1;
/*  48 */   private static final Collator DEFAULT_COLLATOR = Collator.getInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected Collator _collator = DEFAULT_COLLATOR;
/*     */ 
/*     */   
/*     */   protected Collator[] _collators;
/*     */ 
/*     */   
/*     */   protected Locale _locale;
/*     */ 
/*     */   
/*     */   protected CollatorFactory _collatorFactory;
/*     */ 
/*     */   
/*     */   protected SortSettings _settings;
/*     */   
/*  69 */   private DOM _dom = null;
/*     */   private int _node;
/*  71 */   private int _last = 0;
/*  72 */   private int _scanned = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object[] _values;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSortRecord(int node) {
/*  83 */     this._node = node;
/*     */   }
/*     */   
/*     */   public NodeSortRecord() {
/*  87 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void initialize(int node, int last, DOM dom, SortSettings settings) throws TransletException {
/*  98 */     this._dom = dom;
/*  99 */     this._node = node;
/* 100 */     this._last = last;
/* 101 */     this._settings = settings;
/*     */     
/* 103 */     int levels = (settings.getSortOrders()).length;
/* 104 */     this._values = new Object[levels];
/*     */ 
/*     */     
/* 107 */     String colFactClassname = System.getProperty("org.apache.xalan.xsltc.COLLATOR_FACTORY");
/*     */ 
/*     */     
/* 110 */     if (colFactClassname != null) {
/*     */       
/* 112 */       try { Object candObj = ObjectFactory.findProviderClass(colFactClassname, ObjectFactory.findClassLoader(), true);
/*     */         
/* 114 */         this._collatorFactory = (CollatorFactory)candObj; } catch (ClassNotFoundException e)
/*     */       
/* 116 */       { throw new TransletException(e); }
/*     */       
/* 118 */       Locale[] locales = settings.getLocales();
/* 119 */       this._collators = new Collator[levels];
/* 120 */       for (int i = 0; i < levels; i++) {
/* 121 */         this._collators[i] = this._collatorFactory.getCollator(locales[i]);
/*     */       }
/* 123 */       this._collator = this._collators[0];
/*     */     } else {
/* 125 */       this._collators = settings.getCollators();
/* 126 */       this._collator = this._collators[0];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNode() {
/* 134 */     return this._node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int compareDocOrder(NodeSortRecord other) {
/* 141 */     return this._node - other._node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Comparable stringValue(int level) {
/* 151 */     if (this._scanned <= level) {
/* 152 */       AbstractTranslet translet = this._settings.getTranslet();
/* 153 */       Locale[] locales = this._settings.getLocales();
/* 154 */       String[] caseOrder = this._settings.getCaseOrders();
/*     */ 
/*     */       
/* 157 */       String str = extractValueFromDOM(this._dom, this._node, level, translet, this._last);
/*     */       
/* 159 */       Comparable key = StringComparable.getComparator(str, locales[level], this._collators[level], caseOrder[level]);
/*     */ 
/*     */ 
/*     */       
/* 163 */       this._values[this._scanned++] = key;
/* 164 */       return key;
/*     */     } 
/* 166 */     return (Comparable)this._values[level];
/*     */   }
/*     */ 
/*     */   
/*     */   private final Double numericValue(int level) {
/* 171 */     if (this._scanned <= level) {
/* 172 */       Double double_; AbstractTranslet translet = this._settings.getTranslet();
/*     */ 
/*     */       
/* 175 */       String str = extractValueFromDOM(this._dom, this._node, level, translet, this._last);
/*     */ 
/*     */ 
/*     */       
/* 179 */       try { double_ = new Double(str); } catch (NumberFormatException e)
/*     */       
/*     */       { 
/*     */         
/* 183 */         double_ = new Double(Double.NEGATIVE_INFINITY); }
/*     */       
/* 185 */       this._values[this._scanned++] = double_;
/* 186 */       return double_;
/*     */     } 
/* 188 */     return (Double)this._values[level];
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
/*     */   public int compareTo(NodeSortRecord other) {
/* 200 */     int[] sortOrder = this._settings.getSortOrders();
/* 201 */     int levels = (this._settings.getSortOrders()).length;
/* 202 */     int[] compareTypes = this._settings.getTypes();
/*     */     
/* 204 */     for (int level = 0; level < levels; level++) {
/*     */       int i;
/* 206 */       if (compareTypes[level] == 1) {
/* 207 */         Double our = numericValue(level);
/* 208 */         Double their = other.numericValue(level);
/* 209 */         i = our.compareTo(their);
/*     */       } else {
/*     */         
/* 212 */         Comparable our = stringValue(level);
/* 213 */         Comparable their = other.stringValue(level);
/* 214 */         i = our.compareTo(their);
/*     */       } 
/*     */ 
/*     */       
/* 218 */       if (i != 0) {
/* 219 */         return (sortOrder[level] == 1) ? (0 - i) : i;
/*     */       }
/*     */     } 
/*     */     
/* 223 */     return this._node - other._node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collator[] getCollator() {
/* 231 */     return this._collators;
/*     */   }
/*     */   
/*     */   public abstract String extractValueFromDOM(DOM paramDOM, int paramInt1, int paramInt2, AbstractTranslet paramAbstractTranslet, int paramInt3);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/NodeSortRecord.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */