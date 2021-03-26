/*     */ package org.apache.xmlgraphics.ps.dsc.events;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
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
/*     */ public class DSCCommentPage
/*     */   extends AbstractDSCComment
/*     */ {
/*     */   private String pageName;
/*  35 */   private int pagePosition = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCCommentPage() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCCommentPage(String pageName, int pagePosition) {
/*  49 */     setPageName(pageName);
/*  50 */     setPagePosition(pagePosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCCommentPage(int pagePosition) {
/*  58 */     this(Integer.toString(pagePosition), pagePosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPageName() {
/*  66 */     return this.pageName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageName(String name) {
/*  74 */     this.pageName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPagePosition() {
/*  82 */     return this.pagePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPagePosition(int position) {
/*  90 */     if (position <= 0) {
/*  91 */       throw new IllegalArgumentException("position must be 1 or above");
/*     */     }
/*  93 */     this.pagePosition = position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 100 */     return "Page";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasValues() {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseValue(String value) {
/* 114 */     List params = splitParams(value);
/* 115 */     Iterator<String> iter = params.iterator();
/* 116 */     this.pageName = iter.next();
/* 117 */     this.pagePosition = Integer.parseInt(iter.next());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(PSGenerator gen) throws IOException {
/* 125 */     gen.writeDSCComment(getName(), new Object[] { getPageName(), Integer.valueOf(getPagePosition()) });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/DSCCommentPage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */