package com.winterbe.java8.samples.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Java7中文件IO发生了很大的变化，专门引入了很多新的类：
 * <p>
 * import java.nio.file.DirectoryStream;
 * import java.nio.file.FileSystem;
 * import java.nio.file.FileSystems;
 * import java.nio.file.Files;
 * import java.nio.file.Path;
 * import java.nio.file.Paths;
 * import java.nio.file.attribute.FileAttribute;
 * import java.nio.file.attribute.PosixFilePermission;
 * import java.nio.file.attribute.PosixFilePermissions;
 * <p>
 * ......等等，来取代原来的基于java.io.File的文件IO操作方式.
 * Created by ray.wang on 2018/6/9.
 */
public class File2 {

    public static void main(String[] args) throws Exception {
        //                paths();
        //        createFile();
        //        listDir();
        permissions();
        //        copyFile();

    }

    /**
     * 0.paths
     * Path就是取代File的,Path用于来表示文件路径和文件。可以有多种方法来构造一个Path对象来表示一个文件路径，或者一个文件
     */
    private static void paths() throws IOException {
        // 1)首先是final类Paths的两个static方法，如何从一个路径字符串来构造Path对象：
        Path path = Paths.get("/tmp/", "java8");
        Path path0 = Paths.get("");
        Path path1 = Paths.get("/tmp/java8");

        URI u = URI.create("file:///tmp/java8");
        Path path2 = Paths.get(u);

        // 2)FileSystems 构造
        Path path3 = FileSystems.getDefault().getPath("/tmp/", "testFile");

        // 3)File和Path之间的转换，File和URI之间的转换：
        File file = new File("/tmp/testFile");
        Path path4 = file.toPath();
        path4.toFile();
        file.toURI();

        // 4）创建一个文件
        Path path5 = Paths.get("/tmp/java7");
        // windows下不支持PosixFilePermission来指定rwx权限。
        Set<PosixFilePermission> posixFilePermissions = PosixFilePermissions.fromString("rw-rw-rw-");
        FileAttribute<Set<PosixFilePermission>> fileAttribute =
                PosixFilePermissions.asFileAttribute(posixFilePermissions);
        if (!Files.exists(path5)) {
            Files.createFile(path5, fileAttribute);
        }

        /*
         5）Files.newBufferedReader读取文件：
         可以看到使用 Files.newBufferedReader
         远比原来的FileInputStream，然后BufferedReader包装，等操作简单的多了。
         这里如果指定的字符编码不对，可能会抛出异常 MalformedInputException ，或者读取到了乱码
        */

        // Charset.forName("GBK")
        BufferedReader reader = Files.newBufferedReader(Paths.get("/tmp/testFile"), StandardCharsets.UTF_8);
        String str;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }

        // 6）文件写操作：
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("/tmp/testFile"), StandardCharsets.UTF_8);
        writer.write("测试文件写操作");
        writer.flush();
        writer.close();

        // 7）遍历一个文件夹：
        Path dir = Paths.get("/tmp");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path p : stream) {
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 遍历单个目录，它不会遍历整个目录。遍历整个目录需要使用：Files.walkFileTree
        try (Stream<Path> stream = Files.list(Paths.get("/tmp"))) {
            Iterator<Path> ite = stream.iterator();
            while (ite.hasNext()) {
                Path pp = ite.next();
                System.out.println(pp.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 1.创建目录和文件
     * 注意:
     * 1)创建目录和文件Files.createDirectories 和 Files.createFile不能混用，必须先有目录，才能在目录中创建文件。
     * 2)如果文件存在,再创建会报错
     *
     * @throws Exception
     */
    private static void createFile() throws Exception {
        Files.createDirectories(Paths.get("/tmp/java8/test"));
        if (!Files.exists(Paths.get("/tmp/java8/test2"))) {
            Files.createDirectories(Paths.get("/tmp/java8/test2"));
            Files.createFile(Paths.get("/tmp/java8/test2/test2.file"));
        }
    }

    /**
     * 2.文件复制:
     * 从文件复制到文件：Files.copy(Path source, Path target, CopyOption options);
     * 从输入流复制到文件：Files.copy(InputStream in, Path target, CopyOption options);
     * 从文件复制到输出流：Files.copy(Path source, OutputStream out);
     *
     * @throws Exception
     */
    private static void copyFile() throws Exception {
        Files.copy(Paths.get("/tmp/testFile"), System.out);
        Files.copy(Paths.get("/tmp/testFile"), Paths.get("/tmp/testFile2"), StandardCopyOption.REPLACE_EXISTING);
        InputStream fis = System.in;
        Files.copy(fis, Paths.get("/tmp/testFile3"), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 3.遍历一个目录和文件夹上面已经介绍了：Files.newDirectoryStream ， Files.walkFileTree
     * 遍历整个文件目录
     *
     * @throws Exception
     */
    private static void listDir() throws Exception {
        Path startingDir = Paths.get("/tmp");
        List<Path> result = new LinkedList<>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        System.out.println("result.size()=" + result.size());

        System.out.println("done.");
    }

    /**
     * 读取和设置文件权限：
     */
    private static void permissions() throws IOException {
        Path profile = Paths.get("/Users/wanglei60/GitProjects/baidu/aipe/aipe-cvpaas/cvpaas-agent/data/agent.json");
        PosixFileAttributes posixFileAttributes = Files.readAttributes(profile, PosixFileAttributes.class);// 读取文件的权限
        Set<PosixFilePermission> posixPermissions = posixFileAttributes.permissions();
        posixPermissions.clear();
        String owner = posixFileAttributes.owner().getName();
        String perms = PosixFilePermissions.toString(posixPermissions);
        System.out.format("%s %s%n", owner, perms);

        posixPermissions.add(PosixFilePermission.OWNER_READ);
        posixPermissions.add(PosixFilePermission.GROUP_READ);
        posixPermissions.add(PosixFilePermission.OTHERS_READ);
        posixPermissions.add(PosixFilePermission.OWNER_WRITE);

        Files.setPosixFilePermissions(profile, posixPermissions);    // 设置文件的权限
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;

        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(".java")) {
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
