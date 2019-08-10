package pl.margol.loadings.Loading;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long truckSetId;
}
