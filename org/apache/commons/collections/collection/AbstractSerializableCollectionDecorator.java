/*    */ package org.apache.commons.collections.collection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ public abstract class AbstractSerializableCollectionDecorator
/*    */   extends AbstractCollectionDecorator
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6249888059822088500L;
/*    */   
/*    */   protected AbstractSerializableCollectionDecorator(Collection coll) {
/* 41 */     super(coll);
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
/* 52 */     out.defaultWriteObject();
/* 53 */     out.writeObject(this.collection);
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
/* 64 */     in.defaultReadObject();
/* 65 */     this.collection = (Collection)in.readObject();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/AbstractSerializableCollectionDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */