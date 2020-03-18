package com.example.todolist.model;
import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "CARD_NAME", nullable = false)
    private String name;

    @Column(name = "CARD_DESCRIPTION", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "CARD_BLOCKER")
    private Card blockerCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getBlockerCard() {
        return blockerCard;
    }

    public void setBlockerCard(Card blockerCard) {
        this.blockerCard = blockerCard;
    }
}
