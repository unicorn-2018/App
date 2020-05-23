package jp.co.bizrefine;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BizcalendarApplicationTests {

	@Test
	public void contextLoads() {
		dumpFile(new File("D:/root"), 0);
	}

private static void dumpFile(File file, int level){

        File[] files = file.listFiles();

        if(files == null){
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level; i++){
            sb.append("  ");
        }
        String indent = sb.toString();

        System.out.println(indent + "/" + file.getName());

        for (File tmpFile : files) {

            if(tmpFile.isDirectory()){

                // �ċA�Ăяo��
                dumpFile(tmpFile, level + 1);

            }else{
                System.out.println(indent + "  " + tmpFile.getName());
            }
        }

    }
}

