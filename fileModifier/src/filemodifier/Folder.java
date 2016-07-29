/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemodifier;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Devils
 */
public class Folder {
    
    private final List<Folder> subFolders;
    
    Folder(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }
    
    List<Folder> getSubFolders() {
        return this.subFolders;
    }
    
    
    static Folder fromDirectory(File dir,String toWrite) throws IOException {
        List<Folder> subFolders = new LinkedList<>();
        for (File entry : dir.listFiles()) {
            if (entry.isDirectory()) {
                subFolders.add(Folder.fromDirectory(entry,toWrite));
            }
            else if(entry.getName().contains(".h")) {
                Document.appendToFile(entry,toWrite);
                //System.out.println("You have modified: "+entry.getName());
                //documents.add(Document.fromFile(entry));
            }
        }
        return new Folder(subFolders);
    }
}
