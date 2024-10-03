package com.ust.tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    @NotNull
    private String name;
    @NotBlank(message = "type of transaction is mandatory")
    private String transaction_type;
    @Min(value = 1, message="amount should be greater than 1")
    private double amount;
    @Size(min=5, max=300)
    private String description;
}
