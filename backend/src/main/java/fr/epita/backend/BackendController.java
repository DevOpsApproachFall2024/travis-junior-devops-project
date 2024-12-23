package fr.epita.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
public class BackendController {
    private final ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Let's get rolling!!!");
    }

    /**
     * Computes the Fibonacci sequence up to the nth number.
     *
     * @param n The position in the Fibonacci sequence.
     * @return The nth Fibonacci number.
     */
    @CrossOrigin
    @GetMapping("/fibonacci/{n}")
    public ObjectNode fibonacci(@PathVariable("n") int n) {
        if (n < 0) {
            final ObjectNode error = mapper.createObjectNode();
            error.put("error", "n must be greater than 0");
            return error;
        }
        final long result = computeFibonacci(n);
        final ObjectNode objectnode = mapper.createObjectNode();
        objectnode.put("result", result);
        return objectnode;
    }

    // Helper method to compute Fibonacci
    final private long computeFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

}
