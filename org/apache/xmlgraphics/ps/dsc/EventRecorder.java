/*     */ package org.apache.xmlgraphics.ps.dsc;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
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
/*     */ public class EventRecorder
/*     */   implements DSCHandler
/*     */ {
/*  33 */   private List events = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replay(DSCHandler handler) throws IOException {
/*  41 */     Iterator iter = this.events.iterator();
/*  42 */     while (iter.hasNext()) {
/*  43 */       Object obj = iter.next();
/*  44 */       if (obj instanceof PSLine) {
/*  45 */         handler.line(((PSLine)obj).getLine()); continue;
/*  46 */       }  if (obj instanceof PSComment) {
/*  47 */         handler.comment(((PSComment)obj).getComment()); continue;
/*  48 */       }  if (obj instanceof DSCComment) {
/*  49 */         handler.handleDSCComment((DSCComment)obj); continue;
/*     */       } 
/*  51 */       throw new IllegalStateException("Unsupported class type");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(String comment) throws IOException {
/*  60 */     this.events.add(new PSComment(comment));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDSCComment(DSCComment comment) throws IOException {
/*  68 */     this.events.add(comment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void line(String line) throws IOException {
/*  75 */     this.events.add(new PSLine(line));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(String header) throws IOException {
/*  82 */     throw new UnsupportedOperationException(getClass().getName() + " is only used to handle parts of a document");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws IOException {
/*  90 */     throw new UnsupportedOperationException(getClass().getName() + " is only used to handle parts of a document");
/*     */   }
/*     */ 
/*     */   
/*     */   private static class PSComment
/*     */   {
/*     */     private String comment;
/*     */     
/*     */     public PSComment(String comment) {
/*  99 */       this.comment = comment;
/*     */     }
/*     */     
/*     */     public String getComment() {
/* 103 */       return this.comment;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class PSLine
/*     */   {
/*     */     private String line;
/*     */     
/*     */     public PSLine(String line) {
/* 112 */       this.line = line;
/*     */     }
/*     */     
/*     */     public String getLine() {
/* 116 */       return this.line;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/EventRecorder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */