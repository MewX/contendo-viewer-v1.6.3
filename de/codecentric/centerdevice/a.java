/*     */ package de.codecentric.centerdevice;
/*     */ 
/*     */ import com.sun.javafx.scene.control.GlobalMenuAdapter;
/*     */ import de.codecentric.centerdevice.a.a;
/*     */ import de.codecentric.centerdevice.a.b;
/*     */ import de.codecentric.centerdevice.a.c;
/*     */ import de.codecentric.centerdevice.a.d;
/*     */ import de.codecentric.centerdevice.b.c;
/*     */ import de.codecentric.centerdevice.b.d;
/*     */ import de.codecentric.centerdevice.c.a;
/*     */ import de.codecentric.centerdevice.c.b;
/*     */ import de.codecentric.centerdevice.d.a;
/*     */ import de.codecentric.centerdevice.d.b;
/*     */ import de.codecentric.centerdevice.dialogs.about.a;
/*     */ import de.codecentric.centerdevice.e.a;
/*     */ import de.codecentric.centerdevice.e.c;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Locale;
/*     */ import javafx.collections.ListChangeListener;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.control.Menu;
/*     */ import javafx.scene.control.MenuBar;
/*     */ import javafx.scene.control.MenuItem;
/*     */ import javafx.scene.control.SeparatorMenuItem;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyCodeCombination;
/*     */ import javafx.scene.input.KeyCombination;
/*     */ import javafx.scene.layout.Pane;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   private static final String a = "Apple";
/*     */   private final d b;
/*     */   private final c c;
/*     */   private final a d;
/*     */   
/*     */   private a(a adapterContext, a labelMaker) {
/*  43 */     this.b = adapterContext.c();
/*  44 */     this.c = adapterContext.b();
/*  45 */     this.d = labelMaker;
/*     */   }
/*     */   
/*     */   public static a a() {
/*  49 */     return a(Locale.ENGLISH);
/*     */   }
/*     */   
/*     */   public static a a(Locale locale) {
/*  53 */     return a(new a(locale));
/*     */   }
/*     */   
/*     */   public static a a(a labelMaker) {
/*  57 */     a context = a.a();
/*  58 */     if (context == null) {
/*  59 */       return null;
/*     */     }
/*     */     
/*  62 */     return new a(context, labelMaker);
/*     */   }
/*     */   
/*     */   public Menu a(String appName) {
/*  66 */     return a(appName, e(appName));
/*     */   }
/*     */   
/*     */   public Menu a(String appName, Stage aboutStage) {
/*  70 */     return new Menu("Apple", null, new MenuItem[] { b(appName, aboutStage), (MenuItem)new SeparatorMenuItem(), d(appName), c(), 
/*  71 */           b(), (MenuItem)new SeparatorMenuItem(), c(appName) });
/*     */   }
/*     */   
/*     */   public MenuItem b(String appName) {
/*  75 */     return b(appName, e(appName));
/*     */   }
/*     */ 
/*     */   
/*     */   private Stage e(String appName) {
/*  80 */     a stageBuilder = a.g(this.d.a(b.c, new Object[] { appName })).c(appName).b().f("Copyright © " + 
/*  81 */         Calendar.getInstance().get(1));
/*     */     
/*     */     try {
/*  84 */       c parser = c.a("/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/GenericApplicationIcon.icns");
/*  85 */       stageBuilder = stageBuilder.a(new Image(parser.b(d.z)));
/*  86 */     } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */     
/*  90 */     return stageBuilder.a();
/*     */   }
/*     */   
/*     */   public MenuItem b(String appName, Stage aboutStage) {
/*  94 */     MenuItem about = new MenuItem(this.d.a(b.c, new Object[] { appName }));
/*  95 */     about.setOnAction(event -> aboutStage.show());
/*  96 */     return about;
/*     */   }
/*     */   
/*     */   public MenuItem c(String appName) {
/* 100 */     MenuItem quit = new MenuItem(this.d.a(b.b, new Object[] { appName }));
/* 101 */     quit.setOnAction(event -> this.c.d());
/* 102 */     quit.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.Q, new KeyCombination.Modifier[] { KeyCombination.META_DOWN }));
/* 103 */     return quit;
/*     */   }
/*     */   
/*     */   public MenuItem b() {
/* 107 */     MenuItem unhideAll = new MenuItem(this.d.a(b.d, new Object[0]));
/* 108 */     unhideAll.setOnAction(event -> this.c.c());
/* 109 */     return unhideAll;
/*     */   }
/*     */   
/*     */   public MenuItem c() {
/* 113 */     MenuItem hideOthers = new MenuItem(this.d.a(b.e, new Object[0]));
/* 114 */     hideOthers.setOnAction(event -> this.c.b());
/* 115 */     hideOthers.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.H, new KeyCombination.Modifier[] { KeyCombination.META_DOWN, KeyCombination.ALT_DOWN }));
/* 116 */     return hideOthers;
/*     */   }
/*     */   
/*     */   public MenuItem d(String appName) {
/* 120 */     MenuItem hide = new MenuItem(this.d.a(b.a, new Object[] { appName }));
/* 121 */     hide.setOnAction(event -> this.c.a());
/* 122 */     hide.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.H, new KeyCombination.Modifier[] { KeyCombination.META_DOWN }));
/* 123 */     return hide;
/*     */   }
/*     */   
/*     */   public MenuItem d() {
/* 127 */     MenuItem menuItem = new MenuItem(this.d.a(b.f, new Object[0]));
/* 128 */     menuItem.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.M, new KeyCombination.Modifier[] { KeyCombination.META_DOWN }));
/* 129 */     menuItem.setOnAction(event -> c.c());
/* 130 */     return menuItem;
/*     */   }
/*     */   
/*     */   public MenuItem e() {
/* 134 */     MenuItem menuItem = new MenuItem(this.d.a(b.g, new Object[0]));
/* 135 */     menuItem.setOnAction(event -> c.b());
/* 136 */     return menuItem;
/*     */   }
/*     */   
/*     */   public MenuItem f() {
/* 140 */     MenuItem menuItem = new MenuItem(this.d.a(b.h, new Object[0]));
/* 141 */     menuItem.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.W, new KeyCombination.Modifier[] { KeyCombination.META_DOWN }));
/* 142 */     menuItem.setOnAction(event -> c.d());
/* 143 */     return menuItem;
/*     */   }
/*     */   
/*     */   public MenuItem g() {
/* 147 */     MenuItem menuItem = new MenuItem(this.d.a(b.i, new Object[0]));
/* 148 */     menuItem.setOnAction(event -> c.a());
/* 149 */     return menuItem;
/*     */   }
/*     */   
/*     */   public MenuItem h() {
/* 153 */     MenuItem menuItem = new MenuItem(this.d.a(b.j, new Object[0]));
/* 154 */     menuItem.setAccelerator((KeyCombination)new KeyCodeCombination(KeyCode.BACK_QUOTE, new KeyCombination.Modifier[] { KeyCombination.META_DOWN }));
/* 155 */     menuItem.setOnAction(event -> c.e());
/* 156 */     return menuItem;
/*     */   }
/*     */   
/*     */   public void a(Menu menu) {
/*     */     try {
/* 161 */       this.b.a(GlobalMenuAdapter.adapt(menu));
/* 162 */     } catch (Throwable e) {
/* 163 */       throw new b(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(MenuBar menuBar) {
/* 168 */     b(menuBar);
/* 169 */     a.a(menuBar);
/*     */   }
/*     */   
/*     */   public void i() {
/* 173 */     a.a();
/*     */   }
/*     */   
/*     */   public void b(MenuBar menuBar) {
/* 177 */     c.f().forEach(stage -> a(stage, menuBar));
/*     */   }
/*     */   
/*     */   public void a(Stage stage, MenuBar menuBar) {
/* 181 */     Parent parent = stage.getScene().getRoot();
/* 182 */     if (parent instanceof Pane) {
/* 183 */       a((Pane)parent, menuBar);
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(Pane pane, MenuBar menuBar) {
/* 188 */     a(c(menuBar));
/* 189 */     a.a(pane, menuBar);
/*     */   }
/*     */   
/*     */   public void b(Menu menu) {
/* 193 */     menu.getItems().add(new SeparatorMenuItem());
/* 194 */     c.f().addListener((ListChangeListener)new b(menu));
/*     */   }
/*     */   
/*     */   public void a(boolean forceQuit) {
/* 198 */     this.c.a(forceQuit);
/*     */   }
/*     */   
/*     */   protected Menu c(MenuBar menuBar) {
/* 202 */     return (Menu)menuBar.getMenus().get(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */