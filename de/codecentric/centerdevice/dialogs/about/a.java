/*     */ package de.codecentric.centerdevice.dialogs.about;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.web.WebView;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   public static final String a = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/GenericApplicationIcon.icns";
/*     */   public static final int b = 80;
/*     */   private final Stage c;
/*     */   private Label d;
/*     */   private Label e;
/*     */   private Label f;
/*     */   private Node g;
/*     */   private ImageView h;
/*     */   
/*     */   private a(Stage stage) {
/*  30 */     this.c = stage;
/*     */   }
/*     */   
/*     */   public Stage a() {
/*     */     try {
/*  35 */       c();
/*  36 */     } catch (IOException e) {
/*  37 */       e.printStackTrace();
/*     */     } 
/*  39 */     return this.c;
/*     */   }
/*     */   
/*     */   private void c() throws IOException {
/*  43 */     FXMLLoader loader = new FXMLLoader(a.class.getClassLoader().getResource("about.fxml"));
/*  44 */     Parent root = (Parent)loader.load();
/*     */     
/*  46 */     AboutController controller = (AboutController)loader.getController();
/*     */     
/*  48 */     if (this.h != null) {
/*  49 */       controller.a().getChildren().add(this.h);
/*     */     }
/*     */     
/*  52 */     if (this.e != null) {
/*  53 */       controller.a().getChildren().add(this.e);
/*     */     }
/*     */     
/*  56 */     if (this.d != null) {
/*  57 */       controller.a().getChildren().add(this.d);
/*     */     }
/*     */     
/*  60 */     if (this.g != null) {
/*  61 */       controller.a().getChildren().add(this.g);
/*     */     }
/*     */     
/*  64 */     if (this.f != null) {
/*  65 */       controller.a().getChildren().add(this.f);
/*     */     }
/*     */     
/*  68 */     this.c.setScene(new Scene(root));
/*     */   }
/*     */   
/*     */   public a b() {
/*  72 */     this.c.focusedProperty().addListener((observable, oldValue, newValue) -> {
/*     */           if (!newValue.booleanValue()) {
/*     */             this.c.close();
/*     */           }
/*     */         });
/*     */     
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   public a a(int with, int height) {
/*  82 */     this.c.setMinWidth(with);
/*  83 */     this.c.setMaxWidth(height);
/*     */     
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public a a(String title) {
/*  89 */     this.c.setTitle(title);
/*     */     
/*  91 */     return this;
/*     */   }
/*     */   
/*     */   public a b(String version) {
/*  95 */     this.d = new Label(version);
/*  96 */     this.d.getStyleClass().add("version");
/*     */     
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   public a c(String name) {
/* 102 */     this.e = new Label(name);
/* 103 */     this.e.getStyleClass().add("app_name");
/*     */     
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public a d(String html) {
/* 109 */     WebView creditsView = d();
/* 110 */     creditsView.getEngine().loadContent(html);
/* 111 */     a((Node)creditsView);
/*     */     
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   private WebView d() {
/* 117 */     WebView view = new WebView();
/* 118 */     view.setPrefHeight(140.0D);
/* 119 */     return view;
/*     */   }
/*     */   
/*     */   private void a(Node view) {
/* 123 */     BorderPane pane = new BorderPane();
/* 124 */     pane.setCenter(view);
/* 125 */     pane.getStyleClass().add("credits");
/* 126 */     this.g = (Node)pane;
/*     */   }
/*     */   
/*     */   public a e(String url) {
/* 130 */     WebView creditsView = d();
/* 131 */     creditsView.getEngine().load(url);
/* 132 */     a((Node)creditsView);
/*     */     
/* 134 */     return this;
/*     */   }
/*     */   
/*     */   public a f(String copyright) {
/* 138 */     this.f = new Label(copyright);
/*     */     
/* 140 */     return this;
/*     */   }
/*     */   
/*     */   public a a(Image image) {
/* 144 */     return a(image, 80.0D, 80.0D);
/*     */   }
/*     */   
/*     */   public a a(Image image, double width, double height) {
/* 148 */     this.h = new ImageView(image);
/* 149 */     this.h.setFitWidth(width);
/* 150 */     this.h.setFitHeight(height);
/*     */     
/* 152 */     return this;
/*     */   }
/*     */   
/*     */   public static a g(String title) {
/* 156 */     Stage aboutStage = new Stage();
/* 157 */     aboutStage.setResizable(false);
/* 158 */     return (new a(aboutStage)).a(title).a(300, 300);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/dialogs/about/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */