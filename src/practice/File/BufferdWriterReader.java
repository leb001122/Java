package practice.File;

import java.io.*;

public class BufferdWriterReader {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if(!file.exists())
            file.createNewFile();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write("20200881 이은빈 SOFT");
        bw.newLine();
        bw.write("20200909 서민정 SOFT");
        bw.newLine();
        bw.write("20200743 윤겸지 SOFT");
        bw.newLine();
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line=br.readLine())!=null)
            System.out.println(line);

        br.close();
    }
}
