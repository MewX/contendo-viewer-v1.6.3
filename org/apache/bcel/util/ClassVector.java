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
/*    */ public class ClassVector
/*    */ {
/* 68 */   protected ArrayList vec = new ArrayList();
/*    */   
/* 70 */   public void addElement(JavaClass clazz) { this.vec.add(clazz); }
/* 71 */   public JavaClass elementAt(int index) { return this.vec.get(index); } public void removeElementAt(int index) {
/* 72 */     this.vec.remove(index);
/*    */   }
/*    */   public JavaClass[] toArray() {
/* 75 */     JavaClass[] classes = new JavaClass[this.vec.size()];
/* 76 */     this.vec.toArray((Object[])classes);
/* 77 */     return classes;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ClassVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */