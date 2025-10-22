package com.tenpo.challenge.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.tenpo.challenge.common.Constants;
import com.tenpo.challenge.dto.HistoryRecordDto;
import com.tenpo.challenge.entity.HistoryRecord;
import com.tenpo.challenge.repository.HistoryRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(Constants.ENDPOINT_HISTORY)
@RequiredArgsConstructor
@Tag(name = "Historial", description = "Consulta del historial de llamadas paginado")
public class HistoryController {

    private final HistoryRecordRepository repository;

    @GetMapping
    @Operation(
            summary = "Lista el historial paginado",
            description = "Devuelve el historial de llamadas con fecha, endpoint, parámetros, respuesta y error",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = HistoryRecordDto.class))))
            }
    )
    public ResponseEntity<Page<HistoryRecordDto>> list(
            @Parameter(description = "Número de página (0-index)")
            @RequestParam(name = Constants.PARAM_PAGE, defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página (1-100)")
            @RequestParam(name = Constants.PARAM_SIZE, defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, Math.max(1, Math.min(size, 100)));
        Page<HistoryRecord> result = repository.findAll(pageable);
        List<HistoryRecordDto> items = result
                .getContent()
                .stream()
                .map(r -> new HistoryRecordDto(
                        r.getId(),
                        r.getCreatedAt(),
                        r.getEndpoint(),
                        r.getParameters(),
                        r.getResponse(),
                        r.getError()))
                .collect(Collectors.toList());
        Page<HistoryRecordDto> response = new PageImpl<>(items, pageable, result.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
