package com.challenge.tenpo.mock;

import com.challenge.tenpo.rest.dto.PercentageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@Slf4j
@RequestMapping("/mock/percentage")
@AllArgsConstructor
public class PercentageController {


    @Operation(
            method = "Get",
            summary = "Mocked endpoint that return a random percentage ",
            description = "This is a mocked endpoint that returns a random percentage",
            responses = {
                    @ApiResponse(responseCode = "200"),
            }
    )
    @GetMapping
    public PercentageDTO getPercentage () {
        //Generate random within 0 and 100
        Double randomDouble = ThreadLocalRandom.current().nextDouble(0, 100);
        //Rounding to two decimals
        randomDouble = Math.floor(randomDouble * 100) / 100;
        return new PercentageDTO(randomDouble);

    }

}
