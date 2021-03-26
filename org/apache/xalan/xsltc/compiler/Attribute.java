/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.util.Util;
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
/*    */ final class Attribute
/*    */   extends Instruction
/*    */ {
/*    */   private QName _name;
/*    */   
/*    */   public void display(int indent) {
/* 32 */     indent(indent);
/* 33 */     Util.println("Attribute " + this._name);
/* 34 */     displayContents(indent + 4);
/*    */   }
/*    */   
/*    */   public void parseContents(Parser parser) {
/* 38 */     this._name = parser.getQName(getAttribute("name"));
/* 39 */     parseChildren(parser);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Attribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */