package org.apache.batik.bridge;

public interface UpdateManagerListener {
  void managerStarted(UpdateManagerEvent paramUpdateManagerEvent);
  
  void managerSuspended(UpdateManagerEvent paramUpdateManagerEvent);
  
  void managerResumed(UpdateManagerEvent paramUpdateManagerEvent);
  
  void managerStopped(UpdateManagerEvent paramUpdateManagerEvent);
  
  void updateStarted(UpdateManagerEvent paramUpdateManagerEvent);
  
  void updateCompleted(UpdateManagerEvent paramUpdateManagerEvent);
  
  void updateFailed(UpdateManagerEvent paramUpdateManagerEvent);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UpdateManagerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */