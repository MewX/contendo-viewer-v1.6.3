/*     */ package de.codecentric.centerdevice.e;
/*     */ 
/*     */ import com.sun.javafx.stage.StageHelper;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ListChangeListener;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.geometry.Rectangle2D;
/*     */ import javafx.stage.Screen;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.Window;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */ {
/*     */   private static ObservableList<Stage> a;
/*     */   private static ObservableList<Window> b;
/*     */   
/*     */   public static void a() {
/*  25 */     Optional<Stage> focusedStage = g();
/*  26 */     f().forEach(stage -> stage.toFront());
/*  27 */     focusedStage.ifPresent(stage -> stage.toFront());
/*     */   }
/*     */   
/*     */   public static void b() {
/*  31 */     g().ifPresent(stage -> {
/*     */           ObservableList<Screen> screens = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
/*     */           if (screens.size() == 1) {
/*     */             a(stage, ((Screen)screens.get(0)).getBounds());
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void c() {
/*  42 */     g().ifPresent(stage -> stage.setIconified(true));
/*     */   }
/*     */   
/*     */   public static void d() {
/*  46 */     g().ifPresent(stage -> stage.close());
/*     */   }
/*     */   
/*     */   public static void e() {
/*  50 */     ObservableList<Stage> stages = f();
/*  51 */     int currentStageIndex = a((List<Stage>)stages);
/*  52 */     if (currentStageIndex < stages.size() - 1) {
/*  53 */       ((Stage)stages.get(currentStageIndex + 1)).toFront();
/*  54 */     } else if (stages.size() > 0) {
/*  55 */       ((Stage)stages.get(0)).toFront();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ObservableList<Stage> f() {
/*  61 */     if (a == null) {
/*     */       
/*     */       try {
/*  64 */         b = (ObservableList<Window>)Window.class.getMethod("getWindows", new Class[0]).invoke(null, new Object[0]);
/*  65 */         a = FXCollections.observableArrayList();
/*  66 */         b.addListener(new ListChangeListener<Window>()
/*     */             {
/*     */               public void onChanged(ListChangeListener.Change<? extends Window> c) {
/*  69 */                 c.h();
/*     */               }
/*     */             });
/*  72 */         i();
/*  73 */       } catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException noSuchMethodException) {}
/*     */ 
/*     */ 
/*     */       
/*  77 */       if (a == null) {
/*     */         
/*     */         try {
/*  80 */           a = (ObservableList<Stage>)StageHelper.class.getMethod("getStages", new Class[0]).invoke(null, new Object[0]);
/*  81 */         } catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException noSuchMethodException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  87 */     return a;
/*     */   }
/*     */   
/*     */   private static void i() {
/*  91 */     List<Stage> newStages = new LinkedList<>();
/*  92 */     for (Window w : b) {
/*  93 */       if (w instanceof Stage) {
/*  94 */         newStages.add((Stage)w);
/*     */       }
/*     */     } 
/*  97 */     a.setAll(newStages);
/*     */   }
/*     */   
/*     */   public static Optional<Stage> g() {
/* 101 */     return f().stream().filter(stage -> stage.isFocused()).findFirst();
/*     */   }
/*     */   
/*     */   public static int a(List<Stage> stages) {
/* 105 */     for (int i = 0; i < stages.size(); i++) {
/* 106 */       if (((Stage)stages.get(i)).isFocused()) {
/* 107 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return -1;
/*     */   }
/*     */   
/*     */   public static void a(Stage stage, Rectangle2D screenBounds) {
/* 115 */     stage.setX(screenBounds.getMinX());
/* 116 */     stage.setY(screenBounds.getMinY());
/* 117 */     stage.setWidth(screenBounds.getMaxX() - screenBounds.getMinX());
/* 118 */     stage.setHeight(screenBounds.getMaxY() - screenBounds.getMinY());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/e/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */