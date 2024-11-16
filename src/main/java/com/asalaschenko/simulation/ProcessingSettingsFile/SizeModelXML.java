package com.asalaschenko.simulation.ProcessingSettingsFile;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Column", "Row"})
public class SizeModelXML {

    public SizeModelXML() {

    }

    public SizeModelXML(int column, int row) {
        this.Column = column;
        this.Row = row;
    }

    private int Column;

    private int Row;

    public int getRow() {
        return Row;
    }

    public int getColumn() {
        return Column;
    }

    public void setRow(int row) {
        Row = row;
    }

    public void setColumn(int column) {
        Column = column;
    }
}
