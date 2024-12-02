package com.picpaysimplicado.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long sendId, Long receiverId) {
}
