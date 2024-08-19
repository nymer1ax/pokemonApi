package co.com.pokemon.jpa.pokemoncard;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemon_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_id", nullable = false, unique = true)
    private String cardId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "hp", nullable = false)
    private int hp;

    @Column(name = "max_hp", nullable = false)
    private int maxHp;

    @Column(name = "attack_name", nullable = false)
    private String attackName;

    @Column(name = "attack_damage", nullable = false)
    private int attackDamage;

    @Column(name = "weakness_type")
    private String weaknessType;

    @Column(name = "resistance_type")
    private String resistanceType;

    @Column(name = "retreat_cost")
    private String retreatCost;
}
