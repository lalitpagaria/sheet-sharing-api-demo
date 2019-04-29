package com.layer.demo.model;

import com.layer.demo.validator.ValidName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Selection {
    @NotNull
    @NotEmpty
    @ValidName
    private String sheetName;
    @Valid
    private CellRange cellRange;
}
