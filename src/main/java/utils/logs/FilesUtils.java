package utils.logs;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FilesUtils {

    public static String getRessourceFile(String pathInResFolder) throws URISyntaxException {
        URL res = FilesUtils.class.getClassLoader().getResource(pathInResFolder);
        File file = Paths.get(res.toURI()).toFile();
        return file.getAbsolutePath();
    }
}
