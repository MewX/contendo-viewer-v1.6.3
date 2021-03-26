/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class When
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _test;
/*     */   private boolean _ignore = false;
/*     */   
/*     */   public void display(int indent) {
/*  41 */     indent(indent);
/*  42 */     Util.println("When");
/*  43 */     indent(indent + 4);
/*  44 */     System.out.print("test ");
/*  45 */     Util.println(this._test.toString());
/*  46 */     displayContents(indent + 4);
/*     */   }
/*     */   
/*     */   public Expression getTest() {
/*  50 */     return this._test;
/*     */   }
/*     */   
/*     */   public boolean ignore() {
/*  54 */     return this._ignore;
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  58 */     this._test = parser.parseExpression(this, "test", null);
/*     */ 
/*     */ 
/*     */     
/*  62 */     Object result = this._test.evaluateAtCompileTime();
/*  63 */     if (result != null && result instanceof Boolean) {
/*  64 */       this._ignore = !((Boolean)result).booleanValue();
/*     */     }
/*     */     
/*  67 */     parseChildren(parser);
/*     */ 
/*     */     
/*  70 */     if (this._test.isDummy()) {
/*  71 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "test");
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
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  84 */     if (!(this._test.typeCheck(stable) instanceof org.apache.xalan.xsltc.compiler.util.BooleanType)) {
/*  85 */       this._test = new CastExpr(this._test, Type.Boolean);
/*     */     }
/*     */     
/*  88 */     if (!this._ignore) {
/*  89 */       typeCheckContents(stable);
/*     */     }
/*     */     
/*  92 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 100 */     ErrorMsg msg = new ErrorMsg("STRAY_WHEN_ERR", this);
/* 101 */     getParser().reportError(3, msg);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/When.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */