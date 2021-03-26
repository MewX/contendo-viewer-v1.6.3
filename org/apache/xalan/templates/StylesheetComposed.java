/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StylesheetComposed
/*     */   extends Stylesheet
/*     */ {
/*     */   private int m_importNumber;
/*     */   private int m_importCountComposed;
/*     */   private int m_endImportCountComposed;
/*     */   private transient Vector m_includesComposed;
/*     */   
/*     */   public StylesheetComposed(Stylesheet parent) {
/*  53 */     super(parent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.m_importNumber = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAggregatedType() {
/*     */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void recomposeImports() {
/* 182 */     this.m_importNumber = getStylesheetRoot().getImportNumber(this);
/*     */     
/* 184 */     StylesheetRoot root = getStylesheetRoot();
/* 185 */     int globalImportCount = root.getGlobalImportCount();
/*     */     
/* 187 */     this.m_importCountComposed = globalImportCount - this.m_importNumber - 1;
/*     */ 
/*     */     
/* 190 */     int count = getImportCount();
/* 191 */     if (count > 0) {
/*     */       
/* 193 */       this.m_endImportCountComposed += count;
/* 194 */       while (count > 0) {
/* 195 */         this.m_endImportCountComposed += getImport(--count).getEndImportCountComposed();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 200 */     count = getIncludeCountComposed();
/* 201 */     while (count > 0) {
/*     */       
/* 203 */       int imports = getIncludeComposed(--count).getImportCount();
/* 204 */       this.m_endImportCountComposed += imports;
/* 205 */       while (imports > 0) {
/* 206 */         this.m_endImportCountComposed += getIncludeComposed(count).getImport(--imports).getEndImportCountComposed();
/*     */       }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public StylesheetComposed getImportComposed(int i) throws ArrayIndexOutOfBoundsException {
/* 225 */     StylesheetRoot root = getStylesheetRoot();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     return root.getGlobalImport(1 + this.m_importNumber + i);
/*     */   } public void recompose(Vector recomposableElements) throws TransformerException { int n = getIncludeCountComposed(); for (int i = -1; i < n; i++) {
/*     */       Stylesheet included = getIncludeComposed(i); int s = included.getOutputCount(); for (int j = 0; j < s; j++)
/*     */         recomposableElements.addElement(included.getOutput(j));  s = included.getAttributeSetCount(); for (int k = 0; k < s; k++)
/*     */         recomposableElements.addElement(included.getAttributeSet(k));  s = included.getDecimalFormatCount(); for (int m = 0; m < s; m++)
/*     */         recomposableElements.addElement(included.getDecimalFormat(m));  s = included.getKeyCount(); for (int i1 = 0; i1 < s; i1++)
/*     */         recomposableElements.addElement(included.getKey(i1));  s = included.getNamespaceAliasCount(); for (int i2 = 0; i2 < s; i2++)
/*     */         recomposableElements.addElement(included.getNamespaceAlias(i2));  s = included.getTemplateCount(); for (int i3 = 0; i3 < s; i3++)
/*     */         recomposableElements.addElement(included.getTemplate(i3));  s = included.getVariableOrParamCount(); for (int i4 = 0; i4 < s; i4++)
/*     */         recomposableElements.addElement(included.getVariableOrParam(i4));  s = included.getStripSpaceCount(); for (int i5 = 0; i5 < s; i5++)
/*     */         recomposableElements.addElement(included.getStripSpace(i5));  s = included.getPreserveSpaceCount(); for (int i6 = 0; i6 < s; i6++)
/*     */         recomposableElements.addElement(included.getPreserveSpace(i6)); 
/* 243 */     }  } public int getImportCountComposed() { return this.m_importCountComposed; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndImportCountComposed() {
/* 253 */     return this.m_endImportCountComposed;
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
/*     */   
/*     */   void recomposeIncludes(Stylesheet including) {
/* 272 */     int n = including.getIncludeCount();
/*     */     
/* 274 */     if (n > 0) {
/*     */       
/* 276 */       if (null == this.m_includesComposed) {
/* 277 */         this.m_includesComposed = new Vector();
/*     */       }
/* 279 */       for (int i = 0; i < n; i++) {
/*     */         
/* 281 */         Stylesheet included = including.getInclude(i);
/* 282 */         this.m_includesComposed.addElement(included);
/* 283 */         recomposeIncludes(included);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Stylesheet getIncludeComposed(int i) throws ArrayIndexOutOfBoundsException {
/* 302 */     if (-1 == i) {
/* 303 */       return this;
/*     */     }
/* 305 */     if (null == this.m_includesComposed) {
/* 306 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/* 308 */     return this.m_includesComposed.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIncludeCountComposed() {
/* 319 */     return (null != this.m_includesComposed) ? this.m_includesComposed.size() : 0;
/*     */   }
/*     */   
/*     */   public void recomposeTemplates(boolean flushFirst) throws TransformerException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/StylesheetComposed.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */