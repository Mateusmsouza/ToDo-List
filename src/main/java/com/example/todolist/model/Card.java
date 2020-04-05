package com.example.todolist.model;
import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "card")
public class Card {

    public Card(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "CARD_NAME", nullable = false)
    private String name;

    @Column(name = "CARD_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CARD_STATUS", columnDefinition="default 'TODO")
    private String status;

    @OneToOne
    @JoinColumn(name = "CARD_BLOCKER", nullable = true)
    private Card blockerCard;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinTable( name = "user_app",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CARD_USER_OWNER") }
    )
    private User userCardOwner;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserCardOwner() {
        return userCardOwner;
    }

    public void setUserCardOwner(User userCardOwner) {
        this.userCardOwner = userCardOwner;
    }
}
