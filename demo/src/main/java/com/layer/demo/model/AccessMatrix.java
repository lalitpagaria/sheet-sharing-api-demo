package com.layer.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessMatrix {
    private Boolean isWholeSheet;
    private List<CellRange> cells;
}
