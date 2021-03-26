/*    */ package org.apache.commons.collections.primitives.adapters.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.apache.commons.collections.primitives.CharIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReaderCharIterator
/*    */   implements CharIterator
/*    */ {
/*    */   private Reader reader;
/*    */   private boolean nextAvailable;
/*    */   private int next;
/*    */   
/*    */   public ReaderCharIterator(Reader in) {
/* 79 */     this.reader = null;
/* 80 */     this.nextAvailable = false;
/*    */     this.reader = in;
/*    */   }
/*    */   
/*    */   public static CharIterator adapt(Reader in) {
/*    */     return (null == in) ? null : new ReaderCharIterator(in);
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     ensureNextAvailable();
/*    */     return (-1 != this.next);
/*    */   }
/*    */   
/*    */   public char next() {
/*    */     if (!hasNext())
/*    */       throw new NoSuchElementException("No next element"); 
/*    */     this.nextAvailable = false;
/*    */     return (char)this.next;
/*    */   }
/*    */   
/*    */   public void remove() throws UnsupportedOperationException {
/*    */     throw new UnsupportedOperationException("remove() is not supported here");
/*    */   }
/*    */   
/*    */   private void ensureNextAvailable() {
/*    */     if (!this.nextAvailable)
/*    */       readNext(); 
/*    */   }
/*    */   
/*    */   private void readNext() {
/*    */     try {
/*    */       this.next = this.reader.read();
/*    */       this.nextAvailable = true;
/*    */     } catch (IOException e) {
/*    */       throw new RuntimeException(e.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/io/ReaderCharIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */