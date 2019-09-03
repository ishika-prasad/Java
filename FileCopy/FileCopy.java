/**
 * This class is for the file copy
 * for multithreading.
 *
 * @author  Ishika Prasad
 */

import java.io.*;

public class FileCopy extends Thread {
    File src, dest;

    FileCopy(File src, File dest) {
        this.src = src;
        this.dest = dest;
    }

    /**
     * This method is used to copy the file
     * from source to destination.
     * @throws IOException
     */
    public void copy() throws IOException {
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
     * This method is used for the multithreading copy
     */
    public void run() {
        try {
            copy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}