/*      */ package org.apache.batik.ext.awt.geom;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RectListManager
/*      */   implements Collection
/*      */ {
/*   42 */   Rectangle[] rects = null;
/*   43 */   int size = 0;
/*      */   
/*   45 */   Rectangle bounds = null;
/*      */   
/*      */   public void dump() {
/*   48 */     System.err.println("RLM: " + this + " Sz: " + this.size);
/*   49 */     System.err.println("Bounds: " + getBounds());
/*   50 */     for (int i = 0; i < this.size; i++) {
/*   51 */       Rectangle r = this.rects[i];
/*   52 */       System.err.println("  [" + r.x + ", " + r.y + ", " + r.width + ", " + r.height + ']');
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   61 */   public static Comparator comparator = new RectXComparator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(Collection rects) {
/*   68 */     this.rects = new Rectangle[rects.size()];
/*   69 */     Iterator<Rectangle> i = rects.iterator();
/*   70 */     int j = 0;
/*   71 */     while (i.hasNext())
/*   72 */       this.rects[j++] = i.next(); 
/*   73 */     this.size = this.rects.length;
/*      */ 
/*      */     
/*   76 */     Arrays.sort(this.rects, comparator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(Rectangle[] rects) {
/*   86 */     this(rects, 0, rects.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(Rectangle[] rects, int off, int sz) {
/*   98 */     this.size = sz;
/*   99 */     this.rects = new Rectangle[sz];
/*  100 */     System.arraycopy(rects, off, this.rects, 0, sz);
/*  101 */     Arrays.sort(this.rects, comparator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(RectListManager rlm) {
/*  110 */     this(rlm.rects);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(Rectangle rect) {
/*  118 */     this();
/*  119 */     add(rect);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager() {
/*  127 */     this.rects = new Rectangle[10];
/*  128 */     this.size = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager(int capacity) {
/*  138 */     this.rects = new Rectangle[capacity];
/*      */   }
/*      */   
/*      */   public Rectangle getBounds() {
/*  142 */     if (this.bounds != null)
/*  143 */       return this.bounds; 
/*  144 */     if (this.size == 0) return null; 
/*  145 */     this.bounds = new Rectangle(this.rects[0]);
/*  146 */     for (int i = 1; i < this.size; i++) {
/*  147 */       Rectangle r = this.rects[i];
/*  148 */       if (r.x < this.bounds.x) {
/*  149 */         this.bounds.width = this.bounds.x + this.bounds.width - r.x;
/*  150 */         this.bounds.x = r.x;
/*      */       } 
/*  152 */       if (r.y < this.bounds.y) {
/*  153 */         this.bounds.height = this.bounds.y + this.bounds.height - r.y;
/*  154 */         this.bounds.y = r.y;
/*      */       } 
/*  156 */       if (r.x + r.width > this.bounds.x + this.bounds.width)
/*  157 */         this.bounds.width = r.x + r.width - this.bounds.x; 
/*  158 */       if (r.y + r.height > this.bounds.y + this.bounds.height)
/*  159 */         this.bounds.height = r.y + r.height - this.bounds.y; 
/*      */     } 
/*  161 */     return this.bounds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  168 */     return copy();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RectListManager copy() {
/*  177 */     return new RectListManager(this.rects);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  183 */     return this.size;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  189 */     return (this.size == 0);
/*      */   }
/*      */   public void clear() {
/*  192 */     Arrays.fill((Object[])this.rects, (Object)null);
/*  193 */     this.size = 0;
/*  194 */     this.bounds = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator iterator() {
/*  201 */     return new RLMIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ListIterator listIterator() {
/*  209 */     return new RLMIterator();
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/*  213 */     Rectangle[] arrayOfRectangle = new Rectangle[this.size];
/*  214 */     System.arraycopy(this.rects, 0, arrayOfRectangle, 0, this.size);
/*  215 */     return (Object[])arrayOfRectangle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/*      */     Rectangle[] arrayOfRectangle;
/*  228 */     Class<?> t = a.getClass().getComponentType();
/*  229 */     if (t != Object.class && t != Rectangle.class) {
/*      */ 
/*      */       
/*  232 */       Arrays.fill(a, (Object)null);
/*  233 */       return a;
/*      */     } 
/*      */     
/*  236 */     if (a.length < this.size)
/*  237 */       arrayOfRectangle = new Rectangle[this.size]; 
/*  238 */     System.arraycopy(this.rects, 0, arrayOfRectangle, 0, this.size);
/*  239 */     Arrays.fill((Object[])arrayOfRectangle, this.size, arrayOfRectangle.length, (Object)null);
/*      */     
/*  241 */     return (Object[])arrayOfRectangle;
/*      */   }
/*      */   
/*      */   public boolean add(Object o) {
/*  245 */     add((Rectangle)o);
/*  246 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(Rectangle rect) {
/*  254 */     add(rect, 0, this.size - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void add(Rectangle rect, int l, int r) {
/*  268 */     ensureCapacity(this.size + 1);
/*  269 */     int idx = l;
/*  270 */     while (l <= r) {
/*  271 */       idx = (l + r) / 2;
/*  272 */       for (; this.rects[idx] == null && idx < r; idx++);
/*  273 */       if (this.rects[idx] == null) {
/*      */         
/*  275 */         r = (l + r) / 2;
/*  276 */         idx = (l + r) / 2;
/*  277 */         if (l > r)
/*  278 */           idx = l; 
/*  279 */         for (; this.rects[idx] == null && idx > l; idx--);
/*  280 */         if (this.rects[idx] == null) {
/*  281 */           this.rects[idx] = rect;
/*      */           return;
/*      */         } 
/*      */       } 
/*  285 */       if (rect.x == (this.rects[idx]).x)
/*  286 */         break;  if (rect.x < (this.rects[idx]).x) {
/*  287 */         if (idx == 0 || (
/*  288 */           this.rects[idx - 1] != null && rect.x >= (this.rects[idx - 1]).x))
/*      */           break; 
/*  290 */         r = idx - 1; continue;
/*      */       } 
/*  292 */       if (idx == this.size - 1) { idx++; break; }
/*  293 */        if (this.rects[idx + 1] != null && rect.x <= (this.rects[idx + 1]).x) {
/*  294 */         idx++; break;
/*  295 */       }  l = idx + 1;
/*      */     } 
/*      */ 
/*      */     
/*  299 */     if (idx < this.size) {
/*  300 */       System.arraycopy(this.rects, idx, this.rects, idx + 1, this.size - idx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  311 */     this.rects[idx] = rect;
/*  312 */     this.size++;
/*  313 */     this.bounds = null;
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/*  317 */     if (c instanceof RectListManager) {
/*  318 */       add((RectListManager)c);
/*      */     } else {
/*  320 */       add(new RectListManager(c));
/*      */     } 
/*      */     
/*  323 */     return (c.size() != 0);
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/*  327 */     Rectangle rect = (Rectangle)o;
/*  328 */     int l = 0, r = this.size - 1, idx = 0;
/*  329 */     while (l <= r) {
/*  330 */       idx = l + r >>> 1;
/*  331 */       if (rect.x == (this.rects[idx]).x)
/*  332 */         break;  if (rect.x < (this.rects[idx]).x) {
/*  333 */         if (idx == 0 || 
/*  334 */           rect.x >= (this.rects[idx - 1]).x)
/*  335 */           break;  r = idx - 1; continue;
/*      */       } 
/*  337 */       if (idx == this.size - 1) { idx++; break; }
/*  338 */        if (rect.x <= (this.rects[idx + 1]).x) { idx++; break; }
/*  339 */        l = idx + 1;
/*      */     } 
/*      */ 
/*      */     
/*  343 */     if ((this.rects[idx]).x != rect.x) return false;
/*      */     
/*      */     int i;
/*  346 */     for (i = idx; i >= 0; i--) {
/*  347 */       if (this.rects[idx].equals(rect)) return true; 
/*  348 */       if ((this.rects[idx]).x != rect.x) {
/*      */         break;
/*      */       }
/*      */     } 
/*  352 */     for (i = idx + 1; i < this.size; i++) {
/*  353 */       if (this.rects[idx].equals(rect)) return true; 
/*  354 */       if ((this.rects[idx]).x != rect.x) {
/*      */         break;
/*      */       }
/*      */     } 
/*  358 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAll(Collection c) {
/*  366 */     if (c instanceof RectListManager)
/*  367 */       return containsAll((RectListManager)c); 
/*  368 */     return containsAll(new RectListManager(c));
/*      */   }
/*      */   
/*      */   public boolean containsAll(RectListManager rlm) {
/*  372 */     int xChange = 0;
/*  373 */     for (int j = 0, i = 0; j < rlm.size; j++) {
/*  374 */       i = xChange;
/*  375 */       while ((this.rects[i]).x < (rlm.rects[j]).x) {
/*  376 */         i++;
/*  377 */         if (i == this.size) return false; 
/*      */       } 
/*  379 */       xChange = i;
/*  380 */       int x = (this.rects[i]).x;
/*  381 */       while (!rlm.rects[j].equals(this.rects[i])) {
/*  382 */         i++;
/*  383 */         if (i == this.size) return false; 
/*  384 */         if (x != (this.rects[i]).x)
/*  385 */           return false; 
/*      */       } 
/*      */     } 
/*  388 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Object o) {
/*  397 */     return remove((Rectangle)o);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean remove(Rectangle rect) {
/*  406 */     int l = 0, r = this.size - 1, idx = 0;
/*  407 */     while (l <= r) {
/*  408 */       idx = l + r >>> 1;
/*  409 */       if (rect.x == (this.rects[idx]).x)
/*  410 */         break;  if (rect.x < (this.rects[idx]).x) {
/*  411 */         if (idx == 0 || 
/*  412 */           rect.x >= (this.rects[idx - 1]).x)
/*  413 */           break;  r = idx - 1; continue;
/*      */       } 
/*  415 */       if (idx == this.size - 1) { idx++; break; }
/*  416 */        if (rect.x <= (this.rects[idx + 1]).x) { idx++; break; }
/*  417 */        l = idx + 1;
/*      */     } 
/*      */ 
/*      */     
/*  421 */     if ((this.rects[idx]).x != rect.x) return false;
/*      */     
/*      */     int i;
/*  424 */     for (i = idx; i >= 0; i--) {
/*  425 */       if (this.rects[idx].equals(rect)) {
/*  426 */         System.arraycopy(this.rects, idx + 1, this.rects, idx, this.size - idx);
/*  427 */         this.size--;
/*  428 */         this.bounds = null;
/*  429 */         return true;
/*      */       } 
/*  431 */       if ((this.rects[idx]).x != rect.x) {
/*      */         break;
/*      */       }
/*      */     } 
/*  435 */     for (i = idx + 1; i < this.size; i++) {
/*  436 */       if (this.rects[idx].equals(rect)) {
/*  437 */         System.arraycopy(this.rects, idx + 1, this.rects, idx, this.size - idx);
/*  438 */         this.size--;
/*  439 */         this.bounds = null;
/*  440 */         return true;
/*      */       } 
/*  442 */       if ((this.rects[idx]).x != rect.x) {
/*      */         break;
/*      */       }
/*      */     } 
/*  446 */     return false;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/*  450 */     if (c instanceof RectListManager)
/*  451 */       return removeAll((RectListManager)c); 
/*  452 */     return removeAll(new RectListManager(c));
/*      */   }
/*      */   
/*      */   public boolean removeAll(RectListManager rlm) {
/*  456 */     int xChange = 0;
/*  457 */     boolean ret = false; int j, i;
/*  458 */     for (j = 0, i = 0; j < rlm.size; j++) {
/*  459 */       i = xChange;
/*  460 */       while (this.rects[i] == null || (this.rects[i]).x < (rlm.rects[j]).x) {
/*      */         
/*  462 */         i++;
/*  463 */         if (i == this.size)
/*      */           break; 
/*      */       } 
/*  466 */       if (i == this.size)
/*      */         break; 
/*  468 */       xChange = i;
/*  469 */       int x = (this.rects[i]).x;
/*      */       label35: do {
/*  471 */         while (this.rects[i] == null) {
/*  472 */           i++;
/*  473 */           if (i == this.size)
/*      */             break label35; 
/*      */         } 
/*  476 */         if (!rlm.rects[j].equals(this.rects[i]))
/*  477 */           continue;  this.rects[i] = null;
/*  478 */         ret = true;
/*      */         
/*  480 */         ++i;
/*  481 */       } while (i != this.size && 
/*  482 */         x == (this.rects[i]).x);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  487 */     if (ret) {
/*  488 */       j = 0; i = 0;
/*  489 */       while (i < this.size) {
/*  490 */         if (this.rects[i] != null)
/*  491 */           this.rects[j++] = this.rects[i]; 
/*  492 */         i++;
/*      */       } 
/*  494 */       this.size = j;
/*  495 */       this.bounds = null;
/*      */     } 
/*  497 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/*  501 */     if (c instanceof RectListManager)
/*  502 */       return retainAll((RectListManager)c); 
/*  503 */     return retainAll(new RectListManager(c));
/*      */   }
/*      */   public boolean retainAll(RectListManager rlm) {
/*  506 */     int xChange = 0;
/*  507 */     boolean ret = false;
/*      */     int j, i;
/*  509 */     for (j = 0, i = 0; j < this.size; j++) {
/*  510 */       i = xChange;
/*      */       do {
/*  512 */         i++;
/*  513 */       } while ((rlm.rects[i]).x < (this.rects[j]).x && i != rlm.size);
/*      */       
/*  515 */       if (i == rlm.size) {
/*  516 */         ret = true;
/*      */ 
/*      */         
/*  519 */         for (int k = j; k < this.size; k++)
/*  520 */           this.rects[k] = null; 
/*  521 */         this.size = j;
/*      */         
/*      */         break;
/*      */       } 
/*  525 */       xChange = i;
/*  526 */       int x = (rlm.rects[i]).x;
/*      */       
/*  528 */       while (!this.rects[j].equals(rlm.rects[i])) {
/*  529 */         i++;
/*  530 */         if (i == rlm.size || x != (rlm.rects[i]).x) {
/*      */ 
/*      */           
/*  533 */           this.rects[j] = null;
/*  534 */           ret = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  541 */     if (ret) {
/*  542 */       j = 0; i = 0;
/*  543 */       while (i < this.size) {
/*  544 */         if (this.rects[i] != null)
/*  545 */           this.rects[j++] = this.rects[i]; 
/*  546 */         i++;
/*      */       } 
/*  548 */       this.size = j;
/*  549 */       this.bounds = null;
/*      */     } 
/*  551 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(RectListManager rlm) {
/*  562 */     if (rlm.size == 0) {
/*      */       return;
/*      */     }
/*  565 */     Rectangle[] dst = this.rects;
/*  566 */     if (this.rects.length < this.size + rlm.size) {
/*  567 */       dst = new Rectangle[this.size + rlm.size];
/*      */     }
/*      */     
/*  570 */     if (this.size == 0) {
/*  571 */       System.arraycopy(rlm.rects, 0, dst, this.size, rlm.size);
/*  572 */       this.size = rlm.size;
/*  573 */       this.bounds = null;
/*      */       
/*      */       return;
/*      */     } 
/*  577 */     Rectangle[] src1 = rlm.rects;
/*  578 */     int src1Sz = rlm.size;
/*  579 */     int src1I = src1Sz - 1;
/*      */     
/*  581 */     Rectangle[] src2 = this.rects;
/*  582 */     int src2Sz = this.size;
/*  583 */     int src2I = src2Sz - 1;
/*      */     
/*  585 */     int dstI = this.size + rlm.size - 1;
/*  586 */     int x1 = (src1[src1I]).x;
/*  587 */     int x2 = (src2[src2I]).x;
/*      */     
/*  589 */     while (dstI >= 0) {
/*  590 */       if (x1 <= x2) {
/*  591 */         dst[dstI] = src2[src2I];
/*  592 */         if (src2I == 0) {
/*  593 */           System.arraycopy(src1, 0, dst, 0, src1I + 1);
/*      */           break;
/*      */         } 
/*  596 */         src2I--;
/*  597 */         x2 = (src2[src2I]).x;
/*      */       } else {
/*  599 */         dst[dstI] = src1[src1I];
/*  600 */         if (src1I == 0) {
/*  601 */           System.arraycopy(src2, 0, dst, 0, src2I + 1);
/*      */           break;
/*      */         } 
/*  604 */         src1I--;
/*  605 */         x1 = (src1[src1I]).x;
/*      */       } 
/*  607 */       dstI--;
/*      */     } 
/*  609 */     this.rects = dst;
/*  610 */     this.size += rlm.size;
/*  611 */     this.bounds = null;
/*      */   }
/*      */   
/*      */   public void mergeRects(int overhead, int lineOverhead) {
/*  615 */     if (this.size == 0) {
/*      */       return;
/*      */     }
/*  618 */     Rectangle[] splits = new Rectangle[4]; int i;
/*  619 */     for (i = 0; i < this.size; i++) {
/*  620 */       Rectangle rectangle = this.rects[i];
/*  621 */       if (rectangle != null) {
/*  622 */         int k, cost1 = overhead + rectangle.height * lineOverhead + rectangle.height * rectangle.width;
/*      */ 
/*      */         
/*      */         do {
/*  626 */           int maxX = rectangle.x + rectangle.width + overhead / rectangle.height;
/*  627 */           for (k = i + 1; k < this.size; k++) {
/*  628 */             Rectangle cr = this.rects[k];
/*  629 */             if (cr != null && cr != rectangle) {
/*  630 */               if (cr.x >= maxX) {
/*      */                 
/*  632 */                 k = this.size;
/*      */                 break;
/*      */               } 
/*  635 */               int cost2 = overhead + cr.height * lineOverhead + cr.height * cr.width;
/*      */ 
/*      */ 
/*      */               
/*  639 */               Rectangle mr = rectangle.union(cr);
/*  640 */               int cost3 = overhead + mr.height * lineOverhead + mr.height * mr.width;
/*      */ 
/*      */               
/*  643 */               if (cost3 <= cost1 + cost2) {
/*  644 */                 rectangle = this.rects[i] = mr;
/*  645 */                 this.rects[k] = null;
/*  646 */                 cost1 = cost3;
/*  647 */                 k = -1;
/*      */                 
/*      */                 break;
/*      */               } 
/*  651 */               if (rectangle.intersects(cr)) {
/*      */                 
/*  653 */                 splitRect(cr, rectangle, splits);
/*  654 */                 int splitCost = 0;
/*  655 */                 int l = 0;
/*  656 */                 for (int m = 0; m < 4; m++) {
/*  657 */                   if (splits[m] != null) {
/*  658 */                     Rectangle sr = splits[m];
/*      */ 
/*      */                     
/*  661 */                     if (m < 3) splits[l++] = sr; 
/*  662 */                     splitCost += overhead + sr.height * lineOverhead + sr.height * sr.width;
/*      */                   } 
/*      */                 } 
/*      */ 
/*      */                 
/*  667 */                 if (splitCost < cost2)
/*      */                 {
/*      */                   
/*  670 */                   if (l == 0) {
/*      */                     
/*  672 */                     this.rects[k] = null;
/*  673 */                     if (splits[3] != null) {
/*  674 */                       add(splits[3], k, this.size - 1);
/*      */                     }
/*      */                   } else {
/*      */                     
/*  678 */                     this.rects[k] = splits[0];
/*  679 */                     if (l > 1)
/*  680 */                       insertRects(splits, 1, k + 1, l - 1); 
/*  681 */                     if (splits[3] != null)
/*  682 */                       add(splits[3], k, this.size - 1); 
/*      */                   } 
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*  688 */         } while (k != this.size);
/*      */       } 
/*      */     } 
/*      */     
/*  692 */     int j = 0; i = 0;
/*  693 */     float area = 0.0F;
/*  694 */     while (i < this.size) {
/*  695 */       if (this.rects[i] != null) {
/*  696 */         Rectangle rectangle = this.rects[i];
/*  697 */         this.rects[j++] = rectangle;
/*  698 */         area += (overhead + rectangle.height * lineOverhead + rectangle.height * rectangle.width);
/*      */       } 
/*      */       
/*  701 */       i++;
/*      */     } 
/*  703 */     this.size = j;
/*  704 */     this.bounds = null;
/*  705 */     Rectangle r = getBounds();
/*  706 */     if (r == null)
/*  707 */       return;  if ((overhead + r.height * lineOverhead + r.height * r.width) < area) {
/*  708 */       this.rects[0] = r;
/*  709 */       this.size = 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void subtract(RectListManager rlm, int overhead, int lineOverhead) {
/*  716 */     int jMin = 0;
/*  717 */     Rectangle[] splits = new Rectangle[4];
/*      */     
/*  719 */     for (int i = 0; i < this.size; i++) {
/*  720 */       Rectangle r = this.rects[i];
/*  721 */       int cost = overhead + r.height * lineOverhead + r.height * r.width;
/*      */ 
/*      */       
/*  724 */       for (int m = jMin; m < rlm.size; m++) {
/*  725 */         Rectangle sr = rlm.rects[m];
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  730 */         if (sr.x + sr.width < r.x) {
/*      */ 
/*      */           
/*  733 */           if (m == jMin) jMin++;
/*      */           
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  740 */           if (sr.x > r.x + r.width) {
/*      */             break;
/*      */           }
/*      */           
/*  744 */           if (r.intersects(sr)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  750 */             splitRect(r, sr, splits);
/*      */             
/*  752 */             int splitCost = 0;
/*      */             
/*  754 */             for (int n = 0; n < 4; n++) {
/*  755 */               Rectangle tmpR = splits[n];
/*  756 */               if (tmpR != null) {
/*  757 */                 splitCost += overhead + tmpR.height * lineOverhead + tmpR.height * tmpR.width;
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/*  762 */             if (splitCost < cost) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  773 */               int l = 0;
/*  774 */               for (int i1 = 0; i1 < 3; i1++) {
/*  775 */                 if (splits[i1] != null) {
/*  776 */                   splits[l++] = splits[i1];
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/*  781 */               if (l == 0) {
/*  782 */                 (this.rects[i]).width = 0;
/*      */ 
/*      */                 
/*  785 */                 if (splits[3] != null) {
/*  786 */                   add(splits[3], i, this.size - 1);
/*      */                 }
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */ 
/*      */               
/*  794 */               r = splits[0];
/*  795 */               this.rects[i] = r;
/*  796 */               cost = overhead + r.height * lineOverhead + r.height * r.width;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  803 */               if (l > 1) {
/*  804 */                 insertRects(splits, 1, i + 1, l - 1);
/*      */               }
/*      */ 
/*      */               
/*  808 */               if (splits[3] != null)
/*  809 */                 add(splits[3], i + l, this.size - 1); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  814 */     }  int j = 0, k = 0;
/*  815 */     while (k < this.size) {
/*  816 */       if ((this.rects[k]).width == 0) {
/*  817 */         this.rects[k] = null;
/*      */       } else {
/*  819 */         this.rects[j++] = this.rects[k];
/*  820 */       }  k++;
/*      */     } 
/*  822 */     this.size = j;
/*  823 */     this.bounds = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void splitRect(Rectangle r, Rectangle sr, Rectangle[] splits) {
/*  850 */     int rx0 = r.x;
/*  851 */     int rx1 = rx0 + r.width - 1;
/*  852 */     int ry0 = r.y;
/*  853 */     int ry1 = ry0 + r.height - 1;
/*      */     
/*  855 */     int srx0 = sr.x;
/*  856 */     int srx1 = srx0 + sr.width - 1;
/*  857 */     int sry0 = sr.y;
/*  858 */     int sry1 = sry0 + sr.height - 1;
/*      */     
/*  860 */     if (ry0 < sry0 && ry1 >= sry0) {
/*  861 */       splits[0] = new Rectangle(rx0, ry0, r.width, sry0 - ry0);
/*  862 */       ry0 = sry0;
/*      */     } else {
/*  864 */       splits[0] = null;
/*      */     } 
/*      */     
/*  867 */     if (ry0 <= sry1 && ry1 > sry1) {
/*  868 */       splits[1] = new Rectangle(rx0, sry1 + 1, r.width, ry1 - sry1);
/*  869 */       ry1 = sry1;
/*      */     } else {
/*  871 */       splits[1] = null;
/*      */     } 
/*      */     
/*  874 */     if (rx0 < srx0 && rx1 >= srx0) {
/*  875 */       splits[2] = new Rectangle(rx0, ry0, srx0 - rx0, ry1 - ry0 + 1);
/*      */     } else {
/*  877 */       splits[2] = null;
/*      */     } 
/*      */     
/*  880 */     if (rx0 <= srx1 && rx1 > srx1) {
/*  881 */       splits[3] = new Rectangle(srx1 + 1, ry0, rx1 - srx1, ry1 - ry0 + 1);
/*      */     } else {
/*  883 */       splits[3] = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertRects(Rectangle[] rects, int srcPos, int dstPos, int len) {
/*  889 */     if (len == 0) {
/*      */       return;
/*      */     }
/*  892 */     ensureCapacity(this.size + len);
/*      */ 
/*      */     
/*  895 */     for (int i = this.size - 1; i >= dstPos; i--) {
/*  896 */       this.rects[i + len] = this.rects[i];
/*      */     }
/*      */     
/*  899 */     System.arraycopy(rects, srcPos, this.rects, dstPos, len);
/*      */     
/*  901 */     this.size += len;
/*      */   }
/*      */   
/*      */   public void ensureCapacity(int sz) {
/*  905 */     if (sz <= this.rects.length)
/*      */       return; 
/*  907 */     int nSz = this.rects.length + (this.rects.length >> 1) + 1;
/*  908 */     while (nSz < sz) {
/*  909 */       nSz += (nSz >> 1) + 1;
/*      */     }
/*  911 */     Rectangle[] nRects = new Rectangle[nSz];
/*  912 */     System.arraycopy(this.rects, 0, nRects, 0, this.size);
/*      */     
/*  914 */     this.rects = nRects;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class RectXComparator
/*      */     implements Serializable, Comparator
/*      */   {
/*      */     public final int compare(Object o1, Object o2) {
/*  928 */       return ((Rectangle)o1).x - ((Rectangle)o2).x;
/*      */     }
/*      */   }
/*      */   
/*      */   private class RLMIterator
/*      */     implements ListIterator {
/*  934 */     int idx = 0;
/*      */     boolean removeOk = false;
/*      */     boolean forward = true;
/*      */     
/*      */     public boolean hasNext() {
/*  939 */       return (this.idx < RectListManager.this.size); } public int nextIndex() {
/*  940 */       return this.idx;
/*      */     } public Object next() {
/*  942 */       if (this.idx >= RectListManager.this.size)
/*  943 */         throw new NoSuchElementException("No Next Element"); 
/*  944 */       this.forward = true;
/*  945 */       this.removeOk = true;
/*  946 */       return RectListManager.this.rects[this.idx++];
/*      */     }
/*      */     
/*  949 */     public boolean hasPrevious() { return (this.idx > 0); } public int previousIndex() {
/*  950 */       return this.idx - 1;
/*      */     } public Object previous() {
/*  952 */       if (this.idx <= 0)
/*  953 */         throw new NoSuchElementException("No Previous Element"); 
/*  954 */       this.forward = false;
/*  955 */       this.removeOk = true;
/*  956 */       return RectListManager.this.rects[--this.idx];
/*      */     }
/*      */     
/*      */     public void remove() {
/*  960 */       if (!this.removeOk) {
/*  961 */         throw new IllegalStateException("remove can only be called directly after next/previous");
/*      */       }
/*      */       
/*  964 */       if (this.forward) this.idx--; 
/*  965 */       if (this.idx != RectListManager.this.size - 1)
/*  966 */         System.arraycopy(RectListManager.this.rects, this.idx + 1, RectListManager.this.rects, this.idx, RectListManager.this.size - this.idx + 1); 
/*  967 */       RectListManager.this.size--;
/*  968 */       RectListManager.this.rects[RectListManager.this.size] = null;
/*  969 */       this.removeOk = false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void set(Object o) {
/*  974 */       Rectangle r = (Rectangle)o;
/*      */       
/*  976 */       if (!this.removeOk) {
/*  977 */         throw new IllegalStateException("set can only be called directly after next/previous");
/*      */       }
/*      */       
/*  980 */       if (this.forward) this.idx--;
/*      */       
/*  982 */       if (this.idx + 1 < RectListManager.this.size && 
/*  983 */         (RectListManager.this.rects[this.idx + 1]).x < r.x) {
/*  984 */         throw new UnsupportedOperationException("RectListManager entries must be sorted");
/*      */       }
/*      */       
/*  987 */       if (this.idx >= 0 && 
/*  988 */         (RectListManager.this.rects[this.idx - 1]).x > r.x) {
/*  989 */         throw new UnsupportedOperationException("RectListManager entries must be sorted");
/*      */       }
/*      */ 
/*      */       
/*  993 */       RectListManager.this.rects[this.idx] = r;
/*  994 */       this.removeOk = false;
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/*  998 */       Rectangle r = (Rectangle)o;
/*  999 */       if (this.idx < RectListManager.this.size && 
/* 1000 */         (RectListManager.this.rects[this.idx]).x < r.x) {
/* 1001 */         throw new UnsupportedOperationException("RectListManager entries must be sorted");
/*      */       }
/*      */       
/* 1004 */       if (this.idx != 0 && 
/* 1005 */         (RectListManager.this.rects[this.idx - 1]).x > r.x) {
/* 1006 */         throw new UnsupportedOperationException("RectListManager entries must be sorted");
/*      */       }
/*      */       
/* 1009 */       RectListManager.this.ensureCapacity(RectListManager.this.size + 1);
/* 1010 */       if (this.idx != RectListManager.this.size)
/* 1011 */         System.arraycopy(RectListManager.this.rects, this.idx, RectListManager.this.rects, this.idx + 1, RectListManager.this.size - this.idx); 
/* 1012 */       RectListManager.this.rects[this.idx] = r;
/* 1013 */       this.idx++;
/* 1014 */       this.removeOk = false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/RectListManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */