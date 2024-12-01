package com.picpaysimplicado.dtos;

import java.math.BigDecimal;

public record TransactioDTO(BigDecimal value, Long sendId, Long receiverId) {
}
