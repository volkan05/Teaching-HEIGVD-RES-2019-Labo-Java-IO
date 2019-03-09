package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;


/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    // Check that the root directory exists
    if(rootDirectory == null){
      return;
    }

    // Root directory is visited
    vistor.visit(rootDirectory);

    // Check that the root directory is a directory and not anything else
    if(rootDirectory.isDirectory()){
      // Creating of an array which contains all files of the directory
      File[] tabOfFiles = rootDirectory.listFiles();

      // Check if the array contains files
      if(tabOfFiles == null){
        return;
      }

      Arrays.sort(tabOfFiles);

      // Every files or directories are processed
      for(File files : tabOfFiles){
        // If it's a file, it will be processed
        if(files.isFile()){
          vistor.visit(files);
          // If it's a directory, it will be explore
        }else{
          explore(files, vistor);
        }
      }
    }
  }

}
