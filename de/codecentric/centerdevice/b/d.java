/*    */ package de.codecentric.centerdevice.b;
/*    */ 
/*    */ public enum d {
/*  4 */   a("ICON"),
/*  5 */   b("ICN#"),
/*  6 */   c("icm#"),
/*  7 */   d("icm4"),
/*  8 */   e("icm8"),
/*  9 */   f("ics#"),
/* 10 */   g("ics4"),
/* 11 */   h("ics8"),
/* 12 */   i("is32"),
/* 13 */   j("s8mk"),
/* 14 */   k("icl4"),
/* 15 */   l("icl8"),
/* 16 */   m("il32"),
/* 17 */   n("l8mk"),
/* 18 */   o("ich#"),
/* 19 */   p("ich4"),
/* 20 */   q("ich8"),
/* 21 */   r("ih32"),
/* 22 */   s("h8mk"),
/* 23 */   t("it32"),
/* 24 */   u("t8mk"),
/* 25 */   v("icp4"),
/* 26 */   w("icp5"),
/* 27 */   x("icp6"),
/* 28 */   y("ic07"),
/* 29 */   z("ic08"),
/* 30 */   A("ic09"),
/* 31 */   B("ic10"),
/* 32 */   C("ic11"),
/* 33 */   D("ic12"),
/* 34 */   E("ic13"),
/* 35 */   F("ic14");
/*    */   public static d[] a() {
/*    */     return (d[])H.clone();
/*    */   }
/*    */   d(String osType) {
/* 40 */     this.G = osType;
/*    */   } public static d a(String name) {
/*    */     return Enum.<d>valueOf(d.class, name);
/*    */   } private String G; String b() {
/* 44 */     return this.G;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */