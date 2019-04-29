package com.layer.demo.model;

import com.layer.demo.validator.ValidCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellRange {
    @NotNull
    @NotEmpty
    @ValidCell
    private String start;
    @ValidCell
    private String end;
}
