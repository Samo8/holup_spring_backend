package sk.zvjs.holup.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.zvjs.holup.calendar_event.CalendarEvent;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, nullable = false)
    private Long convictedNumber;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CalendarEvent> calendarEvents = new HashSet<>();
    private String apiKey;

    public User(Long convictedNumber, String password) {
        this.convictedNumber = convictedNumber;
        this.password = password;
    }

    public User(UUID id, Long convictedNumber, String password) {
        this.id = id;
        this.convictedNumber = convictedNumber;
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 50;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        setApiKey(generatedString);
    }
}
