package org.example.tp3b;
import java.util.HashMap;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
public class Depot {
    private HashMap<Integer, Produit> depot_produit = new HashMap<>();

    public Depot() {
    }

    public void ajouter_produit(Produit produit) {
        logger.info("Méthode ajouter_produit exécutée.");;
        if (depot_produit.containsKey(produit.getId())) {
            throw new DuplicateKeyException("Cet ID est déja présent");
        } else {
            depot_produit.put(produit.getId(), produit);
        }
    }

    public void getDepot_produit() {
        logger.info("Méthode getDepot_produit exécutée.");;
        for (int i = 0; i < depot_produit.size(); i++) {
            System.out.println(depot_produit.get(i));
        }
    }

    public void get_produitbyId(int id) throws ProduitNonTrouveException {
        logger.info("Méthode get_produitbyId exécutée.");;
        for (int i = 0; i < depot_produit.size(); i++) {
            if (depot_produit.containsKey(id)) {
                System.out.println(depot_produit.get(id));
            } else {
                throw new ProduitNonTrouveException(("L'id '" + id) + "' n'existe pas dans la HashMap.");
            }
        }
    }

    public void supprimer_produit(int id) throws ProduitNonTrouveException {
        logger.info("Méthode supprimer_produit exécutée.");;
        if (depot_produit.containsKey(id)) {
            depot_produit.remove(id);
        } else {
            throw new ProduitNonTrouveException("L'élément que tu veux supprimer n'existe pas");
        }
    }

    public void set_produit(int id, double prix, String date) throws ProduitNonTrouveException {
        logger.info("Méthode set_produit exécutée.");;
        if (depot_produit.containsKey(id)) {
            Produit p = depot_produit.get(id);
            p.setPrix_produit(prix);
            p.setDate_experiration(date);
        } else {
            throw new ProduitNonTrouveException("Le produit que tu veux modifier n'existe pas");
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Depot.class);
}