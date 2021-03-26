/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;
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
/*     */ public abstract class PDOutlineNode
/*     */   extends PDDictionaryWrapper
/*     */ {
/*     */   public PDOutlineNode() {}
/*     */   
/*     */   public PDOutlineNode(COSDictionary dict) {
/*  47 */     super(dict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDOutlineNode getParent() {
/*  55 */     COSBase base = getCOSObject().getDictionaryObject(COSName.PARENT);
/*  56 */     if (base instanceof COSDictionary) {
/*     */       
/*  58 */       COSDictionary parent = (COSDictionary)base;
/*  59 */       if (COSName.OUTLINES.equals(parent.getCOSName(COSName.TYPE)))
/*     */       {
/*  61 */         return new PDDocumentOutline(parent);
/*     */       }
/*  63 */       return new PDOutlineItem(parent);
/*     */     } 
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   void setParent(PDOutlineNode parent) {
/*  70 */     getCOSObject().setItem(COSName.PARENT, (COSObjectable)parent);
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
/*     */   public void addLast(PDOutlineItem newChild) {
/*  82 */     requireSingleNode(newChild);
/*  83 */     append(newChild);
/*  84 */     updateParentOpenCountForAddedChild(newChild);
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
/*     */   public void addFirst(PDOutlineItem newChild) {
/*  96 */     requireSingleNode(newChild);
/*  97 */     prepend(newChild);
/*  98 */     updateParentOpenCountForAddedChild(newChild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void requireSingleNode(PDOutlineItem node) {
/* 108 */     if (node.getNextSibling() != null || node.getPreviousSibling() != null)
/*     */     {
/* 110 */       throw new IllegalArgumentException("A single node with no siblings is required");
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
/*     */   private void append(PDOutlineItem newChild) {
/* 122 */     newChild.setParent(this);
/* 123 */     if (!hasChildren()) {
/*     */       
/* 125 */       setFirstChild(newChild);
/*     */     }
/*     */     else {
/*     */       
/* 129 */       PDOutlineItem previousLastChild = getLastChild();
/* 130 */       previousLastChild.setNextSibling(newChild);
/* 131 */       newChild.setPreviousSibling(previousLastChild);
/*     */     } 
/* 133 */     setLastChild(newChild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void prepend(PDOutlineItem newChild) {
/* 144 */     newChild.setParent(this);
/* 145 */     if (!hasChildren()) {
/*     */       
/* 147 */       setLastChild(newChild);
/*     */     }
/*     */     else {
/*     */       
/* 151 */       PDOutlineItem previousFirstChild = getFirstChild();
/* 152 */       newChild.setNextSibling(previousFirstChild);
/* 153 */       previousFirstChild.setPreviousSibling(newChild);
/*     */     } 
/* 155 */     setFirstChild(newChild);
/*     */   }
/*     */ 
/*     */   
/*     */   void updateParentOpenCountForAddedChild(PDOutlineItem newChild) {
/* 160 */     int delta = 1;
/* 161 */     if (newChild.isNodeOpen())
/*     */     {
/* 163 */       delta += newChild.getOpenCount();
/*     */     }
/* 165 */     newChild.updateParentOpenCount(delta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChildren() {
/* 173 */     return (getFirstChild() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   PDOutlineItem getOutlineItem(COSName name) {
/* 178 */     COSBase base = getCOSObject().getDictionaryObject(name);
/* 179 */     if (base instanceof COSDictionary)
/*     */     {
/* 181 */       return new PDOutlineItem((COSDictionary)base);
/*     */     }
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOutlineItem getFirstChild() {
/* 191 */     return getOutlineItem(COSName.FIRST);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFirstChild(PDOutlineNode outlineNode) {
/* 201 */     getCOSObject().setItem(COSName.FIRST, (COSObjectable)outlineNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOutlineItem getLastChild() {
/* 209 */     return getOutlineItem(COSName.LAST);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLastChild(PDOutlineNode outlineNode) {
/* 219 */     getCOSObject().setItem(COSName.LAST, (COSObjectable)outlineNode);
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
/*     */   public int getOpenCount() {
/* 231 */     return getCOSObject().getInt(COSName.COUNT, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOpenCount(int openCount) {
/* 241 */     getCOSObject().setInt(COSName.COUNT, openCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openNode() {
/* 251 */     if (!isNodeOpen())
/*     */     {
/* 253 */       switchNodeCount();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeNode() {
/* 263 */     if (isNodeOpen())
/*     */     {
/* 265 */       switchNodeCount();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void switchNodeCount() {
/* 271 */     int openCount = getOpenCount();
/* 272 */     setOpenCount(-openCount);
/* 273 */     updateParentOpenCount(-openCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNodeOpen() {
/* 281 */     return (getOpenCount() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateParentOpenCount(int delta) {
/* 291 */     PDOutlineNode parent = getParent();
/* 292 */     if (parent != null)
/*     */     {
/* 294 */       if (parent.isNodeOpen()) {
/*     */         
/* 296 */         parent.setOpenCount(parent.getOpenCount() + delta);
/* 297 */         parent.updateParentOpenCount(delta);
/*     */       }
/*     */       else {
/*     */         
/* 301 */         parent.setOpenCount(parent.getOpenCount() - delta);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<PDOutlineItem> children() {
/* 311 */     return new Iterable<PDOutlineItem>()
/*     */       {
/*     */         
/*     */         public Iterator<PDOutlineItem> iterator()
/*     */         {
/* 316 */           return new PDOutlineItemIterator(PDOutlineNode.this.getFirstChild());
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/outline/PDOutlineNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */