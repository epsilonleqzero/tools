/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemodifier;

import java.io.File;

/**
 *
 * @author Devils
 */
public class appendToFile implements Runnable{
    
    private final File file;
    
    public appendToFile(File file){
        this.file=file;
    }

    @Override
    public void run() {
        
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
