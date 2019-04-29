package com.layer.demo.validator;

import com.layer.demo.constants.Limits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CellValidator implements ConstraintValidator<ValidCell, String> {

    private static final String ROW_PATTERN = "^[A-Z]{1,4}";
    private static final String COLUMN_PATTERN = "\\d{1,7}$";

    @Override
    public void initialize(ValidCell constraintAnnotation) {
    }
    @Override
    public boolean isValid(String cellId, ConstraintValidatorContext context){
        return (validateCell(cellId));
    }

    private boolean validateCell(String cellId) {
        // NOTE - Allow null here, to enable single cell sharing
        if(cellId == null) {
            return true;
        }
        String[] col_indexes = cellId.split(ROW_PATTERN);
        String[] row_indexes = cellId.split(COLUMN_PATTERN);

        if(col_indexes.length == 2 && row_indexes.length == 1) {
            if(row_indexes[0].length() > Limits.MAX_CELL_ROW_LENGTH) {
                return false;
            }
            try {
                int columnValue = Integer.parseInt(col_indexes[1]);
                if(columnValue > 0 && columnValue <= Limits.MAX_CELL_COLUMN_VALUE) {
                    return true;
                }
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
}
