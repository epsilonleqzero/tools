/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemodifier;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Devils
 */
public class FolderSearchTask extends RecursiveTask<Long> {
        private final Folder folder;
        private final String searchedWord;
        
        FolderSearchTask(Folder folder, String searchedWord) {
            super();
            this.folder = folder;
            this.searchedWord = searchedWord;
        }
        
        @Override
        protected Long compute() {
            long count = 0L;
            List<RecursiveTask<Long>> forks = new LinkedList<>();
            folder.getSubFolders().stream().map((subFolder) -> new FolderSearchTask(subFolder, searchedWord)).map((task) -> {
                forks.add(task);
                return task;
            }).forEach((task) -> {
                task.fork();
            });
            for (RecursiveTask<Long> task : forks) {
                count = count + task.join();
            }
            return count;
        }
}
