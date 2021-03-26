/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CursorableLinkedList
/*     */   extends AbstractLinkedList
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8836393098519411393L;
/*  68 */   protected transient List cursors = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CursorableLinkedList() {
/*  76 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CursorableLinkedList(Collection coll) {
/*  85 */     super(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  93 */     super.init();
/*  94 */     this.cursors = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 108 */     return super.listIterator(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator listIterator() {
/* 127 */     return cursor(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator listIterator(int fromIndex) {
/* 147 */     return cursor(fromIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor cursor() {
/* 174 */     return cursor(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cursor cursor(int fromIndex) {
/* 205 */     Cursor cursor = new Cursor(this, fromIndex);
/* 206 */     registerCursor(cursor);
/* 207 */     return cursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateNode(AbstractLinkedList.Node node, Object value) {
/* 220 */     super.updateNode(node, value);
/* 221 */     broadcastNodeChanged(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addNode(AbstractLinkedList.Node nodeToInsert, AbstractLinkedList.Node insertBeforeNode) {
/* 232 */     super.addNode(nodeToInsert, insertBeforeNode);
/* 233 */     broadcastNodeInserted(nodeToInsert);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeNode(AbstractLinkedList.Node node) {
/* 243 */     super.removeNode(node);
/* 244 */     broadcastNodeRemoved(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeAllNodes() {
/* 251 */     if (size() > 0) {
/*     */       
/* 253 */       Iterator it = iterator();
/* 254 */       while (it.hasNext()) {
/* 255 */         it.next();
/* 256 */         it.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerCursor(Cursor cursor) {
/* 270 */     for (Iterator it = this.cursors.iterator(); it.hasNext(); ) {
/* 271 */       WeakReference ref = it.next();
/* 272 */       if (ref.get() == null) {
/* 273 */         it.remove();
/*     */       }
/*     */     } 
/* 276 */     this.cursors.add(new WeakReference(cursor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unregisterCursor(Cursor cursor) {
/* 285 */     for (Iterator it = this.cursors.iterator(); it.hasNext(); ) {
/* 286 */       WeakReference ref = it.next();
/* 287 */       Cursor cur = ref.get();
/* 288 */       if (cur == null) {
/*     */ 
/*     */ 
/*     */         
/* 292 */         it.remove(); continue;
/*     */       } 
/* 294 */       if (cur == cursor) {
/* 295 */         ref.clear();
/* 296 */         it.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void broadcastNodeChanged(AbstractLinkedList.Node node) {
/* 310 */     Iterator it = this.cursors.iterator();
/* 311 */     while (it.hasNext()) {
/* 312 */       WeakReference ref = it.next();
/* 313 */       Cursor cursor = ref.get();
/* 314 */       if (cursor == null) {
/* 315 */         it.remove(); continue;
/*     */       } 
/* 317 */       cursor.nodeChanged(node);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void broadcastNodeRemoved(AbstractLinkedList.Node node) {
/* 329 */     Iterator it = this.cursors.iterator();
/* 330 */     while (it.hasNext()) {
/* 331 */       WeakReference ref = it.next();
/* 332 */       Cursor cursor = ref.get();
/* 333 */       if (cursor == null) {
/* 334 */         it.remove(); continue;
/*     */       } 
/* 336 */       cursor.nodeRemoved(node);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void broadcastNodeInserted(AbstractLinkedList.Node node) {
/* 348 */     Iterator it = this.cursors.iterator();
/* 349 */     while (it.hasNext()) {
/* 350 */       WeakReference ref = it.next();
/* 351 */       Cursor cursor = ref.get();
/* 352 */       if (cursor == null) {
/* 353 */         it.remove(); continue;
/*     */       } 
/* 355 */       cursor.nodeInserted(node);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 365 */     out.defaultWriteObject();
/* 366 */     doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 373 */     in.defaultReadObject();
/* 374 */     doReadObject(in);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Cursor
/*     */     extends AbstractLinkedList.LinkedListIterator
/*     */   {
/*     */     boolean valid = true;
/*     */ 
/*     */ 
/*     */     
/*     */     boolean nextIndexValid = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Cursor(CursorableLinkedList parent, int index) {
/* 394 */       super(parent, index);
/* 395 */       this.valid = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void add(Object obj) {
/* 405 */       super.add(obj);
/*     */       
/* 407 */       this.next = this.next.next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int nextIndex() {
/* 416 */       if (!this.nextIndexValid) {
/* 417 */         if (this.next == this.parent.header) {
/* 418 */           this.nextIndex = this.parent.size();
/*     */         } else {
/* 420 */           int pos = 0;
/* 421 */           AbstractLinkedList.Node temp = this.parent.header.next;
/* 422 */           while (temp != this.next) {
/* 423 */             pos++;
/* 424 */             temp = temp.next;
/*     */           } 
/* 426 */           this.nextIndex = pos;
/*     */         } 
/* 428 */         this.nextIndexValid = true;
/*     */       } 
/* 430 */       return this.nextIndex;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void nodeChanged(AbstractLinkedList.Node node) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void nodeRemoved(AbstractLinkedList.Node node) {
/* 448 */       if (node == this.next) {
/* 449 */         this.next = node.next;
/* 450 */       } else if (node == this.current) {
/* 451 */         this.current = null;
/* 452 */         this.nextIndex--;
/*     */       } else {
/* 454 */         this.nextIndexValid = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void nodeInserted(AbstractLinkedList.Node node) {
/* 464 */       if (node.previous == this.current) {
/* 465 */         this.next = node;
/* 466 */       } else if (this.next.previous == node) {
/* 467 */         this.next = node;
/*     */       } else {
/* 469 */         this.nextIndexValid = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void checkModCount() {
/* 477 */       if (!this.valid) {
/* 478 */         throw new ConcurrentModificationException("Cursor closed");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() {
/* 491 */       if (this.valid) {
/* 492 */         ((CursorableLinkedList)this.parent).unregisterCursor(this);
/* 493 */         this.valid = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/CursorableLinkedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */