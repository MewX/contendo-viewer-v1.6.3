/*     */ package net.a.a.e;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.a.a.c.f;
/*     */ import net.a.a.e.d.a.a;
/*     */ import net.a.a.e.d.a.d;
/*     */ import net.a.a.e.d.c;
/*     */ import net.a.a.e.d.c.f;
/*     */ import net.a.a.e.d.d;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.GenericElementNS;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.mathml.MathMLElement;
/*     */ import org.w3c.dom.mathml.MathMLMathElement;
/*     */ import org.w3c.dom.mathml.MathMLNodeList;
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
/*     */ public abstract class c
/*     */   extends GenericElementNS
/*     */   implements d
/*     */ {
/*     */   public static final String a = "mathvariant";
/*     */   public static final String b = "mathcolor";
/*     */   public static final String c = "mathsize";
/*     */   public static final String d = "fontfamily";
/*     */   public static final String e = "fontstyle";
/*     */   public static final String f = "fontweight";
/*     */   public static final String g = "fontsize";
/*     */   public static final String h = "color";
/*     */   public static final String i = "background";
/*     */   public static final String j = "class";
/*     */   public static final String k = "style";
/*     */   public static final String l = "id";
/*     */   public static final String m = "xlink:href";
/*     */   public static final String n = "xref";
/*     */   public static final String o = "mathbackground";
/*     */   public static final int p = 32;
/*     */   public static final String q = "http://www.w3.org/1998/Math/MathML";
/*     */   private static final float r = 0.38F;
/* 130 */   private static final Set<String> s = new HashSet<String>();
/*     */ 
/*     */ 
/*     */   
/*     */   private d t;
/*     */ 
/*     */   
/* 137 */   private final Map<String, String> u = new HashMap<String, String>();
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
/*     */   public c(String paramString, AbstractDocument paramAbstractDocument) {
/* 149 */     super("http://www.w3.org/1998/Math/MathML", paramString, paramAbstractDocument);
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
/*     */ 
/*     */   
/*     */   public c(String paramString1, String paramString2, AbstractDocument paramAbstractDocument) {
/* 164 */     super(paramString1, paramString2, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font a(net.a.a.c paramc) {
/*     */     byte b;
/* 176 */     String str = f();
/*     */     
/* 178 */     if (str.length() > 0) {
/* 179 */       b = str.charAt(0);
/*     */     } else {
/* 181 */       b = 65;
/*     */     } 
/* 183 */     return d().a(
/* 184 */         d.a(paramc), b, 
/* 185 */         b(paramc), true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public d d() {
/*     */     d d2;
/* 192 */     String str = a("mathvariant", false);
/*     */ 
/*     */     
/* 195 */     d d1 = g();
/* 196 */     while (str == null && d1 != null) {
/*     */       
/* 198 */       if (d1 instanceof c) {
/* 199 */         str = ((c)d1).a("mathvariant", false);
/*     */       }
/*     */       
/* 202 */       d1 = d1.g();
/*     */     } 
/* 204 */     if (str == null)
/*     */     {
/* 206 */       str = this.u.get("mathvariant");
/*     */     }
/*     */     
/* 209 */     if (str == null) {
/* 210 */       d2 = d.j;
/*     */     } else {
/* 212 */       d2 = d.a(str);
/* 213 */       if (d2 == null) {
/* 214 */         d2 = d.j;
/*     */       }
/*     */     } 
/* 217 */     return d2;
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
/*     */   public final void a(MathMLElement paramMathMLElement) {
/* 325 */     if (paramMathMLElement != null) {
/* 326 */       appendChild((Node)paramMathMLElement);
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
/*     */ 
/*     */   
/*     */   protected d a(int paramInt) {
/* 342 */     List list = c.a((Node)this);
/* 343 */     int i = 0;
/* 344 */     for (Node node : list) {
/* 345 */       if (node instanceof d) {
/* 346 */         if (i == paramInt) {
/* 347 */           return (d)node;
/*     */         }
/* 349 */         i++;
/*     */       } 
/*     */     } 
/* 352 */     for (; i < paramInt; i++) {
/* 353 */       appendChild(this.ownerDocument.createElement("mtext"));
/*     */     }
/*     */     
/* 356 */     d d1 = (d)this.ownerDocument.createElement("mtext");
/* 357 */     appendChild((Node)d1);
/* 358 */     return d1;
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
/*     */   
/*     */   protected void a(int paramInt, MathMLElement paramMathMLElement) {
/* 372 */     NodeList nodeList = getChildNodes();
/* 373 */     while (nodeList.getLength() < paramInt) {
/* 374 */       appendChild(getOwnerDocument().createTextNode(""));
/*     */     }
/* 376 */     if (nodeList.getLength() == paramInt) {
/* 377 */       a(paramMathMLElement);
/*     */     } else {
/* 379 */       replaceChild((Node)paramMathMLElement, nodeList.item(paramInt));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(d paramd) {
/* 385 */     NodeList nodeList = getChildNodes();
/* 386 */     for (byte b = 0; b < nodeList.getLength(); b++) {
/* 387 */       if (nodeList.item(b).equals(paramd)) {
/* 388 */         return b;
/*     */       }
/*     */     } 
/* 391 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int e() {
/* 397 */     List list = c.a((Node)this);
/* 398 */     byte b = 0;
/* 399 */     for (Node node : list) {
/* 400 */       if (node instanceof d) {
/* 401 */         b++;
/*     */       }
/*     */     } 
/* 404 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String f() {
/* 413 */     return f.a((Node)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(d paramd) {
/* 418 */     this.t = paramd;
/*     */   }
/*     */   private f a() {
/*     */     f f;
/* 422 */     Node node = getParentNode();
/*     */     
/* 424 */     if (node instanceof f) {
/* 425 */       f = (f)node;
/*     */     } else {
/* 427 */       f = null;
/*     */     } 
/* 429 */     if (f == null) {
/* 430 */       return this.t;
/*     */     }
/* 432 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d g() {
/* 439 */     f f = a();
/* 440 */     if (f instanceof d) {
/* 441 */       return (d)f;
/*     */     }
/* 443 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMathvariant(String paramString) {
/* 454 */     setAttribute("mathvariant", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMathvariant() {
/* 463 */     return a("mathvariant");
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
/*     */   
/*     */   public FontMetrics a(Graphics2D paramGraphics2D, net.a.a.c paramc) {
/* 477 */     return paramGraphics2D.getFontMetrics(a(paramc));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMathcolor(String paramString) {
/* 487 */     setAttribute("mathcolor", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMathcolor() {
/* 497 */     String str = a("mathcolor");
/* 498 */     if (str == null)
/*     */     {
/* 500 */       str = a("color");
/*     */     }
/* 502 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMathsize() {
/* 512 */     String str = a("mathsize");
/* 513 */     if (str == null)
/*     */     {
/* 515 */       str = a("fontsize");
/*     */     }
/* 517 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMathsize(String paramString) {
/* 528 */     setAttribute("mathsize", paramString);
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
/*     */   
/*     */   protected void a(String paramString1, String paramString2) {
/* 542 */     this.u.put(paramString1, paramString2);
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
/*     */   protected String a(String paramString) {
/* 555 */     return a(paramString, true);
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected String a(String paramString, boolean paramBoolean) {
/*     */     String str;
/* 572 */     Attr attr = getAttributeNodeNS("http://www.w3.org/1998/Math/MathML", paramString);
/*     */     
/* 574 */     if (attr == null) {
/* 575 */       attr = getAttributeNode(paramString);
/*     */     }
/* 577 */     if (attr == null) {
/* 578 */       if (paramBoolean) {
/* 579 */         str = b(paramString);
/*     */       } else {
/* 581 */         str = null;
/*     */       } 
/*     */     } else {
/* 584 */       str = attr.getValue().trim();
/*     */     } 
/* 586 */     return str;
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
/*     */   private String b(String paramString) {
/* 598 */     return this.u.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMathbackground() {
/* 609 */     String str = a("mathbackground");
/* 610 */     if (str == null)
/*     */     {
/* 612 */       str = a("background");
/*     */     }
/* 614 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMathbackground(String paramString) {
/* 624 */     setAttribute("mathbackground", paramString);
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
/*     */   
/*     */   public float b(Graphics2D paramGraphics2D, net.a.a.c paramc) {
/* 638 */     return a(paramGraphics2D, paramc).getAscent() * 0.38F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 644 */     return getAttribute("class");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClassName(String paramString) {
/* 649 */     setAttribute("class", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMathElementStyle() {
/* 654 */     return getAttribute("style");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMathElementStyle(String paramString) {
/* 659 */     setAttribute("style", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 665 */     return getAttribute("id");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setId(String paramString) {
/* 670 */     setAttribute("id", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXref() {
/* 675 */     return getAttribute("xref");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXref(String paramString) {
/* 680 */     setAttribute("xref", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHref() {
/* 685 */     return getAttribute("xlink:href");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHref(String paramString) {
/* 690 */     setAttribute("xlink:href", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLMathElement getOwnerMathElement() {
/* 695 */     d d1 = g();
/* 696 */     while (d1 != null) {
/* 697 */       if (d1 instanceof MathMLMathElement) {
/* 698 */         return (MathMLMathElement)d1;
/*     */       }
/* 700 */       d1 = d1.g();
/*     */     } 
/* 702 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(d paramd) {
/* 707 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(d paramd, net.a.a.c paramc) {
/* 713 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLNodeList getContents() {
/* 722 */     return (MathMLNodeList)getChildNodes();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public net.a.a.c a(int paramInt, net.a.a.c paramc) {
/* 728 */     return b(paramc);
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
/*     */ 
/*     */   
/*     */   public net.a.a.c b(net.a.a.c paramc) {
/* 743 */     return c(paramc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private net.a.a.c c(net.a.a.c paramc) {
/*     */     f f;
/*     */     Color color;
/* 755 */     net.a.a.c c1 = paramc;
/*     */ 
/*     */ 
/*     */     
/* 759 */     String str1 = getMathsize();
/*     */ 
/*     */     
/* 762 */     String str2 = getMathcolor();
/* 763 */     if (str2 == null) {
/* 764 */       color = null;
/*     */     } else {
/* 766 */       color = a.a(str2, Color.BLACK);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 773 */     if (str1 != null || color != null) {
/* 774 */       f = new f(paramc, str1, color);
/*     */     }
/*     */     
/* 777 */     return (net.a.a.c)f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<i> b() {
/* 783 */     return c.b((Node)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<i> c() {
/* 790 */     return c.b((Node)this);
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
/*     */   protected void a(g paramg, d paramd, f paramf, net.a.a.c paramc) {
/* 814 */     c.a(paramg, paramd, 
/* 815 */         b(), paramf);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(g paramg, d paramd, f paramf, net.a.a.c paramc) {
/* 821 */     a(paramg, paramd, f.b, paramc);
/*     */ 
/*     */     
/* 824 */     if (getMathbackground() == null) {
/* 825 */       paramd.a(paramf);
/*     */     } else {
/* 827 */       paramd.a(f.b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(g paramg, d paramd, net.a.a.c paramc) {
/* 834 */     a(paramg, paramd, f.c, paramc);
/*     */ 
/*     */ 
/*     */     
/* 838 */     String str = getMathbackground();
/* 839 */     Color color = a.a(str, null);
/*     */     
/* 841 */     c.a(color, paramd, false);
/* 842 */     paramd.a(f.c);
/*     */   }
/*     */   
/*     */   static {
/* 846 */     s
/* 847 */       .add("color");
/* 848 */     s
/* 849 */       .add("background");
/* 850 */     s
/* 851 */       .add("fontsize");
/* 852 */     s
/* 853 */       .add("fontweight");
/* 854 */     s
/* 855 */       .add("fontstyle");
/* 856 */     s
/* 857 */       .add("fontfamily");
/*     */     
/* 859 */     s.add("moveablelimits");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void h() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dispatchEvent(Event paramEvent) {
/* 873 */     if (paramEvent instanceof org.apache.batik.dom.events.DOMMutationEvent) {
/* 874 */       h();
/*     */     }
/* 876 */     return super.dispatchEvent(paramEvent);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */