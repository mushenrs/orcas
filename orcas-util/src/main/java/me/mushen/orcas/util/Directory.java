package me.mushen.orcas.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-01-17
 */
public final class Directory {

    /**
     * 获取指定目录中所有的File对象列表
     * @param dirPath 指定目录路径
     * @return
     */
    public static List<File> local(String dirPath) {
        return local(new File(dirPath));
    }

    /**
     * 获取指定目录中所有的File对象列表
     * @param dir 指定目录File
     * @return
     */
    public static List<File> local(File dir) {
        return local(dir, ".*");
    }

    /**
     * 获取指定目录中文件符合正则表达式条件的File对象列表
     * @param dirPath 指定目录路径
     * @param regex 文件名正则表达式
     * @return
     */
    public static List<File> local(String dirPath, final String regex) {
        return local(new File(dirPath), regex);
    }

    /**
     * 获取指定目录中文件符合正则表达式条件的File对象列表
     * @param dir 指定目录File
     * @param regex 文件名正则表达式
     * @return
     */
    public static List<File> local(File dir, final String regex) {
        File[] files = dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
        return CollectionMaps.isNullOrEmpty(files) ? new ArrayList<>() : Arrays.asList(files);
    }

    /**
     * 获取指定起始路径中所有子目录和文件(递归遍历整个目录树)
     * @param startDir 起始路径File
     * @return
     */
    public static FileDirTree walk(File startDir){
        return recurseDir(startDir, ".*");
    }

    /**
     * 获取指定起始路径中所有子目录和文件(递归遍历整个目录树)
     * @param startDirPath 起始路径
     * @return
     */
    public static FileDirTree walk(String startDirPath){
        return walk(new File(startDirPath));
    }

    /**
     * 获取指定起始路径中所有子目录和文件(递归遍历整个目录树), 其中如果是文件需要满足正则表达式
     * @param startDir 起始路径File
     * @param regex 文件名正则表达式
     * @return
     */
    public static FileDirTree walk(File startDir, final String regex){
        return recurseDir(startDir, regex);
    }

    /**
     * 获取指定起始路径中所有子目录和文件(递归遍历整个目录树), 其中如果是文件需要满足正则表达式
     * @param startDirPath 起始路径File
     * @param regex 文件名正则表达式
     * @return
     */
    public static FileDirTree walk(String startDirPath, final String regex){
        return walk(new File(startDirPath), regex);
    }

    private static FileDirTree recurseDir(File startDir, final String regex) {
        FileDirTree result = new FileDirTree();
        // 当前目录下的所有文件和子目录
        File[] items = startDir.listFiles();
        if(items != null && items.length > 0) {
            for(File item : items) {
                if(item.isDirectory()) { // 目录
                    result.addDir(item);
                    // 递归遍历子目录
                    result.add(recurseDir(item, regex));
                }else {
                    if(item.getName().matches(regex)) { // 文件是否符合正则表达式条件
                        result.addFile(item);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 文件和目录Tuple
     */
    public static class FileDirTree {
        // 文件列表
        private List<File> fileList = new ArrayList<>();
        // 目录列表
        private List<File> dirList = new ArrayList<>();

        private void addFile(File file) {
            if(file != null) {
                fileList.add(file);
            }
        }

        private void addFiles(Collection<File> files) {
            if(!CollectionMaps.isNullOrEmpty(files)) {
                fileList.addAll(files);
            }
        }

        private void addDir(File dir) {
            if(dir != null) {
                dirList.add(dir);
            }
        }

        private void addDirs(Collection<File> dirs) {
            if(!CollectionMaps.isNullOrEmpty(dirs)) {
                dirList.addAll(dirs);
            }
        }

        private void addFileDir(File file, File dir) {
            addFile(file);
            addDir(dir);
        }

        private void addFileDirs(Collection<File> files, Collection<File> dirs) {
            addFiles(files);
            addDirs(dirs);
        }

        private void add(FileDirTree fileDirTree) {
            dirList.addAll(fileDirTree.getDirList());
            fileList.addAll(fileDirTree.getFileList());
        }

        public List<File> getFileList() {
            return fileList;
        }

        public List<File> getDirList() {
            return dirList;
        }

        private FileDirTree(){}
    }
}
