/*    */ package de.codecentric.centerdevice.a;
/*    */ 
/*    */ import com.sun.glass.ui.Application;
/*    */ import de.codecentric.centerdevice.e.b;
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import javafx.application.Platform;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */ {
/*    */   private MethodHandle a;
/*    */   private MethodHandle b;
/*    */   private MethodHandle c;
/*    */   private Application d;
/*    */   private boolean e = true;
/*    */   
/*    */   public c() throws ReflectiveOperationException {
/* 20 */     this.d = Application.GetApplication();
/*    */     
/* 22 */     this.a = b.a(this.d, "_hide", new Class[0]);
/* 23 */     this.b = b.a(this.d, "_hideOtherApplications", new Class[0]);
/* 24 */     this.c = b.a(this.d, "_unhideAllApplications", new Class[0]);
/*    */   }
/*    */   
/*    */   public void a() {
/* 28 */     b.a(this.a, this.d);
/*    */   }
/*    */   
/*    */   public void b() {
/* 32 */     b.a(this.b, this.d);
/*    */   }
/*    */   
/*    */   public void c() {
/* 36 */     b.a(this.c, this.d);
/*    */   }
/*    */   
/*    */   public void d() {
/* 40 */     Application.EventHandler eh = this.d.getEventHandler();
/* 41 */     if (eh != null) {
/* 42 */       eh.handleQuitAction(Application.GetApplication(), System.nanoTime());
/*    */     }
/* 44 */     if (this.e) {
/* 45 */       Platform.exit();
/*    */     }
/*    */   }
/*    */   
/*    */   public void a(boolean forceQuit) {
/* 50 */     this.e = forceQuit;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */