package samples.employee;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeMain {

    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("samples/EmployeeVirg.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }





    public static void main(String[] args) throws IOException, URISyntaxException {
         getEmployeeListLineByLine().forEach(emp -> Logger.logInfo(emp.toString()));
    }



    public List<String[]> readAllLines() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        return reader.readAll();
    }

    public static List<Employee> getEmployeeListLineByLine() throws URISyntaxException, IOException {

        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');

        List<Employee> emps = new ArrayList<Employee>();

        // read line by line
        String[] record = null;

        while ((record = reader.readNext()) != null) {
            Employee emp = new Employee();
            emp.setId(record[0]);
            emp.setName(record[1]);
            emp.setAge(record[2]);
            emp.setCountry(record[3]);
            emps.add(emp);
        }

        reader.close();
        return emps;
    }

    public static List<Employee> getEmployeeListBean() throws IOException, URISyntaxException {

        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');

        ColumnPositionMappingStrategy<Employee> beanStrategy = new ColumnPositionMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);
        beanStrategy.setColumnMapping(new String[] {"id","name","age","country"});

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();

        List<Employee> emps = csvToBean.parse(beanStrategy, reader);

        return emps;
    }

    public static List<Employee> getEmployeeListManual() throws IOException, URISyntaxException {

        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<Employee> emps = new ArrayList<>();

        List<String[]> records = reader.readAll();
        Iterator<String[]> iterator = records.iterator();

        while (iterator.hasNext()) {
            String[] record = iterator.next();
            Employee emp = new Employee();
            emp.setId(record[0]);
            emp.setName(record[1]);
            emp.setAge(record[2]);
            emp.setCountry(record[3]);
            emps.add(emp);
        }

        reader.close();

        return emps;
    }

}
