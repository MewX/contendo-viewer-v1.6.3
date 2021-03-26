/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LocationPathPattern
/*     */   extends Pattern
/*     */ {
/*     */   private Template _template;
/*     */   private int _importPrecedence;
/*  36 */   private double _priority = Double.NaN;
/*  37 */   private int _position = 0;
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  40 */     return Type.Void;
/*     */   }
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ 
/*     */   
/*     */   public void setTemplate(Template template) {
/*  48 */     this._template = template;
/*  49 */     this._priority = template.getPriority();
/*  50 */     this._importPrecedence = template.getImportPrecedence();
/*  51 */     this._position = template.getPosition();
/*     */   }
/*     */   
/*     */   public Template getTemplate() {
/*  55 */     return this._template;
/*     */   }
/*     */   
/*     */   public final double getPriority() {
/*  59 */     return Double.isNaN(this._priority) ? getDefaultPriority() : this._priority;
/*     */   }
/*     */   
/*     */   public double getDefaultPriority() {
/*  63 */     return 0.5D;
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
/*     */   public boolean noSmallerThan(LocationPathPattern other) {
/*  75 */     if (this._importPrecedence > other._importPrecedence) {
/*  76 */       return true;
/*     */     }
/*  78 */     if (this._importPrecedence == other._importPrecedence) {
/*  79 */       if (this._priority > other._priority) {
/*  80 */         return true;
/*     */       }
/*  82 */       if (this._priority == other._priority && 
/*  83 */         this._position > other._position) {
/*  84 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public abstract StepPattern getKernelPattern();
/*     */   
/*     */   public abstract void reduceKernelPattern();
/*     */   
/*     */   public abstract boolean isWildcard();
/*     */   
/*     */   public int getAxis() {
/*  98 */     StepPattern sp = getKernelPattern();
/*  99 */     return (sp != null) ? sp.getAxis() : 3;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 103 */     return "root()";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LocationPathPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */