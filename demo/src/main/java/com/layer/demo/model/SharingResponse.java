package com.layer.demo.model;

import com.layer.demo.validator.ValidName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharingResponse {
    @NotNull
    @NotEmpty
    @ValidName
    private String fileName;
    @NotNull
    @Valid
    Map<String, Map<String, List<CellRange>>> sheetAccess;
}
