/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.filechooser.FileSystemView;
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
/*     */ class WindowsAltFileSystemView
/*     */   extends FileSystemView
/*     */ {
/*     */   public static final String EXCEPTION_CONTAINING_DIR_NULL = "AltFileSystemView.exception.containing.dir.null";
/*     */   public static final String EXCEPTION_DIRECTORY_ALREADY_EXISTS = "AltFileSystemView.exception.directory.already.exists";
/*     */   public static final String NEW_FOLDER_NAME = " AltFileSystemView.new.folder.name";
/*     */   public static final String FLOPPY_DRIVE = "AltFileSystemView.floppy.drive";
/*     */   
/*     */   public boolean isRoot(File f) {
/*  69 */     if (!f.isAbsolute()) {
/*  70 */       return false;
/*     */     }
/*     */     
/*  73 */     String parentPath = f.getParent();
/*  74 */     if (parentPath == null) {
/*  75 */       return true;
/*     */     }
/*  77 */     File parent = new File(parentPath);
/*  78 */     return parent.equals(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File createNewFolder(File containingDir) throws IOException {
/*  87 */     if (containingDir == null) {
/*  88 */       throw new IOException(Resources.getString("AltFileSystemView.exception.containing.dir.null"));
/*     */     }
/*  90 */     File newFolder = null;
/*     */     
/*  92 */     newFolder = createFileObject(containingDir, Resources.getString(" AltFileSystemView.new.folder.name"));
/*     */     
/*  94 */     int i = 2;
/*  95 */     while (newFolder.exists() && i < 100) {
/*  96 */       newFolder = createFileObject(containingDir, Resources.getString(" AltFileSystemView.new.folder.name") + " (" + i + ')');
/*     */       
/*  98 */       i++;
/*     */     } 
/*     */     
/* 101 */     if (newFolder.exists()) {
/* 102 */       throw new IOException(Resources.formatMessage("AltFileSystemView.exception.directory.already.exists", new Object[] { newFolder.getAbsolutePath() }));
/*     */     }
/*     */ 
/*     */     
/* 106 */     newFolder.mkdirs();
/*     */ 
/*     */     
/* 109 */     return newFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHiddenFile(File f) {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File[] getRoots() {
/* 127 */     List<FileSystemRoot> rootsVector = new ArrayList();
/*     */ 
/*     */     
/* 130 */     FileSystemRoot floppy = new FileSystemRoot(Resources.getString("AltFileSystemView.floppy.drive") + "\\");
/*     */     
/* 132 */     rootsVector.add(floppy);
/*     */ 
/*     */ 
/*     */     
/* 136 */     for (char c = 'C'; c <= 'Z'; c = (char)(c + 1)) {
/* 137 */       char[] device = { c, ':', '\\' };
/* 138 */       String deviceName = new String(device);
/* 139 */       File deviceFile = new FileSystemRoot(deviceName);
/* 140 */       if (deviceFile != null && deviceFile.exists()) {
/* 141 */         rootsVector.add(deviceFile);
/*     */       }
/*     */     } 
/* 144 */     File[] roots = new File[rootsVector.size()];
/* 145 */     rootsVector.toArray(roots);
/* 146 */     return roots;
/*     */   }
/*     */   
/*     */   class FileSystemRoot extends File {
/*     */     public FileSystemRoot(File f) {
/* 151 */       super(f, "");
/*     */     }
/*     */     
/*     */     public FileSystemRoot(String s) {
/* 155 */       super(s);
/*     */     }
/*     */     
/*     */     public boolean isDirectory() {
/* 159 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/WindowsAltFileSystemView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */