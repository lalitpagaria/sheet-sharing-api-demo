package com.layer.demo.model;

import com.layer.demo.validator.ValidEmailCollection;
import com.layer.demo.validator.ValidName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharingRequest {
    @NotNull
    @NotEmpty
    @ValidName
    private String fileName;
    @NotNull
    @ValidEmailCollection
    private List<String> emails;
    @NotNull
    @Valid
    private List<Selection> selections;
}
