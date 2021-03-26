/*    */ package org.apache.bcel.util;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ 
/*    */ public class ClassQueue
/*    */ {
/* 68 */   protected int left = 0;
/* 69 */   private ArrayList vec = new ArrayList();
/*    */   public void enqueue(JavaClass clazz) {
/* 71 */     this.vec.add(clazz);
/*    */   } public JavaClass dequeue() {
/* 73 */     JavaClass clazz = this.vec.get(this.left);
/* 74 */     this.vec.remove(this.left++);
/* 75 */     return clazz;
/*    */   } public boolean empty() {
/* 77 */     return (this.vec.size() <= this.left);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ClassQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */