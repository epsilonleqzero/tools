/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemodifier;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.lang.Runtime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devils
 */
public class FileModifier {

    private final static int NUM_CPUS = Runtime.getRuntime().availableProcessors();

    private final Executor fileReaderWriter = Executors.newSingleThreadExecutor();
    private final Executor fileProcessor = Executors.newFixedThreadPool(NUM_CPUS);
    //private final static String thePath = "C:\\Users\\Devils\\Documents\\GitHub\\github-network-testing\\linux-3.1.2";
    /**
     * @param args the command line arguments
     */

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
    }

    public static void main(String[] args) {
        String thePath=System.getProperty("user.dir");
        if(thePath.contains("github-network-testing")){
            System.out.println("Right location, running.");
        long count =0;
        FileModifier mod = new FileModifier();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String toWrite=dateFormat.format(date);
        String ap = "There are " + NUM_CPUS + " CPUS";
        long currentTime=System.nanoTime();
        try {
            Folder folder = Folder.fromDirectory(new File(thePath), toWrite);
            count = mod.countOccurrencesInParallel(folder, toWrite);
        } catch (IOException ex) {
            Logger.getLogger(FileModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        long after=System.nanoTime();
        long thetime=after-currentTime;
        System.out.println("This process took "+ TimeUnit.SECONDS.convert(thetime, TimeUnit.NANOSECONDS) + " seconds");
        }
        else{
            System.out.println("Wrong location");
        }
    }
}
