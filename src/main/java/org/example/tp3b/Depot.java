package org.example.tp3b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import java.util.Date;
import java.util.HashMap;

public class Depot {

    private HashMap<Integer, Produit> depot_produit = new HashMap<>();

    public Depot(){}

    public void ajouter_produit(Produit produit){
        if(depot_produit.containsKey(produit.getId())){
            throw new DuplicateKeyException("Cet ID est déja présent");
        }else {
            depot_produit.put(produit.getId(), produit);

        }
    }
    public void getDepot_produit() {
        for(int i=0; i<depot_produit.size(); i++){
            System.out.println(depot_produit.get(i));
        }
    }
    public void get_produitbyId(int id) throws ProduitNonTrouveException {
        for(int i=0; i<depot_produit.size(); i++){
            if(depot_produit.containsKey(id)){
                System.out.println(depot_produit.get(id));
            }else{
                throw new ProduitNonTrouveException("L'id '" + id + "' n'existe pas dans la HashMap.");
            }
        }
    }

    public void supprimer_produit(int id) throws ProduitNonTrouveException {
        if(depot_produit.containsKey(id)){
            depot_produit.remove(id);
        }else{
            throw new ProduitNonTrouveException("L'élément que tu veux supprimer n'existe pas");
        }
    }

    public void set_produit(int id, double prix, String date ) throws ProduitNonTrouveException {
        if(depot_produit.containsKey(id)){
            Produit p = depot_produit.get(id);
            p.setPrix_produit(prix);
            p.setDate_experiration(date);
        }else {
            throw new ProduitNonTrouveException("Le produit que tu veux modifier n'existe pas");
        }

    }

}
