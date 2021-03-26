/*    */ package org.apache.commons.collections.list;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSerializableListDecorator
/*    */   extends AbstractListDecorator
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2684959196747496299L;
/*    */   
/*    */   protected AbstractSerializableListDecorator(List list) {
/* 42 */     super(list);
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/AbstractSerializableListDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */