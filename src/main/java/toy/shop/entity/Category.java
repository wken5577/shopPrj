package toy.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence")
public class Category {

    @Id @GeneratedValue(generator = "category_sequence")
    private Long id;

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
