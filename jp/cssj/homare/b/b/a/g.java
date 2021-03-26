/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import jp.cssj.homare.b.a.a.d;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.b.s;
/*      */ import jp.cssj.homare.b.a.b.t;
/*      */ import jp.cssj.homare.b.a.b.u;
/*      */ import jp.cssj.homare.b.a.b.v;
/*      */ import jp.cssj.homare.b.a.b.w;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.D;
/*      */ import jp.cssj.homare.b.a.c.E;
/*      */ import jp.cssj.homare.b.a.c.F;
/*      */ import jp.cssj.homare.b.a.c.G;
/*      */ import jp.cssj.homare.b.a.c.I;
/*      */ import jp.cssj.homare.b.a.c.J;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.j;
/*      */ import jp.cssj.homare.b.a.c.s;
/*      */ import jp.cssj.homare.b.a.d;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.b.d;
/*      */ import jp.cssj.homare.b.b.f;
/*      */ import jp.cssj.homare.b.e.c;
/*      */ import jp.cssj.homare.b.f.c;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ import jp.cssj.sakae.e.d;
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
/*      */ public class g
/*      */   implements f
/*      */ {
/*      */   protected static class a
/*      */   {
/*      */     private final Object d;
/*      */     public final int a;
/*      */     public final int b;
/*      */     
/*      */     public a(l cellBuilder, int colspan) {
/*   65 */       this.d = cellBuilder;
/*   66 */       s cellBox = (s)cellBuilder.j();
/*   67 */       this.a = (cellBox.u()).e;
/*   68 */       this.b = colspan;
/*      */     }
/*      */     
/*      */     public a(s cell, int rowspan, int colspan) {
/*   72 */       if (!c && rowspan < 1) throw new AssertionError(); 
/*   73 */       if (!c && colspan < 1) throw new AssertionError(); 
/*   74 */       this.d = cell;
/*   75 */       this.a = rowspan;
/*   76 */       this.b = colspan;
/*      */     }
/*      */     
/*      */     public boolean a() {
/*   80 */       return this.d instanceof s;
/*      */     }
/*      */     
/*      */     public l b() {
/*   84 */       return (l)this.d;
/*      */     }
/*      */     
/*      */     public s c() {
/*   88 */       if (a()) {
/*   89 */         return (s)this.d;
/*      */       }
/*   91 */       return (s)b().j();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   private final List<d> e = new ArrayList<>();
/*   99 */   private final List<jp.cssj.homare.b.b.a> f = new ArrayList<>();
/*  100 */   private final List<jp.cssj.homare.b.b.a> g = new ArrayList<>();
/*  101 */   private u h = null;
/*      */ 
/*      */ 
/*      */   
/*  105 */   private List<j[]> k = null, l = null;
/*  106 */   private List<j[]> m = null, n = null;
/*  107 */   private List<j[]> o = null; private List<j[]> p = null;
/*  108 */   private c q = null;
/*  109 */   private c r = null;
/*  110 */   private c s = null;
/*      */ 
/*      */   
/*  113 */   private double[] t = null;
/*      */   
/*  115 */   private w u = null;
/*      */   
/*  117 */   private final Map<s, v.b> v = new HashMap<>();
/*      */ 
/*      */   
/*  120 */   private final List<List<a>> w = new ArrayList<>();
/*  121 */   private final List<v> x = new ArrayList<>();
/*      */ 
/*      */   
/*      */   private boolean y = false;
/*      */   
/*      */   private boolean z = true;
/*      */   
/*      */   private boolean A = true;
/*      */   
/*  130 */   private w B = null;
/*      */ 
/*      */   
/*  133 */   private v C = null;
/*      */   
/*  135 */   private List<a> D = null; private final boolean b; private r c; private h d; private double i; private double j;
/*      */   
/*      */   public g(r tableBox) {
/*  138 */     this.c = tableBox;
/*  139 */     this.b = e.a((tableBox.g()).D);
/*      */   }
/*      */   
/*      */   public r a() {
/*  143 */     return this.c;
/*      */   } public void a(d box) {
/*      */     t column;
/*      */     u parentColumnGroup;
/*  147 */     box.a(this.c.g());
/*  148 */     switch (box.a()) {
/*      */       
/*      */       case 8:
/*      */       case 9:
/*  152 */         column = (t)box;
/*  153 */         if (this.e.isEmpty()) {
/*  154 */           if (this.h == null) {
/*  155 */             this.h = new u(new s(), new F());
/*  156 */             this.h.a(this.c.g());
/*      */           } 
/*  158 */           this.h.a(column);
/*      */           break;
/*      */         } 
/*  161 */         parentColumnGroup = (u)this.e.get(this.e.size() - 1);
/*  162 */         parentColumnGroup.a(column);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/*  168 */         this.u = (w)box;
/*  169 */         if (this.B == null) {
/*  170 */           this.B = this.u;
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/*  177 */         this.C = (v)box;
/*  178 */         this.D = new ArrayList<>();
/*  179 */         g();
/*      */         break;
/*      */       
/*      */       default:
/*  183 */         throw new IllegalStateException();
/*      */     } 
/*  185 */     this.e.add(box);
/*      */   }
/*      */   
/*      */   private double a(v rowBox) {
/*      */     double rowSize;
/*  190 */     s rowParams = rowBox.c();
/*  191 */     switch (rowParams.c.a()) {
/*      */       case 1:
/*  193 */         rowSize = rowParams.c.b();
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/*  197 */         rowSize = 0.0D;
/*      */         break;
/*      */       default:
/*  200 */         throw new IllegalStateException();
/*      */     } 
/*  202 */     switch (rowParams.d.a()) {
/*      */       case 1:
/*  204 */         rowSize = Math.max(rowParams.d.b(), rowSize);
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/*      */         break;
/*      */       default:
/*  210 */         throw new IllegalStateException();
/*      */     } 
/*  212 */     switch (rowParams.e.a()) {
/*      */       case 1:
/*  214 */         rowSize = Math.min(rowParams.e.b(), rowSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/*  222 */         return rowSize;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   } private void e() {
/*      */     double tableFrame, lineBorderSpacing, tableInnerSize;
/*  227 */     G tableParams = this.c.g();
/*  228 */     f flowBox = (f)this.c.h();
/*      */ 
/*      */     
/*  231 */     c containerBox = (this.d.a(this.d.g() - 2)).a;
/*      */     
/*  233 */     this.c.a(containerBox.h());
/*      */     
/*  235 */     i flowParams = flowBox.c_();
/*  236 */     double lineSize = containerBox.h();
/*      */ 
/*      */     
/*  239 */     if (this.b) {
/*  240 */       tableFrame = this.c.i().e();
/*  241 */       lineBorderSpacing = tableParams.aw;
/*  242 */       tableInnerSize = e.b(flowParams.X, lineSize);
/*  243 */       if (!a && e.a(tableInnerSize)) throw new AssertionError(); 
/*  244 */       double minWidth = e.b(flowParams.Y, lineSize);
/*  245 */       tableInnerSize = Math.max(minWidth, tableInnerSize);
/*  246 */       double maxWidth = e.b(flowParams.Z, lineSize);
/*  247 */       if (!e.a(maxWidth) && !e.a(tableInnerSize)) {
/*  248 */         tableInnerSize = Math.min(maxWidth, tableInnerSize);
/*      */       }
/*      */     } else {
/*  251 */       tableFrame = this.c.i().f();
/*  252 */       lineBorderSpacing = tableParams.av;
/*  253 */       tableInnerSize = e.a(flowParams.X, lineSize);
/*  254 */       double minWidth = e.a(flowParams.Y, lineSize);
/*  255 */       tableInnerSize = Math.max(minWidth, tableInnerSize);
/*  256 */       double maxWidth = e.a(flowParams.Z, lineSize);
/*  257 */       if (!e.a(maxWidth) && !e.a(tableInnerSize)) {
/*  258 */         tableInnerSize = Math.min(maxWidth, tableInnerSize);
/*      */       }
/*      */     } 
/*  261 */     if (e.a(tableInnerSize)) {
/*  262 */       tableInnerSize = flowBox.h();
/*      */     }
/*  264 */     tableInnerSize -= tableFrame;
/*      */     
/*  266 */     int columnCount = 0;
/*  267 */     if (this.h != null) {
/*  268 */       List<Object> stack = new ArrayList();
/*  269 */       u colgroup = this.h;
/*  270 */       this.c.a(colgroup);
/*  271 */       int k = 0;
/*      */       while (true) {
/*  273 */         for (; k < colgroup.h(); k++) {
/*  274 */           t column = colgroup.a(k);
/*  275 */           F colParams = column.g();
/*  276 */           if (column.a() == 8 && ((u)column)
/*  277 */             .h() > 0) {
/*  278 */             stack.add(colgroup);
/*  279 */             stack.add(d.a(k + 1));
/*  280 */             colgroup = (u)column;
/*  281 */             k = 0;
/*      */             continue;
/*      */           } 
/*  284 */           columnCount += colParams.a;
/*      */         } 
/*      */         
/*  287 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/*  290 */         k = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  291 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } 
/*  294 */     columnCount = Math.max((this.D == null) ? 0 : this.D.size(), columnCount);
/*  295 */     double refSize = tableInnerSize;
/*  296 */     if (tableParams.ax == 0)
/*      */     {
/*  298 */       refSize -= columnCount * lineBorderSpacing;
/*      */     }
/*  300 */     refSize = Math.max(0.0D, refSize);
/*  301 */     f[] columnSizeList = new f[columnCount];
/*  302 */     if (this.h != null) {
/*  303 */       List<Object> stack = new ArrayList();
/*  304 */       u colgroup = this.h;
/*  305 */       int m = 0, k = 0;
/*      */       while (true) {
/*  307 */         for (; m < colgroup.h(); m++) {
/*  308 */           int n; double fix; f size; int i1; t column = colgroup.a(m);
/*  309 */           s colParams = column.c();
/*  310 */           F colPos = column.g();
/*  311 */           if (column.a() == 8 && ((u)column)
/*  312 */             .h() > 0) {
/*  313 */             stack.add(colgroup);
/*  314 */             stack.add(d.a(m + 1));
/*  315 */             colgroup = (u)column;
/*  316 */             m = 0;
/*      */             continue;
/*      */           } 
/*  319 */           switch (colParams.c.a()) {
/*      */             case 3:
/*  321 */               for (n = 0; n < colPos.a; n++) {
/*  322 */                 columnSizeList[k++] = null;
/*      */               }
/*      */               break;
/*      */             case 1:
/*  326 */               fix = colParams.c.b();
/*  327 */               if (tableParams.ax == 0)
/*      */               {
/*  329 */                 fix += lineBorderSpacing;
/*      */               }
/*  331 */               size = new f(fix, false);
/*  332 */               for (i1 = 0; i1 < colPos.a; i1++) {
/*  333 */                 columnSizeList[k++] = size;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 2:
/*  338 */               fix = refSize * colParams.c.b();
/*  339 */               if (tableParams.ax == 0)
/*      */               {
/*  341 */                 fix += lineBorderSpacing;
/*      */               }
/*  343 */               size = new f(fix, true);
/*  344 */               for (i1 = 0; i1 < colPos.a; i1++) {
/*  345 */                 columnSizeList[k++] = size;
/*      */               }
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/*  351 */               throw new IllegalStateException();
/*      */           } 
/*      */         
/*      */         } 
/*  355 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/*  358 */         m = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  359 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } 
/*      */     
/*  363 */     this.t = new double[columnCount];
/*  364 */     int autoCount = 0;
/*  365 */     double sizeSum = 0.0D, percentSizeSum = 0.0D; int i;
/*  366 */     for (i = 0; i < columnCount; i++) {
/*      */       
/*  368 */       if (this.D == null || i >= this.D.size()) {
/*      */         f size;
/*  370 */         if (columnSizeList[i] == null) {
/*  371 */           size = null;
/*  372 */           autoCount++;
/*      */         } else {
/*  374 */           size = columnSizeList[i];
/*  375 */           sizeSum += size.b;
/*  376 */           if (size.a) {
/*  377 */             percentSizeSum += size.b;
/*      */           }
/*      */         } 
/*  380 */         this.t[i] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */       } else {
/*      */         f size;
/*  383 */         a cell = this.D.get(i);
/*  384 */         s cellBox = cell.c();
/*  385 */         i cellParams = cellBox.c_();
/*  386 */         if (this.b) {
/*  387 */           double fix; if (cellParams.X.b() != 3) {
/*      */             double space;
/*      */             
/*  390 */             if (tableParams.ax == 0) {
/*  391 */               space = lineBorderSpacing / 2.0D;
/*      */             } else {
/*  393 */               space = 0.0D;
/*      */             } 
/*  395 */             jp.cssj.homare.b.e.a cellSpacing = new jp.cssj.homare.b.e.a(space, 0.0D, space, 0.0D);
/*  396 */             cellBox.a(containerBox.h(), this.c, cellSpacing);
/*      */           } 
/*  398 */           switch (cellParams.X.b()) {
/*      */             case 3:
/*  400 */               size = null;
/*      */               break;
/*      */             case 1:
/*  403 */               fix = cellParams.X.d();
/*  404 */               if (cellParams.aa == 1) {
/*  405 */                 fix += cellBox.m().e();
/*      */               }
/*  407 */               fix /= cell.b;
/*  408 */               size = new f(fix, false);
/*      */               break;
/*      */             
/*      */             case 2:
/*  412 */               fix = refSize * cellParams.X.d();
/*  413 */               if (cellParams.aa == 1) {
/*  414 */                 fix += cellBox.m().e();
/*      */               }
/*  416 */               fix /= cell.b;
/*  417 */               size = new f(fix, true);
/*      */               break;
/*      */             
/*      */             default:
/*  421 */               throw new IllegalStateException();
/*      */           } 
/*      */         } else {
/*  424 */           double fix; if (cellParams.X.a() != 3) {
/*      */             double space;
/*      */             
/*  427 */             if (tableParams.ax == 0) {
/*  428 */               space = lineBorderSpacing / 2.0D;
/*      */             } else {
/*  430 */               space = 0.0D;
/*      */             } 
/*  432 */             jp.cssj.homare.b.e.a cellSpacing = new jp.cssj.homare.b.e.a(0.0D, space, 0.0D, space);
/*  433 */             cellBox.a(containerBox.h(), this.c, cellSpacing);
/*      */           } 
/*  435 */           switch (cellParams.X.a()) {
/*      */             case 3:
/*  437 */               size = null;
/*      */               break;
/*      */             case 1:
/*  440 */               fix = cellParams.X.c();
/*  441 */               if (cellParams.aa == 1) {
/*  442 */                 fix += cellBox.m().f();
/*      */               }
/*  444 */               fix /= cell.b;
/*  445 */               size = new f(fix, false);
/*      */               break;
/*      */             
/*      */             case 2:
/*  449 */               fix = refSize * cellParams.X.c();
/*  450 */               if (cellParams.aa == 1) {
/*  451 */                 fix += cellBox.m().f();
/*      */               }
/*  453 */               fix /= cell.b;
/*  454 */               size = new f(fix, true);
/*      */               break;
/*      */             
/*      */             default:
/*  458 */               throw new IllegalStateException();
/*      */           } 
/*      */         
/*      */         } 
/*  462 */         if (columnSizeList[i] == null) {
/*  463 */           if (size == null) {
/*  464 */             autoCount++;
/*      */           } else {
/*      */             
/*  467 */             columnSizeList[i] = size;
/*  468 */             sizeSum += size.b;
/*  469 */             if (size.a) {
/*  470 */               percentSizeSum += size.b;
/*      */             }
/*      */           } 
/*  473 */           this.t[i] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */         } else {
/*  475 */           f columnWidth = columnSizeList[i];
/*  476 */           this.t[i] = columnWidth.b;
/*  477 */           sizeSum += this.t[i];
/*  478 */           if (columnWidth.a) {
/*  479 */             percentSizeSum += columnWidth.b;
/*      */           }
/*      */         } 
/*  482 */         for (int k = 1; k < cell.b; k++) {
/*  483 */           i++;
/*  484 */           if (columnSizeList[i] == null) {
/*  485 */             if (size == null) {
/*  486 */               autoCount++;
/*      */             } else {
/*  488 */               columnSizeList[i] = size;
/*  489 */               sizeSum += size.b;
/*  490 */               if (size.a) {
/*  491 */                 percentSizeSum += size.b;
/*      */               }
/*      */             } 
/*  494 */             this.t[i] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */           } else {
/*  496 */             f columnWidth = columnSizeList[i];
/*  497 */             this.t[i] = columnWidth.b;
/*  498 */             sizeSum += this.t[i];
/*  499 */             if (columnWidth.a) {
/*  500 */               percentSizeSum += columnWidth.b;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  509 */     if (percentSizeSum > 0.0D && sizeSum > tableInnerSize) {
/*      */       
/*  511 */       double removeSize = Math.min(percentSizeSum, sizeSum - tableInnerSize);
/*  512 */       for (int k = 0; k < columnCount; k++) {
/*  513 */         f size = columnSizeList[k];
/*  514 */         if (size != null && size.a) {
/*  515 */           double sizeDiff = removeSize * size.b / percentSizeSum;
/*  516 */           this.t[k] = this.t[k] - sizeDiff;
/*  517 */           sizeSum -= sizeDiff;
/*      */         } 
/*      */       } 
/*      */     } 
/*  521 */     if (autoCount > 0) {
/*      */       double size;
/*  523 */       if (tableInnerSize > sizeSum) {
/*  524 */         size = (tableInnerSize - sizeSum) / autoCount;
/*      */       } else {
/*  526 */         tableInnerSize = sizeSum;
/*  527 */         size = 0.0D;
/*      */       } 
/*  529 */       for (int k = 0; k < columnCount; k++) {
/*  530 */         if (e.a(this.t[k])) {
/*  531 */           this.t[k] = size;
/*      */         }
/*      */       } 
/*  534 */     } else if (tableInnerSize > sizeSum) {
/*  535 */       double size = (tableInnerSize - sizeSum) / columnCount;
/*  536 */       for (int k = 0; k < columnCount; k++) {
/*  537 */         this.t[k] = this.t[k] + size;
/*      */       }
/*      */     } else {
/*  540 */       tableInnerSize = 0.0D;
/*  541 */       for (i = 0; i < columnCount; i++) {
/*  542 */         tableInnerSize += this.t[i];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  547 */     double tableSize = tableInnerSize + tableFrame;
/*  548 */     flowBox.a((d)this.d, tableSize, tableSize, true);
/*      */ 
/*      */     
/*  551 */     for (int j = 0; j < this.f.size(); j++) {
/*  552 */       l captionBuilder = (l)this.f.get(j);
/*  553 */       f captionBox = (f)captionBuilder.j();
/*  554 */       this.d.a(captionBox);
/*  555 */       captionBuilder.a(this.d);
/*  556 */       this.d.e();
/*      */     } 
/*  558 */     this.j = tableInnerSize;
/*      */ 
/*      */     
/*  561 */     if (this.h != null) {
/*  562 */       int col = 0;
/*  563 */       List<Object> stack = new ArrayList();
/*  564 */       u colgroup = this.h;
/*  565 */       int k = 0;
/*      */       while (true) {
/*  567 */         for (; k < colgroup.h(); k++) {
/*  568 */           int span; t column = colgroup.a(k);
/*  569 */           F colPos = column.g();
/*      */ 
/*      */           
/*  572 */           if (column.a() == 8 && ((u)column)
/*  573 */             .h() > 0) {
/*  574 */             span = ((u)column).h();
/*      */           } else {
/*  576 */             span = colPos.a;
/*      */           } 
/*  578 */           double size = 0.0D;
/*  579 */           for (int m = 0; m < span; m++) {
/*  580 */             size += this.t[col + m];
/*      */           }
/*  582 */           column.a(size);
/*  583 */           if (column.a() == 8 && ((u)column)
/*  584 */             .h() > 0) {
/*  585 */             stack.add(colgroup);
/*  586 */             stack.add(d.a(k + 1));
/*  587 */             colgroup = (u)column;
/*  588 */             k = 0;
/*      */             continue;
/*      */           } 
/*  591 */           col += span;
/*      */         } 
/*      */         
/*  594 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/*  597 */         k = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  598 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public void b() {
/*      */     boolean firstRow;
/*      */     s rowGroupParams;
/*  605 */     d box = this.e.remove(this.e.size() - 1);
/*  606 */     switch (box.a()) {
/*      */       case 8:
/*      */       case 9:
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/*  614 */         this.u = null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/*  620 */         firstRow = (this.t == null);
/*  621 */         if (firstRow) {
/*  622 */           e();
/*      */         }
/*  624 */         rowGroupParams = this.B.c();
/*  625 */         if (rowGroupParams.c.a() == 1) {
/*  626 */           if (this.u != this.B) {
/*  627 */             a(false);
/*      */           }
/*  629 */           this.y = false;
/*      */         } else {
/*  631 */           if (this.y) {
/*  632 */             a(false);
/*      */           }
/*  634 */           this.y = true;
/*  635 */           for (int i = 0; i < this.D.size(); i++) {
/*  636 */             a cell = this.D.get(i);
/*  637 */             if (cell.a > 1) {
/*  638 */               this.y = false;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*  643 */         this.w.add(this.D);
/*  644 */         this.x.add(this.C);
/*      */     } 
/*      */ 
/*      */     
/*  648 */     throw new IllegalStateException();
/*      */   }
/*      */   
/*      */   private void f() {
/*      */     double pageSize;
/*  653 */     if (this.h == null) {
/*      */       return;
/*      */     }
/*      */     
/*  657 */     if (this.b) {
/*  658 */       pageSize = this.c.s();
/*      */     } else {
/*  660 */       pageSize = this.c.t();
/*      */     } 
/*  662 */     List<Object> stack = new ArrayList();
/*  663 */     u colgroup = this.h;
/*  664 */     int i = 0;
/*      */     while (true) {
/*  666 */       for (; i < colgroup.h(); i++) {
/*  667 */         t column = colgroup.a(i);
/*  668 */         column.b(pageSize);
/*  669 */         if (column.a() == 8 && ((u)column)
/*  670 */           .h() > 0) {
/*  671 */           stack.add(colgroup);
/*  672 */           stack.add(d.a(i + 1));
/*  673 */           colgroup = (u)column;
/*  674 */           i = 0;
/*      */           continue;
/*      */         } 
/*      */       } 
/*  678 */       if (stack.isEmpty()) {
/*      */         break;
/*      */       }
/*  681 */       i = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  682 */       colgroup = (u)stack.remove(stack.size() - 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(boolean lastRow) {
/*  693 */     G tableParams = this.c.g();
/*  694 */     s rowGroupParams = this.B.c();
/*  695 */     I rowGroupPos = this.B.g();
/*  696 */     boolean firstRow = this.z;
/*  697 */     this.z = false;
/*  698 */     boolean groupFirst = this.A;
/*  699 */     this.A = false;
/*  700 */     boolean groupLast = (this.B != this.u);
/*      */     
/*  702 */     lastRow = ((groupLast && rowGroupPos.c == 3) || (lastRow && this.c.l() == null));
/*      */     
/*  704 */     if (tableParams.ax == 1) {
/*      */       List<j[]> vborders;
/*      */       List<j[]> hborders;
/*  707 */       switch (rowGroupPos.c) {
/*      */         case 1:
/*  709 */           if (this.l == null) {
/*  710 */             this.l = (List)new ArrayList<>();
/*      */           }
/*  712 */           if (this.k == null) {
/*  713 */             this.k = (List)new ArrayList<>();
/*      */           }
/*  715 */           vborders = this.l;
/*  716 */           hborders = this.k;
/*      */           break;
/*      */         
/*      */         case 2:
/*  720 */           if (this.n == null) {
/*  721 */             this.n = (List)new ArrayList<>();
/*      */           }
/*  723 */           if (this.m == null) {
/*  724 */             this.m = (List)new ArrayList<>();
/*      */           }
/*  726 */           vborders = this.n;
/*  727 */           hborders = this.m;
/*      */           break;
/*      */         
/*      */         case 3:
/*  731 */           if (this.p == null) {
/*  732 */             this.p = (List)new ArrayList<>();
/*      */           }
/*  734 */           if (this.o == null) {
/*  735 */             this.o = (List)new ArrayList<>();
/*      */           }
/*  737 */           vborders = this.p;
/*  738 */           hborders = this.o;
/*      */           break;
/*      */         
/*      */         default:
/*  742 */           throw new IllegalStateException();
/*      */       } 
/*  744 */       if (this.b) {
/*  745 */         for (int i = 0; i < this.w.size(); i++) {
/*  746 */           j[] firstBorder; List<a> cells = this.w.get(i);
/*  747 */           j[] lineBorder = new j[this.t.length + 1];
/*  748 */           vborders.add(lineBorder);
/*      */           
/*  750 */           if (hborders.isEmpty()) {
/*  751 */             firstBorder = new j[this.t.length];
/*  752 */             hborders.add(firstBorder);
/*      */           } else {
/*  754 */             firstBorder = hborders.get(hborders.size() - 1);
/*      */           } 
/*  756 */           j[] lastBorder = new j[this.t.length];
/*  757 */           hborders.add(lastBorder);
/*      */ 
/*      */ 
/*      */           
/*  761 */           lineBorder[0] = c.a(lineBorder[0], tableParams.S.c
/*  762 */               .a());
/*      */           
/*  764 */           lineBorder[lineBorder.length - 1] = 
/*  765 */             c.a(lineBorder[lineBorder.length - 1], tableParams.S.c.a());
/*      */           
/*  767 */           if (firstRow && i == 0) {
/*  768 */             for (int m = 0; m < firstBorder.length; m++) {
/*  769 */               firstBorder[m] = c.a(firstBorder[m], tableParams.S.c
/*  770 */                   .b());
/*      */             }
/*      */           }
/*      */           
/*  774 */           if (lastRow && i == this.w.size() - 1) {
/*  775 */             for (int m = 0; m < lastBorder.length; m++) {
/*  776 */               lastBorder[m] = c.a(lastBorder[m], tableParams.S.c
/*  777 */                   .d());
/*      */             }
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  783 */           if (this.h != null) {
/*  784 */             u columnGroup = this.h;
/*  785 */             int col = 0;
/*  786 */             List<Object> stack = new ArrayList();
/*  787 */             int m = 0;
/*      */             while (true) {
/*  789 */               for (; m < columnGroup.h(); m++) {
/*  790 */                 int colspan; t column = columnGroup.a(m);
/*  791 */                 s colParams = column.c();
/*  792 */                 F colPos = column.g();
/*      */                 
/*  794 */                 if (column.a() == 8 && ((u)column)
/*  795 */                   .h() > 0) {
/*  796 */                   colspan = ((u)column).h();
/*      */                 } else {
/*  798 */                   colspan = colPos.a;
/*      */                 } 
/*  800 */                 if (firstRow && i == 0) {
/*  801 */                   for (int n = 0; n < colspan; n++) {
/*  802 */                     int jj = col + n;
/*      */                     
/*  804 */                     firstBorder[jj] = c.a(firstBorder[jj], colParams.b
/*  805 */                         .b());
/*      */                   } 
/*      */                 }
/*  808 */                 if (lastRow && i == this.w.size() - 1) {
/*  809 */                   for (int n = 0; n < colspan; n++) {
/*  810 */                     int jj = col + n;
/*      */                     
/*  812 */                     lastBorder[jj] = c.a(lastBorder[jj], colParams.b
/*  813 */                         .d());
/*      */                   } 
/*      */                 }
/*      */                 
/*  817 */                 lineBorder[col] = c.a(lineBorder[col], colParams.b
/*  818 */                     .a());
/*      */                 
/*  820 */                 lineBorder[col + colspan] = 
/*  821 */                   c.a(lineBorder[col + colspan], colParams.b.c());
/*  822 */                 if (column.a() == 8 && ((u)column)
/*  823 */                   .h() > 0) {
/*  824 */                   stack.add(columnGroup);
/*  825 */                   stack.add(d.a(m + 1));
/*  826 */                   columnGroup = (u)column;
/*  827 */                   m = 0;
/*      */                   continue;
/*      */                 } 
/*  830 */                 col += colspan;
/*      */               } 
/*      */               
/*  833 */               if (stack.isEmpty()) {
/*      */                 break;
/*      */               }
/*  836 */               m = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  837 */               columnGroup = (u)stack.remove(stack.size() - 1);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  843 */           lineBorder[0] = c.a(lineBorder[0], rowGroupParams.b.a());
/*      */           
/*  845 */           lineBorder[lineBorder.length - 1] = 
/*  846 */             c.a(lineBorder[lineBorder.length - 1], rowGroupParams.b.a());
/*      */           
/*  848 */           if (groupFirst) {
/*  849 */             for (int m = 0; m < this.t.length; m++) {
/*  850 */               firstBorder[m] = c.a(firstBorder[m], rowGroupParams.b
/*  851 */                   .b());
/*      */             }
/*      */           }
/*      */           
/*  855 */           if (groupLast) {
/*  856 */             for (int m = 0; m < this.t.length; m++) {
/*  857 */               lastBorder[m] = c.a(lastBorder[m], rowGroupParams.b
/*  858 */                   .d());
/*      */             }
/*      */           }
/*      */ 
/*      */           
/*  863 */           v rowBox = this.x.get(i);
/*  864 */           s rowParams = rowBox.c();
/*      */           
/*  866 */           lineBorder[0] = c.a(lineBorder[0], rowParams.b.a());
/*      */           
/*  868 */           lineBorder[lineBorder.length - 1] = 
/*  869 */             c.a(lineBorder[lineBorder.length - 1], rowParams.b.a());
/*      */           
/*  871 */           for (int j = 0; j < cells.size(); j++) {
/*  872 */             a cell = cells.get(j);
/*  873 */             if (cell.a == 1) {
/*  874 */               lastBorder[j] = c.a(lastBorder[j], rowParams.b
/*  875 */                   .d());
/*      */             }
/*      */           } 
/*      */           
/*  879 */           List<?> nextCells = (i < this.w.size() - 1) ? this.w.get(i + 1) : this.D;
/*      */           
/*  881 */           if (!groupLast || i < this.w.size() - 1) {
/*  882 */             s nextRowParams = this.C.c();
/*  883 */             for (int m = 0; m < nextCells.size(); m++) {
/*  884 */               a nextCell = (a)nextCells.get(m);
/*  885 */               E cellPos = nextCell.c().u();
/*  886 */               if (nextCell.a == cellPos.e) {
/*  887 */                 lastBorder[m] = c.a(lastBorder[m], nextRowParams.b
/*  888 */                     .b());
/*      */               }
/*      */             } 
/*      */           } 
/*  892 */           if (groupFirst && i == 0)
/*      */           {
/*  894 */             for (int m = 0; m < cells.size(); m++) {
/*  895 */               firstBorder[m] = c.a(firstBorder[m], rowParams.b
/*  896 */                   .b());
/*      */             }
/*      */           }
/*      */           
/*      */           int k;
/*  901 */           for (k = 0; k < cells.size(); k++) {
/*  902 */             a cell = cells.get(k);
/*  903 */             i cellParams = cell.c().c_();
/*      */ 
/*      */ 
/*      */             
/*  907 */             lineBorder[k] = c.a(lineBorder[k], cellParams.S.c
/*  908 */                 .a());
/*  909 */             k += cell.b - 1;
/*      */             
/*  911 */             lineBorder[k + 1] = c.a(lineBorder[k + 1], cellParams.S.c
/*  912 */                 .c());
/*      */           } 
/*      */           
/*  915 */           if (groupFirst && i == 0)
/*      */           {
/*  917 */             for (k = 0; k < cells.size(); k++) {
/*  918 */               a cell = cells.get(k);
/*  919 */               i cellParams = cell.c().c_();
/*  920 */               firstBorder[k] = c.a(firstBorder[k], cellParams.S.c
/*  921 */                   .b());
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  928 */           for (k = 0; k < cells.size(); k++) {
/*  929 */             a cell = cells.get(k);
/*  930 */             i cellParams = cell.c().c_();
/*      */             
/*  932 */             if (cell.a == 1) {
/*  933 */               lastBorder[k] = c.a(lastBorder[k], cellParams.S.c
/*  934 */                   .d());
/*      */             } else {
/*  936 */               lastBorder[k] = c.a(lastBorder[k], j.k);
/*      */             } 
/*      */           } 
/*      */           
/*  940 */           if (!groupLast || i < this.w.size() - 1) {
/*  941 */             for (k = 0; k < nextCells.size(); k++) {
/*  942 */               a cell = (a)nextCells.get(k);
/*  943 */               i cellParams = cell.c().c_();
/*  944 */               if (cell.a == (cell.c().u()).e) {
/*  945 */                 lastBorder[k] = c.a(lastBorder[k], cellParams.S.c
/*  946 */                     .b());
/*      */               } else {
/*  948 */                 lastBorder[k] = c.a(lastBorder[k], j.k);
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } else {
/*  954 */         for (int i = 0; i < this.w.size(); i++) {
/*  955 */           j[] firstBorder; List<?> cells = this.w.get(i);
/*  956 */           j[] lineBorder = new j[this.t.length + 1];
/*  957 */           vborders.add(lineBorder);
/*      */           
/*  959 */           if (hborders.isEmpty()) {
/*  960 */             firstBorder = new j[this.t.length];
/*  961 */             hborders.add(firstBorder);
/*      */           } else {
/*  963 */             firstBorder = hborders.get(hborders.size() - 1);
/*      */           } 
/*  965 */           j[] lastBorder = new j[this.t.length];
/*  966 */           hborders.add(lastBorder);
/*      */ 
/*      */ 
/*      */           
/*  970 */           lineBorder[0] = c.a(lineBorder[0], tableParams.S.c
/*  971 */               .d());
/*      */           
/*  973 */           lineBorder[lineBorder.length - 1] = 
/*  974 */             c.a(lineBorder[lineBorder.length - 1], tableParams.S.c.d());
/*      */           
/*  976 */           if (firstRow && i == 0) {
/*  977 */             for (int m = 0; m < firstBorder.length; m++) {
/*  978 */               firstBorder[m] = c.a(firstBorder[m], tableParams.S.c
/*  979 */                   .a());
/*      */             }
/*      */           }
/*      */           
/*  983 */           if (lastRow && i == this.w.size() - 1) {
/*  984 */             for (int m = 0; m < lastBorder.length; m++) {
/*  985 */               lastBorder[m] = c.a(lastBorder[m], tableParams.S.c
/*  986 */                   .c());
/*      */             }
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  992 */           if (this.h != null) {
/*  993 */             u columnGroup = this.h;
/*  994 */             int col = 0;
/*  995 */             List<Object> stack = new ArrayList();
/*  996 */             int m = 0;
/*      */             while (true) {
/*  998 */               for (; m < columnGroup.h(); m++) {
/*  999 */                 int colspan; t column = columnGroup.a(m);
/* 1000 */                 s colParams = column.c();
/* 1001 */                 F colPos = column.g();
/*      */                 
/* 1003 */                 if (column.a() == 8 && ((u)column)
/* 1004 */                   .h() > 0) {
/* 1005 */                   colspan = ((u)column).h();
/*      */                 } else {
/* 1007 */                   colspan = colPos.a;
/*      */                 } 
/* 1009 */                 if (firstRow && i == 0) {
/* 1010 */                   for (int n = 0; n < colspan; n++) {
/* 1011 */                     int jj = col + n;
/*      */                     
/* 1013 */                     firstBorder[jj] = c.a(firstBorder[jj], colParams.b
/* 1014 */                         .a());
/*      */                   } 
/*      */                 }
/* 1017 */                 if (lastRow && i == this.w.size() - 1) {
/* 1018 */                   for (int n = 0; n < colspan; n++) {
/* 1019 */                     int jj = col + n;
/*      */                     
/* 1021 */                     lastBorder[jj] = c.a(lastBorder[jj], colParams.b
/* 1022 */                         .c());
/*      */                   } 
/*      */                 }
/*      */                 
/* 1026 */                 lineBorder[col] = c.a(lineBorder[col], colParams.b
/* 1027 */                     .d());
/*      */                 
/* 1029 */                 lineBorder[col + colspan] = 
/* 1030 */                   c.a(lineBorder[col + colspan], colParams.b.b());
/* 1031 */                 if (column.a() == 8 && ((u)column)
/* 1032 */                   .h() > 0) {
/* 1033 */                   stack.add(columnGroup);
/* 1034 */                   stack.add(d.a(m + 1));
/* 1035 */                   columnGroup = (u)column;
/* 1036 */                   m = 0;
/*      */                   continue;
/*      */                 } 
/* 1039 */                 col += colspan;
/*      */               } 
/*      */               
/* 1042 */               if (stack.isEmpty()) {
/*      */                 break;
/*      */               }
/* 1045 */               m = ((Integer)stack.remove(stack.size() - 1)).intValue();
/* 1046 */               columnGroup = (u)stack.remove(stack.size() - 1);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1052 */           lineBorder[0] = c.a(lineBorder[0], rowGroupParams.b
/* 1053 */               .d());
/*      */           
/* 1055 */           lineBorder[lineBorder.length - 1] = 
/* 1056 */             c.a(lineBorder[lineBorder.length - 1], rowGroupParams.b.d());
/*      */           
/* 1058 */           if (groupFirst) {
/* 1059 */             for (int m = 0; m < this.t.length; m++) {
/* 1060 */               firstBorder[m] = c.a(firstBorder[m], rowGroupParams.b
/* 1061 */                   .a());
/*      */             }
/*      */           }
/*      */           
/* 1065 */           if (groupLast) {
/* 1066 */             for (int m = 0; m < this.t.length; m++) {
/* 1067 */               lastBorder[m] = c.a(lastBorder[m], rowGroupParams.b
/* 1068 */                   .c());
/*      */             }
/*      */           }
/*      */ 
/*      */           
/* 1073 */           v rowBox = this.x.get(i);
/* 1074 */           s rowParams = rowBox.c();
/*      */           
/* 1076 */           lineBorder[0] = c.a(lineBorder[0], rowParams.b.d());
/*      */           
/* 1078 */           lineBorder[lineBorder.length - 1] = 
/* 1079 */             c.a(lineBorder[lineBorder.length - 1], rowParams.b.d());
/*      */           
/* 1081 */           for (int j = 0; j < cells.size(); j++) {
/* 1082 */             a cell = (a)cells.get(j);
/* 1083 */             if (cell.a == 1) {
/* 1084 */               lastBorder[j] = c.a(lastBorder[j], rowParams.b
/* 1085 */                   .c());
/*      */             }
/*      */           } 
/*      */           
/* 1089 */           List<?> nextCells = (i < this.w.size() - 1) ? this.w.get(i + 1) : this.D;
/*      */           
/* 1091 */           if (!groupLast || i < this.w.size() - 1) {
/* 1092 */             s nextRowParams = this.C.c();
/* 1093 */             for (int m = 0; m < nextCells.size(); m++) {
/* 1094 */               a nextCell = (a)nextCells.get(m);
/* 1095 */               E cellPps = nextCell.c().u();
/* 1096 */               if (nextCell.a == cellPps.e) {
/* 1097 */                 lastBorder[m] = c.a(lastBorder[m], nextRowParams.b
/* 1098 */                     .a());
/*      */               }
/*      */             } 
/*      */           } 
/* 1102 */           if (groupFirst && i == 0)
/*      */           {
/* 1104 */             for (int m = 0; m < cells.size(); m++) {
/* 1105 */               firstBorder[m] = c.a(firstBorder[m], rowParams.b
/* 1106 */                   .a());
/*      */             }
/*      */           }
/*      */           
/*      */           int k;
/* 1111 */           for (k = 0; k < cells.size(); k++) {
/* 1112 */             a cell = (a)cells.get(k);
/* 1113 */             i cellParams = cell.c().c_();
/*      */ 
/*      */ 
/*      */             
/* 1117 */             lineBorder[k] = c.a(lineBorder[k], cellParams.S.c
/* 1118 */                 .d());
/* 1119 */             k += cell.b - 1;
/*      */             
/* 1121 */             lineBorder[k + 1] = c.a(lineBorder[k + 1], cellParams.S.c
/* 1122 */                 .b());
/*      */           } 
/*      */           
/* 1125 */           if (groupFirst && i == 0)
/*      */           {
/* 1127 */             for (k = 0; k < cells.size(); k++) {
/* 1128 */               a cell = (a)cells.get(k);
/* 1129 */               i cellParams = cell.c().c_();
/* 1130 */               firstBorder[k] = c.a(firstBorder[k], cellParams.S.c
/* 1131 */                   .a());
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1138 */           for (k = 0; k < cells.size(); k++) {
/* 1139 */             a cell = (a)cells.get(k);
/* 1140 */             i cellParams = cell.c().c_();
/*      */             
/* 1142 */             if (cell.a == 1) {
/* 1143 */               lastBorder[k] = c.a(lastBorder[k], cellParams.S.c
/* 1144 */                   .c());
/*      */             } else {
/* 1146 */               lastBorder[k] = c.a(lastBorder[k], j.k);
/*      */             } 
/*      */           } 
/*      */           
/* 1150 */           if (!groupLast || i < this.w.size() - 1) {
/* 1151 */             for (k = 0; k < nextCells.size(); k++) {
/* 1152 */               a cell = (a)nextCells.get(k);
/* 1153 */               i cellParams = cell.c().c_();
/* 1154 */               if (cell.a == (cell.c().u()).e) {
/* 1155 */                 lastBorder[k] = c.a(lastBorder[k], cellParams.S.c
/* 1156 */                     .a());
/*      */               } else {
/* 1158 */                 lastBorder[k] = c.a(lastBorder[k], j.k);
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1167 */     for (int row = 0; row < this.w.size(); row++) {
/* 1168 */       List<?> cells = this.w.get(row);
/* 1169 */       v rowBox = this.x.get(row);
/* 1170 */       for (int i = 0; i < cells.size(); i++) {
/* 1171 */         a cell = (a)cells.get(i);
/* 1172 */         int span = cell.b;
/* 1173 */         if (cell.a()) {
/* 1174 */           i += span - 1;
/* 1175 */           v.b source = this.v.get(cell.c());
/* 1176 */           rowBox.a(source);
/*      */         } else {
/*      */           
/* 1179 */           s cellBox = cell.c();
/* 1180 */           if (tableParams.ax == 0) {
/*      */             
/* 1182 */             double top = tableParams.aw / 2.0D;
/* 1183 */             double right = tableParams.av / 2.0D;
/* 1184 */             double bottom = tableParams.aw / 2.0D;
/* 1185 */             double left = tableParams.av / 2.0D;
/* 1186 */             jp.cssj.homare.b.e.a cellSpacing = new jp.cssj.homare.b.e.a(top, right, bottom, left);
/* 1187 */             cellBox.a(this.d.k().h(), this.c, cellSpacing);
/*      */           } else {
/*      */             List<j[]> vborders, hborders;
/*      */             jp.cssj.homare.b.e.a spacing;
/* 1191 */             switch (rowGroupPos.c) {
/*      */               case 1:
/* 1193 */                 vborders = this.l;
/* 1194 */                 hborders = this.k;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 3:
/* 1199 */                 vborders = this.p;
/* 1200 */                 hborders = this.o;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 2:
/* 1205 */                 vborders = this.n;
/* 1206 */                 hborders = this.m;
/*      */                 break;
/*      */               
/*      */               default:
/* 1210 */                 throw new IllegalStateException();
/*      */             } 
/* 1212 */             double pageFirst = 0.0D, lineEnd = 0.0D, pageLast = 0.0D, lineStart = 0.0D;
/* 1213 */             int borderRow = hborders.size() - this.w.size() + row;
/* 1214 */             j[] prevBorder = hborders.get(borderRow - 1);
/*      */             
/* 1216 */             j[] nextBorder = hborders.get(Math.min(hborders.size() - 1, borderRow + cell.a - 1));
/* 1217 */             j[] lineBorder = vborders.get(borderRow - 1);
/* 1218 */             for (int k = 0; k < cell.b; k++) {
/* 1219 */               int kk = i + k;
/* 1220 */               if (kk >= this.t.length) {
/*      */                 break;
/*      */               }
/* 1223 */               if (prevBorder[kk] != null) {
/* 1224 */                 pageFirst = Math.max(pageFirst, (prevBorder[kk]).m / 2.0D);
/*      */               }
/* 1226 */               if (nextBorder[kk] != null) {
/* 1227 */                 pageLast = Math.max(pageLast, (nextBorder[kk]).m / 2.0D);
/*      */               }
/*      */             } 
/* 1230 */             if (lineBorder[i] != null) {
/* 1231 */               lineStart = Math.max(lineStart, (lineBorder[i]).m / 2.0D);
/*      */             }
/* 1233 */             if (lineBorder[i + cell.b] != null) {
/* 1234 */               lineEnd = Math.max(lineEnd, (lineBorder[i + cell.b]).m / 2.0D);
/*      */             }
/*      */             
/* 1237 */             if (this.b) {
/* 1238 */               spacing = new jp.cssj.homare.b.e.a(lineStart, pageFirst, lineEnd, pageLast);
/*      */             } else {
/* 1240 */               spacing = new jp.cssj.homare.b.e.a(pageFirst, lineEnd, pageLast, lineStart);
/*      */             } 
/* 1242 */             cellBox.a(this.d.k().h(), this.c, spacing);
/*      */           } 
/*      */ 
/*      */           
/* 1246 */           double d = this.t[i];
/* 1247 */           for (int j = 1; j < span; j++) {
/* 1248 */             d = d + this.t[++i];
/*      */           }
/* 1250 */           if (this.b) {
/* 1251 */             cellBox.c(d);
/* 1252 */             if (!e.a((cellBox.c_()).D)) {
/* 1253 */               cellBox.b((cellBox.c_()).C.e() * 10.0D);
/*      */             }
/*      */           } else {
/* 1256 */             cellBox.b(d);
/* 1257 */             if (e.a((cellBox.c_()).D)) {
/* 1258 */               cellBox.c((cellBox.c_()).C.e() * 10.0D);
/*      */             }
/*      */           } 
/* 1261 */           a cellBindBuilder = new a((d)this.d, (c)cellBox);
/* 1262 */           cell.b().a(cellBindBuilder);
/* 1263 */           cellBindBuilder.x();
/* 1264 */           v.b source = rowBox.a(cellBox);
/* 1265 */           this.v.put(cellBox, source);
/*      */         } 
/*      */       } 
/* 1268 */     }  if (this.w.size() == 1) {
/*      */       
/* 1270 */       List<?> cells = this.w.get(0);
/* 1271 */       v rowBox = this.x.get(0);
/* 1272 */       double rowSize = a(rowBox);
/* 1273 */       double rowAscent = 0.0D; int i;
/* 1274 */       for (i = 0; i < cells.size(); i++) {
/* 1275 */         a cell = (a)cells.get(i);
/* 1276 */         if (cell.a()) {
/* 1277 */           i += cell.b - 1;
/*      */         } else {
/*      */           
/* 1280 */           s cellBox = cell.c();
/* 1281 */           double firstAscent = cellBox.n();
/* 1282 */           if (!e.a(firstAscent) && firstAscent > rowAscent)
/* 1283 */             rowAscent = firstAscent; 
/*      */         } 
/*      */       } 
/* 1286 */       for (i = 0; i < cells.size(); i++) {
/* 1287 */         double cellSize; a cell = (a)cells.get(i);
/* 1288 */         s cellBox = cell.c();
/* 1289 */         i cellParams = cellBox.c_();
/*      */         
/* 1291 */         if (this.b) {
/* 1292 */           cellSize = cellBox.p();
/* 1293 */           if (cellParams.X.a() == 1) {
/* 1294 */             double width = cellParams.X.c();
/* 1295 */             cellSize = Math.max(cellSize, width);
/*      */           } 
/*      */         } else {
/* 1298 */           cellSize = cellBox.q();
/* 1299 */           if (cellParams.X.b() == 1) {
/* 1300 */             double height = cellParams.X.d();
/* 1301 */             cellSize = Math.max(cellSize, height);
/*      */           } 
/*      */         } 
/* 1304 */         rowSize = Math.max(rowSize, cellSize);
/*      */       } 
/* 1306 */       for (int j = 0; j < cells.size(); j++) {
/* 1307 */         a cell = (a)cells.get(j);
/* 1308 */         if (!cell.a()) {
/*      */ 
/*      */           
/* 1311 */           s cellBox = cell.c();
/* 1312 */           cellBox.d(rowAscent);
/* 1313 */           if (this.b) {
/* 1314 */             cellBox.b(rowSize);
/*      */           } else {
/* 1316 */             cellBox.c(rowSize);
/*      */           } 
/* 1318 */           cellBox.v();
/* 1319 */           j += cell.b - 1;
/*      */         } 
/* 1321 */       }  rowBox.a(this.j);
/* 1322 */       rowBox.b(rowSize);
/* 1323 */       this.B.a(rowBox);
/* 1324 */       if (tableParams.ax == 1)
/*      */       {
/* 1326 */         a(rowSize);
/*      */       }
/* 1328 */       this.i += rowSize;
/*      */     } else {
/*      */       
/* 1331 */       Map<i, i> rowspans = new HashMap<>();
/* 1332 */       List<i> rowspanList = new ArrayList<>();
/* 1333 */       boolean[] noAdjRows = new boolean[this.w.size()];
/* 1334 */       boolean[] autoRows = new boolean[this.w.size()];
/*      */       
/* 1336 */       for (int m = 0; m < this.w.size(); m++) {
/* 1337 */         List<?> cells = this.w.get(m);
/* 1338 */         v rowBox = this.x.get(m);
/* 1339 */         s rowParams = rowBox.c();
/* 1340 */         double d1 = a(rowBox);
/* 1341 */         if (rowParams.c.a() == 3) {
/* 1342 */           autoRows[m] = true;
/*      */         }
/* 1344 */         double rowAscent = 0.0D; int n;
/* 1345 */         for (n = 0; n < cells.size(); n++) {
/* 1346 */           a cell = (a)cells.get(n);
/* 1347 */           if (cell.a()) {
/* 1348 */             n += cell.b - 1;
/*      */           } else {
/*      */             
/* 1351 */             s cellBox = cell.c();
/* 1352 */             double firstAscent = cellBox.n();
/* 1353 */             if (!e.a(firstAscent) && firstAscent > rowAscent)
/* 1354 */               rowAscent = firstAscent; 
/*      */           } 
/*      */         } 
/* 1357 */         for (n = 0; n < cells.size(); n++) {
/* 1358 */           a cell = (a)cells.get(n);
/* 1359 */           if (cell.a()) {
/* 1360 */             n += cell.b - 1;
/*      */           } else {
/*      */             
/* 1363 */             s cellBox = cell.c();
/* 1364 */             cellBox.d(rowAscent);
/* 1365 */             i cellParams = cellBox.c_();
/* 1366 */             double cellSize = cellBox.q();
/* 1367 */             if (cellParams.X.b() == 1) {
/* 1368 */               double height = cellParams.X.d();
/* 1369 */               if (cellParams.aa == 1) {
/* 1370 */                 height += cellBox.m().e();
/*      */               }
/* 1372 */               cellSize = Math.max(cellSize, height);
/*      */             } 
/*      */             
/* 1375 */             int cellRowspan = Math.min(this.w.size() - m, cell.a);
/* 1376 */             if (cellRowspan <= 1) {
/*      */               
/* 1378 */               noAdjRows[m] = true;
/* 1379 */               d1 = Math.max(d1, cellSize);
/*      */             } else {
/*      */               
/* 1382 */               i key = new i(m, cellRowspan);
/* 1383 */               i rowspan = rowspans.get(key);
/* 1384 */               if (rowspan == null) {
/* 1385 */                 rowspan = key;
/* 1386 */                 rowspans.put(key, rowspan);
/* 1387 */                 rowspanList.add(rowspan);
/*      */               } 
/* 1389 */               rowspan.c = Math.max(rowspan.c, cellSize);
/*      */             } 
/* 1391 */             n += cell.b - 1;
/*      */           } 
/* 1393 */         }  rowBox.b(d1);
/*      */       } 
/*      */ 
/*      */       
/* 1397 */       Collections.sort(rowspanList, i.d);
/* 1398 */       for (int j = 0; j < rowspanList.size(); j++) {
/* 1399 */         i rowspan = rowspanList.get(j);
/* 1400 */         double minSum = ((v)this.x.get(rowspan.a)).f();
/* 1401 */         for (int n = 1; n < rowspan.b; n++) {
/* 1402 */           int kk = rowspan.a + n;
/* 1403 */           if (kk < this.x.size()) {
/* 1404 */             v rowBox = this.x.get(kk);
/* 1405 */             minSum += rowBox.f();
/*      */           } 
/*      */         } 
/* 1408 */         double minRem = rowspan.c - minSum;
/* 1409 */         if (minRem > 0.0D) {
/*      */           
/* 1411 */           double adjCount = 0.0D, autoCount = 0.0D; int i1;
/* 1412 */           for (i1 = 0; i1 < rowspan.b; i1++) {
/* 1413 */             int kk = rowspan.a + i1;
/* 1414 */             if (kk >= this.x.size()) {
/*      */               break;
/*      */             }
/* 1417 */             if (!noAdjRows[kk] && autoRows[kk]) {
/* 1418 */               adjCount++;
/*      */             }
/* 1420 */             if (autoRows[kk]) {
/* 1421 */               autoCount++;
/*      */             }
/*      */             
/* 1424 */             v rowBox = this.x.get(kk);
/* 1425 */             if ((rowBox.c()).c.a() == 2) {
/* 1426 */               double diff = minRem * (rowBox.c()).c.b();
/* 1427 */               minRem -= diff;
/* 1428 */               rowBox.b(rowBox.f() + diff);
/*      */             } 
/*      */           } 
/* 1431 */           if (adjCount > 0.0D && adjCount < rowspan.b) {
/*      */             
/* 1433 */             minRem /= adjCount;
/* 1434 */             for (i1 = 0; i1 < rowspan.b; i1++) {
/* 1435 */               int kk = rowspan.a + i1;
/* 1436 */               if (kk >= this.x.size()) {
/*      */                 break;
/*      */               }
/* 1439 */               if (!noAdjRows[kk] && autoRows[kk]) {
/* 1440 */                 v rowBox = this.x.get(kk);
/* 1441 */                 double size = rowBox.f() + minRem;
/* 1442 */                 rowBox.b(size);
/*      */               } 
/*      */             } 
/* 1445 */           } else if (autoCount > 0.0D && autoCount < rowspan.b) {
/*      */             
/* 1447 */             minRem /= autoCount;
/* 1448 */             for (i1 = 0; i1 < rowspan.b; i1++) {
/* 1449 */               int kk = rowspan.a + i1;
/* 1450 */               if (kk >= this.x.size()) {
/*      */                 break;
/*      */               }
/* 1453 */               if (autoRows[kk]) {
/* 1454 */                 v rowBox = this.x.get(kk);
/* 1455 */                 double size = rowBox.f() + minRem;
/* 1456 */                 rowBox.b(size);
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/* 1461 */             minRem /= rowspan.b;
/* 1462 */             for (i1 = 0; i1 < rowspan.b; i1++) {
/* 1463 */               int kk = rowspan.a + i1;
/* 1464 */               if (kk >= this.x.size()) {
/*      */                 break;
/*      */               }
/* 1467 */               v rowBox = this.x.get(kk);
/* 1468 */               double size = rowBox.f() + minRem;
/* 1469 */               rowBox.b(size);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1476 */       if (rowGroupParams.c.a() == 1) {
/* 1477 */         double rowSizeSum = 0.0D;
/* 1478 */         for (int n = 0; n < this.x.size(); n++) {
/* 1479 */           v rowBox = this.x.get(n);
/* 1480 */           rowSizeSum += rowBox.f();
/*      */         } 
/* 1482 */         double groupSize = rowGroupParams.c.b();
/* 1483 */         if (groupSize > rowSizeSum) {
/* 1484 */           for (int i1 = 0; i1 < this.x.size(); i1++) {
/* 1485 */             v rowBox = this.x.get(i1);
/* 1486 */             double rowSize = rowBox.f();
/* 1487 */             if (rowSizeSum == 0.0D) {
/* 1488 */               rowBox.b(groupSize / this.x.size());
/*      */             } else {
/* 1490 */               rowBox.b(rowSize * groupSize / rowSizeSum);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1497 */       for (int k = 0; k < this.x.size(); k++) {
/* 1498 */         v rowBox = this.x.get(k);
/* 1499 */         this.B.a(rowBox);
/*      */       } 
/*      */       
/*      */       int i;
/* 1503 */       for (i = 0; i < this.x.size(); i++) {
/* 1504 */         List<?> cells = this.w.get(i);
/* 1505 */         v rowBox = this.x.get(i);
/* 1506 */         for (int n = 0; n < cells.size(); n++) {
/* 1507 */           a cell = (a)cells.get(n);
/* 1508 */           if (!cell.a()) {
/*      */ 
/*      */             
/* 1511 */             double rowSize = rowBox.f();
/* 1512 */             for (int i1 = 1; i1 < cell.a; i1++) {
/* 1513 */               int kk = i + i1;
/* 1514 */               if (kk >= this.x.size()) {
/*      */                 break;
/*      */               }
/* 1517 */               v xrowBox = this.x.get(kk);
/* 1518 */               rowSize += xrowBox.f();
/*      */             } 
/* 1520 */             s cellBox = cell.c();
/* 1521 */             if (this.b) {
/* 1522 */               cellBox.b(rowSize);
/*      */             } else {
/* 1524 */               cellBox.c(rowSize);
/*      */             } 
/* 1526 */             cellBox.v();
/* 1527 */             n += cell.b - 1;
/*      */           } 
/*      */         } 
/* 1530 */       }  if (tableParams.ax == 1) {
/*      */         
/* 1532 */         for (i = 0; i < this.x.size(); i++) {
/* 1533 */           v rowBox = this.x.get(i);
/* 1534 */           double rowSize = rowBox.f();
/* 1535 */           this.i += rowSize;
/* 1536 */           a(rowSize);
/*      */         } 
/*      */       } else {
/* 1539 */         for (i = 0; i < this.x.size(); i++) {
/* 1540 */           v rowBox = this.x.get(i);
/* 1541 */           double rowSize = rowBox.f();
/* 1542 */           this.i += rowSize;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1547 */     this.w.clear();
/* 1548 */     this.x.clear();
/*      */     
/* 1550 */     if (this.d.r != 0 && 
/* 1551 */       (this.B.g()).c == 2) {
/* 1552 */       while (b(groupLast));
/*      */     }
/*      */     
/* 1555 */     if (groupLast) {
/*      */       
/* 1557 */       switch ((this.B.g()).c) {
/*      */         case 1:
/* 1559 */           this.c.a(this.B);
/*      */           break;
/*      */         case 3:
/* 1562 */           this.c.b(this.B);
/*      */           break;
/*      */         case 2:
/* 1565 */           this.c.c(this.B);
/*      */           break;
/*      */         default:
/* 1568 */           throw new IllegalStateException();
/*      */       } 
/* 1570 */       this.B = this.u;
/* 1571 */       this.A = true;
/*      */     } 
/* 1573 */     if (this.d.r != 0 && this.B != null && 
/* 1574 */       (this.B.g()).c == 2)
/*      */       while (true) {
/*      */         
/* 1577 */         double pageBottom = this.d.z() - this.d.t();
/*      */         
/* 1579 */         if (e.a(this.i, pageBottom) > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1584 */           double pageLimit = this.d.z();
/* 1585 */           pageLimit -= this.d.t();
/* 1586 */           pageLimit -= this.c.i().a();
/* 1587 */           if (this.c.k() != null) {
/* 1588 */             pageLimit -= this.c.k().f();
/*      */           }
/* 1590 */           if (this.c.l() != null) {
/* 1591 */             pageLimit -= this.c.l().f();
/* 1592 */             pageLimit -= this.c.i().c();
/*      */           } 
/* 1594 */           for (int i = 0; i < this.c.m(); i++) {
/* 1595 */             pageLimit -= this.c.a(i).f();
/*      */           }
/* 1597 */           byte flags = (this.c.m() == 0) ? 1 : 0;
/* 1598 */           if (a((d)d.c, pageLimit, flags))
/*      */             continue; 
/*      */         } 
/*      */         break;
/*      */       }  
/*      */   }
/*      */   
/*      */   private boolean b(boolean groupLast) {
/*      */     double firstFrame, lastFrame;
/*      */     v v1;
/*      */     boolean forceBreak;
/* 1609 */     if (e.a((this.c.g()).D)) {
/* 1610 */       firstFrame = this.c.i().d();
/* 1611 */       lastFrame = this.c.i().b();
/*      */     } else {
/* 1613 */       firstFrame = this.c.i().a();
/* 1614 */       lastFrame = this.c.i().c();
/*      */     } 
/* 1616 */     double pageLimit = this.d.z();
/* 1617 */     pageLimit -= this.d.t();
/* 1618 */     pageLimit -= firstFrame;
/* 1619 */     if (this.c.k() != null) {
/* 1620 */       pageLimit -= this.c.k().f();
/*      */     }
/* 1622 */     if (this.c.l() != null) {
/* 1623 */       pageLimit -= this.c.l().f();
/* 1624 */       pageLimit -= lastFrame;
/*      */     } 
/* 1626 */     for (int i = 0; i < this.c.m(); i++) {
/* 1627 */       w groupBox = this.c.a(i);
/* 1628 */       pageLimit -= groupBox.f();
/*      */     } 
/* 1630 */     byte flags = (this.c.m() == 0) ? 1 : 0;
/* 1631 */     d box = null;
/* 1632 */     int row = 0;
/* 1633 */     double rowSplitLine = pageLimit;
/* 1634 */     byte breakMode = 0;
/* 1635 */     for (; row < this.B.h(); row++) {
/* 1636 */       v rowBox = this.B.a(row);
/* 1637 */       double rowSize = rowBox.f();
/*      */ 
/*      */       
/* 1640 */       if (e.a(rowSize, rowSplitLine) > 0) {
/*      */         break;
/*      */       }
/* 1643 */       J pos = rowBox.g();
/* 1644 */       if (row > 0) {
/* 1645 */         breakMode = pos.a;
/* 1646 */         if (breakMode == 4 || breakMode == 7) {
/*      */           
/* 1648 */           row--;
/* 1649 */           v1 = this.B.a(row);
/*      */           break;
/*      */         } 
/*      */       } 
/* 1653 */       if (row == this.B.h() - 1) {
/*      */         break;
/*      */       }
/*      */       
/* 1657 */       breakMode = pos.b;
/* 1658 */       if (breakMode == 4 || breakMode == 7) {
/*      */         
/* 1660 */         v1 = rowBox;
/*      */         break;
/*      */       } 
/* 1663 */       rowSplitLine -= rowSize;
/*      */     } 
/* 1665 */     if (v1 != null) {
/* 1666 */       d.c mode = new d.c((d)v1, breakMode, 0, row);
/* 1667 */       return a((d)mode, pageLimit, (byte)0);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1672 */     if (e.a(pageLimit, 0.0D) > 0)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1677 */       if (a((d)d.c, pageLimit, flags)) {
/* 1678 */         return true;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 1683 */     if (groupLast && this.B != null && this.u != null)
/* 1684 */     { forceBreak = true;
/*      */       
/* 1686 */       breakMode = (this.B.g()).b;
/* 1687 */       if (breakMode != 4 && breakMode != 7)
/*      */       
/*      */       { 
/* 1690 */         if (this.B.h() > 0)
/*      */         
/* 1692 */         { breakMode = (this.B.a(this.B.h() - 1).g()).b;
/* 1693 */           if (breakMode == 4 || breakMode == 7)
/*      */           
/*      */           { 
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
/* 1708 */             if (forceBreak) {
/*      */ 
/*      */               
/* 1711 */               d.c mode = new d.c((d)this.B, breakMode, 0, -1);
/* 1712 */               a((d)mode, pageLimit, (byte)0);
/*      */             } 
/*      */             
/* 1715 */             return false; }  }  breakMode = (this.u.g()).a; if (breakMode != 4 && breakMode != 7) { breakMode = (this.C.g()).a; if (breakMode != 4 && breakMode != 7) forceBreak = false;  }  }  } else { return false; }  if (forceBreak) { d.c c1 = new d.c((d)this.B, breakMode, 0, -1); a((d)c1, pageLimit, (byte)0); }  return false;
/*      */   }
/*      */   
/*      */   private boolean a(d mode, double pageLimit, byte flags) {
/* 1719 */     if (e.a(this.d.t(), 0.0D) > 0) {
/* 1720 */       flags = (byte)(flags & 0xFFFFFFFE);
/*      */     }
/* 1722 */     w rowGroupBox = this.B;
/* 1723 */     int rowCount = rowGroupBox.h();
/*      */ 
/*      */     
/* 1726 */     if ((flags & 0x1) == 0 && 
/* 1727 */       (this.c.g()).U == 1)
/*      */     {
/* 1729 */       return false;
/*      */     }
/* 1731 */     w nextRowGroupBox = (w)rowGroupBox.b(pageLimit, mode, flags);
/* 1732 */     if (nextRowGroupBox == null || nextRowGroupBox == rowGroupBox)
/*      */     {
/* 1734 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1738 */     this.c.c(rowGroupBox);
/*      */     
/* 1740 */     G tableParams = this.c.g();
/* 1741 */     if (tableParams.ax == 1) {
/*      */       
/* 1743 */       int nextRowCount = nextRowGroupBox.h();
/* 1744 */       List<j[]> nextBodyHborders = (List)new ArrayList<>();
/* 1745 */       List<j[]> nextBodyVborders = (List)new ArrayList<>();
/* 1746 */       c nextBodyRowSizes = new c();
/* 1747 */       for (int i = 0; i < nextRowCount; i++) {
/* 1748 */         nextBodyHborders.add(0, this.m.remove(this.m.size() - 1));
/* 1749 */         nextBodyVborders.add(0, this.n.remove(this.n.size() - 1));
/* 1750 */         nextBodyRowSizes.b(0, this.r.b(this.r.b() - 1));
/*      */       } 
/* 1752 */       nextBodyHborders.add(0, this.m.get(this.m.size() - 1));
/* 1753 */       if (nextRowCount + rowGroupBox.h() > rowCount) {
/*      */         
/* 1755 */         this.n.add(nextBodyVborders.get(0));
/* 1756 */         j[] hborders = new j[((j[])nextBodyHborders.get(0)).length];
/* 1757 */         this.m.add(hborders);
/* 1758 */         double nextSize = nextRowGroupBox.a(0).f();
/* 1759 */         this.r.a(nextBodyRowSizes.a(0) - nextSize);
/* 1760 */         nextBodyRowSizes.a(0, nextSize);
/*      */       } 
/* 1762 */       h();
/* 1763 */       this.m = nextBodyHborders;
/* 1764 */       this.n = nextBodyVborders;
/* 1765 */       this.r = nextBodyRowSizes;
/*      */     } 
/*      */     
/* 1768 */     this.d.a((j)this.c);
/* 1769 */     f();
/* 1770 */     this.c = this.c.n();
/* 1771 */     byte breakMode = 7;
/* 1772 */     if (mode.a() == 1 && 
/* 1773 */       ((d.b)mode).e != 7) {
/* 1774 */       breakMode = 4;
/*      */     }
/*      */     
/* 1777 */     this.d.b(breakMode);
/* 1778 */     if (this.h != null) {
/* 1779 */       this.h = (u)this.h.a(0.0D, 0.0D);
/* 1780 */       this.c.a(this.h);
/*      */     } 
/*      */     
/* 1783 */     if (this.u == this.B) {
/* 1784 */       this.u = this.B = nextRowGroupBox;
/*      */     } else {
/* 1786 */       this.B = nextRowGroupBox;
/*      */     } 
/* 1788 */     this.A = true;
/* 1789 */     if (this.b) {
/* 1790 */       this.i = this.c.p();
/*      */     } else {
/* 1792 */       this.i = this.c.q();
/*      */     } 
/* 1794 */     this.i += this.B.f();
/* 1795 */     return true;
/*      */   }
/*      */   
/*      */   private void a(double size) {
/* 1799 */     switch ((this.B.g()).c) {
/*      */       case 1:
/* 1801 */         if (this.q == null) {
/* 1802 */           this.q = new c();
/*      */         }
/* 1804 */         this.q.a(size);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1808 */         if (this.s == null) {
/* 1809 */           this.s = new c();
/*      */         }
/* 1811 */         this.s.a(size);
/*      */         return;
/*      */       
/*      */       case 2:
/* 1815 */         if (this.r == null) {
/* 1816 */           this.r = new c();
/*      */         }
/* 1818 */         this.r.a(size);
/*      */         return;
/*      */     } 
/* 1821 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void g() {
/* 1826 */     if (!this.w.isEmpty()) {
/*      */       
/* 1828 */       List<?> upperCells = this.w.get(this.w.size() - 1);
/* 1829 */       while (upperCells.size() > this.D.size()) {
/* 1830 */         a upperCell = (a)upperCells.get(this.D.size());
/* 1831 */         if (upperCell.a > 1)
/* 1832 */           for (int colspan = upperCell.b; colspan >= 1; colspan--)
/* 1833 */             this.D.add(new a(upperCell.c(), upperCell.a - 1, colspan));  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public jp.cssj.homare.b.b.a a(c box) {
/*      */     jp.cssj.homare.b.b.a builder;
/*      */     f caption;
/*      */     s cellBox;
/*      */     int colspan;
/*      */     a cell;
/*      */     int i;
/* 1844 */     switch (box.a()) {
/*      */       
/*      */       case 5:
/* 1847 */         caption = (f)box;
/* 1848 */         builder = new l((d)this.d, (c)caption);
/* 1849 */         switch (((D)caption.b_()).v)
/*      */         { case 1:
/* 1851 */             this.f.add(builder);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1888 */             return builder;case 2: this.g.add(builder); return builder; }  throw new IllegalStateException();case 12: cellBox = (s)box; builder = new l((d)this.d, (c)cellBox); colspan = (cellBox.u()).c; if (this.t != null) { int remainder = this.t.length - this.D.size(); if (remainder <= 0) return builder;  colspan = Math.min(colspan, remainder); }  cell = new a((l)builder, colspan); this.D.add(cell); for (i = cell.b; i > 1; i--) this.D.add(new a(cell.c(), cell.a, i));  g(); return builder;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   } public void a(h builder) {
/* 1892 */     if (!a && this.c.h().b_().a() != 4) throw new AssertionError(); 
/* 1893 */     f flowBox = (f)this.c.h();
/* 1894 */     this.d = builder;
/* 1895 */     this.d.a(flowBox);
/* 1896 */     this.i = 0.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   private void h() {
/* 1901 */     int columnCount = (this.t == null) ? 0 : this.t.length;
/* 1902 */     double[] headerRowSizes = null;
/* 1903 */     j[][] headerHborders = (j[][])null;
/* 1904 */     j[][] headerVborders = (j[][])null;
/*      */ 
/*      */     
/* 1907 */     if (this.k != null) {
/* 1908 */       int groupRowCount = this.l.size();
/* 1909 */       headerRowSizes = this.q.a();
/* 1910 */       headerHborders = new j[columnCount][groupRowCount + 1];
/* 1911 */       headerVborders = new j[groupRowCount][];
/* 1912 */       for (int i = 0; i < groupRowCount; i++) {
/* 1913 */         j[] arrayOfJ = this.k.get(i);
/* 1914 */         for (int k = 0; k < columnCount; k++) {
/* 1915 */           headerHborders[k][i] = arrayOfJ[k];
/*      */         }
/* 1917 */         headerVborders[i] = this.l.get(i);
/*      */       } 
/* 1919 */       j[] border = this.k.get(groupRowCount);
/* 1920 */       for (int j = 0; j < columnCount; j++) {
/* 1921 */         headerHborders[j][headerRowSizes.length] = border[j];
/*      */       }
/*      */     } 
/*      */     
/* 1925 */     double[] bodyRowSizes = null;
/* 1926 */     j[][] bodyHborders = (j[][])null;
/* 1927 */     j[][] bodyVborders = (j[][])null;
/*      */ 
/*      */     
/* 1930 */     if (this.n != null) {
/* 1931 */       int groupRowCount = this.n.size();
/* 1932 */       bodyRowSizes = this.r.a();
/* 1933 */       bodyHborders = new j[columnCount][groupRowCount + 1];
/* 1934 */       bodyVborders = new j[groupRowCount][];
/* 1935 */       for (int i = 0; i < groupRowCount; i++) {
/* 1936 */         j[] arrayOfJ = this.m.get(i);
/* 1937 */         for (int k = 0; k < columnCount; k++) {
/* 1938 */           bodyHborders[k][i] = arrayOfJ[k];
/*      */         }
/* 1940 */         bodyVborders[i] = this.n.get(i);
/*      */       } 
/* 1942 */       j[] border = this.m.get(groupRowCount);
/* 1943 */       for (int j = 0; j < columnCount; j++) {
/* 1944 */         bodyHborders[j][bodyRowSizes.length] = border[j];
/*      */       }
/*      */     } 
/*      */     
/* 1948 */     double[] footerRowSizes = null;
/* 1949 */     j[][] footerHborders = (j[][])null;
/* 1950 */     j[][] footerVborders = (j[][])null;
/*      */ 
/*      */     
/* 1953 */     if (this.o != null) {
/* 1954 */       int groupRowCount = this.p.size();
/* 1955 */       footerRowSizes = this.s.a();
/* 1956 */       footerHborders = new j[columnCount][groupRowCount + 1];
/* 1957 */       footerVborders = new j[groupRowCount][];
/* 1958 */       for (int i = 0; i < groupRowCount; i++) {
/* 1959 */         j[] arrayOfJ = this.o.get(i);
/* 1960 */         for (int k = 0; k < columnCount; k++) {
/* 1961 */           footerHborders[k][i] = arrayOfJ[k];
/*      */         }
/* 1963 */         footerVborders[i] = this.p.get(i);
/*      */       } 
/* 1965 */       j[] border = this.o.get(groupRowCount);
/* 1966 */       for (int j = 0; j < columnCount; j++) {
/* 1967 */         footerHborders[j][footerRowSizes.length] = border[j];
/*      */       }
/*      */     } 
/*      */     
/* 1971 */     c borders = new c(this.t, headerRowSizes, headerVborders, headerHborders, bodyRowSizes, bodyVborders, bodyHborders, footerRowSizes, footerVborders, footerHborders);
/*      */ 
/*      */     
/* 1974 */     this.c.a(borders);
/*      */   }
/*      */ 
/*      */   
/*      */   public void d() {
/* 1979 */     if (!this.x.isEmpty()) {
/* 1980 */       a(true);
/*      */     }
/*      */     
/* 1983 */     f();
/* 1984 */     G tableParams = this.c.g();
/* 1985 */     if (tableParams.ax == 1) {
/* 1986 */       h();
/*      */     }
/* 1988 */     if (this.t == null) {
/* 1989 */       e();
/* 1990 */       this.c.a(this.j, 0.0D);
/*      */     } 
/* 1992 */     this.d.a((j)this.c);
/*      */ 
/*      */     
/* 1995 */     for (int i = 0; i < this.g.size(); i++) {
/* 1996 */       l captionBuilder = (l)this.g.get(i);
/* 1997 */       f captionBox = (f)captionBuilder.j();
/* 1998 */       this.d.a(captionBox);
/* 1999 */       captionBuilder.a(this.d);
/* 2000 */       this.d.e();
/*      */     } 
/*      */     
/* 2003 */     if (!a && this.c.h().b_().a() != 4) throw new AssertionError(); 
/* 2004 */     this.d.e();
/*      */   }
/*      */   
/*      */   public boolean c() {
/* 2008 */     return true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */