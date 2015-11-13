package edu.tsu.ge.salesman.core.input;

import edu.tsu.ge.salesman.core.model.City;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelInputParser implements InputParser {

    private final List<City> cities;

    public ExcelInputParser(InputStream inputStream) throws Exception {
        cities = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            int rowIndex = 0;
            for (Row row : sheet) {
                City city = new City();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowIndex = cell.getRowIndex();
                    if (rowIndex == 0 || cell.getColumnIndex() == 0) {
                        continue;
                    }

                    switch (cell.getColumnIndex()) {
                        case 1:
                            city.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            city.setX((int) cell.getNumericCellValue());
                            break;
                        case 3:
                            city.setY((int) cell.getNumericCellValue());
                            break;
                    }
                }
                if (rowIndex != 0) {
                    cities.add(city);
                }
            }
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage(), t);
        }
    }

    @Override
    public List<City> getCities() {
        return cities;
    }
}
