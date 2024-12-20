package fr.epita.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
public class BackendController {

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

    @GetMapping("/fibonacci/{n}")
    public ResponseEntity<String> fibonacci(@PathVariable("n") int n) {
        if (n < 0) {
            return ResponseEntity.badRequest().body("Error: Fibonacci sequence is not defined for negative numbers.");
        }
        final long result = computeFibonacci(n);
        final String response = "The Fibonacci number at position " + n + " is: " + result;
        return ResponseEntity.ok(response);
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
