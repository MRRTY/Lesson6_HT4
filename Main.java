import javax.swing.table.TableRowSorter;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Greg on 03.02.2017.
 */
public class Main {
    public static void main(String args[]){
        File input = new File("C:/USERS/GREG/Desktop/input");
        File output = new File("C:/USERS/GREG/Desktop/output");
        int n = input.listFiles().length;
        ArrayBlockingQueue<File> queue= new ArrayBlockingQueue<File>(n);
        for (File f: input.listFiles()) {
            if(f.isFile())
                queue.add(f);
        }
        int c = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[c];
        for(Thread t : threads){
            if(!queue.isEmpty()) {
                File f = queue.poll();
                t = new Thread(new CopyController(output, f));
                t.start();

            }
        }
        while (!queue.isEmpty()){
            for(int i = 0; i < c; i++){
                    if (!threads[i].isAlive()) {
                        File f = queue.poll();
                        threads[i] = new Thread(new CopyController(output,f));
                        threads[i].start();

                    }

            }
        }

    }

}
