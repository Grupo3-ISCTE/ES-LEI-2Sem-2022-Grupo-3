package org.jfree.data.io;


import org.jfree.data.category.CategoryDataset;
import java.io.Reader;
import java.io.IOException;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class HandleCategoryDataset {
    private char fieldDelimiter;



    public void setFieldDelimiter(char fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public char getFieldDelimiter(){return this.fieldDelimiter;}

    /**
     * Reads a  {@link CategoryDataset}  from a CSV file or input source.
     * @param in   the input source.
     * @return  A category dataset.
     * @throws IOException  if there is an I/O problem.
     */
    public CategoryDataset readCategoryDataset(Reader in, CSV cSV) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BufferedReader reader = new BufferedReader(in);
        List columnKeys = null;
        int lineIndex = 0;
        String line = reader.readLine();
        while (line != null) {
            if (lineIndex == 0) {
                columnKeys = extractColumnKeys(line, cSV);
            } else {
                extractRowKeyAndData(line, dataset, columnKeys, cSV);
            }
            line = reader.readLine();
            lineIndex++;
        }
        return dataset;
    }

    /**
     * Extracts the column keys from a string.
     * @param line   a line from the input file.
     * @return  A list of column keys.
     */
    public List extractColumnKeys(String line, CSV cSV) {
        List keys = new java.util.ArrayList();
        int fieldIndex = 0;
        int start = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == this.fieldDelimiter) {
                if (fieldIndex > 0) {
                    String key = line.substring(start, i);
                    keys.add(cSV.removeStringDelimiters(key));
                }
                start = i + 1;
                fieldIndex++;
            }
        }
        String key = line.substring(start, line.length());
        keys.add(cSV.removeStringDelimiters(key));
        return keys;
    }

    /**
     * Extracts the row key and data for a single line from the input source.
     * @param line   the line from the input source.
     * @param dataset   the dataset to be populated.
     * @param columnKeys   the column keys.
     */
    public void extractRowKeyAndData(String line, DefaultCategoryDataset dataset, List columnKeys, CSV cSV) {
        Comparable rowKey = null;
        int fieldIndex = 0;
        int start = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == this.fieldDelimiter) {
                if (fieldIndex == 0) {
                    String key = line.substring(start, i);
                    rowKey = cSV.removeStringDelimiters(key);
                } else {
                    Double value = Double.valueOf(cSV.removeStringDelimiters(line.substring(start, i)));
                    dataset.addValue(value, rowKey, (Comparable) columnKeys.get(fieldIndex - 1));
                }
                start = i + 1;
                fieldIndex++;
            }
        }
        Double value = Double.valueOf(cSV.removeStringDelimiters(line.substring(start, line.length())));
        dataset.addValue(value, rowKey, (Comparable) columnKeys.get(fieldIndex - 1));
    }
}