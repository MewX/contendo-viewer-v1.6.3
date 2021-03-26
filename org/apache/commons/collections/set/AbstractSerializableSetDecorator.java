/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
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
/*    */ public abstract class AbstractSerializableSetDecorator
/*    */   extends AbstractSetDecorator
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1229469966212206107L;
/*    */   
/*    */   protected AbstractSerializableSetDecorator(Set set) {
/* 42 */     super(set);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 53 */     out.defaultWriteObject();
/* 54 */     out.writeObject(this.collection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 65 */     in.defaultReadObject();
/* 66 */     this.collection = (Collection)in.readObject();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/AbstractSerializableSetDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */