/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ public class PDPageTree
/*     */   implements Iterable<PDPage>, COSObjectable
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(PDPageTree.class);
/*     */ 
/*     */   
/*     */   private final COSDictionary root;
/*     */   
/*     */   private final PDDocument document;
/*     */ 
/*     */   
/*     */   public PDPageTree() {
/*  51 */     this.root = new COSDictionary();
/*  52 */     this.root.setItem(COSName.TYPE, (COSBase)COSName.PAGES);
/*  53 */     this.root.setItem(COSName.KIDS, (COSBase)new COSArray());
/*  54 */     this.root.setItem(COSName.COUNT, (COSBase)COSInteger.ZERO);
/*  55 */     this.document = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageTree(COSDictionary root) {
/*  65 */     this(root, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDPageTree(COSDictionary root, PDDocument document) {
/*  76 */     if (root == null)
/*     */     {
/*  78 */       throw new IllegalArgumentException("page tree root cannot be null");
/*     */     }
/*     */     
/*  81 */     if (COSName.PAGE.equals(root.getCOSName(COSName.TYPE))) {
/*     */       
/*  83 */       COSArray kids = new COSArray();
/*  84 */       kids.add((COSBase)root);
/*  85 */       this.root = new COSDictionary();
/*  86 */       this.root.setItem(COSName.KIDS, (COSBase)kids);
/*  87 */       this.root.setInt(COSName.COUNT, 1);
/*     */     }
/*     */     else {
/*     */       
/*  91 */       this.root = root;
/*     */     } 
/*  93 */     this.document = document;
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
/*     */   public static COSBase getInheritableAttribute(COSDictionary node, COSName key) {
/* 105 */     COSBase value = node.getDictionaryObject(key);
/* 106 */     if (value != null)
/*     */     {
/* 108 */       return value;
/*     */     }
/*     */     
/* 111 */     COSBase base = node.getDictionaryObject(COSName.PARENT, COSName.P);
/* 112 */     if (base instanceof COSDictionary) {
/*     */       
/* 114 */       COSDictionary parent = (COSDictionary)base;
/* 115 */       if (COSName.PAGES.equals(parent.getDictionaryObject(COSName.TYPE)))
/*     */       {
/* 117 */         return getInheritableAttribute(parent, key);
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<PDPage> iterator() {
/* 130 */     return new PageIterator(this.root);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<COSDictionary> getKids(COSDictionary node) {
/* 140 */     List<COSDictionary> result = new ArrayList<COSDictionary>();
/*     */     
/* 142 */     COSArray kids = node.getCOSArray(COSName.KIDS);
/* 143 */     if (kids == null)
/*     */     {
/*     */       
/* 146 */       return result;
/*     */     }
/*     */     
/* 149 */     for (int i = 0, size = kids.size(); i < size; i++) {
/*     */       
/* 151 */       COSBase base = kids.getObject(i);
/* 152 */       if (base instanceof COSDictionary) {
/*     */         
/* 154 */         result.add((COSDictionary)base);
/*     */       }
/*     */       else {
/*     */         
/* 158 */         LOG.warn("COSDictionary expected, but got " + ((base == null) ? "null" : base
/* 159 */             .getClass().getSimpleName()));
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final class PageIterator
/*     */     implements Iterator<PDPage>
/*     */   {
/* 171 */     private final Queue<COSDictionary> queue = new ArrayDeque<COSDictionary>();
/*     */ 
/*     */     
/*     */     private PageIterator(COSDictionary node) {
/* 175 */       enqueueKids(node);
/*     */     }
/*     */ 
/*     */     
/*     */     private void enqueueKids(COSDictionary node) {
/* 180 */       if (PDPageTree.this.isPageTreeNode(node)) {
/*     */         
/* 182 */         List<COSDictionary> kids = PDPageTree.this.getKids(node);
/* 183 */         for (COSDictionary kid : kids)
/*     */         {
/* 185 */           enqueueKids(kid);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 190 */         this.queue.add(node);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 197 */       return !this.queue.isEmpty();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public PDPage next() {
/* 203 */       COSDictionary next = this.queue.poll();
/*     */       
/* 205 */       PDPageTree.sanitizeType(next);
/*     */       
/* 207 */       ResourceCache resourceCache = (PDPageTree.this.document != null) ? PDPageTree.this.document.getResourceCache() : null;
/* 208 */       return new PDPage(next, resourceCache);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void remove() {
/* 214 */       throw new UnsupportedOperationException();
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
/*     */   public PDPage get(int index) {
/* 227 */     COSDictionary dict = get(index + 1, this.root, 0);
/*     */     
/* 229 */     sanitizeType(dict);
/*     */     
/* 231 */     ResourceCache resourceCache = (this.document != null) ? this.document.getResourceCache() : null;
/* 232 */     return new PDPage(dict, resourceCache);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void sanitizeType(COSDictionary dictionary) {
/* 237 */     COSName type = dictionary.getCOSName(COSName.TYPE);
/* 238 */     if (type == null) {
/*     */       
/* 240 */       dictionary.setItem(COSName.TYPE, (COSBase)COSName.PAGE);
/*     */       return;
/*     */     } 
/* 243 */     if (!COSName.PAGE.equals(type))
/*     */     {
/* 245 */       throw new IllegalStateException("Expected 'Page' but found " + type);
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
/*     */   
/*     */   private COSDictionary get(int pageNum, COSDictionary node, int encountered) {
/* 259 */     if (pageNum < 0)
/*     */     {
/* 261 */       throw new IndexOutOfBoundsException("Index out of bounds: " + pageNum);
/*     */     }
/*     */     
/* 264 */     if (isPageTreeNode(node)) {
/*     */       
/* 266 */       int count = node.getInt(COSName.COUNT, 0);
/* 267 */       if (pageNum <= encountered + count) {
/*     */ 
/*     */         
/* 270 */         for (COSDictionary kid : getKids(node)) {
/*     */ 
/*     */           
/* 273 */           if (isPageTreeNode(kid)) {
/*     */             
/* 275 */             int kidCount = kid.getInt(COSName.COUNT, 0);
/* 276 */             if (pageNum <= encountered + kidCount)
/*     */             {
/*     */               
/* 279 */               return get(pageNum, kid, encountered);
/*     */             }
/*     */ 
/*     */             
/* 283 */             encountered += kidCount;
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 289 */           encountered++;
/* 290 */           if (pageNum == encountered)
/*     */           {
/*     */             
/* 293 */             return get(pageNum, kid, encountered);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 298 */         throw new IllegalStateException("Index not found: " + pageNum);
/*     */       } 
/*     */ 
/*     */       
/* 302 */       throw new IndexOutOfBoundsException("Index out of bounds: " + pageNum);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 307 */     if (encountered == pageNum)
/*     */     {
/* 309 */       return node;
/*     */     }
/*     */ 
/*     */     
/* 313 */     throw new IllegalStateException("Index not found: " + pageNum);
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
/*     */   private boolean isPageTreeNode(COSDictionary node) {
/* 325 */     return (node != null && (node
/* 326 */       .getCOSName(COSName.TYPE) == COSName.PAGES || node.containsKey(COSName.KIDS)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(PDPage page) {
/* 337 */     SearchContext context = new SearchContext(page);
/* 338 */     if (findPage(context, this.root))
/*     */     {
/* 340 */       return context.index;
/*     */     }
/* 342 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean findPage(SearchContext context, COSDictionary node) {
/* 347 */     for (COSDictionary kid : getKids(node)) {
/*     */       
/* 349 */       if (context.found) {
/*     */         break;
/*     */       }
/*     */       
/* 353 */       if (isPageTreeNode(kid)) {
/*     */         
/* 355 */         findPage(context, kid);
/*     */         
/*     */         continue;
/*     */       } 
/* 359 */       context.visitPage(kid);
/*     */     } 
/*     */     
/* 362 */     return context.found;
/*     */   }
/*     */   
/*     */   private static final class SearchContext
/*     */   {
/*     */     private final COSDictionary searched;
/* 368 */     private int index = -1;
/*     */     
/*     */     private boolean found;
/*     */     
/*     */     private SearchContext(PDPage page) {
/* 373 */       this.searched = page.getCOSObject();
/*     */     }
/*     */ 
/*     */     
/*     */     private void visitPage(COSDictionary current) {
/* 378 */       this.index++;
/* 379 */       this.found = this.searched.equals(current);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 390 */     return this.root.getInt(COSName.COUNT, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 396 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 405 */     COSDictionary node = get(index + 1, this.root, 0);
/* 406 */     remove(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(PDPage page) {
/* 416 */     remove(page.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void remove(COSDictionary node) {
/* 425 */     COSDictionary parent = (COSDictionary)node.getDictionaryObject(COSName.PARENT, COSName.P);
/* 426 */     COSArray kids = (COSArray)parent.getDictionaryObject(COSName.KIDS);
/* 427 */     if (kids.removeObject((COSBase)node)) {
/*     */       do
/*     */       {
/*     */ 
/*     */         
/* 432 */         node = (COSDictionary)node.getDictionaryObject(COSName.PARENT, COSName.P);
/* 433 */         if (node == null)
/*     */           continue; 
/* 435 */         node.setInt(COSName.COUNT, node.getInt(COSName.COUNT) - 1);
/*     */       
/*     */       }
/* 438 */       while (node != null);
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
/*     */   public void add(PDPage page) {
/* 450 */     COSDictionary node = page.getCOSObject();
/* 451 */     node.setItem(COSName.PARENT, (COSBase)this.root);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 456 */     COSArray kids = (COSArray)this.root.getDictionaryObject(COSName.KIDS);
/* 457 */     kids.add((COSBase)node);
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 462 */       node = (COSDictionary)node.getDictionaryObject(COSName.PARENT, COSName.P);
/* 463 */       if (node == null)
/*     */         continue; 
/* 465 */       node.setInt(COSName.COUNT, node.getInt(COSName.COUNT) + 1);
/*     */     
/*     */     }
/* 468 */     while (node != null);
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
/*     */   public void insertBefore(PDPage newPage, PDPage nextPage) {
/* 481 */     COSDictionary nextPageDict = nextPage.getCOSObject();
/* 482 */     COSDictionary parentDict = (COSDictionary)nextPageDict.getDictionaryObject(COSName.PARENT);
/* 483 */     COSArray kids = (COSArray)parentDict.getDictionaryObject(COSName.KIDS);
/* 484 */     boolean found = false;
/* 485 */     for (int i = 0; i < kids.size(); i++) {
/*     */       
/* 487 */       COSDictionary pageDict = (COSDictionary)kids.getObject(i);
/* 488 */       if (pageDict.equals(nextPage.getCOSObject())) {
/*     */         
/* 490 */         kids.add(i, (COSBase)newPage.getCOSObject());
/* 491 */         newPage.getCOSObject().setItem(COSName.PARENT, (COSBase)parentDict);
/* 492 */         found = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 496 */     if (!found)
/*     */     {
/* 498 */       throw new IllegalArgumentException("attempted to insert before orphan page");
/*     */     }
/* 500 */     increaseParents(parentDict);
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
/*     */   public void insertAfter(PDPage newPage, PDPage prevPage) {
/* 513 */     COSDictionary prevPageDict = prevPage.getCOSObject();
/* 514 */     COSDictionary parentDict = (COSDictionary)prevPageDict.getDictionaryObject(COSName.PARENT);
/* 515 */     COSArray kids = (COSArray)parentDict.getDictionaryObject(COSName.KIDS);
/* 516 */     boolean found = false;
/* 517 */     for (int i = 0; i < kids.size(); i++) {
/*     */       
/* 519 */       COSDictionary pageDict = (COSDictionary)kids.getObject(i);
/* 520 */       if (pageDict.equals(prevPage.getCOSObject())) {
/*     */         
/* 522 */         kids.add(i + 1, (COSBase)newPage.getCOSObject());
/* 523 */         newPage.getCOSObject().setItem(COSName.PARENT, (COSBase)parentDict);
/* 524 */         found = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 528 */     if (!found)
/*     */     {
/* 530 */       throw new IllegalArgumentException("attempted to insert before orphan page");
/*     */     }
/* 532 */     increaseParents(parentDict);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void increaseParents(COSDictionary parentDict) {
/*     */     do {
/* 539 */       int cnt = parentDict.getInt(COSName.COUNT);
/* 540 */       parentDict.setInt(COSName.COUNT, cnt + 1);
/* 541 */       parentDict = (COSDictionary)parentDict.getDictionaryObject(COSName.PARENT);
/*     */     }
/* 543 */     while (parentDict != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDPageTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */