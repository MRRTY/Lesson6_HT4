import java.io.*;

/**
 * Created by Greg on 04.02.2017.
 */
public class CopyController implements Runnable {
    private File directoryTo;
    private File file;

    public CopyController() {

    }

    public CopyController(File directoryTo, File file) {
        this.directoryTo = directoryTo;
        this.file = file;
    }

    @Override
    public void run() {
        copy();

    }
    private void copy(){
        File f = new File(directoryTo.getAbsoluteFile()+"\\"+file.getName());

        try (FileInputStream fis = new FileInputStream(file);

             FileOutputStream fos = new FileOutputStream(f)) {
            f.createNewFile();
            byte[] buffer = new byte[524_288];
            int byteread = 0;
            for (; (byteread = fis.read(buffer)) > 0;) {
                fos.write(buffer, 0, byteread);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
