/*     */ package org.apache.batik.util;
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
/*     */ public class DoublyLinkedList
/*     */ {
/*     */   public static class Node
/*     */   {
/*  34 */     private Node next = null;
/*  35 */     private Node prev = null;
/*     */     
/*  37 */     public final Node getNext() { return this.next; } public final Node getPrev() {
/*  38 */       return this.prev;
/*     */     }
/*  40 */     protected final void setNext(Node newNext) { this.next = newNext; } protected final void setPrev(Node newPrev) {
/*  41 */       this.prev = newPrev;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected final void unlink() {
/*  47 */       if (getNext() != null)
/*  48 */         getNext().setPrev(getPrev()); 
/*  49 */       if (getPrev() != null) {
/*  50 */         getPrev().setNext(getNext());
/*     */       }
/*  52 */       setNext(null);
/*  53 */       setPrev(null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected final void insertBefore(Node nde) {
/*  63 */       if (this == nde)
/*     */         return; 
/*  65 */       if (getPrev() != null) {
/*  66 */         unlink();
/*     */       }
/*     */       
/*  69 */       if (nde == null) {
/*     */         
/*  71 */         setNext(this);
/*  72 */         setPrev(this);
/*     */       } else {
/*  74 */         setNext(nde);
/*  75 */         setPrev(nde.getPrev());
/*  76 */         nde.setPrev(this);
/*  77 */         if (getPrev() != null) {
/*  78 */           getPrev().setNext(this);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*  84 */   private Node head = null;
/*  85 */   private int size = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getSize() {
/*  92 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void empty() {
/*  98 */     for (; this.size > 0; pop());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getHead() {
/* 105 */     return this.head;
/*     */   }
/*     */ 
/*     */   
/*     */   public Node getTail() {
/* 110 */     return this.head.getPrev();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void touch(Node nde) {
/* 117 */     if (nde == null)
/* 118 */       return;  nde.insertBefore(this.head);
/* 119 */     this.head = nde;
/*     */   }
/*     */   
/*     */   public void add(int index, Node nde) {
/* 123 */     if (nde == null)
/* 124 */       return;  if (index == 0) {
/*     */       
/* 126 */       nde.insertBefore(this.head);
/* 127 */       this.head = nde;
/* 128 */     } else if (index == this.size) {
/*     */ 
/*     */       
/* 131 */       nde.insertBefore(this.head);
/*     */     } else {
/* 133 */       Node after = this.head;
/* 134 */       while (index != 0) {
/* 135 */         after = after.getNext();
/* 136 */         index--;
/*     */       } 
/* 138 */       nde.insertBefore(after);
/*     */     } 
/* 140 */     this.size++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Node nde) {
/* 150 */     if (nde == null)
/* 151 */       return;  nde.insertBefore(this.head);
/* 152 */     this.head = nde;
/* 153 */     this.size++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(Node nde) {
/* 164 */     if (nde == null)
/* 165 */       return;  if (nde == this.head)
/* 166 */       if (this.head.getNext() == this.head) {
/* 167 */         this.head = null;
/*     */       } else {
/* 169 */         this.head = this.head.getNext();
/*     */       }  
/* 171 */     nde.unlink();
/* 172 */     this.size--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node pop() {
/* 180 */     if (this.head == null) return null;
/*     */     
/* 182 */     Node nde = this.head;
/* 183 */     remove(nde);
/* 184 */     return nde;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node unpush() {
/* 192 */     if (this.head == null) return null;
/*     */     
/* 194 */     Node nde = getTail();
/* 195 */     remove(nde);
/* 196 */     return nde;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(Node nde) {
/* 205 */     nde.insertBefore(this.head);
/* 206 */     if (this.head == null) this.head = nde; 
/* 207 */     this.size++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpop(Node nde) {
/* 214 */     nde.insertBefore(this.head);
/* 215 */     this.head = nde;
/* 216 */     this.size++;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/DoublyLinkedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */