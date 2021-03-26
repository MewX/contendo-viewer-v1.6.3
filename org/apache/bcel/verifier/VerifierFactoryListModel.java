/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.TreeSet;
/*     */ import javax.swing.ListModel;
/*     */ import javax.swing.event.ListDataEvent;
/*     */ import javax.swing.event.ListDataListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerifierFactoryListModel
/*     */   implements ListModel, VerifierFactoryObserver
/*     */ {
/*  67 */   private ArrayList listeners = new ArrayList();
/*     */   
/*  69 */   private TreeSet cache = new TreeSet();
/*     */   
/*     */   public VerifierFactoryListModel() {
/*  72 */     VerifierFactory.attach(this);
/*  73 */     update(null);
/*     */   }
/*     */   
/*     */   public synchronized void update(String s) {
/*  77 */     int size = this.listeners.size();
/*     */     
/*  79 */     Verifier[] verifiers = VerifierFactory.getVerifiers();
/*  80 */     int num_of_verifiers = verifiers.length;
/*  81 */     this.cache.clear();
/*  82 */     for (int i = 0; i < num_of_verifiers; i++) {
/*  83 */       this.cache.add(verifiers[i].getClassName());
/*     */     }
/*     */     
/*  86 */     for (int j = 0; j < size; j++) {
/*  87 */       ListDataEvent e = new ListDataEvent(this, 0, 0, num_of_verifiers - 1);
/*  88 */       ((ListDataListener)this.listeners.get(j)).contentsChanged(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void addListDataListener(ListDataListener l) {
/*  93 */     this.listeners.add(l);
/*     */   }
/*     */   
/*     */   public synchronized void removeListDataListener(ListDataListener l) {
/*  97 */     this.listeners.remove(l);
/*     */   }
/*     */   
/*     */   public synchronized int getSize() {
/* 101 */     return this.cache.size();
/*     */   }
/*     */   
/*     */   public synchronized Object getElementAt(int index) {
/* 105 */     return this.cache.toArray()[index];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/VerifierFactoryListModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */