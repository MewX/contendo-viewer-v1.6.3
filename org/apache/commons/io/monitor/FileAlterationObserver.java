/*     */ package org.apache.commons.io.monitor;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.IOCase;
/*     */ import org.apache.commons.io.comparator.NameFileComparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileAlterationObserver
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1185122225658782848L;
/* 125 */   private final List<FileAlterationListener> listeners = new CopyOnWriteArrayList<>();
/*     */ 
/*     */   
/*     */   private final FileEntry rootEntry;
/*     */   
/*     */   private final FileFilter fileFilter;
/*     */   
/*     */   private final Comparator<File> comparator;
/*     */ 
/*     */   
/*     */   public FileAlterationObserver(String directoryName) {
/* 136 */     this(new File(directoryName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileAlterationObserver(String directoryName, FileFilter fileFilter) {
/* 146 */     this(new File(directoryName), fileFilter);
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
/*     */   public FileAlterationObserver(String directoryName, FileFilter fileFilter, IOCase caseSensitivity) {
/* 159 */     this(new File(directoryName), fileFilter, caseSensitivity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileAlterationObserver(File directory) {
/* 168 */     this(directory, (FileFilter)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileAlterationObserver(File directory, FileFilter fileFilter) {
/* 178 */     this(directory, fileFilter, (IOCase)null);
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
/*     */   public FileAlterationObserver(File directory, FileFilter fileFilter, IOCase caseSensitivity) {
/* 190 */     this(new FileEntry(directory), fileFilter, caseSensitivity);
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
/*     */   protected FileAlterationObserver(FileEntry rootEntry, FileFilter fileFilter, IOCase caseSensitivity) {
/* 203 */     if (rootEntry == null) {
/* 204 */       throw new IllegalArgumentException("Root entry is missing");
/*     */     }
/* 206 */     if (rootEntry.getFile() == null) {
/* 207 */       throw new IllegalArgumentException("Root directory is missing");
/*     */     }
/* 209 */     this.rootEntry = rootEntry;
/* 210 */     this.fileFilter = fileFilter;
/* 211 */     if (caseSensitivity == null || caseSensitivity.equals(IOCase.SYSTEM)) {
/* 212 */       this.comparator = NameFileComparator.NAME_SYSTEM_COMPARATOR;
/* 213 */     } else if (caseSensitivity.equals(IOCase.INSENSITIVE)) {
/* 214 */       this.comparator = NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
/*     */     } else {
/* 216 */       this.comparator = NameFileComparator.NAME_COMPARATOR;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getDirectory() {
/* 226 */     return this.rootEntry.getFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileFilter getFileFilter() {
/* 236 */     return this.fileFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(FileAlterationListener listener) {
/* 245 */     if (listener != null) {
/* 246 */       this.listeners.add(listener);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeListener(FileAlterationListener listener) {
/* 256 */     if (listener != null) {
/* 257 */       while (this.listeners.remove(listener));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<FileAlterationListener> getListeners() {
/* 268 */     return this.listeners;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() throws Exception {
/* 277 */     this.rootEntry.refresh(this.rootEntry.getFile());
/* 278 */     FileEntry[] children = doListFiles(this.rootEntry.getFile(), this.rootEntry);
/* 279 */     this.rootEntry.setChildren(children);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkAndNotify() {
/* 296 */     for (FileAlterationListener listener : this.listeners) {
/* 297 */       listener.onStart(this);
/*     */     }
/*     */ 
/*     */     
/* 301 */     File rootFile = this.rootEntry.getFile();
/* 302 */     if (rootFile.exists()) {
/* 303 */       checkAndNotify(this.rootEntry, this.rootEntry.getChildren(), listFiles(rootFile));
/* 304 */     } else if (this.rootEntry.isExists()) {
/* 305 */       checkAndNotify(this.rootEntry, this.rootEntry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     for (FileAlterationListener listener : this.listeners) {
/* 312 */       listener.onStop(this);
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
/*     */   private void checkAndNotify(FileEntry parent, FileEntry[] previous, File[] files) {
/* 324 */     int c = 0;
/* 325 */     FileEntry[] current = (files.length > 0) ? new FileEntry[files.length] : FileEntry.EMPTY_ENTRIES;
/* 326 */     for (FileEntry entry : previous) {
/* 327 */       while (c < files.length && this.comparator.compare(entry.getFile(), files[c]) > 0) {
/* 328 */         current[c] = createFileEntry(parent, files[c]);
/* 329 */         doCreate(current[c]);
/* 330 */         c++;
/*     */       } 
/* 332 */       if (c < files.length && this.comparator.compare(entry.getFile(), files[c]) == 0) {
/* 333 */         doMatch(entry, files[c]);
/* 334 */         checkAndNotify(entry, entry.getChildren(), listFiles(files[c]));
/* 335 */         current[c] = entry;
/* 336 */         c++;
/*     */       } else {
/* 338 */         checkAndNotify(entry, entry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
/* 339 */         doDelete(entry);
/*     */       } 
/*     */     } 
/* 342 */     for (; c < files.length; c++) {
/* 343 */       current[c] = createFileEntry(parent, files[c]);
/* 344 */       doCreate(current[c]);
/*     */     } 
/* 346 */     parent.setChildren(current);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FileEntry createFileEntry(FileEntry parent, File file) {
/* 357 */     FileEntry entry = parent.newChildInstance(file);
/* 358 */     entry.refresh(file);
/* 359 */     FileEntry[] children = doListFiles(file, entry);
/* 360 */     entry.setChildren(children);
/* 361 */     return entry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FileEntry[] doListFiles(File file, FileEntry entry) {
/* 371 */     File[] files = listFiles(file);
/* 372 */     FileEntry[] children = (files.length > 0) ? new FileEntry[files.length] : FileEntry.EMPTY_ENTRIES;
/* 373 */     for (int i = 0; i < files.length; i++) {
/* 374 */       children[i] = createFileEntry(entry, files[i]);
/*     */     }
/* 376 */     return children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doCreate(FileEntry entry) {
/* 385 */     for (FileAlterationListener listener : this.listeners) {
/* 386 */       if (entry.isDirectory()) {
/* 387 */         listener.onDirectoryCreate(entry.getFile()); continue;
/*     */       } 
/* 389 */       listener.onFileCreate(entry.getFile());
/*     */     } 
/*     */     
/* 392 */     FileEntry[] children = entry.getChildren();
/* 393 */     for (FileEntry aChildren : children) {
/* 394 */       doCreate(aChildren);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doMatch(FileEntry entry, File file) {
/* 405 */     if (entry.refresh(file)) {
/* 406 */       for (FileAlterationListener listener : this.listeners) {
/* 407 */         if (entry.isDirectory()) {
/* 408 */           listener.onDirectoryChange(file); continue;
/*     */         } 
/* 410 */         listener.onFileChange(file);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doDelete(FileEntry entry) {
/* 422 */     for (FileAlterationListener listener : this.listeners) {
/* 423 */       if (entry.isDirectory()) {
/* 424 */         listener.onDirectoryDelete(entry.getFile()); continue;
/*     */       } 
/* 426 */       listener.onFileDelete(entry.getFile());
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
/*     */   private File[] listFiles(File file) {
/* 439 */     File[] children = null;
/* 440 */     if (file.isDirectory()) {
/* 441 */       children = (this.fileFilter == null) ? file.listFiles() : file.listFiles(this.fileFilter);
/*     */     }
/* 443 */     if (children == null) {
/* 444 */       children = FileUtils.EMPTY_FILE_ARRAY;
/*     */     }
/* 446 */     if (this.comparator != null && children.length > 1) {
/* 447 */       Arrays.sort(children, this.comparator);
/*     */     }
/* 449 */     return children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 459 */     StringBuilder builder = new StringBuilder();
/* 460 */     builder.append(getClass().getSimpleName());
/* 461 */     builder.append("[file='");
/* 462 */     builder.append(getDirectory().getPath());
/* 463 */     builder.append('\'');
/* 464 */     if (this.fileFilter != null) {
/* 465 */       builder.append(", ");
/* 466 */       builder.append(this.fileFilter.toString());
/*     */     } 
/* 468 */     builder.append(", listeners=");
/* 469 */     builder.append(this.listeners.size());
/* 470 */     builder.append("]");
/* 471 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/monitor/FileAlterationObserver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */