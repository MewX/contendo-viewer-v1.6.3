/*    */ package org.apache.bcel.util;
/*    */ 
/*    */ import java.util.Stack;
/*    */ import org.apache.bcel.classfile.JavaClass;
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
/*    */ public class ClassStack
/*    */ {
/* 67 */   private Stack stack = new Stack();
/*    */   
/* 69 */   public void push(JavaClass clazz) { this.stack.push(clazz); }
/* 70 */   public JavaClass pop() { return this.stack.pop(); }
/* 71 */   public JavaClass top() { return this.stack.peek(); } public boolean empty() {
/* 72 */     return this.stack.empty();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ClassStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */