package com.irems.vendingMachine.VendingMachine;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VendingMachineController {
    private final VendingMachine vendingMachine;

}
