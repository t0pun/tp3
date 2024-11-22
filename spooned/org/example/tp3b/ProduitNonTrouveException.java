package org.example.tp3b;
import org.slf4j.Logger;
public class ProduitNonTrouveException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(ProduitNonTrouveException.class);

    public ProduitNonTrouveException(String message) {
        super(message);
    }
}