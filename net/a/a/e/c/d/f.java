/*      */ package net.a.a.e.c.d;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.geom.Dimension2D;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import net.a.a.c;
/*      */ import net.a.a.c.d;
/*      */ import net.a.a.e.d;
/*      */ import net.a.a.e.d.a.c;
/*      */ import net.a.a.e.d.c;
/*      */ import net.a.a.e.d.d;
/*      */ import net.a.a.g.d;
/*      */ import net.a.a.g.g;
/*      */ import net.a.a.g.i;
/*      */ import net.a.a.g.j;
/*      */ import org.apache.batik.dom.AbstractDocument;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.mathml.MathMLLabeledRowElement;
/*      */ import org.w3c.dom.mathml.MathMLNodeList;
/*      */ import org.w3c.dom.mathml.MathMLTableCellElement;
/*      */ import org.w3c.dom.mathml.MathMLTableElement;
/*      */ import org.w3c.dom.mathml.MathMLTableRowElement;
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
/*      */ public final class f
/*      */   extends a
/*      */   implements MathMLTableElement
/*      */ {
/*      */   public static final String r = "mtable";
/*      */   static final String s = "columnalign";
/*      */   static final String t = "rowalign";
/*      */   static final String u = "groupalign";
/*      */   private static final String w = "rowlines";
/*      */   private static final String x = "columnlines";
/*      */   private static final String y = "align";
/*      */   private static final String z = "alignmentscope";
/*      */   private static final String A = "columnwidth";
/*      */   private static final String B = "width";
/*      */   private static final String C = "rowspacing";
/*      */   private static final String D = "columnspacing";
/*      */   private static final String E = "frame";
/*      */   private static final String F = "framespacing";
/*      */   private static final String G = "equalrows";
/*      */   private static final String H = "equalcolumns";
/*      */   private static final String I = "displaystyle";
/*      */   private static final String J = "side";
/*      */   private static final String K = "minlabelspacing";
/*      */   private static final String L = "none";
/*      */   private static final String M = "dashed";
/*      */   private static final String N = "solid";
/*      */   private static final long O = 1L;
/*      */   private static final String P = "0.8em";
/*      */   private static final String Q = "1.0ex";
/*      */   private static final String R = "0.4em 0.5ex";
/*      */   private static final String S = "auto";
/*      */   private static final Log T;
/*      */   
/*      */   static {
/*  157 */     T = LogFactory.getLog(f.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public enum a
/*      */   {
/*  164 */     a,
/*      */     
/*  166 */     b,
/*      */     
/*  168 */     c;
/*      */ 
/*      */ 
/*      */     
/*      */     public static a[] a() {
/*      */       return (a[])d.clone();
/*      */     }
/*      */ 
/*      */     
/*      */     public static a b(String param1String) {
/*      */       a a1;
/*  179 */       if (param1String.equalsIgnoreCase("solid")) {
/*  180 */         a1 = b;
/*  181 */       } else if (param1String.equalsIgnoreCase("dashed")) {
/*  182 */         a1 = c;
/*      */       } else {
/*  184 */         a1 = a;
/*      */       } 
/*  186 */       return a1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static a a(String param1String) {
/*      */       return Enum.<a>valueOf(a.class, param1String);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class b
/*      */   {
/*      */     static final int a = 0;
/*      */     
/*      */     static final int b = 1;
/*      */     
/*      */     static final int c = 2;
/*      */     
/*      */     static final int d = 3;
/*      */     
/*      */     static final int e = 4;
/*      */     
/*      */     static final String f = "top";
/*      */     
/*      */     static final String g = "bottom";
/*      */     
/*      */     static final String h = "center";
/*      */     
/*      */     static final String i = "baseline";
/*      */     
/*      */     static final String j = "axis";
/*      */     
/*  220 */     static final b k = new b(3, 0);
/*      */     
/*      */     private static final String l = "Invalid vertical alignment value: ";
/*      */     
/*      */     private final int m;
/*      */     
/*      */     private final int n;
/*      */ 
/*      */     
/*      */     private b(int param1Int1, int param1Int2) {
/*  230 */       this.m = param1Int1;
/*  231 */       this.n = param1Int2;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static b a(String param1String) {
/*      */       byte b1;
/*      */       String str2;
/*  242 */       if (param1String == null || param1String.length() == 0) {
/*  243 */         return null;
/*      */       }
/*      */       
/*  246 */       int i = 0;
/*  247 */       String str1 = param1String.trim().toLowerCase(Locale.ENGLISH);
/*      */       
/*  249 */       if (str1.startsWith("top")) {
/*  250 */         b1 = 0;
/*  251 */         str2 = str1.substring("top".length()).trim();
/*  252 */       } else if (str1.startsWith("bottom")) {
/*  253 */         b1 = 1;
/*  254 */         str2 = str1.substring("bottom".length()).trim();
/*  255 */       } else if (str1.startsWith("center")) {
/*  256 */         b1 = 2;
/*  257 */         str2 = str1.substring("center".length()).trim();
/*  258 */       } else if (str1.startsWith("baseline")) {
/*  259 */         b1 = 3;
/*  260 */         str2 = str1.substring("baseline".length()).trim();
/*  261 */       } else if (str1.startsWith("axis")) {
/*  262 */         b1 = 4;
/*  263 */         str2 = str1.substring("axis".length()).trim();
/*      */       } else {
/*  265 */         f.a()
/*  266 */           .warn("Invalid vertical alignment value: " + param1String);
/*      */         
/*  268 */         b1 = 3;
/*  269 */         str2 = "0";
/*      */       } 
/*  271 */       if (str2.length() > 0) {
/*      */         try {
/*  273 */           i = Integer.parseInt(str2);
/*  274 */         } catch (NumberFormatException numberFormatException) {
/*  275 */           f.a()
/*  276 */             .warn("Invalid vertical alignment value: " + param1String);
/*      */         } 
/*      */       }
/*      */       
/*  280 */       return new b(b1, i);
/*      */     }
/*      */     
/*      */     public int a() {
/*  284 */       return this.m;
/*      */     }
/*      */     
/*      */     public int b() {
/*  288 */       return this.n;
/*      */     }
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
/*      */   public f(String paramString, AbstractDocument paramAbstractDocument) {
/*  301 */     super(paramString, paramAbstractDocument);
/*      */     
/*  303 */     a("align", "axis");
/*      */     
/*  305 */     a("rowalign", "baseline");
/*      */     
/*  307 */     a("columnalign", "center");
/*      */     
/*  309 */     a("groupalign", "{left}");
/*      */     
/*  311 */     a("alignmentscope", "true");
/*      */ 
/*      */     
/*  314 */     a("columnwidth", "auto");
/*      */     
/*  316 */     a("width", "auto");
/*  317 */     a("rowspacing", "1.0ex");
/*      */     
/*  319 */     a("columnspacing", "0.8em");
/*      */     
/*  321 */     a("rowlines", "none");
/*      */     
/*  323 */     a("columnlines", "none");
/*      */     
/*  325 */     a("frame", "none");
/*  326 */     a("framespacing", "0.4em 0.5ex");
/*      */     
/*  328 */     a("equalrows", "false");
/*  329 */     a("equalcolumns", "false");
/*  330 */     a("displaystyle", "false");
/*  331 */     a("side", "right");
/*  332 */     a("minlabelspacing", "0.8em");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node newNode() {
/*  339 */     return (Node)new f(this.nodeName, this.ownerDocument);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public c a(int paramInt, c paramc) {
/*  346 */     return (c)new net.a.a.c.b(
/*  347 */         b(paramc));
/*      */   }
/*      */   
/*      */   private float c(c paramc) {
/*  351 */     if (a.a.equals(i())) {
/*  352 */       return 0.0F;
/*      */     }
/*  354 */     String str = a(getFramespacing(), 0);
/*      */     
/*  356 */     return net.a.a.e.d.a.a.a(str, paramc, "pt");
/*      */   }
/*      */ 
/*      */   
/*      */   private float d(c paramc) {
/*  361 */     if (a.a.equals(i())) {
/*  362 */       return 0.0F;
/*      */     }
/*  364 */     String str = a(getFramespacing(), 1);
/*      */     
/*  366 */     return net.a.a.e.d.a.a.a(str, paramc, "pt");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRowlines() {
/*  372 */     return a("rowlines");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRowlines(String paramString) {
/*  377 */     setAttribute("rowlines", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getColumnlines() {
/*  382 */     return a("columnlines");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setColumnlines(String paramString) {
/*  387 */     setAttribute("columnlines", paramString);
/*      */   }
/*      */   
/*      */   private a g(int paramInt) {
/*  391 */     return a.b(a(
/*  392 */           getRowlines(), paramInt));
/*      */   }
/*      */   
/*      */   private a h(int paramInt) {
/*  396 */     return a.b(a(
/*  397 */           getColumnlines(), paramInt));
/*      */   }
/*      */   
/*      */   private a i() {
/*  401 */     return a.b(getFrame());
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
/*      */   private String a(String paramString, int paramInt) {
/*      */     String[] arrayOfString;
/*  419 */     if (paramString == null) {
/*  420 */       arrayOfString = new String[0];
/*      */     } else {
/*  422 */       arrayOfString = paramString.split("\\s");
/*      */     } 
/*  424 */     byte b = -1;
/*  425 */     String str = "";
/*  426 */     for (String str1 : arrayOfString) {
/*  427 */       if (str1.length() > 0) {
/*  428 */         b++;
/*  429 */         if (b == paramInt) {
/*  430 */           return str1;
/*      */         }
/*  432 */         str = str1;
/*      */       } 
/*      */     } 
/*  435 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getColumnwidth() {
/*  440 */     return a("columnwidth");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setColumnwidth(String paramString) {
/*  445 */     setAttribute("columnwidth", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getWidth() {
/*  450 */     return a("width");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWidth(String paramString) {
/*  455 */     setAttribute("width", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getAlign() {
/*  460 */     return a("align");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlign(String paramString) {
/*  465 */     setAttribute("align", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getAlignmentscope() {
/*  470 */     return a("alignmentscope");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlignmentscope(String paramString) {
/*  475 */     setAttribute("alignmentscope", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getRowspacing() {
/*  480 */     return a("rowspacing");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRowspacing(String paramString) {
/*  485 */     setAttribute("rowspacing", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getColumnspacing() {
/*  490 */     return a("columnspacing");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setColumnspacing(String paramString) {
/*  495 */     setAttribute("columnspacing", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getFrame() {
/*  500 */     return a("frame");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFrame(String paramString) {
/*  505 */     setAttribute("frame", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getFramespacing() {
/*  510 */     return a("framespacing");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFramespacing(String paramString) {
/*  515 */     setAttribute("framespacing", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getEqualrows() {
/*  520 */     return a("equalrows");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEqualrows(String paramString) {
/*  525 */     setAttribute("equalrows", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getEqualcolumns() {
/*  530 */     return a("equalcolumns");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEqualcolumns(String paramString) {
/*  535 */     setAttribute("equalcolumns", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDisplaystyle() {
/*  540 */     return a("displaystyle");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplaystyle(String paramString) {
/*  545 */     setAttribute("displaystyle", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getSide() {
/*  550 */     return a("side");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSide(String paramString) {
/*  555 */     setAttribute("side", paramString);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMinlabelspacing() {
/*  560 */     return a("minlabelspacing");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinlabelspacing(String paramString) {
/*  565 */     setAttribute("minlabelspacing", paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLNodeList getRows() {
/*  571 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement b(int paramInt) {
/*  577 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLLabeledRowElement c(int paramInt) {
/*  583 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement d(int paramInt) {
/*  589 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement a(int paramInt, MathMLTableRowElement paramMathMLTableRowElement) {
/*  596 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement b(int paramInt, MathMLTableRowElement paramMathMLTableRowElement) {
/*  603 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void e(int paramInt) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement f(int paramInt) {
/*  614 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteRow(long paramLong) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement getRow(long paramLong) {
/*  626 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLLabeledRowElement insertEmptyLabeledRow(long paramLong) {
/*  632 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement insertEmptyRow(long paramLong) {
/*  638 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement insertRow(long paramLong, MathMLTableRowElement paramMathMLTableRowElement) {
/*  645 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement removeRow(long paramLong) {
/*  651 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MathMLTableRowElement setRow(long paramLong, MathMLTableRowElement paramMathMLTableRowElement) {
/*  658 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(g paramg, d paramd, net.a.a.g.f paramf, c paramc) {
/*  669 */     Graphics2D graphics2D = paramg.d();
/*  670 */     c c1 = b(paramc);
/*  671 */     List list = b();
/*  672 */     d[] arrayOfD = new d[list.size()];
/*  673 */     i[] arrayOfI = new i[list.size()];
/*  674 */     float f1 = 0.0F;
/*  675 */     byte b = 0;
/*      */ 
/*      */     
/*  678 */     float f2 = d(c1);
/*  679 */     float f3 = f2;
/*  680 */     for (i i : list) {
/*  681 */       arrayOfI[b] = i;
/*  682 */       d d1 = paramg.a(i);
/*  683 */       f1 += d1.b(paramf);
/*  684 */       arrayOfD[b] = d1;
/*  685 */       b++;
/*  686 */       d1.a(0.0F, f1, paramf);
/*  687 */       f1 += d1.c(paramf);
/*  688 */       f3 = f1;
/*  689 */       f1 += net.a.a.e.d.a.a.a(a(
/*  690 */             getRowspacing(), b), c1, "pt");
/*      */     } 
/*  692 */     f3 += f2;
/*      */     
/*  694 */     float f4 = a(paramf, paramc, graphics2D, arrayOfD, b, f3);
/*      */ 
/*      */ 
/*      */     
/*  698 */     List[] arrayOfList = (List[])a(arrayOfI, b);
/*  699 */     a(paramg, (List<i>[])arrayOfList, arrayOfD, b, paramf);
/*      */     
/*  701 */     List<Float> list1 = a(paramg, paramf, b, (List<i>[])arrayOfList);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  706 */     float f5 = a(paramg, paramf, c1, b, (List<i>[])arrayOfList, list1);
/*      */ 
/*      */     
/*  709 */     a(paramf, arrayOfD, b, f5);
/*      */     
/*  711 */     a(paramd, arrayOfD, b, f5, paramf, c1);
/*  712 */     a(paramd, list1, f4, f3, c1);
/*  713 */     a(paramd, f5, f4, f3, c1);
/*      */     
/*  715 */     float f6 = c(c1);
/*  716 */     net.a.a.e.d.b b1 = new net.a.a.e.d.b(f6, f2);
/*      */     
/*  718 */     net.a.a.e.d.b b2 = new net.a.a.e.d.b(f6, f2);
/*      */     
/*  720 */     c.a(paramg, paramd, (Node)this, paramf, (Dimension2D)b1, (Dimension2D)b2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(d paramd, float paramFloat1, float paramFloat2, float paramFloat3, c paramc) {
/*  727 */     a a1 = i();
/*  728 */     boolean bool1 = a.b.equals(a1);
/*  729 */     boolean bool2 = a.c.equals(a1);
/*  730 */     if (bool2 || bool1) {
/*  731 */       float f1 = d.b(paramc);
/*  732 */       float f2 = f1 / 2.0F;
/*  733 */       Color color = (Color)paramc.a(d.i);
/*  734 */       List<j> list = paramd.e();
/*  735 */       float f3 = d(paramc);
/*      */       
/*  737 */       float f4 = f2;
/*  738 */       float f5 = paramFloat1 - f2;
/*  739 */       float f6 = paramFloat2 + f2 - f3;
/*  740 */       float f7 = paramFloat3 + paramFloat2 - f2;
/*  741 */       list.add(new j(f4, f6, f5, f6, f1, color, bool2));
/*      */       
/*  743 */       list.add(new j(f4, f7, f5, f7, f1, color, bool2));
/*      */       
/*  745 */       list.add(new j(f4, f6, f4, f7, f1, color, bool2));
/*      */       
/*  747 */       list.add(new j(f5, f6, f5, f7, f1, color, bool2));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(d paramd, d[] paramArrayOfd, int paramInt, float paramFloat, net.a.a.g.f paramf, c paramc) {
/*  755 */     float f1 = d.b(paramc);
/*  756 */     Color color = (Color)paramc.a(d.i);
/*      */     
/*  758 */     float f2 = c(paramc);
/*  759 */     for (byte b = 0; b < paramInt - 1; b++) {
/*  760 */       a a1 = g(b);
/*  761 */       boolean bool1 = a.b.equals(a1);
/*  762 */       boolean bool2 = a.c.equals(a1);
/*  763 */       if (bool2 || bool1) {
/*      */ 
/*      */ 
/*      */         
/*  767 */         float f3 = (paramArrayOfd[b].g(paramf) + paramArrayOfd[b].c(paramf) + paramArrayOfd[b + 1].g(paramf) - paramArrayOfd[b + 1].b(paramf)) / 2.0F;
/*  768 */         paramd.e().add(new j(f2, f3, paramFloat - f2, f3, f1, color, bool2));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(d paramd, List<Float> paramList, float paramFloat1, float paramFloat2, c paramc) {
/*  778 */     float f1 = d.b(paramc);
/*  779 */     Color color = (Color)paramc.a(d.i);
/*  780 */     float f2 = c(paramc);
/*      */     
/*  782 */     float f3 = d(paramc);
/*  783 */     float f4 = (paramList.size() - 1);
/*  784 */     for (byte b = 0; b < f4; b++) {
/*  785 */       a a1 = h(b);
/*  786 */       boolean bool1 = a.b.equals(a1);
/*  787 */       boolean bool2 = a.c.equals(a1);
/*  788 */       if (bool2 || bool1) {
/*  789 */         float f5 = a(paramc, b) / 2.0F;
/*  790 */         f2 += ((Float)paramList.get(b)).floatValue() + f5;
/*  791 */         paramd.e().add(new j(f2, paramFloat1, f2, paramFloat2 + paramFloat1 - f3, f1, color, bool2));
/*      */ 
/*      */ 
/*      */         
/*  795 */         f2 += f5;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(g paramg, List<i>[] paramArrayOfList, d[] paramArrayOfd, int paramInt, net.a.a.g.f paramf) {
/*  803 */     for (byte b = 0; b < paramInt; b++) {
/*  804 */       float f1 = paramArrayOfd[b].b(paramf);
/*  805 */       float f2 = paramArrayOfd[b].c(paramf);
/*  806 */       for (i i : paramArrayOfList[b]) {
/*  807 */         float f3; d d1 = paramg.a(i);
/*      */         
/*  809 */         b b1 = b((d)i, b);
/*      */         
/*  811 */         if (b1.a() == 0) {
/*  812 */           f3 = -f1 + d1.b(paramf);
/*  813 */         } else if (b1.a() == 1) {
/*      */           
/*  815 */           f3 = f2 - d1.c(paramf);
/*  816 */         } else if (b1.a() == 2) {
/*      */ 
/*      */           
/*  819 */           f3 = (-f1 + f2 + d1.b(paramf) - d1.c(paramf)) / 2.0F;
/*  820 */         } else if (b1.a() == 4) {
/*      */ 
/*      */ 
/*      */           
/*  824 */           f3 = (-f1 + f2 + d1.b(paramf) - d1.c(paramf)) / 2.0F;
/*      */         } else {
/*      */           
/*  827 */           f3 = 0.0F;
/*      */         } 
/*  829 */         d1.b(f3, paramf);
/*  830 */         d1.c(f1 + f3);
/*  831 */         d1.b(f2 - f3);
/*      */       } 
/*      */     } 
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
/*      */   private float a(net.a.a.g.f paramf, c paramc, Graphics2D paramGraphics2D, d[] paramArrayOfd, int paramInt, float paramFloat) {
/*  845 */     float f1 = -b(paramGraphics2D, paramc) - paramFloat / 2.0F;
/*      */ 
/*      */     
/*  848 */     for (byte b = 0; b < paramInt; b++) {
/*  849 */       paramArrayOfd[b].b(f1, paramf);
/*      */     }
/*  851 */     return f1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private List<i>[] a(i[] paramArrayOfi, int paramInt) {
/*  857 */     List[] arrayOfList = new List[paramInt];
/*  858 */     for (byte b = 0; b < paramInt; b++) {
/*  859 */       if (paramArrayOfi[b] instanceof MathMLTableRowElement) {
/*  860 */         arrayOfList[b] = paramArrayOfi[b].b();
/*      */       } else {
/*  862 */         arrayOfList[b] = new ArrayList(1);
/*  863 */         arrayOfList[b].add(paramArrayOfi[b]);
/*      */       } 
/*      */     } 
/*  866 */     return (List<i>[])arrayOfList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private List<Float> a(g paramg, net.a.a.g.f paramf, int paramInt, List<i>[] paramArrayOfList) {
/*  872 */     ArrayList<Float> arrayList = new ArrayList();
/*  873 */     for (byte b = 0; b < paramInt; b++) {
/*  874 */       int i = paramArrayOfList[b].size() - arrayList.size();
/*  875 */       while (i > 0) {
/*  876 */         arrayList.add(Float.valueOf(0.0F));
/*  877 */         i--;
/*      */       } 
/*  879 */       byte b1 = 0;
/*  880 */       for (i i1 : paramArrayOfList[b]) {
/*  881 */         float f1 = Math.max(((Float)arrayList.get(b1)).floatValue(), paramg
/*  882 */             .a(i1).d(paramf));
/*  883 */         arrayList.set(b1, Float.valueOf(f1));
/*  884 */         b1++;
/*      */       } 
/*      */     } 
/*  887 */     if (Boolean.parseBoolean(getEqualcolumns())) {
/*  888 */       a(arrayList);
/*      */     }
/*  890 */     return arrayList;
/*      */   }
/*      */   
/*      */   private void a(List<Float> paramList) {
/*  894 */     float f1 = 0.0F;
/*  895 */     for (Float float_ : paramList) {
/*  896 */       f1 = Math.max(float_.floatValue(), f1);
/*      */     }
/*  898 */     int i = paramList.size();
/*  899 */     for (byte b = 0; b < i; b++) {
/*  900 */       paramList.set(b, Float.valueOf(f1));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(net.a.a.g.f paramf, d[] paramArrayOfd, int paramInt, float paramFloat) {
/*  906 */     for (byte b = 0; b < paramInt; b++) {
/*  907 */       paramArrayOfd[b].e(paramFloat, paramf);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float a(g paramg, net.a.a.g.f paramf, c paramc, int paramInt, List<i>[] paramArrayOfList, List<Float> paramList) {
/*  915 */     float f1 = c(paramc);
/*  916 */     float f2 = f1;
/*  917 */     for (byte b = 0; b < paramInt; b++) {
/*  918 */       float f3 = f1;
/*  919 */       byte b1 = 0;
/*  920 */       for (i i : paramArrayOfList[b]) {
/*  921 */         d d = paramg.a(i);
/*  922 */         c c1 = a((d)i, b1);
/*  923 */         float f4 = ((Float)paramList.get(b1)).floatValue();
/*  924 */         float f5 = c1.a(paramf, d, f4);
/*  925 */         d.a(f3 + f5, d.g(paramf), paramf);
/*      */         
/*  927 */         d.a(f4);
/*  928 */         f3 += f4;
/*  929 */         f2 = Math.max(f2, f3);
/*  930 */         f3 += a(paramc, b1);
/*  931 */         b1++;
/*      */       } 
/*      */     } 
/*  934 */     return f2 + f1;
/*      */   }
/*      */   
/*      */   private float a(c paramc, int paramInt) {
/*  938 */     return net.a.a.e.d.a.a.a(
/*  939 */         a(getColumnspacing(), paramInt), paramc, "pt");
/*      */   }
/*      */ 
/*      */   
/*      */   private c a(d paramd, int paramInt) {
/*      */     c c;
/*  945 */     if (!v && paramd == null) throw new AssertionError();
/*      */     
/*  947 */     if (paramd instanceof MathMLTableCellElement) {
/*  948 */       MathMLTableCellElement mathMLTableCellElement = (MathMLTableCellElement)paramd;
/*  949 */       String str = mathMLTableCellElement.getColumnalign();
/*  950 */       c c1 = c.a(str, null);
/*  951 */       if (c1 == null) {
/*  952 */         c = a(paramd.g(), paramInt);
/*      */       } else {
/*  954 */         c = c1;
/*      */       } 
/*  956 */     } else if (paramd instanceof MathMLTableRowElement) {
/*  957 */       MathMLTableRowElement mathMLTableRowElement = (MathMLTableRowElement)paramd;
/*  958 */       String str = mathMLTableRowElement.getColumnalign();
/*  959 */       if (str != null && str.length() > 0) {
/*  960 */         c = c.a(a(str, paramInt), c.b);
/*      */       } else {
/*      */         
/*  963 */         c = a(paramd.g(), paramInt);
/*      */       } 
/*  965 */     } else if (paramd instanceof MathMLTableElement) {
/*  966 */       MathMLTableElement mathMLTableElement = (MathMLTableElement)paramd;
/*  967 */       String str = mathMLTableElement.getColumnalign();
/*  968 */       if (str != null && str.length() > 0) {
/*  969 */         c = c.a(a(str, paramInt), c.b);
/*      */       } else {
/*      */         
/*  972 */         c = c.b;
/*      */       } 
/*      */     } else {
/*  975 */       c = a(paramd.g(), paramInt);
/*      */     } 
/*  977 */     return c;
/*      */   }
/*      */   private b b(d paramd, int paramInt) {
/*      */     b b;
/*  981 */     if (!v && paramd == null) throw new AssertionError();
/*      */     
/*  983 */     if (paramd instanceof MathMLTableCellElement) {
/*  984 */       MathMLTableCellElement mathMLTableCellElement = (MathMLTableCellElement)paramd;
/*  985 */       String str = mathMLTableCellElement.getRowalign();
/*  986 */       b b1 = b.a(str);
/*  987 */       if (b1 == null) {
/*  988 */         b = b(paramd.g(), paramInt);
/*      */       } else {
/*  990 */         b = b1;
/*      */       } 
/*  992 */     } else if (paramd instanceof MathMLTableRowElement) {
/*  993 */       MathMLTableRowElement mathMLTableRowElement = (MathMLTableRowElement)paramd;
/*  994 */       String str = mathMLTableRowElement.getRowalign();
/*  995 */       b b1 = b.a(str);
/*  996 */       if (b1 == null) {
/*  997 */         b = b(paramd.g(), paramInt);
/*      */       } else {
/*  999 */         b = b1;
/*      */       } 
/* 1001 */     } else if (paramd instanceof MathMLTableElement) {
/* 1002 */       MathMLTableElement mathMLTableElement = (MathMLTableElement)paramd;
/* 1003 */       String str = mathMLTableElement.getRowalign();
/* 1004 */       if (str != null && str.length() > 0) {
/* 1005 */         b = b.a(a(str, paramInt));
/*      */       } else {
/*      */         
/* 1008 */         b = b.k;
/*      */       } 
/*      */     } else {
/* 1011 */       b = b(paramd.g(), paramInt);
/*      */     } 
/* 1013 */     return b;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/d/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */