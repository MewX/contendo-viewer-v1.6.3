/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NamespaceAlias
/*    */   extends TopLevelElement
/*    */ {
/*    */   private String sPrefix;
/*    */   private String rPrefix;
/*    */   
/*    */   public void parseContents(Parser parser) {
/* 41 */     this.sPrefix = getAttribute("stylesheet-prefix");
/* 42 */     this.rPrefix = getAttribute("result-prefix");
/* 43 */     parser.getSymbolTable().addPrefixAlias(this.sPrefix, this.rPrefix);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 47 */     return Type.Void;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NamespaceAlias.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */