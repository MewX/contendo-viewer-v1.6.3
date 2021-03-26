/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import jp.cssj.homare.b.a.b.d;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.h;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.b.s;
/*      */ import jp.cssj.homare.b.a.b.t;
/*      */ import jp.cssj.homare.b.a.b.u;
/*      */ import jp.cssj.homare.b.a.b.v;
/*      */ import jp.cssj.homare.b.a.b.w;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.A;
/*      */ import jp.cssj.homare.b.a.c.D;
/*      */ import jp.cssj.homare.b.a.c.E;
/*      */ import jp.cssj.homare.b.a.c.F;
/*      */ import jp.cssj.homare.b.a.c.G;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.j;
/*      */ import jp.cssj.homare.b.a.c.s;
/*      */ import jp.cssj.homare.b.a.d;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.b.d;
/*      */ import jp.cssj.homare.b.b.f;
/*      */ import jp.cssj.homare.b.b.g;
/*      */ import jp.cssj.homare.b.e.c;
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
/*      */ public class m
/*      */   implements f, g
/*      */ {
/*      */   protected static class a
/*      */   {
/*      */     private final Object d;
/*      */     public final int a;
/*      */     public final int b;
/*      */     
/*      */     public a(l cellBuilder) {
/*   65 */       this.d = cellBuilder;
/*   66 */       s cellBox = (s)cellBuilder.j();
/*   67 */       this.a = (cellBox.u()).e;
/*   68 */       this.b = (cellBox.u()).c;
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
/*   98 */   private final List<d> f = new ArrayList<>();
/*   99 */   private final List<jp.cssj.homare.b.b.a> g = new ArrayList<>();
/*  100 */   private final List<jp.cssj.homare.b.b.a> h = new ArrayList<>();
/*  101 */   private w i = null;
/*  102 */   private w j = null;
/*  103 */   private v k = null;
/*  104 */   private final List<w> l = new ArrayList<>();
/*  105 */   private final Map<w, ArrayList<v>> m = new HashMap<>();
/*  106 */   private final Map<v, ArrayList<a>> n = new HashMap<>();
/*  107 */   private final Map<s, v.b> o = new HashMap<>();
/*  108 */   private final List<w> p = new ArrayList<>();
/*  109 */   private u q = null;
/*  110 */   private v r = null;
/*  111 */   private c s = null;
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
/*  131 */   private double B = 0.0D; private double C = 0.0D; private final boolean b; private final boolean c; private final d d; private final r e; private double[] t; private double[] u;
/*      */   
/*      */   public m(d layoutStack, r tableBox) {
/*  134 */     this.d = layoutStack;
/*  135 */     this.e = tableBox;
/*  136 */     G tableParams = tableBox.g();
/*  137 */     this.b = e.a(tableParams.D);
/*  138 */     this
/*      */       
/*  140 */       .c = (tableParams.ay == 1 && (this.b ? tableParams.X.b() : tableParams.X.a()) != 3);
/*      */   }
/*      */   private double[] v; private byte[] w; private static final byte x = 0; private static final byte y = 1; private static final byte z = 2; private static final byte A = 3;
/*      */   public final double g() {
/*  144 */     G tableParams = this.e.g();
/*  145 */     if (this.b) {
/*  146 */       if (tableParams.X.b() == 1) {
/*  147 */         return Math.max(this.B, tableParams.X.d());
/*      */       }
/*      */     }
/*  150 */     else if (tableParams.X.a() == 1) {
/*  151 */       return Math.max(this.B, tableParams.X.c());
/*      */     } 
/*      */     
/*  154 */     return this.B;
/*      */   }
/*      */   
/*      */   public final double t() {
/*  158 */     G tableParams = this.e.g();
/*  159 */     if (this.b) {
/*  160 */       if (tableParams.X.b() == 1) {
/*  161 */         return Math.max(this.C, tableParams.X.d());
/*      */       }
/*      */     }
/*  164 */     else if (tableParams.X.a() == 1) {
/*  165 */       return Math.max(this.C, tableParams.X.c());
/*      */     } 
/*      */     
/*  168 */     return this.C;
/*      */   }
/*      */   
/*      */   public final double u() {
/*  172 */     return 0.0D;
/*      */   }
/*      */   
/*      */   public final r a() {
/*  176 */     return this.e; } public final void a(d box) {
/*      */     t column;
/*      */     w rowGroup;
/*      */     u parentColumnGroup;
/*      */     v row;
/*      */     List<v> rows;
/*  182 */     box.a(this.e.g());
/*  183 */     switch (box.a()) {
/*      */       
/*      */       case 8:
/*      */       case 9:
/*  187 */         column = (t)box;
/*  188 */         if (this.f.isEmpty()) {
/*  189 */           if (this.q == null) {
/*  190 */             this.q = new u(new s(), new F());
/*  191 */             this.q.a(this.e.g());
/*      */           } 
/*  193 */           this.q.a(column);
/*      */           break;
/*      */         } 
/*  196 */         parentColumnGroup = (u)this.f.get(this.f.size() - 1);
/*  197 */         parentColumnGroup.a(column);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/*  203 */         rowGroup = (w)box;
/*  204 */         this.m.put(rowGroup, new ArrayList<>());
/*  205 */         switch ((rowGroup.g()).c) {
/*      */           case 1:
/*  207 */             this.i = rowGroup;
/*      */             break;
/*      */           case 3:
/*  210 */             this.j = rowGroup;
/*      */             break;
/*      */           case 2:
/*  213 */             this.l.add(rowGroup);
/*      */             break;
/*      */         } 
/*  216 */         throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/*  224 */         rowGroup = (w)this.f.get(this.f.size() - 1);
/*  225 */         row = (v)box;
/*  226 */         rows = this.m.get(rowGroup);
/*  227 */         rows.add(row);
/*  228 */         this.n.put(row, new ArrayList<>());
/*      */         break;
/*      */       
/*      */       default:
/*  232 */         throw new IllegalStateException();
/*      */     } 
/*  234 */     this.f.add(box);
/*      */   }
/*      */   
/*      */   public final void b() {
/*      */     v rowBox;
/*  239 */     d box = this.f.remove(this.f.size() - 1);
/*      */ 
/*      */     
/*  242 */     switch (box.a()) {
/*      */       case 8:
/*      */       case 9:
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/*  250 */         this.r = null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/*  256 */         rowBox = (v)box;
/*  257 */         a(rowBox);
/*  258 */         this.r = rowBox;
/*  259 */         if (this.k == null) {
/*  260 */           this.k = rowBox;
/*      */         }
/*      */     } 
/*      */ 
/*      */     
/*  265 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(v row) {
/*  270 */     if (this.r != null) {
/*      */       
/*  272 */       List<a> cells = this.n.get(row);
/*  273 */       List<?> upperCells = this.n.get(this.r);
/*  274 */       while (upperCells.size() > cells.size()) {
/*  275 */         a upperCell = (a)upperCells.get(cells.size());
/*  276 */         if (upperCell.a > 1)
/*  277 */           for (int colspan = upperCell.b; colspan >= 1; colspan--)
/*  278 */             cells.add(new a(upperCell.c(), upperCell.a - 1, colspan));  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public final jp.cssj.homare.b.b.a a(c box) {
/*      */     v rowBox;
/*      */     List<a> cells;
/*      */     a cell;
/*      */     int colspan;
/*  288 */     jp.cssj.homare.b.b.a builder = new l(this.d, box);
/*  289 */     switch (box.a()) {
/*      */       
/*      */       case 5:
/*  292 */         switch (((D)box.b_()).v)
/*      */         { case 1:
/*  294 */             this.g.add(builder);
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
/*  323 */             return builder;case 2: this.h.add(builder); return builder; }  throw new IllegalStateException();case 12: rowBox = (v)this.f.get(this.f.size() - 1); cells = this.n.get(rowBox); a(rowBox); cell = new a((l)builder); cells.add(cell); for (colspan = cell.b; colspan > 1; colspan--) cells.add(new a(cell.c(), cell.a, colspan));  return builder;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private c a(int columnCount, int headerRowCount, int bodyRowCount, int footerRowCount, List<List<?>> rowLists, List<List<?>> cellLists) {
/*  331 */     G params = this.e.g();
/*      */ 
/*      */     
/*  334 */     c borders = new c(columnCount, headerRowCount, bodyRowCount, footerRowCount);
/*      */     
/*  336 */     int rowCount = headerRowCount + bodyRowCount + footerRowCount;
/*      */     
/*  338 */     A border = params.S.c;
/*  339 */     if (this.b) {
/*      */       
/*  341 */       for (int col = 0; col < columnCount; col++) {
/*  342 */         borders.a(col, 0, false, border.b());
/*  343 */         borders.a(col, rowCount, true, border.d());
/*      */       } 
/*  345 */       for (int row = 0; row < rowCount; row++) {
/*  346 */         borders.a(row, 0, border.a());
/*  347 */         borders.a(row, columnCount, border.c());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  352 */       u columnGroup = this.q;
/*  353 */       if (columnGroup != null) {
/*  354 */         int k = 0;
/*  355 */         List<Object> stack = new ArrayList();
/*  356 */         int n = 0;
/*      */         while (true) {
/*  358 */           for (; n < columnGroup.h(); n++) {
/*  359 */             int colspan; t column = columnGroup.a(n);
/*  360 */             F colPos = column.g();
/*  361 */             s colParams = column.c();
/*      */             
/*  363 */             if (column.a() == 8 && ((u)column)
/*  364 */               .h() > 0) {
/*  365 */               colspan = ((u)column).h();
/*      */             } else {
/*  367 */               colspan = colPos.a;
/*      */             }  int i1;
/*  369 */             for (i1 = 0; i1 < colspan; i1++) {
/*  370 */               int jj = k + i1;
/*      */               
/*  372 */               borders.a(jj, 0, false, colParams.b.b());
/*      */               
/*  374 */               borders.a(jj, rowCount, true, colParams.b.d());
/*      */             } 
/*  376 */             for (i1 = 0; i1 < rowCount; i1++) {
/*      */               
/*  378 */               borders.a(i1, k, colParams.b.a());
/*      */               
/*  380 */               borders.a(i1, k + colspan, colParams.b.c());
/*      */             } 
/*  382 */             if (column.a() == 8 && ((u)column)
/*  383 */               .h() > 0) {
/*  384 */               stack.add(columnGroup);
/*  385 */               stack.add(d.a(n + 1));
/*  386 */               columnGroup = (u)column;
/*  387 */               n = 0;
/*      */               continue;
/*      */             } 
/*  390 */             k += colspan;
/*      */           } 
/*      */           
/*  393 */           if (stack.isEmpty()) {
/*      */             break;
/*      */           }
/*  396 */           n = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  397 */           columnGroup = (u)stack.remove(stack.size() - 1);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  404 */       int j = 0; int i;
/*  405 */       for (i = 0; i < this.p.size(); i++) {
/*  406 */         w rowGroup = this.p.get(i);
/*  407 */         s rowGroupParams = rowGroup.c();
/*  408 */         List<?> rows = rowLists.get(i);
/*  409 */         int rowspan = rows.size(); int k;
/*  410 */         for (k = 0; k < columnCount; k++) {
/*      */           
/*  412 */           borders.a(k, j, false, rowGroupParams.b.b());
/*      */           
/*  414 */           borders.a(k, j + rowspan, true, rowGroupParams.b.d());
/*      */         } 
/*  416 */         for (k = 0; k < rowspan; k++) {
/*  417 */           int jj = j + k;
/*      */           
/*  419 */           borders.a(jj, 0, rowGroupParams.b.a());
/*      */           
/*  421 */           borders.a(jj, columnCount, rowGroupParams.b.c());
/*      */           
/*  423 */           s rowParams = ((v)rows.get(k)).c();
/*      */           
/*  425 */           borders.a(jj, 0, rowParams.b.a());
/*      */           
/*  427 */           borders.a(jj, columnCount, rowParams.b.c());
/*      */           
/*  429 */           List<?> cells = cellLists.get(j + k);
/*  430 */           for (int n = 0; n < cells.size(); n++) {
/*  431 */             a cell = (a)cells.get(n);
/*  432 */             E cellPos = cell.c().u();
/*      */             
/*  434 */             if (cell.a == cellPos.e) {
/*  435 */               borders.a(n, jj, false, rowParams.b.b());
/*      */             }
/*      */             
/*  438 */             if (cell.a == 1) {
/*  439 */               borders.a(n, jj + 1, true, rowParams.b.d());
/*      */             }
/*      */           } 
/*      */         } 
/*  443 */         j += rowspan;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  449 */       j = 0;
/*  450 */       for (i = 0; i < this.p.size(); i++) {
/*  451 */         List<?> rows = rowLists.get(i);
/*  452 */         for (int k = 0; k < rows.size(); k++) {
/*  453 */           List<?> cells = cellLists.get(j);
/*  454 */           for (int n = 0; n < cells.size(); n++) {
/*  455 */             a cell = (a)cells.get(n);
/*  456 */             if (!cell.a()) {
/*      */ 
/*      */               
/*  459 */               i cellParams = cell.c().c_();
/*  460 */               E cellPos = cell.c().u();
/*  461 */               int bottom = j + cellPos.e;
/*  462 */               for (int i1 = 0; i1 < cellPos.c; i1++) {
/*  463 */                 int kk = n + i1;
/*  464 */                 if (kk >= columnCount) {
/*      */                   break;
/*      */                 }
/*      */                 
/*  468 */                 borders.a(kk, j, false, cellParams.S.c.b());
/*  469 */                 for (int l = 1; l < cellPos.e; l++) {
/*  470 */                   int ll = j + l;
/*  471 */                   if (ll > rowCount) {
/*      */                     break;
/*      */                   }
/*  474 */                   borders.a(kk, ll, false, j.k);
/*      */                 } 
/*      */                 
/*  477 */                 borders.a(kk, Math.min(rowCount, bottom), true, cellParams.S.c
/*  478 */                     .d());
/*      */               } 
/*  480 */               int right = n + cellPos.c;
/*  481 */               for (int i2 = 0; i2 < cellPos.e; i2++) {
/*  482 */                 int kk = j + i2;
/*  483 */                 if (kk >= rowCount) {
/*      */                   break;
/*      */                 }
/*      */                 
/*  487 */                 borders.a(kk, n, cellParams.S.c.a());
/*  488 */                 for (int l = 1; l < cellPos.c; l++) {
/*  489 */                   int ll = n + l;
/*  490 */                   borders.a(kk, ll, j.k);
/*      */                 } 
/*  492 */                 if (right <= columnCount)
/*      */                 {
/*  494 */                   borders.a(kk, right, cellParams.S.c.c());
/*      */                 }
/*      */               } 
/*  497 */               n = right - 1;
/*      */             } 
/*  499 */           }  j++;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  505 */       for (int col = 0; col < columnCount; col++) {
/*  506 */         borders.a(col, 0, false, border.a());
/*  507 */         borders.a(col, rowCount, true, border.c());
/*      */       } 
/*  509 */       for (int row = 0; row < rowCount; row++) {
/*  510 */         borders.a(row, 0, border.d());
/*  511 */         borders.a(row, columnCount, border.b());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  516 */       u columnGroup = this.q;
/*  517 */       if (columnGroup != null) {
/*  518 */         int k = 0;
/*  519 */         List<Object> stack = new ArrayList();
/*  520 */         int n = 0;
/*      */         while (true) {
/*  522 */           for (; n < columnGroup.h(); n++) {
/*  523 */             int colspan; t column = columnGroup.a(n);
/*  524 */             F colPos = column.g();
/*  525 */             s colParams = column.c();
/*      */             
/*  527 */             if (column.a() == 8 && ((u)column)
/*  528 */               .h() > 0) {
/*  529 */               colspan = ((u)column).h();
/*      */             } else {
/*  531 */               colspan = colPos.a;
/*      */             }  int i1;
/*  533 */             for (i1 = 0; i1 < colspan; i1++) {
/*  534 */               int jj = k + i1;
/*      */               
/*  536 */               borders.a(jj, 0, false, colParams.b.a());
/*      */               
/*  538 */               borders.a(jj, rowCount, true, colParams.b.c());
/*      */             } 
/*  540 */             for (i1 = 0; i1 < rowCount; i1++) {
/*      */               
/*  542 */               borders.a(i1, k, colParams.b.d());
/*      */               
/*  544 */               borders.a(i1, k + colspan, colParams.b.b());
/*      */             } 
/*  546 */             if (column.a() == 8 && ((u)column)
/*  547 */               .h() > 0) {
/*  548 */               stack.add(columnGroup);
/*  549 */               stack.add(d.a(n + 1));
/*  550 */               columnGroup = (u)column;
/*  551 */               n = 0;
/*      */               continue;
/*      */             } 
/*  554 */             k += colspan;
/*      */           } 
/*      */           
/*  557 */           if (stack.isEmpty()) {
/*      */             break;
/*      */           }
/*  560 */           n = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  561 */           columnGroup = (u)stack.remove(stack.size() - 1);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  568 */       int j = 0; int i;
/*  569 */       for (i = 0; i < this.p.size(); i++) {
/*  570 */         w rowGroup = this.p.get(i);
/*  571 */         s rowGroupParams = rowGroup.c();
/*  572 */         List<?> rows = rowLists.get(i);
/*  573 */         int rowspan = rows.size(); int k;
/*  574 */         for (k = 0; k < columnCount; k++) {
/*      */           
/*  576 */           borders.a(k, j, false, rowGroupParams.b.a());
/*      */           
/*  578 */           borders.a(k, j + rowspan, true, rowGroupParams.b.c());
/*      */         } 
/*  580 */         for (k = 0; k < rowspan; k++) {
/*  581 */           int jj = j + k;
/*      */           
/*  583 */           borders.a(jj, 0, rowGroupParams.b.d());
/*      */           
/*  585 */           borders.a(jj, columnCount, rowGroupParams.b.b());
/*      */           
/*  587 */           s rowParams = ((v)rows.get(k)).c();
/*      */           
/*  589 */           borders.a(jj, 0, rowParams.b.d());
/*      */           
/*  591 */           borders.a(jj, columnCount, rowParams.b.b());
/*      */           
/*  593 */           List<?> cells = cellLists.get(j + k);
/*  594 */           for (int n = 0; n < cells.size(); n++) {
/*  595 */             a cell = (a)cells.get(n);
/*  596 */             E cellPos = cell.c().u();
/*      */             
/*  598 */             if (cell.a == cellPos.e) {
/*  599 */               borders.a(n, jj, false, rowParams.b.a());
/*      */             }
/*      */             
/*  602 */             if (cell.a == 1) {
/*  603 */               borders.a(n, jj + 1, true, rowParams.b.c());
/*      */             }
/*      */           } 
/*      */         } 
/*  607 */         j += rowspan;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  613 */       j = 0;
/*  614 */       for (i = 0; i < this.p.size(); i++) {
/*  615 */         List<?> rows = rowLists.get(i);
/*  616 */         for (int k = 0; k < rows.size(); k++) {
/*  617 */           List<?> cells = cellLists.get(j);
/*  618 */           for (int n = 0; n < cells.size(); n++) {
/*  619 */             a cell = (a)cells.get(n);
/*  620 */             if (!cell.a()) {
/*      */ 
/*      */               
/*  623 */               i cellParams = cell.c().c_();
/*  624 */               E cellPos = cell.c().u();
/*  625 */               int bottom = j + cellPos.e;
/*  626 */               for (int i1 = 0; i1 < cellPos.c; i1++) {
/*  627 */                 int kk = n + i1;
/*  628 */                 if (kk >= columnCount) {
/*      */                   break;
/*      */                 }
/*      */                 
/*  632 */                 borders.a(kk, j, false, cellParams.S.c.a());
/*  633 */                 for (int l = 1; l < cellPos.e; l++) {
/*  634 */                   int ll = j + l;
/*  635 */                   if (ll > rowCount) {
/*      */                     break;
/*      */                   }
/*  638 */                   borders.a(kk, ll, false, j.k);
/*      */                 } 
/*      */                 
/*  641 */                 borders.a(kk, Math.min(rowCount, bottom), true, cellParams.S.c
/*  642 */                     .c());
/*      */               } 
/*  644 */               int right = n + cellPos.c;
/*  645 */               for (int i2 = 0; i2 < cellPos.e; i2++) {
/*  646 */                 int kk = j + i2;
/*  647 */                 if (kk >= rowCount) {
/*      */                   break;
/*      */                 }
/*      */                 
/*  651 */                 borders.a(kk, n, cellParams.S.c.d());
/*  652 */                 for (int l = 1; l < cellPos.c; l++) {
/*  653 */                   int ll = n + l;
/*  654 */                   borders.a(kk, ll, j.k);
/*      */                 } 
/*  656 */                 if (right <= columnCount)
/*      */                 {
/*  658 */                   borders.a(kk, right, cellParams.S.c.b());
/*      */                 }
/*      */               } 
/*  661 */               n = right - 1;
/*      */             } 
/*  663 */           }  j++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  668 */     return borders;
/*      */   }
/*      */   
/*      */   public void d()
/*      */   {
/*      */     int columnCount;
/*      */     double tableFrame, lineBorderSpacing;
/*  675 */     G tableParams = this.e.g();
/*      */ 
/*      */     
/*  678 */     if (this.i != null) {
/*  679 */       this.p.add(this.i);
/*      */     }
/*  681 */     for (int i = 0; i < this.l.size(); i++) {
/*  682 */       this.p.add(this.l.get(i));
/*      */     }
/*  684 */     if (this.j != null) {
/*  685 */       this.p.add(this.j);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  691 */     if (this.q != null) {
/*  692 */       columnCount = 0;
/*  693 */       List<Object> stack = new ArrayList();
/*  694 */       u colgroup = this.q;
/*  695 */       this.e.a(colgroup);
/*  696 */       int i1 = 0;
/*      */       while (true) {
/*  698 */         for (; i1 < colgroup.h(); i1++) {
/*  699 */           t column = colgroup.a(i1);
/*  700 */           F colPos = column.g();
/*  701 */           if (column.a() == 8 && ((u)column)
/*  702 */             .h() > 0) {
/*  703 */             stack.add(colgroup);
/*  704 */             stack.add(d.a(i1 + 1));
/*  705 */             colgroup = (u)column;
/*  706 */             i1 = 0;
/*      */             continue;
/*      */           } 
/*  709 */           columnCount += colPos.a;
/*      */         } 
/*      */         
/*  712 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/*  715 */         i1 = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  716 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } else {
/*  719 */       columnCount = 0;
/*      */     } 
/*      */     
/*  722 */     int headerRowCount = 0, bodyRowCount = 0, footerRowCount = 0;
/*  723 */     List<List<?>> rowLists = new ArrayList<>();
/*  724 */     List<List<?>> cellLists = new ArrayList<>();
/*  725 */     for (int j = 0; j < this.p.size(); j++) {
/*  726 */       w rowGroup = this.p.get(j);
/*  727 */       List<?> rows = this.m.get(rowGroup);
/*  728 */       rowLists.add(rows);
/*  729 */       for (int i1 = 0; i1 < rows.size(); i1++) {
/*  730 */         v v1 = (v)rows.get(i1);
/*  731 */         List<?> cells = this.n.get(v1);
/*  732 */         cellLists.add(cells);
/*  733 */         columnCount = Math.max(columnCount, cells.size());
/*      */       } 
/*  735 */       switch ((rowGroup.g()).c) {
/*      */         case 1:
/*  737 */           headerRowCount += rows.size();
/*      */           break;
/*      */         case 2:
/*  740 */           bodyRowCount += rows.size();
/*      */           break;
/*      */         case 3:
/*  743 */           footerRowCount += rows.size();
/*      */           break;
/*      */         default:
/*  746 */           throw new IllegalStateException();
/*      */       } 
/*      */     } 
/*  749 */     int rowCount = headerRowCount + bodyRowCount + footerRowCount;
/*      */ 
/*      */     
/*  752 */     if (tableParams.ax == 1) {
/*      */       
/*  754 */       this.s = a(columnCount, headerRowCount, bodyRowCount, footerRowCount, rowLists, cellLists);
/*      */       
/*  756 */       this.e.a(this.s);
/*      */     } 
/*  758 */     this.e.a(this.d.k().h());
/*      */ 
/*      */     
/*  761 */     if (this.b) {
/*  762 */       tableFrame = this.e.i().e();
/*  763 */       lineBorderSpacing = tableParams.aw;
/*      */     } else {
/*  765 */       tableFrame = this.e.i().f();
/*  766 */       lineBorderSpacing = tableParams.av;
/*      */     } 
/*      */ 
/*      */     
/*  770 */     this.t = new double[columnCount];
/*  771 */     this.u = new double[columnCount];
/*  772 */     this.v = new double[columnCount];
/*  773 */     this.w = new byte[columnCount];
/*  774 */     Map<d, d> colspans = new HashMap<>();
/*  775 */     List<d> colspanList = new ArrayList<>();
/*      */     
/*  777 */     if (this.q != null) {
/*      */       
/*  779 */       int col = 0;
/*  780 */       List<Object> stack = new ArrayList();
/*  781 */       u colgroup = this.q;
/*  782 */       int i1 = 0;
/*      */       while (true) {
/*  784 */         for (; i1 < colgroup.h(); i1++) {
/*  785 */           int span; double fix, pct; int s; t column = colgroup.a(i1);
/*  786 */           F colPos = column.g();
/*  787 */           s colParams = column.c();
/*      */           
/*  789 */           if (column.a() == 8 && ((u)column)
/*  790 */             .h() > 0) {
/*  791 */             span = ((u)column).h();
/*      */           } else {
/*  793 */             span = colPos.a;
/*      */           } 
/*  795 */           switch (colParams.c.a()) {
/*      */             case 1:
/*  797 */               fix = colParams.c.b();
/*  798 */               fix += lineBorderSpacing;
/*  799 */               for (s = 0; s < span; s++) {
/*  800 */                 int i2 = col + s;
/*  801 */                 if (this.w[i2] <= 1) {
/*  802 */                   if (this.w[i2] != 1) {
/*  803 */                     this.w[i2] = 1;
/*  804 */                     this.u[i2] = 0.0D;
/*      */                   } 
/*  806 */                   this.u[i2] = Math.max(this.u[i2], fix);
/*      */                 } 
/*  808 */                 this.v[i2] = Math.max(this.v[i2], fix);
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 2:
/*  813 */               pct = colParams.c.b();
/*  814 */               for (s = 0; s < span; s++) {
/*  815 */                 int i2 = col + s;
/*  816 */                 if (this.w[i2] <= 2) {
/*  817 */                   if (this.w[i2] != 2) {
/*  818 */                     this.w[i2] = 2;
/*  819 */                     this.u[i2] = 0.0D;
/*      */                   } 
/*  821 */                   if (pct > this.u[i2]) {
/*  822 */                     double pctDiff = pct - this.u[i2];
/*  823 */                     this.u[i2] = this.u[i2] + pctDiff;
/*  824 */                     this.v[i2] = 1.0D;
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 3:
/*      */               break;
/*      */             default:
/*  833 */               throw new IllegalStateException();
/*      */           } 
/*  835 */           if (colParams.d.a() == 1) {
/*  836 */             double minSize = colParams.d.b();
/*  837 */             this.t[col] = Math.max(minSize, this.t[col]);
/*  838 */             this.v[col] = Math.max(minSize, this.v[col]);
/*      */           } 
/*  840 */           if (colParams.e.a() == 1) {
/*  841 */             double maxSize = colParams.e.b();
/*  842 */             this.t[col] = Math.min(maxSize, this.t[col]);
/*  843 */             if (this.w[col] == 1) {
/*  844 */               this.u[col] = Math.min(maxSize, this.u[col]);
/*  845 */               this.v[col] = Math.min(maxSize, this.v[col]);
/*      */             } 
/*      */           } 
/*      */           
/*  849 */           if (column.a() == 8 && ((u)column)
/*  850 */             .h() > 0) {
/*  851 */             stack.add(colgroup);
/*  852 */             stack.add(d.a(i1 + 1));
/*  853 */             colgroup = (u)column;
/*  854 */             i1 = 0;
/*      */             continue;
/*      */           } 
/*  857 */           col += span;
/*      */         } 
/*      */         
/*  860 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/*  863 */         i1 = ((Integer)stack.remove(stack.size() - 1)).intValue();
/*  864 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  869 */     int row = 0; int k;
/*  870 */     for (k = 0; k < this.p.size(); k++) {
/*  871 */       List<?> rows = this.m.get(this.p.get(k));
/*  872 */       for (int i1 = 0; i1 < rows.size(); i1++) {
/*  873 */         List<?> cells = this.n.get(rows.get(i1));
/*      */         
/*  875 */         for (int col = 0; col < cells.size(); col++) {
/*  876 */           a cell = (a)cells.get(col);
/*  877 */           if (!cell.a()) {
/*      */             double cellFrame;
/*      */             
/*  880 */             int span = cell.b;
/*  881 */             s cellBox = cell.c();
/*  882 */             i cellParams = cellBox.c_();
/*  883 */             E cellPos = cellBox.u();
/*  884 */             if (tableParams.ax == 0) {
/*      */               
/*  886 */               double top = tableParams.aw / 2.0D;
/*  887 */               double right = tableParams.av / 2.0D;
/*  888 */               double bottom = tableParams.aw / 2.0D;
/*  889 */               double left = tableParams.av / 2.0D;
/*  890 */               jp.cssj.homare.b.e.a cellSpacing = new jp.cssj.homare.b.e.a(top, right, bottom, left);
/*  891 */               cellBox.a(this.d.k().h(), this.e, cellSpacing);
/*      */             } else {
/*      */               jp.cssj.homare.b.e.a spacing;
/*  894 */               double pageFirst = 0.0D, lineEnd = 0.0D, pageLast = 0.0D, lineStart = 0.0D;
/*  895 */               int bottomIndex = row + cellPos.e;
/*  896 */               for (int i2 = 0; i2 < cellPos.c; i2++) {
/*  897 */                 int kk = col + i2;
/*  898 */                 if (kk >= columnCount) {
/*      */                   break;
/*      */                 }
/*  901 */                 pageFirst = Math.max(pageFirst, (this.s.a(kk, row)).m / 2.0D);
/*  902 */                 if (bottomIndex <= rowCount) {
/*  903 */                   pageLast = Math.max(pageLast, (this.s.a(kk, bottomIndex)).m / 2.0D);
/*      */                 }
/*      */               } 
/*  906 */               int rightIndex = col + cellPos.c;
/*  907 */               for (int i3 = 0; i3 < cellPos.e; i3++) {
/*  908 */                 int kk = row + i3;
/*  909 */                 if (kk >= rowCount) {
/*      */                   break;
/*      */                 }
/*  912 */                 lineStart = Math.max(lineStart, (this.s.b(kk, col)).m / 2.0D);
/*  913 */                 if (rightIndex <= columnCount) {
/*  914 */                   lineEnd = Math.max(lineEnd, (this.s.b(kk, rightIndex)).m / 2.0D);
/*      */                 }
/*      */               } 
/*      */               
/*  918 */               if (this.b) {
/*  919 */                 spacing = new jp.cssj.homare.b.e.a(lineStart, pageFirst, lineEnd, pageLast);
/*      */               } else {
/*  921 */                 spacing = new jp.cssj.homare.b.e.a(pageFirst, lineEnd, pageLast, lineStart);
/*      */               } 
/*  923 */               cellBox.a(this.d.k().h(), this.e, spacing);
/*      */             } 
/*      */ 
/*      */             
/*  927 */             if (this.b) {
/*  928 */               cellFrame = cellBox.m().e();
/*      */             } else {
/*  930 */               cellFrame = cellBox.m().f();
/*      */             } 
/*  932 */             l builder = cell.b();
/*      */ 
/*      */             
/*  935 */             double des = builder.u(), min = des;
/*      */             
/*  937 */             min = builder.g();
/*  938 */             des = builder.t();
/*      */             
/*  940 */             min += cellFrame;
/*  941 */             des += cellFrame;
/*  942 */             double spec = 0.0D;
/*  943 */             byte type = 0;
/*      */             
/*  945 */             if (this.b) {
/*  946 */               switch (cellParams.X.b()) {
/*      */                 case 1:
/*  948 */                   type = 1;
/*  949 */                   spec = cellParams.X.d() + cellFrame;
/*      */                   break;
/*      */                 case 2:
/*  952 */                   type = 2;
/*  953 */                   spec = cellParams.X.d();
/*      */                   break;
/*      */                 case 3:
/*  956 */                   spec = des;
/*      */                   break;
/*      */                 default:
/*  959 */                   throw new IllegalStateException();
/*      */               } 
/*  961 */               if (cellParams.Y.b() == 1) {
/*  962 */                 double minSize = cellParams.Y.d() + cellFrame;
/*  963 */                 min = Math.max(minSize, min);
/*  964 */                 des = Math.max(minSize, des);
/*      */               } 
/*  966 */               if (cellParams.Z.b() == 1) {
/*  967 */                 double maxSize = cellParams.Z.d() + cellFrame;
/*  968 */                 min = Math.min(maxSize, min);
/*  969 */                 des = Math.min(maxSize, des);
/*  970 */                 if (type == 1) {
/*  971 */                   spec = Math.min(maxSize, spec);
/*      */                 }
/*      */               } 
/*      */             } else {
/*  975 */               switch (cellParams.X.a()) {
/*      */                 case 1:
/*  977 */                   type = 1;
/*  978 */                   spec = cellParams.X.c() + cellFrame;
/*      */                   break;
/*      */                 case 2:
/*  981 */                   type = 2;
/*  982 */                   spec = cellParams.X.c();
/*      */                   break;
/*      */                 case 3:
/*  985 */                   spec = des;
/*      */                   break;
/*      */                 default:
/*  988 */                   throw new IllegalStateException();
/*      */               } 
/*  990 */               if (cellParams.Y.a() == 1) {
/*  991 */                 double minSize = cellParams.Y.c() + cellFrame;
/*  992 */                 min = Math.max(minSize, min);
/*  993 */                 des = Math.max(minSize, des);
/*      */               } 
/*  995 */               if (cellParams.Z.a() == 1) {
/*  996 */                 double maxSize = cellParams.Z.c() + cellFrame;
/*  997 */                 min = Math.min(maxSize, min);
/*  998 */                 des = Math.min(maxSize, des);
/*  999 */                 if (type == 1) {
/* 1000 */                   spec = Math.min(maxSize, spec);
/*      */                 }
/*      */               } 
/*      */             } 
/* 1004 */             if (cellParams.aa == 2 && type == 1) {
/* 1005 */               spec -= cellFrame;
/*      */             }
/*      */             
/* 1008 */             if (span == 1) {
/*      */               
/* 1010 */               this.t[col] = Math.max(this.t[col], min);
/* 1011 */               switch (type) {
/*      */                 case 0:
/* 1013 */                   if (this.w[col] == 0) {
/* 1014 */                     this.u[col] = Math.max(this.u[col], spec);
/*      */                   }
/*      */                   break;
/*      */                 case 1:
/* 1018 */                   if (this.w[col] <= 1) {
/* 1019 */                     if (this.w[col] != 1) {
/* 1020 */                       this.w[col] = 1;
/* 1021 */                       this.u[col] = 0.0D;
/*      */                     } 
/* 1023 */                     this.u[col] = Math.max(this.u[col], spec);
/* 1024 */                     this.v[col] = Math.max(this.t[col], this.u[col]); break;
/*      */                   } 
/* 1026 */                   des = Math.max(des, spec);
/*      */                   break;
/*      */                 
/*      */                 case 2:
/* 1030 */                   if (this.w[col] <= 2) {
/* 1031 */                     if (this.w[col] != 2) {
/* 1032 */                       this.w[col] = 2;
/* 1033 */                       this.u[col] = 0.0D;
/*      */                     } 
/* 1035 */                     if (spec > this.u[col]) {
/* 1036 */                       double pctDiff = spec - this.u[col];
/* 1037 */                       this.u[col] = this.u[col] + pctDiff;
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */                 default:
/* 1042 */                   throw new IllegalStateException();
/*      */               } 
/* 1044 */               if (this.w[col] != 1) {
/* 1045 */                 this.v[col] = Math.max(this.v[col], des);
/*      */               }
/*      */             } else {
/*      */               double pctDiff;
/* 1049 */               d key = new d(col, span);
/* 1050 */               d colspan = colspans.get(key);
/* 1051 */               if (colspan == null) {
/* 1052 */                 colspans.put(key, key);
/* 1053 */                 colspanList.add(key);
/* 1054 */                 colspan = key;
/*      */               } 
/* 1056 */               colspan.c = Math.max(colspan.c, min);
/* 1057 */               colspan.f = Math.max(colspan.f, des);
/* 1058 */               switch (type) {
/*      */                 case 0:
/*      */                   break;
/*      */                 case 1:
/* 1062 */                   if (e.a(colspan.e)) {
/* 1063 */                     colspan.e = spec; break;
/*      */                   } 
/* 1065 */                   colspan.e = Math.max(colspan.e, spec);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 2:
/* 1070 */                   if (e.a(colspan.d)) {
/* 1071 */                     pctDiff = spec;
/* 1072 */                     colspan.d = 0.0D;
/*      */                   } else {
/* 1074 */                     pctDiff = spec - colspan.d;
/*      */                   } 
/* 1076 */                   if (pctDiff > 0.0D) {
/* 1077 */                     colspan.d += pctDiff;
/*      */                   }
/*      */                   break;
/*      */                 default:
/* 1081 */                   throw new IllegalStateException();
/*      */               } 
/*      */             } 
/*      */           } 
/* 1085 */         }  row++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1090 */     Collections.sort(colspanList, d.g);
/* 1091 */     for (k = 0; k < colspanList.size(); k++) {
/* 1092 */       d colspan = colspanList.get(k);
/*      */       
/* 1094 */       boolean fix = !e.a(colspan.e);
/* 1095 */       double spec = fix ? colspan.e : colspan.f;
/* 1096 */       double desSum = 0.0D;
/* 1097 */       int noFixCount = 0, effCount = 0;
/* 1098 */       double noFixDesSum = 0.0D; int s;
/* 1099 */       for (s = 0; s < colspan.b; s++) {
/* 1100 */         int i1 = colspan.a + s;
/* 1101 */         double des = this.v[i1];
/* 1102 */         if (des != 0.0D) {
/*      */ 
/*      */           
/* 1105 */           effCount++;
/* 1106 */           desSum += des;
/* 1107 */           if (this.w[i1] != 1) {
/*      */ 
/*      */             
/* 1110 */             noFixCount++;
/* 1111 */             noFixDesSum += des;
/*      */           } 
/*      */         } 
/* 1114 */       }  if (effCount == 0) {
/* 1115 */         noFixDesSum = 0.0D;
/* 1116 */         for (s = 0; s < colspan.b; s++) {
/* 1117 */           int i1 = colspan.a + s;
/* 1118 */           double des = this.v[i1];
/* 1119 */           effCount++;
/* 1120 */           desSum += des;
/* 1121 */           if (this.w[i1] != 1) {
/*      */ 
/*      */             
/* 1124 */             noFixCount++;
/* 1125 */             noFixDesSum += des;
/*      */           } 
/*      */         } 
/* 1128 */       }  if (noFixCount != 0 || fix)
/*      */       {
/*      */ 
/*      */         
/* 1132 */         if (spec > desSum) {
/* 1133 */           if (effCount == 0) {
/* 1134 */             effCount = colspan.b;
/*      */           }
/* 1136 */           if (noFixCount == 0) {
/* 1137 */             noFixDesSum = desSum;
/*      */           }
/* 1139 */           double rem = spec - desSum;
/* 1140 */           for (int i1 = 0; i1 < colspan.b; i1++) {
/* 1141 */             int i2 = colspan.a + i1;
/* 1142 */             if (effCount == colspan.b || this.v[i2] != 0.0D)
/*      */             {
/*      */               
/* 1145 */               if (noFixCount == 0 || this.w[i2] != 1) {
/*      */                 double diff;
/*      */ 
/*      */                 
/* 1149 */                 if (noFixDesSum > 0.0D) {
/* 1150 */                   diff = rem * this.v[i2] / noFixDesSum;
/*      */                 } else {
/* 1152 */                   diff = rem / colspan.b;
/*      */                 } 
/* 1154 */                 this.v[i2] = this.v[i2] + diff;
/* 1155 */                 if (this.w[i2] != 2)
/*      */                 {
/*      */                   
/* 1158 */                   this.u[i2] = Math.max(this.t[i2], this.v[i2]); } 
/*      */               }  } 
/*      */           } 
/*      */         }  } 
/* 1162 */     }  for (k = 0; k < colspanList.size(); k++) {
/* 1163 */       d colspan = colspanList.get(k);
/*      */       
/* 1165 */       double minSum = 0.0D, desSum = 0.0D, diffSum = 0.0D;
/* 1166 */       for (int s = 0; s < colspan.b; s++) {
/* 1167 */         int i1 = colspan.a + s;
/* 1168 */         double min = this.t[i1];
/* 1169 */         double des = this.v[i1];
/* 1170 */         minSum += min;
/* 1171 */         desSum += des;
/* 1172 */         diffSum += des - min;
/*      */       } 
/* 1174 */       if (colspan.c > minSum) {
/* 1175 */         double rem = colspan.c - minSum;
/* 1176 */         if (diffSum > 0.0D) {
/* 1177 */           double dist = Math.min(rem, diffSum);
/* 1178 */           for (int i2 = 0; i2 < colspan.b; i2++) {
/* 1179 */             int i3 = colspan.a + i2;
/* 1180 */             double min = this.t[i3];
/* 1181 */             double des = this.v[i3];
/* 1182 */             double diff = dist * (des - min) / diffSum;
/* 1183 */             min = this.t[i3] = this.t[i3] + diff;
/* 1184 */             if (this.w[i3] == 0) {
/* 1185 */               this.u[i3] = Math.max(min, this.u[i3]);
/*      */             }
/*      */           } 
/* 1188 */           rem -= dist;
/*      */         } 
/* 1190 */         for (int i1 = 0; i1 < colspan.b; i1++) {
/* 1191 */           double diff; int i2 = colspan.a + i1;
/* 1192 */           double des = this.v[i2];
/*      */           
/* 1194 */           if (desSum > 0.0D) {
/* 1195 */             diff = rem * des / desSum;
/*      */           } else {
/* 1197 */             diff = rem / colspan.b;
/*      */           } 
/* 1199 */           double min = this.t[i2] = this.t[i2] + diff;
/* 1200 */           this.v[i2] = Math.max(min, this.v[i2]);
/* 1201 */           if (this.w[i2] == 0)
/*      */           {
/*      */             
/* 1204 */             this.u[i2] = Math.max(min, this.u[i2]); } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1208 */     for (k = 0; k < colspanList.size(); k++) {
/* 1209 */       d colspan = colspanList.get(k);
/*      */       
/* 1211 */       if (!e.a(colspan.d)) {
/*      */ 
/*      */         
/* 1214 */         double spec = colspan.d;
/* 1215 */         double pctSum = 0.0D;
/* 1216 */         int nonPctCount = 0;
/* 1217 */         double nonPctSum = 0.0D, desSum = 0.0D;
/* 1218 */         for (int s = 0; s < colspan.b; s++) {
/* 1219 */           int i1 = colspan.a + s;
/* 1220 */           double des = this.v[i1];
/* 1221 */           desSum += des;
/* 1222 */           if (this.w[i1] == 2) {
/* 1223 */             pctSum += this.u[i1];
/*      */           } else {
/*      */             
/* 1226 */             nonPctCount++;
/* 1227 */             nonPctSum += des;
/*      */           } 
/* 1229 */         }  if (spec > pctSum) {
/* 1230 */           double rem = spec - pctSum;
/* 1231 */           if (nonPctCount == 0) {
/* 1232 */             nonPctCount = colspan.b;
/* 1233 */             nonPctSum = desSum;
/*      */           } 
/* 1235 */           for (int i1 = 0; i1 < colspan.b; i1++) {
/* 1236 */             int i2 = colspan.a + i1;
/* 1237 */             if (nonPctCount == colspan.b || this.w[i2] != 2) {
/*      */               double diff;
/*      */ 
/*      */               
/* 1241 */               if (nonPctSum > 0.0D) {
/* 1242 */                 diff = rem * this.v[i2] / nonPctSum;
/*      */               } else {
/* 1244 */                 diff = rem / nonPctCount;
/*      */               } 
/* 1246 */               if (this.w[i2] == 2) {
/* 1247 */                 this.u[i2] = this.u[i2] + diff;
/*      */               } else {
/* 1249 */                 this.w[i2] = 2;
/* 1250 */                 this.u[i2] = diff;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1257 */     double pctRem = 1.0D;
/* 1258 */     for (int n = 0; n < columnCount; n++) {
/* 1259 */       this.B += this.t[n];
/* 1260 */       if (this.w[n] == 2) {
/* 1261 */         this.u[n] = Math.min(pctRem, this.u[n]);
/* 1262 */         pctRem -= this.u[n];
/*      */       } 
/* 1264 */       this.C += Math.max(this.t[n], this.v[n]);
/*      */     } 
/* 1266 */     this.B += tableFrame;
/* 1267 */     this.C += tableFrame; } public void a(a builder) { double tableSize, tableFrame, lineBorderSpacing; f flowBox; h inlineBox; d floatingBox; jp.cssj.homare.b.a.b.a absoluteBox;
/*      */     c cBox;
/*      */     double columnSizes[], specifiedPageSize;
/*      */     f f1;
/*      */     h h1;
/*      */     d d1;
/*      */     jp.cssj.homare.b.a.b.a a1;
/*      */     c c1;
/*      */     jp.cssj.homare.b.a.b.a a2;
/* 1276 */     G tableParams = this.e.g();
/* 1277 */     c containerBox = this.d.k();
/*      */ 
/*      */     
/* 1280 */     double lineSize = (e.a((containerBox.c_()).D) == e.a(tableParams.D)) ? containerBox.h() : (this.b ? this.d.o() : this.d.n());
/*      */ 
/*      */ 
/*      */     
/* 1284 */     if (this.b) {
/*      */       
/* 1286 */       tableSize = e.b(tableParams.X, lineSize);
/* 1287 */       double minSize = e.b(tableParams.Y, lineSize);
/* 1288 */       tableSize = Math.max(minSize, tableSize);
/* 1289 */       double maxSize = e.b(tableParams.Z, lineSize);
/* 1290 */       if (!e.a(maxSize) && !e.a(tableSize)) {
/* 1291 */         tableSize = Math.min(maxSize, tableSize);
/*      */       }
/* 1293 */       if (tableParams.X.b() != 3) {
/* 1294 */         tableSize += (this.e.i()).b.b();
/*      */       }
/* 1296 */       tableFrame = this.e.i().e();
/* 1297 */       lineBorderSpacing = tableParams.aw;
/*      */     } else {
/*      */       
/* 1300 */       tableSize = e.a(tableParams.X, lineSize);
/* 1301 */       double minSize = e.a(tableParams.Y, lineSize);
/* 1302 */       tableSize = Math.max(minSize, tableSize);
/* 1303 */       double maxSize = e.a(tableParams.Z, lineSize);
/* 1304 */       if (!e.a(maxSize) && !e.a(tableSize)) {
/* 1305 */         tableSize = Math.min(maxSize, tableSize);
/*      */       }
/* 1307 */       if (tableParams.X.a() != 3) {
/* 1308 */         tableSize += (this.e.i()).b.a();
/*      */       }
/* 1310 */       tableFrame = this.e.i().f();
/* 1311 */       lineBorderSpacing = tableParams.av;
/*      */     } 
/*      */ 
/*      */     
/* 1315 */     jp.cssj.homare.b.a.a blockBox = this.e.h();
/* 1316 */     a anonBuilder = null;
/* 1317 */     switch (blockBox.b_().a()) {
/*      */       case 4:
/* 1319 */         flowBox = (f)blockBox;
/* 1320 */         builder.a(flowBox);
/* 1321 */         anonBuilder = builder;
/*      */         break;
/*      */       
/*      */       case 5:
/* 1325 */         inlineBox = (h)blockBox;
/* 1326 */         anonBuilder = new a(this.d, (c)inlineBox);
/* 1327 */         inlineBox.a((d)builder, lineSize, lineSize, false);
/*      */         break;
/*      */       
/*      */       case 6:
/* 1331 */         floatingBox = (d)blockBox;
/* 1332 */         anonBuilder = new a(this.d, (c)floatingBox);
/* 1333 */         floatingBox.a((d)builder, lineSize, lineSize, false);
/*      */         break;
/*      */       
/*      */       case 7:
/* 1337 */         absoluteBox = (jp.cssj.homare.b.a.b.a)blockBox;
/* 1338 */         anonBuilder = new a(this.d, (c)absoluteBox);
/*      */         
/* 1340 */         if ((absoluteBox.c()).c != 1) {
/* 1341 */           cBox = builder.h().j();
/*      */         } else {
/* 1343 */           cBox = builder.l();
/*      */         } 
/* 1345 */         absoluteBox.a((jp.cssj.homare.b.a.m)cBox, lineSize, lineSize);
/*      */         break;
/*      */       
/*      */       default:
/* 1349 */         new IllegalStateException();
/*      */         break;
/*      */     } 
/* 1352 */     int columnCount = this.t.length;
/*      */     
/* 1354 */     if (this.c) {
/*      */       
/* 1356 */       if (e.a(tableSize)) {
/* 1357 */         tableSize = lineSize;
/*      */       }
/* 1359 */       tableSize -= tableFrame;
/* 1360 */       double refSize = tableSize;
/* 1361 */       if (tableParams.ax == 0)
/*      */       {
/* 1363 */         refSize -= columnCount * lineBorderSpacing;
/*      */       }
/* 1365 */       refSize = Math.max(0.0D, refSize);
/* 1366 */       f[] columnSizeList = new f[columnCount];
/* 1367 */       if (this.q != null) {
/* 1368 */         List<Object> stack = new ArrayList();
/* 1369 */         u colgroup = this.q;
/* 1370 */         this.e.a(colgroup);
/* 1371 */         int i4 = 0, i5 = 0;
/*      */         while (true) {
/* 1373 */           for (; i4 < colgroup.h(); i4++) {
/* 1374 */             int i6; double fix; f width, size; int i7; t column = colgroup.a(i4);
/* 1375 */             F colPos = column.g();
/* 1376 */             s colParams = column.c();
/* 1377 */             if (column.a() == 8 && ((u)column)
/* 1378 */               .h() > 0) {
/* 1379 */               stack.add(colgroup);
/* 1380 */               stack.add(d.a(i4 + 1));
/* 1381 */               colgroup = (u)column;
/* 1382 */               i4 = 0;
/*      */               continue;
/*      */             } 
/* 1385 */             switch (colParams.c.a()) {
/*      */               case 3:
/* 1387 */                 for (i6 = 0; i6 < colPos.a; i6++) {
/* 1388 */                   columnSizeList[i5++] = null;
/*      */                 }
/*      */                 break;
/*      */               case 1:
/* 1392 */                 fix = colParams.c.b();
/* 1393 */                 if (tableParams.ax == 0)
/*      */                 {
/* 1395 */                   fix += tableParams.av;
/*      */                 }
/* 1397 */                 width = new f(fix, false);
/* 1398 */                 for (i7 = 0; i7 < colPos.a; i7++) {
/* 1399 */                   columnSizeList[i5++] = width;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 2:
/* 1404 */                 fix = refSize * colParams.c.b();
/* 1405 */                 if (tableParams.ax == 0)
/*      */                 {
/* 1407 */                   fix += tableParams.av;
/*      */                 }
/* 1409 */                 size = new f(fix, true);
/* 1410 */                 for (i7 = 0; i7 < colPos.a; i7++) {
/* 1411 */                   columnSizeList[i5++] = size;
/*      */                 }
/*      */                 break;
/*      */ 
/*      */               
/*      */               default:
/* 1417 */                 throw new IllegalStateException();
/*      */             } 
/*      */           
/*      */           } 
/* 1421 */           if (stack.isEmpty()) {
/*      */             break;
/*      */           }
/* 1424 */           i4 = ((Integer)stack.remove(stack.size() - 1)).intValue();
/* 1425 */           colgroup = (u)stack.remove(stack.size() - 1);
/*      */         } 
/*      */       } 
/* 1428 */       List<?> cells = this.n.get(this.k);
/* 1429 */       columnSizes = new double[columnCount];
/* 1430 */       int autoCount = 0;
/* 1431 */       double sizeSum = 0.0D, percentSizeSum = 0.0D; int i3;
/* 1432 */       for (i3 = 0; i3 < columnCount; i3++) {
/*      */         
/* 1434 */         if (i3 >= cells.size()) {
/*      */           f size;
/* 1436 */           if (columnSizeList[i3] == null) {
/* 1437 */             size = null;
/* 1438 */             autoCount++;
/*      */           } else {
/* 1440 */             size = columnSizeList[i3];
/* 1441 */             sizeSum += size.b;
/* 1442 */             if (size.a) {
/* 1443 */               percentSizeSum += size.b;
/*      */             }
/*      */           } 
/* 1446 */           columnSizes[i3] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */         } else {
/*      */           f size;
/* 1449 */           a cell = (a)cells.get(i3);
/* 1450 */           s cellBox = cell.c();
/* 1451 */           i cellParams = cellBox.c_();
/* 1452 */           if (this.b) {
/* 1453 */             double fix; switch (cellParams.X.b()) {
/*      */               case 3:
/* 1455 */                 size = null;
/*      */                 break;
/*      */               case 1:
/* 1458 */                 fix = cellParams.X.d();
/* 1459 */                 if (cellParams.aa == 1) {
/* 1460 */                   fix += cellBox.m().e();
/*      */                 }
/* 1462 */                 fix /= cell.b;
/* 1463 */                 size = new f(fix, false);
/*      */                 break;
/*      */               
/*      */               case 2:
/* 1467 */                 fix = refSize * cellParams.X.d();
/* 1468 */                 if (cellParams.aa == 1) {
/* 1469 */                   fix += cellBox.m().e();
/*      */                 }
/* 1471 */                 fix /= cell.b;
/* 1472 */                 size = new f(fix, true);
/*      */                 break;
/*      */               
/*      */               default:
/* 1476 */                 throw new IllegalStateException();
/*      */             } 
/*      */           } else {
/* 1479 */             double fix; switch (cellParams.X.a()) {
/*      */               case 3:
/* 1481 */                 size = null;
/*      */                 break;
/*      */               case 1:
/* 1484 */                 fix = cellParams.X.c();
/* 1485 */                 if (cellParams.aa == 1) {
/* 1486 */                   fix += cellBox.m().f();
/*      */                 }
/* 1488 */                 fix /= cell.b;
/* 1489 */                 size = new f(fix, false);
/*      */                 break;
/*      */               
/*      */               case 2:
/* 1493 */                 fix = refSize * cellParams.X.c();
/* 1494 */                 if (cellParams.aa == 1) {
/* 1495 */                   fix += cellBox.m().f();
/*      */                 }
/* 1497 */                 fix /= cell.b;
/* 1498 */                 size = new f(fix, true);
/*      */                 break;
/*      */               
/*      */               default:
/* 1502 */                 throw new IllegalStateException();
/*      */             } 
/*      */           } 
/* 1505 */           if (columnSizeList[i3] == null) {
/* 1506 */             if (size == null) {
/* 1507 */               autoCount++;
/*      */             } else {
/*      */               
/* 1510 */               columnSizeList[i3] = size;
/* 1511 */               sizeSum += size.b;
/* 1512 */               if (size.a) {
/* 1513 */                 percentSizeSum += size.b;
/*      */               }
/*      */             } 
/* 1516 */             columnSizes[i3] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */           } else {
/* 1518 */             f columnSize = columnSizeList[i3];
/* 1519 */             columnSizes[i3] = columnSize.b;
/* 1520 */             sizeSum += columnSize.b;
/* 1521 */             if (columnSize.a) {
/* 1522 */               percentSizeSum += columnSize.b;
/*      */             }
/*      */           } 
/*      */           
/* 1526 */           for (int i4 = 1; i4 < cell.b; i4++) {
/* 1527 */             i3++;
/* 1528 */             if (columnSizeList[i3] == null) {
/* 1529 */               if (size == null) {
/* 1530 */                 autoCount++;
/*      */               } else {
/* 1532 */                 columnSizeList[i3] = size;
/* 1533 */                 sizeSum += size.b;
/* 1534 */                 if (size.a) {
/* 1535 */                   percentSizeSum += size.b;
/*      */                 }
/*      */               } 
/* 1538 */               columnSizes[i3] = (size == null) ? 1.722773839210782E308D : size.b;
/*      */             } else {
/* 1540 */               f columnSize = columnSizeList[i3];
/* 1541 */               columnSizes[i3] = columnSize.b;
/* 1542 */               sizeSum += columnSize.b;
/* 1543 */               if (columnSize.a) {
/* 1544 */                 percentSizeSum += columnSize.b;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1552 */       if (percentSizeSum > 0.0D && sizeSum > tableSize) {
/*      */         
/* 1554 */         double removeSize = Math.min(percentSizeSum, sizeSum - tableSize);
/* 1555 */         for (int i4 = 0; i4 < columnCount; i4++) {
/* 1556 */           f size = columnSizeList[i4];
/* 1557 */           if (size != null && size.a) {
/* 1558 */             if (!a && e.a(columnSizes[i4])) throw new AssertionError(); 
/* 1559 */             double sizeDiff = removeSize * size.b / percentSizeSum;
/* 1560 */             columnSizes[i4] = columnSizes[i4] - sizeDiff;
/* 1561 */             sizeSum -= sizeDiff;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1565 */       if (autoCount > 0) {
/*      */         double size;
/* 1567 */         if (tableSize > sizeSum) {
/* 1568 */           size = (tableSize - sizeSum) / autoCount;
/*      */         } else {
/* 1570 */           tableSize = sizeSum;
/* 1571 */           size = 0.0D;
/*      */         } 
/* 1573 */         for (int i4 = 0; i4 < columnCount; i4++) {
/* 1574 */           if (e.a(columnSizes[i4])) {
/* 1575 */             columnSizes[i4] = size;
/*      */           }
/*      */         } 
/* 1578 */       } else if (tableSize > sizeSum) {
/* 1579 */         double size = (tableSize - sizeSum) / columnCount;
/* 1580 */         for (int i4 = 0; i4 < columnCount; i4++) {
/* 1581 */           if (!a && e.a(columnSizes[i4])) throw new AssertionError(); 
/* 1582 */           columnSizes[i4] = columnSizes[i4] + size;
/*      */         } 
/*      */       } else {
/* 1585 */         tableSize = 0.0D;
/* 1586 */         for (i3 = 0; i3 < columnCount; i3++) {
/* 1587 */           if (!a && e.a(columnSizes[i3])) throw new AssertionError(); 
/* 1588 */           tableSize += columnSizes[i3];
/*      */         } 
/*      */       } 
/* 1591 */       tableSize += tableFrame;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1597 */       columnSizes = new double[columnCount];
/* 1598 */       if (columnCount > 0) {
/* 1599 */         double maxTableSize = blockBox.h();
/* 1600 */         if (e.a(tableSize)) {
/* 1601 */           tableSize = this.C;
/* 1602 */           if (tableSize < maxTableSize && columnCount > 1) {
/*      */             
/* 1604 */             int pctCount = 0, effColumnCount = 0;
/* 1605 */             double pctSum = 0.0D, noPctDesSum = 0.0D;
/* 1606 */             double d2 = tableSize - tableFrame;
/* 1607 */             for (int i4 = 0; i4 < columnCount; i4++) {
/* 1608 */               double des = this.v[i4];
/* 1609 */               if (this.w[i4] == 2 || des != 0.0D) {
/*      */ 
/*      */                 
/* 1612 */                 effColumnCount++;
/* 1613 */                 if (this.w[i4] != 2) {
/* 1614 */                   noPctDesSum += des;
/*      */                 } else {
/*      */                   
/* 1617 */                   pctCount++;
/* 1618 */                   double pct = this.u[i4];
/* 1619 */                   pctSum += pct;
/* 1620 */                   if (pct != 1.0D && pct != 0.0D) {
/* 1621 */                     d2 = Math.max(d2, des / pct);
/*      */                   } else {
/* 1623 */                     d2 = maxTableSize - tableFrame;
/*      */                   } 
/* 1625 */                   if (d2 >= maxTableSize - tableFrame)
/*      */                     break; 
/*      */                 } 
/*      */               } 
/* 1629 */             }  if (pctCount != 0 && pctCount != effColumnCount) {
/* 1630 */               if (pctSum != 1.0D && pctSum != 0.0D) {
/* 1631 */                 d2 = Math.max(d2, noPctDesSum / (1.0D - pctSum));
/* 1632 */               } else if (noPctDesSum > 0.0D) {
/* 1633 */                 d2 = maxTableSize - tableFrame;
/*      */               } 
/*      */             }
/* 1636 */             tableSize = d2 + tableFrame;
/*      */           } 
/*      */         } 
/*      */         
/* 1640 */         if (tableSize < this.B) {
/* 1641 */           tableSize = this.B;
/*      */         }
/* 1643 */         if (tableSize > maxTableSize) {
/* 1644 */           tableSize = maxTableSize;
/*      */         }
/* 1646 */         double innerSize = tableSize - tableFrame;
/*      */         
/* 1648 */         if (tableParams.ax == 0) {
/*      */           
/* 1650 */           double refSize = innerSize - columnCount * lineBorderSpacing;
/* 1651 */           for (int i4 = 0; i4 < columnCount; i4++) {
/* 1652 */             if (this.w[i4] == 2) {
/*      */ 
/*      */               
/* 1655 */               this.u[i4] = this.u[i4] * refSize;
/* 1656 */               if (tableParams.ax == 0)
/*      */               {
/* 1658 */                 this.u[i4] = this.u[i4] + lineBorderSpacing;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1663 */           for (int i4 = 0; i4 < columnCount; i4++) {
/* 1664 */             if (this.w[i4] == 2)
/*      */             {
/*      */               
/* 1667 */               this.u[i4] = this.u[i4] * innerSize;
/*      */             }
/*      */           } 
/*      */         } 
/* 1671 */         double sizeSum = 0.0D;
/*      */         int i3;
/* 1673 */         for (i3 = 0; i3 < columnCount; i3++) {
/* 1674 */           columnSizes[i3] = this.t[i3];
/* 1675 */           sizeSum += this.t[i3];
/*      */         } 
/* 1677 */         if (sizeSum > maxTableSize) {
/* 1678 */           for (i3 = 0; i3 < columnCount; i3++) {
/* 1679 */             columnSizes[i3] = this.t[i3] * (maxTableSize - tableFrame) / sizeSum;
/*      */           }
/* 1681 */           sizeSum = maxTableSize;
/*      */         } 
/*      */         
/*      */         byte type;
/*      */         
/* 1686 */         for (type = 2; type >= 0 && 
/* 1687 */           innerSize > sizeSum; type = (byte)(type - 1)) {
/*      */ 
/*      */           
/* 1690 */           double diffSum = 0.0D;
/* 1691 */           for (int i4 = 0; i4 < columnCount; i4++) {
/* 1692 */             if (this.w[i4] == type) {
/*      */ 
/*      */               
/* 1695 */               double size = this.u[i4];
/* 1696 */               if (!a && e.a(columnSizes[i4])) throw new AssertionError(); 
/* 1697 */               diffSum += Math.max(0.0D, size - columnSizes[i4]);
/*      */             } 
/* 1699 */           }  if (diffSum > 0.0D) {
/* 1700 */             double rem = innerSize - sizeSum;
/* 1701 */             if (diffSum <= rem)
/* 1702 */             { for (int i5 = 0; i5 < columnCount; i5++) {
/* 1703 */                 if (this.w[i5] == type) {
/*      */ 
/*      */                   
/* 1706 */                   double width = this.u[i5];
/* 1707 */                   if (!a && e.a(columnSizes[i5])) throw new AssertionError(); 
/* 1708 */                   double diff = Math.max(0.0D, width - columnSizes[i5]);
/* 1709 */                   columnSizes[i5] = columnSizes[i5] + diff;
/* 1710 */                   sizeSum += diff;
/*      */                 } 
/*      */               }  }
/* 1713 */             else { for (int i5 = 0; i5 < columnCount; i5++) {
/* 1714 */                 if (this.w[i5] == type) {
/*      */ 
/*      */                   
/* 1717 */                   double size = this.u[i5];
/* 1718 */                   if (!a && e.a(columnSizes[i5])) throw new AssertionError(); 
/* 1719 */                   double diff = Math.max(0.0D, size - columnSizes[i5]);
/* 1720 */                   diff = diff * rem / diffSum;
/* 1721 */                   columnSizes[i5] = columnSizes[i5] + diff;
/* 1722 */                   sizeSum += diff;
/*      */                 } 
/*      */               }  }
/*      */           
/*      */           } 
/*      */         } 
/*      */         
/* 1729 */         if (innerSize > sizeSum) {
/* 1730 */           double rem = innerSize - sizeSum;
/* 1731 */           int[] counts = new int[3];
/* 1732 */           double[] sums = new double[3];
/* 1733 */           for (int i4 = 0; i4 < columnCount; i4++) {
/* 1734 */             counts[this.w[i4]] = counts[this.w[i4]] + 1;
/* 1735 */             if (!a && e.a(columnSizes[i4])) throw new AssertionError(); 
/* 1736 */             sums[this.w[i4]] = sums[this.w[i4]] + columnSizes[i4];
/*      */           } 
/* 1738 */           for (byte b = 0; b < 3; ) {
/* 1739 */             byte b1; int count = counts[b];
/* 1740 */             double sum = sums[b];
/* 1741 */             if (count == 0) {
/*      */               b1 = (byte)(b + 1); continue;
/*      */             } 
/* 1744 */             for (int i5 = 0; i5 < columnCount; i5++) {
/* 1745 */               if (this.w[i5] == b1) {
/*      */                 double diff;
/*      */ 
/*      */                 
/* 1749 */                 if (!a && e.a(columnSizes[i5])) throw new AssertionError(); 
/* 1750 */                 if (sum > 0.0D) {
/* 1751 */                   diff = rem * columnSizes[i5] / sum;
/*      */                 } else {
/* 1753 */                   diff = rem / count;
/*      */                 } 
/* 1755 */                 columnSizes[i5] = columnSizes[i5] + diff;
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/*      */         } 
/* 1761 */       } else if (e.a(tableSize)) {
/* 1762 */         tableSize = 0.0D;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1768 */     if (this.b) {
/*      */       
/* 1770 */       switch (tableParams.X.a()) {
/*      */         case 1:
/* 1772 */           specifiedPageSize = tableParams.X.c() - this.e.i().f();
/*      */           break;
/*      */         case 2:
/* 1775 */           specifiedPageSize = e.a(tableParams.X, this.d
/* 1776 */               .n());
/*      */           break;
/*      */         case 3:
/* 1779 */           specifiedPageSize = 0.0D;
/*      */           break;
/*      */         default:
/* 1782 */           throw new IllegalStateException();
/*      */       } 
/*      */     
/*      */     } else {
/* 1786 */       switch (tableParams.X.b()) {
/*      */         case 1:
/* 1788 */           specifiedPageSize = tableParams.X.d() - this.e.i().e();
/*      */           break;
/*      */         case 2:
/* 1791 */           specifiedPageSize = e.b(tableParams.X, this.d
/* 1792 */               .o());
/*      */           break;
/*      */         case 3:
/* 1795 */           specifiedPageSize = 0.0D;
/*      */           break;
/*      */         default:
/* 1798 */           throw new IllegalStateException();
/*      */       } 
/*      */     } 
/* 1801 */     double tableInnerSize = tableSize - tableFrame;
/*      */     
/* 1803 */     if (!a && e.a(tableSize)) throw new AssertionError(); 
/* 1804 */     switch (blockBox.b_().a()) {
/*      */       case 4:
/* 1806 */         f1 = (f)blockBox;
/* 1807 */         f1.a((d)builder, tableSize, tableSize, true);
/*      */         break;
/*      */       
/*      */       case 5:
/* 1811 */         h1 = (h)blockBox;
/* 1812 */         h1.a((d)builder, tableSize, tableSize, true);
/*      */         break;
/*      */       
/*      */       case 6:
/* 1816 */         d1 = (d)blockBox;
/* 1817 */         d1.a((d)builder, tableSize, tableSize, true);
/*      */         break;
/*      */       
/*      */       case 7:
/* 1821 */         a1 = (jp.cssj.homare.b.a.b.a)blockBox;
/*      */         
/* 1823 */         if ((a1.c()).c != 1) {
/* 1824 */           c1 = builder.h().j();
/*      */         } else {
/* 1826 */           c1 = builder.l();
/*      */         } 
/* 1828 */         a1.a((jp.cssj.homare.b.a.m)c1, tableSize, tableSize);
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 1833 */         throw new IllegalStateException();
/*      */     } 
/*      */ 
/*      */     
/* 1837 */     for (int i = 0; i < this.g.size(); i++) {
/* 1838 */       l captionBuilder = (l)this.g.get(i);
/* 1839 */       f captionBox = (f)captionBuilder.j();
/* 1840 */       anonBuilder.a(captionBox);
/* 1841 */       captionBuilder.a(anonBuilder);
/* 1842 */       anonBuilder.e();
/*      */     } 
/*      */ 
/*      */     
/* 1846 */     int rowCount = 0;
/* 1847 */     for (int j = 0; j < this.p.size(); j++) {
/* 1848 */       List<?> rows = this.m.get(this.p.get(j));
/* 1849 */       rowCount += rows.size();
/*      */     } 
/*      */ 
/*      */     
/* 1853 */     double[] rowRatios = new double[rowCount];
/* 1854 */     double rowSizeSum = 0.0D;
/* 1855 */     int autoRowCount = 0;
/*      */     
/* 1857 */     int rowIndex = 0; int n;
/* 1858 */     for (n = 0; n < this.p.size(); n++) {
/* 1859 */       w rowGroupBox = this.p.get(n);
/* 1860 */       List<?> rows = this.m.get(rowGroupBox);
/*      */ 
/*      */       
/* 1863 */       Map<i, i> rowspans = new HashMap<>();
/* 1864 */       List<i> rowspanList = new ArrayList<>();
/* 1865 */       boolean[] noAdjRows = new boolean[rows.size()];
/* 1866 */       boolean[] autoRows = new boolean[rows.size()];
/*      */       
/*      */       int i3;
/* 1869 */       for (i3 = 0; i3 < rows.size(); i3++) {
/* 1870 */         double rowSize; v rowBox = (v)rows.get(i3);
/*      */ 
/*      */ 
/*      */         
/* 1874 */         s rowParams = rowBox.c();
/* 1875 */         switch (rowParams.c.a()) {
/*      */           case 1:
/* 1877 */             rowSize = rowParams.c.b();
/*      */             break;
/*      */           case 2:
/* 1880 */             rowRatios[rowIndex] = rowParams.c.b();
/* 1881 */             if (rowRatios[rowIndex] > 0.0D) {
/* 1882 */               rowSize = 0.0D;
/*      */               break;
/*      */             } 
/*      */           case 3:
/* 1886 */             autoRowCount++;
/* 1887 */             autoRows[i3] = true;
/* 1888 */             rowSize = 0.0D;
/*      */             break;
/*      */           default:
/* 1891 */             throw new IllegalStateException();
/*      */         } 
/* 1893 */         switch (rowParams.d.a()) {
/*      */           case 1:
/* 1895 */             rowSize = Math.max(rowParams.d.b(), rowSize);
/*      */             break;
/*      */           case 2:
/*      */           case 3:
/*      */             break;
/*      */           default:
/* 1901 */             throw new IllegalStateException();
/*      */         } 
/* 1903 */         switch (rowParams.e.a()) {
/*      */           case 1:
/* 1905 */             rowSize = Math.min(rowParams.e.b(), rowSize);
/*      */             break;
/*      */           case 2:
/*      */           case 3:
/*      */             break;
/*      */           default:
/* 1911 */             throw new IllegalStateException();
/*      */         } 
/*      */ 
/*      */         
/* 1915 */         List<?> cells = this.n.get(rowBox); int i4;
/* 1916 */         for (i4 = 0; i4 < cells.size(); i4++) {
/* 1917 */           a cell = (a)cells.get(i4);
/* 1918 */           int span = cell.b;
/* 1919 */           s cellBox = cell.c();
/* 1920 */           if (cell.a()) {
/* 1921 */             i4 += span - 1;
/* 1922 */             v.b rcell = this.o.get(cellBox);
/*      */             
/* 1924 */             this.o.put(cellBox, rowBox.a(rcell));
/*      */           } else {
/*      */             
/* 1927 */             i cellParams = cellBox.c_();
/* 1928 */             if (this.b) {
/* 1929 */               if (cellParams.X.a() == 2) {
/* 1930 */                 int rowspan = Math.min(rows.size() - i3, (cellBox.u()).e);
/* 1931 */                 for (int i5 = 0; i5 < rowspan; i5++) {
/* 1932 */                   rowRatios[rowIndex + i5] = Math.max(rowRatios[rowIndex + i5], cellParams.X
/* 1933 */                       .c() / rowspan);
/*      */                 }
/*      */               }
/*      */             
/* 1937 */             } else if (cellParams.X.b() == 2) {
/* 1938 */               int rowspan = Math.min(rows.size() - i3, (cellBox.u()).e);
/* 1939 */               for (int i5 = 0; i5 < rowspan; i5++) {
/* 1940 */                 rowRatios[rowIndex + i5] = Math.max(rowRatios[rowIndex + i5], cellParams.X
/* 1941 */                     .d() / rowspan);
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1947 */             double size = columnSizes[i4];
/* 1948 */             for (int l = 1; l < span; l++) {
/* 1949 */               size += columnSizes[++i4];
/* 1950 */               if (!a && e.a(columnSizes[i4])) throw new AssertionError(); 
/*      */             } 
/* 1952 */             if (this.b) {
/* 1953 */               cellBox.c(size);
/* 1954 */               if (!e.a(cellParams.D)) {
/* 1955 */                 cellBox.b(cell.b().t() + cellBox.m().f() + tableParams.av);
/*      */               }
/*      */             } else {
/*      */               
/* 1959 */               cellBox.b(size);
/* 1960 */               if (e.a(cellParams.D)) {
/* 1961 */                 cellBox.c(cell.b().t() + cellBox
/* 1962 */                     .m().e() + tableParams.aw);
/*      */               }
/*      */             } 
/* 1965 */             a cellBindBuilder = new a(this.d, (c)cellBox);
/* 1966 */             cell.b().a(cellBindBuilder);
/* 1967 */             cellBindBuilder.x();
/*      */             
/* 1969 */             this.o.put(cellBox, rowBox.a(cellBox));
/* 1970 */             int cellRowspan = Math.min(rows.size() - i3, cell.a);
/* 1971 */             if (cellRowspan <= 1) {
/*      */               
/* 1973 */               noAdjRows[i3] = true;
/*      */             } else {
/*      */               double cellSize;
/* 1976 */               i key = new i(i3, cellRowspan);
/* 1977 */               i rowspan = rowspans.get(key);
/* 1978 */               if (rowspan == null) {
/* 1979 */                 rowspan = key;
/* 1980 */                 rowspans.put(key, rowspan);
/* 1981 */                 rowspanList.add(rowspan);
/*      */               } 
/*      */               
/* 1984 */               if (this.b) {
/* 1985 */                 cellSize = cellBox.p();
/* 1986 */                 if (cellParams.X.a() == 1) {
/* 1987 */                   double width = cellParams.X.c();
/* 1988 */                   if (cellParams.aa == 1) {
/* 1989 */                     width += cellBox.m().f();
/*      */                   }
/* 1991 */                   cellSize = Math.max(cellSize, width);
/*      */                 } 
/*      */               } else {
/* 1994 */                 cellSize = cellBox.q();
/* 1995 */                 if (cellParams.X.b() == 1) {
/* 1996 */                   double height = cellParams.X.d();
/* 1997 */                   if (cellParams.aa == 1) {
/* 1998 */                     height += cellBox.m().e();
/*      */                   }
/* 2000 */                   cellSize = Math.max(cellSize, height);
/*      */                 } 
/*      */               } 
/* 2003 */               rowspan.c = Math.max(rowspan.c, cellSize);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 2008 */         for (i4 = 0; i4 < cells.size(); i4++) {
/* 2009 */           a cell = (a)cells.get(i4);
/* 2010 */           if (!cell.a()) {
/*      */ 
/*      */             
/* 2013 */             s cellBox = cell.c();
/*      */             
/* 2015 */             int cellRowspan = Math.min(rows.size() - i3, cell.a);
/* 2016 */             if (cellRowspan <= 1) {
/* 2017 */               double cellSize; i cellParams = cellBox.c_();
/*      */               
/* 2019 */               if (this.b) {
/* 2020 */                 cellSize = cellBox.p();
/* 2021 */                 if (cellParams.X.a() == 1) {
/* 2022 */                   double width = cellParams.X.c();
/* 2023 */                   cellSize = Math.max(cellSize, width);
/*      */                 } 
/*      */               } else {
/* 2026 */                 cellSize = cellBox.q();
/* 2027 */                 if (cellParams.X.b() == 1) {
/* 2028 */                   double height = cellParams.X.d();
/* 2029 */                   cellSize = Math.max(cellSize, height);
/*      */                 } 
/*      */               } 
/* 2032 */               rowSize = Math.max(rowSize, cellSize);
/*      */             } 
/*      */           } 
/*      */         } 
/* 2036 */         rowBox.b(rowSize);
/* 2037 */         rowIndex++;
/*      */       } 
/*      */ 
/*      */       
/* 2041 */       Collections.sort(rowspanList, i.d);
/* 2042 */       for (i3 = 0; i3 < rowspanList.size(); i3++) {
/* 2043 */         i rowspan = rowspanList.get(i3);
/* 2044 */         double minSum = ((v)rows.get(rowspan.a)).f();
/* 2045 */         for (int i4 = 1; i4 < rowspan.b; i4++) {
/* 2046 */           int kk = rowspan.a + i4;
/* 2047 */           if (kk < rows.size()) {
/* 2048 */             v rowBox = (v)rows.get(kk);
/* 2049 */             minSum += rowBox.f();
/*      */           } 
/*      */         } 
/* 2052 */         double minRem = rowspan.c - minSum;
/* 2053 */         if (minRem > 0.0D) {
/*      */           
/* 2055 */           double adjCount = 0.0D, autoCount = 0.0D; int i5;
/* 2056 */           for (i5 = 0; i5 < rowspan.b; i5++) {
/* 2057 */             int kk = rowspan.a + i5;
/* 2058 */             if (kk >= rows.size()) {
/*      */               break;
/*      */             }
/* 2061 */             if (!noAdjRows[kk] && autoRows[kk]) {
/* 2062 */               adjCount++;
/*      */             }
/* 2064 */             if (autoRows[kk]) {
/* 2065 */               autoCount++;
/*      */             }
/*      */             
/* 2068 */             double rowRatio = rowRatios[kk];
/* 2069 */             if (rowRatio > 0.0D) {
/* 2070 */               v rowBox = (v)rows.get(kk);
/* 2071 */               double diff = minRem * rowRatio;
/* 2072 */               minRem -= diff;
/* 2073 */               rowBox.b(rowBox.f() + diff);
/*      */             } 
/*      */           } 
/* 2076 */           if (adjCount > 0.0D && adjCount < rowspan.b) {
/*      */             
/* 2078 */             minRem /= adjCount;
/* 2079 */             for (i5 = 0; i5 < rowspan.b; i5++) {
/* 2080 */               int kk = rowspan.a + i5;
/* 2081 */               if (kk >= rows.size()) {
/*      */                 break;
/*      */               }
/* 2084 */               if (!noAdjRows[kk] && autoRows[kk]) {
/* 2085 */                 v rowBox = (v)rows.get(kk);
/* 2086 */                 double height = rowBox.f() + minRem;
/* 2087 */                 rowBox.b(height);
/*      */               } 
/*      */             } 
/* 2090 */           } else if (autoCount > 0.0D && autoCount < rowspan.b) {
/*      */             
/* 2092 */             minRem /= autoCount;
/* 2093 */             for (i5 = 0; i5 < rowspan.b; i5++) {
/* 2094 */               int kk = rowspan.a + i5;
/* 2095 */               if (kk >= rows.size()) {
/*      */                 break;
/*      */               }
/* 2098 */               if (autoRows[kk]) {
/* 2099 */                 v rowBox = (v)rows.get(kk);
/* 2100 */                 double height = rowBox.f() + minRem;
/* 2101 */                 rowBox.b(height);
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/* 2106 */             minRem /= rowspan.b;
/* 2107 */             for (i5 = 0; i5 < rowspan.b; i5++) {
/* 2108 */               int kk = rowspan.a + i5;
/* 2109 */               if (kk >= rows.size()) {
/*      */                 break;
/*      */               }
/* 2112 */               v rowBox = (v)rows.get(kk);
/* 2113 */               double height = rowBox.f() + minRem;
/* 2114 */               rowBox.b(height);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2120 */       for (i3 = 0; i3 < rows.size(); i3++) {
/* 2121 */         v rowBox = (v)rows.get(i3);
/* 2122 */         rowSizeSum += rowBox.f();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2129 */     double remainder = specifiedPageSize - rowSizeSum;
/* 2130 */     int i1 = 0;
/* 2131 */     for (int i2 = 0; remainder > 0.0D && i2 < this.p.size(); i2++) {
/* 2132 */       w rowGroupBox = this.p.get(i2);
/* 2133 */       List<?> rows = this.m.get(rowGroupBox);
/* 2134 */       for (int i3 = 0; remainder > 0.0D && i3 < rows.size(); i3++) {
/* 2135 */         v rowBox = (v)rows.get(i3);
/* 2136 */         double rowRatio = rowRatios[i1];
/* 2137 */         if (rowRatio > 0.0D) {
/* 2138 */           double rowHeight = rowBox.f();
/* 2139 */           double diff = Math.min(remainder, specifiedPageSize * rowRatio - rowHeight);
/* 2140 */           if (diff > 0.0D) {
/* 2141 */             remainder -= diff;
/* 2142 */             rowHeight += diff;
/* 2143 */             rowSizeSum += diff;
/* 2144 */             rowBox.b(rowHeight);
/*      */           } 
/*      */         } 
/* 2147 */         i1++;
/*      */       } 
/*      */     } 
/*      */     
/*      */     int k;
/*      */     
/* 2153 */     for (k = 0; k < this.p.size(); k++) {
/* 2154 */       w rowGroupBox = this.p.get(k);
/* 2155 */       s params = rowGroupBox.c();
/* 2156 */       if (params.c.a() == 1) {
/*      */ 
/*      */         
/* 2159 */         List<?> rows = this.m.get(rowGroupBox);
/* 2160 */         double groupRowHeightSum = 0.0D;
/* 2161 */         for (int i3 = 0; i3 < rows.size(); i3++) {
/* 2162 */           v rowBox = (v)rows.get(i3);
/* 2163 */           groupRowHeightSum += rowBox.f();
/*      */         } 
/* 2165 */         double groupSize = params.c.b();
/* 2166 */         if (groupSize > groupRowHeightSum) {
/* 2167 */           for (int i4 = 0; i4 < rows.size(); i4++) {
/* 2168 */             v rowBox = (v)rows.get(i4);
/* 2169 */             double rowSize = rowBox.f();
/* 2170 */             if (groupRowHeightSum == 0.0D) {
/* 2171 */               double diff = groupSize / rowCount;
/* 2172 */               rowSizeSum += diff;
/* 2173 */               rowBox.b(diff);
/*      */             } else {
/* 2175 */               double height = rowSize * groupSize / groupRowHeightSum;
/* 2176 */               rowSizeSum += height - rowBox.f();
/* 2177 */               rowBox.b(height);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2184 */     if (rowSizeSum < specifiedPageSize) {
/* 2185 */       if (autoRowCount > 0 && autoRowCount < rowCount)
/*      */       
/* 2187 */       { double d2 = specifiedPageSize - rowSizeSum; int i3;
/* 2188 */         for (i3 = 0; i3 < this.p.size(); i3++) {
/* 2189 */           w rowGroupBox = this.p.get(i3);
/* 2190 */           List<?> rows = this.m.get(rowGroupBox);
/* 2191 */           for (int i4 = 0; i4 < rows.size(); i4++) {
/* 2192 */             v rowBox = (v)rows.get(i4);
/* 2193 */             s params = rowBox.c();
/* 2194 */             if (params.c.a() != 3)
/*      */             {
/*      */               
/* 2197 */               rowSizeSum -= rowBox.f(); } 
/*      */           } 
/*      */         } 
/* 2200 */         for (i3 = 0; i3 < this.p.size(); i3++) {
/* 2201 */           w rowGroupBox = this.p.get(i3);
/* 2202 */           List<?> rows = this.m.get(rowGroupBox);
/* 2203 */           for (int i4 = 0; i4 < rows.size(); i4++) {
/* 2204 */             v rowBox = (v)rows.get(i4);
/* 2205 */             s params = rowBox.c();
/* 2206 */             if (params.c.a() == 3) {
/*      */ 
/*      */               
/* 2209 */               double rowSize = rowBox.f();
/* 2210 */               if (rowSizeSum <= 0.0D) {
/* 2211 */                 rowSize += d2 / autoRowCount;
/*      */               } else {
/* 2213 */                 rowSize += d2 * rowSize / rowSizeSum;
/*      */               } 
/* 2215 */               rowBox.b(rowSize);
/*      */             } 
/*      */           } 
/*      */         }  }
/* 2219 */       else { for (k = 0; k < this.p.size(); k++) {
/* 2220 */           w rowGroupBox = this.p.get(k);
/* 2221 */           List<?> rows = this.m.get(rowGroupBox);
/* 2222 */           for (int i3 = 0; i3 < rows.size(); i3++) {
/* 2223 */             v rowBox = (v)rows.get(i3);
/* 2224 */             double rowHeight = rowBox.f();
/* 2225 */             if (rowSizeSum <= 0.0D) {
/* 2226 */               rowBox.b(specifiedPageSize / rowCount);
/*      */             } else {
/* 2228 */               rowBox.b(specifiedPageSize * rowHeight / rowSizeSum);
/*      */             } 
/*      */           } 
/*      */         }  }
/*      */     
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2237 */     for (k = 0; k < this.p.size(); k++) {
/* 2238 */       w rowGroup = this.p.get(k);
/* 2239 */       List<?> rows = this.m.get(rowGroup);
/* 2240 */       for (int i3 = 0; i3 < rows.size(); i3++) {
/* 2241 */         v rowBox = (v)rows.get(i3);
/*      */         
/* 2243 */         rowBox.a(tableInnerSize);
/* 2244 */         rowGroup.a(rowBox);
/* 2245 */         List<?> cells = this.n.get(rowBox);
/*      */ 
/*      */         
/* 2248 */         double rowAscent = 0.0D; int i4;
/* 2249 */         for (i4 = 0; i4 < cells.size(); i4++) {
/* 2250 */           a cell = (a)cells.get(i4);
/* 2251 */           if (!cell.a()) {
/*      */ 
/*      */             
/* 2254 */             s cellBox = cell.c();
/* 2255 */             double firstAscent = cellBox.n();
/* 2256 */             if (!e.a(firstAscent) && firstAscent > rowAscent)
/* 2257 */               rowAscent = firstAscent; 
/*      */           } 
/*      */         } 
/* 2260 */         for (i4 = 0; i4 < cells.size(); i4++) {
/* 2261 */           a cell = (a)cells.get(i4);
/* 2262 */           if (!cell.a()) {
/*      */ 
/*      */             
/* 2265 */             s cellBox = cell.c();
/* 2266 */             double rowSize = rowBox.f();
/* 2267 */             int rowspan = Math.min(rows.size() - i3, (cellBox.u()).e);
/* 2268 */             for (int l = 1; l < rowspan; l++) {
/* 2269 */               int i5 = i3 + l;
/* 2270 */               v xrow = (v)rows.get(i5);
/* 2271 */               rowSize += xrow.f();
/*      */             } 
/* 2273 */             cellBox.d(rowAscent);
/* 2274 */             if (this.b) {
/* 2275 */               cellBox.b(rowSize);
/*      */             } else {
/* 2277 */               cellBox.c(rowSize);
/*      */             } 
/* 2279 */             cellBox.v();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2285 */     if (this.i != null) {
/* 2286 */       this.e.a(this.i);
/*      */     }
/* 2288 */     for (k = 0; k < this.l.size(); k++) {
/* 2289 */       this.e.c(this.l.get(k));
/*      */     }
/* 2291 */     if (this.j != null) {
/* 2292 */       this.e.b(this.j);
/*      */     }
/* 2294 */     if (rowCount == 0 || columnCount == 0) {
/* 2295 */       if (this.b) {
/* 2296 */         this.e.a(specifiedPageSize, tableSize - this.e.i().e());
/*      */       } else {
/* 2298 */         this.e.a(tableSize - this.e.i().f(), specifiedPageSize);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2303 */     if (this.q != null) {
/*      */       double pageSize;
/* 2305 */       if (this.b) {
/* 2306 */         pageSize = this.e.s();
/*      */       } else {
/* 2308 */         pageSize = this.e.t();
/*      */       } 
/* 2310 */       int col = 0;
/* 2311 */       List<Object> stack = new ArrayList();
/* 2312 */       u colgroup = this.q;
/* 2313 */       this.e.a(colgroup);
/* 2314 */       int i3 = 0;
/*      */       while (true) {
/* 2316 */         for (; i3 < colgroup.h(); i3++) {
/* 2317 */           int span; t column = colgroup.a(i3);
/* 2318 */           F colPos = column.g();
/*      */ 
/*      */           
/* 2321 */           if (column.a() == 8 && ((u)column)
/* 2322 */             .h() > 0) {
/* 2323 */             span = ((u)column).h();
/*      */           } else {
/* 2325 */             span = colPos.a;
/*      */           } 
/* 2327 */           double size = 0.0D;
/* 2328 */           for (int i4 = 0; i4 < span; i4++) {
/* 2329 */             size += columnSizes[col + i4];
/*      */           }
/* 2331 */           column.a(size);
/* 2332 */           column.b(pageSize);
/*      */           
/* 2334 */           if (column.a() == 8 && ((u)column)
/* 2335 */             .h() > 0) {
/* 2336 */             stack.add(colgroup);
/* 2337 */             stack.add(d.a(i3 + 1));
/* 2338 */             colgroup = (u)column;
/* 2339 */             i3 = 0;
/*      */             continue;
/*      */           } 
/* 2342 */           col += span;
/*      */         } 
/*      */         
/* 2345 */         if (stack.isEmpty()) {
/*      */           break;
/*      */         }
/* 2348 */         i3 = ((Integer)stack.remove(stack.size() - 1)).intValue();
/* 2349 */         colgroup = (u)stack.remove(stack.size() - 1);
/*      */       } 
/*      */     } 
/*      */     
/* 2353 */     if (tableParams.ax == 1) {
/*      */       
/* 2355 */       for (k = 0; k < columnSizes.length; k++) {
/* 2356 */         if (!a && e.a(columnSizes[k])) throw new AssertionError(); 
/* 2357 */         this.s.a(k, columnSizes[k]);
/*      */       } 
/* 2359 */       int row = 0;
/* 2360 */       for (n = 0; n < this.p.size(); n++) {
/* 2361 */         List<?> rows = this.m.get(this.p.get(n));
/* 2362 */         for (int i3 = 0; i3 < rows.size(); i3++) {
/* 2363 */           double rowHeight = ((v)rows.get(i3)).f();
/* 2364 */           this.s.b(row++, rowHeight);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2369 */     anonBuilder.a((j)this.e);
/*      */ 
/*      */     
/* 2372 */     for (k = 0; k < this.h.size(); k++) {
/* 2373 */       l captionBuilder = (l)this.h.get(k);
/* 2374 */       f captionBox = (f)captionBuilder.j();
/* 2375 */       anonBuilder.a(captionBox);
/* 2376 */       captionBuilder.a(anonBuilder);
/* 2377 */       anonBuilder.e();
/*      */     } 
/*      */     
/* 2380 */     switch (blockBox.b_().a()) {
/*      */       case 4:
/* 2382 */         builder.e();
/*      */       
/*      */       case 5:
/* 2385 */         anonBuilder.x();
/*      */ 
/*      */       
/*      */       case 6:
/* 2389 */         anonBuilder.x();
/* 2390 */         builder.a((j)blockBox);
/*      */       
/*      */       case 7:
/* 2393 */         anonBuilder.x();
/* 2394 */         a2 = (jp.cssj.homare.b.a.b.a)blockBox;
/* 2395 */         switch ((a2.c()).b) {
/*      */           case 1:
/* 2397 */             builder.a((j)a2);
/*      */           
/*      */           case 2:
/*      */             return;
/*      */         } 
/*      */         
/* 2403 */         throw new IllegalStateException();
/*      */     } 
/*      */ 
/*      */     
/* 2407 */     throw new IllegalStateException(); }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean c() {
/* 2412 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */