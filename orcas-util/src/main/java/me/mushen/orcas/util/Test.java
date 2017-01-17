package me.mushen.orcas.util;

import java.io.File;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-01-17
 */
public class Test {

    public static void main(String[] args){
        System.out.println(Directory.local("/tmp", ".*"));
        System.out.println(Directory.local(new File("/tmp")));

        Directory.FileDirTree fileDirTree1 = Directory.walk("/tmp");
        System.out.println(fileDirTree1.getDirList());
        System.out.println(fileDirTree1.getFileList());

        Directory.FileDirTree fileDirTree2 = Directory.walk("/tmp", ".*\\.txt");
        System.out.println(fileDirTree2.getDirList());
        System.out.println(fileDirTree2.getFileList());
    }
}
