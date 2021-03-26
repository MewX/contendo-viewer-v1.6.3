/*     */ package org.apache.commons.io.monitor;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Serializable;
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
/*     */ public class FileEntry
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2505664948818681153L;
/*  47 */   static final FileEntry[] EMPTY_ENTRIES = new FileEntry[0];
/*     */   
/*     */   private final FileEntry parent;
/*     */   
/*     */   private FileEntry[] children;
/*     */   
/*     */   private final File file;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private boolean exists;
/*     */   
/*     */   private boolean directory;
/*     */   private long lastModified;
/*     */   private long length;
/*     */   
/*     */   public FileEntry(File file) {
/*  64 */     this(null, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileEntry(FileEntry parent, File file) {
/*  74 */     if (file == null) {
/*  75 */       throw new IllegalArgumentException("File is missing");
/*     */     }
/*  77 */     this.file = file;
/*  78 */     this.parent = parent;
/*  79 */     this.name = file.getName();
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
/*     */   public boolean refresh(File file) {
/*  99 */     boolean origExists = this.exists;
/* 100 */     long origLastModified = this.lastModified;
/* 101 */     boolean origDirectory = this.directory;
/* 102 */     long origLength = this.length;
/*     */ 
/*     */     
/* 105 */     this.name = file.getName();
/* 106 */     this.exists = file.exists();
/* 107 */     this.directory = (this.exists && file.isDirectory());
/* 108 */     this.lastModified = this.exists ? file.lastModified() : 0L;
/* 109 */     this.length = (this.exists && !this.directory) ? file.length() : 0L;
/*     */ 
/*     */     
/* 112 */     return (this.exists != origExists || this.lastModified != origLastModified || this.directory != origDirectory || this.length != origLength);
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
/*     */   public FileEntry newChildInstance(File file) {
/* 128 */     return new FileEntry(this, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileEntry getParent() {
/* 137 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/* 146 */     return (this.parent == null) ? 0 : (this.parent.getLevel() + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileEntry[] getChildren() {
/* 157 */     return (this.children != null) ? this.children : EMPTY_ENTRIES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChildren(FileEntry[] children) {
/* 166 */     this.children = children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 175 */     return this.file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 184 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 193 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastModified() {
/* 203 */     return this.lastModified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastModified(long lastModified) {
/* 213 */     this.lastModified = lastModified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLength() {
/* 222 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(long length) {
/* 231 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExists() {
/* 241 */     return this.exists;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExists(boolean exists) {
/* 251 */     this.exists = exists;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDirectory() {
/* 260 */     return this.directory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirectory(boolean directory) {
/* 269 */     this.directory = directory;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/monitor/FileEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */