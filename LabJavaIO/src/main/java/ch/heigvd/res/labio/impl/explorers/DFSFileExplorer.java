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
    // Vérifie qu'un dossier racine est entré en paramètre
    if(rootDirectory == null){
      return;
    }

    // Le dossier racine est visité
    vistor.visit(rootDirectory);

    // Vérifie que le dossier fourni soit bien un dossier afin de le parcourir
    if(rootDirectory.isDirectory()){
      // va contenir tous les fichiers du dossier
      File[] tabOfFiles = rootDirectory.listFiles();

      // Vérifie que le dossier contienne quelque chose (dossiers ou fichiers)
      if(tabOfFiles == null){
        return;
      }

      Arrays.sort(tabOfFiles);

      // Chaque dossier ou fichier du dossier racine est parcouru pour leur traitement
      for(File files : tabOfFiles){
        // Si c'est un fichier, on le traite
        if(files.isFile()){
          vistor.visit(files);
          // Si c'est un dossier, on l'explore à son tour
        }else{
          explore(files, vistor);
        }
      }
    }
  }

}
