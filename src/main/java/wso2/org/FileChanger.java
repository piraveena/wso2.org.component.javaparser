package wso2.org;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileChanger {

    public void writeInComponentFile(String wso2Annotation, int lineNumber,String filePath) throws IOException{
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.add(lineNumber, wso2Annotation);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }




}
