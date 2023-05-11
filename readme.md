# Demo versturen data naar API met Retrofit
Deze repo is een voorbeeld voor gebruik in de lessen
**Advanced Web & Mobile** in de bachelor opleiding **Toegepaste Informatica** van **Odisee**.

## Link met voorbeeld API *Web & Mobile*
Dit voorbeeld werkt met de API die we tijdens de eerste semester hebben gebruikt, met mogelijk
een kleine aanpassing.

De queries die in de API gebruikt worden, staan in commentaar in de relevante bestanden.

### Opvragen lijst Producten
Het SQL-statement in de API :
``` 
SELECT PR_ID, PR_CT_ID, PR_naam, PR_prijs, CT_OM
FROM producten2 
JOIN categorieen 
    ON PR_CT_ID = CT_ID
```
Aangezien hier geen gegevens van de gebruiker voor deze query nodig zijn, is hier geen prepared statement van gemaakt.

### Toevoegen Product
Het prepared-statement in de API :
``` 
INSERT INTO producten2 (PR_naam, PR_CT_ID, PR_prijs) 
VALUES (?,?,?)
```
Omdat hier wel met gegevens van de gebruiker wordt gewerkt, maken we hier wel gebruik van een prepared statement met placeholders.

