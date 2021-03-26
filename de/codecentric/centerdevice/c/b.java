/*    */ package de.codecentric.centerdevice.c;
/*    */ 
/*    */ public enum b {
/*  4 */   a("hide"), b("quit"), c("about"), d("show_all"), e("hide_others"), f("minimize"),
/*  5 */   g("zoom"), h("close_window"), i("bring_all_to_front"),
/*  6 */   j("cycle_through_windows"), k("file"), l("window"), m("view"),
/*  7 */   n("help"), o("edit");
/*    */   public static b[] a() {
/*    */     return (b[])q.clone();
/*    */   }
/*    */   b(String propertyKey) {
/* 12 */     this.p = propertyKey;
/*    */   } public static b a(String name) {
/*    */     return Enum.<b>valueOf(b.class, name);
/*    */   } private String p; public String b() {
/* 16 */     return this.p;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */