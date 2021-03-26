/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Queue;
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
/*     */ public class PDFieldTree
/*     */   implements Iterable<PDField>
/*     */ {
/*     */   private final PDAcroForm acroForm;
/*     */   
/*     */   public PDFieldTree(PDAcroForm acroForm) {
/*  40 */     if (acroForm == null)
/*     */     {
/*  42 */       throw new IllegalArgumentException("root cannot be null");
/*     */     }
/*  44 */     this.acroForm = acroForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<PDField> iterator() {
/*  53 */     return new FieldIterator(this.acroForm);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class FieldIterator
/*     */     implements Iterator<PDField>
/*     */   {
/*  61 */     private final Queue<PDField> queue = new ArrayDeque<PDField>();
/*     */ 
/*     */     
/*     */     private FieldIterator(PDAcroForm form) {
/*  65 */       List<PDField> fields = form.getFields();
/*  66 */       for (PDField field : fields)
/*     */       {
/*  68 */         enqueueKids(field);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/*  75 */       return !this.queue.isEmpty();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public PDField next() {
/*  81 */       if (!hasNext()) {
/*  82 */         throw new NoSuchElementException();
/*     */       }
/*     */       
/*  85 */       return this.queue.poll();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void remove() {
/*  91 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     private void enqueueKids(PDField node) {
/*  96 */       this.queue.add(node);
/*  97 */       if (node instanceof PDNonTerminalField) {
/*     */         
/*  99 */         List<PDField> kids = ((PDNonTerminalField)node).getChildren();
/* 100 */         for (PDField kid : kids)
/*     */         {
/* 102 */           enqueueKids(kid);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDFieldTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */