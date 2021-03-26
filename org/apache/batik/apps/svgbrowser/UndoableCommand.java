package org.apache.batik.apps.svgbrowser;

public interface UndoableCommand {
  void execute();
  
  void undo();
  
  void redo();
  
  String getName();
  
  boolean shouldExecute();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/UndoableCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */