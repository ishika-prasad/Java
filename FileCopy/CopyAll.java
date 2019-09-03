/*
 * CopyAll.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is used to create a sequential
 * and multithreaded way to copy a directory to
 *  new location.
 *
 * @author  Ishika Prasad
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyAll {

    /**
     * This method checks if the source directory
     *  does not exist then exit the program and if
     *  source path is not a directory path then exit
     *  the program
     * @param srcDirPath source of directory path
     */
    private static void validateSourceDirectory(String srcDirPath) {
        File srcFile = new File(srcDirPath);
        if (!srcFile.exists()) {
            System.out.println("Source directory does not exist");
            System.exit(0);
        }
        if (!srcFile.isDirectory()) {
            System.out.println("Source path is not a directory path");
            System.exit(0);
        }
    }

    /**
     * This method is used to copy the file from
     * source to destination.
     * @param src source of the file
     * @param dest destination of a file
     * @throws IOException
     */
    public static void copy(File src, File dest) throws IOException {
        if (!dest.exists()) {
            dest.createNewFile();
        }
        try (InputStream is = new FileInputStream(src); OutputStream os = new FileOutputStream(dest);) {
            byte[] buf = new byte[1024];
            int bytes;
            while ((bytes = is.read(buf)) > 0) {
                os.write(buf, 0, bytes);
            }
        }
    }

    /**
     * This method is used for get the
     * destination directory with the new name
     * @param desDirPath  destination directory path
     * @return destination file
     */
    private static File getDestinationDirectory(String desDirPath) {
        File desDir = new File(desDirPath);
        if (desDir.exists()) {
            desDirPath = desDirPath + "_copy";
            desDir = new File(desDirPath);
        }
        int count = 1;
        while (desDir.exists()) {
            desDirPath = desDirPath + count;
            count++;
            desDir = new File(desDirPath);
        }
        desDir.mkdir();
        return desDir;
    }

    /**
     * This method is used to perform the sequential
     * copy from given source directory path to destination
     * directory path
     * @param srcDirPath given source directory path
     * @param desDirPath given destination directory path
     * @throws IOException
     */
    private static void performSequentialCopy(String srcDirPath, String desDirPath)
            throws IOException {
        File srcDir = new File(srcDirPath);
        File desDir = getDestinationDirectory(desDirPath);

        System.out.println("destination directory path" + desDir.getAbsolutePath());
        File[] files = srcDir.listFiles();
        long startTime = System.currentTimeMillis();
        for (File file : files) {
            String desFile = desDir.getAbsolutePath() + File.separator + file.getName();
            copy(file, new File(desFile));
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("time taken in sequential copy " + time + " ms");
    }

    /**
     * Thia method is used to perform multithreading copy
     * from given source directory path to destination
     * directory path
     * @param srcDirPath given source directory path
     * @param desDirPath given destination directory path
     * @throws InterruptedException
     */
    private static void peformMultiThreadedCopy(String srcDirPath, String desDirPath) throws InterruptedException {
        File srcDir = new File(srcDirPath);
        File desDir = getDestinationDirectory(desDirPath);
        System.out.println("destination directory path" + desDir.getAbsolutePath());
        File[] files = srcDir.listFiles();
        ExecutorService service = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (File file : files) {
            String desFile = desDir.getAbsolutePath() + File.separator + file.getName();
            FileCopy fileCopy = new FileCopy(file, new File(desFile));
            service.submit(fileCopy);
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long time = System.currentTimeMillis() - start;
        System.out.println("time taken in multithreaded copy " + time + " ms");

    }

    /**
     * The main method
     * @param args ags[0] is path for source directory
     *             args[1] is path for destination directory
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String srcDirPath = args[0];
        String desDirPath = args[1];
        validateSourceDirectory(srcDirPath);
        performSequentialCopy(srcDirPath, desDirPath);
        peformMultiThreadedCopy(srcDirPath, desDirPath);
    }
}