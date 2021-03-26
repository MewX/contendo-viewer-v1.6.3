/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import java.util.Stack;
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
/*    */ public final class StringStack
/*    */   extends Stack
/*    */ {
/*    */   public String peekString() {
/* 30 */     return (String)peek();
/*    */   }
/*    */   
/*    */   public String popString() {
/* 34 */     return (String)pop();
/*    */   }
/*    */   
/*    */   public String pushString(String val) {
/* 38 */     return (String)push((E)val);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/StringStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */