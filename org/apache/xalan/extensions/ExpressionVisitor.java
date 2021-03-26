/*    */ package org.apache.xalan.extensions;
/*    */ 
/*    */ import org.apache.xalan.templates.StylesheetRoot;
/*    */ import org.apache.xpath.ExpressionOwner;
/*    */ import org.apache.xpath.XPathVisitor;
/*    */ import org.apache.xpath.functions.FuncExtFunction;
/*    */ import org.apache.xpath.functions.FuncExtFunctionAvailable;
/*    */ import org.apache.xpath.functions.Function;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpressionVisitor
/*    */   extends XPathVisitor
/*    */ {
/*    */   private StylesheetRoot m_sroot;
/*    */   
/*    */   public ExpressionVisitor(StylesheetRoot sroot) {
/* 49 */     this.m_sroot = sroot;
/*    */   }
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
/*    */   public boolean visitFunction(ExpressionOwner owner, Function func) {
/* 62 */     if (func instanceof FuncExtFunction) {
/*    */       
/* 64 */       String namespace = ((FuncExtFunction)func).getNamespace();
/* 65 */       this.m_sroot.getExtensionNamespacesManager().registerExtension(namespace);
/*    */     }
/* 67 */     else if (func instanceof FuncExtFunctionAvailable) {
/*    */       
/* 69 */       String arg = ((FuncExtFunctionAvailable)func).getArg0().toString();
/* 70 */       if (arg.indexOf(":") > 0) {
/*    */         
/* 72 */         String prefix = arg.substring(0, arg.indexOf(":"));
/* 73 */         String namespace = this.m_sroot.getNamespaceForPrefix(prefix);
/* 74 */         this.m_sroot.getExtensionNamespacesManager().registerExtension(namespace);
/*    */       } 
/*    */     } 
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExpressionVisitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */