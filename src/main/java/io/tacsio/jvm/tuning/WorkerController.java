package io.tacsio.jvm.tuning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.ONE;

@RestController
@RequestMapping("/work")
public class WorkerController {

    private final WorkService workService;

    WorkerController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/fact/{val}")
    public ResponseEntity work(@PathVariable int val) {
        var result = workService.factorial(val);

        return ResponseEntity.ok("%s digits".formatted(result.toString().length()));
    }

    @GetMapping("/cpu")
    public ResponseEntity cpu() {
        workService.cpu();
        return ResponseEntity.ok("finished");
    }
}

@Service
class WorkService {
    private final Logger log = LoggerFactory.getLogger(WorkService.class);

    public void cpu() {
        var generator = new Random();
        double r = generator.nextFloat();
        double v = Math.sin(Math.cos(Math.sin(Math.cos(r))));
        log.debug("result {}", v);
    }

    public BigInteger factorial(int i) {
        if (i == 0 | i == 1) return ONE;

        BigInteger x = BigInteger.valueOf(i);
        BigInteger result = ONE;
        while (x.compareTo(ONE) != 0) {
            result = result.multiply(x);
            x = x.subtract(ONE);
        }

        return result;
    }
}
